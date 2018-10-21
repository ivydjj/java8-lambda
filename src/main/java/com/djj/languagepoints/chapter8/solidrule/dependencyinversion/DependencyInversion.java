package com.djj.languagepoints.chapter8.solidrule.dependencyinversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by djj on 2018/10/19.
 *
 *
 */
public class DependencyInversion {
    /*
        自动化构建地址簿:

        实现时使用了依赖反转原则达到上层的解耦。
        该应用以电子卡片作为输入，使用某种存储机制编写地址簿。

        代码分成如下三个基本模块：
        一个能解析电子卡片格式的电子卡片阅读器；
        能将地址存为文本文件的地址簿存储模块；
        从电子卡片中获取有效信息并将其写入地址簿的编写模块。

        电子卡片阅读器和地址簿存储模块都不依赖于其他模块，因此很容易在其他系统中重用。
        还可以替换它们，比如用一个其他的阅读器，或者从人们的 Twitter 账户信息中读取内容；
        又比如我们不想将地址簿存为一个文本文件，而是使用数据库存储等其他形式。
        为了具备能在系统中替换组件的灵活性，必须保证编写模块不依赖阅读器或存储模块的实现细节。
        因此我们引入了对阅读信息和输出信息的抽象，编写模块的实现依赖于这种抽象。
        在运行时传入具体的实现细节，这就是依赖反转原则的工作原理。
     */

    /*
        文件资源管理：
        从一种假想的标记语言中提取标题，
        标题以冒号（ ：）结尾。我们的方法先读取文件，逐行检查，滤出标题，然后关闭文件。
        我们还将和读写文件有关的异常封装成接近待解决问题的异常： HeadingLookupException

        可惜，我们的代码将提取标题和资源管理、文件处理混在一起。
        我们真正想要的是编写提取标题的代码，而将操作文件相关的细节交给另一个方法。
        可以使用 Stream<String> 作为抽象，让代码依赖它，而不是文件。
        Stream 对象更安全，而且不容易被滥用。
        我们还想传入一个函数，在读文件出问题时，可以创建一个问题域里的异常。

        withLinesOf 方法接受一个 Reader 参数处理文件读写，然后将其封装进一个 BufferedReader 对象，这样就可以逐行读取文件了。
        handler 函数代表了我们想在该方法中执行的代码，它以文件中的每一行组成的 Stream 作为参数。
        另一个参数是 error ，输入输出有异常时会调用该方法，它会构建出与问题域有关的异常，出问题时就抛出该异常。
     */
    public List<String> findHeadings(Reader input) {
        try (BufferedReader reader = new BufferedReader(input)) {
            return reader.lines()
                    .filter(line -> line.endsWith(":"))
                    .map(line -> line.substring(0, line.length() - 1))
                    .collect(toList());
        } catch (IOException e) {
            throw new HeadingLookupException(e);
        }
    }

    public List<String> findHeadingsForNew(Reader input) {
        return withLinesOf(input,
                lines -> lines.filter(line -> line.endsWith(":"))
                        .map(line -> line.substring(0, line.length()-1))
                        .collect(toList()),
                HeadingLookupException::new);
    }

    /*
    这里我们使用 Stream 对原始的 Reader 和文件处理做抽象，
    这种方式也适用于函数式编程语言中的资源管理——通常使用高阶函数管理资源，
    接受一个回调函数使用打开的资源，然后再关闭资源。
     */
    private <T> T withLinesOf(Reader input,
                              Function<Stream<String>, T> handler,
                              Function<IOException, RuntimeException> error) {

        try (BufferedReader reader = new BufferedReader(input)) {
            return handler.apply(reader.lines());
        } catch (IOException e) {
            throw error.apply(e);
        }
    }

}
