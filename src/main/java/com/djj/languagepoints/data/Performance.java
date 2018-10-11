package com.djj.languagepoints.data;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

public interface Performance {
    String getName();

    Stream<Artist> getMusicians();

    // TODO: test
    public default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> {
            return concat(Stream.of(artist), artist.getMembers());
        });
    }

}
