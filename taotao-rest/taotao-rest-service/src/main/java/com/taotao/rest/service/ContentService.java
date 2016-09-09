package com.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;

/**
 * 内容管理service
 * @author Administrator
 *
 */
public interface ContentService {
	/**
	 * 根据分类id查询内容list
	 * @param categoryId
	 * @return
	 */
	List<TbContent> getContentList(Long categoryId);
}
