package OSMsoft.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import OSMsoft.Table.*;
import OSMsoft.core.*;

/**
 * @author Taiho
 * EmployeeDAO 该类用于负责对数据库的操作
 */
public class EmployeeDAO {
    String sql = "";
    ConnDB conn;

    /**
     * 执行插入新员工的函数
     * @param employeeTable 要插入的员工信息
     */
    public void insertNewEmployee(EmployeeTable employeeTable){
        conn = new ConnDB();
        //定义sql语句
        sql = "insert into employee(name, workage, age, gender, phonenumber, password, job, depid, email) "
                +"values( \'"+employeeTable.getName()+"\', "//员工名
                +"\'0\', "//新插入的员工work age肯定为0
                +"\'"+employeeTable.getAge() +"\', "//员工年龄
                +"\'"+employeeTable.getGender()+"\', "//员工性别
                +"\'"+employeeTable.getPhoneNumber()+"\', "//员工电话
                +"\'"+000000+"\', "//员工密码,默认为000000
                +"\'"+employeeTable.getJob()+"\', "//员工职责
                +"\'"+employeeTable.getDepartmentID()+"\', "//员工所属部门
                +"\'"+employeeTable.getEmail()+"\') ";//员工电子邮箱

        //执行sql语句
        conn.executeQuery(sql);
        //执行结束关闭连接
        conn.close();
        System.out.println("添加employee的语句:"+sql);
    }

    /**
     * 备用函数，直接展示所有员工
     * @return 返回一个存储employeeTable的ArrayList，若没有员工，则employee的name属性是“no such employee”
     */
    public ArrayList<EmployeeTable> displayAll(){
        conn = new ConnDB();
        ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        sql = "select * from employee";
        ResultSet rs = conn.executeQuery(sql);
        addElements(eList, rs);
        System.out.println("查询employee的语句："+sql);
        conn.close();
        return eList;
    }

    /**
     * 利用id查询employee的方法
     * @param id 想要查询的employee的id
     * @return 返回一个存储employee数据的Table类，若没有找到该员工，则employee的name属性是“no such employee”
     * 可以在后续的操作中进行判断
     */
    public EmployeeTable searchEmployeeByID(int id){
        conn = new ConnDB();
        EmployeeTable employeeTable = new EmployeeTable();
        sql = "select * from employee where employeeid = \'"+id+"\'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                rs.previous();
                while (rs.next()){
                    employeeTable.setEmployeeID(Integer.parseInt(rs.getString(1)));
                    employeeTable.setName(rs.getString(2));
                    employeeTable.setWorkAge(Integer.parseInt(rs.getString(3)));
                    employeeTable.setAge(Integer.parseInt(rs.getString(4)));
                    employeeTable.setGender(rs.getString(5));
                    employeeTable.setPhoneNumber(Long.parseLong(rs.getString(6)));
                    employeeTable.setPassword(rs.getString(7));
                    employeeTable.setJob(rs.getString(8));
                    employeeTable.setDepartmentID(Integer.parseInt(rs.getString(9)));
                    employeeTable.setEmail(rs.getString(10));
                }
            }else{
                employeeTable.setName("no such employee");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("查询employee的语句："+sql);
        conn.close();
        return employeeTable;
    }

    /**
     * 利用员工姓名查找员工的方法
     * @param name 想要查找的员工的姓名
     * @return 返回一个存放了所有相同工作年龄的员工的ArrayList，若没有一个员工姓名与查询关键词匹配
     * 将创建一个Table类并setName为“no such employee”并将该Table放入List返回，可由后续程序进行处理
     */
    public ArrayList<EmployeeTable> searchEmployeeByName(String name){
        conn = new ConnDB();
        ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        sql = "select * from employee where name = \'"+name+"\'";
        ResultSet rs = conn.executeQuery(sql);
        addElements(eList, rs);
        System.out.println("查询employee的语句："+sql);
        conn.close();
        return eList;
    }

