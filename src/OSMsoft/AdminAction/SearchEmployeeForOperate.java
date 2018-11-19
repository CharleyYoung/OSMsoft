package OSMsoft.AdminAction;

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
import java.util.ArrayList;

@WebServlet(name = "SearchEmployeeForOperate")
public class SearchEmployeeForOperate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //获取session
        HttpSession session = request.getSession(true);
        //获取Attribute
        String style = (String)request.getParameter("style");
        String keyWord = (String)request.getParameter("keyWord");
        //去掉字符串两端Unicode编码小于等于32（\u0020）的所有字符
        keyWord = keyWord.trim();
        //定义存放结果的Table和ArrayList
        EmployeeTable employeeTable = new EmployeeTable();
        ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        //定义对数据库操作的DAO
        EmployeeDAO employeeDAO = new EmployeeDAO();
        //判断关键词是否为空
        if(keyWord.equals("")|| keyWord.isEmpty()){
            out.println("<script>");
            out.println("alert('请输入完整的关键词');window.location.href='OperateEmployeeForAdmin.jsp';");
            out.println("</script>");
        }
        //执行数据库操作
        try {
            if (style.equals("id")) {
                employeeTable = employeeDAO.searchEmployeeByID(Integer.parseInt(String.valueOf(keyWord)));
                eList.add(employeeTable);
            } else if (style.equals("name")) {
                eList = employeeDAO.searchEmployeeByName(keyWord);
            } else if (style.equals("workage")) {
                eList = employeeDAO.searchEmployeeByWorkAge(Integer.parseInt(String.valueOf(keyWord)));
            } else if (style.equals("age")) {
                eList = employeeDAO.searchEmployeeByAge(Integer.parseInt(String.valueOf(keyWord)));
            } else if (style.equals("gender")) {
                eList = employeeDAO.searchEmployeeByGender(keyWord);
            } else if (style.equals("department")) {
                eList = employeeDAO.searchEmployeeByDepartment(keyWord);
            } else if (style.equals("job")) {
                eList = employeeDAO.searchEmployeeByJob(keyWord);
            }
        }catch (Exception e){
            out.println("<script>");
            out.println("alert('关键词有误，请确认输入正确的关键词');window.location.href='OperateEmployeeForAdmin.jsp';");
            out.println("</script>");
        }if(eList.get(0).getName().equals("no such employee")){
            out.println("<script>");
            out.println("alert('搜索结果为空，请确认结果');window.location.href='OperateEmployeeForAdmin.jsp';");
            out.println("</script>");
        }else {
            //往request里面存放数据
            request.setAttribute("searchResult", eList);
            //进行页面跳转
            request.getRequestDispatcher("OperateEmployeeForAdmin.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
