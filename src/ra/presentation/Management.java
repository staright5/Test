package ra.presentation;

import ra.business.DepartmentBusiness;
import ra.business.EmployeeBusiness;
import ra.entity.Department;
import ra.entity.Employee;

import java.util.List;
import java.util.Scanner;

public class Management {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************HR-MANAGEMENT******************");
            System.out.println("1. Quản lý phòng ban");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> displayDepartmentMenu(scanner);
                case 2 -> displayEmployeeMenu(scanner);
                case 3 -> System.exit(0);
                default -> System.out.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public static void displayDepartmentMenu(Scanner scanner) {
        do {
            System.out.println("**********************DEPARTMENT-MENU********************");
            System.out.println("1. Danh sách phòng ban");
            System.out.println("2. Thêm mới phòng ban");
            System.out.println("3. Cập nhật thông tin phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Hiển thị phòng ban có nhiều nhân viên nhất");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> departmentList();
                case 2 -> addNewDepartment(scanner);
                case 3 -> updateDepartment(scanner);
                case 4 -> deleteDepartment(scanner);
                //case 5 ->
                case 6 -> {
                    System.out.println("Quay lại Menu HR-MANAGEMENT...");
                    return;
                }
                default -> System.out.println("Vui lòng chọn từ 1-6");
            }
        } while (true);
    }

    public static void displayEmployeeMenu(Scanner scanner) {
        do{
            System.out.println("************************EMPLOYEE-MENU********************");
            System.out.println("1. Danh sách nhân viên");
            System.out.println("2. Thêm mới nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Xóa nhân viên ");
            System.out.println("5. Hiển thị danh sách nhân viên theo phòng ban");
            System.out.println("6. Tìm kiếm nhân viên theo tên");
            System.out.println("7. Hiển thị top 5 nhân viên có lương cao nhất");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> EmployeeList();
                case 2 -> addNewEmployee(scanner);
                case 3 -> updateEmployee(scanner);
                case 4 -> deleteEmployee(scanner);
                //case 5 ->
                case 6 -> findEmployeeByName(scanner);
                //case 7 ->
                case 8 -> {
                    System.out.println("Quay lại Menu HR-MANAGEMENT...");
                    return;
                }
                default -> System.out.println("Vui lòng chọn từ 1-8");
            }
        }while(true);
    }

    //Department-Menu
    public static void departmentList() {
        List<Department> departmentList = DepartmentBusiness.getAllDepartment();
        departmentList.stream().forEach(Department::display);
    }

    public static void addNewDepartment(Scanner scanner) {
        Department newDepartment = new Department();
        newDepartment.inputData(scanner);
        boolean result = DepartmentBusiness.addDepartment(newDepartment);
        if (result) {
            System.out.println("Đã thêm mới Department");
        } else {
            System.err.println("Thêm mới thất bại!");
        }
    }

    public static void updateDepartment(Scanner scanner) {
        System.out.println("Nhập vào mã của Department cần cập nhật:");
        int departmentId = Integer.parseInt(scanner.nextLine());
        Department department = DepartmentBusiness.findDepartmentById(departmentId);
        if (department != null) {
            System.out.println("Nhập vào tên Department:");
            boolean isExitDepartmentName = true;
            do {
                String departmentName = scanner.nextLine();
                boolean isExit = DepartmentBusiness.isExistDepartment(departmentId, departmentName);
                if (isExit) {
                    System.err.println("Tên Department đã tồn tại, vui lòng nhập tên khác!");
                } else {
                    department.setDepartmentName(departmentName);
                    isExitDepartmentName = false;
                }
            } while (isExitDepartmentName);
            boolean result = DepartmentBusiness.updateDepartment(department);
            if (result) {
                System.out.println("Cập Nhật thành công");
            }else{
                System.out.println("Cập Nhật thất bại!");
            }
        } else {
            System.err.println("Mã Department không tồn tại!");
        }
    }

    public static void deleteDepartment(Scanner scanner) {
        System.out.println("Nhập vào mã của Department cần xóa:");
        int departmentId = Integer.parseInt(scanner.nextLine());
        Department department = DepartmentBusiness.findDepartmentById(departmentId);
        if (department != null) {
            boolean result = DepartmentBusiness.deleteDepartment(departmentId);
            if (result) {
                System.out.println("Đã xóa thành công");
            } else {
                System.out.println("Xóa thất bại");
            }
        } else {
            System.err.println("Mã Department không tồn tại!");
        }
    }

    //Employee-Menu
    public static void EmployeeList() {
        List<Employee> employeeList = EmployeeBusiness.getAllEmployee();
        employeeList.stream().forEach(Employee::display);
    }

    public static void addNewEmployee(Scanner scanner) {
        Employee newEmployee = new Employee();
        newEmployee.inputData(scanner);
        int departmentId = newEmployee.getDepartmentId();
        boolean result = EmployeeBusiness.addEmployee(newEmployee,departmentId);
        if (result) {
            System.out.println("Đã thêm mới Department");
        } else {
            System.err.println("Thêm mới thất bại!");
        }
    }
    public static void updateEmployee(Scanner scanner){
        System.out.println("Nhập vào tên của Employee cần cập nhật:");
        String employeeName = scanner.nextLine();
        Employee employee = EmployeeBusiness.findEmployeeByName(employeeName);
        if(employee != null){
            System.out.println("Nhập tên mới cho Employee: ");
            employee.setEmployeeName(scanner.nextLine());
            System.out.println("Nhập vị trí mới cho Employee: ");
            employee.setEmployeePosition(scanner.nextLine());
            System.out.println("Nhập lương mới cho Employee: ");
            employee.setEmployeeSalary(Double.parseDouble(scanner.nextLine()));
            boolean result = EmployeeBusiness.updateEmployee(employee);
            if (result) {
                System.out.println("Cập nhật thành công");
            }else{
                System.out.println("Cập nhật thất bại!");
            }
        }else{
            System.err.println("Tên Employee không tồn tại!");
        }
    }

    public static void deleteEmployee(Scanner scanner) {
        System.out.println("Nhập vào tên của employee cần xóa:");
        String employeeName = scanner.nextLine();
        Employee employee = EmployeeBusiness.findEmployeeByName(employeeName);
        if (employee != null) {
            boolean result = EmployeeBusiness.deleteEmployee(employee.getEmployeeId());
            if (result) {
                System.out.println("Đã xóa thành công");
            } else {
                System.out.println("Xóa thất bại");
            }
        } else {
            System.err.println("Tên Employee không tồn tại!");
        }
    }
    public static void findEmployeeByName(Scanner scanner){
        System.out.println("Nhập vào tên Employee cần tìm: ");
        String employeeName = scanner.nextLine();
        Employee employee = EmployeeBusiness.findEmployeeByName(employeeName);
        if (employee != null) {
            employee.display();
        }else{
            System.err.println("Tên Employee không tồn tại");
        }
    }
}
