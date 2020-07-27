package com.zhaoluming.senior2.week2.test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.zhaoluming.senior2.goods.domain.Goods;
import com.zhaoluming.senior2.goods.service.GoodsService;
import com.zhaoluming.utils.NumberUtil;
import com.zhaoluming.utils.StreamUtils;
import com.zhaoluming.utils.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class GoodsData {

	@Resource
	private GoodsService goodsServiceImpl;
	
	/**
	 * 1.添加list
	 * @Title: getGoodsData 
	 * @Description: TODO
	 * @throws IOException
	 * @return: void
	 */
	@Test
	public void getGoodsData() throws IOException {
		//使用工具类读取文件
		List<String> textFile = StreamUtils.readingLineFormTextFile(new File("D:\\workspace3\\zhaoluming.senior2.week2\\src\\test\\resources\\data.txt"));
		//遍历文件内容
		for (String goods : textFile) {
			//根据==分隔内容
			String[] split = goods.split("==");
			Goods goods2 = new Goods();
			//ID值要使用isNumber()工具方法判断是不是数字。
			if(NumberUtil.isNumber(split[0])) {
				goods2.setId(Integer.valueOf(split[0]));
			}
			//商品名称要使用hasText()方法判断有没有值。
			if(StringUtil.hasText(split[1])) {
				goods2.setName(split[1]);
			}
			//价格要使用hasText()方法判断有没有值，并使用isNumber()判断是不是数字（不是数字的可以手工处理好再解析）。然后去掉“¥”符号，再转成数字。
			if(StringUtil.hasText(split[2])) {
				split[2] = split[2].replace("¥", "");
				if(NumberUtil.isNumber(split[2])) {
					goods2.setPrice(BigDecimal.valueOf(Double.valueOf(split[2])));
				}else {
					goods2.setPrice(BigDecimal.valueOf(Double.valueOf(split[2])));
				}
			}
			//百分比使用hasText()方法判断有没有值，如果没值则默认为0，并使用isNumber()判断是不是数字。然后去掉“%”符号，再转成数字。
			if(StringUtil.hasText(split[3])) {
				split[3] = split[3].replace("%", "");
				if(NumberUtil.isNumber(split[3])) {
					goods2.setYishou(Integer.valueOf(split[3]));
				}else {
					goods2.setYishou(0);
				}
			}else {
				goods2.setYishou(0);
			}
			//将每行数据解析到Goods类中，总共106条数据
			Gson gson = new Gson();
			String json = gson.toJson(goods2);
			goodsServiceImpl.redisInsertList("goods_list", json);
		}
		System.out.println("添加完毕!");
	}
	
	/**
	 * 1.添加zset
	 * @Title: getGoodsData2 
	 * @Description: TODO
	 * @throws IOException
	 * @return: void
	 */
	@Test
	public void getGoodsData2() throws IOException {
		List<String> textFile = StreamUtils.readingLineFormTextFile(new File("D:\\workspace3\\zhaoluming.senior2.week2\\src\\test\\resources\\data.txt"));
		for (String goods : textFile) {
			String[] split = goods.split("==");
			Goods goods2 = new Goods();
			if(NumberUtil.isNumber(split[0])) {
				goods2.setId(Integer.valueOf(split[0]));
			}
			if(StringUtil.hasText(split[1])) {
				goods2.setName(split[1]);
			}
			if(StringUtil.hasText(split[2])) {
				split[2] = split[2].replace("¥", "");
				if(NumberUtil.isNumber(split[2])) {
					goods2.setPrice(BigDecimal.valueOf(Double.valueOf(split[2])));
				}else {
					goods2.setPrice(BigDecimal.valueOf(Double.valueOf(split[2])));
				}
			}
			if(StringUtil.hasText(split[3])) {
				split[3] = split[3].replace("%", "");
				if(NumberUtil.isNumber(split[3])) {
					goods2.setYishou(Integer.valueOf(split[3]));
				}else {
					goods2.setYishou(0);
				}
			}else {
				goods2.setYishou(0);
			}
			Gson gson = new Gson();
			String json = gson.toJson(goods2);
			goodsServiceImpl.redisInsertZset("goods_zset", json, (double)goods2.getYishou());
		}
		System.out.println("添加完毕!");
	}
	
	/**
	 * 1.测试list读取
	 * @Title: getALlList 
	 * @Description: TODO
	 * @return: void
	 */
	@Test
	public void getALlList() {
		List<Goods> redisGetGoodsList = goodsServiceImpl.redisGetGoodsList();
		for (Goods goods : redisGetGoodsList) {
			System.out.println(goods);
		}
	}
	/**
	 * 1.测试zset读取
	 * @Title: getALlZset 
	 * @Description: TODO
	 * @return: void
	 */
	@Test
	public void getALlZset() {
		Set<String> redisGetGoodsZset = goodsServiceImpl.redisGetGoodsZset();
		Gson gson = new Gson();
		for (String goods : redisGetGoodsZset) {
			System.out.println(gson.fromJson(goods, Goods.class));
		}
	}
}
