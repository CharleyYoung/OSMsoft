package OSMsoft.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.math.BigDecimal;

/**
 * @author zzh187
 * ObtainPersonalIncomeTaxAndActualSalary 该类为自定义标签，用于根据应得工资计算出个人所得税
 */
public class ObtainPersonalIncomeTaxAndActualSalary extends SimpleTagSupport {
    //采用doTag方法实现根据应得工资计算出个人所得税
    public void doTag() throws JspException {
            double DeservedSalary=(double)getJspContext().getAttribute("DeservedSalary");

            double PersonalIncomeTax = 0;
            double ExceedSalary = DeservedSalary - 3500;
            if(ExceedSalary > 0 && ExceedSalary <= 1500){
                PersonalIncomeTax = ExceedSalary * 0.03;
            }else if (ExceedSalary <= 4500){
                PersonalIncomeTax = ExceedSalary * 0.10;
            }else if (ExceedSalary <= 9000){
                PersonalIncomeTax = ExceedSalary * 0.20;
            }else if (ExceedSalary <= 35000){
                PersonalIncomeTax = ExceedSalary * 0.25;
            }else if (ExceedSalary <= 55000){
                PersonalIncomeTax = ExceedSalary * 0.30;
            }else if (ExceedSalary <= 80000){
                PersonalIncomeTax = ExceedSalary * 0.35;
            }else{
                PersonalIncomeTax = ExceedSalary * 0.45;
            }
        //利用jspContext来设置Attribute,作用范围为page
        double ActualSalary = DeservedSalary - PersonalIncomeTax;
        BigDecimal a = new BigDecimal(ActualSalary);
        ActualSalary = a.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal b = new BigDecimal(PersonalIncomeTax);
        PersonalIncomeTax = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        getJspContext().setAttribute("PersonalIncomeTax",PersonalIncomeTax);
        getJspContext().setAttribute("ActualSalary",ActualSalary);
    }
}
