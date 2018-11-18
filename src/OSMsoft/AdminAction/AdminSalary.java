package OSMsoft.AdminAction;
import OSMsoft.DAO.AdminDAO;
import OSMsoft.DAO.EmployeeDAO;
import OSMsoft.Table.AdminTable;
import OSMsoft.Table.EmployeeTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
public class AdminSalary extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        String account=request.getParameter("salary1");
        int accountint=Integer.parseInt(account);
        PrintWriter out = response.getWriter();
        System.out.println(accountint);
        EmployeeDAO employeeDAO=new EmployeeDAO();
        EmployeeTable employeeTable = employeeDAO.searchEmployeeByID(accountint);
        try {
            if(employeeTable.getName().equals("no such employee")){
                out.print("<script language='javascript' charset='utf-8'>alert('错误的工号');window.location.href='AdminLogin.jsp';</script>");
            } else{
                System.out.println("查询成功");
                response.sendRedirect("AdminSalary1.jsp");
                session.setAttribute("salary",accountint);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
