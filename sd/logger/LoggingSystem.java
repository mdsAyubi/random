import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

public class LoggingSystem {
    public static void main(final String[] args) {

    }
}

class LoggerFactory {

    ConfigurationManager configurationManager;
    Map<String, Logger> loggerMap;

    public Logger getLogger(String name) {
        return loggerMap.get(name);
    }

    public LoggerFactory(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
        this.loggerMap = configurationManager.configure();
    }
}

interface ConfigurationManager {
    public Map<String, Logger> configure();
}

class JavaBasedConfiguration implements ConfigurationManager {

    String fileName;

    public Map<String, Logger> configure() {
        Map<String, Logger> map = new HashMap<>();
        Appender fileAppender = null;
        try {
            fileAppender = new FileAppender(new ConsoleLogFormatter(), new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Appender consoleAppender = new ConsoleAppender();
        map.put("root", new Logger(Level.INFO, new LevelBasedLogFilter(Level.INFO),
                new LogHandler(List.of(consoleAppender, fileAppender), 1024)));
        return map;
    }
}

class XMLBasedConfigurationManager implements ConfigurationManager {

    @Override
    public Map<String, Logger> configure() {
        // TODO Auto-generated method stub
        return null;
    }

}

class Logger {

    Level level;
    Filter filter;
    LogHandler logHandler;

    public Logger(Level level, Filter filter, LogHandler logHandler) {
        this.level = level;
        this.filter = filter;
        this.logHandler = logHandler;
    }

    public void trace(final String message) {
        log(new LogRecord(Level.TRACE, message));
    }

    private void log(final LogRecord logRecord) {
        if (filter.isLoggable(logRecord)) {
            logHandler.handle(logRecord);
        }
    }

    public void debug(final String message) {
        log(new LogRecord(Level.DEBUG, message));
    }

    public void info(final String message) {
        log(new LogRecord(Level.INFO, message));
    }

    public void warn(final String message) {
        log(new LogRecord(Level.WARN, message));
    }

    public void error(final String message) {
        log(new LogRecord(Level.ERROR, message));
    }

    public void fatal(final String message) {
        log(new LogRecord(Level.FATAL, message));
    }

}

enum Level {
    TRACE(1), DEBUG(2), INFO(3), WARN(4), ERROR(5), FATAL(6);

    int level;

    Level(final int level) {
        this.level = level;
    }

    /**
     * If this level is higher than that level
     * 
     * @param that
     * @return
     */
    public boolean isHigher(final Level that) {
        return this.level > that.level;
    }

    /**
     * If this level is lower than that level
     * 
     * @param that
     * @return
     */
    public boolean isLower(final Level that) {
        return this.level <= that.level;
    }
}

interface Filter {
    List<LogRecord> filter(List<LogRecord> logRecords);

    boolean isLoggable(LogRecord logRecord);
}

class LevelBasedLogFilter implements Filter {

    Level level;

    public LevelBasedLogFilter(final Level level) {
        this.level = level;
    }

    @Override
    public List<LogRecord> filter(final List<LogRecord> logRecords) {
        return logRecords.stream().filter(this::isLoggable).collect(Collectors.toList());
    }

    @Override
    public boolean isLoggable(final LogRecord logRecord) {
        return level.isLower(logRecord.level);
    }

}

class LogRecord {

    Level level;
    String message;

    public LogRecord(final Level level, final String message) {
        this.level = level;
        this.message = message;
    }

}

class LogHandler implements Runnable {
    List<Appender> appenders;
    private Queue<LogRecord> queue;
    int bufferLength;

    public void handle(final LogRecord logRecord) {
        queue.offer(logRecord);
    }

    public LogHandler(List<Appender> appenders, int bufferLength) {
        this.appenders = appenders;
        this.bufferLength = bufferLength;
        queue = new ArrayBlockingQueue<>(bufferLength);
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            LogRecord logRecord = queue.poll();
            appenders.forEach(appender -> appender.append(logRecord));
        }
    }
}

interface Appender {
    void append(LogRecord logRecord);
}

class FileAppender implements Appender {

    LogFormatter logFormatter;
    File file;
    BufferedWriter out;

    @Override
    public void append(final LogRecord logRecord) {
        try {
            out.write(logFormatter.format(logRecord));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileAppender(LogFormatter logFormatter, File file) throws IOException {
        this.logFormatter = logFormatter;
        this.file = file;
        this.out = new BufferedWriter(new FileWriter(file));
    }

}

class ConsoleAppender implements Appender {

    LogFormatter logFormatter;

    @Override
    public void append(final LogRecord logRecord) {
        if (logRecord.level.isLower(Level.INFO)) {
            System.out.println(logFormatter.format(logRecord));
        } else {
            System.err.println(logFormatter.format(logRecord));
        }

    }

}

class DBAppender implements Appender {

    @Override
    public void append(final LogRecord logRecord) {

    }

}

interface LogFormatter {
    String format(LogRecord logRecord);
}

class ConsoleLogFormatter implements LogFormatter {

    @Override
    public String format(LogRecord logRecord) {
        return "[" + LocalDateTime.now() + "]" + logRecord.message;
    }

}