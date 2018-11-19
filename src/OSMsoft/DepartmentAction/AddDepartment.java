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
        if(request.getParameter("depname")=="" ){
            out.print("<script>alert('请输入部门名称');window.location='AddDepartment.jsp';</script>");
        }
        else if (request.getParameter("parentdename")==""){
            out.print("<script>alert('请输入上级部门名称');window.location='AddDepartment.jsp';</script>");
        }
        else if(request.getParameter("havason")==""){
            out.print("<script>alert('请选择是否含有子部门');window.location='AddDepartment.jsp';</script>");
        } else{
            //获取页面输入框信息
            DepartmentDAO departmentDAO = new DepartmentDAO();
            String depname = request.getParameter("depname");
            int parentdepid = 0;
            String parentdepname = String.valueOf(request.getParameter("parentdepname"));

            parentdepid = departmentDAO.queryDepartmentByDepname(parentdepname).get(0).getDepartmentID();
            System.out.println("上级部门名称: "+parentdepname);
            System.out.println("上级部门ID: "+parentdepid);

            //判断添加部门是否超过部门层次第三层
            boolean flag = true;//默认没有超过三层，可以添加该部门
            if(parentdepid != 0 ){
                int pparentdepid = departmentDAO.queryDepartmentByDepid(parentdepid).get(0).getParentDepartmentID();
                System.out.println("上级部门的上级部门ID: "+pparentdepid);
                if(pparentdepid !=0 ){
                    int ppparentdepid = departmentDAO.queryDepartmentByDepid(pparentdepid).get(0).getParentDepartmentID();
                    if(ppparentdepid!=0) {//第一层部门的上级部门ID应该为0
                        flag = false;
                        System.out.println("超过层次结构");
                    }
                }
            }
            if (flag){
                String haveson = (String) request.getParameter("haveson");

                //创建Table变量并存储要用到的信息
                DepartmentTable departmentTable = new DepartmentTable();
                departmentTable.setDepartmentName(depname);
                departmentTable.setParentDepartmentID(parentdepid);
                System.out.println("havason: "+haveson);
                if(haveson.equals("nothave")){
                    departmentTable.setUrl("depEmployee.jsp");
                }
                departmentDAO.addDepartment(departmentTable);
                //request.setAttribute("newDepartment",departmentTable);
                out.print("<script>alert('添加成功');window.location='AddDepartment.jsp';</script>");
                //request.getRequestDispatcher("newDepartment.jsp").forward(request,response);
            }else{
                out.print("<script>alert('该部门以下无法再添加子部门（部门层次结构最多为三层），请重新添加');window.location='AddDepartment.jsp';</script>");
            }

        }
    }
}
