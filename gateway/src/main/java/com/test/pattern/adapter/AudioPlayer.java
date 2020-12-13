package com.test.pattern.adapter;

/**
 * @Author: movie
 * @Date: 2020/3/26 22:21
 */
public class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void play(int type, String file) {
        if (type == 3) {
            System.out.println("mp3" + file);
        }

        if (type == 1 || type == 2) {
            mediaAdapter = new MediaAdapter(type);
            mediaAdapter.play(type, file);
        }
    }

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play(1, "1");
        audioPlayer.play(2, "2");
        audioPlayer.play(3, "3");
    }
}
