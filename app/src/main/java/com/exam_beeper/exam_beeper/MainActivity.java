package com.exam_beeper.exam_beeper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import android.view.ViewGroup.LayoutParams;
import java.util.List;



public class MainActivity extends ActionBarActivity {


    private ListView lv;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter <String> eadapter;
    ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           mTitle = mDrawerTitle = getTitle();



        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {
// run your one time code
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
            System.out.println("First");
            Intent i= new Intent(MainActivity.this,First.class);
            startActivity(i);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

//                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

//                getActionBar().setTitle(mDrawerTitle);
            }
        };


        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //   getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);






        String[] countries = new String[]{
                "Science and Engineering",
                "Medicine and Health Care",
                "Banking and Finance",
                "Arts, Law and Teaching",
                "Media and Mass Communication",
                "Management"
        };

        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] flags = new int[]{
                R.drawable.india,
                R.drawable.pakistan,
                R.drawable.srilanka,
                R.drawable.china,
                R.drawable.bangladesh,
                R.drawable.nepal
        };

        // Array of strings to store currencies
        String[] currency = new String[]{
                "JEE MAINS, JEE ADVANCED",
                "NEET, AIPMT",
                "CPT, IPCC",
                "CLAT, NIFT",
                "IIMC, ACJ",
                "CAT, IIFT"
        };


        String[] drawer = {"My Account", "Exams List", "Create Custom List"};
        lv = (ListView) findViewById(R.id.left_drawer);
        eadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawer);
        lv.setAdapter(eadapter);
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // String data = String.valueOf(parent.getItemAtPosition(position));

                        //  Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();

                        Intent i = null;
                        if (position == 0)
                            i = new Intent(MainActivity.this, My_Account.class);

                        if (position == 1)
                            i = new Intent(MainActivity.this, Exams_List.class);


                        if (position == 2)
                            i = new Intent(MainActivity.this, Custom_List.class);


                        startActivity(i);
                    }
                }
        );


// Each row in the list stores exam name, hots and pic
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 6; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("txt", countries[i]);
            hm.put("cur", currency[i] + ".....");
            hm.put("flag", Integer.toString(flags[i]));
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"flag", "txt", "cur"};

        // Ids of views in listview_layout
        int[] to = {R.id.flag, R.id.txt, R.id.cur};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        final SimpleAdapter adapter = new SimpleAdapter(this, aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        // Setting the adapter to the listView
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // String data = String.valueOf(parent.getItemAtPosition(position));

                        //  Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();

                        AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);

                        //  adb.setMessage(" selected Item is="+parent.getItemAtPosition(position));
                        // adb.setPositiveButton("Ok", null);
                        // adb.setTitle((int) parent.getItemIdAtPosition(position));
                        Intent i = null;
                                                i= new Intent(MainActivity.this, Exams_List.class);
            /*            if(position==0)
                           extra="SCIENCE & ENGINNERING";// adb.setTitle("SCIENCE & ENGINNERING");
                        if(position==1)
                            extra="Medicine and Health Care";//   adb.setTitle("Medicine and Health Care");
                        if(position==2)
                            extra="Banking and Finance";// adb.setTitle("Banking and Finance");
                        if(position==3)
                            extra="Arts, Law and Teaching";//adb.setTitle("Arts, Law and Teaching");
                        if(position==4)
                            extra="Media and Mass Communication";//adb.setTitle("Media and Mass Communication");
                        if(position==5)
                            extra="Management";//adb.setTitle("Management");
*/
                       // i.putExtra("category_name",extra);
                        startActivity(i);

                       /* adb.setMessage("SET REMINDER");
                        adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                        adb.setNegativeButton("No", null);
                        AlertDialog alertDialog=adb.create();
                        alertDialog.show();
                        //adb.setNeutralButton("Cancel", null);
                        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        theButton.setOnClickListener(new CustomListener(alertDialog));
                            */


                    }
                }
        );










    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager searchManager =(SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
           searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        } */
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
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
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }







    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;

        public CustomListener(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            Intent i = null;
            i = new Intent(MainActivity.this, Reminder.class);
            startActivity(i);
        }
    }
}