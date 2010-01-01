require File.dirname(__FILE__) + '/../../../spec_helper'

module Builder
  describe XmlMarkup, 'under JRuby' do
    describe JVM::XmlMarkup do
      it "should know how to convert symbols to strings" do
        JVM::XmlMarkup.new._attr_value(:ooga).should == 'ooga'
        JVM::XmlMarkup.new._attr_value('ooga').should == 'ooga'
      end
      
      it "should know how to insert attributes given symbols" do
        builder = JVM::XmlMarkup.new
        builder._insert_attributes({:ooga => :booga, :foo => :bar}, [:ooga, :foo])
        builder.to_s.should == " ooga=\"booga\" foo=\"bar\""
      end

      it "should know how to insert attributes given strings" do  
        builder = JVM::XmlMarkup.new("")
        builder._insert_attributes({'ooga' => 'booga', 'foo' => 'bar'}, ['ooga', 'foo'])
        builder.to_s.should == " ooga=\"booga\" foo=\"bar\""
      end
      
      it "should know how to insert attributes given integers" do  
        builder = JVM::XmlMarkup.new("")
        builder._insert_attributes({'id' => 1})
        builder.to_s.should == " id=\"1\""
      end
    end
    
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

    it "should do nothing when insert attributes and attrs is nil" do
      builder = XmlMarkup.new
      builder.__send__(:_insert_attributes, nil, [:ooga, :foo])
      builder.target!.should == ""
    end

    it "should know how to quote special symbols in insert attribute values" do
      builder = XmlMarkup.new
      builder.__send__(:_insert_attributes, {:ooga => :booga, :foo => 'ba&r'}, [:ooga, :foo])
      builder.target!.should == " ooga=\"booga\" foo=\"ba&amp;r\""
    end

    it "should know how to insert attributes" do
      builder = XmlMarkup.new
      builder.__send__(:_insert_attributes, {:ooga => :booga, :foo => :bar}, [:ooga, :foo])
      builder.target!.should == " ooga=\"booga\" foo=\"bar\""

      builder = XmlMarkup.new
      builder.__send__(:_insert_attributes, {:ooga => :booga, :foo => :bar}, [:foo, :ooga])
      builder.target!.should == " foo=\"bar\" ooga=\"booga\""
    end
    
    it "should know how to create a start tag" do
      builder = XmlMarkup.new
      builder.__send__(:_start_tag, :ooga, {})
      builder.target!.should == "<ooga>"
    end
    
    it "should know how to create a start tag that is an end tag too" do
      builder = XmlMarkup.new
      builder.__send__(:_start_tag, :ooga, {}, true)
      builder.target!.should == "<ooga/>"
    end
    
    it "should know how to create a start tag with attributes" do
      builder = XmlMarkup.new
      builder.__send__(:_start_tag, :ooga, {:ayyo => :machcha})
      builder.target!.should == "<ooga ayyo=\"machcha\">"
    end
    
    it "should test_mixed_attribute_quoting_with_nested_builders" do
      xml = Builder::XmlMarkup.new
      x = Builder::XmlMarkup.new(:target => xml)
      xml.ref(:id=>:"H&amp;R") {
        x.element(:tag=>"Long&Short")
      }
      xml.target!.should == "<ref id=\"H&amp;R\"><element tag=\"Long&amp;Short\"/></ref>"
    end    
  end
end
