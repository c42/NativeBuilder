package in.c42.nativebuilder;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.IOException;
import java.util.Map;

public abstract class XmlBase {
    private static final String DefaultEncoding = "utf-8";
    private Integer indent;
    private Integer initial;
    private String encoding;

    public XmlBase() {
        this.indent = 0;
        this.initial = 0;
        this.encoding = DefaultEncoding;
    }

    public XmlBase(Integer indent, Integer initial) {
        this(indent, initial, DefaultEncoding);
    }

    public XmlBase(Integer indent, Integer initial, String encoding) {
        this.indent = indent;
        this.initial = initial;
        this.encoding = encoding.toLowerCase();
    }

    public void textExclamation(String text) throws IOException {
        _text(_escape(text));
    }

    public String _escape(String text){
        return StringEscapeUtils.escapeXml(text);
    }

    public String _escape_quote(String string) {
        // I'm maintaining the same methods as Ruby Builder for
        // consistency; however _escape also takes care of escaping
        // quotes.
        return _escape(string);
    }

    public void _newline() throws IOException {
        if(indent == 0) return;
        textExclamation("\n");
//       def _newline
//        return if @indent == 0
//        text! "\n"
//       end
    }

    public abstract void _start_tag(Object tagName, Map<Object, Object> attributes, boolean endToo) throws IOException;

    public abstract void _end_tag(Object tagName) throws IOException;

    public abstract void _text(String text) throws IOException;
}
