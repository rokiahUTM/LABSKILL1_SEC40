package com.example.labskill1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button loginButton,cancelButton;
    EditText usernameET,passwordET;

    TextView tx1, registerTV, forgotPwdTV;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = getSharedPreferences("login_details",MODE_PRIVATE);

        usernameET = (EditText)findViewById(R.id.userNameET);
        passwordET = (EditText)findViewById(R.id.passwordET);
        loginButton = (Button)findViewById(R.id.loginBtn);
        cancelButton = (Button)findViewById(R.id.cancelLoginBtn);
        registerTV = (TextView) findViewById(R.id.registerTV);
        forgotPwdTV = (TextView) findViewById(R.id.forgotPwdTV);
        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);

        if(pref.contains("username"))
            loginButton.setEnabled(true);

        loginButton.setOnClickListener(new ProcessClick());
        cancelButton.setOnClickListener(new ProcessClick());
        registerTV.setOnClickListener(new ProcessClick());
        forgotPwdTV.setOnClickListener(new ProcessClick());



    }

    public void login(){
        String username = pref.getString("username","@54321");
        String password = pref.getString("password","@54321");
        if(usernameET.getText().toString().equals(username) && passwordET.getText().toString().equals(password)) {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

            tx1.setVisibility(View.VISIBLE);
            tx1.setBackgroundColor(Color.RED);
            counter--;
            tx1.setText(Integer.toString(counter));

            if (counter == 0) {
                loginButton.setEnabled(false);
            }
        }
    }

    public void registerLogin(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.register_dialog,null);
        final EditText newUsernameET = (EditText)mView.findViewById(R.id.newUsernameET);
        final EditText newPasswordET = (EditText)mView.findViewById(R.id.newPasswordET);
        final EditText newEmailET = (EditText)mView.findViewById(R.id.newEmailET);
        Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);
        Button btn_okay = (Button)mView.findViewById(R.id.btn_okay);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = newUsernameET.getText().toString();
                String newPassword = newPasswordET.getText().toString();
                String newEmail = newEmailET.getText().toString();

                if(!newUsername.isEmpty()&&!newPassword.isEmpty()&&!newEmail.isEmpty()) {
                    editor = pref.edit();
                    editor.putString("username", newUsername);
                    editor.putString("password", newPassword);
                    editor.putString("email", newEmail);
                    editor.commit();
                    loginButton.setEnabled(true);
                }

                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    class ProcessClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.loginBtn: login();
                                    break;
                case R.id.cancelLoginBtn:  finish();
                                           break;
                case R.id.registerTV: registerLogin();
                                      break;
                case R.id.forgotPwdTV:
                                      break;
            }
        }

    }


}