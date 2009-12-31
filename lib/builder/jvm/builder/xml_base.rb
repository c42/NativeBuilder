module Builder
  class XmlBase
    alias_method :method_missing_without_jvm_to_string_conversion, :method_missing
    def method_missing_with_jvm_to_string_conversion(sym, *args, &block)
      target = method_missing_without_jvm_to_string_conversion(sym, *args, &block)
      target.__class__ == JVM::XmlMarkup ? target.toString : target
    end
    alias_method :method_missing, :method_missing_with_jvm_to_string_conversion
  end
end