package com.maximejunger.junger_m.fischebox;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by junger_m on 04/05/15.
 */

public class SoundPoolPlayer {
    private SoundPool mShortPlayer= null;
    private HashMap mSounds = new HashMap();

    public SoundPoolPlayer(Context pContext)
    {
        // setup Soundpool
        this.mShortPlayer = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);

        mSounds.put(R.raw.zoom0009, this.mShortPlayer.load(pContext, R.raw.zoom0009, 1));
        mSounds.put(R.raw.zoom0010, this.mShortPlayer.load(pContext, R.raw.zoom0010, 1));
        mSounds.put(R.raw.zoom0011, this.mShortPlayer.load(pContext, R.raw.zoom0011, 1));
        mSounds.put(R.raw.zoom0012, this.mShortPlayer.load(pContext, R.raw.zoom0012, 1));
        mSounds.put(R.raw.zoom0013, this.mShortPlayer.load(pContext, R.raw.zoom0013, 1));
        mSounds.put(R.raw.zoom0014, this.mShortPlayer.load(pContext, R.raw.zoom0014, 1));
        mSounds.put(R.raw.zoom0015, this.mShortPlayer.load(pContext, R.raw.zoom0015, 1));
        mSounds.put(R.raw.zoom0016, this.mShortPlayer.load(pContext, R.raw.zoom0016, 1));
        mSounds.put(R.raw.zoom0017, this.mShortPlayer.load(pContext, R.raw.zoom0017, 1));
        mSounds.put(R.raw.zoom0018, this.mShortPlayer.load(pContext, R.raw.zoom0018, 1));
        mSounds.put(R.raw.zoom0019, this.mShortPlayer.load(pContext, R.raw.zoom0019, 1));
        mSounds.put(R.raw.zoom0020, this.mShortPlayer.load(pContext, R.raw.zoom0020, 1));
        mSounds.put(R.raw.zoom0021, this.mShortPlayer.load(pContext, R.raw.zoom0021, 1));
        mSounds.put(R.raw.zoom0022, this.mShortPlayer.load(pContext, R.raw.zoom0022, 1));
        mSounds.put(R.raw.zoom0023, this.mShortPlayer.load(pContext, R.raw.zoom0023, 1));
        mSounds.put(R.raw.zoom0024, this.mShortPlayer.load(pContext, R.raw.zoom0024, 1));
        mSounds.put(R.raw.zoom0025, this.mShortPlayer.load(pContext, R.raw.zoom0025, 1));
        mSounds.put(R.raw.zoom0026, this.mShortPlayer.load(pContext, R.raw.zoom0026, 1));



    }

    public void playShortResource(int piResource) {
        int iSoundId = (Integer) mSounds.get(piResource);
        this.mShortPlayer.play(iSoundId, 0.99f, 0.99f, 0, 0, 1);
    }

    // Cleanup
    public void release() {
        // Cleanup
        this.mShortPlayer.release();
        this.mShortPlayer = null;
    }
}