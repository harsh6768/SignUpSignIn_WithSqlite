package com.example.myfriends.signup_signin_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        boolean result=databaseHelper.onInsert(mUsername.getText().toString(),mEmail.getText().toString(),mPass.getText().toString());

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
