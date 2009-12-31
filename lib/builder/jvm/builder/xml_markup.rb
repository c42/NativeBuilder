# This extends the Ruby builder class
module Builder
  class XmlMarkup
    BlankSlate.reveal(:class)
    alias :__class__ :class
    BlankSlate.hide(:class)

    BlankSlate.reveal(:instance_variable_get)
    alias :__instance_variable_get__ :instance_variable_get
    BlankSlate.hide(:instance_variable_get)
    
    def initialize(options={})
      indent = options[:indent] || 0
      margin = options[:margin] || 0
      super(indent, margin)
      target = options[:target]
      if target
        @target = target.__class__ == String ? JVM::XmlMarkup.new(target) : target
      else
        @target = JVM::XmlMarkup.new("")
      end
    end

    # Return the target of the builder.
    def target!
      @target.__class__ == JVM::XmlMarkup ? @target.toString : @target
    end
  end
end
