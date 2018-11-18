package OSMsoft.AdminAction;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 部门树，用于向指定的JSP页面返回存在各个部门树状信息的ArrayList
 *
 * @author saulzhang
 */


public class DepTreeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        res.setContentType("text/html");
        TreeServiceImp treeServiceImp = new TreeServiceImp();
        ArrayList<TreeNode> treeDep = treeServiceImp.testQueryDepList();
        session.setAttribute("depList", treeDep);

        //change by saulzhang

        res.sendRedirect("/testTree.jsp");
    }
}
