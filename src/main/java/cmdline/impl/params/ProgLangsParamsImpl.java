package cmdline.impl.params;

import cmdline.api.params.CommonParams;
import cmdline.api.params.LangParams;
import cmdline.api.params.ProgLangParams;
import cmdline.impl.common.SizeUnit;
import com.beust.jcommander.ParametersDelegate;
import org.apache.tika.mime.MediaType;

import java.nio.file.Path;
import java.util.List;

/**
 * Defines parameters for the lang command.
 * The visibility of this class allows reuse inside its package only.
 */
final class ProgLangsParamsImpl implements ProgLangParams {
    @ParametersDelegate
    private final CommonParams commonParams = ParamsFactory.instance().createCommonParams();
    @ParametersDelegate
    private final LangParams langParam = ParamsFactory.instance().createLangParams();

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
    public List<MediaType> getMediaTypes() {
        return langParam.getMediaTypes();
    }
}
