package com.example.students;

import java.util.ArrayList;

public class Date {

    int date,month,year;
    static int[] M31 = new int[]{1, 3, 5, 7, 8, 10, 12};

    public Date(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public Date() {
        this(0,0,0);
    }


    boolean isleap() {
        if ((year % 4)==0) return true;
        else return false;
    }

    boolean is31()
    {
        boolean IS31 = false;
        for(int y:M31)
        {
            if(M31[y] == month)
                IS31 = true;
        }
        return IS31;
    }

    boolean isValid()
    {
        if(date<1||month<1||month>12)
            return false;

        if(this.is31())
        {
            if(date >31)
                return false;
        }
        else if (month == 2)
        {
            if(isleap())
            {
                if(date>29)
                    return false;
            }
            else
            {
                if(date>28)
                    return false;
            }

        }
        else
        {
            if(date>30)
                return false;
        }
        return true;
    }

    public Date clone()
    {
        Date New = new Date(date,month,year);
        return New;
    }

    public void increment()
    {
        ++date;
        if(!isValid())
        {
            date=1;
            month++;
            if(!isValid())
            {
                month = 1;
                year ++;
            }
        }
    }
    public void decrement()
    {
        date--;
        if(!isValid())
        {
            month--;

            if(month == 2)
                date = isleap() ? 29 : 28;
            else
                date = is31() ? 31 :30;

            if(!isValid())
            {
                year --;
                month = 12;
            }
        }
    }

    public Date next()
    {
        Date next = clone();
        next.increment();
        return next;
    }

    public Date previous()
    {
        Date next = clone();
        next.decrement();
        return next;
    }

    public String toString()
    {
        return date+"/"+month+"/"+year;
    }

    public static Date parseDate(String str)
    {
        str=str+"/";
        int start=0,flag=0;
        int date=0,month=0,year=0;
        for(int i=0;i<str.length();++i)
        {
            if(str.charAt(i)=='/')
            {
                String temp=str.substring(start,i);
                int t=Integer.parseInt(temp);
                switch(flag)
                {
                    case 0:date=t;break;
                    case 1:month=t;break;
                    case 2:year=t;break;
                }
                start=i+1;
            }
        }
        return new Date(date,month,year);
    }

    public int hashCode()
    {
        String X="";
        if(date<=9) X+="0";
        X+=date;
        if(month<=9) X+="0";
        X+=month;
        if(year<=999) X+="0";
        X+=year;
        return Integer.parseInt(X);
    }
}
