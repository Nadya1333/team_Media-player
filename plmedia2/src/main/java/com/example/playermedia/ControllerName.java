package com.example.playermedia;


import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;

import javafx.beans.InvalidationListener;


public class ControllerName implements Initializable{
    @FXML
    private ResourceBundle resourcs;

    @FXML
    private Slider songSlider;

    @FXML
    private Slider volumeSlider;

    //14 июня. Список треков. ДФ
    @FXML
    private ListView<String> playlistView;


    @FXML
    private URL location;

    @FXML
    private ImageView heart;

    //14 июня. Добавление треков. ДФ
    @FXML
    private ImageView addSongs;

    @FXML
    private ImageView next;

    @FXML
    private ImageView pause;

    @FXML
    private ImageView play;

    @FXML
    private ImageView prev;

    @FXML
    private ImageView repeat;

    @FXML
    private ImageView shuffle;


    /*public ControllerName() {
        mediaPlay();

    }*/
   /* @FXML
    private void mediaPlay(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select mp3", "*.mp3");
        fileChooser.getExtensionFilters().add(filter);
        String filePath = fileChooser.showOpenDialog(null).toURI().toString();
        File f = new File(filePath);
        Media media = new Media(filePath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        //labelVolume.setText("10%");
        //volumeSlider.setValue(10.0);
        mediaPlayer.setVolume(10.0 * 0.01);
        beginTimer();


        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
                songSlider.setValue(newValue.toSeconds());
            }
        });



    }*/
    @FXML
    private boolean isPlaying;
    private Timer timer;
    private TimerTask task;
    private Media med;
    private MediaPlayer mediaPlayer;
    @FXML private Label songLabel;
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private String currentTrack;




    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        songs = new ArrayList<File>();
        directory = new File("music");
        files = directory.listFiles();
        if(files !=null){
            for (File file: files){
                songs.add(file);
            }
        }
        med = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(med);
        songLabel.setText(songs.get(songNumber).getName());

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
                songSlider.setValue(newValue.toSeconds());
            }
        });
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    mediaPlayer.setVolume(volumeSlider.getValue()*0.01);
            }
        });
        //14 июня. Список треков. ДФ
        playlistView();


    }

    /*
    @FXML
    void onButtonClickOpenList(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("list.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("List");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window");

        }
    }


   @FXML
    void initialize() {
        menu.setOnMouseClicked(MouseEvent -> {
                    onButtonClickOpenList(MouseEvent);
                }
        );
    }
    */


    @FXML
    void play(MouseEvent event){
        beginTimer();
        play();
    }
    @FXML
    void pause(MouseEvent event){
        cancelTimer();
        pause();
    }

    @FXML
    void addSongs(MouseEvent event) throws IOException {
        addSongs();
    }

    @FXML
    void volume(MouseEvent event){
        volume();
    }

    @FXML
    void restart(MouseEvent event){
        restart();
    }

    @FXML
    void nextMedia(MouseEvent event) {
        nextMedia();
    }

    @FXML
    void previousMedia(MouseEvent event){
        previousMedia();
    }


    private void play() {
        System.out.println("Воспроизведение");
        beginTimer();
        mediaPlayer.play();
        isPlaying = true;
    }

    private void pause() {
        System.out.println("Пауза");
        mediaPlayer.pause();
        cancelTimer();
        isPlaying = false;
    }
    @FXML
    private void volume() {
        volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            }
        });
    }

    @FXML
    private void restart() {
        mediaPlayer.seek(Duration.millis(0));
        mediaPlayer.pause();
        mediaPlayer.play();
    }
    @FXML
    private void nextMedia(){
        if (songNumber < songs.size()-1){
            songNumber++;
            mediaPlayer.stop();

            med = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(med);

            songLabel.setText(songs.get(songNumber).getName());

            playlistView.getSelectionModel().select(songs.get(songNumber).getName());

            play();
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
                    songSlider.setValue(newValue.toSeconds());
                }
            });

        }
        else{
            songNumber = 0;
            mediaPlayer.stop();

            med = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(med);

            songLabel.setText(songs.get(songNumber).getName());
            playlistView.getSelectionModel().select(songs.get(songNumber).getName());
            play();
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
                    songSlider.setValue(newValue.toSeconds());
                }
            });
        }
    }

    @FXML
    private void previousMedia(){
        if (songNumber >0 ){
            songNumber--;
            mediaPlayer.stop();

            med = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(med);

            songLabel.setText(songs.get(songNumber).getName());

            playlistView.getSelectionModel().select(songs.get(songNumber).getName());

            play();
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
                    songSlider.setValue(newValue.toSeconds());
                }
            });
        }
        else{
            songNumber = songs.size() - 1;
            mediaPlayer.stop();

            med = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(med);

            songLabel.setText(songs.get(songNumber).getName());

            playlistView.getSelectionModel().select(songs.get(songNumber).getName());

        }
    }

    //14 июня. Список треков. ДФ
    private void playlistView(){
        String[] playlist  = new String[songs.size()];
        for (int u = 0; u < songs.size();u++) {
            playlist[u]= songs.get(u).getName();
        }
        playlistView.getItems().addAll(playlist);
        playlistView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentTrack = playlistView.getSelectionModel().getSelectedItem();
                songLabel.setText(currentTrack);
                songNumber = Arrays.asList(playlist).indexOf(songLabel.getText());
                mediaPlayer.stop();

                med = new Media(songs.get(songNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(med);

                songLabel.setText(songs.get(songNumber).getName());

                play();
                mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
                        songSlider.setValue(newValue.toSeconds());
                    }
                });
            }
        });
    }

    //14 июня. Добавление треков. ДФ
    private void addSongs() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select mp3", "*.mp3");
        fileChooser.getExtensionFilters().add(filter);
        URI filePath = fileChooser.showOpenDialog(null).toURI();
        Path frompath = (Path)Paths.get(filePath);
        File f = new File(filePath.toString());
        Path inpath = (Path)Paths.get("../plmedia2/music/" + f.getName());
        Files.copy(frompath,inpath);
        URL arg0 = null;
        ResourceBundle arg1 = null;
        //удалить элементы лист вью
        playlistView.getItems().clear();
        initialize(arg0, arg1);

    }

    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                isPlaying = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = med.getDuration().toSeconds();
                songSlider.setValue(current / end);

                if (current / end == 1) {
                    cancelTimer();
                }
            }
        };
    }
    public void cancelTimer() {
        isPlaying = false;
        timer.cancel();
    }

}

