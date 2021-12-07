package visitors.impl.proglangs;

import filesystem.api.Component;
import filesystem.api.Composite;
import iterators.IteratorFactory;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.tika.mime.MediaType;
import org.slf4j.Logger;
import utils.display.CommandLineTable;
import utils.logger.CmdLogger;
import utils.properties.PropertiesConfigExplorer;
import utils.properties.PropertiesConfigLoader;
import visitors.impl.common.VisitorImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static utils.cmdhelper.SizeFormatter.formatSize;
import static utils.cmdhelper.SizeFormatter.translateSizeInUnit;

final class ProgLangsVisitor extends VisitorImpl {

    public static final String LANG = "Language";
    private static final String NB_LINES = "Lines";

    private final CommandLineTable cmdLineTable;
    //Maps MediaType to <nbFiles, nbLines, totalSize>
    private final Map<MediaType, Triple<Long, Long, Long>> mediaTypeFLS;
    private final Logger logger;
    private List<MediaType> mediaTypes;

    ProgLangsVisitor() {
        super();
        cmdLineTable = new CommandLineTable();
        mediaTypeFLS = new HashMap<>();
        logger = CmdLogger.instance().getLogger(this.getClass());
    }

    @Override
    public void visit(Component visitable) {
        Iterator<Component> iterator = IteratorFactory.getInstance().getIterator(visitable, mediaTypes);
        if (iterator.hasNext())
            updateFLSMap(visitable);
    }

    @Override
    public void visit(Composite<Component> visitable) {
        Component component;
        Iterator<Component> iterator = IteratorFactory.getInstance().getIterator(visitable, mediaTypes);
        while (iterator.hasNext()) {
            component = iterator.next();
            updateFLSMap(component);
        }
    }

    @Override
    public void print() {
        initCmdLineTableHeaders();
        cmdLineTable.setShowVerticalLines(true);
        try {
            PropertiesConfigExplorer explorer = PropertiesConfigExplorer.getInstance()
                    .setConfig(PropertiesConfigLoader.getInstance().getConfiguration());

            Triple<Long, Long, Long> data = mediaTypeFLS.entrySet().stream().map(e -> {
                cmdLineTable.addRow(explorer.getLang(e.getKey().toString()),
                        String.valueOf(e.getValue().getLeft()),
                        String.valueOf(e.getValue().getMiddle()),
                        formatSize(translateSizeInUnit(e.getValue().getRight(), sizeUnit)));
                return e.getValue();
            }).reduce(Triple.of(0L, 0L, 0L), (t1, t2) ->
                    Triple.of(t1.getLeft() + t2.getLeft(),
                            t1.getMiddle() + t2.getMiddle(),
                            t1.getRight() + t2.getRight())
            );
            cmdLineTable.addFinalRow(TOTAL + WS + unitStr,
                    String.valueOf(data.getLeft()),
                    String.valueOf(data.getMiddle()),
                    formatSize(translateSizeInUnit(data.getRight(), sizeUnit)));
            cmdLineTable.print();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    void setMediaTypes(List<MediaType> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    private void updateFLSMap(Component visitable) {
        try {
            long size = visitable.getSize();
            MediaType mtype = visitable.getContentType();
            long nbLines;
            try (Stream<String> input = Files.lines(visitable.getPath(), StandardCharsets.UTF_8)) {
                nbLines = input.count();
            }
            Triple<Long, Long, Long> newValue = mediaTypeFLS.computeIfAbsent(mtype, k ->
                    Triple.of(1L, nbLines, size)
            );
            if (newValue.getRight() != size && newValue.getMiddle() != nbLines)
                mediaTypeFLS.computeIfPresent(mtype, (k, v) ->
                        Triple.of(v.getLeft() + 1L, v.getMiddle() + nbLines, v.getRight() + size));
            logger.info("Visited {}", visitable.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCmdLineTableHeaders() {
        cmdLineTable.setHeaders(LANG, NB_FILES, NB_LINES, TOTAL_SIZE + WS + unitStr);
    }
}
