package visitors.impl.proglangs;

import com.google.inject.AbstractModule;
import visitors.api.ProgLangsVisitorBuilder;

/**
 * Binds annotated {@link ProgLangsVisitorBuilder} interface to its implementation, for dependency injection.
 * @see <a href="https://github.com/google/guice/wiki/GettingStarted">Guice documentation</a>.
 */
public final class ProgLangsVisitorBuilderModule extends AbstractModule {

    public ProgLangsVisitorBuilderModule() {
        super();
    }

    @Override
    protected void configure() {
        bind(ProgLangsVisitorBuilder.class)
                .annotatedWith(ProgLangsVisitorBuilder.ProgLanguages.class)
                .to(ProgLangsVisitorBuilderImpl.class);
    }
}
