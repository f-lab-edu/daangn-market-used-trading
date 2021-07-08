package com.ssibongee.daangnmarket.image.util;

public enum FileType {

    PNG("png"),
    JPEG("jpeg"),
    JPG("jpg"),
    GIF("git");

    private final String extension;
    
    FileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
