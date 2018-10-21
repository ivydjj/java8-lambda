package com.djj.languagepoints.chapter8.designmode.strategy;

import java.io.IOException;
import java.io.OutputStream;

/*
定义压缩数据的策略接口
 */

public interface CompressionStrategy {

    OutputStream compress(OutputStream data) throws IOException;

}
