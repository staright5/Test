package ra.business;

import ra.entity.Department;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DepartmentBusiness {
    public static List<Department> getAllDepartment(){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Department> departments = null;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call get_all_department()}");
            ResultSet rs = callSt.executeQuery();
            departments = new ArrayList<>();
            while(rs.next()){
                Department department = new Department();
                department.setDepartmentId(rs.getInt("department_id"));
                department.setDepartmentName(rs.getString("department_name"));
                department.setDepartmentStatus(rs.getBoolean("department_status"));
                departments.add(department);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return departments;
    }

    public static boolean addDepartment(Department department){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call add_new_department(?)}");
            callSt.setString(1, department.getDepartmentName());
            callSt.executeUpdate();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateDepartment(Department department){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call update_department(?,?)}");
            callSt.setInt(1, department.getDepartmentId());
            callSt.setString(2, department.getDepartmentName());
            callSt.executeUpdate();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean deleteDepartment(int departmentId){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call delete_department(?)}");
            callSt.setInt(1, departmentId);
            callSt.executeUpdate();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean isExistDepartment(int departmentId, String departmentName){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean isExist = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call department_is_exist(?,?,?)}");
            callSt.setInt(1, departmentId);
            callSt.setString(2, departmentName);
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

    public static Department findDepartmentById(int departmentId){
        Connection conn = null;
        CallableStatement callSt = null;
        Department department = null;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call find_department_by_id(?)}");
            callSt.setInt(1, departmentId);
            ResultSet rs = callSt.executeQuery();
            while(rs.next()){
                department = new Department();
                department.setDepartmentId(rs.getInt("department_id"));
                department.setDepartmentName(rs.getString("department_name"));
                department.setDepartmentStatus(rs.getBoolean("department_status"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return department;
    }

}
