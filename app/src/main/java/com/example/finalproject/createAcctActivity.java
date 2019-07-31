package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class createAcctActivity extends AppCompatActivity {

    public static final String TAG = "createAcctACTIVITY_Log";

    Button createAcct;

    TextView name;
    TextView email;
    TextView password;

    userControl UserControl;
    user User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acct);


        name = findViewById(R.id.create_name);
        email = findViewById(R.id.create_email);
        password = findViewById(R.id.create_password);
        createAcct = findViewById(R.id.submitAcct);

        UserControl = userControl.get(this.getApplicationContext());
        User = new user();
    }

    private boolean  exist(){
        String namez = name.getText().toString();
        String emailz = email.getText().toString();
        String passwordz = password.getText().toString();

        return UserControl.exist(emailz, passwordz);
    }

    public void createAccount(View view) {
        Log.i(TAG, "create account button clicked");


        if(!exist()){
            if(!name.getText().toString().equals("") && !email.getText().toString().equals("") && !password.getText().toString().equals("")){
                if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    User.setAdmin(true);
                    User.setName(name.getText().toString());
                    User.setEmail(email.getText().toString());
                    User.setPassword(password.getText().toString());
                    UserControl.addUser(User); // can you use a database for multiple activities ???
                    Toast t = Toast.makeText(this, "ADMIN ACCOUNT CREATED SUCCESSFULLY", Toast.LENGTH_LONG);
                    t.show();
                    Intent intent = new Intent(createAcctActivity.this, adminLogin.class);
                    startActivity(intent);

                }
                else{
                    User.setAdmin(false);
                    User.setName(name.getText().toString());
                    User.setEmail(email.getText().toString());
                    User.setPassword(password.getText().toString());
                    UserControl.addUser(User); // can you use a database for multiple activities ???
                    Toast t = Toast.makeText(this, "ACCOUNT CREATED SUCCESSFULLY", Toast.LENGTH_LONG);
                    t.show();
                    Intent intent = new Intent(createAcctActivity.this, logInActivity.class);
                    startActivity(intent);
                }
            }
            else{
                Toast t = Toast.makeText(this, "TRY AGAIN", Toast.LENGTH_LONG);
                t.show();
            }

        }
        else{
            Toast t = Toast.makeText(this, "USER ALREADY EXISTS", Toast.LENGTH_LONG);
            t.show();

        }

    }
}
