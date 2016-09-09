package com.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/itemcat")
public class ItemCatController {
	@Autowired
	private ItemCatService catService;
	
	/**
	 * 查询商品分类列表
	 * @return
	 */
	@RequestMapping("/all")
	@ResponseBody
	public Object getItemCatList(String callback){
		CatResult result = catService.getItemCatList();
		
		//此方法需要spring mvc 4.1才支持
		MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
	}
}
