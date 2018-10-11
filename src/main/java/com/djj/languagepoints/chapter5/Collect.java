package com.djj.languagepoints.chapter5;

import com.djj.languagepoints.data.Album;
import com.djj.languagepoints.data.Artist;
import com.djj.languagepoints.data.SampleData;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.djj.languagepoints.data.SampleData.albums;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/*
收集器:
一种通用的、从流生成复杂值的结构。只要将它传给 collect 方法，所有的流就都可以使用它了。

下游收集器：
收集器是生成最终结果的一剂配方，下游收集器则是生成部分结果的配方，主收集器中会用到下游收集器。
 */
public class Collect {
    public static void main(String[] args) {
        // 转换成其他集合
        // 使用 toCollection ，用定制的集合收集元素
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        list.stream().collect(Collectors.toCollection(TreeSet::new));

        // 转换成值
        // maxBy minBy averagingInt summingInt
        System.out.println(list.stream().collect(Collectors.maxBy(Comparator.comparing(Integer::intValue))));
        System.out.println(list.stream().collect(Collectors.minBy(Comparator.comparing(Integer::intValue))));
        System.out.println(list.stream().collect(Collectors.averagingInt(Integer::intValue)));
        System.out.println(list.stream().collect(Collectors.summingInt(Integer::intValue)));

        System.out.println("---" + biggestGroup(SampleData.threeArtists()));
        System.out.println("---" + averageNumberOfTracks(Arrays.asList(SampleData.aLoveSupreme, SampleData.sampleShortAlbum, SampleData.manyTrackAlbum)));

        // 数据分块 partitioningBy
        // 它接受一个流，并将其分成两部分,它使用 Predicate 对象判断一个元素应该属于哪个部分，并根据布尔值返回一个 Map 到列表。
        // 因此，对于 true List 中的元素， Predicate 返回 true ；对其他 List 中的元素， Predicate 返回 false 。
        Map<Boolean, List<Integer>> result = list.stream().collect(Collectors.partitioningBy(e -> e % 2 == 0));
        System.out.println(result.get(true));

        System.out.println("----" + bandsAndSolo(SampleData.threeArtists()).get(false));

        // 数据分组 groupingBy
        // 数据分组是一种更自然的分割数据操作，与将数据分成 ture 和 false 两部分不同，可以使用任意值对数据分组。
        // 调用流的 collect 方法，传入一个收集器。 groupingBy 收集器接受一个分类函数，用来对数据分组，
        // 就像 partitioningBy 一样，接受一个Predicate 对象将数据分成 ture 和 false 两部分。
        // 我们使用的分类器是一个 Function 对象，和 map 操作用到的一样。
        Map<Boolean, List<Integer>> result1 = list.stream().collect(groupingBy(e -> e % 2 != 0));
        System.out.println(result1.get(true));

        System.out.println("----" + albumsByArtist(albums).keySet());


        // 字符串
        System.out.println(list.stream().map(e -> e.toString()).collect(Collectors.joining(", ", "[", "]")));

        // 组合收集器,下游收集器
        // counting

//        // 常规版
//        Map<Artist, List<Album>> albumsByArtist
//                = SampleData.albums.collect(Collectors.groupingBy(album -> album.getMainMusician()));
//        Map<Artist, Integer> numberOfAlbums = new HashMap<>();
//        for(Map.Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
//            numberOfAlbums.put(entry.getKey(), entry.getValue().size());
//        }
        // 升级版
        // groupingBy 先将元素分成块，每块都与分类函数 getMainMusician 提供的键值相关联，
        // 然后使用下游的另一个收集器收集每块中的元素，最好将结果映射为一个 Map
        Map<Artist, Long> result2 = Stream.of(SampleData.aLoveSupreme).collect(Collectors.groupingBy(album -> album.getMainMusician(), Collectors.counting()));
        result2.keySet().forEach(e -> System.out.println(e.getName() + "---" + result2.get(e)));

        // mapping
        // mapping 允许在收集器的容器上执行类似 map 的操作。但是需要指明使用什么样的集合类存储结果，比如 toList 。
        // mapping 收集器和 map 方法一样，接受一个 Function 对象作为参数
        Map<Artist, List<String>> result3 =
        Stream.of(SampleData.aLoveSupreme).collect(Collectors.groupingBy(Album::getMainMusician, Collectors.mapping(Album::getName, toList())));
        result3.keySet().forEach(e -> System.out.println(e.getName() + "---" + result3.get(e)));



    }

    // maxBy
    public static Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
        return artists.collect(Collectors.maxBy(Comparator.comparing(getCount)));
    }

    // averagingInt
    public static double averageNumberOfTracks(List<Album> albums) {
        return albums.stream().collect(Collectors.averagingInt(album -> album.getTrackList().size()));
    }

    // partitioningBy
    public static Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
//        return artists.collect(Collectors.partitioningBy(artist -> artist.isSolo()));
        return artists.collect(Collectors.partitioningBy(Artist::isSolo));
    }

    // groupingBy
    public static Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician()));
    }
}




