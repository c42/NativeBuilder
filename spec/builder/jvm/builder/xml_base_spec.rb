require File.dirname(__FILE__) + '/../../../spec_helper'

module Builder
  describe XmlBase, 'under JRuby' do
    it "should decorate method_missing to replace a JVM::XmlMarkup with a String" do
      XmlMarkup.new.x("men").should == '<x>men</x>'
    end
  end
end
