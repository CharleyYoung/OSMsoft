package OSMsoft.AdminAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import OSMsoft.DAO.*;
import OSMsoft.Table.*;

/**
 * @author Taiho
 * AdminIndex 该servlet类负责处理用户登录逻辑，并根据用户填写结果跳转到相应界面
 */
@WebServlet(name = "/AdminIndex")
public class AdminIndex extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(request.getParameter("account")=="" || request.getParameter("password") ==""){
            out.print("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><script language='javascript' charset='UTF-8'>alert('账户和密码不能为空');window.location.href='AdminLogin.jsp';</script>");
        } else {
            //获取session，如果session不存在，就创建一个
            HttpSession session = request.getSession(true);
            response.setContentType("text/html");
            //获取cookie，如果cookie不存在，就创建一个
            Cookie[] cookie = request.getCookies();
            //获取输入框中的输入值
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            //利用account去数据库中寻找
            AdminDAO adminDAO = new AdminDAO();
            try {
                AdminTable adminTable = adminDAO.getAdminByAccount(account);
                if(adminTable.getAccount().equals("no admin")){
                    out.print("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> <script language='javascript' charset='utf-8'>alert('错误的账户名');window.location.href='AdminLogin.jsp';</script>");
                } else if(!adminTable.getPassword().equals(password)){
                    out.print("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> <script language='javascript' charset='utf-8'>alert('错误的密码');window.location.href='AdminLogin.jsp';</script>");
                } else{
                    //登录成功
                    System.out.println("Log in success");
                    //设置session以维持会话信息

                    session.setAttribute("Account",account);
                    session.setAttribute("Password",password);

                    //change by saulzhang
                    //return the department tree
                    TreeServiceImp treeServiceImp = new TreeServiceImp();
                    ArrayList<TreeNode> treeDep = treeServiceImp.testQueryDepList();
                    session.setAttribute("depList", treeDep);
                    System.out.print(treeDep);
                    response.sendRedirect("AdminHomepage.jsp");

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
