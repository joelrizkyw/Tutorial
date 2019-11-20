package com.example.tutorial2android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ActivityEditText extends Activity {
    EditText eText;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editext_example);

        eText = (EditText) findViewById(R.id.ediTxt1);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = eText.getText().toString();
                Toast msg = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                msg.show();
            }
        });
    }
}
