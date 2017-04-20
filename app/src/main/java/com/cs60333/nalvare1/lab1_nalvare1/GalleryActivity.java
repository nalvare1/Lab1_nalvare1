package com.cs60333.nalvare1.lab1_nalvare1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by apple on 3/27/17.
 */

public class GalleryActivity extends Activity {
    static final int CAMERA_REQUEST = 1;
    DatabaseHelper dbHelper;
    int team_id;
    GridView gridview;
    String timeStamp;
    FloatingActionButton floating_gallery_button;

    @Override
    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_gallery);

        System.out.println("populated");
        dbHelper = new DatabaseHelper(getApplicationContext());
        Intent intent = getIntent();
        team_id = intent.getExtras().getInt("id");

        gridview = (GridView) findViewById(R.id.gridview);
        populateGridView();

        System.out.println("populated");

        floating_gallery_button = (FloatingActionButton) findViewById(R.id.floating_gallery_action_button);

        floating_gallery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("cameraClicked");
                cameraClick(v);
                System.out.println("post cameraClicked");

            }
        });
    }

    public void cameraClick(View v) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            System.out.println("click1");
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
            System.out.println("click2");
        }
        /* before lab 7:
          File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
          String pictureName = getPictureName();
          File imageFile = new File(pictureDirectory, pictureName);
          Uri pictureUri = Uri.fromFile(imageFile);
          cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
          startActivityForResult(cameraIntent, CAMERA_REQUEST);
        */
    }
      //before lab 7:
      //  cameraButton.setOnClickListener(cameraButtonClicked);

    private void setTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        timeStamp = sdf.format(new Date());
    }

    private String getPictureName() {
        String imageName = "TeamImages" + timeStamp + ".jpg";
        return imageName;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("HELLO");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {

                System.out.println("HELLO");
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                String iDate = sdf.format(new Date()).toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.TEAM_ID, team_id);
                contentValues.put(DatabaseHelper.IMAGE, byteArray);
                contentValues.put(DatabaseHelper.COL_DATE, iDate);
                //contentValues.put(DatabaseHelper.COL_URI, imageUri.toString());
                dbHelper.insertData_images(DatabaseHelper.IMAGES_TABLE_NAME, contentValues);
                System.out.println("image saved");
                populateGridView();
            }
        }
    }
    private void populateGridView() {
        System.out.println("HELLO 11111");
        String[] fields = new String[]{DatabaseHelper.COL_IMAGE_ID, DatabaseHelper.IMAGE, DatabaseHelper.COL_DATE};
        String where = " team_id = ?";
        String[] args = new String[]{Integer.toString(team_id)};
        int[] items = new int[] {R.id.col_id, R.id.book_image, R.id.date_tv};
        System.out.println("HELLO 22");


        Cursor cursor = dbHelper.getSelectEntries(DatabaseHelper.IMAGES_TABLE_NAME, fields, where, args, DatabaseHelper.COL_IMAGE_ID + " DESC");
        SimpleCursorAdapter galleryCursorAdapter = new SimpleCursorAdapter (this, R.layout.image_layout, cursor, fields, items, 0);

        galleryCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue (View view, Cursor cursor, int columnIndex){
                if (view.getId() == R.id.book_image) {
                    ImageView imageView=(ImageView) view;
                    byte[] imageArray = cursor.getBlob(1);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageArray,0,imageArray.length);
                    imageView.setImageBitmap(bitmap);
                    return true;
                }
                return false;
            }});

        System.out.println("HELLO 33");
        gridview.setAdapter(galleryCursorAdapter);
    }
}
