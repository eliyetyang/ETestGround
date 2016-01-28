package com.test.eliyetyang.testground.voice;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.test.eliyetyang.testground.voice.bean.VoiceModel;

/**
 * Created by eliyetyang on 15-11-10.
 */
public class VoiceInputListener {
    private Context mContext;
    private RecognizerDialog mIatDialog;
    private Gson mGson;
    private Button mStartButton;
    private EditText mInputEditText;
    private IVoiceTextOuter mIVoiceTextOuter;
    private IVoiceParser mIVoiceParser;

    public VoiceInputListener(Context mContext, Gson mGson, Button mStartButton, EditText mInputEditText) {
        this(mContext, mGson, mStartButton, mInputEditText, null);
    }

    public VoiceInputListener(Context mContext, Gson mGson, Button mStartButton, EditText mInputEditText, IVoiceTextOuter mIVoiceTextOuter) {
        this.mContext = mContext;
        this.mGson = mGson;
        this.mStartButton = mStartButton;
        this.mInputEditText = mInputEditText;
        this.mIVoiceTextOuter = mIVoiceTextOuter;
        mIVoiceParser = new DefaultModeParser();
    }

    public void start() {
        mStartButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mIatDialog.show();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        //cancel speech
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        mIatDialog = new RecognizerDialog(mContext, null);
        mIatDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                Log.e("DialogListener", recognizerResult.getResultString());
                VoiceModel voiceModel = mGson.fromJson(recognizerResult.getResultString(), VoiceModel.class);
                try {
                    mIVoiceTextOuter.putTextIntoView(mInputEditText, mIVoiceParser.parsingModel(voiceModel));
                } catch (NullModelException e) {
                    Toast.makeText(mContext, "error input bean", Toast.LENGTH_SHORT).show();
                    Log.e("VoiceInputListener", "null VoiceModel");
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(SpeechError speechError) {
                Log.e("DialogListener", speechError.toString());
            }
        });
        if (mIVoiceTextOuter == null) {
            mIVoiceTextOuter = new DefaultTextOuter();
        }
    }
}
