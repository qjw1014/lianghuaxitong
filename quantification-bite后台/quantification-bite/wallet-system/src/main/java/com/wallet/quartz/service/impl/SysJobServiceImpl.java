package com.wallet.quartz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.quartz.domain.SysJob;
import com.wallet.quartz.mapper.SysJobMapper;
import com.wallet.quartz.service.ISysJobService;

/**
 * 定时任务调度信息 服务层
 * 
 * @author wallet
 */
@Service
public class SysJobServiceImpl implements ISysJobService
{
   

    @Autowired
    private SysJobMapper jobMapper;

    

    /**
     * 获取quartz调度器的计划任务列表
     * 
     * @param job 调度信息
     * @return
     */
    @Override
    public List<SysJob> selectJobList(SysJob job)
    {
        return jobMapper.selectJobList(job);
    }

    /**
     * 通过调度任务ID查询调度信息
     * 
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public SysJob selectJobById(Long jobId)
    {
        return jobMapper.selectJobById(jobId);
    }

	@Override
	public List<SysJob> selectJobAll() {
		return jobMapper.selectJobAll();
	}

	@Override
	public int updateJob(SysJob job) {
		return jobMapper.updateJob(job);
	}

	@Override
	public int deleteJobById(Long jobId) {
		return jobMapper.deleteJobById(jobId);
	}

	@Override
	public int insertJob(SysJob job) {
		return jobMapper.insertJob(job);
	}

    

    
}
