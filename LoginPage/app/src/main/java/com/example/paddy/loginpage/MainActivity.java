package com.example.paddy.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {


    private  String UserNameText;
    private  String PasswordText;

   private Intent intentWelcome= new Intent(MainActivity.this,Welcome.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username;
        EditText password;
        username= (EditText) findViewById(R.id.username);

        password= (EditText) findViewById(R.id.password);

        Button submit= (Button) findViewById(R.id.submit);

        Button register= (Button)findViewById(R.id.register);
        UserNameText= String.valueOf(username.getText());

        PasswordText= String.valueOf(password.getText());

       OnClickListener RegisterListener= new OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent= new Intent(MainActivity.this,Registration.class);

               startActivity(intent);
           }
       };

        OnClickListener SubmitListener= new OnClickListener() {
            @Override
            public void onClick(View view) {


                new UpdateTask().execute();
                Toast toast= Toast.makeText(getApplicationContext(),"Submission Successful",Toast.LENGTH_SHORT);
                toast.show();
            }
        };

        submit.setOnClickListener(SubmitListener);
        register.setOnClickListener(RegisterListener);




        }






    private class UpdateTask extends AsyncTask<Void,Void,Boolean>
    {


        boolean flag=false;
        String userCheck;
        String passCheck;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(Void... voids) {


            try {

                SQLiteOpenHelper helper=new Database(getApplicationContext());
                SQLiteDatabase db=  helper.getReadableDatabase();
                Cursor cursor= db.query("USERINFO",new String[]{ "USERNAME,PASSWORD"},null,null,null,null,null);
                if(cursor.moveToFirst()) {
                    userCheck = cursor.getString(0);
                    passCheck = cursor.getString(1);
                }
                while(cursor.moveToNext())
                {



                    if(userCheck.equals(UserNameText)&&passCheck.equals(PasswordText))
                    {
                        flag=true;
                        break;

                    }
                    cursor.moveToNext();
                    userCheck = cursor.getString(0);
                    passCheck = cursor.getString(1);
                }
                cursor.close();
                if(flag)
                {
                    intentWelcome.putExtra(Welcome.UserName,UserNameText);
                    intentWelcome.putExtra(Welcome.Password,PasswordText);
                    intentWelcome.putExtra(Welcome.Exists,true);
                }
                else {
                    intentWelcome.putExtra(Welcome.Exists, false);
                }

                return true;
            } catch (Exception e) {
                e.printStackTrace();

            }

return false;
        }
    }

}
