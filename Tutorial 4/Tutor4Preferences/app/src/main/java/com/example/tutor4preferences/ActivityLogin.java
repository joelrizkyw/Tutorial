package com.example.tutor4preferences;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {
    private EditText viewUser, viewPass;
    private Button btnSignIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewUser = (EditText) findViewById(R.id.user);
        viewPass = (EditText) findViewById(R.id.pass);

        viewPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    checkData();
                    return true;
                }

                return false;
            }
        });

        btnSignIn = (Button) findViewById(R.id.btn_signIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();
            }
        });

        btnSignUp = (Button) findViewById(R.id.btn_signUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ActivityRegister.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(Preferences.getLoggedInStatus(getBaseContext())) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);

            finish();
        }
    }

    private void checkData() {
        viewUser.setError(null);
        viewPass.setError(null);

        View fokus = null;
        boolean cancel = false;

        String user = viewUser.getText().toString();
        String pass = viewPass.getText().toString();

        if(TextUtils.isEmpty(user)) {
            viewUser.setError("This field is required");
            fokus = viewUser;
            cancel = true;
        } else if(!checkUser(user)) {
            viewUser.setError("This username is not found");
            fokus = viewUser;
            cancel = true;
        }

        if(TextUtils.isEmpty(pass)) {
            viewPass.setError("This field is required");
            fokus = viewPass;
            cancel = true;
        } else if(!checkPass(pass)) {
            viewPass.setError("This password is incorrect");
            fokus = viewPass;
            cancel = true;
        }

        if(cancel) {
            fokus.requestFocus();
        } else {
            masuk();
        }
    }

    private void masuk() {
        Preferences.setLoggedInUser(getBaseContext(), Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(), true);

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);

        finish();
    }

    private boolean checkUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    private boolean checkPass(String pass) {
        return pass.equals(Preferences.getRegisteredPass(getBaseContext()));
    }
}
