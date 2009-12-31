require 'java'
require "#{File.dirname(__FILE__)}/jvm/native_builder.jar"

module Builder
  module JVM
    include_package "in.c42.nativebuilder"
  end
end

require "#{File.dirname(__FILE__)}/jvm/xml_markup_extensions"
require "#{File.dirname(__FILE__)}/jvm/builder"
