package com.taotao.rest.service;

import java.util.List;

import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;

/**
 * 商品分类service
 * @author Administrator
 *
 */
public interface ItemCatService {
	/**
	 * 查询商品分类数据，并返回树结构
	 * @return
	 */
	CatResult getItemCatList();
}
