package com.example.students;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentDetail extends Fragment {

    Context CurrentContext;

    String Username;
    String FillString;

    LayoutInflater Inflater;

    public StudentDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Inflater = inflater;

        return inflater.inflate(R.layout.fragment_student_details, container, false);

    }

    @Override
    public void onStart(){
        super.onStart();

        CurrentContext = Inflater.getContext();


    }

    public void setData(String Username)
    {
        this.Username = Username;
        updateFillString();
    }

    void updateFillString()
    {
        new GetDataTask().execute();
    }

    class GetDataTask extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void[] voids) {

            SQLhelper helper = new SQLhelper(CurrentContext);
            SQLiteDatabase database = helper.getReadableDatabase();

            try {
                Student S = helper.retrieve(Username);
                FillString = "NAME: "+S.name+"\nRoll No.: "+S.roll+"\nSubject: "+S.Subject+"\nUsername: "+S.Username+"\nPassword: "+S.Password;
//                Student Y=new Student("Ram",23,"Physics","RRR","RRR");
//                CharSequence Json=Y.toString();
//                Y=Student.parseStudent(Json);
//                FillString=Y.toString();
            } catch (Exception e) {
               new MyToast(CurrentContext,e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v)
        {
            View thisView = getView();

            if(thisView!=null)
            {
                TextView detailBox = (TextView) thisView.findViewById(R.id.detailsBox);
                detailBox.setText(FillString);
            }

        }

    }

}
