package cmdline.api.params;

/**
 * Common definition for options on how to handle files specification.
 */
public interface FileParams {
    boolean isFilesOnly();
    boolean isIncludeDirectories();
}
