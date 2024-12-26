package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.utils.LogUtils;
import java.util.regex.Pattern;
public class EmojiUtils {
    public static final String unicodeReg = "[一-龿䷀-䷿\u0000-\u007f\u0080-ÿĀ-ſƀ-ɏɐ-ʯʰ-˿̀-ͯͰ-ϿЀ-ӿԀ-ԯ\u0530-֏\u0590-\u05ff\u0600-ۿ܀-ݏݐ-ݿހ-\u07bfࠀ-\u085fࡠ-\u087f\u0880-ࢯऀ-ॿঀ-\u09ff\u0a00-\u0a7f\u0a80-૿\u0b00-\u0b7f\u0b80-\u0bffఀ-౿ಀ-\u0cffഀ-ൿ\u0d80-\u0dff\u0e00-\u0e7f\u0e80-\u0effༀ-\u0fffက-႟Ⴀ-ჿᄀ-ᇿሀ-\u137fᎀ-\u139fᎠ-\u13ff᐀-ᙿ\u1680-\u169fᚠ-\u16ffᜀ-\u171fᜠ-\u173fᝀ-\u175fᝠ-\u177fក-\u17ff᠀-\u18afᢰ-\u18ffᤀ-᥏ᥐ-\u197fᦀ-᧟᧠-᧿ᨀ-᨟ᨠ-\u1a5f᪀-\u1aefᬀ-\u1b7fᮀ-᮰ᯀ-᯿ᰀ-ᱏ᱐-᱿ᲀ-᳟ᴀ-ᵿᶀ-ᶿ᷀-᷿Ḁ-ỿἀ-\u1fff\u2000-\u206f⁰-\u209f₠-\u20cf⃐-\u20ff℀-⅏⅐-\u218f←-⇿∀-⋿⌀-⏿␀-\u243f⑀-\u245f①-⓿─-╿▀-▟■-◿☀-⛿✀-➿⟀-⟯⟰-⟿⠀-⣿⤀-⥿⦀-⧿⨀-⫿⬀-\u2bffⰀ-\u2c5fⱠ-ⱿⲀ-⳿ⴀ-\u2d2fⴰ-⵿ⶀ-\u2ddf⸀-\u2e7f⺀-\u2eff⼀-\u2fdf⿰-\u2fff\u3000-〿\u3040-ゟ゠-ヿ\u3100-\u312f\u3130-\u318f㆐-㆟ㆠ-\u31bf㇀-\u31efㇰ-ㇿ㈀-㋿㌀-㏿㐀-\u4dbf䷀-䷿一-龿ꀀ-\ua48f꒐-\ua4cfꔀ-ꘟꙠ-\ua6ff꜀-ꜟ꜠-ꟿꠀ-\ua82fꡀ-\ua87fꢀ-\ua8df꤀-\ua97fꦀ-꧟ꨀ-\uaa3fꩀ-ꩯꪀ-꫟\uab00-ꭟꮀ-ꮠ가-\ud7af\ue000-\uf8ff豈-\ufaffﬀ-ﭏﭐ-\ufdff︀-️︐-\ufe1f︠-︯︰-﹏﹐-\ufe6fﹰ-\ufeff\uff00-\uffef\ufff0-\uffff]";

    public static boolean emojiFilter(String str) {
        try {
            Pattern compile = Pattern.compile(unicodeReg);
            boolean z = false;
            for (int i = 0; i < str.length(); i++) {
                if (!compile.matcher(String.valueOf(str.charAt(i))).find()) {
                    z = true;
                }
            }
            return z;
        } catch (Exception e) {
            LogUtils.e(e);
            return true;
        }
    }

    public static boolean emojiFilter(char c) {
        try {
            return true ^ Pattern.compile(unicodeReg).matcher(String.valueOf(c)).find();
        } catch (Exception e) {
            LogUtils.e(e);
            return true;
        }
    }
}
