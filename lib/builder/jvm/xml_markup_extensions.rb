# This extends the Java class

Builder::JVM::XmlMarkup.class_eval do
  def <<(string)
    append(string)
  end
end
