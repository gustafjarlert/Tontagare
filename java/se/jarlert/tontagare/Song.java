package se.jarlert.tontagare;


/**
 * Created by Gustaf on 2016-04-19.
 */
public class Song {

    private int pageNr;
    private String[] chord;
    private String key;
    private String title;

    public Song(int pageNr, String chordCode, String key, String title) {
        this.pageNr = pageNr;
        this.key = key;
        this.title = title;

        this.chord = chordCode.split("<");

    }

    public int getPageNr() {
        return pageNr;
    }

    public String[] getChord() {
        return chord;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }
}
