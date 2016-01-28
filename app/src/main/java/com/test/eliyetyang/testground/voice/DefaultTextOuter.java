package com.test.eliyetyang.testground.voice;

import android.widget.EditText;

/**
 * Created by eliyetyang on 15-11-10.
 */
public class DefaultTextOuter implements IVoiceTextOuter {
    @Override
    public CharSequence putTextIntoView(EditText targetView, CharSequence result) {
        int selectionStart = targetView.getSelectionStart();
        int selectionEnd = targetView.getSelectionEnd();
        targetView.append(result, selectionStart, selectionEnd);
        return result.toString();
    }
}
