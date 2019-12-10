package com.example.androidaudiovideo;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityAudio extends AppCompatActivity implements Runnable {
    // Membuat objek MediaPlayer
    MediaPlayer mediaPlayer = new MediaPlayer();
    // Membuat objek SeekBar
    SeekBar seekBar;
    // Mengatur nilai variabel wasPlaying sebagai false
    boolean wasPlaying = false;
    // Membuat objek FloatingActionButton
    FloatingActionButton fab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        fab = findViewById(R.id.button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSong();
            }
        });

        final TextView seekBarHint = findViewById(R.id.textView);
        seekBar = findViewById(R.id.seekbar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarHint.setVisibility(View.VISIBLE);
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarHint.setVisibility(View.VISIBLE);
                // Proses konversi milliseconds ke seconds
                int x = (int) Math.ceil(progress / 1000f);
                // Mengatur nilai perpindahan adalah per detik
                if (x < 10)
                    seekBarHint.setText("0:0" + x);
                else
                    seekBarHint.setText("0:" + x);
                // Menyimpan variabel percent untuk mendapat persentase nilai posisi SeekBar
                double percent = progress / (double) seekBar.getMax();
                int offset = seekBar.getThumbOffset();
                int seekWidth = seekBar.getWidth();
                int val = (int) Math.round(percent * (seekWidth - 2 * offset));
                int labelWidth = seekBarHint.getWidth();
                seekBarHint.setX(offset + seekBar.getX() + val
                        - Math.round(percent * offset)
                        - Math.round(percent * labelWidth / 2));
// Pengecekkan apakah SeekBar berhenti/ lagu selesai diputar.
                if (progress > 0 && mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    clearMediaPlayer();
                    fab.setImageDrawable(ContextCompat.getDrawable(ActivityAudio.this, android.R.drawable.ic_media_play));
                    ActivityAudio.this.seekBar.setProgress(0);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });
    }

    public void playSong() {
        try {
            // Ketika lagu dijalankan
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                clearMediaPlayer();
                seekBar.setProgress(0);
                // Menganti nilai wasPlaying menjadi true
                wasPlaying = true;
                fab.setImageDrawable(ContextCompat.getDrawable(ActivityAudio.this, android.R.drawable.ic_media_play));
            }
            // Mengecek nilai wasPlaying jika tidak true
            if (!wasPlaying) {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                }
// Jika nilai wasPlaying true, menentukan icon yang muncul, yakni icon pause
                fab.setImageDrawable(ContextCompat.getDrawable(ActivityAudio.this, android.R.drawable.ic_media_pause));
                // Memilih lagu yang akan dijalankan
                AssetFileDescriptor descriptor = getAssets().openFd("music.mp3");
// Mengisi nilai mediaPlayer dengan data dari sumber lagu yang didapatkan
                mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                descriptor.close();
                // Menyiapkan objek mediaPlayer
                mediaPlayer.prepare();
                // Mengatur volume lagu
                mediaPlayer.setVolume(0.5f, 0.5f);
                // Mengatur nilai looping lagu
                mediaPlayer.setLooping(false);
                // Mengambil panjang lagu
                seekBar.setMax(mediaPlayer.getDuration());
                // Memulai memutar lagu
                mediaPlayer.start();
                new Thread(this).start();
            }
            wasPlaying = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // Menacari posisi SeekBar's thumb saat ii
        int currentPosition = mediaPlayer.getCurrentPosition();
        int total = mediaPlayer.getDuration();
        while (mediaPlayer != null && mediaPlayer.isPlaying() && currentPosition < total) {
            try {
                Thread.sleep(1000);
                currentPosition = mediaPlayer.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            seekBar.setProgress(currentPosition);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearMediaPlayer();
    }

    private void clearMediaPlayer() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

}
