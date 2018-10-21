package com.djj.languagepoints.chapter8.designmode.template_method;

import com.djj.languagepoints.chapter8.designmode.template_method.lambdas.Company;
import com.djj.languagepoints.chapter8.designmode.template_method.lambdas.CompanyLoanApplication;

/*
模板方法模式


开发软件时一个常见的情况是有一个通用的算法，只是步骤上略有不同。
我们希望不同的实现能够遵守通用模式，保证它们使用了同一个算法，也是为了让代码更加易读。
一旦你从整体上理解了算法，就能更容易理解其各种实现。

模板方法模式是为这些情况设计的：
整体算法的设计是一个抽象类，它有一系列抽象方法，代表算法中可被定制的步骤，同时这个类中包含了一些通用代码。
算法的每一个变种由具体的类实现，它们重写了抽象方法，提供了相应的实现。

例：
假设我们是一家银行，需要对公众、公司和职员放贷。
放贷程序大体一致——验明身份、信用记录和收入记录。这些信息来源不一，衡量标准也不一样。
你可以查看一个家庭的账单来核对个人身份；公司都在官方机构注册过，比如美国的 SEC、英国的 Companies House。
 */
public class TemplateMethodMain {
    public static void main(String[] args) {
        companyLoanForLambda();
        System.out.println("---------------------------------------");
        companyLoanForCommon();
    }

    /*
    lambda

    使用 Lambda 表达式和方法引用，我们能换个角度思考模板方法模式，实现方式也跟以前不一样。
    模板方法模式真正要做的是将一组方法调用按一定顺序组织起来。
    如果用函数接口表示函数，用 Lambda 表达式或者方法引用实现这些接口，相比使用继承构建算法，就会得到极大的灵活性。

    将行为分配给 company 类的原因是各个国家之间确认公司信息的方式不同。
    在英国，Companies House 规范了注册公司信息的地址，但在美国，各个州的政策是不一样的。

    使用函数接口实现检查方法并没有排除继承的方式。我们可以显式地在这些类中使用Lambda 表达式或者方法引用。
    我们也不需要强制 EmployeeLoanApplication 继承 PersonalLoanApplication 来达到复用，可以对同一个方法传递引用。
    它们之间是否天然存在继承关系取决于员工的借贷是否是普通人借贷这种特殊情况，或者是另外一种不同类型的借贷。
    因此，使用这种方式能让我们更加紧密地为问题建模。
     */
    public static void companyLoanForLambda(){
        Company company = new Company();
        CompanyLoanApplication companyLoanApplication = new CompanyLoanApplication(company);
        try {
            companyLoanApplication.checkLoanApplication();
        } catch (ApplicationDenied applicationDenied) {
            applicationDenied.printStackTrace();
        }
    }

    public static void companyLoanForCommon(){
        com.djj.languagepoints.chapter8.designmode.template_method.traditional.CompanyLoanApplication  companyLoanApplication
                = new com.djj.languagepoints.chapter8.designmode.template_method.traditional.CompanyLoanApplication();

        try {
            companyLoanApplication.checkLoanApplication();
        } catch (ApplicationDenied applicationDenied) {
            applicationDenied.printStackTrace();
        }
    }

}
