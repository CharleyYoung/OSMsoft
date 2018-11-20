package OSMsoft.AdminAction;

import OSMsoft.DAO.DepartmentDAO;
import OSMsoft.DAO.EmployeeDAO;
import OSMsoft.DAO.SalaryDao;
import OSMsoft.Table.DepartmentTable;
import OSMsoft.Table.EmployeeTable;
import OSMsoft.Table.SalaryTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UpdateSalaryForAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        //获取上一个页面传递的参数值
        int id = Integer.parseInt(String.valueOf(request.getParameter("account")));
        System.out.println(id);
        float Js=Float.parseFloat(String.valueOf(request.getParameter("jobSalary")));
        System.out.println(Js);
        float Ps=Float.parseFloat(String.valueOf(request.getParameter("performanceSalary")));
        System.out.println(Ps);
        float Ws=Float.parseFloat(String.valueOf(request.getParameter("workAgeSalary")));
        System.out.println(Ws);
        float Ss=Float.parseFloat(String.valueOf(request.getParameter("subsideAllowance")));
        System.out.println(Ss);
        int year = Integer.parseInt(String.valueOf(request.getParameter("year")));
        System.out.println(year);
        int month = Integer.parseInt(String.valueOf(request.getParameter("month")));
        System.out.println(month);
        //利用该参数值删除员工并给出反馈
        SalaryDao salaryDao = new SalaryDao();
        SalaryTable salaryTable=new SalaryTable();
        salaryTable.setEmployeeID(id);
        salaryTable.setJobSalary(Js);
        salaryTable.setPerformanceSalary(Ps);
        salaryTable.setWorkAgeSalary(Ws);
        salaryTable.setSubsideAllowance(Ss);
        salaryTable.setYear(year);
        salaryTable.setMonth(month);
        session.setAttribute("old",salaryTable);
        response.sendRedirect("AlterSalary1.jsp");
    }
}
