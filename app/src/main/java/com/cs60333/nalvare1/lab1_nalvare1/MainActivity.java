package com.cs60333.nalvare1.lab1_nalvare1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String[] teams = {"Ohio State", "Florida State", "Wake Forest", "Boston College", "North Carolina State", "Georgia Tech", "North Virginia", "Chicago State"};
        String[] wake = {"wf", "Wake Forest", "Feb 2"};
        String[] ohio = {"os", "Ohio State", "Feb 4"};
        String[] florida = {"fsu", "Florida State", "Feb 11"};
        String[] boston = {"bc", "Boston College", "Feb 14"};
        String[] northC = {"ncs", "North Carolina State", "Feb 18"};
        String[] georgia = {"gt", "Georgia Tech", "Feb 26"};
        String[] northV = {"nv", "North Virginia", "March 1"};
        String[] chicago = {"cs", "Chicago State", "March 4"};

        //create a list of arrays
        ArrayList<String[]> arr = new ArrayList<String[]>();
        arr.add(wake);
        arr.add(ohio);
        arr.add(florida);
        arr.add(boston);
        arr.add(northC);
        arr.add(georgia);
        arr.add(northV);
        arr.add(chicago);


        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getApplicationContext(), arr);

        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);



    }
}
