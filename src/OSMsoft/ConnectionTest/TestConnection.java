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
//        Double jobSalary,workAgeSalary;
//        String phonenumber;
//        int i;
//        ResultSet rs = conn.executeQuery("select * from employee where workage = '3'");
//
//        while(rs.next()){
//            phonenumber = String.valueOf(rs.getString(6));
//            //workAgeSalary = Double.valueOf(rs.getString(3));
//            System.out.println("phonenumber: " + phonenumber);
////            System.out.println("WorkAgeSalary: " + workAgeSalary);
//        }

        //ResultSet rs1 = conn.executeQuery("select * from employee");
        EmployeeDAO employeeDAO = new EmployeeDAO();

        ArrayList<EmployeeTable> employeeTable = new ArrayList<EmployeeTable>();

        employeeTable = employeeDAO.searchEmployeeByName("ckwk");

        for (EmployeeTable et : employeeTable) {
            System.out.println(et.getName());
        }

        //测试Admin相关的工作是否正常
/*        AdminDAO adminDAO = new AdminDAO();
        AdminTable adminTable = new AdminTable();
        adminTable = adminDAO.getAdminByAccount("admin");
        if(adminTable.getAccount() == null){
            System.out.println("There is no such admin");
        } else {
            System.out.println(adminTable.getAccount());
            System.out.println(adminTable.getPassword());
        }*/
    }
}
