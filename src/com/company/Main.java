package com.company;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Album> albums = new ArrayList<>();
    private static List<Song> playlist = new ArrayList<>();

    public static void main(String[] args) {
        // Menu to create the albums first
        boolean flag = true;
        printMenu();
        while (flag) {
            System.out.println("Choose an option:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printMenu();
                    break;
                case 1:
                    addAlbum();
                    break;
                case 2:
                    addSongToAlbum();
                    break;
                case 3:
                    flag = false;
                    System.out.println("Exiting the menu!");
                    break;

            }
        }
        // Menu to create the playlist

        flag = true;
        printPlaylistMenu();
        while (flag) {
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printPlaylistMenu();
                    break;
                case 1:
                    addSongToPlaylist();
                    break;
                case 2:
                    flag = false;
                    System.out.println("Exiting the playlist menu!");
                    break;

            }
        }

        actionsInPlaylist(playlist);


    }

    public static void actionsInPlaylist(List<Song> playlist) {

        ListIterator<Song> listIterator = playlist.listIterator();
        if (listIterator.hasNext())
            System.out.println("The song currently playing is " + listIterator.next().getTitle());
        // TODO add else case when the playlist is empty

        boolean flag = true;
        printOptionsInThePLaylist();
        while (flag) {
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printOptionsInThePLaylist();
                    break;
                case 1:
                    //goToNextSong;
                    if (listIterator.hasNext())
                        System.out.println("Now playing " + listIterator.next().getTitle());
                    else {
                        System.out.println("Your reached end of playlist");
                    }
                    break;
                case 2:
                    // goToPreviousSong
                    if (listIterator.hasPrevious()) {
                        listIterator.previous();
                        if (listIterator.hasPrevious())
                            System.out.println("Now playing " + listIterator.previous().getTitle());
                        else {
                            System.out.println("You reached end of playlist.");
                        }
                    } else {
                        System.out.println("You reached end of playlist.");
                    }
                    break;
                case 3:
                    // replayCurrentSong
                    if (listIterator.hasPrevious())
                        System.out.println("You are listening to " + listIterator.previous().getTitle() + " again.");

                    break;
                case 4:
                    //listSongs
                    int i = 1;
                    for (Song song : playlist) {
                        System.out.println(i + ". " + song.getTitle());
                        i++;
                    }


                    break;
                case 5:
                    // removeSongFromPlaylist
                    listIterator.remove();

                    break;
                case 6:
                    //exitPlaylist
                    System.out.println("You are exiting now.");
                    flag = false;
                    break;

            }
        }
    }


    public static void printMenu() {
        System.out.println("Available actions:\n press ");
        System.out.println("0 - to print Menu\n" +
                "1 - to add album\n" +
                "2 - to add song to an album\n" +
                "3 - to exit menu.");
    }

    public static void printPlaylistMenu() {
        System.out.println("Available actions:\n press ");
        System.out.println("0 - to print Menu\n" +
                "1 - to add songs to playlist\n" +
                "2 - to exit menu\n"
        );
    }

    public static void printOptionsInThePLaylist() {
        System.out.println("Available actions:\n press ");
        System.out.println("0 - to print Menu\n" +
                "1 - go to next song\n" +
                "2 - go to previous song\n" +
                "3 - replay current Song.\n" +
                "4 - list songs\n" +
                "5 - remove song from playlist\n" +
                "6 - Quit\n");
    }


    public static void addSongToPlaylist() {
        System.out.println("From which album you want to add a song:");
        String albumName = scanner.nextLine();
        System.out.println("Which song you want to add to the playlist? ");
        String songName = scanner.nextLine();
        int index = albumExists(albumName);
        if (index >= 0) {
            int songIndex = albums.get(index).checkSong(songName);
            if (songIndex >= 0)
                playlist.add(albums.get(index).getSongs().get(songIndex));
            else {
                System.out.println(songName + " doesn't exist.");
            }
        } else {
            System.out.println(albumName + " doesn't exist.");
        }
    }


    public static void addAlbum() {
        System.out.println("Enter name of the album: ");
        String nameAlbum = scanner.nextLine();
        //Album album = new Album(nameAlbum);
        //This above is not correct like this i am creating new album but i am not adding it to the albums arraylist
        // if i do that code than i have to add: albums.add(album);
        albums.add(new Album(nameAlbum));
    }

    public static void addSongToAlbum() {
        System.out.println("Enter the album: ");
        String albumName = scanner.nextLine();
        System.out.println("Enter the song: ");
        String songName = scanner.nextLine();
        System.out.println("Enter the duration: ");
        Double songDuration = scanner.nextDouble();
        int index = albumExists(albumName);
        if (index >= 0) {
            albums.get(index).addSongs(songName, songDuration);

        } else {
            System.out.println(albumName + " album, is not in the list.");
        }
    }

    private static int albumExists(String albumName) {
        int index = -1;
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getAlbumName().equals(albumName)) {
                index = i;
                break;
            }
        }
        if (index >= 0)
            return index;

        return -1;
    }


}
