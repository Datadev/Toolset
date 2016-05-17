package br.com.datadev.toolset.swing.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author Fabrício
 */
public class FixedLengthDocumentTest {

    private FixedLengthDocument doc;
    private String str1;
    private String str2;
    private int offset;
    private AttributeSet attr;

    public FixedLengthDocumentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        doc = new FixedLengthDocument(5);
        str1 = "FixedLengthDocument";
        str2 = "Test";
        offset = 0;
        attr = null;
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of insertString method, of class FixedLengthDocument.
     *
     * @throws javax.swing.text.BadLocationException
     */
    @org.junit.Test
    public void testInsertString1() throws BadLocationException {
        doc.insertString(offset, str1, attr);
        assertFalse(doc.getLength() == str1.length());
    }

    /**
     * Test of insertString method, of class FixedLengthDocument.
     *
     * @throws javax.swing.text.BadLocationException
     */
    @org.junit.Test
    public void testInsertString2() throws BadLocationException {
        doc.insertString(offset, str2, attr);
        assertTrue(doc.getText(offset, doc.getLength()).equals(str2));
    }
}
