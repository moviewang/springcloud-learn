package com.test.pattern.adapter;

/**
 * @Author: movie
 * @Date: 2020/3/26 21:58
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String name) {
        System.out.println("vlc" + name);
    }

    @Override
    public void playMp4(String name) {
    }
}
