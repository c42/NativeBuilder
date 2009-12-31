package in.c42.nativebuilder;

class XmlMarkup{
    private StringBuilder target;

    public XmlMarkup(String target){
        this.target = new StringBuilder(target);
    }

    public String toString() {
        return target.toString();
    }

   
    public XmlMarkup append(String string) {
        this.target.append(string);
        return this;
    }
}