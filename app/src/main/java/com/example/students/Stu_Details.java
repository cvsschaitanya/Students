package com.example.students;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class Stu_Details extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Student S = (Student) intent.getSerializableExtra("Student");

        //String[] details = S.toArray();
       SQLhelper helper = new SQLhelper(this);
       new GetListTask ().execute(this);


    }

    class GetListTask extends AsyncTask<Stu_Details,Void,Object>
    {

        Stu_Details Stu_DetailsObj;

        @Override
        protected Object doInBackground(Stu_Details[] studetObj) {
            try
            {
                Stu_DetailsObj = (Stu_Details)studetObj[0];

                SQLhelper helper = new SQLhelper(Stu_DetailsObj);
                SQLiteDatabase database =  helper.getReadableDatabase();

                Cursor cursor;

                cursor = database.query("students", new String[]{"_id","USERNAME", "PASSWORD"}, "NOT roll = ?" ,new String[]{"-1"}, null, null, null);
                //cursor = database.query("students", new String[]{"_id","USERNAME", "PASSWORD"}, null, null, null, null, null);

                return cursor;


            } catch (Exception e) {
                return e;
            }
        }

        protected void onPostExecute(Object object) {
            if (object instanceof Cursor) {
                CursorAdapter listAdapter = new SimpleCursorAdapter(Stu_DetailsObj, android.R.layout.simple_list_item_1, (Cursor)object, new String[]{"USERNAME"}, new int[]{android.R.id.text1}, 0) {

                };

                final ListView listView = getListView();
                listView.setAdapter(listAdapter);
            }
            else {
                new MyToast(Stu_DetailsObj, ((Exception)object).getMessage());
            }
        }
    }

}
