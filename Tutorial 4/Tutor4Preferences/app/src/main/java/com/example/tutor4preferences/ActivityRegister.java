package com.example.tutor4preferences;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister extends AppCompatActivity {
    private EditText editUser, editPass, editConfirmPass;
    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUser = (EditText) findViewById(R.id.user_signup);
        editPass = (EditText) findViewById(R.id.pass_signup);
        editConfirmPass = (EditText) findViewById(R.id.pass_signup_confirm);

        editConfirmPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    checkData();
                    return true;
                }

                return false;
            }
        });

        btnReg = (Button) findViewById(R.id.btn_register);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();
            }
        });
    }

    private void checkData() {
        editUser.setError(null);
        editPass.setError(null);
        editConfirmPass.setError(null);

        View fokus = null;
        boolean cancel = false;

        String user = editUser.getText().toString();
        String pass = editPass.getText().toString();
        String repass = editConfirmPass.getText().toString();

        if(TextUtils.isEmpty(user)) {
            editUser.setError("This field is required");
            fokus = editUser;
            cancel = true;
        } else if(checkUser(user)) {
            editUser.setError("This username is already exist");
            fokus = editUser;
            cancel = true;
        }

        if(TextUtils.isEmpty(pass)) {
            editPass.setError("This field is required");
            fokus = editPass;
            cancel = true;
        } else if(!checkPass(pass, repass)) {
            editPass.setError("This password is incorrect");
            fokus = editPass;
            cancel = true;
        }

        if(cancel) {
            fokus.requestFocus();
        } else {
            Preferences.setRegisteredUser(getBaseContext(), user);
            Preferences.setRegisteredPass(getBaseContext(), pass);

            finish();
        }
    }

    private boolean checkUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    private boolean checkPass(String pass, String repass) {
        return pass.equals(repass);
    }
}
