package OSMsoft.EmployeeAction;

import OSMsoft.DAO.EmployeeDAO;
import OSMsoft.Table.EmployeeTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zzh187
 * ChangePassword 该Servlet负责处理Employee修改密码
 */
@WebServlet(name = "ChangePassword")
public class ChangePassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //获取session，如果session不存在，就创建一个
        HttpSession session = request.getSession(true);
        //获取页面Account信息
        int account = 0;
        try {
            String account1 = session.getAttribute("Account").toString();
            account = Integer.parseInt(account1);
        } catch (Exception e) {
            account = 0;
        }
        //创建DAO变量
        EmployeeDAO employeeDAO = new EmployeeDAO();
        //创建Table变量并存储要用到的信息
        EmployeeTable employeeTable = employeeDAO.searchEmployeeByID(account);
        String password = employeeTable.getPassword();
        //判断所有输入框是否填满
        if (request.getParameter("oldpassword") == "" || request.getParameter("newpassword") == ""
                || request.getParameter("againnewpassword") == "") {
            out.print("<script language='javascript' charset='UTF-8'>alert('输入内容不能为空！');" +
                    "window.location.href='ChangePasswordForEmployee.jsp';</script>");
        }//判断旧密码是否填写正确
        else if (!String.valueOf(request.getParameter("oldpassword")).equals(password)) {
            out.print("<script language='javascript' charset='UTF-8'>alert('旧密码输入错误');" +
                    "window.location.href='ChangePasswordForEmployee.jsp';</script>");
         //判断两次密码是否填写正确
        } else if (!String.valueOf(request.getParameter("newpassword")).equals(String.valueOf(request.getParameter("againnewpassword")))) {
            out.print("<script language='javascript' charset='UTF-8'>alert('两次新密码不匹配');" +
                    "window.location.href='ChangePasswordForEmployee.jsp';</script>");
        } else {
            employeeTable.setPassword(String.valueOf(request.getParameter("newpassword")));
            employeeDAO.updateEmployeePassword(employeeTable);
            session.setAttribute("Employee", employeeTable);
            session.setAttribute("Account", account);
            response.sendRedirect("EmployeeHomepage.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
