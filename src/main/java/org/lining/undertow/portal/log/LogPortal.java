package org.lining.undertow.portal.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description:
 * date 2018/1/25
 *
 * @author lining1
 * @version 1.0.0
 */
public class LogPortal {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogPortal.class);

    public static void log2File(String json) {
        LOGGER.info(json);
    }
}
