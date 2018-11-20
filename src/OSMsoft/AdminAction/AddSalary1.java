package OSMsoft.AdminAction;

import OSMsoft.DAO.*;
import OSMsoft.Table.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AddSalary1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        String number= request.getParameter("employeeID");
        String year=request.getParameter("year");
        String month=request.getParameter("month");
        Boolean a =number.matches("[0-9]+");
        Boolean b =year.matches("[0-9]+");
        Boolean c =month.matches("[0-9]+");
        Boolean d =isNumber(request.getParameter("jobSalary"));
        Boolean e =isNumber(request.getParameter("performanceSalary"));
        Boolean f =isNumber(request.getParameter("workAgeSalary"));
        Boolean g =isNumber(request.getParameter("subsideAllowance"));
        System.out.println(123);
        //判断所有输入框是否填满
        if(request.getParameter("employeeID")=="" || request.getParameter("jobSalary")=="" || request.getParameter("performanceSalary")==""
                || request.getParameter("workAgeSalary")=="" || request.getParameter("subsideAllowance")=="" ||
                request.getParameter("year")==""||request.getParameter("month")==""){
            out.print("<script language='javascript' charset='UTF-8'>alert('请填写全部信息');" +
                    "window.location.href='AddSalary.jsp';</script>");
        }//判断年龄信息是否填写正确
        else if(a==false||b==false||c==false||d==false||e==false||f==false||g==false){
            out.print("<script language='javascript' charset='UTF-8'>alert('请填写正确的信息格式');" +
                    "window.location.href='AlterSalary1.jsp';</script>");
        }
        else{
            EmployeeDAO employeeDAO=new EmployeeDAO();
            int s1=Integer.parseInt(String.valueOf(request.getParameter("employeeID")));
            EmployeeTable employeeTable = employeeDAO.searchEmployeeByID(s1);
            if(employeeTable.getName().equals("no such employee")) {
                out.print("<script language='javascript' charset='UTF-8'>alert('错误的员工号');" +
                        "window.location.href='AlterSalary1.jsp';</script>");
            }
            else{
                double s2 = Double.parseDouble(String.valueOf(request.getParameter("jobSalary")));
                double s3 = Double.parseDouble(String.valueOf(request.getParameter("performanceSalary")));
                double s4 = Double.parseDouble(String.valueOf(request.getParameter("workAgeSalary")));
                double s5 = Double.parseDouble(String.valueOf(request.getParameter("subsideAllowance")));
                int s6 = Integer.parseInt(String.valueOf(request.getParameter("year")));
                int s7 = Integer.parseInt(String.valueOf(request.getParameter("month")));
                SalaryDao salaryDao = new SalaryDao();
                SalaryTable salaryTable = new SalaryTable();
                salaryTable.setEmployeeID(s1);
                salaryTable.setJobSalary(s2);
                salaryTable.setPerformanceSalary(s3);
                salaryTable.setWorkAgeSalary(s4);
                salaryTable.setSubsideAllowance(s5);
                salaryTable.setYear(s6);
                salaryTable.setMonth(s7);
                SalaryTable oldSalaryTable=(SalaryTable)session.getAttribute("old");
                int oldaccount=oldSalaryTable.getEmployeeID();
                int oldyear=oldSalaryTable.getYear();
                int oldmonth=oldSalaryTable.getMonth();
                System.out.println(s7);
                salaryDao.updateSalary(salaryTable,oldaccount,oldyear,oldmonth);
                response.sendRedirect("AdminSalary2.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public static boolean isNumber(String str){
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }
}