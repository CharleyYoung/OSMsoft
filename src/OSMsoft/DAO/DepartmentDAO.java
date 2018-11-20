package OSMsoft.DAO;

import OSMsoft.Table.*;
import OSMsoft.core.ConnDB;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author YocLu
 * DepartmentDAO 该类用于负责对数据库的操作
 */
public class DepartmentDAO {
    String sql = "";
    ConnDB conn;

    /**
     * 添加department
     * @param departmentTable 添加的部门信息
     * @throws Exception
     */
    public void addDepartment(DepartmentTable departmentTable) {
        conn = new ConnDB();
        sql = "insert into department(depname, parentdepid, url) values(\'"+departmentTable.getDepartmentName()
                +"\',\'"+departmentTable.getParentDepartmentID()+"\', \'"+departmentTable.getUrl()+"\')";
        try {
            conn.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            conn.close();
        }
        System.out.println("添加department:"+sql);
    }

    /**
     * @param dList 包含DepartmentTable的ArrayList列表
     * @param rs ResultSet
     */
    private void addElements(ArrayList<DepartmentTable> dList, ResultSet rs){
        try{
            if(rs.next()){
                rs.previous();
                while (rs.next()){
                    DepartmentTable departmentTable = new DepartmentTable();
                    departmentTable.setDepartmentID(Integer.parseInt(rs.getString(1)));
                    departmentTable.setDepartmentName(rs.getString(2));
                    departmentTable.setParentDepartmentID(Integer.parseInt(rs.getString(3)));
                    dList.add(departmentTable);
                }
            }else {
                DepartmentTable departmentTable = new DepartmentTable();
                departmentTable.setDepartmentName("no such department");
                dList.add(departmentTable);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 返回所有department
     * @return dList 返回包含查询到的department信息的ArrayList<DepartmentTable>
     */
    public ArrayList<DepartmentTable> returnAllDepartment(){
        conn = new ConnDB();
        ArrayList<DepartmentTable> dList = new ArrayList<DepartmentTable>();
        sql = "select * from department";
        ResultSet rs = conn.executeQuery(sql);
        addElements(dList, rs);
        System.out.println("查询department的语句："+sql);
        conn.close();
        return  dList;
    }

    /**查询department
     * @param depid 根据depid去数据库查询相应department
     * @return dList 返回包含查询到的department信息的ArrayList<DepartmentTable>
     */
    public ArrayList<DepartmentTable> queryDepartmentByDepid(int depid){
        conn = new ConnDB();
        ArrayList<DepartmentTable> dList = new ArrayList<DepartmentTable>();
        sql = "select * from department where depid = \'" + depid + "\'";
        ResultSet rs = conn.executeQuery(sql);
        addElements(dList, rs);
        System.out.println("查询department的语句："+sql);
        conn.close();
        return dList;
    }

    /**
     * @param depname 根据depname去数据库查询相应department
     * @return dList 返回包含查询到的department信息的ArrayList<DepartmentTable>
     */
    public ArrayList<DepartmentTable> queryDepartmentByDepname(String depname){
        conn = new ConnDB();
        ArrayList<DepartmentTable> dList = new ArrayList<DepartmentTable>();
        sql = "select * from department where depname = \'" + depname + "\'";
        ResultSet rs = conn.executeQuery(sql);
        addElements(dList, rs);
        System.out.println("查询department的语句："+sql);
        conn.close();
        return dList;
    }

    /**
     * @param parentdepid 根据parentdepid去数据库查询相应department
     * @return dList 返回包含查询到的department信息的ArrayList<DepartmentTable>
     */
    public ArrayList<DepartmentTable> queryDepartmentByParentdepid(int parentdepid){
        conn = new ConnDB();
        ArrayList<DepartmentTable> dList = new ArrayList<DepartmentTable>();
        sql = "select * from department where parentdepid = \'" + parentdepid + "\'";
        ResultSet rs = conn.executeQuery(sql);
        addElements(dList, rs);
        System.out.println("查询department的语句："+sql);
        conn.close();
        return dList;
    }

    /**
     * 更新部门信息
     * @param departmentTable 传入要跟新的部门
     * @return 返回一个布尔值
     */
    public boolean updateDepartment(DepartmentTable departmentTable){
        conn = new ConnDB();
        boolean flag = true;
        sql = "update department set depname = \'"+departmentTable.getDepartmentName()+
                "\' where depid = \'"+departmentTable.getDepartmentID()+"\'";
        System.out.println("更新sql:"+sql);
        try{
            conn.executeUpdate(sql);
            System.out.println("更新成功");
        }catch (Exception e){
            flag = false;
            e.printStackTrace();
            System.out.println("更新失败");
        }finally {
            conn.close();
        }
        return flag;
    }

    /**
     * 判断部门是否含有子部门
     * @param depid 待判断的部门ID
     * @return 返回一个布尔值
     */
    public boolean hasSonDepartment(int depid){
        boolean flag = false;//判断待删除部门是否有子部门的标志，true表示没有子部门，false表示有子部门
        int id = 1;
        while (id>0){
            DepartmentTable departmentTable = queryDepartmentByDepid(id).get(0);
            int parentid= -1;
            if (departmentTable.getDepartmentName().equals("no such department")){
                flag = true;
                break;
            }else{
                parentid = departmentTable.getParentDepartmentID();
                if (parentid == depid){//如果有子部门的parentID等于待删除部门的ID
                    flag = false;
                    break;
                }else{
                    id ++ ;
                    continue;
                }
            }
        }
        return flag;
    }

    /**
     * 删除部门
     * @param depid 通过ID删除部门
     * @return 返回一个布尔值
     */
    public boolean deleteDepartmentByDepid(int depid){
        conn = new ConnDB();
        boolean flag = true;//函数返回值,默认删除成功

        //判断待删除部门是否有员工
        EmployeeDAO employeeDAO = new EmployeeDAO();
        String depname = queryDepartmentByDepid(depid).get(0).getDepartmentName();
        ArrayList<EmployeeTable> eList = employeeDAO.searchEmployeeByDepartment(depname);//获取部门员工列表
//        if(hasSonDepartment(depid)){
//            System.out.println("没有子部门");
//        }else{
//            System.out.println("有子部门");
//        }
//        if( eList.get(0).getName().equals("no such employee")){
//            System.out.println("没有员工");
//        }else{
//            System.out.println("有员工");
//            System.out.println(eList.get(0).getName());
//        }

        //当待删除部门既没有子部门又没有员工时可以执行删除的sql语句
        if (hasSonDepartment(depid) && eList.get(0).getName().equals("no such employee")){
            sql = "delete from department where depid = \'" + depid + "\'";
            try{
                conn.executeUpdate(sql);
                //System.out.println("删除成功");
            }catch (Exception e){
                flag = false;
                e.printStackTrace();
                //System.out.println("删除失败");
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
    public boolean deleteDepartmentByDepname(String depname){
        conn = new ConnDB();
        boolean flag = true;//函数返回值

        //判断待删除部门是否有员工
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList<EmployeeTable> eList = employeeDAO.searchEmployeeByDepartment(depname);//获取部门员工列表

        DepartmentTable departmentTable2 = queryDepartmentByDepname(depname).get(0);
        int depid = departmentTable2.getDepartmentID();//获得待删除部门的ID
        System.out.println("待删除部门的ID"+depid);

        //当待删除部门既没有子部门又没有员工时可以执行删除的sql语句
        if (hasSonDepartment(depid) && eList.get(0).getName().equals("no such employee")){
            sql = "delete from department where depname = \'" + depname + "\'";
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
