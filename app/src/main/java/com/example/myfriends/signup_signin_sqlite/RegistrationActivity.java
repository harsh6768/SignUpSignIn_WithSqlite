package com.example.myfriends.signup_signin_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mUsername,mEmail,mPass;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        databaseHelper=new DatabaseHelper(this);

        mUsername=(EditText)findViewById(R.id.reg_usernameId);
        mEmail=(EditText)findViewById(R.id.reg_emailId);
        mPass=(EditText)findViewById(R.id.reg_passId);

    }

    public void onSignUp(View view)
    {

        String username=mUsername.getText().toString().trim();
        String email=mEmail.getText().toString().trim();
        String pass=mPass.getText().toString().trim();

        if(username.isEmpty()){
            mUsername.setError("username is empty");
        }else if(email.isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            mEmail.setError("email is empty or not valid");
        }else if(pass.isEmpty()){
            mPass.setError("password is empty");
        }else{

            //to check email already exist or not
            boolean isExist=databaseHelper.onEmailCheck(email);

            if(isExist){
                mEmail.setError("Email Already Exist");
            }else{

                boolean result=databaseHelper.onInsert(mUsername.getText().toString(),mEmail.getText().toString(),mPass.getText().toString());

                //if data inserted then navigate to login activity
                if(result)
                {
                    Toast.makeText(RegistrationActivity.this,"Successfully Registered!!!",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(RegistrationActivity.this,"Failed!!!",Toast.LENGTH_LONG).show();

            }

        }


    }
}
