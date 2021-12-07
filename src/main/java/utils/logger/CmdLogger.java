package utils.logger;

import org.slf4j.LoggerFactory;

public final class CmdLogger {

    private static final CmdLogger INSTANCE = new CmdLogger();

    private CmdLogger() {}

    public static CmdLogger instance() {return INSTANCE;}

    public org.slf4j.Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
