package in.c42.nativebuilder;

import org.apache.commons.lang.StringEscapeUtils;

public class XmlBase {
    public XmlBase() {
    }

    public String _escape(String text){
        return StringEscapeUtils.escapeXml(text);
    }
}
