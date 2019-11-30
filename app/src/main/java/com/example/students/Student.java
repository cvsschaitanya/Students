package com.example.students;

import android.content.ContentValues;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

    private static final String[] title = {"Name","Roll","Subject","Username","Password"};

    String name,Username,Password,Subject;
    int roll;


    static char delim='\n';

    public boolean isValid(String U,String P)
    {
        return Username.equals(U) && Password.equals(P);
    }

    public Student(String name,int roll, String subject, String username, String password ) {
        this.name = name;
        Username = username;
        Password = password;
        Subject = subject;
        this.roll = roll;
    }

    public void sayHi(TextView opening) {
        opening.setText("Hi "+name);
    }

    public String toString(){
        String R="";
        R = R + name + delim + roll + delim + Subject + delim + Username + delim + Password + delim;
        return R;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return roll == student.roll &&
                name.equals(student.name) &&
                Username.equals( student.Username) &&
                Password.equals(student.Password) &&
                Subject.equals(student.Subject);
    }



    public static Student parseStudent(String str){
        String name = null,Username = null,Password = null,Subject = null;
        int roll = 0;

        int start=0,ele=1 ;
        for(int i=0;i<str.length();++i)
        {
            if(str.charAt(i)==delim)
            {
                switch(ele)
                {
                    case 1:name=str.substring(start,i);break;
                    case 2:roll=Integer.parseInt(str.substring(start,i));break;
                    case 3:Subject=str.substring(start,i);break;
                    case 4:Username=str.substring(start,i);break;
                    case 5:Password=str.substring(start,i);break;
                }
                start=i+1;ele++;
            }
        }
        return new Student(name,roll,Subject,Username,Password);
    }

    String[] toArray()
    {
        String[] det;
        det = new String[]{"Name:"+name, "Roll No:"+roll, "Subject:"+Subject, "Username:"+Username, "Password:"+Password};
        return det;
    }


    public ContentValues toContentValues() {

        ContentValues tab = new ContentValues();
        tab.put("NAME",name);
        tab.put("ROLL",roll);
        tab.put("SUBJECT",Subject);
        tab.put("USERNAME",Username);
        tab.put("PASSWORD",Password);

        return tab;

    }

    public boolean isThisPass(String c_password) {

        return c_password.equals(Password);

    }

}
