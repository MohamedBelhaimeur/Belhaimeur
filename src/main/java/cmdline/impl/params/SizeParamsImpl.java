package cmdline.impl.params;

import cmdline.api.params.CommonParams;
import cmdline.api.params.FileParams;
import cmdline.api.params.SizeParams;
import cmdline.impl.params.CommonParamsImpl;
import cmdline.impl.params.FileParamsImpl;
import cmdline.impl.common.SizeUnit;
import com.beust.jcommander.ParametersDelegate;

import java.nio.file.Path;
import java.util.List;

/**
 * Defines specific parameters for size command line.
 * Reuses {@link CommonParams} and {@link FileParams}
 */
final class SizeParamsImpl implements SizeParams {

    @ParametersDelegate
    private final CommonParams commonParams  = ParamsFactory.instance().createCommonParams();

    @ParametersDelegate
    private final FileParams fileParams = ParamsFactory.instance().createFileParams();

    @Override
    public List<Path> getPaths() {
        return commonParams.getPaths();
    }

    @Override
    public SizeUnit getSizeUnit() {
        return commonParams.getSizeUnit();
    }

    @Override
    public boolean isHelp() {
        return commonParams.isHelp();
    }

    @Override
    public boolean isFilesOnly() {
        return fileParams.isFilesOnly();
    }

    @Override
    public boolean isIncludeDirectories() {
        return fileParams.isIncludeDirectories();
    }
}
