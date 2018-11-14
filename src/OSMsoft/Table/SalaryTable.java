package OSMsoft.Table;

/**
 * @author Taiho
 * SalaryTable 用于存放从数据库中读取出的Salary元素的类
 */
public class SalaryTable {

    private double jobSalary;
    private double performanceSalary;
    private double workAgeSalary;
    private double subsideAllowance;
    private int month;
    private int year;
    private int employeeID;

    public double getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(double jobSalary) {
        this.jobSalary = jobSalary;
    }

    public double getPerformanceSalary() {
        return performanceSalary;
    }

    public void setPerformanceSalary(double performanceSalary) {
        this.performanceSalary = performanceSalary;
    }

    public double getWorkAgeSalary() {
        return workAgeSalary;
    }

    public void setWorkAgeSalary(double workAgeSalary) {
        this.workAgeSalary = workAgeSalary;
    }

    public double getSubsideAllowance() {
        return subsideAllowance;
    }

    public void setSubsideAllowance(double subsideAllowance) {
        this.subsideAllowance = subsideAllowance;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}
