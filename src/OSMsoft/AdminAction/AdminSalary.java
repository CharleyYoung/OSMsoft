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

public class AdminSalary extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String account = request.getParameter("salary1");
        PrintWriter out = response.getWriter();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        SalaryDao salaryDao = new SalaryDao();
        Boolean number = account.matches("[0-9]+");
        if (number) {
            int accountint = Integer.parseInt(account);
            System.out.println(accountint);
            EmployeeTable employeeTable = employeeDAO.searchEmployeeByID(accountint);
            try {
                if (employeeTable.getName().equals("no such employee")) {
                    out.print("<script language='javascript' charset='UTF-8'>alert('错误的员工号');" +
                            "window.location.href='AdminSalary.jsp';</script>");
                } else {
                    System.out.println("查询成功");
                    SalaryTable salaryTable = salaryDao.getSalarytableByIdAndTimeRencently(accountint);
                    ArrayList<SalaryTable> SalaryList = salaryDao.returnAllSalaryTableById(accountint);

                    session.setAttribute("account", account);
                    session.setAttribute("time", SalaryList);
                    //计算获得应得工资
                    double DeservedSalary = salaryTable.getJobSalary() + salaryTable.getPerformanceSalary()
                            + salaryTable.getSubsideAllowance() + salaryTable.getWorkAgeSalary();
                    session.setAttribute("DeservedSalary", DeservedSalary);
                    session.setAttribute("salary", salaryTable);
                    response.sendRedirect("AdminSalary1.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            out.print("<script language='javascript' charset='UTF-8'>alert('输入不是数字');" +
                    "window.location.href='AdminSalary.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
