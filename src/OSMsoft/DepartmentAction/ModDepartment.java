package OSMsoft.DepartmentAction;

import OSMsoft.DAO.DepartmentDAO;
import OSMsoft.Table.DepartmentTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author YocLu
 * Servlet implementation class modDepartment
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

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int depid =Integer.valueOf(request.getParameter("depid"));
        String depname=request.getParameter("depname");
        int parentdepid =Integer.valueOf(request.getParameter("parentdepid"));
        System.out.println(depname);
        DepartmentDAO departmentDAO = new DepartmentDAO();
        DepartmentTable departmentTable = new DepartmentTable();
        departmentTable.setDepartmentName(depname);
        departmentTable.setParentDepartmentID(parentdepid);
        boolean flag = departmentDAO.updateDepartment(departmentTable);
        PrintWriter out = response.getWriter();
        if (flag) {
            out.print("<script>alert('Modify department successfully!');window.location='modDepartment.jsp'</script>");
        } else {
            out.print("<script>alert('Modify department unsuccessfully!');window.location='modDepartment.jsp'</script>");
        }

    }

}
