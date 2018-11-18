package OSMsoft.DepartmentAction;

import OSMsoft.DAO.DepartmentDAO;
import OSMsoft.Table.DepartmentTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author YocLu
 * Servlet implementation class SearchDepartment
 */
public class SearchDepartment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDepartment() {
        super();
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        HttpSession session = request.getSession();
        ArrayList<DepartmentTable> dList = new ArrayList<DepartmentTable>();
        int i = 0;

        String style = request.getParameter("style");
        String name = request.getParameter("name");
        name = name.trim();
        if (name == "" || name.isEmpty()) {
            out.print("<script>alert('Please enter the full keyword, the keyword cannot null!');window.location='DepartmentInfo.jsp';</script>");
        }
        if (style.equals("depname")) {
            dList = departmentDAO.queryDepartmentByDepname(name);
        } else if (style.equals("depid")) {
            dList = departmentDAO.queryDepartmentByDepid(Integer.parseInt(name));
        } else {
            dList = departmentDAO.queryDepartmentByParentdepid(Integer.parseInt(name));
        }

        System.out.println(dList.size());
        if (dList.isEmpty()) {
            out.print("<script>alert('No related departments! Please try new search terms! !');window.location='DepartmentInfo.jsp';</script>");
        } else {
            session.setAttribute("departmentcount", dList.size());
            for (DepartmentTable department : dList) {
                session.setAttribute("depid" + i, department.getDepartmentID());
                session.setAttribute("depname" + i, department.getDepartmentName());
                session.setAttribute("parentdepid" + i, department.getParentDepartmentID());
                i++;
            }
            request.getRequestDispatcher("DepartmentInfo.jsp").forward(request, response);
        }

    }
}