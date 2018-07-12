package com.example.myfriends.signup_signin_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText logEmail,logPass;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper=new DatabaseHelper(this);

        logEmail=(EditText)findViewById(R.id.log_emailId);
        logPass=(EditText)findViewById(R.id.log_passId);


    }

    public void onSignIn(View view)
    {

        boolean result=databaseHelper.onRetrive(logEmail.getText().toString(),logPass.getText().toString());

        if(result)
        {
            Toast.makeText(this,"Login Successful!!!",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,LandingPage.class);
            startActivity(intent);
        }
        else
        {

            Toast.makeText(this,"Login Failed!!!",Toast.LENGTH_LONG).show();

        }
    }


    public void onRegiter(View view)
    {

        Intent intent=new Intent(this,RegistrationActivity.class);
        startActivity(intent);

    }
}
