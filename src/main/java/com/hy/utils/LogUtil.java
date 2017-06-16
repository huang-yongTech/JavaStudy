package com.hy.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by huangyong on 2017/6/16.
 * 日志类
 */
public class LogUtil {
    private LogUtil() {
    }

    public static Logger getLogger() {
        return Log.logger;
    }

    private static class Log {
        private static final Logger logger = LogManager.getLogger();
    }
}
