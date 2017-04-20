package com.cs60333.nalvare1.lab1_nalvare1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by apple on 3/24/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "books.db";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_NAME = "Teams";
    public static String IMAGES_TABLE_NAME = "Images";
    public static String TEAM_NAME = "team_name";
    public static String COL_ID = "_id";
    public static String COL_IMAGE_ID = "_id";
    public static String IMAGE_NAME = "image_name";
    public static String DATE_ABBREV = "date_abbrev";
    public static String DATE_AND_TIME = "date_and_time";
    public static String TEAM_MASCOT = "team_mascot";
    public static String TEAM_RECORD = "team_record";
    public static String GAME_SCORE = "game_score";
    public static String TEAM_ID = "team_id";
    public static String IMAGE = "image";
    public static String COL_DATE = "date";
    public static String COL_URI = "uri";
    public SQLiteDatabase db;

    // constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + IMAGE_NAME + " Text, "
                + TEAM_NAME + " Text, "
                + DATE_ABBREV + " Text, "
                + DATE_AND_TIME + " Text, "
                + TEAM_MASCOT + " Text, "
                + TEAM_RECORD + " Text, "
                + GAME_SCORE + " Text, "
                + TEAM_ID + " Text "
                + ")");
        db.execSQL("CREATE TABLE " + IMAGES_TABLE_NAME + " ( " + COL_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TEAM_ID + " INTEGER, "
                + IMAGE + " BLOB, " +
                COL_DATE + " TEXT, "
                + COL_URI + " TEXT )");
        //create another table here for the images!!
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE if exists "  +  TABLE_NAME );
        db.execSQL(" DROP TABLE if exists "  +  IMAGES_TABLE_NAME );
        onCreate(db);
    }

    public void insertData(Team team) {
        db = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();

        contentvalues.put(IMAGE_NAME, team.getImageName());
        contentvalues.put(TEAM_NAME, team.getTeamName());
        contentvalues.put(DATE_ABBREV, team.getDateAbbrev());
        contentvalues.put(DATE_AND_TIME, team.getDateAndTime());
        contentvalues.put(TEAM_MASCOT, team.getTeamMascot());
        contentvalues.put(TEAM_RECORD, team.getTeamRecord());
        contentvalues.put(GAME_SCORE, team.getGameScore());
        contentvalues.put(TEAM_ID, team.getTeamID());

        long ret = db.insert(TABLE_NAME, null, contentvalues );

        if (ret > -1) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Unsuccessful insert");
        }

        db.close();
    }
    /*
    public void insertData_images(Image image) {

        db = getWritableDatabase();
        ContentValues contentValues2 = new ContentValues();

        contentValues2.put(TEAM_ID, image.get_image_id());
        //contentValues2.put(COL_IMAGE_ID, image.get_image_id());
        contentValues2.put(COL_URI, image.get_uri());
        contentValues2.put(IMAGE, image.get_image());
        contentValues2.put(COL_DATE, image.getDate());


        long ret = db.insert(IMAGES_TABLE_NAME, null, contentValues2 );

        if (ret > 0) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Insert Unsuccessful");
        }

        db.close();
    }
    */
    public void insertData_images(String tblName, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();

        long ret = db.insert(tblName, null, contentValues );

        if (ret > 0) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Insert Unsuccessful");
        }

        db.close();
    }
    public void deleteData(int team_id) { //deletes one entry
        db = getWritableDatabase();
        db.delete(TABLE_NAME, " _id = ?", new String[]{Integer.toString(team_id)});
        db.close();
    }
    public void deleteData_images(int image_id) { //deletes one entry
        db = getWritableDatabase();
        db.delete(IMAGES_TABLE_NAME, " _id = ?", new String[]{Integer.toString(image_id)});
        db.close();
    }

    public String getString() {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, new String[]{});
        StringBuilder stringbuilder = new StringBuilder();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    stringbuilder.append(cursor.getString(cursor.getColumnIndex(TEAM_NAME)));
                    stringbuilder.append("\n");
                }while (cursor.moveToNext());
            }
        }

        cursor.close();
        return stringbuilder.toString();
    }

    public void readData(Context context) {
        MyCsvFileReader reader = new MyCsvFileReader(context);//is normally "getApplicationContext()" when in MainActivity
        ArrayList<String[]> readerArrayList = reader.readCsvFile(R.raw.schedule); //returns an array list of strings??

        //made previous team data into a loop
        //ArrayList<Team> teams = new ArrayList<>();
        for(int i = 0; i < readerArrayList.size(); i++) {
           // Team x = new Team(readerArrayList.get(i));
            insertData(new Team(readerArrayList.get(i)));
            //teams.add(x);
        }


    }

    public ArrayList<Team> returnData() {
        ArrayList<Team> teams = new ArrayList<>();

        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    String[] team_array = new String[]{cursor.getString(cursor.getColumnIndex(IMAGE_NAME)),
                            cursor.getString(cursor.getColumnIndex(TEAM_NAME)),
                            cursor.getString(cursor.getColumnIndex(DATE_ABBREV)),
                            cursor.getString(cursor.getColumnIndex(DATE_AND_TIME)),
                            cursor.getString(cursor.getColumnIndex(TEAM_MASCOT)),
                            cursor.getString(cursor.getColumnIndex(TEAM_RECORD)),
                            cursor.getString(cursor.getColumnIndex(GAME_SCORE)),
                            cursor.getString(cursor.getColumnIndex(TEAM_ID))};
                    teams.add(new Team(team_array));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return teams;
    }

    public Cursor getSelectEntries(String tblName, String[] columns, String where, String[] args, String orderBy) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblName, columns, where, args, null, null, orderBy);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
