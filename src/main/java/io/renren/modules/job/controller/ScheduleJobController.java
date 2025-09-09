/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.job.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.job.entity.ScheduleJobEntity;
import io.renren.modules.job.service.ScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/schedule")
@Api(tags = "定时任务")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:list")
	@ApiOperation("查询定时任务列表")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = scheduleJobService.queryPage(params);

		return R.success(page);
	}
	
	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	@RequiresPermissions("sys:schedule:info")
	@ApiOperation("定时任务详情")
	public R info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity schedule = scheduleJobService.getById(jobId);
		
		return R.success(schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@RequestMapping("/save")
	@RequiresPermissions("sys:schedule:save")
	@ApiOperation("保存定时任务")
	public R save(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
		
		scheduleJobService.saveJob(scheduleJob);
		
		return R.success();
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@RequestMapping("/update")
	@RequiresPermissions("sys:schedule:update")
	@ApiOperation("修改定时任务")
	public R update(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
				
		scheduleJobService.update(scheduleJob);
		
		return R.success();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:schedule:delete")
	@ApiOperation("删除定时任务")
	public R delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return R.success();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@RequestMapping("/run")
	@RequiresPermissions("sys:schedule:run")
	@ApiOperation("立即执行任务")
	public R run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		
		return R.success();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@RequestMapping("/pause")
	@RequiresPermissions("sys:schedule:pause")
	@ApiOperation("暂停定时任务")
	public R pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return R.success();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@RequestMapping("/resume")
	@RequiresPermissions("sys:schedule:resume")
	@ApiOperation("恢复定时任务")
	public R resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return R.success();
	}

}
