package com.cs60333.nalvare1.lab1_nalvare1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by apple on 2/8/17.
 */

public class ScheduleAdapter extends ArrayAdapter<String[]> {
    ScheduleAdapter (Context context, ArrayList<String[]> schedule) {
        super(context, R.layout.schedule_item, schedule);
    }

    public String getPackageName() {
        return "com.cs60333.nalvare1.lab1_nalvare1";
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(R.layout.schedule_item, parent, false);

        String matchItem[] = getItem(position);

        ImageView teamLogo = (ImageView) scheduleView.findViewById(R.id.teamLogo);
        String mDrawableName = matchItem[0];
        int resID = getContext().getResources().getIdentifier(mDrawableName, "drawable", getContext().getPackageName());
        teamLogo.setImageResource(resID);

        TextView teamName = (TextView) scheduleView.findViewById(R.id.scheduleText);
        teamName.setText(matchItem[1]);

        TextView gameDate = (TextView) scheduleView.findViewById(R.id.gameText);
        gameDate.setText(matchItem[2]);

        return scheduleView;
    }

}
