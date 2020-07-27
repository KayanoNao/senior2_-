package com.zhaoluming.senior2.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zhaoluming.senior2.goods.domain.Goods;
import com.zhaoluming.senior2.goods.service.GoodsService;

@Controller
public class GoodsController {

	@Resource
	private GoodsService goodsService;
	
	@RequestMapping("list")
	public String toList(Model model) {
		List<Goods> redisGetGoodsList = goodsService.redisGetGoodsList();
		model.addAttribute("list", redisGetGoodsList);
		return "list";
	}
	@RequestMapping("zset")
	public String toZset(Model model) {
		Set<String> redisGetGoodsZset = goodsService.redisGetGoodsZset();
		Gson gson = new Gson();
		ArrayList<Goods> zset = new ArrayList<Goods>();
		for (String goods : redisGetGoodsZset) {
			zset.add(gson.fromJson(goods, Goods.class));
		}
		model.addAttribute("zset", zset);
		return "zset";
	}
}
