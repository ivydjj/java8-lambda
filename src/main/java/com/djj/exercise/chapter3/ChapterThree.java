package com.djj.exercise.chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChapterThree {
    public static void main(String[] args) {
        Integer sum = addUp(Stream.of(1, 2, 3));
        System.out.println(sum);

        List<String> nameList = objectToStringList(Stream.of(new Book("book1", 100.1), new Book("book2", 200.2)));
        System.out.println(nameList.toString());

        List<Book> bookList = ivyFilter(Stream.of(new Book("book1", 100.1), new Book("book2", 200.2)));
        bookList.forEach(e -> System.out.println(e.getName()));

        outerToInner();

        // 3.9.6
        // 3.9.7
        // 计算一个字符串中小写字母的个数
        // 在一个字符串列表中，找出包含最多小写字母的字符串。对于空列表，返回 Optional<String> 对象。
        System.out.println(lowerCaseCount("asdfghjklASDFG"));
        System.out.println(maxLowerCaseCount(Arrays.asList("as", "JK", "dkkIII", "a>>>")));
    }

    // 3.9.7
    static Optional<String> maxLowerCaseCount(List<String> strList){
        return strList.stream().max(Comparator.comparing(ChapterThree::lowerCaseCount));
    }

    // 3.9.6
    static int lowerCaseCount(String str){
        return (int) str.chars().filter(Character::isLowerCase).count();
    }

    // 3.9.2
    static void outerToInner(){
        // 内
        Double totalBookPrice = Arrays.asList(new Book("book1", 100.1), new Book("book2", 200.2)).stream().map(e -> e.getPrice()).reduce(0.0D, (sum, price) -> sum + price);
        System.out.println(totalBookPrice);

        // 外
        List<Book> bookList = Arrays.asList(new Book("book1", 100.1), new Book("book2", 200.2));
        totalBookPrice = 0D;
        for (Book book : bookList) {
            totalBookPrice += book.getPrice();
        }
        System.out.println(totalBookPrice);
    }

    // 3.9.1.a
    static int addUp(Stream<Integer> numbers){
        return numbers.reduce(0, (simpleSum, param) -> simpleSum + param);
    }

    // 3.9.1.b
    static List<String> objectToStringList(Stream<Book> books){ // flatMap????????
        return books.map(e -> e.getName()).collect(Collectors.toList());
    }

    // 3.9.1.c
    static List<Book> ivyFilter(Stream<Book> books){
        return books.filter(e -> e.getPrice() > 200).collect(Collectors.toList());
    }
}

class Book{
    private String name;
    private Double price;

    public Book() {
    }

    public Book(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
