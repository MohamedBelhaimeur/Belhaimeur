package cmdline.impl.contenttypes;

import cmdline.api.params.CommonParams;
import cmdline.impl.common.CommandBase;
import cmdline.impl.params.ParamsFactory;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.google.inject.Guice;
import com.google.inject.Injector;
import visitors.api.Visitor;
import visitors.impl.contenttypes.CTypesVisitorBuilderModule;

/**
 * Content types external stats command line implementation.
 * External stats are about:
 * - number of files of each content type
 * - total size for all files of each content type
 * - sum of the above two numbers
 */
@Parameters(commandNames = {"ctypes"}, commandDescription = "Shows external stats for files in a file tree. " +
        "Displays stats about: number of files of each encountered content type; total size for all files of each content type; " +
        "sum of the two previous numbers. " +
        "Default behavior includes only files sizes in the total, while showing each directory's own size.")
public final class CommandCTypes extends CommandBase {

    private static final String NAME = "ctypes";

    @ParametersDelegate
    private final CommonParams params = ParamsFactory.instance().createCommonParams();

    public CommandCTypes() {super();}

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute(JCommander jc) {
        if (isDisplayHelp(params.isHelp(), jc))
            return;

        buildComponentTree(params.getPaths());

        Injector injector = Guice.createInjector(new CTypesVisitorBuilderModule());
        CTypesVisitorBuilderDirector director = injector.getInstance(CTypesVisitorBuilderDirector.class);
        Visitor visitor = director.buildVisitor(params).getVisitor();
        components.forEach(c -> c.accept(visitor));
        visitor.print();
    }
}
