package com.djj.languagepoints.chapter8.designmode.strategy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

/*
策略模式

策略模式能在运行时改变软件的算法行为。

其主要思想是定义一个通用的问题，使用不同的算法来实现，然后将这些算法都封装在一个统一接口的背后

例子：文件压缩
提供给用户各种压缩文件的方式，可以使用 zip 算法，也可以使用 gzip 算法，我们实现一个通用的 Compressor 类，能以任何一种算法压缩文件。

使用 Lambda 表达式或者方法引用可以去掉样板代码。
在这里，可以去掉具体的策略实现，使用一个方法实现算法，这里的算法由构造函数中对应的 OutputStream 实现。
使用这种方式，可以完全舍弃 GzipCompressionStrategy 和 ZipCompressionStrategy 类。


总结：
无论使用观察者模式或策略模式，实现时采用 Lambda 表达式还是传统的类，取决于策略和观察者代码的复杂度。
从某种角度来说，将大量代码塞进一个方法会让可读性变差是决定如何使用Lambda 表达式的黄金法则。
 */
public class StrategyMain {
    public static void main(String[] args) throws URISyntaxException, IOException, ClassNotFoundException {
        InputStream is = StrategyMain.class.getClassLoader().getResourceAsStream("chapter8/data.txt");
        byte b[] = new byte[1024]; // 所有的内容都读到此数组之中
        is.read(b); // 读取内容   网络编程中 read 方法会阻塞
        is.close(); // 关闭输出流
        System.out.println(new String(b));  // 把byte数组变为字符串输出

        // 获取src/main/resources下文件的绝对路径
        URL url = StrategyMain.class.getClassLoader().getResource("chapter8/data.txt");
        Path path = Paths.get(new URI(url.getPath()));
        classBasedExample(path, new File("chapter8/out.txt"));
    }

    public static void classBasedExample(Path inFile, File outFile) throws IOException {
        Compressor gzipCompressor = new Compressor(new GzipCompressionStrategy());
        gzipCompressor.compress(inFile, outFile);

        Compressor zipCompressor = new Compressor(new ZipCompressionStrategy());
        zipCompressor.compress(inFile, outFile);
    }

    // lambda
    // 不需要 GzipCompressionStrategy 和 ZipCompressionStrategy
    public static void lambdaBasedExample(Path inFile, File outFile) throws IOException {
        Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
        gzipCompressor.compress(inFile, outFile);

        Compressor zipCompressor = new Compressor(ZipOutputStream::new);
        zipCompressor.compress(inFile, outFile);
    }
}
