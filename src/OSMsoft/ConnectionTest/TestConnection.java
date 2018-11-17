package OSMsoft.ConnectionTest;

import OSMsoft.core.*;
import java.sql.*;
import java.util.ArrayList;

import OSMsoft.DAO.*;
import OSMsoft.Table.*;

/**
 * @author Taiho
 * TestConnection 该类负责测试ConnDB是否正常
 */
public class TestConnection {

    public static void main(String[] args) throws SQLException {
        ConnDB conn = new ConnDB();
        Double jobSalary,workAgeSalary;
        int i;
        ResultSet rs = conn.executeQuery("select * from salary");
        while(rs.next()){
            jobSalary = Double.valueOf(rs.getString(1));
            workAgeSalary = Double.valueOf(rs.getString(3));
            System.out.println("JobSalary: " + jobSalary);
            System.out.println("WorkAgeSalary: " + workAgeSalary);
        }


        //测试Admin相关的工作是否正常
        AdminDAO adminDAO = new AdminDAO();
        AdminTable adminTable = new AdminTable();
        adminTable = adminDAO.getAdminByAccount("Taiho");
        if(adminTable.getAccount() == null){
            System.out.println("There is no such admin");
        } else {
            System.out.println(adminTable.getAccount());
            System.out.println(adminTable.getPassword());
        }

        //测试Employee相关工作是否正常
        ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        EmployeeTable employeeTable = new EmployeeTable();
        employeeTable.setName("staff001");
        employeeTable.setAge(20);
        employeeTable.setPhoneNumber(136);
        employeeTable.setEmail("123@163.com");
        employeeTable.setJob("admin");
        employeeTable.setGender("male");
        employeeTable.setDepartmentID(5);
        employeeTable.setPassword("12345678");
        EmployeeDAO employeeDAO = new EmployeeDAO();
        //employeeDAO.insertNewEmployee(employeeTable);
        eList = employeeDAO.searchEmployeeByName("staff002");
        for(EmployeeTable et:eList){
            System.out.println("员工姓名"+et.getName());
        }

    }
}
