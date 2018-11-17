package OSMsoft.DepartmentAction;

import OSMsoft.DAO.DepartmentDAO;
import OSMsoft.Table.DepartmentTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author YocLu
 * Servlet implementation class SearchDepartment
 */
public class SearchDepartment extends HttpServlet {

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String searchType = request.getParameter("style");
        String searchTarget = request.getParameter("name");
        DepartmentDAO departmentDAO = new DepartmentDAO();
        DepartmentTable departmentTable = new DepartmentTable();
        ArrayList<DepartmentTable> dList  = new ArrayList<DepartmentTable>();
        System.out.println(searchType);
        System.out.println(searchTarget);
        if(searchType.equals("DepartmentName")) {
            dList=departmentDAO.queryDepartmentByDepname(searchTarget);
            for(int i=0;i<dList.size();i++) {
                departmentTable = (DepartmentTable) dList.get(i);
//                request.setAttribute("Status", departmentTable.getStatus());
//                System.out.println(departmentTable.getStatus());
            }
        }
    }
}
