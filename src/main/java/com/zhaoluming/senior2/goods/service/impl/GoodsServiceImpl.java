package com.zhaoluming.senior2.goods.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.zhaoluming.senior2.goods.domain.Goods;
import com.zhaoluming.senior2.goods.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public Integer redisInsertList(String key, String value) {
		ListOperations<String, String> opsForList = redisTemplate.opsForList();
		opsForList.leftPush(key, value);
		return null;
	}

	@Override
	public Integer redisInsertZset(String key, String value, double score) {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		opsForZSet.add(key, value, score);
		return null;
	}

	@Override
	public List<Goods> redisGetGoodsList() {
		ListOperations<String, String> opsForList = redisTemplate.opsForList();
		List<String> range = opsForList.range("goods_list", 0, -1);
		ArrayList<Goods> arrayList = new ArrayList<Goods>();
		Gson gson = new Gson();
		for (String goods : range) {
			arrayList.add(gson.fromJson(goods, Goods.class));
		}
		return arrayList;
	}

	@Override
	public Set<String> redisGetGoodsZset() {
		ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
		Set<String> range = opsForZSet.reverseRange("goods_zset", 0, -1);
		return range;
	}

}
