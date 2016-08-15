package com.example.paddy.loginpage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
     private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = (EditText)findViewById(R.id.userNameRegister);
        password= (EditText)findViewById(R.id.userPasswordRegister);

        new UpdateTask().execute();

    }






    private class UpdateTask extends AsyncTask<Void,Void,Boolean>
    {
        ContentValues cv;
        @Override
        protected void onPreExecute() {

            cv=new ContentValues();
            cv.put("USERNAME", String.valueOf(username.getText()));
            cv.put("PASSWORD", String.valueOf(password.getText()));

        }

        @Override
        protected Boolean doInBackground(Void... voids) {


            try {
                SQLiteOpenHelper helper=new Database(getApplicationContext());
                SQLiteDatabase db=  helper.getWritableDatabase();
                db.insert("USERINFO",null,cv);

                Toast.makeText(getApplicationContext(),"User Registered Successfully", Toast.LENGTH_SHORT).show();
                return  true;
            } catch (Exception e) {
                e.printStackTrace();
                return  false;
            }


        }
    }
}
