package OSMsoft.EmployeeAction;

import OSMsoft.DAO.EmployeeDAO;
import OSMsoft.Table.EmployeeTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zzh187
 * ManageInformation 该Servlet负责处理Employee管理个人信息
 */
@WebServlet(name = "ManageInformation")
public class ManageInformation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        //获取session，如果session不存在，就创建一个
        HttpSession session = request.getSession(true);
        //判断所有输入框是否填满
        if (request.getParameter("name") == "" || request.getParameter("age") == "" || request.getParameter("tele") == ""
                || request.getParameter("email") == "" || request.getParameter("gender") == "") {
            out.print("<script language='javascript' charset='UTF-8'>alert('修改信息不能为空！');" +
                    "window.location.href='ManageInformation.jsp';</script>");
        }//判断年龄信息是否填写正确
        else if (Integer.parseInt(String.valueOf(request.getParameter("age"))) <= 0) {
            out.print("<script language='javascript' charset='UTF-8'>alert('请填写正确年龄信息');" +
                    "window.location.href='ManageInformation.jsp';</script>");
        } else {
            //获取页面输入框信息
            int account = 0;
            try {
                System.out.println(request.getParameter("Account"));
                account = Integer.parseInt(request.getParameter("Account"));
            } catch (Exception e) {
                account = 0;
            }
            String name = request.getParameter("name");
            int workage = Integer.parseInt(request.getParameter("workage"));
            int age = Integer.parseInt(String.valueOf(request.getParameter("age")));
            long phoneNumber = Long.parseLong((String) request.getParameter("tele"));
            String email = (String) request.getParameter("email");
            String gender = (String) request.getParameter("gender");
            if (gender.equals("male")) {
                gender = "男";
            }
            if (gender.equals("female")) {
                gender = "女";
            }
            System.out.println(request.getParameter("gender"));
            String job = (String) request.getParameter("job");
            int department = Integer.parseInt(request.getParameter("department"));
            //创建DAO变量，employeeDAO负责执行插入
            EmployeeDAO employeeDAO = new EmployeeDAO();
            //创建Table变量并存储要用到的信息
            EmployeeTable employeeTable = employeeDAO.searchEmployeeByID(account);
            //往employeeTable中存储信息
            employeeTable.setName(name);
            employeeTable.setWorkAge(workage);
            employeeTable.setAge(age);
            employeeTable.setPhoneNumber(phoneNumber);
            employeeTable.setEmail(email);
            employeeTable.setJob(job);
            employeeTable.setDepartmentID(department);
            employeeTable.setGender(gender);
            //插入Employee信息
            employeeDAO.updateEmployee(employeeTable);
            session.setAttribute("Account", account);
            session.setAttribute("Password", employeeTable.getPassword());
            session.setAttribute("Name", employeeTable.getName());
            session.setAttribute("WorkAge", employeeTable.getWorkAge());
            session.setAttribute("Age", employeeTable.getAge());
            session.setAttribute("Gender", gender);
            session.setAttribute("PhoneNumber", employeeTable.getPhoneNumber());
            session.setAttribute("Email", employeeTable.getEmail());
            session.setAttribute("Job", employeeTable.getJob());
            session.setAttribute("DepID", employeeTable.getDepartmentID());
            response.sendRedirect("EmployeeHomepage.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
