package com.djj.languagepoints.chapter5;

import com.djj.languagepoints.data.Artist;

import java.util.HashMap;
import java.util.Map;

/*
说明：

 */
public class Explain {
    public static void main(String[] args) {
        System.out.println(getArtist1("name").getName());

        System.out.println(getArtist2("name").getName());
    }

    // 法一
    public static Artist getArtist1(String name) {
        Map<String, Artist> artistCache = new HashMap<>();

        Artist artist = artistCache.get(name);
        if (artist == null) {
            artist = readArtistFromDB(name);
            artistCache.put(name, artist);
        }
        return artist;
    }

    // 法二
    public static Artist getArtist2(String name) {
        Map<String, Artist> artistCache = new HashMap<>();

        return artistCache.computeIfAbsent(name, Explain::readArtistFromDB);
    }

    public static Artist readArtistFromDB(String name){
        return new Artist("nameDB", "USA_DB");
    }
}
