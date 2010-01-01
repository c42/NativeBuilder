package in.c42.nativebuilder;

import org.jruby.RubySymbol;

import java.util.*;

class XmlMarkup extends XmlBase {
    private StringBuilder target;

    public XmlMarkup() {
        super();
        this.target = new StringBuilder();
    }

    public XmlMarkup(String target) {
        super();
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
            return _escape_quotes(symbolOrString.toString());
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
                target.append(" ").append(k.toString()).append("=").append("\"").append(_attr_value(v)).append("\"");
            }
        }

        Set<Object> pendingKeys = attrs.keySet();
        pendingKeys.removeAll(order);
        for (Object k : pendingKeys) {
            Object v = attrs.get(k);
            target.append(" ").append(k.toString()).append("=").append("\"").append(_attr_value(v)).append("\"");
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

    public String _escape_quotes(String string) {
        // I'm maintaining the same methods as Ruby Builder for
        // consistency; however _escape also takes care of escaping
        // quotes.
        return _escape(string);
    }
}