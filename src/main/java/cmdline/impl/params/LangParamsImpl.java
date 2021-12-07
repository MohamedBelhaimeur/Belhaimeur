package cmdline.impl.params;

import cmdline.api.params.LangParams;
import cmdline.impl.proglangs.ProgLangsValidator;
import com.beust.jcommander.Parameter;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.tika.mime.MediaType;
import utils.properties.PropertiesConfigExplorer;
import utils.properties.PropertiesConfigLoader;
import utils.cmdhelper.WSSplitter;

import java.util.ArrayList;
import java.util.List;

final class LangParamsImpl implements LangParams {

    @Parameter(names = {"-l", "--languages"}, variableArity = true,
            description = "space-separated list of prog. languages to consider for filtering (see supported languages below).",
            validateWith = ProgLangsValidator.class,
            converter = ProgLangsValidator.class,
            splitter = WSSplitter.class)
    private List<List<MediaType>> mediaTypes;

    @Override
    public List<MediaType> getMediaTypes() {
        if (mediaTypes != null)
            return mediaTypes.stream().reduce(new ArrayList<>(), (l1, l2) -> {
                l1.addAll(l2);
                return l1;
            });
        else
            return loadSupportedMediaTypes();
    }

    private List<MediaType> loadSupportedMediaTypes() {
        List<MediaType> mTypes = new ArrayList<>();
        PropertiesConfigLoader configLoader = PropertiesConfigLoader.getInstance();
        try {
            Configuration config = configLoader.setFileName(ProgLangsValidator.CONTENTTYPES_PROPERTIES).getConfiguration();
            PropertiesConfigExplorer explorer = PropertiesConfigExplorer.getInstance().setConfig(config);
            mTypes.addAll(explorer.getValues().stream().map(MediaType::parse).toList());
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return mTypes;
    }
}
