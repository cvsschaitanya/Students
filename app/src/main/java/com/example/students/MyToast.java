package com.example.students;

import android.content.Context;
import android.widget.Toast;

public class MyToast {

    public MyToast(Context context,String msg) {
        Toast toast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        toast.show();
    }



}
