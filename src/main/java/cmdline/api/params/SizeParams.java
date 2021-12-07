package cmdline.api.params;

/**
 * Definition of the API for specific parameters of the size command line.
 */
public interface SizeParams extends CommonParams {
     boolean isFilesOnly();
     boolean isIncludeDirectories();
}
