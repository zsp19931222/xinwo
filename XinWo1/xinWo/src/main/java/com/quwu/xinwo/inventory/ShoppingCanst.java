package com.quwu.xinwo.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCanst {
	public static List<Inventory_Bean> list=new ArrayList<Inventory_Bean>();	//购物车数据集合
 
	public static HashMap<String, Integer> isConut=new HashMap<String, Integer>();// 存点击item的加减号所获得的下标
	
	public static HashMap<Integer, Integer> first=new HashMap<Integer, Integer>();// 是否第一次进软件

}
