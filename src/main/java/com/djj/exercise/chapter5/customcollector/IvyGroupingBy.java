package com.djj.exercise.chapter5.customcollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/*
待收集元素的类型 T
累加器的类型 Map<K, List<T>>
最终结果的类型 Map<K, List<T>>
 */
public class IvyGroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {
    private final static Set<Characteristics> characteristics = new HashSet<>(); // 没有它，抛空指针
    static {
        characteristics.add(Characteristics.IDENTITY_FINISH);
    }

    private final Function<? super T, ? extends K> classifier;

    public IvyGroupingBy(Function<? super T, ? extends K> classifier) {
        this.classifier = classifier;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
//        // ivy
//        return new BiConsumer<Map<K, List<T>>, T>() {
//            @Override
//            public void accept(Map<K, List<T>> kListMap, T t) {
//                kListMap.keySet().forEach(e -> {
//                    if (t.equals(k)){
//                        kListMap.get(e).add(t);
//                    }
//                });
//            }
//        };
        return (map, element) -> {
            K key = classifier.apply(element);
            List<T> elements = map.computeIfAbsent(key, k -> new ArrayList<>());
            elements.add(element);
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
//        // ivy
//        return new BinaryOperator<Map<K, List<T>>>() {
//            @Override
//            public Map<K, List<T>> apply(Map<K, List<T>> kListMap, Map<K, List<T>> kListMap2) {
//                Set<K> thisKeys = kListMap.keySet();
//                Set<K> otherKeys = kListMap2.keySet();
//
//                // kListMap中存在kListMap2中的key
//                for(Iterator<K> it = thisKeys.iterator(); it.hasNext();){
//                    K next = it.next();
//                    if (otherKeys.contains(next)){
//                        kListMap.get(next).addAll(kListMap2.get(next));
//                    }
//                }
//                // kListMap中不存在kListMap2中的key
//                // 取差集
//                Set<K> otherKeys2 = kListMap2.keySet();
//                otherKeys2.removeAll(thisKeys);
//                otherKeys2.forEach(e -> kListMap.put(e, kListMap2.get(e)));
//                return kListMap;
//            }
//        };

        return (left, right) -> {
            right.forEach((key, value) -> {
                left.merge(key, value, (leftValue, rightValue) -> {
                    leftValue.addAll(rightValue);
                    return leftValue;
                });
            });
            return left;
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
//        // ivy
//        return new Function<Map<K, List<T>>, Map<K, List<T>>>() {
//            @Override
//            public Map<K, List<T>> apply(Map<K, List<T>> kListMap) {
//                return kListMap;
//            }
//        };

        return map -> map;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }

}
