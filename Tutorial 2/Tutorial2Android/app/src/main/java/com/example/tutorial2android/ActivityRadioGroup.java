package com.example.tutorial2android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ActivityRadioGroup extends Activity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiogroup_example);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(ActivityRadioGroup.this, radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}


