package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.redis.annotation.RedisHandle;
import com.taotao.redis.constant.RedisConstant;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper catMapper;
	
	@Override
	@RedisHandle(key=RedisConstant.ITEMCAT_REDIS_KEY)
	public CatResult getItemCatList() {
		CatResult result = new CatResult();
		result.setData(getCatList(0L));
		return result;
	}
	
	/**
	 * 递归查询商品分类结构树
	 * @return
	 */
	private List<Object> getCatList(Long parentId) {
		//设置查询条件
		TbItemCatExample example = new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		List<TbItemCat> list = catMapper.selectByExample(example);

		List<Object> catList = new ArrayList<Object>();
		
		int count = 0;
		//循环查询子节点
		for (TbItemCat cat : list) {
			//判断是否是叶子节点
			if (cat.getIsParent()) {
				CatNode node = new CatNode();
				node.setUrl( "/products/"+cat.getId()+".html");
				//第一级节点名称需要url
				if (cat.getParentId() == 0) 
					node.setName("<a href='/products/"+cat.getId()+".html'>"+cat.getName()+"</a>");
				else 
					node.setName(cat.getName());
				
				node.setItem(getCatList(cat.getId()));//查询下一级子目录
				catList.add(node);
			
				//控制前端页面显示数量
				if (cat.getParentId() == 0 && count >= 13) {
					break;
				}
				count ++;
			}else {
				//是最底层子节点
				catList.add( "/products/"+cat.getId()+".html|"+cat.getName());
			}
		}
		
		return catList;
	}

}
