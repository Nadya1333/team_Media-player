package com.example.playermedia;
import javafx.beans.property.*;

public class listkassette {

        private SimpleIntegerProperty num;
        private SimpleStringProperty track;
        private SimpleIntegerProperty time;
        private SimpleStringProperty author;
        private SimpleStringProperty album;

        listkassette(int num, String track, int time, String author, String album){
            this.num = new SimpleIntegerProperty(num);
            this.track = new SimpleStringProperty(track);
            this.time = new SimpleIntegerProperty(time);
            this.author = new SimpleStringProperty(author);
            this.album = new SimpleStringProperty(album);
        }

        public int getNum(){ return num.get();}
        public void setNum(int value){ num.set(value);}

        public String getTrack(){ return track.get();}
        public void setTrack(String value){ track.set(value);}

        public int getTime(){ return time.get();}
        public void setTime(int value){ time.set(value);}

        public String getAuthor(){ return author.get();}
        public void setAuthor(String value){ author.set(value);}

        public String getAlbum(){ return album.get();}
        public void setAlbum(String value){ album.set(value);}
    }

