require File.dirname(__FILE__) + '/../../../spec_helper'

module Builder
  describe XmlMarkup, 'under JRuby' do
    it "should expose #class as #__class__" do
      BlankSlate.instance_variable_get('@hidden_methods').keys.should include(:class)
      XmlMarkup.new.__class__.should == XmlMarkup
    end

    it "should build a JVM::XmlBuilder if the target passed to the constructor is nil" do
      XmlMarkup.new.__instance_variable_get__("@target").class.should == Builder::JVM::XmlMarkup
    end

    it "should build a JVM::XmlBuilder if the target passed to the constructor is a string" do
      XmlMarkup.new(:target => "<ooga></ooga>").__instance_variable_get__("@target").class.should == Builder::JVM::XmlMarkup
    end

    it "should convert a JVM::XmlMarkup back to a String" do
      XmlMarkup.new(:target => "<ooga></ooga>").target!.class.should == String
    end

    it "should pass the target string on to the JVM::XmlBuilder" do
      XmlMarkup.new(:target => "<ooga></ooga>").target!.should == "<ooga></ooga>"
    end

    it "should use the target object directly as the target without decoration if it is not a string and not nil" do
      target = Object.new
      XmlMarkup.new(:target => target).target!.should == target
    end

    it "should know how to create an XML processing instruction" do
      builder = XmlMarkup.new
      builder.instruct!
      builder.target!.to_s.should == "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
    end
  end
end
