package com.cs60333.nalvare1.lab1_nalvare1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by apple on 4/18/17.
 */

public class ImageActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent i = getIntent();
        int position = i.getExtras().getInt("id");
        ImageView imageView = (ImageView) findViewById(R.id.image_view);
    }

}
