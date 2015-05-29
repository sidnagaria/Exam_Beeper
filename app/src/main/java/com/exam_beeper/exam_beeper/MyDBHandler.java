package com.exam_beeper.exam_beeper;

/**
 * Created by lenovo on 30-04-2015.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;


import java.sql.SQLClientInfoException;

public class MyDBHandler extends SQLiteOpenHelper {

    //Examcount
    public int i=0;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "exams.db";
    private static final String TABLE_EXAMS = "exams";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EXAMNAME = "_examname";
    private static final String COLUMN_YEAR = "_year";
    private static final String COLUMN_MONTH = "_month";
    private static final String COLUMN_DATE = "_date";
    private static final String COLUMN_CATEGORY="_category";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_EXAMS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXAMNAME + " VARCHAR(40), " +
                COLUMN_YEAR + " INTEGER, "+
                COLUMN_MONTH + " INTEGER, "+
                COLUMN_DATE + " INTEGER, " +
                COLUMN_CATEGORY+" VARCHAR(40) "+
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_EXAMS);
        onCreate(db);
    }


    public void addExam(Exams exam) {
    ContentValues values = new ContentValues();
        values.put("_examname", exam.get_examname());
        values.put("_year", exam.get_year());
        values.put("_month", exam.get_month());
        values.put("_date", exam.get_date());
        values.put(COLUMN_CATEGORY, exam.get_category());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EXAMS, null, values);


      /*  SQLiteDatabase db = getWritableDatabase();
        System.out.println("val : "+exam.get_examname());

        db.execSQL("INSERT INTO "+ TABLE_EXAMS +" (" + COLUMN_EXAMNAME+") "+ "VALUES ('" + exam.get_examname()+"');");
//db.execSQL("INSERT INTO "+ TABLE_EXAMS +" (" + COLUMN_MONTH+") "+ "VALUES (" + exam.get_month()+");");

        System.out.println("DATE: "+exam.get_year()+" "+exam.get_month()+" "+exam.get_date());

       db.execSQL("INSERT INTO "+ TABLE_EXAMS +" (" + COLUMN_YEAR + ", "+COLUMN_MONTH+", "+COLUMN_DATE+") "+ "VALUES (" +exam.get_year()+", "+exam.get_month()+", "+exam.get_date()+");");
        /*db.execSQL("INSERT INTO "+ TABLE_EXAMS +" (" + COLUMN_EXAMNAME+", "+COLUMN_YEAR + ", "+COLUMN_MONTH+", "+COLUMN_DATE+") "
               + "VALUES (" +
                        exam.get_examname()+ ", "+exam.get_year()+", "+exam.get_month()+", "+exam.get_date()+");"

        );
        */
        //Log.e("hello", "message");*/
                db.close();
    }

    public void deleteExam(String examname) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_EXAMS + " WHERE " + COLUMN_EXAMNAME + "=\"" + examname + "\";");
    }

    public String databaseToString() {
        String dbString = "";

        try {
            String query = "SELECT * FROM " + TABLE_EXAMS + ";";
            //String dbString = "";
            //SQLiteDatabase db = getWritableDatabase();
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() == 0) {
               // Log.e("NO DATA", "NO DATA");
              //  Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                return dbString;
            }

            //while(!cursor.isAfterLast()) {

            cursor.moveToFirst();
            // cursor.moveToPosition(0);
            for (; !cursor.isAfterLast(); cursor.moveToNext()) {
                Log.e("Print", "Print");
                //if(cursor.getCount()>0)
                //String num = cursor.getString(cursor.getColumnIndex("productname"));
                if (cursor.getString(1) != null) {

                      dbString += "Id: " + cursor.getString(0) + "\t Exmaname: " + cursor.getString(1)+" Date "+cursor.getString(2)+"-"+cursor.getString(3)+"-"+cursor.getString(4);
                        dbString += "\n";


                }
            }







               /* if (num != null) {
                    dbString += cursor.getString(cursor.getColumnIndex("productname"));
                    dbString += "\n";
                }*/


            cursor.close();
            //}
            db.close();


           // return dbString;
        }
        catch (SQLiteException e)
        {

        }
        return dbString;
    }







    public String[] SearchExam()
    {
        String s[]=new String[100];
        i=0;

        try{

            String query = "SELECT * FROM " + TABLE_EXAMS + ";";


            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() == 0) {

                return s;
            }

            cursor.moveToFirst();

            for (; !cursor.isAfterLast(); cursor.moveToNext()) {
              //  Log.e("Print", "Print");

                if (cursor.getString(1) != null) {
                    //Initailizing as Null
                    s[i]="";
                    s[i++] += cursor.getString(1);

                    }
            }
cursor.close();
            db.close();


        }
        catch(SQLiteException e)
        {

        }
        return s;
    }



public String[] SearchDate()
{

    String d[]=new String[100];
    i=0;

    try{

        String query = "SELECT * FROM " + TABLE_EXAMS + ";";


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {

            return d;
        }

        cursor.moveToFirst();

        for (; !cursor.isAfterLast(); cursor.moveToNext()) {
            //  Log.e("Print", "Print");

            if (cursor.getString(1) != null) {
                //Initailizing as Null
                d[i]="";
                if(Integer.parseInt(cursor.getString(3))<=9&& Integer.parseInt(cursor.getString(4))<=9)
                    d[i++] += cursor.getString(2)+"-0"+cursor.getString(3)+"-0"+cursor.getString(4);
                else if(Integer.parseInt(cursor.getString(3))<=9)
                    d[i++] += cursor.getString(2)+"-0"+cursor.getString(3)+"-"+cursor.getString(4);
                else if(Integer.parseInt(cursor.getString(4))<=9)
                    d[i++] += cursor.getString(2)+"-"+cursor.getString(3)+"-0"+cursor.getString(4);
                else
                    d[i++] += cursor.getString(2)+"-"+cursor.getString(3)+"-"+cursor.getString(4);

            }
        }
        cursor.close();
        db.close();


    }
    catch(SQLiteException e)
    {

    }
    return d;
}




    public String[] SearchCategory()
    {
        String se[]=new String[100];
        i=0;

        try{

            String query = "SELECT * FROM " + TABLE_EXAMS + ";";


            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() == 0) {

                return se;
            }

            cursor.moveToFirst();

            for (; !cursor.isAfterLast(); cursor.moveToNext()) {
                //  Log.e("Print", "Print");

                if (cursor.getString(5) != null) {
                    //Initailizing as Null
                    se[i]="";
                    se[i++] += cursor.getString(5);

                }
            }
            cursor.close();
            db.close();


        }
        catch(SQLiteException e)
        {

        }
        return se;

    }



    public int ExamCount()
    {
return i;
    }



}