package cmdline.impl.params;

import cmdline.api.params.FileParams;
import com.beust.jcommander.Parameter;

final class FileParamsImpl implements FileParams {
    @Parameter(names = {"-fs", "--include-files"}, description = "include files sizes only in total, " +
            "showing each directory total as contained files or subdirectories' total." +
            " This implies that an empty directory's size is 0. This option is mutually exclusive with -ds.")
    boolean filesOnly = false;

    @Parameter(names = {"-ds", "--include-directories"}, description = "include directories sizes also in total, " +
            "showing each directory's own size. This option is mutually exclusive with -fs.")
    boolean includeDirectories = false;

    @Override
    public boolean isFilesOnly() {
        return filesOnly;
    }

    @Override
    public boolean isIncludeDirectories() {
        return includeDirectories;
    }
}
