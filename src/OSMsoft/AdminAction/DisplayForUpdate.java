package OSMsoft.AdminAction;

import OSMsoft.DAO.EmployeeDAO;
import OSMsoft.Table.EmployeeTable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisplayForUpdate")
public class DisplayForUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        //获取上一个页面传递的参数值
        int id = Integer.parseInt(String.valueOf(request.getParameter("employee")));
        //利用该参数值搜索员工
        EmployeeDAO employeeDAO = new EmployeeDAO();
        EmployeeTable employeeTable = employeeDAO.searchEmployeeByID(id);
        request.setAttribute("Employee",employeeTable);
        request.getRequestDispatcher("UpEmployeeFAdmin.jsp").forward(request,response);
    }
}
