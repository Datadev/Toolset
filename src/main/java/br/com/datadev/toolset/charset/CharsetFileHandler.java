package br.com.datadev.toolset.charset;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Fabrício
 */
public class CharsetFileHandler extends CharsetHandler {

    private final File file;

    public CharsetFileHandler(File file) throws IOException {
        super(FileUtils.readFileToString(file));
        this.file = file;
    }

    private void convert(Charset to, File dst) throws IOException {
        String content = super.convertTo(to);
        FileUtils.write(dst, content.replace("\u0000", ""), to);
    }

    public void convertFileTo(Charset to) throws IOException {
        this.convert(to, this.file);
    }

    public void convertFileTo(String to) throws IOException {
        this.convert(Charset.forName(to), this.file);
    }

    public void convertFileToNew(Charset to, File newFile) throws IOException {
        this.convert(to, newFile);
    }

    public void convertFileToNew(String to, File newFile) throws IOException {
        this.convert(Charset.forName(to), newFile);
    }
}
