import java.io.IOException;

import org.jruby.Ruby;
import org.jruby.runtime.load.BasicLibraryService;
import in.c42.nativebuilder.MapUtils;


public class NativeBuilderService implements BasicLibraryService {
    public boolean basicLoad(final Ruby runtime) throws IOException {
        return true;
    }
}
