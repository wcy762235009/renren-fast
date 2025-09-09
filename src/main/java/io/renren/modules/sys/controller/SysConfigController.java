/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.controller;


import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysConfigEntity;
import io.renren.modules.sys.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import static io.renren.common.utils.R.success;
import static io.renren.common.utils.R.error;

/**
 * 系统配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/config")
@Api(tags = "系统配置信息")
public class SysConfigController extends AbstractController {
	@Autowired
	private SysConfigService sysConfigService;
	
	/**
	 * 所有配置列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:config:list")
	@ApiOperation("所有配置列表")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysConfigService.queryPage(params);

		return success(page);
	}
	
	
	/**
	 * 配置信息
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	@ApiOperation("配置信息")
	public R info(@PathVariable("id") Long id){
		SysConfigEntity config = sysConfigService.getById(id);
		
		return success(config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@PostMapping("/save")
	@RequiresPermissions("sys:config:save")
	@ApiOperation("保存配置")
	public R save(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);

		sysConfigService.saveConfig(config);
		
		return success();
	}
	
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@PostMapping("/update")
	@RequiresPermissions("sys:config:update")
	@ApiOperation("修改配置")
	public R update(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);
		
		sysConfigService.update(config);
		
		return success();
	}
	
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@PostMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	@ApiOperation("删除配置")
	public R delete(@RequestBody Long[] ids){
		sysConfigService.deleteBatch(ids);
		
		return success();
	}

}
