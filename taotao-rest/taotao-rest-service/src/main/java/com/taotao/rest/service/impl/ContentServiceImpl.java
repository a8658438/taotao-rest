package com.taotao.rest.service.impl;

import java.util.List;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.redis.annotation.RedisHandle;
import com.taotao.rest.redis.annotation.RedisHandleParse;
import com.taotao.rest.redis.constant.RedisConstant;
import com.taotao.rest.service.ContentService;

/**
 * 内容管理
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	@RedisHandle(hashKey=RedisConstant.CONTENT_REDIS_HASH_KEY)
	public List<TbContent> getContentList(Long categoryId) {
		//从数据库中查询数据
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		return list;
	}
}
