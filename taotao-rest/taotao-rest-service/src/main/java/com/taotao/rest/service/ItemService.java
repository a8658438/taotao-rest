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
}
