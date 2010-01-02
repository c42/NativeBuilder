package in.c42.nativebuilder;

import org.junit.Test;

import static junit.framework.Assert.*;

public class XmlBaseTest {
    @Test
    public void shouldKnowHowToXmlEscapeStrings() throws Exception {
        assertEquals("&amp;", new XmlMarkup()._escape("&"));
        assertEquals("&lt;", new XmlMarkup()._escape("<"));
        assertEquals("&gt;", new XmlMarkup()._escape(">"));     
        assertEquals("&quot;", new XmlMarkup()._escape("\""));
    }
}
