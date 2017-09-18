package com.test.util.logger;

import org.slf4j.Logger;
import org.slf4j.Marker;

import java.util.function.Supplier;

/**
 * Created by jiyanbin on 15/7/21.
 */
public class LoggerWrapper {

    private Logger logger;

    public LoggerWrapper(Logger logger) {
        this.logger = logger;
    }


    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }


    public void trace(Supplier<String> supplier) {
        if (logger.isTraceEnabled())
            logger.trace(supplier.get());
    }


    public void trace(Supplier<String> supplier, Object arg) {
        if (logger.isTraceEnabled())
            logger.trace(supplier.get(), arg);
    }


    public void trace(Supplier<String> supplier, Object arg1, Object arg2) {
        if (logger.isTraceEnabled())
            logger.trace(supplier.get(), arg1,arg2);
    }


    public void trace(Supplier<String> supplier, Object... arguments) {
        if (logger.isTraceEnabled())
            logger.trace(supplier.get(), arguments);
    }


    public void trace(Supplier<String> supplier, Throwable t) {
        if (logger.isTraceEnabled())
            logger.trace(supplier.get(), t);
    }


    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled();
    }


    public void trace(Marker marker, Supplier<String> supplier) {
        if (logger.isTraceEnabled())
            logger.trace(marker, supplier.get());
    }


    public void trace(Marker marker, Supplier<String> supplier, Object arg) {
        if (logger.isTraceEnabled())
            logger.trace(marker, supplier.get(),arg);
    }


    public void trace(Marker marker, Supplier<String> supplier, Object arg1, Object arg2) {
        if (logger.isTraceEnabled())
            logger.trace(marker, supplier.get(),arg1,arg2);
    }


    public void trace(Marker marker, Supplier<String> supplier, Object... argArray) {
        if (logger.isTraceEnabled())
            logger.trace(marker, supplier.get(),argArray);
    }


    public void trace(Marker marker, Supplier<String> supplier, Throwable t) {
        if (logger.isTraceEnabled())
            logger.trace(marker, supplier.get(), t);
    }


    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }


    public void debug(Supplier<String> supplier) {
        if (logger.isDebugEnabled())
            logger.debug(supplier.get());
    }


    public void debug(Supplier<String> supplier, Object arg) {
        if (logger.isDebugEnabled())
            logger.debug(supplier.get(), arg);
    }


    public void debug(Supplier<String> supplier, Object arg1, Object arg2) {
        if (logger.isDebugEnabled())
            logger.debug(supplier.get(),arg1,arg2);
    }


    public void debug(Supplier<String> supplier, Object... arguments) {
        if (logger.isDebugEnabled())
            logger.debug(supplier.get(), arguments);
    }


    public void debug(Supplier<String> supplier, Throwable t) {
        if (logger.isDebugEnabled())
            logger.debug(supplier.get(), t);
    }


    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled();
    }


    public void debug(Marker marker, Supplier<String> supplier) {
        if (logger.isDebugEnabled())
            logger.debug(marker, supplier.get());
    }


    public void debug(Marker marker, Supplier<String> supplier, Object arg) {
        if (logger.isDebugEnabled())
            logger.debug(marker, supplier.get(),arg);
    }


    public void debug(Marker marker, Supplier<String> supplier, Object arg1, Object arg2) {
        if (logger.isDebugEnabled())
            logger.debug(marker, supplier.get(),arg1,arg2);
    }


    public void debug(Marker marker, Supplier<String> supplier, Object... arguments) {
        if (logger.isDebugEnabled())
            logger.debug(marker, supplier.get(),arguments);
    }


    public void debug(Marker marker, Supplier<String> supplier, Throwable t) {
        if (logger.isDebugEnabled())
            logger.debug(marker, supplier.get(), t);
    }


    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }


    public void info(Supplier<String> supplier) {
        if (logger.isInfoEnabled())
            logger.info(supplier.get());
    }


    public void info(Supplier<String> supplier, Object arg) {
        if (logger.isInfoEnabled())
            logger.info(supplier.get(), arg);
    }


    public void info(Supplier<String> supplier, Object arg1, Object arg2) {
        if (logger.isInfoEnabled())
            logger.info(supplier.get(),arg1,arg2);
    }


    public void info(Supplier<String> supplier, Object... arguments) {
        if (logger.isInfoEnabled())
            logger.info(supplier.get(), arguments);
    }


    public void info(Supplier<String> supplier, Throwable t) {
        if (logger.isInfoEnabled())
            logger.info(supplier.get(), t);
    }


    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled();
    }


    public void info(Marker marker, Supplier<String> supplier) {
        if (logger.isInfoEnabled())
            logger.info(marker, supplier.get());
    }


    public void info(Marker marker, Supplier<String> supplier, Object arg) {
        if (logger.isInfoEnabled())
            logger.info(marker, supplier.get(),arg);
    }


    public void info(Marker marker, Supplier<String> supplier, Object arg1, Object arg2) {
        if (logger.isInfoEnabled())
            logger.info(marker, supplier.get(),arg1,arg2);
    }


    public void info(Marker marker, Supplier<String> supplier, Object... arguments) {
        if (logger.isInfoEnabled())
            logger.info(marker, supplier.get(),arguments);
    }


    public void info(Marker marker, Supplier<String> supplier, Throwable t) {
        if (logger.isInfoEnabled())
            logger.info(marker, supplier.get(), t);
    }


    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }


    public void warn(Supplier<String> supplier) {
        if (logger.isWarnEnabled())
            logger.warn(supplier.get());
    }


    public void warn(Supplier<String> supplier, Object arg) {
        if (logger.isWarnEnabled())
            logger.warn(supplier.get(), arg);
    }


    public void warn(Supplier<String> supplier, Object... arguments) {
        if (logger.isWarnEnabled())
            logger.warn(supplier.get(), arguments);
    }


    public void warn(Supplier<String> supplier, Object arg1, Object arg2) {
        if (logger.isWarnEnabled())
            logger.warn(supplier.get(),arg1,arg2);
    }


    public void warn(Supplier<String> supplier, Throwable t) {
        if (logger.isWarnEnabled())
            logger.warn(supplier.get(), t);
    }


    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled();
    }


    public void warn(Marker marker, Supplier<String> supplier) {
        if (logger.isWarnEnabled())
            logger.warn(marker, supplier.get());
    }


    public void warn(Marker marker, Supplier<String> supplier, Object arg) {
        if (logger.isWarnEnabled())
            logger.warn(marker, supplier.get(),arg);
    }


    public void warn(Marker marker, Supplier<String> supplier, Object arg1, Object arg2) {
        if (logger.isWarnEnabled())
            logger.warn(marker, supplier.get(),arg1,arg2);
    }


    public void warn(Marker marker, Supplier<String> supplier, Object... arguments) {
        if (logger.isWarnEnabled())
            logger.warn(marker, supplier.get(),arguments);
    }


    public void warn(Marker marker, Supplier<String> supplier, Throwable t) {
        if (logger.isWarnEnabled())
            logger.warn(marker, supplier.get(), t);
    }


    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }


    public void error(Supplier<String> supplier) {
        if (logger.isErrorEnabled())
            logger.error(supplier.get());
    }


    public void error(Supplier<String> supplier, Object arg) {
        if (logger.isErrorEnabled())
            logger.error(supplier.get(), arg);
    }


    public void error(Supplier<String> supplier, Object arg1, Object arg2) {
        if (logger.isErrorEnabled())
            logger.error(supplier.get(),arg1,arg2);
    }


    public void error(Supplier<String> supplier, Object... arguments) {
        if (logger.isErrorEnabled())
            logger.error(supplier.get(), arguments);
    }


    public void error(Supplier<String> supplier, Throwable t) {
        if (logger.isErrorEnabled())
            logger.error(supplier.get(), t);
    }


    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled();
    }


    public void error(Marker marker, Supplier<String> supplier) {
        if (logger.isErrorEnabled())
            logger.error(marker, supplier.get());
    }


    public void error(Marker marker, Supplier<String> supplier, Object arg) {
        if (logger.isErrorEnabled())
            logger.error(marker, supplier.get(),arg);
    }


    public void error(Marker marker, Supplier<String> supplier, Object arg1, Object arg2) {
        if (logger.isErrorEnabled())
            logger.error(marker, supplier.get(),arg1,arg2);
    }


    public void error(Marker marker, Supplier<String> supplier, Object... arguments) {
        if (logger.isErrorEnabled())
            logger.error(marker, supplier.get(),arguments);
    }


    public void error(Marker marker, Supplier<String> supplier, Throwable t) {
        if (logger.isErrorEnabled())
            logger.error(marker, supplier.get(), t);
    }
}
