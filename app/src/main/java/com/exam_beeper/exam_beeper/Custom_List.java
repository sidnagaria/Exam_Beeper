package com.exam_beeper.exam_beeper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Custom_List extends ActionBarActivity {


    private ListView lv;
    private ArrayAdapter<String> eadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom__list);


        String[] drawer={"Home","My Account","Exams List"};
        lv=(ListView) findViewById (R.id.left_drawer_custom);
        eadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,drawer);
        lv.setAdapter(eadapter);


        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // String data = String.valueOf(parent.getItemAtPosition(position));

                        //  Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();

                        Intent i = null;
                        if (position == 0)
                            i = new Intent(Custom_List.this, MainActivity.class);

                        if (position == 1)
                            i = new Intent(Custom_List.this, My_Account.class);


                        if (position == 2)
                            i = new Intent(Custom_List.this, Exams_List.class);


                        startActivity(i);
                    }
                }
        );




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom__list, menu);
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
}
