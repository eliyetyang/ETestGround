package com.test.eliyetyang.testground.voice;

import com.test.eliyetyang.testground.voice.bean.VoiceModel;

/**
 * Created by eliyetyang on 15-11-26.
 */
public interface IVoiceParser {
    CharSequence parsingModel(VoiceModel voiceModel) throws NullModelException;
}
