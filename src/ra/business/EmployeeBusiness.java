package ra.business;

import ra.entity.Department;
import ra.entity.Employee;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class EmployeeBusiness {
    public static List<Employee> getAllEmployee(){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> employees = null;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call get_all_employee()}");
            ResultSet rs = callSt.executeQuery();
            employees = new ArrayList<>();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setEmployeeName(rs.getString("employee_name"));
                employee.setEmployeePosition(rs.getString("employee_position"));
                employee.setEmployeeSalary(rs.getDouble("employee_salary"));
                employee.setEmployeeHireDate(rs.getDate("employee_hire_date"));
                employees.add(employee);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employees;
    }
    public static boolean addEmployee(Employee employee,int departmentId){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.getConnection();
            java.sql.Date hireDate = new java.sql.Date(employee.getEmployeeHireDate().getTime());
            callSt = conn.prepareCall("{call add_new_employee(?,?,?,?,?)}");
            callSt.setString(1, employee.getEmployeeName());
            callSt.setString(2, employee.getEmployeePosition());
            callSt.setDouble(3, employee.getEmployeeSalary());
            callSt.setDate(4, hireDate);
            callSt.setInt(5, departmentId);
            callSt.executeUpdate();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean isExistEmployee(int employeeId, String employeeName){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean isExist = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call employee_is_exist(?,?,?)}");
            callSt.setInt(1, employeeId);
            callSt.setString(2, employeeName);
            callSt.registerOutParameter(3, Types.BOOLEAN);
            callSt.execute();
            isExist = callSt.getBoolean(3);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return isExist;
    }

    public static Employee findEmployeeByName(String employeeName){
        Connection conn = null;
        CallableStatement callSt = null;
        Employee employee = null;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call find_employee_by_name(?)}");
            callSt.setString(1, employeeName);
            ResultSet rs = callSt.executeQuery();
            while(rs.next()){
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setEmployeeName(rs.getString("employee_name"));
                employee.setEmployeePosition(rs.getString("employee_position"));
                employee.setEmployeeSalary(rs.getDouble("employee_salary"));
                employee.setEmployeeHireDate(rs.getDate("employee_hire_date"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employee;
    }

    public static boolean deleteEmployee(int employeeId){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call delete_employee(?)}");
            callSt.setInt(1, employeeId);
            callSt.executeUpdate();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateEmployee(Employee employee){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call update_employee(?,?,?,?)}");
            callSt.setString(1, employee.getEmployeeName());
            callSt.setString(2, employee.getEmployeePosition());
            callSt.setDouble(3, employee.getEmployeeSalary());
            callSt.setInt(4, employee.getEmployeeId());
            callSt.executeUpdate();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
}
