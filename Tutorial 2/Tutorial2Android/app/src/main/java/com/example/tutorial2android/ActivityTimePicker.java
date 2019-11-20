package com.example.tutorial2android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class ActivityTimePicker extends Activity {
    private TimePicker timePicker;
    private TextView time;
    private Calendar calendar;
    private String format = "";
    private Button save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepicker_example);

        save = (Button) findViewById(R.id.button);
        timePicker = (TimePicker) findViewById(R.id.timePicker1);
        time = (TextView) findViewById(R.id.textView5);
        calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(view);
            }
        });
    }

    public void setTime(View view) {
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();

        showTime(hour, min);
    }

    public void showTime(int hour, int min) {
        if(hour == 0) {
            hour += 12;
            format = "AM";
        } else if(hour == 12){
            format = "PM";
        } else if(hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        time.setText(new StringBuilder().append(hour).append(" : ").append(min).append(" ").append(format));
    }
}
