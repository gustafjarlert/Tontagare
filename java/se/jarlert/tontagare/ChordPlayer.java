package se.jarlert.tontagare;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Gustaf on 2016-04-18.
 */
public class ChordPlayer {

    //delay between tones in ms
    private static int delay = 800;

    public static void playChords(Context context ,String[] tones) {
        for(int currentTone=0;currentTone<tones.length;currentTone++) {
            String currTone = tones[currentTone];
            Log.d("Playing the following:", currTone);
            int tone = R.raw.c3;
            switch (currTone) {
                case "c1":
                    tone = R.raw.c1;
                    break;
                case "c#1":
                    tone = R.raw.cx1;
                    break;
                case "d1":
                    tone = R.raw.d1;
                    break;
                case "d#1":
                    tone = R.raw.dx1;
                    break;
                case "e1":
                    tone = R.raw.e1;
                    break;
                case "f1":
                    tone = R.raw.f1;
                    break;
                case "f#1":
                    tone = R.raw.fx1;
                    break;
                case "g1":
                    tone = R.raw.g1;
                    break;
                case "g#1":
                    tone = R.raw.gx1;
                    break;
                case "a1":
                    tone = R.raw.a1;
                    break;
                case "a#1":
                    tone = R.raw.ax1;
                    break;
                case "b1":
                    tone = R.raw.b1;
                    break;
                case "c2":
                    tone = R.raw.c2;
                    break;
                case "c#2":
                    tone = R.raw.cx2;
                    break;
                case "d2":
                    tone = R.raw.d2;
                    break;
                case "d#2":
                    tone = R.raw.dx2;
                    break;
                case "e2":
                    tone = R.raw.e2;
                    break;
                case "f2":
                    tone = R.raw.f2;
                    break;
                case "f#2":
                    tone = R.raw.fx2;
                    break;
                case "g2":
                    tone = R.raw.g2;
                    break;
                case "g#2":
                    tone = R.raw.gx2;
                    break;
                case "a2":
                    tone = R.raw.a2;
                    break;
                case "a#2":
                    tone = R.raw.ax2;
                    break;
                case "b2":
                    tone = R.raw.b2;
                    break;
                case "c3":
                    tone = R.raw.c3;
                    break;
                case "c#3":
                    tone = R.raw.cx3;
                    break;
                case "d3":
                    tone = R.raw.d3;
                    break;
                case "d#3":
                    tone = R.raw.dx3;
                    break;
                case "e3":
                    tone = R.raw.e3;
                    break;
                case "f3":
                    tone = R.raw.f3;
                    break;
                case "f#3":
                    tone = R.raw.fx3;
                    break;
                case "g3":
                    tone = R.raw.g3;
                    break;
                case "g#3":
                    tone = R.raw.gx3;
                    break;
                case "a3":
                    tone = R.raw.a3;
                    break;
                case "a#3":
                    tone = R.raw.ax3;
                    break;
                case "b3":
                    tone = R.raw.b3;
                    break;
                case "c4":
                    tone = R.raw.c4;
                    break;
                case "c#4":
                    tone = R.raw.cx4;
                    break;
                case "d4":
                    tone = R.raw.d4;
                    break;
                case "d#4":
                    tone = R.raw.dx4;
                    break;
                case "e4":
                    tone = R.raw.e4;
                    break;
                case "f4":
                    tone = R.raw.f4;
                    break;
                case "f#4":
                    tone = R.raw.fx4;
                    break;
                case "g4":
                    tone = R.raw.g4;
                    break;
                case "g#4":
                    tone = R.raw.gx4;
                    break;
                case "a4":
                    tone = R.raw.a4;
                    break;
                case "a#4":
                    tone = R.raw.ax4;
                    break;
                case "b4":
                    tone = R.raw.b4;
                    break;
                case "c5":
                    tone = R.raw.c5;
                    break;
            }
            final MediaPlayer mediaPlayer = MediaPlayer.create(context, tone);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.start();

                }
            }, currentTone*800);
            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.release();
                }
            }, currentTone*800+700);
        }
    }
}
