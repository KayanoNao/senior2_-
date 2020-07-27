package com.zhaoluming.senior2.goods.service;

import java.util.List;
import java.util.Set;

import com.zhaoluming.senior2.goods.domain.Goods;

public interface GoodsService {

	/**
	 * 1.像redis中插入list集合数据
	 * @Title: redisInsertList 
	 * @Description: TODO
	 * @param key
	 * @param value
	 * @return
	 * @return: Integer
	 */
	Integer redisInsertList(String key, String value);
	
	/**
	 * 1.像redis中插入zset集合数据
	 * @Title: redisInsertList 
	 * @Description: TODO
	 * @param key
	 * @param value
	 * @return
	 * @return: Integer
	 */
	Integer redisInsertZset(String key, String value, double score);
	
	/**
	 * 1.获取list集合中的数据
	 * @Title: redisGetGoodsList 
	 * @Description: TODO
	 * @return
	 * @return: List<Goods>
	 */
	List<Goods> redisGetGoodsList();
	/**
	 * 1.获取zset集合中的数据
	 * @Title: redisGetGoodsZset 
	 * @Description: TODO
	 * @return
	 * @return: List<Goods>
	 */
	Set<String> redisGetGoodsZset();
}
