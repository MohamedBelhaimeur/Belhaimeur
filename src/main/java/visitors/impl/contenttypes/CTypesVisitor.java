package visitors.impl.contenttypes;

import filesystem.api.Component;
import filesystem.api.Composite;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.tika.mime.MediaType;
import org.slf4j.Logger;
import utils.display.CommandLineTable;
import utils.logger.CmdLogger;
import visitors.impl.common.VisitorImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static utils.cmdhelper.SizeFormatter.formatSize;
import static utils.cmdhelper.SizeFormatter.translateSizeInUnit;

final class CTypesVisitor extends VisitorImpl {

    public static final String TYPE = "Content Type";

    private final CommandLineTable cmdLineTable;
    // Maps MediaType to <nbFiles, totalSize>
    private final Map<MediaType, Pair<Long, Long>> mTypeNbSize;
    private final Logger logger;

    CTypesVisitor(){
        super();
        cmdLineTable = new CommandLineTable();
        mTypeNbSize = new HashMap<>();
        logger = CmdLogger.instance().getLogger(this.getClass());
    }

    @Override
    public void visit(Component visitable) {
        try {
            long size = visitable.getSize();
            MediaType mtype = visitable.getContentType();
            mTypeNbSize.merge(mtype, Pair.of(1L, size),
                    (oldP, newP) -> Pair.of(oldP.getLeft() + newP.getLeft(),
                    oldP.getRight() + newP.getRight()));
            logger.info("Visited {}", visitable.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Composite<Component> visitable) {
        visitable.getChildren().forEach(c -> c.accept(this));
    }

    @Override
    public void print() {
        initCmdLineTableHeaders();
        cmdLineTable.setShowVerticalLines(true);
        Pair<Long, Long> nbFilesNSize = mTypeNbSize.entrySet().stream().map(e -> {
            cmdLineTable.addRow(e.getKey().toString(),
                    String.valueOf(e.getValue().getLeft()),
                    formatSize(translateSizeInUnit(e.getValue().getRight(), sizeUnit)));
            return e.getValue();
        }).reduce(Pair.of(0L, 0L), (p1, p2) ->
                Pair.of(p1.getLeft() + p2.getLeft(), p1.getRight() + p2.getRight()));
        cmdLineTable.addFinalRow(TOTAL + WS + unitStr,
                String.valueOf(nbFilesNSize.getLeft()),
                        formatSize(translateSizeInUnit(nbFilesNSize.getRight(), sizeUnit)));
        cmdLineTable.print();
    }

    private void initCmdLineTableHeaders() {
        cmdLineTable.setHeaders(TYPE, NB_FILES, TOTAL_SIZE + WS + unitStr);
    }

}
