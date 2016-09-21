package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.redis.service.RedisService;

@Controller
@RequestMapping("/cache/sync/")
public class RedisController {
	@Autowired
	private RedisService redisService;
	
	/**
	 * 同步内容管理
	 * @param contentCId
	 * @return
	 */
	@RequestMapping("/content/{contentCId}")
	@ResponseBody
	public TaotaoResult syncContentCat(@PathVariable Long contentCId){
		TaotaoResult result = redisService.syncContent(contentCId);
		return result;
	}
}
