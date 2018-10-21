package com.djj.languagepoints.chapter8.designmode.template_method.traditional;

public class CompanyLoanApplication extends LoanApplication {

    @Override
    protected void checkIdentity() {
        System.out.println("-----company checkIdentity--------");
    }

    @Override
    protected void checkIncomeHistory() {
        System.out.println("----company checkIncomeHistory---------");
    }

    @Override
    protected void checkCreditHistory() {
        System.out.println("---- company checkCreditHistory---------");
    }

}
