package cmdline.impl.proglangs;

import cmdline.api.commandline.CommandLine;
import cmdline.api.commandline.CommandLineProvider;

public final class ProgLangsProvider implements CommandLineProvider {
    @Override
    public CommandLine create() {
        return new CommandProgLangs();
    }
}
