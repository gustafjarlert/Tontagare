package se.jarlert.tontagare;

import se.jarlert.tontagare.util.SystemUiHider;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class StartMenu extends Activity implements AdapterView.OnItemClickListener {

    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList menuTitles = new ArrayList();
    SongBook mainSongBook;
    boolean showingChapters;
    Chapter currentChapter;
    TextView pageNrText;
    TextView titleText;
    TextView keyText;
    Button searchButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainSongBook = BookReader.createSongBook(getResources());
        setContentView(R.layout.activity_start_menu);

        final EditText pageNrField = (EditText) findViewById(R.id.pageNrInput);
        pageNrText = (TextView) findViewById(R.id.pageNr);
        titleText = (TextView) findViewById(R.id.songName);
        keyText = (TextView) findViewById(R.id.key);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                try {
                                                    int pageNr = Integer.parseInt(pageNrField.getText().toString());
                                                    Song song = mainSongBook.getSongOnPage(pageNr);
                                                    pageNrText.setText("" + pageNr);
                                                    if (song == null) {
                                                        titleText.setText("Not a valid page number");
                                                        keyText.setText("");
                                                    } else {
                                                        titleText.setText(song.getTitle());
                                                        keyText.setText(song.getKey());
                                                        ChordPlayer.playChords(getBaseContext(), song.getChord());
                                                    }
                                                } catch (NumberFormatException e) {
                                                    titleText.setText("Not a valid page number");
                                                }

                                            }


        });




        mainListView = (ListView) findViewById(R.id.main_listview);
        ListIterator<Chapter> chapterIterator = mainSongBook.getChapterListIterator();
        showingChapters = true;
        while (chapterIterator.hasNext()) {
            menuTitles.add(chapterIterator.next().getName());
        }
        mainListView.setOnItemClickListener(this);

// Create an ArrayAdapter for the ListView
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                menuTitles);

// Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);
        mainListView.setOnItemClickListener(this);

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setClickable(false);
        backButton.setAlpha(0);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                menuTitles.clear();
                ListIterator<Chapter> chapterIterator = mainSongBook.getChapterListIterator();
                showingChapters = true;
                while (chapterIterator.hasNext()) {
                    menuTitles.add(chapterIterator.next().getName());
                }
                mArrayAdapter.notifyDataSetChanged();
                backButton.setClickable(false);
                backButton.setAlpha(0);

            };


        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(showingChapters) {
                menuTitles.clear();
                currentChapter = mainSongBook.getChapter(position);
                ListIterator<Song> songIterator = currentChapter.getSongListIterator();
                while (songIterator.hasNext()) {
                    menuTitles.add(songIterator.next().getTitle());
                }
                //menuTitles.add("Tillbaka");
                backButton.setClickable(true);
                backButton.setAlpha(1);
                showingChapters = false;
            } else {
/**                if(position >= currentChapter.getSize()) {
                    menuTitles.clear();
                    ListIterator<Chapter> chapterIterator = mainSongBook.getChapterListIterator();
                    showingChapters = true;
                    while (chapterIterator.hasNext()) {
                        menuTitles.add(chapterIterator.next().getName());
                    }
                    mArrayAdapter.notifyDataSetChanged();
                    return;
                }*/
                Song song = currentChapter.getSong(position);
//                String pageNrString = "" + song.getPageNr();
                pageNrText.setText("" + song.getPageNr());
                titleText.setText(song.getTitle());
                keyText.setText(song.getKey());
                ChordPlayer.playChords(getBaseContext(), song.getChord());

            }
        mArrayAdapter.notifyDataSetChanged();

    }
}
