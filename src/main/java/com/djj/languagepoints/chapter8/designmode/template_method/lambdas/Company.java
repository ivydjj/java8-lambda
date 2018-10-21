package com.djj.languagepoints.chapter8.designmode.template_method.lambdas;

import com.djj.languagepoints.chapter8.designmode.template_method.ApplicationDenied;

public class Company {

    public void checkIdentity() throws ApplicationDenied {
        System.out.println("check company Identity ");
    }

    public void checkProfitAndLoss() throws ApplicationDenied {
        System.out.println("check company ProfitAndLoss ");
    }

    public void checkHistoricalDebt() throws ApplicationDenied {
        System.out.println("check company HistoricalDebt ");
    }

}
