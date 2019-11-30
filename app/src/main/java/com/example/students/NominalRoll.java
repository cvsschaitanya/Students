package com.example.students;

import android.os.Bundle;

import com.example.students.dummy.DummyContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class NominalRoll extends AppCompatActivity implements StudentListFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominal_roll);


    }

    public void onListFragmentInteraction(String username){

        StudentDetail DetBoard = (StudentDetail) getSupportFragmentManager().findFragmentById(R.id.detBoard);
        DetBoard.setData(username);

    }
}
