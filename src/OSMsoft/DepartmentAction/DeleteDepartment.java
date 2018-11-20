package OSMsoft.DepartmentAction;

import OSMsoft.AdminAction.TreeNode;
import OSMsoft.AdminAction.TreeServiceImp;
import OSMsoft.DAO.DepartmentDAO;

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
 * Servlet implementation class DeleteDepartment
 */
public class DeleteDepartment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDepartment() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        int depid = Integer.parseInt(String.valueOf(request.getParameter("depid")));
        Boolean flag = departmentDAO.deleteDepartmentByDepid(depid);

        if (flag) {
            HttpSession session = request.getSession(true);
            session.removeAttribute("depList");
            TreeServiceImp treeServiceImp = new TreeServiceImp();
            ArrayList<TreeNode> treeDep = treeServiceImp.testQueryDepList();
            session.setAttribute("depList", treeDep);
            out.print("<script>alert('删除成功');window.location.href='ManageDepartmentInfo.jsp';</script>");
        } else {
            out.print("<script>alert('待删除部门含有子部门，删除失败');window.location.href='ManageDepartmentInfo.jsp';</script>");
        }
    }
}
