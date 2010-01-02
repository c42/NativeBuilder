package in.c42.nativebuilder;

import org.jruby.RubySymbol;

import java.io.IOException;
import java.util.*;

class XmlMarkup extends XmlBase  {
    private StringBuilder target;

    public XmlMarkup () {
        super();
        this.target = new StringBuilder();
    }

    public XmlMarkup(String target) {
        super();
        this.target = new StringBuilder(target);
    }

    public XmlMarkup(Map<Object, Object> options) {
        super((Integer)MapUtils.stringifyKeysExclamation(options).get("indent"), (Integer)options.get("margin"));
        this.target = new StringBuilder(target);
    }

    public String toString() {
        return target.toString();
    }


    public XmlMarkup append(String string) {
        this.target.append(string);
        return this;
    }

    public String _attr_value(Object symbolOrString) {
        if (symbolOrString instanceof RubySymbol) {
            return ((RubySymbol) symbolOrString).asJavaString();
        } else {
            return _escape_quote(symbolOrString.toString());
        }
    }


    public void _insert_attributes(Map<Object, Object> attrs) {
        _insert_attributes(attrs, new ArrayList<Object>());
    }
    
    public void _insert_attributes(Map<Object, Object> attrs, List<Object> order) {
        if(attrs == null) return;

        for (Object k : order) {
            Object v = attrs.get(k);
            if (v != null) {
                target.append(' ').append(k.toString()).append('=').append('\"').append(_attr_value(v)).append('\"');
            }
        }

        Set<Object> pendingKeys = attrs.keySet();
        pendingKeys.removeAll(order);
        for (Object k : pendingKeys) {
            Object v = attrs.get(k);
            target.append(' ').append(k.toString()).append('=').append('\"').append(_attr_value(v)).append('\"');
        }

//        def _insert_attributes(attrs, order=[])
//          return if attrs.nil?
//          order.each do |k|
//            v = attrs[k]
//            @target << %{ #{k}="#{_attr_value(v)}"} if v # " WART
//          end
//          attrs.each do |k, v|
//            @target << %{ #{k}="#{_attr_value(v)}"} unless order.member?(k) # " WART
//          end
//        end
    }

    public void _start_tag(Object tagName) {
        _start_tag(tagName, new HashMap<Object, Object>());
    }
    
    public void _start_tag(Object tagName, Map<Object, Object> attributes) {
        _start_tag(tagName, attributes, false);
    }

    @Override
    public void _start_tag(Object tagName, Map<Object, Object> attributes, boolean endToo) {
        target.append('<').append(tagName.toString());
        _insert_attributes(attributes);
        if(endToo) target.append('/');
        target.append('>');
//        def _start_tag(sym, attrs, end_too=false)
//          @target << "<#{sym}"
//          _insert_attributes(attrs)
//          @target << "/" if end_too
//          @target << ">"
//        end
    }

    @Override
    public void _end_tag(Object tagName) {
        target.append("</").append(tagName.toString()).append('>');
//        def _end_tag(sym)
//          @target << "</#{sym}>"
//        end
    }

    @Override
    public void _text(String text) throws IOException {
        target.append(text);
    }
}