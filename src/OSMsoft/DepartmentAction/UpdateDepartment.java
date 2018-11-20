package OSMsoft.DepartmentAction;

import OSMsoft.AdminAction.TreeNode;
import OSMsoft.AdminAction.TreeServiceImp;
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
 * Servlet implementation class UpdateDepartment
 */
public class UpdateDepartment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDepartment() {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //判断输入框是否填满
        if (request.getParameter("depname") == "") {
            out.print("<script>alert('请输入部门名称!');window.location.href='modDepartment.jsp';</script>");
        } else {
            //获取页面输入框信息
            int depid = Integer.parseInt(String.valueOf(request.getParameter("depid")));
            String depname = request.getParameter("depname");

            DepartmentDAO departmentDAO = new DepartmentDAO();
            DepartmentTable departmentTable = new DepartmentTable();
            departmentTable.setDepartmentID(depid);
            departmentTable.setDepartmentName(depname);
            boolean flag = departmentDAO.updateDepartment(departmentTable);//判断是否更新信息成功

            if (flag) {
                HttpSession session = request.getSession(true);
                session.removeAttribute("depList");
                TreeServiceImp treeServiceImp = new TreeServiceImp();
                ArrayList<TreeNode> treeDep = treeServiceImp.testQueryDepList();
                session.setAttribute("depList", treeDep);
                out.print("<script>alert('更新成功!');window.location.href='ManageDepartmentInfo.jsp';</script>");
            } else {
                out.print("<script>alert('更新失败!');window.location.href='ModDepartment.jsp';</script>");
            }
        }

    }
}