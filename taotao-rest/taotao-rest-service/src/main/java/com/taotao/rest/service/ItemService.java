package com.taotao.rest.service;

import com.taotao.common.utils.TaotaoResult;

/**
 * 提供查询商品的相关服务
 * @author Administrator
 *
 */
public interface ItemService {
	/**
	 * 根据商品ID查询商品的基本信息
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemBaseInfo(Long itemId);
	/**
	 * 根据商品ID查询商品的描述信息
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemDesc(Long itemId);
	/**
	 * 根据商品ID查询商品的配置参数
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemParam(Long itemId);
}
