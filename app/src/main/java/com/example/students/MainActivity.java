package com.example.students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        File file = new File(getFilesDir(),"students.dat");
        /*TextView OUTPUT = (TextView) findViewById(R.id.home_output);

        if(file.exists())
        {
            OUTPUT.setText("FILE EXISTS");
        }
        else
        {
            OUTPUT.setText("NO FILE EXISTS");
        }*/

        AdapterView.OnItemClickListener listOfItems = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0:
                        signin(view);
                        break;

                    case 1:
                        signup(view);
                        break;

                    case 2:
                        list(view);
                        break;

                    case 3:
                        clearData(view);
                        break;
                }
            }
        };

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(listOfItems);

    }


    public void signup(View view){
        Intent intent = new Intent(this,SIGNUP.class);
        startActivity(intent);
    }

    public void signin(View view){
        Intent intent = new Intent(this,SIGNIN.class);
        startActivity(intent);
    }

    private void list(View view) {
        Intent intent = new Intent(this,NominalRoll.class);
        startActivity(intent);
    }

    public void clearData(View view) {
        SQLhelper helper = new SQLhelper(this);

  /*      SQLiteDatabase database = helper.getReadableDatabase();
        if(!this.deleteDatabase("register.db"))
        {
            CharSequence text = "Database not deleted!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

        }
  */


        helper.clear();
        new MyToast(this,"Data Cleared");

    }

    public void signup(){
        Intent intent = new Intent(this,SIGNUP.class);
        startActivity(intent);
    }

    public void signin(){
        Intent intent = new Intent(this,SIGNIN.class);
        startActivity(intent);
    }

    public void clearData() {
        File file = new File(getFilesDir(), "students.dat");
        file.delete();
        TextView OUTPUT = (TextView) findViewById(R.id.home_output);

    }


}
