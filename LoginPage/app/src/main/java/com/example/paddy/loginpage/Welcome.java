package com.example.paddy.loginpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Welcome extends AppCompatActivity {

     static final String UserName="USERNAME";
   static final String Password="PASSWORD";
     static final String Exists= "Exists";
    private  final String NotExists="Sorry! Username and Password do not match or exist.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView welcomeText= (TextView)findViewById(R.id.WelcomeIntro);

        Intent intent=getIntent();
        if(intent.getExtras().getBoolean(Exists))
        {
            String username = intent.getStringExtra("USERNAME");


            welcomeText.setText("Hello " + username + ".\n Welcome to this World. ");
        }
        else
        {
            welcomeText.setText(NotExists);

        }
    }
}
