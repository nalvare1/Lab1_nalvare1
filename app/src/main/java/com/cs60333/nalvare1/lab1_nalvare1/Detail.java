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

public class Detail extends Activity {
    @Override
    public void onCreate (Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        Button cameraButton = (Button) findViewById(R.id.cameraButton);
        ImageView opposingTeamImage = (ImageView) findViewById(R.id.opposingTeamImage);
        //**set image!!opposingTeamImage.set

        TextView dateText = (TextView) findViewById(R.id.dateText);
        TextView scoreText2 = (TextView) findViewById(R.id.scoreText2);
        TextView opposingTeamText1 = (TextView) findViewById(R.id.opposingTeamText1);
        TextView opposingTeamText2 = (TextView) findViewById(R.id.opposingTeamText2);
        TextView opposingTeamText3 = (TextView) findViewById(R.id.opposingTeamText3);


    //    View.OnClickListener cameraButtonClicked = (v) -> {
    //        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    //        startActivity(cameraIntent);
     //   };
     //   cameraButton.setOnClickListener(cameraButtonClicked);

        String[] stringInfo = getIntent().getStringArrayExtra("team");


//        dateText.setText(stringInfo[3]);

  //      opposingTeamText1.setText(stringInfo[1]);
    //    opposingTeamText2.setText(stringInfo[4]);
      //  opposingTeamText3.setText(stringInfo[5]);

      //  scoreText2.setText(stringInfo[6]);

       // String mDrawableName = stringInfo[0];
       // int resID = getContext().getResources().getIdentifier(mDrawableName, "drawable", getContext().getPackageName());
       // teamLogo.setImageResource(resID);

    }
}
