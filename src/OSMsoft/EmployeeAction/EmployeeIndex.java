package OSMsoft.EmployeeAction;

import OSMsoft.DAO.EmployeeDAO;
import OSMsoft.Table.EmployeeTable;
import OSMsoft.Table.SalaryTable;
import OSMsoft.DAO.SalaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author zzh187
 * EmployeeIndex 该servlet类负责处理针对Employee用户登录逻辑，并根据用户填写结果跳转到相应界面
 */
@WebServlet(name = "/EmployeeIndex")
public class EmployeeIndex extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(request.getParameter("account").equals("") || request.getParameter("password").equals("")){
            out.print("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><script language='javascript' charset='UTF-8'>alert('账户和密码不能为空');window.location.href='EmployeeLogin.jsp';</script>");
        } else {
            //获取session，如果session不存在，就创建一个
            HttpSession session = request.getSession(true);
            //获取cookie，如果cookie不存在，就创建一个
            Cookie[] cookie = request.getCookies();
            //获取输入框中的输入值
            int account = 0;
            try{
                account = Integer.parseInt(request.getParameter("account"));
            }catch(Exception e){
                account=0;
            }
            String password = request.getParameter("password");
            //利用account去数据库中寻找
            EmployeeDAO employeeDAO = new EmployeeDAO();
            try {
                EmployeeTable employeeTable = employeeDAO.searchEmployeeByID(account);
                if(employeeTable.getName().equals("no such employee")){
                    out.print("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><script language='javascript' charset='utf-8'>alert('错误的账户名');window.location.href='EmployeeLogin.jsp';</script>");
                } else if(!employeeTable.getPassword().equals(password)){
                    out.print("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><script language='javascript' charset='utf-8'>alert('错误的密码');window.location.href='EmployeeLogin.jsp';</script>");
                } else{
                    //登录成功
                    System.out.println("Log in success");
                    //存储该员工的部门员工信息，并存储到session中去。
                    String DepName = employeeTable.getDepartmentName();
                    ArrayList<EmployeeTable> employee_list = employeeDAO.searchEmployeeByDepartment(DepName);
                    //获取该员工本月或上月的工资记录
                    SalaryDao salaryDao=new SalaryDao();
                    SalaryTable salaryTable=salaryDao.getSalarytableByIdAndTimeRencently(account);
                    //计算获得应得工资
                    double DeservedSalary = salaryTable.getJobSalary() + salaryTable.getPerformanceSalary()
                            + salaryTable.getSubsideAllowance() + salaryTable.getWorkAgeSalary();
                    //获取该员工获取工资的所有年月份
                    ArrayList<SalaryTable> SalaryList=salaryDao.returnAllSalaryTableById(account);
                    //设置session以维持会话信息
                    session.setAttribute("EmployeeList",employee_list);
                    session.setAttribute("Employee",employeeTable);
                    session.setAttribute("Account",account);
                    session.setAttribute("time",SalaryList);
                    session.setAttribute("salary",salaryTable);
                    session.setAttribute("DeservedSalary",DeservedSalary);
                    response.sendRedirect("EmployeeHomepage.jsp");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
