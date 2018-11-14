package OSMsoft.Table;

/**
 * @author Taiho
 * DepartmentTable 用于存放从数据库中读取出的Department元素的类
 */
public class DepartmentTable {
    private int departmentID;
    private String departmentName;
    private int parentDepartmentID;

    /**
     *
     * @param departmentName 要设置的DepartmentName
     */
    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }

    /**
     *
     * @param parentDepartmentID 要设置的ParentDepartmentID
     */
    public void setParentDepartmentID(int parentDepartmentID){
        this.parentDepartmentID = parentDepartmentID;
    }

    /**
     *
     * @return 返回当前Department的departmentName
     */
    public String getDepartmentName(){
        return this.departmentName;
    }

    /**
     *
     * @return 返回当前Department的parentDepartmentID
     */
    public int getParentDepartmentID(){
        return this.parentDepartmentID;
    }
}