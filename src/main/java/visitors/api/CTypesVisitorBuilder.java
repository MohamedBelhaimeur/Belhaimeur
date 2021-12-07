package visitors.api;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Definition for builder of Content Type visitor implementation.
 */
public interface CTypesVisitorBuilder extends VisitorBuilder {
    /**
     * Annotates this builder so that it can be injected wherever it is required.
     * Placeholders for injection are fields, parameters, and methods.
     */
    @Qualifier
    @Target({FIELD, PARAMETER, METHOD})
    @Retention(RUNTIME)
    @interface FileContentType {}
}
