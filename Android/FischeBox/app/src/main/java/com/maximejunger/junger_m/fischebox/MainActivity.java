package com.maximejunger.junger_m.fischebox;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayDeque;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener
{

    private TextToSpeech tts;

    private ListView    listView;
    private ArrayAdapter    adapter;

    private int before = 0;

    SoundPoolPlayer sound;

    String[] quotes = {
        "Putain mais tu fais chier !",
        "Casse-toi.",
        "TG.",
        "La ferme.",
        "Putain mais casse-toi.",
        "Mais putain mais casse-toi.",
        "Ferme ta gueule.",
        "Franchement fermez vos gueules.",
        "Ça sert à rien.",
        "Mais putain mais fermez vos gueules.",
        "Mais putain mais ferme ta gueule.",
        "Ta gueule.",
        "Tu fais chier.",
        "En même temps si t'es une merde...",
        "Casse-toi putain, casse-toi.",
        "Connard.",
        "Putain mais t'es vraiment un connard.",
        "PD."
    };

    String[] speakSound = {
    "ZOOM0009",
    "ZOOM0010",
    "ZOOM0011",
    "ZOOM0012",
    "ZOOM0013",
    "ZOOM0014",
    "ZOOM0015",
    "ZOOM0016",
    "ZOOM0017",
    "ZOOM0018",
    "ZOOM0019",
    "ZOOM0020",
    "ZOOM0021",
    "ZOOM0022",
    "ZOOM0023",
    "ZOOM0024",
    "ZOOM0025",
    "ZOOM0026",
};

    int[]   speakSounds = {
      R.raw.zoom0009,
      R.raw.zoom0010,
      R.raw.zoom0011,
      R.raw.zoom0012,
      R.raw.zoom0013,
      R.raw.zoom0014,
      R.raw.zoom0015,
      R.raw.zoom0016,
      R.raw.zoom0017,
      R.raw.zoom0018,
      R.raw.zoom0019,
      R.raw.zoom0020,
      R.raw.zoom0021,
      R.raw.zoom0022,
      R.raw.zoom0023,
      R.raw.zoom0024,
      R.raw.zoom0025,
      R.raw.zoom0026,
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, this);
        listView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter(this, R.layout.speak_cell, quotes);
        listView.setAdapter(adapter);

        sound = new SoundPoolPlayer(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //speak(quotes[i]);
                    sound.playShortResource(speakSounds[i]);
                }
            });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
  /*      if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        sound.release();
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.FRANCE);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {

       //         speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    public void randomSpeech(View v)
    {
        Random randomGenerator = new Random();
        //int randomInt = randomGenerator.nextInt(18);
        int actual;

        actual = randomGenerator.nextInt(17);
        while (actual == before) {
            actual = randomGenerator.nextInt(17);
        }
        before = actual;
        //this.speak(quotes[actual]);
        sound.playShortResource(speakSounds[actual]);
    }

    private void speak(String text)
    {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
