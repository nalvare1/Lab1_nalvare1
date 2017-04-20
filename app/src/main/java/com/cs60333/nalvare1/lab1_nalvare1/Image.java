package com.cs60333.nalvare1.lab1_nalvare1;

/**
 * Created by apple on 4/18/17.
 */

public class Image {
    private int _id;
    private int team_id;
    private String uri;
    private byte[] image;
    private String date;

    public Image(int _id, int team_id,  String uri, byte[] image, String date) {
        this._id = _id;
        this.team_id = team_id;
        this.uri = uri;
        this.image = image;
        this.date = date;
    }

    public int get_id() { return _id; }
    public void set_id(int _id) { this._id = _id; }

    public int get_image_id() {
        return team_id;
    }
    public void set_image_id(int _id) {
        this.team_id = team_id;
    }

    public String get_uri() {
        return uri;
    }
    public void set_uri(String uri) {
        this.uri = uri;
    }

    public byte[] get_image() {
        return image;
    }
    public void set_image(byte[] image) {
        this.image = image;
    }

    public String getDate() { return date; }
    public void set_date(String date) { this.date = date; }
}
