package PresentationLayer;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * The purpose of this class is to make an automatic writer to a log file regarding the error that occurs for the user.
 * In this way we will be able to examine what errors happens often.
 * @author Mia
 */

public class Log {

    private static final String FILENAME = "LogFile.log";
    private static final String FILEPATH = "C:/Exceptions/";

    private static final String PATH = FILEPATH + FILENAME;

    private Log() {
    }

    private static void log(Level lvl, String decription) throws Exception {

        Logger logger = Logger.getLogger(Log.class.getName());   // alle operationer på logger er thread safe

        FileHandler fh = new FileHandler(PATH, true);
        fh.setFormatter(new VerySimpleFormatter());
        logger.addHandler(fh);

        logger.setLevel(Level.FINEST);   // her sætter vi niveauet for logningen.

        logger.log(lvl ,  decription  );


        fh.close();

    }

    /**
     * @param description is the text that is written to the log file
     */
    public static void severe(String description ) {

        try {
            log(Level.SEVERE,description );
        } catch (Exception e) {
        }
    }

    public static void info(String description ) {

        try {
            log(Level.INFO,description );
        } catch (Exception e) {
        }
    }

    public static void finest(String description ) {

        try {
            log(Level.FINEST,description );
        } catch (Exception e) {
        }
    }
}


class VerySimpleFormatter extends Formatter {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    /**
     * @param record is an Log object from which we can achive information like the time and date and the level of the
     *               error, which we have placed for instance in the mappers.
     * @return a string with the record information
     */

    @Override
    public String format(final LogRecord record) {
        return String.format(
                "%1$s %2$-7s %3$s\n",
                new SimpleDateFormat(PATTERN).format(
                        new Date(record.getMillis())),
                record.getLevel().getName(),
                formatMessage(record));
    }
}