    /**
     * 利用员工工作年龄来查找员工
     * @param workAge 想要查找的工作年龄
     * @return 返回一个存放了所有相同年龄的员工的ArrayList，若没有一个员工工作年龄与查询关键词匹配
     * 将创建一个Table类并setName为“no such employee”并将该Table放入List返回，可由后续程序进行处理
     */
    public ArrayList<EmployeeTable> searchEmployeeByWorkAge(int workAge){
        conn = new ConnDB();
        ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        sql = "select * from employee where workage = \'"+workAge+"\'";
        ResultSet rs = conn.executeQuery(sql);
        addElements(eList, rs);
        System.out.println("查询employee的语句："+sql);
        conn.close();
        return eList;
    }

    /**
     * 利用员工年龄来查找员工
     * @param age 想要查找的年龄
     * @return 返回一个存放了所有相同姓名的员工的ArrayList，若没有一个员工年龄与查询关键词匹配
     * 将创建一个Table类并setName为“no such employee”并将该Table放入List返回，可由后续程序进行处理
     */
    public ArrayList<EmployeeTable> searchEmployeeByAge(int age){
        conn = new ConnDB();
        ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        sql = "select * from employee where age = \'"+age+"\'";
        ResultSet rs = conn.executeQuery(sql);
        addElements(eList, rs);
        System.out.println("查询employee的语句："+sql);
        conn.close();
        return eList;
    }

    /**
     * 利用员工性别来查找员工
     * @param gender 想要查找的员工性别
     * @return 返回一个存放了所有相同性别的员工的ArrayList，若没有一个员工性别与查询关键词匹配
     * 将创建一个Table类并setName为“no such employee”并将该Table放入List返回，可由后续程序进行处理
     */
    public ArrayList<EmployeeTable> searchEmployeeByGender(String gender){
        conn = new ConnDB();
        ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        sql = "select * from employee where gender = \'"+gender+"\'";
        ResultSet rs = conn.executeQuery(sql);
        addElements(eList, rs);
        System.out.println("查询employee的语句："+sql);
        conn.close();
        return eList;
    }

    /**
     * 利用部门来查找员工
     * @param depName 想要查找的部门名
     * @return 返回一个存放了所有相同部门的员工的ArrayList，若没有一个员工与查询关键词匹配
     * 将创建一个Table类并setName为“no such employee”并将该Table放入List返回，可由后续程序进行处理
     */
    public ArrayList<EmployeeTable> searchEmployeeByDepartment(String depName){
        conn = new ConnDB();
        ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        sql = "select * from employee where depid in ( "
            +"select depid from department where depname = \'"+depName+"\')";
        ResultSet rs = conn.executeQuery(sql);
        addElements(eList, rs);
        System.out.println("查询employee的语句："+sql);
        conn.close();
        return eList;
    }

    /**
     * 利用职位来查找员工
     * @param job 想要查找的职位
     * @return 返回一个存放了所有相同职位的员工的ArrayList，若没有一个员工与查询关键词匹配
     * 将创建一个Table类并setName为“no such employee”并将该Table放入List返回，可由后续程序进行处理
     */
    public ArrayList<EmployeeTable> searchEmployeeByJob(String job){
        conn = new ConnDB();
        ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        sql = "select * from employee where job = \'"+job+"\'";
        ResultSet rs = conn.executeQuery(sql);
        addElements(eList, rs);
        System.out.println("查询employee的语句："+sql);
        conn.close();
        return eList;
    }

    /**
     * 利用员工ID来删除员工
     * @param id 要删除的员工的ID
     * @return 返回一个布尔值，若为true，则删除成功,若为false，则删除失败，可能原因为数据库中不存在该员工
     */
    public boolean deleteEmployeeByID(int id){
        conn = new ConnDB();
        //初始化一个int类型的flag
        int flag = 0;
        sql = "delete from employee where employeeid = \'"+id+"\'";
        //执行删除语句，获取结果
        flag = conn.executeUpdate(sql);
        System.out.println("删除employee的语句："+sql);
        if(flag ==0){
            conn.close();
            return false;
        }else{
            System.out.println("delete successfully");
            conn.close();
            return true;
        }
    }

