package visitors.api;

import org.apache.tika.mime.MediaType;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Definition for builder of ProgLang visitor implementation.
 */
public interface ProgLangsVisitorBuilder extends VisitorBuilder {

    /**
     * Annotates this builder so that it can be injected wherever it is required.
     * Placeholders for injection are fields, parameters, and methods.
     */
    @Qualifier
    @Target({FIELD, PARAMETER, METHOD})
    @Retention(RUNTIME)
    @interface ProgLanguages {}

    ProgLangsVisitorBuilder setMediaTypes(List<MediaType> mediaTypes);
}
