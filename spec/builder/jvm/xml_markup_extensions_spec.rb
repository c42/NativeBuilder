require File.dirname(__FILE__) + '/../../spec_helper'

module Builder
  describe JVM::XmlMarkup do
    it "should support appending to the target using <<" do
      (JVM::XmlMarkup.new("<ooga>") << "</ooga>").to_s.should == "<ooga></ooga>"
    end
  end
end
