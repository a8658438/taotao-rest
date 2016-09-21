package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.redis.annotation.RedisHandle;
import com.taotao.redis.constant.RedisConstant;
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
	@Autowired
	private TbItemDescMapper descMapper;
	@Autowired
	private TbItemParamItemMapper paramItemMapper;
	
	@Override
	@RedisHandle(key=RedisConstant.ITEM_REDIS_KEY+":?:info",expiryDate=RedisConstant.ITEM_REDIS_EXPIRY)
	public TaotaoResult getItemBaseInfo(Long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return TaotaoResult.ok(item);
	}

	@Override
	@RedisHandle(key=RedisConstant.ITEM_REDIS_KEY+":?:desc",expiryDate=RedisConstant.ITEM_REDIS_EXPIRY)
	public TaotaoResult getItemDesc(Long itemId) {
		TbItemDesc desc = descMapper.selectByPrimaryKey(itemId);
		return TaotaoResult.ok(desc);
	}

	@Override
	@RedisHandle(key=RedisConstant.ITEM_REDIS_KEY+":?:param",expiryDate=RedisConstant.ITEM_REDIS_EXPIRY)
	public TaotaoResult getItemParam(Long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		 List<TbItemParamItem> param = paramItemMapper.selectByExampleWithBLOBs(example);
		return param != null && param.size() > 0 ? TaotaoResult.ok(param.get(0)) : TaotaoResult.ok();
	}

}
