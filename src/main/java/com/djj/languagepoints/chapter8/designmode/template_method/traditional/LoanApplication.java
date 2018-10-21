package com.djj.languagepoints.chapter8.designmode.template_method.traditional;

import com.djj.languagepoints.chapter8.designmode.template_method.ApplicationDenied;

/*
使用模板方法模式描述申请贷款过程

使用一个抽象类 LoanApplication 来控制算法结构，该类包含一些贷款调查结果报告的通用代码。
 */
public abstract class LoanApplication {

    public void checkLoanApplication() throws ApplicationDenied {
        checkIdentity();
        checkCreditHistory();
        checkIncomeHistory();
        reportFindings();
    }

    protected abstract void checkIdentity() throws ApplicationDenied;

    protected abstract void checkIncomeHistory() throws ApplicationDenied;

    protected abstract void checkCreditHistory() throws ApplicationDenied;

    private void reportFindings() {

    }


}
