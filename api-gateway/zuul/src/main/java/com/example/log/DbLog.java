package com.example.log;

import com.example.log.feign.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xianzhi.wang
 * @date 2017/12/5 -17:32
 */
public class DbLog extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbLog.class);
    private static DbLog dblog = null;
    private static BlockingQueue<LogInfo> logInfoQueue = new LinkedBlockingQueue<LogInfo>(1024);
    private ILogService logService;

    private DbLog() {
        super("CLogWriterThread");
    }

    public static synchronized DbLog getInstance() {
        if (dblog == null) {
            dblog = new DbLog();
        }
        return dblog;
    }

    public ILogService getLogService() {
        return logService;
    }

    public DbLog setLogService(ILogService logService) {
        if (this.logService == null) {
            this.logService = logService;
        }
        return this;
    }

    public void offerQueue(LogInfo logInfo) {
        try {
            logInfoQueue.offer(logInfo);
        } catch (Exception e) {
            LOGGER.error("日志写入失败", e);
        }
    }

    @Override
    public void run() {
        //缓冲队列
        List<LogInfo> bufferedLogList = new ArrayList<>();
        while (true) {
            try {
                bufferedLogList.add(logInfoQueue.take());
                logInfoQueue.drainTo(bufferedLogList);
                if (bufferedLogList != null && bufferedLogList.size() > 0) {
                    // 写入日志
                    for (LogInfo log : bufferedLogList) {
                        logService.saveLog(log);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 防止缓冲队列填充数据出现异常时不断刷屏
                try {
                    Thread.sleep(1000);
                } catch (Exception eee) {
                }
            } finally {
                if (bufferedLogList != null && bufferedLogList.size() > 0) {
                    try {
                        bufferedLogList.clear();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}
