package com.wallet.quartz.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.wallet.common.exception.job.TaskException;
import com.wallet.quartz.domain.SysJob;

/**
 * 定时任务调度信息信息 服务层
 * 
 * @author wallet
 */
public interface ISysJobService
{
    /**
     * 获取quartz调度器的计划任务
     * 
     * @param job 调度信息
     * @return 调度任务集合
     */
    public List<SysJob> selectJobList(SysJob job);

    public List<SysJob> selectJobAll();
    public SysJob selectJobById(Long jobId);
    public int updateJob(SysJob job);
    public int deleteJobById(Long jobId);
    public int insertJob(SysJob job);
}
