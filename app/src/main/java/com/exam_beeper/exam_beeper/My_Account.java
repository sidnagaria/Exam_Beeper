package com.exam_beeper.exam_beeper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class My_Account extends ActionBarActivity implements View.OnClickListener {

int yr, mnth, dte;
    EditText editText;
    DatePicker datePicker;
    TextView textView;
    MyDBHandler dbhandler;
    Button button1;
    Button button2;
    private AutoCompleteTextView autoComplete;



    private ListView lv;
    private ArrayAdapter<String> eadapter;
    private ArrayAdapter<String> eadapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__account);

        String[] autocomp={ "Science and Engineering",
                "Medicine and Health Care",
                "Banking and Finance",
                "Arts, Law and Teaching",
                "Media and Mass Communication",
                "Management"};

        autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        dbhandler = new MyDBHandler(this, null, null, 1);
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
      datePicker = (DatePicker) findViewById(R.id.datePicker);
        Calendar today = Calendar.getInstance();
       yr= today.get(Calendar.YEAR);
             mnth= today.get(Calendar.MONTH);
                dte=today.get(Calendar.DAY_OF_MONTH);








        String[] drawer = {"Home", "Exams List", "Create Custom List"};
        lv = (ListView) findViewById(R.id.left_drawer_account);
        eadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawer);
        lv.setAdapter(eadapter);



        eadapter2= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, autocomp);
        autoComplete.setAdapter(eadapter2);
        autoComplete.setThreshold(0);






        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
//        datePicker.setOnClickListener(this);

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // String data = String.valueOf(parent.getItemAtPosition(position));

                        //  Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();

                        Intent i = null;
                        if (position == 0)
                            i = new Intent(My_Account.this, MainActivity.class);

                        if (position == 1)
                            i = new Intent(My_Account.this, Exams_List.class);


                        if (position == 2)
                            i = new Intent(My_Account.this, Custom_List.class);


                        startActivity(i);
                    }
                }
        );

        //  addButtonClicked();





        datePicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view,
                                              int year, int monthOfYear, int dayOfMonth) {

yr=year;
                        mnth=monthOfYear;
                        dte=dayOfMonth;
                    }
                });




















    }

    /*public void addButtonClicked()
    {
        Button.OnClickListener buttonAddOnClickListener
                = new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Exams exam= new Exams(editText.getText().toString());
                dbhandler.addExam(exam);
                printDatabase();
            }
        };*/
/*
        Exams exam= new Exams(editText.getText().toString());
        dbhandler.addExam(exam);
    printDatabase();*/
    //}

    /*public void deleteButtonClicked(View view) {
        dbhandler.deleteExam(editText.getText().toString());
        printDatabase();
    }
*/

    public void printDatabase() {
        String dbString = dbhandler.databaseToString();

        textView.setText(dbString);
        editText.setText("");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my__account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, About.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {


        if (v == button1) {
            Exams exam = new Exams(editText.getText().toString(),yr, mnth, dte, autoComplete.getText().toString());
            dbhandler.addExam(exam);
            printDatabase();
            Toast.makeText(My_Account.this, "Entry Done", Toast.LENGTH_SHORT).show();
        }
        if(v==button2)
        {
            dbhandler.deleteExam(editText.getText().toString());
            printDatabase();
            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
        }
       /* if(v==datePicker)
        {

           // Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
           public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay)
            {
                System.out.println(selectedYear+ selectedMonth+ selectedDay);
            }
        }
*/

    }

}