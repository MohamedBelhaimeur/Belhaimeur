package cmdline.impl.params;

import cmdline.api.params.UnitParams;
import cmdline.impl.common.SizeUnit;
import cmdline.impl.common.SizeUnitValidator;
import com.beust.jcommander.Parameter;

/**
 * Defines a single option for unit in which to display sizes.
 */
final class UnitParamsImpl implements UnitParams {
    @Parameter(names = {"-u", "--unit"}, description = "unit in which to display the size",
            converter = SizeUnitValidator.class,  validateWith = SizeUnitValidator.class)
    private SizeUnit sizeUnit = SizeUnit.BYTES;

    @Override
    public SizeUnit getSizeUnit() {
        return sizeUnit;
    }
}
