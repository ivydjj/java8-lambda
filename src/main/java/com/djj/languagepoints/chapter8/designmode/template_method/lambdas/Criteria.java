package com.djj.languagepoints.chapter8.designmode.template_method.lambdas;

import com.djj.languagepoints.chapter8.designmode.template_method.ApplicationDenied;

/*
如果申请失败，函数接口 Criteria 抛出异常

采用这种方式，而不是基于继承的模式的好处是不需要在 LoanApplication 及其子类中实现算法，分配功能时有了更大的灵活性。
 */
public interface Criteria {

    void check() throws ApplicationDenied;

}
