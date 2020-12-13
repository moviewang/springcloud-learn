package com.test.pattern.adapter;

/**
 * @Author: movie
 * @Date: 2020/3/26 21:59
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String name) {
    }

    @Override
    public void playMp4(String name) {
        System.out.println("mp4" + name);
    }
}
