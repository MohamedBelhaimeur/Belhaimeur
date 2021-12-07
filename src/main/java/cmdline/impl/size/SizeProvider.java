package cmdline.impl.size;

import cmdline.api.commandline.CommandLine;
import cmdline.api.commandline.CommandLineProvider;

/**
 * A provider for the size command line.
 * @see CommandSize
 */
public final class SizeProvider implements CommandLineProvider {
    @Override
    public CommandLine create() {
        return new CommandSize();
    }
}
