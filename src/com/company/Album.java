package com.company;

import java.util.ArrayList;

public class Album {
    private String name;
    private ArrayList<Song> songs;

    public Album(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getAlbumName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void addSongs(String nameSong, double duration){
        if(songExists(nameSong) < 0 ) {

            songs.add(new Song(nameSong,duration));
            System.out.println(nameSong + " was added to the album.");
        }
        else{
            System.out.println(nameSong + " already exists.");
        }
    }

    public int checkSong(String name){
        int index = songExists(name);
        return index;
    }

    private int songExists(String songName){
        int index = -1;
        for(int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getTitle().equals(songName)) {
                index = i;
                break;
            }
        }
        if(index >= 0 )
            return index;

        return -1;
    }

}




