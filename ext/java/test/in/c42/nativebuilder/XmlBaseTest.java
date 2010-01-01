package in.c42.nativebuilder;

import org.junit.Test;

import static junit.framework.Assert.*;

public class XmlBaseTest {
    @Test
    public void shouldKnowHowToXmlEscapeStrings() throws Exception {
        assertEquals("&amp;", new XmlBase()._escape("&"));     
        assertEquals("&lt;", new XmlBase()._escape("<"));
        assertEquals("&gt;", new XmlBase()._escape(">"));     
    }
}
