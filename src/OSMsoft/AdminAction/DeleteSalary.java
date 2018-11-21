package OSMsoft.AdminAction;

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

/**
 * 删除某一个员工的某一项工资
 *
 * @author 82533
 */
public class DeleteSalary extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //获取上一个页面传递的参数值
        int id = Integer.parseInt(String.valueOf(request.getParameter("account")));
        int year = Integer.parseInt(String.valueOf(request.getParameter("year")));
        int month = Integer.parseInt(String.valueOf(request.getParameter("month")));
        //利用该参数值删除员工并给出反馈
        boolean flag = false;
        SalaryDao salaryDao = new SalaryDao();
        flag = salaryDao.deleteSalary(id, year, month);
        if (flag == true) {
            out.print("<script language='javascript' charset='UTF-8'>alert('删除成功');" +
                    "window.location.href='AdminSalary2.jsp';</script>");
        } else {
            out.print("<script language='javascript' charset='UTF-8'>alert('删除失败');" +
                    "window.location.href='AdminSalary2.jsp';</script>");
        }
    }
}
