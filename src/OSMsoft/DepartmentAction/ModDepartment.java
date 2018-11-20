package OSMsoft.DepartmentAction;

import OSMsoft.DAO.DepartmentDAO;
import OSMsoft.Table.DepartmentTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YocLu
 * Servlet implementation class ModDepartment
 */
public class ModDepartment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModDepartment() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        int depid = Integer.parseInt(String.valueOf(request.getParameter("depid")));
        DepartmentDAO departmentDAO = new DepartmentDAO();
        DepartmentTable departmentTable = departmentDAO.queryDepartmentByDepid(depid).get(0);
        request.setAttribute("Department",departmentTable);
        request.getRequestDispatcher("ModDepartment.jsp").forward(request,response);
    }

}
