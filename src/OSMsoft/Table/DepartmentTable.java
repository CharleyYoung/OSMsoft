package OSMsoft.Table;

/**
 * @author Taiho
 * DepartmentTable 用于存放从数据库中读取出的Department元素的类
 * @author YocLu
 * 添加获取departmentID的方法
 */
public class DepartmentTable {
    private int departmentID;
    private String departmentName;
    private int parentDepartmentID;
    private String url;//changed by saulzhang 2018-11-18
    private String icon;//changed by saulzhang 2018-11-18

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

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
     * @return 返回当前Department的departmentID
     */
    public int getDepartmentID() {
        return this.departmentID;
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
