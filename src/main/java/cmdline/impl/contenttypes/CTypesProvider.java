package cmdline.impl.contenttypes;

import cmdline.api.commandline.CommandLine;
import cmdline.api.commandline.CommandLineProvider;

/**
 * A provider for the ctype command line.
 * @see CommandCTypes
 */
public final class CTypesProvider implements CommandLineProvider {
    @Override
    public CommandLine create() {
        return new CommandCTypes();
    }
}
