package in.c42.nativebuilder;

import java.util.List;
import java.util.Map;
import java.util.Set;

class XmlMarkup {
    private StringBuilder target;

    public XmlMarkup(String target) {
        this.target = new StringBuilder(target);
    }

    public String toString() {
        return target.toString();
    }


    public XmlMarkup append(String string) {
        this.target.append(string);
        return this;
    }

    public void _insert_attributes(Map<String, String> attrs, List<String> order) {
        if (attrs == null) {
            return;
        }

        for (String anOrder : order) {
            String k = anOrder;
            String v = attrs.get(k);
            if (v != null) {
                target.append(" ").append(k).append("=").append("\"").append(v).append("\"");
            }
        }

        Set<String> pendingKeys = attrs.keySet();
        pendingKeys.removeAll(order);
        for (String k : pendingKeys) {
            target.append(" ").append(k).append("=").append("\"").append(attrs.get(k)).append("\"");
        }
//        order.each do |k|
//          v = attrs[k]
//          @target << %{ #{k}="#{_attr_value(v)}"} if v # " WART
//        end
//        attrs.each do |k, v|
//          @target << %{ #{k}="#{_attr_value(v)}"} unless order.member?(k) # " WART
//        end
    }
}