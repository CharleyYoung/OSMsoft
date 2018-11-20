package OSMsoft.EmployeeAction;

import OSMsoft.DAO.EmployeeDAO;
import OSMsoft.DAO.SalaryDao;
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
import java.util.StringTokenizer;
/**
 * @author zzh187
 * CheckSalaryRecord 该Servlet负责处理Employee查看工资记录
 */
@WebServlet(name = "CheckSalaryRecord")
public class CheckSalaryRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //获取session，如果session不存在，就创建一个
        HttpSession session = request.getSession(true);
        //获取页面salarytime信息
        String salarytime=request.getParameter("salarytime");
        System.out.println(salarytime);
        StringTokenizer a=new StringTokenizer(salarytime);
        String year=a.nextToken(",");
        String month=a.nextToken();
        int yearint=Integer.parseInt(year);
        int monthint=Integer.parseInt(month);
        //获取页面Account信息
        int account = 0;
            String account1 = session.getAttribute("Account").toString();
            account = Integer.parseInt(account1);

        //创建DAO变量
        SalaryDao salaryDao=new SalaryDao();
        //创建Table变量
        SalaryTable salaryTable=salaryDao.getSalarytableByIdAndTime(account,yearint,monthint);

        //计算获得应得工资
        double DeservedSalary = salaryTable.getJobSalary() + salaryTable.getPerformanceSalary()
         + salaryTable.getSubsideAllowance() + salaryTable.getWorkAgeSalary();

            session.setAttribute("salary",salaryTable);
            session.setAttribute("DeservedSalary",DeservedSalary);
            response.sendRedirect("PayrollRecordForEmployee.jsp");

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
