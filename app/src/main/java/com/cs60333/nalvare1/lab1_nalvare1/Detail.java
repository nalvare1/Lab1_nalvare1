package com.cs60333.nalvare1.lab1_nalvare1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by apple on 2/17/17.
 */

public class Detail extends Activity {
    public static int CAMERA_REQUEST = 1;//in manifest need permission to use camera and storage external
    private static final String PHOTOS="photos";
    @Override
    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        Button cameraButton = (Button) findViewById(R.id.cameraButton);
        ImageView opposingTeamImage = (ImageView) findViewById(R.id.opposingTeamImage);
        ImageView notreDameImage = (ImageView) findViewById(R.id.notreDameImage);
        //**set image!!opposingTeamImage.set

        TextView dateText = (TextView) findViewById(R.id.dateText);
        TextView scoreText2 = (TextView) findViewById(R.id.scoreText2);
        TextView opposingTeamText1 = (TextView) findViewById(R.id.opposingTeamText1);
        TextView opposingTeamText2 = (TextView) findViewById(R.id.opposingTeamText2);
        TextView opposingTeamText3 = (TextView) findViewById(R.id.opposingTeamText3);

        Team stringInfo = (Team) getIntent().getSerializableExtra("team");


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


        View.OnClickListener cameraButtonClicked = new View.OnClickListener() {
            public void onClick(View v) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureName = getPictureName();
                File imageFile = new File(pictureDirectory, pictureName);
                Uri pictureUri = Uri.fromFile(imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        };

        cameraButton.setOnClickListener(cameraButtonClicked);

    }

    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "BestMoments" + timestamp + ".jpg";
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                //Intent photoGalleryIntent = new Intent(Intent.ACTION_PICK);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                String pictureDirectoryPath = pictureDirectory.getPath();
                Uri imageUri = Uri.parse(pictureDirectoryPath);
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    ImageView imgView = (ImageView) findViewById(R.id.photo_taken);
                    imgView.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
