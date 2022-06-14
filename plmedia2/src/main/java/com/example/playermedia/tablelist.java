package com.example.playermedia;

import javafx.beans.property.SimpleStringProperty;

public class tablelist {

    private int num;
    private String track;
    private int time;
    private String author;
    private String album;

    public tablelist(int num, String track,int time, String author, String album){
        this.num = num;
        this.track = track;
        this.time = time;
        this.author = author;
        this.album = album;
    }

    public Integer getNum(){
        return num;
    }
    public String getTrack(){
        return track;
    }
    public Integer getTime(){
        return time;
    }
    public String getAuthor(){
        return author;
    }
    public String getAlbum(){
        return album;
    }


}
