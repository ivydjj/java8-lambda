package com.djj.languagepoints.chapter8.solidrule.dependencyinversion;

import java.io.IOException;

public class HeadingLookupException extends RuntimeException {
    public HeadingLookupException(IOException e) {
    }
}
