package logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLogs {
    private static Logger logger = LogManager.getRootLogger();
    public static void main(String[] args) {
        TestLogs obj = new TestLogs();
        obj.metodo();
    }

    private void metodo() {
        logger.debug("Estaba ejecutando y paso algo");
    }
}
