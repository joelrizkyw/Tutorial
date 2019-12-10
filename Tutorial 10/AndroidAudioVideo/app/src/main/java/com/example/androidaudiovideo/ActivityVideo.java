package com.example.androidaudiovideo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivityVideo extends AppCompatActivity {
    // Membuat objek VideoView
    VideoView videoView;
    // Membuat sebuah ArrayList untuk menampung URL video
    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"));
    // Mengatur nilai awal index
    int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);

        // Membuat objek MediaController
        final MediaController mediacontroller = new MediaController(this);
        // Mengatur nilai Anchor di videoView
        mediacontroller.setAnchorView(videoView);
        // Mengatur media control di Video View
        videoView.setMediaController(mediacontroller);
        // Mengatur nilai URL yang di parese ke URI dari index ArrayList
        videoView.setVideoURI(Uri.parse(arrayList.get(index)));
        videoView.requestFocus();
        // Fungsi ketika video selesai diputar
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Memunculkan informasi video selesai diputar
                Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                // Jika index video habis
                if (index++ == arrayList.size()) {
                    index = 0;
                    mp.release();
                    Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                    // Jika video masih ada di list
                } else {
                    videoView.setVideoURI(Uri.parse(arrayList.get(index)));
                    videoView.start();
                }
            }
        });

        // Ketika terjadi error CRASH
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
    }
}
