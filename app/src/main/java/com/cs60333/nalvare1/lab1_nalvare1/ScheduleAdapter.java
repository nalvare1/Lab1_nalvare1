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

public class ScheduleAdapter extends ArrayAdapter<Team> { //ArrayAdapter<String[]>
    ScheduleAdapter (Context context, ArrayList<Team> schedule) {
        super(context, R.layout.schedule_item, schedule);
    }

    public String getPackageName() {
        return "com.cs60333.nalvare1.lab1_nalvare1";
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(R.layout.schedule_item, parent, false);

        Team matchItem = getItem(position);

        ImageView teamLogo = (ImageView) scheduleView.findViewById(R.id.teamLogo);
        String mDrawableName = matchItem.getImageName();
        int resID = getContext().getResources().getIdentifier(mDrawableName, "drawable", getContext().getPackageName());
        teamLogo.setImageResource(resID);

        TextView teamName = (TextView) scheduleView.findViewById(R.id.scheduleText);
        teamName.setText(matchItem.getTeamName());

        TextView gameDate = (TextView) scheduleView.findViewById(R.id.gameText);
        gameDate.setText(matchItem.getDateAbbrev());

       // ImageView photo_from_camera = (ImageView) scheduleView.findViewById(R.id.photo_taken);
       // int resID2 = getContext().getResources().getIdentifier("natalie_football", "drawable", getContext().getPackageName());
       // photo_from_camera.setImageResource(resID2);

        return scheduleView;
    }

}
