package com.scottejames.aoc.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
    private static final Logger LOG = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        System.err.println("Start");
        System.err.println("Initialisied Logger");
        LOG.trace("TRACE");
        LOG.debug("DEBUG");
        LOG.info("INFO");
        LOG.warn("WARN");
        LOG.error("ERROR");

        System.err.println("END");
    }

}
