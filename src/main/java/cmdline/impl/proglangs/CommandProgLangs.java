package cmdline.impl.proglangs;

import cmdline.api.params.ProgLangParams;
import cmdline.impl.params.ParamsFactory;
import visitors.api.VisitorBuilderDirector;
import cmdline.impl.common.CommandBase;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.google.inject.Guice;
import com.google.inject.Injector;
import visitors.api.Visitor;
import visitors.impl.proglangs.ProgLangsVisitorBuilderModule;

/**
 *  Stats on files of particular programming languages (in the large sense).
 *  The files or directories are provided as arguments.
 *   The stats are about:
 *   - number of files for each prog. language
 *   - total number of lines (without exclusion)
 *   - total size
 */
@Parameters(commandNames = {"langs"}, commandDescription = "Shows stats of files from particular prog. languages (in the large sense, e.g., Java, Markdown, PlantUML). " +
        "Displays stats about: number of files, total number of lines (without exclusion), total size.")
public final class CommandProgLangs extends CommandBase {

    private static final String NAME = "langs";

    @ParametersDelegate
    private final ProgLangParams params = ParamsFactory.instance().createProgLangParams();

    public CommandProgLangs() {
        super();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute(JCommander jc) {
        if (isDisplayHelp(params.isHelp(), jc)) {
            System.out.println("Supported languages: " + ProgLangsValidator.getMediaTypeKeys());
            return;
        }

        buildComponentTree(params.getPaths());

        Injector injector = Guice.createInjector(new ProgLangsVisitorBuilderModule());
        VisitorBuilderDirector<ProgLangParams> director = injector.getInstance(ProgLangsVisitorBuilderDirector.class);
        Visitor visitor = director.buildVisitor(params).getVisitor();
        components.forEach(c -> c.accept(visitor));
        visitor.print();
    }
}
