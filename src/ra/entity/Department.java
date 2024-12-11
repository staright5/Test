package ra.entity;

import ra.business.DepartmentBusiness;

import java.util.Scanner;

public class Department implements HumanResoureManager {
    private int departmentId;
    private String departmentName;
    private boolean departmentStatus;

    public Department() {
    }

    public Department(int department_id, String departmentName, boolean departmentStatus) {
        this.departmentId = department_id;
        this.departmentName = departmentName;
        this.departmentStatus = departmentStatus;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int department_id) {
        this.departmentId = department_id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public boolean isDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(boolean departmentStatus) {
        this.departmentStatus = departmentStatus;
    }


    @Override
    public void inputData(Scanner scanner) {
        this.departmentName = inputDepartmentName(scanner);
    }
    public String inputDepartmentName(Scanner scanner) {
        System.out.println("Nhập tên Department: ");
        do{
            String departmentName = scanner.nextLine();
            boolean isExist = DepartmentBusiness.isExistDepartment(0,departmentName);
            if(isExist){
                System.out.println("Tên Department đã tồn tại!");
            }else{
                return departmentName;
            }
        }while(true);
    }
    @Override
    public void display() {
        System.out.printf("Mã Deparment: %d - tên Department: %s - Trạng thái: %s\n",this.departmentId,this.departmentName,this.departmentStatus?"Đang hoạt động":"Ngừng Hoạt Động");
    }
}
