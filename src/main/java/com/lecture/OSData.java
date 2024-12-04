package com.lecture;

public enum OSData {
    IOS("Folder IOS"),
    ANDROID("Folder Android"),
    WINDOWS("Folder Win64"),
    LINUX("Folder Linux"),
    APPLE("Folder AppleM");

    private final String downloader;

    // constructor
    OSData(String downloader) {
        this.downloader = downloader;
    }
}
