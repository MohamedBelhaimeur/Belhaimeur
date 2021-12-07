package cmdline.impl.params;

import cmdline.api.params.BaseParams;
import cmdline.api.params.CommonParams;
import cmdline.api.params.UnitParams;
import cmdline.impl.common.SizeUnit;
import com.beust.jcommander.ParametersDelegate;

import java.nio.file.Path;
import java.util.List;

/**
 * Defines common parameters for command lines.
 * These parameters include base and unit parameters.
 */
final class CommonParamsImpl implements CommonParams {

    @ParametersDelegate
    private final BaseParams baseParams = ParamsFactory.instance().createBaseParams();

    @ParametersDelegate
    private final UnitParams unitParam = ParamsFactory.instance().createUnitParams();

    @Override
    public List<Path> getPaths() {
        return baseParams.getPaths();
    }

    @Override
    public SizeUnit getSizeUnit() {
        return unitParam.getSizeUnit();
    }

    @Override
    public boolean isHelp() {
        return baseParams.isHelp();
    }
}
