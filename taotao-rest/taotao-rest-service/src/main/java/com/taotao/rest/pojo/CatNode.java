package com.taotao.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 页面展示用pojo
 * @author Administrator
 *
 */
public class CatNode {
	//表示在转换为json数据的时候，会url会变成u
	@JsonProperty("u")
	private String url;
	@JsonProperty("n")
	private String name;
	@JsonProperty("i")
	private List<?> item;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<?> getItem() {
		return item;
	}
	public void setItem(List<?> item) {
		this.item = item;
	}
	
	
}
