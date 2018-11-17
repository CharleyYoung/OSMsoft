package OSMsoft.DAO;

import OSMsoft.Table.DepartmentTable;
import OSMsoft.core.ConnDB;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author YocLu
 * DepartmentDAO 该类用于负责对数据库的操作
 */
public class DepartmentDAO {
    String sql = "";
    ConnDB conn = new ConnDB();

    /**
     * 添加department
     * @param departmentTable 添加的部门信息
     * @throws Exception
     */
    public void addDepartment(DepartmentTable departmentTable) throws Exception {
        sql = "insert into department(depname, parentdepid) values(\'"+departmentTable.getDepartmentName()
                +"\',\'"+departmentTable.getParentDepartmentID()+"\')";
        try {
            conn.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            conn.close();
        }
        System.out.println("添加department:"+sql);
    }

    /**查询department
     * @param depid 根据depid去数据库查询相应department
     * @return departmentTable 返回包含查询到的department信息的Table类
     */
    public DepartmentTable queryDepartmentByDepid(int depid){

        sql = "select * from department where depid = \'" + depid + "\'";
        ResultSet rs = conn.executeQuery(sql);
        DepartmentTable departmentTable = new DepartmentTable();
        try {
            if(rs.next()) {
                rs.previous();
                while (rs.next()){
                    departmentTable.setParentDepartmentID(rs.getInt(1));
                    departmentTable.setDepartmentName(rs.getString(2));
                    departmentTable.setParentDepartmentID(rs.getInt(3));
                }
            }else {
                departmentTable.setDepartmentName("no department");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return departmentTable;
    }

    /**
     * @param depname 根据depname去数据库查询相应department
     * @return departmentTable 返回包含查询到的department信息的Table类
     */
    public DepartmentTable queryDepartmentByDepname(String depname){

        sql = "select * from department where depname = \'" + depname + "\'";
        ResultSet rs = conn.executeQuery(sql);

        DepartmentTable departmentTable = new DepartmentTable();
        try {
            if(rs.next()) {
                rs.previous();
                while (rs.next()){
                    departmentTable.setParentDepartmentID(rs.getInt(1));
                    departmentTable.setDepartmentName(rs.getString(2));
                    departmentTable.setParentDepartmentID(rs.getInt(3));
                }
            }else {
                departmentTable.setDepartmentName("no department");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return departmentTable;
    }

    /**
     * @param parentdepid 根据parentdepid去数据库查询相应department
     * @return departmentTable 返回包含查询到的department信息的Table类
     * @throws java.sql.SQLException
     */
    public DepartmentTable queryDepartmentByParentdepid(int parentdepid) throws SQLException{
        sql = "select * from department where depid = \'" + parentdepid + "\'";
        ResultSet rs = conn.executeQuery(sql);
        DepartmentTable departmentTable = new DepartmentTable();
        try {
            if(rs.next()) {
                rs.previous();
                while (rs.next()){
                    departmentTable.setParentDepartmentID(rs.getInt(1));
                    departmentTable.setDepartmentName(rs.getString(2));
                    departmentTable.setParentDepartmentID(rs.getInt(3));
                }
            }else {
                departmentTable.setDepartmentName("no department");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return departmentTable;
    }

    /**
     * 更新部门信息
     * @param departmentTable 传入要跟新的部门
     * @return 返回一个布尔值
     */
    public boolean updateDepartment(DepartmentTable departmentTable){
        boolean flag = true;
        sql = "update department set depname = \'"+departmentTable.getDepartmentName()+
                "\', parentdepid = \'"+departmentTable.getParentDepartmentID()+
                "\' where depid = \'"+departmentTable.getDepartmentID()+"\'";
        try{
            conn.executeUpdate(sql);
        }catch (Exception e){
            flag = false;
            e.printStackTrace();
        }finally {
            conn.close();
        }
        return flag;
    }

    /**
     * 删除部门
     * @param depid 通过ID删除部门
     * @return 返回一个布尔值
     */
    public boolean deleteDepartmentByDepid(int depid){
        boolean flag = true;//函数返回值
        boolean flag2 = false;//判断待删除部门是否有子部门的标志
        int id = 1;
        while (id>0){
            DepartmentTable departmentTable = new DepartmentTable();
            departmentTable = queryDepartmentByDepid(id);
            int parentid= -1;
            if (departmentTable.getDepartmentName().equals("no department")){
                flag2 = true;
                break;
            }else{
                parentid = departmentTable.getParentDepartmentID();
                if (depid == parentid){
                    flag2 = true;
                    break;
                }else{
                    id ++ ;
                    continue;
                }
            }
        }

        if (flag2){
            sql = "delete from department where depid = \'" + depid + "\'";
            try{
                conn.executeUpdate(sql);
            }catch (Exception e){
                flag = false;
                e.printStackTrace();
            }finally {
                conn.close();
            }
        }else{
            flag = false;
        }

        return flag;
    }

    /**
     * 删除部门
     * @param depname 通过Name删除部门
     * @return 返回一个布尔值
     */
    public boolean deleteDepartmentByDepid(String depname){
        boolean flag = true;//函数返回值
        boolean flag2 = false;//判断待删除部门是否有子部门的标志
        DepartmentTable departmentTable2 = queryDepartmentByDepname(depname);
        int depid = departmentTable2.getDepartmentID();//获得待删除部门的ID
        int id = 1;
        while (id>0){
            DepartmentTable departmentTable = queryDepartmentByDepid(id);
            int parentid= -1;
            if (departmentTable.getDepartmentName().equals("no department")){
                flag2 = true;
                break;
            }else{
                parentid = departmentTable.getParentDepartmentID();
                if (depid == parentid){
                    flag2 = true;
                    break;
                }else{
                    id ++ ;
                    continue;
                }
            }
        }

        if (flag2){
            sql = "delete from department where depid = \'" + depname + "\'";
            try{
                conn.executeUpdate(sql);
            }catch (Exception e){
                flag = false;
                e.printStackTrace();
            }finally {
                conn.close();
            }
        }else{
            flag = false;
        }

        return flag;
    }

}
