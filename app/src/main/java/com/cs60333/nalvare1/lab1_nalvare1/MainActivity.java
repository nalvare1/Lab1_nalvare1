package com.cs60333.nalvare1.lab1_nalvare1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {//or extends Activity??
    ArrayList<Team> teams;
    ListView scheduleListView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyCsvFileReader reader = new MyCsvFileReader(getApplicationContext());
        ArrayList<String[]> readerArrayList = reader.readCsvFile(R.raw.schedule); //returns an array list of strings??
        Team wake = new Team(readerArrayList.get(0));
        Team ohio = new Team(readerArrayList.get(1));
        Team florida = new Team(readerArrayList.get(2));
        Team boston = new Team(readerArrayList.get(3));
        Team northC = new Team(readerArrayList.get(4));
        Team georgia = new Team(readerArrayList.get(5));
        Team northV = new Team(readerArrayList.get(6));
        Team chicago = new Team(readerArrayList.get(7));

        //create a list of arrays
        teams = new ArrayList<>();
        //final ArrayList<String[]> arr = new ArrayList<String[]>();
        teams.add(wake);
        teams.add(ohio);
        teams.add(florida);
        teams.add(boston);
        teams.add(northC);
        teams.add(georgia);
        teams.add(northV);
        teams.add(chicago);

        //Lab 6:
        // getSupportActionBar().hide(); //do NOT use this!!(causes errors!)
        Toolbar my_tool_bar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(my_tool_bar);
        my_tool_bar.setTitle("ND Athletics");


        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getApplicationContext(), teams);
        scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);
        //AdapterView.OnItemClickListener clickListener = (parent, view, position, id) -> {

        OnItemClickListener clickListener = new OnItemClickListener() {
            //  @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myintent = new Intent(MainActivity.this, Detail.class);
                myintent.putExtra("team", teams.get(position));
                System.out.println("" + teams.get(position).getTeamName());
                startActivity(myintent);

            }

        };
        //scheduleLisView or another listView???
        scheduleListView.setOnItemClickListener(clickListener);

    }

    //Lab 6: menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //Lab 6: share's gameSchedule() function
    public String gameSchedule() {
        StringBuilder teamStringBuilder = new StringBuilder(20);
        for(int i = 0; i < teams.size(); i++) {
          //  teamStringBuilder.append(teams.get(i).getTeamName());
            teamStringBuilder.append(teams.get(i).getTeamName());
            teamStringBuilder.append(teams.get(i).getDateAndTime());
            teamStringBuilder.append(" ");
        }

        String teamString = teamStringBuilder.toString();
        return teamString;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {
// code for sharing the schedule

            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BasketBall Matches");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, gameSchedule());
            startActivity(android.content.Intent.createChooser(shareIntent, "Share via"));

        } else if (res_id == R.id.sync) {
        // Snackbar with Try Again action

            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Sync is not yet implemented", Snackbar.LENGTH_LONG);
            snackbar.setAction("Try Again", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(coordinatorLayout, "Wait for the next few labs. Thank you for your patience", Snackbar.LENGTH_LONG).show();
                }

            });
            snackbar.show();
        } else if (res_id == R.id.settings) {
        // Floating Contextual Menu with options
            registerForContextMenu(scheduleListView);
            openContextMenu(scheduleListView);
            unregisterForContextMenu(scheduleListView);
        }
        return true;
    }

    //floating_contextual_menu: Lab 6
    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_contextual_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == R.id.women) {
        // to be implemented later
        }
        //and so on ...
        return false;
    }

}