package OSMsoft.DAO;

import java.sql.ResultSet;

import OSMsoft.AdminAction.TreeNode;
import OSMsoft.core.ConnDB;

import java.util.ArrayList;


/**
 * 将所有的部门信息组织成为树的节点起来以ArrayList形式返回所有的节点。
 *
 * @author saulzhang
 */

public class DepartmentTreeDAO {


    public ArrayList<TreeNode> selectAllDep() {

        //ArrayList<EmployeeTable> eList = new ArrayList<EmployeeTable>();
        ConnDB conn = new ConnDB();
        ArrayList<TreeNode> listDep = new ArrayList<TreeNode>();
        String sql = "SELECT depid,depname,parentdepid,url,icon FROM department ORDER BY depid";
        ResultSet rs = conn.executeQuery(sql);
        addElements(listDep, rs);
        conn.close();
        return listDep;
    }

    private void addElements(ArrayList<TreeNode> eList, ResultSet rs) {
        try {
            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    TreeNode treeNode = new TreeNode();
                    treeNode.setId(rs.getString(1));
                    treeNode.setName(rs.getString(2));
                    treeNode.setParentId(rs.getString(3));
                    treeNode.setUrl(rs.getString(4));
                    treeNode.setIcon(rs.getString(5));
                    eList.add(treeNode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
