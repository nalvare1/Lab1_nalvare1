package com.cs60333.nalvare1.lab1_nalvare1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by apple on 2/17/17.
 */

public class DetailActivity extends Activity {
    public static int CAMERA_REQUEST = 1;//in manifest need permission to use camera and storage external
    private static final String PHOTOS="photos";

    Team stringInfo;
    @Override
    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);


        ImageView opposingTeamImage = (ImageView) findViewById(R.id.opposingTeamImage);
        ImageView notreDameImage = (ImageView) findViewById(R.id.notreDameImage);
        //**set image!!opposingTeamImage.set

        TextView dateText = (TextView) findViewById(R.id.ndText);
        TextView scoreText2 = (TextView) findViewById(R.id.scoreText2);
        TextView opposingTeamText1 = (TextView) findViewById(R.id.opposingTeamText1);
        TextView opposingTeamText2 = (TextView) findViewById(R.id.opposingTeamText2);
        TextView opposingTeamText3 = (TextView) findViewById(R.id.opposingTeamText3);

        stringInfo = (Team) getIntent().getSerializableExtra("team");


        dateText.setText(stringInfo.getDateAndTime());

        opposingTeamText1.setText(stringInfo.getTeamName());
        opposingTeamText2.setText(stringInfo.getTeamMascot());
        opposingTeamText3.setText(stringInfo.getTeamRecord());

        scoreText2.setText(stringInfo.getGameScore());

        String mDrawableName = stringInfo.getImageName();
        int resID2 = getApplicationContext().getResources().getIdentifier(mDrawableName, "drawable", getApplicationContext().getPackageName());
        opposingTeamImage.setImageResource(resID2);

        int resID3 = getApplicationContext().getResources().getIdentifier("fightingirishicon", "drawable", getApplicationContext().getPackageName());
        notreDameImage.setImageResource(resID3);

        Button galleryButton = (Button) findViewById(R.id.gallery_button);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(DetailActivity.this, GalleryActivity.class);
                galleryIntent.putExtra("id", Integer.parseInt(stringInfo.getTeamID()));
                startActivity(galleryIntent);

            }
        });
    }
}
