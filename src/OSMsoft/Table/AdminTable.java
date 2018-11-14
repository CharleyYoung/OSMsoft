package OSMsoft.Table;

/**
 * @author Taiho
 * AdminTable 用于存放从数据库中读取出的Admin元素的类
 */
public class AdminTable {
    private String account;
    private String password;

    /**
     *
     * @param account 要设置的account
     */
    public void setAccount(String account){
        this.account = account;
    }

    /**
     *
     * @param password 要设置的password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     *
     * @return 返回当前Admin的account
     */
    public String getAccount(){
        return this.account;
    }

    /**
     *
     * @return 返回当前Admin的password
     */
    public String getPassword(){
        return this.password;
    }
}
