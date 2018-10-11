package com.djj.languagepoints.chapter5.customcollect;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/*
功能：字符串间添加分隔符

待收集元素的类型, 这里是 String
累加器的类型, StringCombiner
最终结果的类型, 这里依然是 String

StringJoiner

 */
public class StringCollector implements Collector<String, StringCombiner, String> {
    private static final Set<Characteristics> characteristics = Collections.emptySet();

    private String prefix;
    private String suffix;
    private String delim; // 分隔符

    public StringCollector(String prefix, String suffix, String delim) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.delim = delim;
    }

    // 工厂方法，用来创建容器
    @Override
    public Supplier<StringCombiner> supplier() {
        return () -> new StringCombiner(prefix, suffix, delim);
    }

    // 累加器
    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    // 合并容器
    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    // 结果
    @Override
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }

    // 特征是一组描述收集器的对象，框架可以对其适当优化
    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}
