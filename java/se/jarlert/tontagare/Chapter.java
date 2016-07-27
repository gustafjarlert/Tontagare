package se.jarlert.tontagare;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Gustaf on 2016-07-21.
 */
public class Chapter {

    private String name;
    private ArrayList<Song> songs;

    public Chapter(String chapterName, ArrayList<Song> songs) {
        name = chapterName;
        this.songs = (ArrayList<Song>) songs.clone();
    }

    public String getName() {
        return name;
    }

    public ListIterator<Song> getSongListIterator() {
        return songs.listIterator();
    }

    public Song getSong(int index) {
        if (index > -1 && index < songs.size())
            return songs.get(index);
        else
            return null;

    }

    public int getSize() {
        return songs.size();
    }
}