    /**
     * 利用员工ID更新密码的方法，非常暴力，直接将除了ID和password的信息全部更新
     * 这样一个函数就能满足更改各种信息的需求
     * @param employeeTable 传入参数，要更新信息的employee的相关信息存放的Table
     * @return 返回一个布尔值，若为true，则更新成功,若为false，则更新失败，可能原因为数据库中不存在该员工
     */
    public boolean updateEmployee(EmployeeTable employeeTable){
        conn = new ConnDB();
        int flag = 0;
        sql = "update employee set name = \'"+employeeTable.getName() + "\', "
                +"workage = \'"+employeeTable.getWorkAge()+"\', "
                +"age = \'"+employeeTable.getAge()+"\', "
                +"gender = \'"+employeeTable.getGender()+"\', "
                +"\"phoneNumber\" = \'"+employeeTable.getPhoneNumber()+"\', "
                +"job = \'"+employeeTable.getJob()+"\', "
                +"depid = \'"+employeeTable.getDepartmentID()+"\', "
                +"email = \'"+employeeTable.getEmail()+"\' "
                +"where employeeid = \'"+employeeTable.getEmployeeID()+"\'";
        flag = conn.executeUpdate(sql);
        System.out.println("更新employee的语句： "+sql);
        if(flag==0){
            conn.close();
            return false;
        }else{
            System.out.println("update successfully");
            conn.close();
            return true;
        }
    }

    /**
     * 利用员工ID更新员工密码的方法，配合上一个方法使用的话，就能更新除了ID外所有信息
     * 也可以单独使用只更新密码
     * @param employeeTable 传入参数，要更新信息的employee的相关信息存放的Table
     * @return 返回一个布尔值，若为true，则更新成功,若为false，则更新失败，可能原因为数据库中不存在该员工
     */
    public boolean updateEmployeePassword(EmployeeTable employeeTable){
        conn = new ConnDB();
        int flag = 0;
        sql = "update employee set password = \'"+employeeTable.getPassword()+"\' "
                +"where employeeid = \'"+employeeTable.getEmployeeID()+"\'";
        flag = conn.executeUpdate(sql);
        System.out.println("更新employee的password的语句： "+sql);
        if(flag == 0){
            conn.close();
            return false;
        }else{
            System.out.println("update password successfully");
            conn.close();
            return true;
        }
    }

    /**
     * 查表创建ArrayList的过程，重复代码故单独提出来
     * @param eList 要返回的ArrayList
     * @param rs ResultSet
     */
    private void addElements(ArrayList<EmployeeTable> eList, ResultSet rs) {
        try{
            if(rs.next()){
                rs.previous();
                while(rs.next()){
                    EmployeeTable employeeTable = new EmployeeTable();
                    employeeTable.setEmployeeID(Integer.parseInt(rs.getString(1)));
                    employeeTable.setName(rs.getString(2));
                    employeeTable.setWorkAge(Integer.parseInt(rs.getString(3)));
                    employeeTable.setAge(Integer.parseInt(rs.getString(4)));
                    employeeTable.setGender(rs.getString(5));
                    employeeTable.setPhoneNumber(Long.parseLong(rs.getString(6)));
                    employeeTable.setPassword(rs.getString(7));
                    employeeTable.setJob(rs.getString(8));
                    employeeTable.setDepartmentID(Integer.parseInt(rs.getString(9)));
                    employeeTable.setEmail(rs.getString(10));
                    eList.add(employeeTable);
                }
            }else{
                EmployeeTable employeeTable = new EmployeeTable();
                employeeTable.setName("no such employee");
                eList.add(employeeTable);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
