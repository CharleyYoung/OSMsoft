package OSMsoft.AdminAction;

import OSMsoft.DAO.AdminDAO;
import OSMsoft.DAO.EmployeeDAO;
import OSMsoft.DAO.SalaryDao;
import OSMsoft.Table.AdminTable;
import OSMsoft.Table.EmployeeTable;
import OSMsoft.Table.SalaryTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AdminSalary1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        String salarytime = request.getParameter("salarytime");
        PrintWriter out = response.getWriter();
        System.out.println(salarytime);
        StringTokenizer a = new StringTokenizer(salarytime);
        String year = a.nextToken(",");
        String month = a.nextToken();
        String account = (String) session.getAttribute("account");
        int accountint = Integer.parseInt(account);
        int yearint = Integer.parseInt(year);
        int monthint = Integer.parseInt(month);
        System.out.println(yearint);
        System.out.println(monthint);
        System.out.println(accountint);
        SalaryDao salaryDao = new SalaryDao();
        SalaryTable salaryTable = salaryDao.getSalarytableByIdAndTime(accountint, yearint, monthint);
        session.setAttribute("salary", salaryTable);
        //计算获得应得工资
        double DeservedSalary = salaryTable.getJobSalary() + salaryTable.getPerformanceSalary()
                + salaryTable.getSubsideAllowance() + salaryTable.getWorkAgeSalary();
        session.setAttribute("DeservedSalary", DeservedSalary);
        response.sendRedirect("AdminSalary1.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
