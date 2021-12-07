package cmdline.impl.proglangs;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.tika.mime.MediaType;
import utils.properties.PropertiesConfigExplorer;
import utils.properties.PropertiesConfigLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class ProgLangsValidator implements IStringConverter<List<MediaType>>, IParameterValidator {

    public static final String CONTENTTYPES_PROPERTIES = "contenttypes.properties";
    private static Configuration config;

    static {
        try {
            config = loadConfig();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getMediaTypeKeys() {
        return PropertiesConfigExplorer.getInstance().setConfig(config).getKeys();
    }

    private static Configuration loadConfig() throws ConfigurationException {
        PropertiesConfigLoader configLoader = PropertiesConfigLoader.getInstance();
        return configLoader.setFileName(CONTENTTYPES_PROPERTIES).getConfiguration();
    }

    @Override
    public void validate(String name, String value) {
        Iterator<String> keys = config.getKeys();
        String key;
        boolean found = false;
        while (keys.hasNext()) {
            key = keys.next();
            if (key.equalsIgnoreCase(value)) {
                found = true;
                break;
            }
        }
        if (!found)
            throw new ParameterException("Language " + value +
                    " is not supported. Use -help to get the list of supported languages.");
    }

    @Override
    public List<MediaType> convert(String value) {
        List<MediaType> mediaTypes = new ArrayList<>();
        String[] types = config.getStringArray(value.toUpperCase());
        if (types.length != 0) {
            Arrays.stream(types).forEach(t -> mediaTypes.add(MediaType.parse(t)));
        } else {
            String type = config.getString(value.toUpperCase());
            mediaTypes.add(MediaType.parse(type));
        }
        return mediaTypes;
    }
}
