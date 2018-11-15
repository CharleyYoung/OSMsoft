package OSMsoft.ConnectionTest;

import OSMsoft.core.*;
import java.sql.*;
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
    }
}
