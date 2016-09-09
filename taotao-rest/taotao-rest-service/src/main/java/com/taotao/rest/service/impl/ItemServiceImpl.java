package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.rest.redis.annotation.RedisHandle;
import com.taotao.rest.redis.constant.RedisConstant;
import com.taotao.rest.service.ItemService;

/**
 * 商品查询服务的实现类
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	@RedisHandle(key=RedisConstant.ITEM_REDIS_KEY+":?:info",expiryDate=RedisConstant.ITEM_REDIS_EXPIRY)
	public TaotaoResult getItemBaseInfo(Long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return TaotaoResult.ok(item);
	}

}
