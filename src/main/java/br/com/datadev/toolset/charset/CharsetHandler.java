package br.com.datadev.toolset.charset;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/**
 *
 * @author Fabrício
 */
public class CharsetHandler {

    private final String content;
    private final Charset charset;

    public CharsetHandler(String content) {
        this.content = content;
        this.charset = detect();
    }

    private Charset detect() {
        CharsetDetector cd = new CharsetDetector();
        cd.setText(content.getBytes());
        CharsetMatch cm = cd.detect();

        if (cm != null) {
            return Charset.forName(cm.getName());
        } else {
            throw new UnsupportedCharsetException("Unsupported");
        }
    }

    public Charset getCharset() {
        return charset;
    }

    public String convertTo(String to) {
        return this.convertTo(Charset.forName(to));
    }

    public String convertTo(Charset to) {
        CharBuffer in = charset.decode(ByteBuffer.wrap(content.getBytes()));
        ByteBuffer out = to.encode(in);
        CharsetDetector cd = new CharsetDetector();
        cd.setText(out.array());
        return new String(out.array(), to).replace("\u0000", "");
    }
}
