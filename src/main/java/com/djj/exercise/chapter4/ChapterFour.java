package com.djj.exercise.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ChapterFour {
    public static void main(String[] args) {
        Artists artists = new Artists(Arrays.asList(new Artist("name1", 10, "desc1"), new Artist("name2", 10, "desc2")));
        System.out.println(artists.getArtist(2));
        System.out.println(artists.getArtistName(2));
    }
}

interface Performance {
    String getName();
    Stream<Artist> getMusicians();

    // 4.12.1
    default Stream<String> getAllMusicians(){
        return getMusicians().flatMap(e -> Stream.of(e.getName(), e.getDesc()));
    }

}

class Artist{
    private String name;
    private Integer age;
    private String desc;

    public Artist() {
    }

    public Artist(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Artist(String name, Integer age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

class Artists {
    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

//    public Artist getArtist(int index) {
//        if (index < 0 || index >= artists.size()) {
//            indexException(index);
//        }
//        return artists.get(index);
//    }
//
//    private void indexException(int index) {
//        throw new IllegalArgumentException(index +
//                "doesn't correspond to an Artist");
//    }
//
//    public String getArtistName(int index) {
//        try {
//            Artist artist = getArtist(index);
//            return artist.getName();
//        } catch (IllegalArgumentException e) {
//            return "unknown";
//        }
//    }

    // 4.12.3
    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }

    public String getArtistName(int index) {
        Optional<Artist> artist = getArtist(index);
        return artist.map(Artist::getName).orElse("unkown");
    }

}