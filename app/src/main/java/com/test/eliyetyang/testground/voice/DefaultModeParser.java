package com.test.eliyetyang.testground.voice;

import com.test.eliyetyang.testground.voice.bean.VoiceModel;

/**
 * Created by eliyetyang on 15-11-26.
 */
public class DefaultModeParser implements IVoiceParser {
    @Override
    public CharSequence parsingModel(VoiceModel voiceModel) throws NullModelException {
        StringBuilder result = null;
        if (voiceModel.ws != null) {
            for (int i = 0; i < voiceModel.ws.size(); i++) {
                for (int j = 0; j < voiceModel.ws.get(i).cw.size(); j++) {
                    result.append(voiceModel.ws.get(i).cw.get(j).w);
                }
            }
        } else {
            throw new NullModelException();
        }
        return result.toString();
    }
}
