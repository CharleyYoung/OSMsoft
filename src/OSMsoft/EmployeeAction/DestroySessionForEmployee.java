package OSMsoft.EmployeeAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zzh187
 * DestroySessionForEmployee 该类负责消除针对Employee的session数据并将用户导向相应界面
 */
@WebServlet(name = "/DestroySessionForEmployee")
public class DestroySessionForEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("Logout success");
        response.sendRedirect("EmployeeLogin.jsp");
    }
}
