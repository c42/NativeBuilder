require 'java'
require "commons-lang-2.4"
require "native_builder"

module Builder
  module JVM
    include_package "in.c42.nativebuilder"
  end
end

require "#{File.dirname(__FILE__)}/jvm/xml_markup_extensions"
require "#{File.dirname(__FILE__)}/jvm/builder"
