package com.djj.languagepoints.chapter7;

import com.djj.languagepoints.data.SampleData;

import java.util.Set;
import java.util.stream.Collectors;

/*
流只能使用一次

【 peek 方法】
流有一个方法让你能查看每个值，同时能继续操作流

记录日志这是 peek 方法的用途之一
peek 方法能记录中间值，在调试时非常有用。
 */
public class LazyAndTest {
    public static void main(String[] args) {
        SampleData.aLoveSupreme.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .forEach(nationality -> System.out.println("Found: " + nationality));

        Set<String> nationalities1
                = SampleData.aLoveSupreme.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .collect(Collectors.<String>toSet());


        Set<String> nationalities2
                = SampleData.aLoveSupreme.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .peek(nation -> System.out.println("Found nationality: " + nation))
                .collect(Collectors.<String>toSet());
    }
}
