package com.test.pattern.adapter;

/**
 * @Author: movie
 * @Date: 2020/3/26 22:02
 */
public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(int type) {
        if (type == 1) {
            advancedMediaPlayer = new VlcPlayer();
        } else if (type == 2) {
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(int type, String file) {
        if (type == 1) {
            advancedMediaPlayer.playVlc(file);
        } else if (type == 2) {
            advancedMediaPlayer.playMp4(file);
        }
    }
}
