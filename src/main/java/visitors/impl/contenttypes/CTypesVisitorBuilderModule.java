package visitors.impl.contenttypes;

import com.google.inject.AbstractModule;
import visitors.api.CTypesVisitorBuilder;

/**
 * Binds annotated {@link CTypesVisitorBuilder} interface to its implementation, for dependency injection.
 * @see <a href="https://github.com/google/guice/wiki/GettingStarted">Guice documentation</a>.
 */
public final class CTypesVisitorBuilderModule extends AbstractModule {

    public CTypesVisitorBuilderModule() {
        super();
    }

    @Override
    protected void configure() {
        bind(CTypesVisitorBuilder.class)
                .annotatedWith(CTypesVisitorBuilder.FileContentType.class)
                .to(CTypesVisitorBuilderImpl.class);
    }
}
