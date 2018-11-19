package OSMsoft.tag;

import OSMsoft.DAO.DepartmentDAO;
import OSMsoft.Table.DepartmentTable;
//import com.sun.deploy.net.HttpRequest;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Taiho
 * DepartmentReader 该类为自定义标签，用于从数据库中获取department信息
 */
public class DepartmentReader extends SimpleTagSupport {

    //采用doTag方法实现从DAO中读取department信息
    public void doTag() throws JspException, IOException{
        DepartmentDAO departmentDAO = new DepartmentDAO();
        ArrayList<DepartmentTable> dList = departmentDAO.returnAllDepartment();
        for(DepartmentTable dTable:dList){
            System.out.println(dTable.getDepartmentName());
        }
        //利用jspContext来设置Attribute,作用范围为page
        getJspContext().setAttribute("departmentInformation",dList);
    }
}
