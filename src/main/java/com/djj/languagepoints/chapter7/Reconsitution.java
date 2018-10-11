package com.djj.languagepoints.chapter7;

import com.djj.languagepoints.data.Album;
import com.djj.languagepoints.data.SampleData;
import com.djj.languagepoints.data.Track;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToLongFunction;

/*
重构

重构遗留代码时考虑如何使用 Lambda 表达式，有一些通用的模式。
 */
public class Reconsitution {
    public static void main(String[] args) {
        ///////////////////////////////////////孤独的覆盖
        /*
        ThreadLocal 就是一个很好的例子。
        ThreadLocal 能创建一个工厂，为每个线程最多只产生一个值。这是确保非线程安全的类在并发环境下安全使用的一种简单方式。
        假设要在数据库中查询一个艺术家，但希望每个线程只做一次这种查询
         */

        // 法一
        ThreadLocal<Album> thisAlbum1 = new ThreadLocal<Album> () {
            @Override protected Album initialValue() {
                return new Album("查询数据库", null, null);
            }
        };

        // 法二
        ThreadLocal<Album> thisAlbum2 = ThreadLocal.withInitial(() -> new Album("查询数据库", null, null));

        ///////////////////////////////////////同样的东西写两遍
        /*
        不要重复你劳动（Don’t Repeat Yourself，DRY）,它的反面是同样的东西写两遍（Write Everything Twice，WET）

        不是所有 WET 的情况都适合 Lambda 化。有时，重复是唯一可以避免系统过紧耦合的方式。
        什么时候该将 WET 的代码 Lambda 化？
        这里有一个信号可以参考。如果有一个整体上大概相似的模式，只是行为上有所不同，就可以试着加入一个 Lambda 表达式。
         */

    }

    private List<Album> albums = Arrays.asList(SampleData.aLoveSupreme);

    // 1 命令式编程
    public long countRunningTime() {
        long count = 0;
        for (Album album : albums) {
            for (Track track : album.getTrackList()) {
                count += track.getLength();
            }
        }
        return count;
    }
    public long countMusicians() {
        long count = 0;
        for (Album album : albums) {
            count += album.getMusicianList().size();
        }
        return count;
    }
    public long countTracks() {
        long count = 0;
        for (Album album : albums) {
            count += album.getTrackList().size();
        }
        return count;
    }

    // 2 流重构命令式
    public long countRunningTime2() {
        return albums.stream()
                .mapToLong(album -> album.getTracks()
                        .mapToLong(track -> track.getLength())
                        .sum())
                .sum();
    }
    public long countMusicians2() {
        return albums.stream()
                .mapToLong(album -> album.getMusicians().count())
                .sum();
    }
    public long countTracks2() {
        return albums.stream()
                .mapToLong(album -> album.getTracks().count())
                .sum();
    }

    // 3 领域方法重构
    public long countFeature(ToLongFunction<Album> function) {
        return albums.stream()
                .mapToLong(function)
                .sum();
    }
    public long countRunningTime3() {
        return countFeature(album -> album.getTracks()
                .mapToLong(track -> track.getLength())
                .sum());
    }
    public long countMusicians3() {
        return countFeature(album -> album.getMusicians().count());
    }
    public long countTracks3() {
        return countFeature(album -> album.getTracks().count());
    }

}
