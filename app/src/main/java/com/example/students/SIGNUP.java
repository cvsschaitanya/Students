package com.example.students;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class SIGNUP extends AppCompatActivity {

    SQLhelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        helper = new SQLhelper(this);

    }

    public void addCreden(View view){

        new InsertRecordTask().execute(this);

    }

    class InsertRecordTask extends AsyncTask<SIGNUP,Void,Exception>
    {

        boolean RollNumberException;
        String c_password;
        Student S;
        SIGNUP SignupObj;

        protected void onPreExecute()
        {
            EditText NAME = (EditText) findViewById(R.id.name);
            EditText ROLL = (EditText) findViewById(R.id.roll);
            EditText SUBJECT = (EditText) findViewById(R.id.sub);
            EditText USERNAME = (EditText) findViewById(R.id.username_new);
            EditText PASSWORD = (EditText) findViewById(R.id.password_new);
            EditText C_PASSWORD = (EditText) findViewById(R.id.c_password_new);

            TextView OUTPUT = (TextView) findViewById(R.id.output);

            String name = String.valueOf(NAME.getText());
            int roll = 0;
            String subject = String.valueOf(SUBJECT.getText());
            String username = String.valueOf(USERNAME.getText());
            String password = String.valueOf(PASSWORD.getText());
            c_password = String.valueOf(C_PASSWORD.getText());

            try {
                roll = Integer.parseInt(String.valueOf(ROLL.getText()));
            } catch (NumberFormatException e) {
                RollNumberException = true;
            }

            S = new Student(name,roll,subject,username,password);

        }

        @Override
        protected Exception doInBackground(SIGNUP... signups) {

            SignupObj = signups[0];

            try{

                if(RollNumberException)
                    return new  NumberFormatException("Enter a valid Roll Number");


                if(!S.isThisPass(c_password))
                    return new Exception("passwords do not match");

                helper.add(S);
                return null;

            }
            catch (SQLException e) {
                return e;
            } catch (Exception e) {
                return e;
            }


        }

        protected void onPostExecute(Exception e)
        {
            if(e==null)
            {
                startActivity(new Intent(SignupObj,MainActivity.class));
                new MyToast(SignupObj,"Account Created");
            }
            else
            {
                new MyToast(SignupObj,e.getMessage());

            }

        }

    }

}
