package in.c42.nativebuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class XmlMarkupTest {
    @Test
    public void shouldKnowHowToCreateAStartTag() {
        XmlMarkup builder = new XmlMarkup("");
        builder._start_tag("ooga");
        assertEquals("<ooga>", builder.toString());
    }

    @Test
    public void shouldKnowHowToCreateAStartTagWithAttributes() {
        XmlMarkup builder = new XmlMarkup("");
        HashMap<Object, Object> attributes = new HashMap<Object, Object>();
        attributes.put("id", new Integer(1));
        attributes.put("foo", "bar");
        builder._start_tag("ooga", attributes);
        assertEquals("<ooga id=\"1\" foo=\"bar\">", builder.toString());
    }

    @Test
    public void shouldKnowHowToCreateAClosedStartTag() {
        XmlMarkup builder = new XmlMarkup("");
        builder._start_tag("ooga", new HashMap<Object, Object>(), true);
        assertEquals("<ooga/>", builder.toString());
    }

    @Test
    public void shouldKnowHowToCreateAClosedStartTagWithAttributes() {
        XmlMarkup builder = new XmlMarkup("");
        HashMap<Object, Object> attributes = new HashMap<Object, Object>();
        attributes.put("id", new Integer(1));
        attributes.put("foo", "bar");
        builder._start_tag("ooga", attributes, true);
        assertEquals("<ooga id=\"1\" foo=\"bar\"/>", builder.toString());
    }

    @Test
    public void shouldKnowHowToCreateAnEndTag(){
        XmlMarkup builder = new XmlMarkup("");
        builder._end_tag("ooga");
        assertEquals("</ooga>", builder.toString());
    }

    @Test
    public void shouldKnowHowToConvertItselfToAString() {
        assertEquals("foo", new XmlMarkup("foo").toString());
    }

    @Test
    public void shouldKnowHowToAppendAStringToTheTarget() {
        assertEquals("foobar", new XmlMarkup("foo").append("bar").toString());
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
        assertEquals("&quot;&lt;oo&amp;ga&gt;&quot;", new XmlMarkup("")._escape_quote("\"<oo&ga>\""));
    }
}