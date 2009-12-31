require File.dirname(__FILE__) + '/../../../spec_helper'

describe Object, 'under JRuby' do
  it "should alias #class to #__class__ so that fracking BlankSlate stops buggering stuff" do
    Object.new.__class__.should == Object
  end
end
