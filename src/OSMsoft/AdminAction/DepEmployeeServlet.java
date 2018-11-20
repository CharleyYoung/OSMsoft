package OSMsoft.AdminAction;


import OSMsoft.Table.EmployeeTable;
import OSMsoft.core.ConnDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DepEmployeeServlet用于获取AdminHomepage.jsp页面传过来的depid,
 * 查询该部门下的所有员工，并将其设置为session的属性，
 * 然后将其传递给depEmployee.jsp页面,打印该部门下的所有员工的信息
 * 该页面向session中添加了两个参数:departmentName（部门的名称） 和 employeeList(部门下的所有员工)
 *
 * @author saulzhang
 */

public class DepEmployeeServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String depid = req.getParameter("depid");
        ConnDB conn = new ConnDB();

        ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        String sql = "select * from employee where depid = " + depid + ";";
        ResultSet rs = conn.executeQuery(sql);

        addElements(eList, rs);
//        System.out.println("查询employee的语句："+sql);

        HttpSession session = req.getSession(true);
        res.setContentType("text/html");
        session.setAttribute("employeeList", eList);

        String departmentName = req.getParameter("departmentName");
        ;

        System.out.println("");

        System.out.println("departmentName:" + departmentName);

        System.out.println("employeeList:" + eList);

        session.setAttribute("departmentName", departmentName);

        res.sendRedirect("/depEmployee.jsp");

    }


    private void addElements(ArrayList<EmployeeTable> eList, ResultSet rs) {
        try {
            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    EmployeeTable employeeTable = new EmployeeTable();

                    employeeTable.setEmployeeID(Integer.parseInt(rs.getString(1)));

                    employeeTable.setName(rs.getString(2));

                    employeeTable.setWorkAge(Integer.parseInt(rs.getString(3)));

                    employeeTable.setAge(Integer.parseInt(rs.getString(4)));

                    employeeTable.setGender(rs.getString(5));

                    employeeTable.setPhoneNumber(Long.parseLong(rs.getString(6)));

                    employeeTable.setPassword(rs.getString(7));

                    employeeTable.setJob(rs.getString(8));

                    employeeTable.setDepartmentID(Integer.parseInt(rs.getString(9)));

                    employeeTable.setEmail(rs.getString(10));

                    eList.add(employeeTable);
                }
            } else {

                EmployeeTable employeeTable = new EmployeeTable();

                employeeTable.setName("no such employee");

                eList.add(employeeTable);

            }
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
