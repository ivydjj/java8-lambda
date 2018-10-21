package com.djj.other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
迭代删除列表中的元素

注意：不是随意一个列表都能删除，如 Arrays.asList 生成的列表删除抛不支持操作异常
 */
public class IteratorDelete {
    public static void main(String[] args) {
        //////////////////// 一般迭代删除
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();){
            Integer next = iterator.next();
            if (next > 3){
                iterator.remove();
            }
        }
        System.out.println(list); // [1, 2, 3]

        //////////////////// lambda
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // 表达式和方法引用二选一
//        list.removeIf(e -> e > 3); // 表达式
        list.removeIf(IteratorDelete::greateThan); // 方法引用
        System.out.println(list); // [1, 2, 3]
    }

    public static boolean greateThan(Integer integer){
        return integer > 3;
    }
}
