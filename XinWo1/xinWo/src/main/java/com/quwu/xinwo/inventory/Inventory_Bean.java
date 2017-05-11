package com.quwu.xinwo.inventory;

public class Inventory_Bean {
	/**
	 * 	private String shopId; // 阶段表ID
	private String shopPicture; // 商品图片资源URL
	private String zongxu; // 总需
	private String shengyu; // 剩余
	private String shopDescription; // 商品描述
	private String xianshi;
	private int count;
	private boolean isChoosed; // 商品是否在购物车中被选中
	private boolean saowe; // 商品是否在购物车中的扫尾被选中
	private float shopPrice; // 商品单价
	private int shopNumber; // 商品数量
	 * */
	private String shopId; // 阶段表ID
	private String shopPicture; // 商品图片资源URL
	private String zongxu; // 总需
	private String shengyu; // 剩余
	private String shopDescription; // 商品描述
	private String xianshi;
	private String stages_id;// 阶段表的ID
	private String prefecture;//专区
	private String goods_id;//商品的ID
	private String virtual_goods;//0真实商品，1虚拟商品
	private String periods;// 期数
	private int count;
	private boolean isChoosed; // 商品是否在购物车中被选中
	private boolean saowe; // 商品是否在购物车中的扫尾被选中
	private float shopPrice; // 商品单价
	private int shopNumber; // 商品数量
	private String order_id;//清单ID

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getVirtual_goods() {
		return virtual_goods;
	}

	public void setVirtual_goods(String virtual_goods) {
		this.virtual_goods = virtual_goods;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getStages_id() {
		return stages_id;
	}

	public void setStages_id(String stages_id) {
		this.stages_id = stages_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getXianshi() {
		return xianshi;
	}

	public void setXianshi(String xianshi) {
		this.xianshi = xianshi;
	}

	public float getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(float shopPrice) {
		this.shopPrice = shopPrice;
	}

	public int getShopNumber() {
		return shopNumber;
	}

	public void setShopNumber(int shopNumber) {
		this.shopNumber = shopNumber;
	}

	public Inventory_Bean(String shopId, String shopPicture, String zongxu,
			String shengyu, String shopDescription, boolean isChoosed,
			boolean saowe, int count,String prefecture,String goods_id,String virtual_goods,String periods,String stages_id,String order_id) {
		super();
		this.shopId = shopId;
		this.shopPicture = shopPicture;
		this.zongxu = zongxu;
		this.shengyu = shengyu;
		this.shopDescription = shopDescription;
		this.isChoosed = isChoosed;
		this.saowe = saowe;
		this.count = count;
		this.prefecture=prefecture;
		this.goods_id=goods_id;
		this.virtual_goods=virtual_goods;
		this.periods=periods;
		this.stages_id=stages_id;
		this.order_id=order_id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopPicture() {
		return shopPicture;
	}

	public void setShopPicture(String shopPicture) {
		this.shopPicture = shopPicture;
	}

	public String getZongxu() {
		return zongxu;
	}

	public void setZongxu(String zongxu) {
		this.zongxu = zongxu;
	}

	public String getShengyu() {
		return shengyu;
	}

	public void setShengyu(String shengyu) {
		this.shengyu = shengyu;
	}

	public String getShopDescription() {
		return shopDescription;
	}

	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}

	public boolean isChoosed() {
		return isChoosed;
	}

	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}

	public boolean isSaowe() {
		return saowe;
	}

	public void setSaowe(boolean saowe) {
		this.saowe = saowe;
	}

}
