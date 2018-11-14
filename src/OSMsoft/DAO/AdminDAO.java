package OSMsoft.DAO;

import java.sql.*;
import java.util.*;
import OSMsoft.core.*;
import OSMsoft.Table.*;

/**
 * @author Taiho
 * AdminDAO 该类用于负责对数据库的操作
 */
public class AdminDAO {
    String sql = "";
    ConnDB conn = new ConnDB();

    /**
     *
     * @param account 根据该account去数据库中查询相应admin
     * @return adminTable 返回包含查询到的admin信息的Table类
     * @throws SQLException
     */
    public AdminTable getAdminByAccount(String account) throws SQLException{
        sql = "select * from admin where account = \'" + account + "\'";
        ResultSet rs = conn.executeQuery(sql);
        AdminTable adminTable = new AdminTable();
        while(rs.next()) {
            adminTable.setAccount(rs.getString(1));
            adminTable.setPassword(rs.getString(2));
        }
        //所有操作执行完成后务必关闭数据库连接
        conn.close();
        return adminTable;
    }
}
