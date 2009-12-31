package in.c42.nativebuilder;

import org.junit.Test;

import static junit.framework.Assert.*;

public class XmlMarkupTest {
    @Test
    public void shouldKnowHowToConvertItselfToAString(){
        assertEquals(new XmlMarkup("foo").toString(), "foo");
    }

    @Test
    public void shouldKnowHowToAppendAStringToTheTarget(){
        assertEquals(new XmlMarkup("foo").append("bar").toString(), "foobar");
    }
}