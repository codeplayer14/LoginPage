package com.example.paddy.loginpage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
        Button button = (Button) findViewById(R.id.button);


        OnClickListener submitListener = new OnClickListener() {
            @Override
            public void onClick(View view) {

                new UpdateTask2().execute();

                Toast toast= Toast.makeText(getApplicationContext(),"Submission Successful",Toast.LENGTH_SHORT);
                toast.show();


            }
        };

        button.setOnClickListener(submitListener);





        }








    private class UpdateTask2 extends AsyncTask<Void,Void,Boolean>
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


                return  true;
            } catch (Exception e) {
                e.printStackTrace();
                return  false;
            }


        }
    }
}
