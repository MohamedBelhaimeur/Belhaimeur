package cmdline.api.params;

import org.apache.tika.mime.MediaType;

import java.util.List;

/**
 * Definition for programming language or file format parameter specification.
 */
public interface LangParams {
    List<MediaType> getMediaTypes();
}
