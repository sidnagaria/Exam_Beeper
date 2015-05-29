package com.exam_beeper.exam_beeper;

/**
 * Created by lenovo on 30-04-2015.
 */
public class Exams {

private int _id;
    private String _examname;
    private int _year;
    private int _month;
    private int _date;



    private String _category;

    public Exams()
    {

    }

    public Exams(String examname, int year, int month, int date, String category) {
        this._examname = examname;
        //System.out.println("PRINT");
        this._year = year;
        this._month = month+1;
        this._date = date;
        this._category=category;
    }


    public void set_year(int _year) {
        this._year = _year;
    }

    public void set_month(int _month) {
        this._month = _month;
    }

    public void set_date(int _date) {
        this._date = _date;
    }

    public int get_year() {

        return _year;
    }

    public int get_month() {
        return _month;
    }

    public int get_date() {
        return _date;
    }


    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_examname(String _examname) {
        this._examname = _examname;
    }



    public int get_id() {
        return _id;
    }

    public String get_examname() {
        return _examname;
    }
    public String get_category() {
        return _category;
    }

}