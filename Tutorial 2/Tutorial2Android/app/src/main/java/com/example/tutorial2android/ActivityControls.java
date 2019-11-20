package com.example.tutorial2android;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class ActivityControls extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview_example);
    }
}
