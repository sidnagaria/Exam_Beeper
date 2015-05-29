package com.exam_beeper.exam_beeper;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Exams_List extends ActionBarActivity {

    private ListView lv;
    private ArrayAdapter<String> eadapter;
    SearchView se, searchView;
    MenuItem searchItem;
    //SearchAlgo sa;
    MyDBHandler dbhandler;
    String s[]=new String[100];
    String a[]=new String[100];
    int examcount = 0;
    int z;
    String d[] = new String[100];


    ListView list;
    // private ArrayAdapter<String> listadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams__list);
        dbhandler = new MyDBHandler(Exams_List.this, null, null, 1);
        s=dbhandler.SearchExam();
        d=dbhandler.SearchDate();
        examcount=dbhandler.ExamCount();
        String[] str={"Hello", "Bte","Bye"};
        printexamlist(s,d,examcount);
     /*  list=(ListView) findViewById(R.id.exams_listview);
        ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);

        list.setAdapter(listadapter);
        System.out.println("Print");
       list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // String data = String.valueOf(parent.getItemAtPosition(position));

                        //  Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();

                        AlertDialog.Builder adb = new AlertDialog.Builder(Exams_List.this);

                        //  adb.setMessage(" selected Item is="+parent.getItemAtPosition(position));
                        // adb.setPositiveButton("Ok", null);
                        // adb.setTitle((int) parent.getItemIdAtPosition(position));
                      //  if(position==0)
                            adb.setTitle(s[position]);
                        adb.setMessage("SET REMINDER");
                        adb.setPositiveButton("Yes", null);
                        adb.setNegativeButton("No", null);
                        //adb.setNeutralButton("Cancel", null);
                        adb.show();

                    }
                }
        );
*/








        String[] drawer = {"Home", "My Account", "Create Custom List"};
        lv = (ListView) findViewById(R.id.left_drawer_exams);
        eadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawer);
        lv.setAdapter(eadapter);
        //  sa=new SearchAlgo();

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // String data = String.valueOf(parent.getItemAtPosition(position));

                        //  Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();

                        Intent i = null;
                        if (position == 0)
                            i = new Intent(Exams_List.this, MainActivity.class);

                        if (position == 1)
                            i = new Intent(Exams_List.this, My_Account.class);


                        if (position == 2)
                            i = new Intent(Exams_List.this, Custom_List.class);


                        startActivity(i);
                    }
                }
        );
    }

    public static String lcs(String a, String b) {
        int[][] lengths = new int[a.length()+1][b.length()+1];

        // row 0 and column 0 are initialized to 0 already

        for (int i = 0; i < a.length(); i++)
            for (int j = 0; j < b.length(); j++)
                if (a.charAt(i) == b.charAt(j))
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                else
                    lengths[i+1][j+1] =
                            Math.max(lengths[i+1][j], lengths[i][j+1]);

        // read the substring out from the matrix
        StringBuffer sb = new StringBuffer();
        for (int x = a.length(), y = b.length();
             x != 0 && y != 0; ) {
            if (lengths[x][y] == lengths[x-1][y])
                x--;
            else if (lengths[x][y] == lengths[x][y-1])
                y--;
            else {
                assert a.charAt(x-1) == b.charAt(y-1);
                sb.append(a.charAt(x-1));
                x--;
                y--;
            }
        }

        return sb.reverse().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_exams__list, menu);
        //  inflater.inflate(R.menu.menu_exams__list, menu);
        inflater.inflate(R.menu.menu_action_search, menu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {


            searchItem = menu.findItem(R.id.action_search);
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            // searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    String si = "";
                    try {





                        s = dbhandler.SearchExam();
                        examcount = dbhandler.ExamCount();



                        int i;
                        int q = 0;
                        for (i = 0; i < examcount; i++) {
                            // Log.e("Search", "Search");
                            String result;
                            int x;
                            int y;

                            x=query.length();

                            result=lcs(query,s[i]);
                            //result = query.compareToIgnoreCase(s[i]);
                            y=result.length();
                            if(x == y)
                            {
                                // System.out.println(s[i]);
                                q++;
                                a[q-1]=s[i];
                            }
                            //  for(i=0;i<q;i++)
                            //    System.out.println(s[i]);
                            printexamlist(a,d,q);



                            //if (result == 0) {
                            //  Log.e("Equal", "Equal");
                            //si = query;
                            //break;
                            // } else
                            //    Log.e("Not_Equal", "NE");
                            //System.out.println();
                        }

                    } catch (SQLiteException e) {

                    }


                    //  Toast.makeText(Exams_List.this, si, Toast.LENGTH_SHORT).show();

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    return true;
                }
            });

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, About.class));
            return true;
        }


        //noinspection SimplifiableIfStatement

        if(id==R.id.action_sort) {
            View menuItemView = findViewById(R.id.action_sort);
            PopupMenu popupMenu = new PopupMenu(this, menuItemView);
            popupMenu.inflate(R.menu.popup);
            popupMenu.show();
            // Log.e("nosort","nosort");

            popupMenu.setOnMenuItemClickListener(
                    new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId()) {
                                case R.id.item1:
                                    Log.e("item1","item1");
                                    try {

                                        s = dbhandler.SearchExam();
                                        d = dbhandler.SearchDate();
                                        examcount = dbhandler.ExamCount();
                                        int i,j;
                                        String temp, temp2;
                                        try {
                                            for (i = 1; i < examcount; i++) {
                                                //System.out.println("SORT");
                                                j = i;
                                                while (j > 0 && s[j].compareToIgnoreCase(s[j-1])<0) {


                                                    temp = s[j];
                                                    temp2=d[j];
                                                    s[j] = s[j - 1];
                                                    d[j]=d[j-1];
                                                    s[j - 1] = temp;
                                                    d[j-1]=temp2;
                                                    j--;
                                                }
                                            }
                                        }
                                        catch(ArrayIndexOutOfBoundsException e)
                                        {

                                        }
                                        for(i=0;i<examcount;i++)
                                            System.out.println(s[i]);
                                        printexamlist(s,d,examcount);

                                    } catch (SQLiteException e) {

                                    }

                                    break;
                                case R.id.item2:
                                    Log.e("item2","item2");
                                    try {
                                        s=dbhandler.SearchExam();
                                        d = dbhandler.SearchDate();
                                        examcount = dbhandler.ExamCount();
                                        int i,j;
                                        String temp, temp2;
                                        try {
                                            for (i = 1; i < examcount; i++) {
                                                //System.out.println("SORT");
                                                j = i;
                                                while (j > 0 && d[j].compareToIgnoreCase(d[j-1])<0) {
                                                    temp = d[j];
                                                    temp2=s[j];
                                                    d[j] = d[j - 1];
                                                    s[j]=s[j-1];
                                                    d[j - 1] = temp;
                                                    s[j-1]=temp2;
                                                    j--;
                                                }
                                            }
                                        }
                                        catch(ArrayIndexOutOfBoundsException e)
                                        {

                                        }
                                        for(i=0;i<examcount;i++)
                                            System.out.println(d[i]);
                                        printexamlist(s,d,examcount);

                                    } catch (SQLiteException e) {

                                    }




                                    break;

                            }
                            return false;
                        }
                    }


            );
        }

        else if (id == R.id.action_search) {

            return true;
        }
        return super.onOptionsItemSelected(item);


    }
    public void printexamlist(final String[] s,final String[] d, final int ecount) {

        list = (ListView) findViewById(R.id.exams_listview);
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();


        for (int i = 0; i < ecount; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("e_txt", s[i]);
            hm.put("e_date", "GET READY ON:\t" + d[i]);
            aList.add(hm);
        }

        String[] from = {"e_txt", "e_date"};
        int[] to = {R.id.e_txt, R.id.e_date};

        final SimpleAdapter adapter = new SimpleAdapter(this, aList, R.layout.exam_listview_layout, from, to);


        //final ArrayAdapter<String> listadapter= new ArrayAdapter<String>(Exams_List.this,android.R.layout.simple_list_item_1,s);


        list.setAdapter(adapter);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // String data = String.valueOf(parent.getItemAtPosition(position));

                        //  Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();

                        AlertDialog.Builder adb = new AlertDialog.Builder(Exams_List.this);

                        //  adb.setMessage(" selected Item is="+parent.getItemAtPosition(position));
                        // adb.setPositiveButton("Ok", null);
                        // adb.setTitle((int) parent.getItemIdAtPosition(position));
                        String[] se = dbhandler.SearchCategory();
                        for (z = 0; z < ecount; z++) {
                            if (position == z)

                                adb.setTitle(se[z]);

                        }

                        adb.setMessage("SET REMINDER");
                        adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                        adb.setNegativeButton("No", null);
                        AlertDialog alertDialog = adb.create();
                        alertDialog.show();
                        //adb.setNeutralButton("Cancel", null);
                        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        theButton.setOnClickListener(new CustomListener(alertDialog));

                    }
                }
        );

    }




    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;

        public CustomListener(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            Intent i = null;
            i = new Intent(Exams_List.this, Reminder.class);
            startActivity(i);
        }
    }

}
