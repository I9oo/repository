package com.itheima.service;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface ISysLogService {
    /**
     * 保存日志信息
     */
    void save(SysLog sysLog);
}
