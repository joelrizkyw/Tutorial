package com.example.tutorial2android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;

public class ActivityAutoComplete extends Activity {
    AutoCompleteTextView auto;
    String[] arr = {"Adrian", "Sofyan", "Arya", "Faris", "Rayhan", "Selgi"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete_example);

        auto = (AutoCompleteTextView) findViewById(R.id.autoTxt1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, arr);

        auto.setThreshold(2);
        auto.setAdapter(adapter);
    }
}
