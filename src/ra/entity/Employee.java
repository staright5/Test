package ra.entity;

import ra.business.EmployeeBusiness;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Employee extends Department implements HumanResoureManager{
   private int employeeId;
   private String employeeName;
   private String employeePosition;
   private double employeeSalary;
   private Date employeeHireDate;

    public Employee() {
    }

    public Employee(int employeeId, String employeeName, String employeePosition, double employeeSalary, Date employeeHireDate) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.employeeSalary = employeeSalary;
        this.employeeHireDate = employeeHireDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Date getEmployeeHireDate() {
        return employeeHireDate;
    }

    public void setEmployeeHireDate(Date employeeHireDate) {
        this.employeeHireDate = employeeHireDate;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.employeeName = inputEmployeeName(scanner);
        System.out.println("Nhập ví trí employee:");
        this.employeePosition = scanner.nextLine();
        System.out.println("Nhập số lương:");
        this.employeeSalary = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập ngày thuê(YYYY-MM-DD):");
        this.employeeHireDate = getEmployeeHireDate(scanner);
        System.out.println("Nhập phòng ban:");
        super.setDepartmentId(Integer.parseInt(scanner.nextLine()));
    }
    public String inputEmployeeName(Scanner scanner) {
        System.out.println("Nhập tên Employee: ");
        do{
            String employeeName = scanner.nextLine();
            boolean isExit = EmployeeBusiness.isExistEmployee(0,employeeName);
            if(isExit){
                System.out.println("Tên Employee đã tồn tại!");
            }else{
                return employeeName;
            }
        }while(true);
    }
    public Date getEmployeeHireDate(Scanner scanner){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date hireDate = null;
        do{
            try{
                String dateInput = scanner.nextLine();
                hireDate = sdf.parse(dateInput);
                break;
            }catch(Exception e){
                System.out.println("Ngày không hợp lệ vui lòng nhập lại!");
            }
        }while(true);
        return hireDate;
    }

    @Override
    public void display() {
        System.out.printf("Mã NV: %d - Tên NV: %s - Vị trí NV: %s - Lương: %f - Ngày thuê: %s\n", this.employeeId, this.employeeName, this.employeePosition, this.employeeSalary, this.employeeHireDate);
    }
}
