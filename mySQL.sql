create database quanlynhansu;
use quanlynhansu;

create table Departments(
    department_id int primary key auto_increment,
    department_name varchar(50) not null unique,
    department_status Bit default 1 -- 1:Đang hoạt động
);

create table Employee(
    employee_id int primary key auto_increment,
    employee_name varchar(50) not null unique,
    employee_position varchar(50) not null,
    employee_salary double not null check(employee_salary>0),
    employee_hire_date date not null,
    department_id int not null,
    foreign key (department_id) references Departments(department_id)
);

-- Department List
delimiter &&
create procedure get_all_department()
begin
    select * from Departments where department_status = 1;
end &&
delimiter &&;
--

-- Addnew Department
delimiter &&
create procedure add_new_department(department_name_in varchar(50))
begin
    insert into Departments(department_name) values (department_name_in);
end &&
delimiter &&;
-- Update Department
delimiter &&
create procedure update_department(department_id_in int,department_name_in varchar(50))
begin
    update Departments set department_name = department_name_in
                       where department_id = department_id_in;
end &&
delimiter &&;

-- check if exist
delimiter &&
create procedure department_is_exist(department_id_in int, department_name_in varchar(50), OUT is_exist bit)
begin
    declare cnt_department int;
    if department_id_in != 0 then
        -- For update
        set cnt_department = (select count(department_name) from Departments where department_name like department_name_in and department_id != department_id_in);
    else
        -- For addnew
        set cnt_department = (select count(department_name)from Departments where department_name like department_name_in );
    end if;
    if cnt_department = 0 then
        set is_exist = 0;
    else
        set is_exist = 1;
    end if;
end &&
delimiter &&;

--

-- Delete Department
delimiter &&
create procedure delete_department(department_id_in int)
begin
    update Departments set department_status = 0 where department_id = department_id_in;
end &&
delimiter &&;
--

-- Find department by id
delimiter &&
create procedure find_department_by_id(department_id_in int)
begin
    select * from Departments where department_id = department_id_in;
end &&
delimiter &&;
--

-- Department with max employee
# delimiter &&
# create procedure
# delimiter &&;


# Danh sách nhân viên
delimiter &&
create procedure get_all_employee()
begin
    select * from Employee;
end &&
delimiter &&;
# Thêm mới nhân viên
delimiter &&
create procedure add_new_employee(employee_name_in varchar(50),employee_position_in varchar(50),employee_salary_in double,employee_hire_date_in date,department_id_in int)
begin
    insert into Employee(employee_name, employee_position, employee_salary, employee_hire_date, department_id) values (employee_name_in,employee_position_in,employee_salary_in,employee_hire_date_in,department_id_in) ;
end &&
delimiter &&;
# Cập nhật thông tin nhân viên
delimiter &&
create procedure update_employee(employee_name_in varchar(50),employee_position_in varchar(50),employee_salary_in double, employee_id_in int)
begin
    update Employee set employee_name = employee_name_in, employee_position = employee_position_in, employee_salary = employee_salary_in
    where employee_id = employee_id_in;
end &&
delimiter &&;
# Xóa nhân viên
delimiter &&
create procedure delete_employee(employee_id_in int)
begin
    delete from Employee where employee_id = employee_id_in;
end &&
delimiter &&;
# Hiển thị danh sách nhân viên theo phòng ban
delimiter &&
create procedure get_employee_by_department(employee_id_in int)
begin
    select e.employee_id, e.employee_name,e.employee_position,e.employee_salary,e.employee_hire_date,e.department_id as department from Employee e inner join Departments d on e.department_id = d.department_id where e.department_id = employee_id_in;
end &&
delimiter &&;
# Tìm kiếm nhân viên theo tên.
delimiter &&
create procedure find_employee_by_name(employee_name_in varchar(50))
begin
    select * from Employee where employee_name = employee_name_in;
end &&
delimiter &&;
# Hiển thị top 5 nhân viên có lương cao nhất
delimiter &&
create procedure top_5_higest_salary_employee()
begin
    select e.employee_id, e.employee_name, e.employee_salary from Employee e order by employee_salary DESC limit 5;
end &&
delimiter &&;
# Thoát
delimiter &&
create procedure employee_is_exist(employee_id_in int, employee_name_in varchar(50), OUT is_exist bit)
begin
    declare cnt_department int;
    if employee_id_in != 0 then
        -- For update
        set cnt_department = (select count(employee_name) from Employee where Employee.employee_name like employee_name_in and Employee.employee_id != employee_id_in);
    else
        -- For addnew
        set cnt_department = (select count(employee_name)from Employee where Employee.employee_name like employee_id_in );
    end if;
    if cnt_department = 0 then
        set is_exist = 0;
    else
        set is_exist = 1;
    end if;
end &&
delimiter &&;


