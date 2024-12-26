package org.slf4j.helpers;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
public class NOPLoggerFactory implements ILoggerFactory {
    @Override
    public Logger getLogger(String str) {
        return NOPLogger.NOP_LOGGER;
    }
}
