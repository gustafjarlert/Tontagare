package se.jarlert.tontagare;

import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gustaf on 2016-04-19.
 */
public class BookReader {

    public static SongBook createSongBook(Resources res) {
        String line = "";
        String tempLine = "";
        try
        {
            InputStream instream = res.openRawResource(R.raw.utantill);
            if (instream != null)
            {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                int currLine = 1;
                ArrayList<Song> newChapterSongs = new ArrayList<Song>();
                String newChapterName = "";
                boolean notFirstChapter = false;
                ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
                HashMap<Integer,Song> songMap = new HashMap<Integer,Song>();
                try
                {
                    while ((line = buffreader.readLine()) != null) {
                        if (line.substring(0,1).equals("§")) {
                            if(notFirstChapter) {
                                chapterList.add(new Chapter(newChapterName, newChapterSongs));
                            } else {
                                notFirstChapter = true;
                            }
                            newChapterSongs.clear();
                            newChapterName = line.substring(2);
                        } else {
                            String[] songparts;
                            int parts = 0;
                            try {
                                Log.d("Tjohoooooooooooo","Line starts"+line+"ends");
                                songparts = line.split(" ");
                            } catch (Exception e) {
                                e.printStackTrace();
                                parts = -1;
                                songparts = null;
                            }
                            if (parts != -1 && (parts = songparts.length) >3) {
                                Log.d("OMG chordcode",songparts[1]);
                                int pageNr = Integer.parseInt(songparts[0]);
                                String chordCode = songparts[1];
                                String key = songparts[2];
                                String title= "";
                                for(int i = 3; i < parts; i++) {
                                    title += songparts[i]+" ";
                                }
                                Song newSong = new Song(pageNr,chordCode,key,title);
                                newChapterSongs.add(newSong);
                                songMap.put(pageNr,newSong);

                            }

                        }
                        currLine++;
                    }
                    chapterList.add(new Chapter(newChapterName, newChapterSongs));
                    return new SongBook(chapterList,songMap);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            String error="";
            error=e.getMessage();
        }
        return null;
    }

    public static Song createSong(Resources res, int pageNr) {
        String line = "";
        String tempLine = "";
        try
        {
            InputStream instream = res.openRawResource(R.raw.utantill);
            if (instream != null)
            {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                int currLine = 1;
                try
                {
                    while ((tempLine = buffreader.readLine()) != null) {
                        if (currLine == pageNr) {
                            line = tempLine;
                            break;
                        }
                        currLine++;
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            String error="";
            error=e.getMessage();
        }
        String[] songparts;
        int parts = 0;
        try {
            Log.d("Tjohoooooooooooo","Line starts"+line+"ends");
            songparts = line.split(" ");
        } catch (Exception e) {
            e.printStackTrace();
            parts = -1;
            songparts = null;
        }
        if (parts != -1 && (parts = songparts.length) >3) {
            Log.d("OMG chordcode",songparts[1]);
            String chordCode = songparts[1];
            String key = songparts[2];
            String title= "";
            for(int i = 3; i < parts; i++) {
                title += songparts[i]+" ";
            }
            return new Song(pageNr,chordCode,key,title);
        } else {
            return new Song(-1,"","","");
        }
    }
}
