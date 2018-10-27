package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface ISysLogDao {
    /**
     * 保存日志信息
     */
    @Insert("insert into sys_log(id,visitTime,username,ip,method)" +
            "values(null,#{visitTime},#{username},#{ip},#{method})")
    void save(SysLog sysLog);
}
