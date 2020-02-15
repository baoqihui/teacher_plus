package com.hbq.teacher_plus.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.time.LocalDateTime;

public class P6SpyLogger implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {        return !"".equals(sql.trim()) ? "[ " + LocalDateTime.now() + " ] --- | took "
            + elapsed + "ms | " + category + " | connection " + connectionId + "\n "
            + sql + ";" : "";
    }
}
