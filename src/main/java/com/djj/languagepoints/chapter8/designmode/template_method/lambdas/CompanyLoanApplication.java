package com.djj.languagepoints.chapter8.designmode.template_method.lambdas;

public class CompanyLoanApplication extends LoanApplication {

    public CompanyLoanApplication(Company company) {
        super(company::checkIdentity,
              company::checkHistoricalDebt,
              company::checkProfitAndLoss);

//        super(() -> company.checkIdentity(),
//                () -> company.checkHistoricalDebt(),
//                () -> company.checkProfitAndLoss());
    }

}
