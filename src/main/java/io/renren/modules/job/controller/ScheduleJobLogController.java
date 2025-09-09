/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.job.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.job.entity.ScheduleJobLogEntity;
import io.renren.modules.job.service.ScheduleJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/scheduleLog")
@Api(tags = "定时任务日志")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	@ApiOperation("定时任务日志列表")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = scheduleJobLogService.queryPage(params);
		
		return R.success(page);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	@ApiOperation("定时任务日志信息")
	public R info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);
		
		return R.success(log);
	}
}
