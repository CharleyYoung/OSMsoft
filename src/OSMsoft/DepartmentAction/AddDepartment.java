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
 * Servlet implementation class AddDepartment
 */
public class AddDepartment extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDepartment() {
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

        //判断所有输入框是否填满
        if (request.getParameter("depname") == "") {
            out.print("<script>alert('请输入部门名称');window.location='AddDepartment.jsp';</script>");
        } else if (request.getParameter("parentdename") == "") {
            out.print("<script>alert('请输入上级部门名称');window.location='AddDepartment.jsp';</script>");
        } else if (request.getParameter("havason") == "") {
            out.print("<script>alert('请选择是否含有子部门');window.location='AddDepartment.jsp';</script>");
        } else {
            //获取页面输入框信息
            DepartmentDAO departmentDAO = new DepartmentDAO();
            String depname = request.getParameter("depname");

            int parentdepid = 0;
            boolean isParent = false;//标记是否为合法的上级部门
            String depurl = "null";//记录待添加部门的url
            String parentdepname = String.valueOf(request.getParameter("parentdepname"));
            DepartmentTable parentdepartmentTable = departmentDAO.queryDepartmentByDepname(parentdepname).get(0);
            if (parentdepartmentTable.getDepartmentName().equals(parentdepname)) {//判断是否存在输入的上级部门
                String url = String.valueOf(parentdepartmentTable.getUrl());
                if (url.equals("null")) {//通过url判断该上级部门是否允许添加子部门
                    isParent = true;
                } else {
                    out.print("<script>alert('输入的上级部门不允许添加子部门，请重新输入');window.location='AddDepartment.jsp';</script>");
                }
            } else {
                out.print("<script>alert('不存在输入的上级部门名称，请重新输入');window.location='AddDepartment.jsp';</script>");
            }

            parentdepid = parentdepartmentTable.getDepartmentID();
            System.out.println("上级部门名称: " + parentdepname);
            System.out.println("上级部门ID: " + parentdepid);

            //判断添加部门是否超过部门层次第三层
            boolean flag = true;//默认没有超过三层，可以添加该部门
            if (parentdepid != 0) {
                int pparentdepid = departmentDAO.queryDepartmentByDepid(parentdepid).get(0).getParentDepartmentID();
                System.out.println("上级部门的上级部门ID: " + pparentdepid);
                if (pparentdepid != 0) {
                    int ppparentdepid = departmentDAO.queryDepartmentByDepid(pparentdepid).get(0).getParentDepartmentID();
                    if (ppparentdepid != 0) {//第一层部门的上级部门ID应该为0
                        flag = false;
                        System.out.println("超过层次结构");
                        out.print("<script>alert('该部门以下无法再添加子部门（部门层次结构最多为三层），请重新添加');window.location='AddDepartment.jsp';</script>");
                    } else {
                        depurl = "depEmployee.jsp";
                        out.print("<script>alert('该部门已经位于部门层次结构第三层，不允许含有子部门，已默添加为不含子部门');window.location='AddDepartment.jsp';</script>");

                    }

                }
            }
            if (isParent && flag) {//如果输入的上级部门合法且未超出层级结构即可进行添加子部门
                String haveson = (String) request.getParameter("haveson");

                //创建Table变量并存储要用到的信息
                DepartmentTable departmentTable = new DepartmentTable();
                departmentTable.setDepartmentName(depname);
                departmentTable.setParentDepartmentID(parentdepid);
                System.out.println("havason: " + haveson);
                if (haveson.equals("nothave")) {
                    depurl = "depEmployee.jsp";
                }
                if (depurl.equals("depEmployee.jsp")) {
                    departmentTable.setUrl(depurl);
                    departmentDAO.addNotHaveSonDepartment(departmentTable);
                } else {
                    departmentDAO.addHaveSonDepartment(departmentTable);
                }

                //request.setAttribute("newDepartment",departmentTable);
                HttpSession session = request.getSession(true);
                session.removeAttribute("depList");
                TreeServiceImp treeServiceImp = new TreeServiceImp();
                ArrayList<TreeNode> treeDep = treeServiceImp.testQueryDepList();
                session.setAttribute("depList", treeDep);
                out.print("<script>alert('添加成功');window.location.href='AddDepartment.jsp';</script>");
                //request.getRequestDispatcher("newDepartment.jsp").forward(request,response);
            } else {
                out.print("<script>alert('添加失败，请重新添加');window.location.href='AddDepartment.jsp';</script>");
            }

        }
    }
}
