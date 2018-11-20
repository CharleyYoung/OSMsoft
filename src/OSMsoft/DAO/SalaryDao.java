package OSMsoft.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import OSMsoft.Table.*;
import OSMsoft.core.*;

public class SalaryDao {
    String sql = "";
    ConnDB conn = new ConnDB();

    public SalaryTable getSalarytableByIdAndTime(int employeeid, int year, int month) {
        conn = new ConnDB();
        sql = "select * from  salary where employeeid=" + "\'" + employeeid + "\'" +
                "and year=" + "\'" + year + "\'" + "and month=" + "\'" + month + "\'";
        ResultSet rs = conn.executeQuery(sql);
        SalaryTable salaryTable = new SalaryTable();
        try {
            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    salaryTable.setJobSalary(rs.getDouble(1));
                    salaryTable.setPerformanceSalary(rs.getDouble(2));
                    salaryTable.setWorkAgeSalary(rs.getDouble(3));
                    salaryTable.setSubsideAllowance(rs.getDouble(4));
                    salaryTable.setMonth(rs.getInt(5));
                    salaryTable.setYear(rs.getInt(6));
                    salaryTable.setEmployeeID(rs.getInt(7));
                }
            } else {
                salaryTable.setEmployeeID(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return salaryTable;
    }

    public void AddSalary(SalaryTable salaryTable) {
        conn = new ConnDB();
        sql = "insert into salary values(\'" + salaryTable.getJobSalary()
                + "\',\'" + salaryTable.getPerformanceSalary() + "\',\'" + salaryTable.getWorkAgeSalary() + "\',\'" + salaryTable.getSubsideAllowance() + "\'" +
                ",\'" + salaryTable.getMonth() + "\',\'" + salaryTable.getYear() + "\',\'" + salaryTable.getEmployeeID() + "\')";
        try {
            conn.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        System.out.println("添加salary:" + sql);
    }

    public boolean updateSalary(SalaryTable salaryTable) {
        conn = new ConnDB();
        boolean flag = true;
        sql = "update salary jobsalary = \'" + salaryTable.getJobSalary() +
                "\', performancesalary = \'" + salaryTable.getPerformanceSalary() +
                "\', workAgesalary = \'" + salaryTable.getWorkAgeSalary() +
                "\', subsideallowance = \'" + salaryTable.getSubsideAllowance() +
                "\' where employeeid = \'" + salaryTable.getEmployeeID() + "\'" + "and month=\'" + salaryTable.getYear() +
                "and month=\'" + salaryTable.getMonth();
        try {
            conn.executeUpdate(sql);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return flag;
    }

    public boolean deleteSalary(int employeeid, int year, int month) {
        conn = new ConnDB();
        boolean flag = true;
        sql = "delete from Salary where employeeid=" + "\'" + employeeid + "\'" +
                "and year=" + "\'" + year + "\'" + "and month=" + "\'" + month + "\'";
        try {
            conn.executeUpdate(sql);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return flag;
    }

    private void addElements(ArrayList<SalaryTable> dList, ResultSet rs) {
        try {
            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    SalaryTable salaryTable = new SalaryTable();
                    salaryTable.setJobSalary(Float.parseFloat(rs.getString(1)));
                    salaryTable.setPerformanceSalary(Float.parseFloat(rs.getString(2)));
                    salaryTable.setWorkAgeSalary(Float.parseFloat(rs.getString(3)));
                    salaryTable.setSubsideAllowance(Float.parseFloat(rs.getString(4)));
                    salaryTable.setMonth(Integer.parseInt(rs.getString(5)));
                    salaryTable.setYear(Integer.parseInt(rs.getString(6)));
                    salaryTable.setEmployeeID(Integer.parseInt(rs.getString(7)));
                    dList.add(salaryTable);
                }
            } else {
                SalaryTable salaryTable = new SalaryTable();
                salaryTable.setEmployeeID(-1);
                dList.add(salaryTable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SalaryTable> returnAllSalaryTable() {
        conn = new ConnDB();
        ArrayList<SalaryTable> dList = new ArrayList<SalaryTable>();
        sql = "select * from salary";
        ResultSet rs = conn.executeQuery(sql);
        addElements(dList, rs);
        System.out.println("查询salary的语句：" + sql);
        conn.close();
        return dList;
    }

    public ArrayList<SalaryTable> returnAllSalaryTableById(int employeeid) {
        conn = new ConnDB();
        ArrayList<SalaryTable> dList = new ArrayList<SalaryTable>();
        sql = "select * from salary where employeeid=" + "\'" + employeeid + "\'";
        ResultSet rs = conn.executeQuery(sql);
        addElements(dList, rs);
        System.out.println("查询salary的语句：" + sql);
        conn.close();
        return dList;
    }

    public SalaryTable getSalarytableByIdAndTimeRencently(int employeeid) {
        conn = new ConnDB();
        sql = "select * from salary where employeeid=" + "\'" + employeeid + "\'" +
                "and year=(select max(year)from salary) and month=(select max(month)from salary)";
        ResultSet rs = conn.executeQuery(sql);
        SalaryTable salaryTable = new SalaryTable();
        try {
            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    salaryTable.setJobSalary(rs.getDouble(1));
                    salaryTable.setPerformanceSalary(rs.getDouble(2));
                    salaryTable.setWorkAgeSalary(rs.getDouble(3));
                    salaryTable.setSubsideAllowance(rs.getDouble(4));
                    salaryTable.setMonth(rs.getInt(5));
                    salaryTable.setYear(rs.getInt(6));
                    salaryTable.setEmployeeID(rs.getInt(7));
                }
            } else {
                salaryTable.setEmployeeID(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return salaryTable;
    }
}
