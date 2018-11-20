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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        DepartmentDAO departmentDAO = new DepartmentDAO();
        ArrayList<DepartmentTable> dList = new ArrayList<DepartmentTable>();

        String style = request.getParameter("style");
        String name = request.getParameter("keyword");
        name = name.trim();
        if (name == "" || name.isEmpty()) {
            out.print("<script>alert('请输入完整的关键词, 关键词不能为空!');window.location.href='ManageDepartmentInfo.jsp';</script>");
        }
        if (style.equals("depname")) {
            dList = departmentDAO.queryDepartmentByDepname(name);
        } else if (style.equals("depid")) {
            if (isNumeric(name)) {
                dList = departmentDAO.queryDepartmentByDepid(Integer.parseInt(name));
            } else {
                out.print("<script>alert('输入有误，ID必须为数字!');window.location.href='ManageDepartmentInfo.jsp';</script>");
            }

        } else if (style.equals("parentdepid")) {
            if (isNumeric(name)) {
                dList = departmentDAO.queryDepartmentByParentdepid(Integer.parseInt(name));
            } else {
                out.print("<script>alert('输入有误，ID必须为数字!');window.location.href='ManageDepartmentInfo.jsp';</script>");
            }
        } else {
            int keyword = departmentDAO.queryDepartmentByDepname(name).get(0).getDepartmentID();
            dList = departmentDAO.queryDepartmentByParentdepid(keyword);
        }
        System.out.println(dList.size());
        if (dList.isEmpty()) {
            out.print("<script>alert('没有相关的部门! 请重新搜索!');window.location.href='ManageDepartmentInfo.jsp';</script>");
        } else {
            //session.setAttribute("departmentcount", dList.size());
            request.setAttribute("searchResult", dList);
            request.getRequestDispatcher("ManageDepartmentInfo.jsp").forward(request, response);
        }

    }

    /**
     * 判断输入的是否为数字
     */
    public boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}