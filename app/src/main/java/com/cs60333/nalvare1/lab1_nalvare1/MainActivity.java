package com.cs60333.nalvare1.lab1_nalvare1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String[] teams = {"Ohio State", "Florida State", "Wake Forest", "Boston College", "North Carolina State", "Georgia Tech", "North Virginia", "Chicago State"};
        String[] wake = {"wf", "Wake Forest", "Feb 2", "Thursday, February 2, 6:00 PM", "Demon Deacon", "(15-9)", "60-92"};
        String[] ohio = {"os", "Ohio State", "Feb 4", "Saturday, February 4, 6:00 PM", "Brutus Buckeye", "(22-7)", "74-82"};
        String[] florida = {"fsu", "Florida State", "Feb 11", "Saturday, February 11, 6:00 PM", "Seminoles", "(21-5)", "72-84"};
        String[] boston = {"bc", "Boston College", "Feb 14", "Tuesday, February 14, 6:00 PM", "Golden Eagles", "(24-6)", "80-83"};
        String[] northC = {"ncs", "North Carolina State", "Feb 18", "Saturday, February 18, 6:00 PM", "Wolfpack", "(16-10)", "54-97"};
        String[] georgia = {"gt", "Georgia Tech", "Feb 26", "Sunday, February 26, 6:00 PM", "Ramblin' Wreck", "(24-8)", "78-82"};
        String[] northV = {"nv", "North Virginia", "March 1", "Wednesday, March 1, 6:00 PM", "Vipers", "(14-12)", "24-84"};
        String[] chicago = {"cs", "Chicago State", "March 4", "Saturday, March 4, 6:00 PM", "Wildcats", "(16-14)", "40-76"};

        //create a list of arrays
        final ArrayList<String[]> arr = new ArrayList<String[]>();
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

        //AdapterView.OnItemClickListener clickListener = (parent, view, position, id) -> {
        OnItemClickListener clickListener = new OnItemClickListener() {
          //  @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myintent = new Intent(MainActivity.this, Detail.class);
                myintent.putExtra("team", arr.get(position));
                startActivity(myintent);
            }

        };
        //scheduleLisView or another listView???
        scheduleListView.setOnItemClickListener(clickListener);

    }
}