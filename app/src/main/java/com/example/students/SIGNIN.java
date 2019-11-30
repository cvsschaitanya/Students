package com.example.students;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Path;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;

public class SIGNIN extends AppCompatActivity {

    SQLhelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        helper = new SQLhelper(this);
    }



    public void checkCreden(View view){

        new ExtractAccountTask().execute(this);

    }

    class ExtractAccountTask extends AsyncTask<SIGNIN,Void,Exception>
    {
        Student S;
        String username,password;
        SIGNIN SIGNIN_OBJ;

        protected void onPreExecute()
        {
            EditText USERNAME = (EditText) findViewById(R.id.username_old);
            EditText PASSWORD = (EditText) findViewById(R.id.password_old);

            username = String.valueOf(USERNAME.getText());
            password = String.valueOf(PASSWORD.getText());

        }


        @Override
        protected Exception doInBackground(SIGNIN[] signinObj) {
            try {
                SIGNIN_OBJ = signinObj[0];
                S = helper.retrieve(username,password);
                return null;
            } catch (Exception e) {
                return e;
            }

        }

        protected void onPostExecute(Exception e)
        {
            TextView Opening = (TextView) findViewById(R.id.OPENING);

            if(e == null)
            {
                S.sayHi(Opening);
            }
            else
            {
                Opening.setText(e.getMessage());
            }
        }
    }
}
