package com.example.paddy.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.annotation.UiThread;
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
    EditText username;
    EditText password;
    Bundle data=new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username= (EditText) findViewById(R.id.username);

        password= (EditText) findViewById(R.id.password);

        Button submit= (Button) findViewById(R.id.submit);

        Button register= (Button)findViewById(R.id.register);


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

                UserNameText= String.valueOf(username.getText());

                PasswordText= String.valueOf(password.getText());
try {
    new UpdateTask().execute().get();
}
catch (Exception e)
{
  e.printStackTrace();
}
                Toast toast= Toast.makeText(getApplicationContext(),"Submission Successful",Toast.LENGTH_SHORT);
                toast.show();


                    Intent intentWelcome = new Intent(MainActivity.this, Welcome.class);
                    intentWelcome.putExtras(data);
                    startActivity(intentWelcome);

            }
        };

        submit.setOnClickListener(SubmitListener);
        register.setOnClickListener(RegisterListener);




        }






    private class UpdateTask extends AsyncTask<Bundle,Boolean,Boolean>
    {


        boolean flag=false;
        String userCheck;
        String passCheck;

        @Override
        protected void onPreExecute() {



        }

        @Override
        protected Boolean doInBackground(Bundle... data2) {


            try {

                SQLiteOpenHelper helper=new Database(getApplicationContext());
                SQLiteDatabase db=  helper.getReadableDatabase();

                Cursor cursor= db.query("USERINFO",new String[]{ "USERNAME,PASSWORD"},null,null,null,null,null);
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {



                    userCheck = cursor.getString(cursor.getColumnIndex("USERNAME"));

                    passCheck = cursor.getString(cursor.getColumnIndex("PASSWORD"));


                    if(userCheck.equals(UserNameText)&&passCheck.equals(PasswordText))
                    {

                        flag=true;
                        break;


                    }


                    cursor.moveToNext();

               }

                cursor.close();

                if(flag)
                {

                    data.putString(Welcome.UserName,UserNameText);
                    data.putString(Welcome.Password,PasswordText);
                    data.putBoolean(Welcome.Exists,true);
                }
                else {
                    data.putString("LOL","LOL");
                    data.putBoolean(Welcome.Exists, false);
                }

                return true;
            } catch (Exception e) {
                e.printStackTrace();

            }

return false;
        }
    }

}
