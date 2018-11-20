package OSMsoft.AdminAction;

import OSMsoft.DAO.DepartmentDAO;
import OSMsoft.DAO.EmployeeDAO;
import OSMsoft.Table.DepartmentTable;
import OSMsoft.Table.EmployeeTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Taiho
 * AddEmployee 该Servlet负责处理添加Employee
 */
@WebServlet(name = "AddEmployee")
public class AddEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //判断所有输入框是否填满
        if (request.getParameter("name") == "" || request.getParameter("age") == "" || request.getParameter("tele") == ""
                || request.getParameter("email") == "" || request.getParameter("job") == "" ||
                request.getParameter("gender") == "" || request.getParameter("department") == "") {
            out.print("<script language='javascript' charset='UTF-8'>alert('请填写全部信息');" +
                    "window.location.href='AddEmployee.jsp';</script>");
        }//判断年龄信息是否填写正确
        else if (Integer.parseInt(String.valueOf(request.getParameter("age"))) <= 0) {
            out.print("<script language='javascript' charset='UTF-8'>alert('请填写正确年龄信息');" +
                    "window.location.href='AddEmployee.jsp';</script>");
        } else {
            //获取页面输入框信息
            String name = request.getParameter("name");
            int age = Integer.parseInt(String.valueOf(request.getParameter("age")));
            long phoneNumber = Long.parseLong((String) request.getParameter("tele"));
            String email = (String) request.getParameter("email");
            String job = (String) request.getParameter("job");
            String gender = (String) request.getParameter("gender");
            String department = (String) request.getParameter("department");
            //创建DAO变量，其中departmentDAO用于利用department获取depID,employeeDAO负责执行插入
            DepartmentDAO departmentDAO = new DepartmentDAO();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            //创建Table变量并存储要用到的信息
            ArrayList<DepartmentTable> dList = departmentDAO.queryDepartmentByDepname(department);
            DepartmentTable departmentTable = dList.get(0);
            EmployeeTable employeeTable = new EmployeeTable();
            //往employeeTable中存储信息
            employeeTable.setName(name);
            employeeTable.setAge(age);
            employeeTable.setPhoneNumber(phoneNumber);
            employeeTable.setEmail(email);
            employeeTable.setJob(job);
            employeeTable.setGender(gender);
            employeeTable.setDepartmentID(departmentTable.getDepartmentID());
            employeeTable.setDepartmentName(departmentTable.getDepartmentName());
            //插入Employee信息
            employeeDAO.insertNewEmployee(employeeTable);
            employeeTable.setWorkAge(0);
            employeeTable.setPassword("000000");
            request.setAttribute("newEmployee", employeeTable);
            request.getRequestDispatcher("NewEmployee.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
