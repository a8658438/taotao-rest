package com.taotao.rest.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;

/**
 * 内容管理controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	/**
	 * 获取内容接口数据
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/list/{categoryId}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable Long categoryId){
		//接口数据
		try {
			List<TbContent> list = contentService.getContentList(categoryId);
			return TaotaoResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
