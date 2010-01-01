package in.c42.nativebuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class XmlMarkupTest {
    @Test
    public void shouldKnowHowToConvertItselfToAString() {
        assertEquals(new XmlMarkup("foo").toString(), "foo");
    }

    @Test
    public void shouldKnowHowToAppendAStringToTheTarget() {
        assertEquals(new XmlMarkup("foo").append("bar").toString(), "foobar");
    }

    @Test
    public void shouldDoNothingIfAttributesAreNullWhileInsertingAttributes() {
        XmlMarkup builder = new XmlMarkup("");
        builder._insert_attributes(null, new ArrayList<Object>());
        assertEquals("", builder.toString());
    }

    @Test
    public void shouldKnowHowToInsertAttributes() {
        XmlMarkup builder = new XmlMarkup("");
        HashMap attrs = new HashMap();
        attrs.put("ooga", "booga");
        attrs.put("foo", "bar");
        attrs.put("avast", "ye");
        ArrayList order = new ArrayList();
        order.add("ooga");
        order.add("foo");
        builder._insert_attributes(attrs, order);
        assertEquals(" ooga=\"booga\" foo=\"bar\" avast=\"ye\"", builder.toString());
    }

    @Test
    public void shouldKnowHowToEscapeXmlCharsAndQuotes() {
        assertEquals("&quot;&lt;oo&amp;ga&gt;&quot;", new XmlMarkup("")._escape_quotes("\"<oo&ga>\""));
    }
}