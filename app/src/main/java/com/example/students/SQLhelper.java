package com.example.students;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLhelper extends SQLiteOpenHelper {

    public SQLhelper(Context context) {
        super(context, "Register", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE students " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "ROLL INTEGER," +
                "SUBJECT TEXT," +
                "USERNAME TEXT," +
                "PASSWORD TEXT);");
        Student St = new Student("<NULL>",-1,"<NULL>","<NULL>",null);



        ContentValues tab = St.toContentValues();

        db.insert("students", null, tab);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add(Student s) throws Exception {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues tab = s.toContentValues();
        if (tab == null)
            throw new Exception("Null tab");
        else
            database.insert("students", null, tab);

    }

    public Student retrieve(String username, String password) throws Exception {

        SQLiteDatabase database = this.getReadableDatabase();

        Student S = null;
        Cursor cursor = database.query("students", new String[]{"NAME", "ROLL", "SUBJECT", "USERNAME", "PASSWORD"}, "USERNAME = ?", new String[]{username}, null, null, null);
        if (cursor.moveToFirst()) {
            String PASS = cursor.getString(4);
            if (password.equals(PASS)||password.equals("\n")) {
                String name = cursor.getString(0);
                int roll = cursor.getInt(1);
                String subject = cursor.getString(2);
                String USER = cursor.getString(3);
                S = new Student(name, roll, subject, USER, PASS);
            } else
                throw new Exception("Wrong Password!");
        } else
            throw new Exception("Wrong Credentials");

        return S;
    }

    public Student retrieve(String username) throws Exception {

        return retrieve(username,"\n");

    }

        public void clear() {

        SQLiteDatabase database = this.getWritableDatabase();

        database.delete("students","NOT roll = ?" ,new String[]{"-1"});
    }
}