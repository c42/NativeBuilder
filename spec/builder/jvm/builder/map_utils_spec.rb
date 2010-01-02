require File.dirname(__FILE__) + '/../../../spec_helper'

describe Builder::JVM::MapUtils, 'under JRuby' do
  it "should be able to fetch values keyed by a symbol using the equivalent string" do
    Builder::JVM::MapUtils.getUsingStringOrSymbol("ooga", {"ooga" => "booga"}, JRuby.runtime).should == "booga"
    Builder::JVM::MapUtils.getUsingStringOrSymbol("ooga", {:ooga => "booga"}, JRuby.runtime).should == "booga"
    Builder::JVM::MapUtils.getUsingStringOrSymbol("ooga", {"ooga" => "woot", :ooga => "booga"}, JRuby.runtime).should == "woot"
  end
end
