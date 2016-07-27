package se.jarlert.tontagare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

/**
 * Created by Gustaf on 2016-07-21.
 */
public class SongBook {
    private int pageNr;
    private String name;
    private ArrayList<Chapter> chapters;
    private HashMap<Integer,Song> songHashMap;

    public SongBook(ArrayList<Chapter> chapters, HashMap<Integer,Song> hashMap) {
        this.chapters = chapters;
        songHashMap = hashMap;
    }

    public ListIterator<Chapter> getChapterListIterator() {
        return chapters.listIterator();
    }

    public Chapter getChapter(int index) {
        if (index > -1 && index < chapters.size())
            return chapters.get(index);
        else
            return null;
    }

    public Song getSongOnPage(int nr) {
        if(songHashMap.containsKey(nr)) {
            return songHashMap.get(nr);
        } else
            return null;
    }

}
