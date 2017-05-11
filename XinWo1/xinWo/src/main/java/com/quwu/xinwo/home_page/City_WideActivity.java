package com.quwu.xinwo.home_page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.City_WideAdapter;
import com.quwu.xinwo.adapter.City_wide_gridview_itemAdapter;
import com.quwu.xinwo.adapter.HP_NearbyAdapter;
import com.quwu.xinwo.bean.City_WideBean;
import com.quwu.xinwo.bean.City_wide_classify_popBean;
import com.quwu.xinwo.bean.HP_NearbyBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.City_WideEntity;
import com.quwu.xinwo.mywight.MyGridView;
import com.quwu.xinwo.mywight.MyListView;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.mywight.MyViewPager;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.popupwindow.City_Wide_Classes_Pop;
import com.quwu.xinwo.popupwindow.City_Wide_Classes_Pop.OnClassesWindowClickListener;
import com.quwu.xinwo.popupwindow.City_Wide_Classify_Pop;
import com.quwu.xinwo.popupwindow.City_Wide_Classify_Pop.OnRankWindowClickListener;
import com.quwu.xinwo.popupwindow.City_Wide_Screen_Pop;
import com.quwu.xinwo.popupwindow.City_Wide_Screen_Pop.OnScreenWindowClickListener;
import com.quwu.xinwo.popupwindow.DoublieListview;
import com.quwu.xinwo.popupwindow.DoublieListview.OnDoublieClickListener;
import com.quwu.xinwo.pull.MyListener;
import com.quwu.xinwo.pull.PullToRefreshLayout;
import com.quwu.xinwo.pull.PullableScrollView;
import com.quwu.xinwo.pull.PullableScrollView.OnScrollListener;
import com.quwu.xinwo.pull.ScrollViewListener;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.ScreenUtils;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.TimeUtils;
import com.quwu.xinwo.until.Tool;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class City_WideActivity extends Activity implements OnClickListener,
		OnScrollListener, ScrollViewListener {
	private PullableScrollView scrollView;
	private MyListView listView;
	private TextView city_wide_noDataText;// 没有数据提示
	private List<City_WideBean> listviewList;
	private City_WideAdapter listviewadapter;
	private DoublieListview all_pop;
	private HashMap<Integer, Integer> scrollYMap;

	private RelativeLayout city_wide_cityBgRel;

	private TextView searchEd;
	private TextView cityText;
	private TextView cityPinyinText;

	private MyViewPager pager;
	private ImageView vp_image1;
	private ImageView vp_image2;
	private List<View> viewList;// 装viewpager视图的集合
	private View vp_view1, vp_view2;// viewpager的视图
	private MyGridView vp_GridView1;// viewpager视图的GridView
	private MyGridView vp_GridView2;// viewpager视图的GridView
	private List<HP_NearbyBean> list = new ArrayList<HP_NearbyBean>();
	private List<HP_NearbyBean> list1 = new ArrayList<HP_NearbyBean>();
	private HP_NearbyAdapter adapter;
	private HP_NearbyAdapter adapter1;
	private LinearLayout city_wide_iconLin;

	private LinearLayout allCityLin;// 全市
	private TextView city_wide_allcityText;
	private LinearLayout classesLin;// 类别
	private LinearLayout rankLin;// 排序
	private LinearLayout screenLin;// 筛选

	// 顶部
	private LinearLayout allCityLin1;// 全市
	private TextView city_wide_allcityText1;
	private LinearLayout classesLin1;// 类别
	private LinearLayout rankLin1;// 排序
	private LinearLayout screenLin1;// 筛选

	private LinearLayout layout2;
	private LinearLayout layout1;
	private PullToRefreshLayout parent_layout;

	private int start = -1;// 第一次进来加载数据的判断

	/**
	 * 服务器返回数据
	 * */
	private String citypages_url;// 图标路径
	private String citypages_title;// 图标标题
	private List<City_WideEntity> json_list;

	/**
	 * 分类按钮
	 * */
	private List<City_wide_classify_popBean> classifylist;
	private List<City_wide_classify_popBean> rank_pop_list;
	/**
	 * 所需参数
	 * */
	private static int pageNow = 1;
	private static int pageSize = 8;
	private String normal_use = "0";// 正常使用，0-没有选中，1-选中了
	private String warranty_period = "0";// 保修期内，0-没有选中，1-选中了
	private String no_repair = "0";// 无拆无修，0-没有选中，1-选中了
	private String brand_new = "0";// 全新商品，0-没有选中，1-选中了
	private String jingdong = "0";// 京东，0-没有选中，1-选中了
	private String mainland_licensed = "0";// 大陆行货，0-没有选中，1-选中了
	private String since = "0";// 可以自提，0-没有选中，1-选中了
	private String city_region1;// 上架的市ID 如 10
	private String small_area1 = "1";// 上架商品的区县ID（注意：如果是选的全地区则传0） 如20
	private String twolevel_id = "1";// 商品二级分类 如 数码商品
	private String three_id = "15"; // 商品的三级分类 如 手机
	private String sortorder_id = "1";// 排序方式 如1
	private String goods_lprice = "0";// 商品的最低价 如 10
	private String goods_hprice = "10000000";// 商品的最高价 如200
	private String fristlevel = "1";// 商品的一级分类 如 众筹就传1

	/**
	 * 城市ID和拼音
	 * */
	private String region_id;// 地区ID
	private String pinyin;// 地区拼音
	private String parent_id;// 省ID

	/**
	 * 返回参数
	 * */
	private String goods_photo;// 商品图片
	private String goods_name;// 商品名称
	private String good_description;// 商品描述
	private String good_region;// 省份
	private String city_region;// 市
	private String small_area;// 区县
	private String ins_time;// 商品上架时间
	private String current_time1;// 服务器当前时间
	private String total_person;// 总需
	private String surplus_person;// 剩余人次
	private String browse_person;// 商品浏览量
	private String goodslevel;// 商品的一级分类
	private String goods_id;// 商品的ID
	private String buy_userid;// 点击查询的用户的ID（如果没有登录则传0）

	private MyGridView city_wide_classifyGridview;
	private City_wide_gridview_itemAdapter gridview_itemAdapter;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.city_wide);
		FinishActivity.finish(R.id.city_wide_returnRel, City_WideActivity.this);
		scrollYMap = new HashMap<Integer, Integer>();
		findID();
		new IconTask().executeOnExecutor(Executors.newCachedThreadPool());
		logMsg(Home_Page_Activity.City);
	}

	private void findID() {
		city_wide_classifyGridview = (MyGridView) findViewById(R.id.city_wide_classifyGridview);

		city_wide_cityBgRel = (RelativeLayout) findViewById(R.id.city_wide_cityBgRel);
		SetW_H.setLinearLayout1(this, city_wide_cityBgRel, 0.26);

		city_wide_noDataText = (TextView) findViewById(R.id.city_wide_noDataText);
		layout1 = (LinearLayout) findViewById(R.id.city_wide_layout2);
		layout2 = (LinearLayout) findViewById(R.id.city_wide_layout1);
		parent_layout = (PullToRefreshLayout) findViewById(R.id.parent_layout);
		scrollView = (PullableScrollView) findViewById(R.id.city_wide_scrollView);
		scrollView.setOnScrollListener(this);
		scrollView.setScrollViewListener(this);
		parent_layout.setOnRefreshListener(new MyListener());
		parent_layout.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					public void onGlobalLayout() {
						onScroll(scrollView.getScrollY());
						int heightDiff = parent_layout.getRootView()
								.getHeight() - parent_layout.getHeight();
						if (heightDiff > 100) { // 说明键盘是弹出状态
							if (all_pop != null) {
								if (all_pop.isShowing()) {
									scrollView.smoothScrollTo(0,
											scrollYMap.get(0));
								}
							}
						} else {
							if (all_pop != null) {
								if (all_pop.isShowing()) {
									scrollView.smoothScrollTo(0,
											scrollYMap.get(0));
								}
							}
						}
					}
				});

		listView = (MyListView) findViewById(R.id.city_wide_listview);

		searchEd = (TextView) findViewById(R.id.city_wide_Ed);
		cityText = (TextView) findViewById(R.id.city_wide_cityText);
		cityPinyinText = (TextView) findViewById(R.id.city_wide_cityPinyinText);
		searchEd.setOnClickListener(this);
		cityText.setOnClickListener(this);
		cityPinyinText.setOnClickListener(this);

		pager = (MyViewPager) findViewById(R.id.city_wide_viewpager);
		vp_image1 = (ImageView) findViewById(R.id.city_wide_vp_image1);
		vp_image2 = (ImageView) findViewById(R.id.city_wide_vp_image2);

		View view = findViewById(R.id.city_wide_layout2);
		View view1 = findViewById(R.id.city_wide_layout1);
		allCityLin = (LinearLayout) view
				.findViewById(R.id.city_wide_allcityLin);
		allCityLin1 = (LinearLayout) view1
				.findViewById(R.id.city_wide_allcityLin);
		city_wide_allcityText = (TextView) view
				.findViewById(R.id.city_wide_allcityText);
		city_wide_allcityText1 = (TextView) view1
				.findViewById(R.id.city_wide_allcityText);
		allCityLin.setOnClickListener(this);
		allCityLin1.setOnClickListener(this);
		classesLin = (LinearLayout) view
				.findViewById(R.id.city_wide_classesLin);
		classesLin1 = (LinearLayout) view1
				.findViewById(R.id.city_wide_classesLin);
		classesLin.setOnClickListener(this);
		classesLin1.setOnClickListener(this);
		rankLin = (LinearLayout) view.findViewById(R.id.city_wide_rankLin);
		rankLin1 = (LinearLayout) view1.findViewById(R.id.city_wide_rankLin);
		rankLin.setOnClickListener(this);
		rankLin1.setOnClickListener(this);
		screenLin = (LinearLayout) view.findViewById(R.id.city_wide_screenLin);
		screenLin1 = (LinearLayout) view1
				.findViewById(R.id.city_wide_screenLin);
		screenLin.setOnClickListener(this);
		screenLin1.setOnClickListener(this);

		city_wide_iconLin = (LinearLayout) findViewById(R.id.city_wide_iconLin);
	}

	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			Intent intent1 = null;
			switch (v.getId()) {
			case R.id.city_wide_Ed:// 跳转搜索页面
				intent = new Intent(City_WideActivity.this,
						SearchActivity.class);
				break;
			case R.id.city_wide_cityText:// 跳转选择地区页面
				intent1 = new Intent(City_WideActivity.this, AreaActivity.class);
				startActivityForResult(intent1, 0);
				break;
			case R.id.city_wide_cityPinyinText:// 跳转选择地区页面
				intent1 = new Intent(City_WideActivity.this, AreaActivity.class);
				startActivityForResult(intent1, 0);
				break;
			case R.id.city_wide_allcityLin:
				parent_layout.setAlpha(0.5f);
				all_pop = new DoublieListview(City_WideActivity.this,
						parent_id, cityText.getText().toString().trim());
				all_pop.showPopupWindow(allCityLin);
				all_pop.setOnDoublieClickListener(new OnDoublieClickListener() {

					public void send(String parent_name,String name,String city_region,String small_area) {
						city_wide_allcityText.setText(name);
						cityText.setText(parent_name);
						small_area1 = small_area;
						city_region1 = city_region;
						parent_id=city_region;
						new ObtainCityIDTask().executeOnExecutor(
								Executors.newCachedThreadPool(), parent_name);
					}
				});
				all_pop.setOnDismissListener(new OnDismissListener() {

					public void onDismiss() {
						parent_layout.setAlpha(1f);
					}
				});
				break;
			case R.id.city_wide_classesLin:
				parent_layout.setAlpha(0.5f);
				City_Wide_Classes_Pop classes_pop = new City_Wide_Classes_Pop(
						City_WideActivity.this);
				classes_pop.showPopupWindow(classesLin);
				classes_pop.setOnDismissListener(new OnDismissListener() {

					public void onDismiss() {
						parent_layout.setAlpha(1f);
					}
				});
				classes_pop
						.setOnClassesWindowClickListener(new OnClassesWindowClickListener() {

							public void send(String name, String twolevel_id,
									String three_id) {
								City_WideActivity.this.twolevel_id = twolevel_id;
								City_WideActivity.this.three_id = three_id;
								new LoadDataTask().executeOnExecutor(Executors
										.newCachedThreadPool());
							}
						});
				break;
			case R.id.city_wide_rankLin:
				new RankTask().executeOnExecutor(Executors
						.newCachedThreadPool());
				break;
			case R.id.city_wide_screenLin:
				parent_layout.setAlpha(0.5f);
				City_Wide_Screen_Pop screen_pop = new City_Wide_Screen_Pop(
						City_WideActivity.this, normal_use, warranty_period,
						no_repair, brand_new, jingdong, mainland_licensed,
						since, goods_hprice, goods_lprice);
				screen_pop.showPopupWindow(screenLin);
				scrollView.smoothScrollTo(0, scrollYMap.get(0) / 2);
				screen_pop
						.setOnScreenWindowClickListener(new OnScreenWindowClickListener() {
							public void send(String normal_use,
									String warranty_period, String no_repair,
									String brand_new, String jingdong,
									String mainland_licensed, String since,
									String high, String low) {
								City_WideActivity.this.normal_use = normal_use;
								City_WideActivity.this.warranty_period = warranty_period;
								City_WideActivity.this.no_repair = no_repair;
								City_WideActivity.this.brand_new = brand_new;
								City_WideActivity.this.jingdong = jingdong;
								City_WideActivity.this.mainland_licensed = mainland_licensed;
								City_WideActivity.this.since = since;
								City_WideActivity.this.goods_lprice = low;
								City_WideActivity.this.goods_hprice = high;
								new LoadDataTask().executeOnExecutor(Executors
										.newCachedThreadPool());
							}
						});
				screen_pop.setOnDismissListener(new OnDismissListener() {

					public void onDismiss() {
						parent_layout.setAlpha(1f);
					}
				});
				break;
			// case R.id.city_wide_classifyLin:
			// new ClassifyTask().executeOnExecutor(Executors
			// .newCachedThreadPool());
			// break;
			default:
				break;
			}
			if (intent != null) {
				startActivity(intent);
			}
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			if (data != null) {
				String string = data.getStringExtra("area_name");
				cityText.setText(string);
				city_wide_allcityText.setText("全" + string);
				small_area1="0";
				new ObtainCityIDTask().executeOnExecutor(
						Executors.newCachedThreadPool(), string);
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 加载viewpager
	 * */
	private void isViewPager(List<HP_NearbyBean> list, List<HP_NearbyBean> list1) {
		viewList = new ArrayList<View>();
		LayoutInflater inflater = this.getLayoutInflater();
		vp_view1 = inflater.inflate(R.layout.prefecture_viewpager, null);
		vp_view2 = inflater.inflate(R.layout.prefecture_viewpager1, null);
		vp_GridView1 = (MyGridView) vp_view1
				.findViewById(R.id.prefecture_viewpager_gridview);
		vp_GridView2 = (MyGridView) vp_view2
				.findViewById(R.id.prefecture_viewpager_gridview1);
		vp_GridView1.setNumColumns(4);
		vp_GridView1.setBackgroundColor(this.getResources().getColor(
				R.color.white));
		vp_GridView2.setNumColumns(4);
		vp_GridView2.setBackgroundColor(this.getResources().getColor(
				R.color.white));
		adapter = new HP_NearbyAdapter(getApplicationContext(), list);
		adapter1 = new HP_NearbyAdapter(getApplicationContext(), list1);
		vp_GridView1.setAdapter(adapter);
		vp_GridView2.setAdapter(adapter1);
		if (list1.size() == 0) {
			viewList.add(vp_view1);
			city_wide_iconLin.setVisibility(View.GONE);
		} else {
			viewList.add(vp_view1);
			viewList.add(vp_view2);
			city_wide_iconLin.setVisibility(View.VISIBLE);
		}
		vp_GridView1.setOnItemClickListener(new MyGridViewItemClickListener());
		vp_GridView2.setOnItemClickListener(new MyGridViewItemClickListener());
		pager.setAdapter(new MyPager());
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int arg0) {
				ImageView[] imageViews = initializeIamgeBg();
				for (int j = 0; j < imageViews.length; j++) {
					imageViews[arg0]
							.setBackgroundResource(R.drawable.point_pre);
					pager.setCurrentItem(arg0);
					if (arg0 != j) {
						imageViews[j]
								.setBackgroundResource(R.drawable.point_mor);
					}
				}
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	/**
	 * Viewpager的adapter
	 * */
	private class MyPager extends PagerAdapter {

		public int getCount() {

			return viewList.size();
		}

		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;

		}

		public Object instantiateItem(ViewGroup container, int position) {

			container.addView(viewList.get(position));
			return viewList.get(position);
		}

		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView(viewList.get(position));
		}

	}

	/**
	 * 初始化ViewPager标识圆
	 * */
	private ImageView[] initializeIamgeBg() {
		ImageView imageViews[] = { vp_image1, vp_image2 };
		return imageViews;
	}

	/**
	 * 加载listview数据
	 * */
	public void isListView() {
		listviewList = new ArrayList<City_WideBean>();

		for (int i = 0; i < 10; i++) {
			listviewList.add(new City_WideBean(
					"http://pic1a.nipic.com/2008-12-01/2008121175139413_2.jpg",
					"商品名称", "商品描述", "重庆 渝北区\t\t10小时前", "2700", "1", "654"));
		}
		listviewadapter = new City_WideAdapter(listviewList,
				getApplicationContext());
		listView.setAdapter(listviewadapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});
	}

	/**
	 * 监听滑动高度
	 * */
	public void onScroll(int scrollY) {
		int mBuyLayout2ParentTop = Math.max(scrollY, layout2.getTop());
		if (scrollY == 0) {
			scrollYMap.put(scrollY, mBuyLayout2ParentTop);
		}
		layout1.layout(0, mBuyLayout2ParentTop, layout1.getWidth(),
				mBuyLayout2ParentTop + layout1.getHeight());
	}

	/**
	 * 监听滑动
	 * */
	public void onScrollChanged(PullableScrollView scrollView, int x, int y,
			int oldx, int oldy) {
		// 监听到底部
		View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
		int d = view.getBottom();
		d -= (scrollView.getHeight() + scrollView.getScrollY());
		if (d == 0) {
			// new Task().executeOnExecutor(Executors.newCachedThreadPool());
		} else {

		}
	}

	/**
	 * gridview点击事件
	 * */
	private class MyGridViewItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(City_WideActivity.this,
					City_Wide_Vp_ClassesActivity.class);
			startActivity(intent);
		}
	}

	public void logMsg(String city) {
		if (city != null) {
			cityText.setText(city);
			if (city.equals("北京(定位失败)")) {
				city_wide_allcityText.setText("全北京");
			} else {
				city_wide_allcityText.setText("全" + city);
			}
			if (city.equals("北京(定位失败)")) {
				new ObtainCityIDTask().executeOnExecutor(
						Executors.newCachedThreadPool(), "北京");
			} else {
				new ObtainCityIDTask().executeOnExecutor(
						Executors.newCachedThreadPool(), city);
			}
		}
	}

	/**
	 * 加载图标
	 * */
	private class IconTask extends AsyncTask<Void, Void, Void> {

		protected Void doInBackground(Void... params) {
			list = new ArrayList<HP_NearbyBean>();
			list1 = new ArrayList<HP_NearbyBean>();
			String result = OKHTTP_POST.doPost1(MyApp.base_address
					+ "citypages/selectcityphoto.do", "", "");
			if (result != null) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(result);
					String result1 = jsonObject.getString("1");
					if (result1 != null) {
						if (result1.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (result1.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(result1,
									new TypeToken<List<City_WideEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								citypages_url = json_list.get(i)
										.getCitypages_url();
								citypages_title = json_list.get(i)
										.getCitypages_title();
								if (i < 8) {
									list.add(new HP_NearbyBean(
											MyApp.base_address + citypages_url,
											citypages_title));
								} else {
									list1.add(new HP_NearbyBean(
											MyApp.base_address + citypages_url,
											citypages_title));
								}
							}
							handler.sendEmptyMessageDelayed(0, 10);
						}
					} else {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
			}
			return null;
		}
	}

	/**
	 * 加载一级分类数据
	 * */
	private class ClassifyTask extends AsyncTask<Void, Void, Void> {

		protected Void doInBackground(Void... params) {
			classifylist = new ArrayList<City_wide_classify_popBean>();

			String result = OKHTTP_POST.doPost1(MyApp.base_address
					+ "lableaction/selecFristgoodscategory.do", "", "");
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("当前还没有数据")) {

					} else if (string.equals("程序异常")) {

					} else {
						Gson gson = new Gson();
						json_list = gson.fromJson(string,
								new TypeToken<List<City_WideEntity>>() {
								}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							classifylist.add(new City_wide_classify_popBean(
									json_list.get(i).getFirstlevel_id(),
									json_list.get(i).getFirstlevel_name()));
						}
						handler.sendEmptyMessageDelayed(3, 10);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}

	/**
	 * 加载排序数据
	 * */
	private class RankTask extends AsyncTask<Void, Void, Void> {

		protected Void doInBackground(Void... params) {
			rank_pop_list = new ArrayList<City_wide_classify_popBean>();
			String result = OKHTTP_POST.doPost1(MyApp.base_address
					+ "lableaction/selectsortorder.do", "", "");
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("当前还没有数据")) {

					} else if (string.equals("程序异常")) {

					} else {
						Gson gson = new Gson();
						json_list = gson.fromJson(string,
								new TypeToken<List<City_WideEntity>>() {
								}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							rank_pop_list.add(new City_wide_classify_popBean(
									json_list.get(i).getSortorder_id(),
									json_list.get(i).getSortorder_content()));
						}
						handler.sendEmptyMessageDelayed(4, 10);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}

	/**
	 * 获取当前城市ID
	 * */
	private class ObtainCityIDTask extends AsyncTask<String, Void, Void> {

		protected Void doInBackground(String... params) {
			String result = OKHTTP_POST.doPost1(MyApp.base_address
					+ "citypages/selectid.do", "region_name", params[0]);
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("当前还没有数据")) {

					} else if (string.equals("程序异常")) {

					} else {
						Gson gson = new Gson();
						json_list = gson.fromJson(string,
								new TypeToken<List<City_WideEntity>>() {
								}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							region_id = json_list.get(i).getRegion_id();
							pinyin = json_list.get(i).getPinyin();
							parent_id = json_list.get(i).getParent_id();
							city_region1 = region_id;
						}
						handler.sendEmptyMessageDelayed(1, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
	}

	/**
	 * 第一次进来加载的数据
	 * */
	private class FirstTask extends AsyncTask<Void, Void, Void> {
		String result;

		protected Void doInBackground(Void... params) {
			listviewList = new ArrayList<City_WideBean>();
			if (region_id != null) {
				result = firstDoPost(MyApp.base_address
						+ "citypages/selectcondition.do", region_id);
			}
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("当前还没有数据")) {

					} else if (string.equals("程序异常")) {

					} else if (string.equals("还没有当前分类")) {

					} else {
						Gson gson = new Gson();
						json_list = gson.fromJson(string,
								new TypeToken<List<City_WideEntity>>() {
								}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							goods_photo = json_list.get(i).getGoods_photo();
							goods_name = json_list.get(i).getGoods_name();
							good_description = json_list.get(i)
									.getGood_description();
							good_region = json_list.get(i).getGood_region();
							city_region = json_list.get(i).getCity_region();
							small_area = json_list.get(i).getSmall_area();
							ins_time = json_list.get(i).getIns_time();
							current_time1 = json_list.get(i).getCurrent_time1();
							total_person = json_list.get(i).getTotal_person();
							surplus_person = json_list.get(i)
									.getSurplus_person();
							browse_person = json_list.get(i).getBrowse_person();
							goodslevel = json_list.get(i).getGoodslevel();
							buy_userid = json_list.get(i).getBuy_userid();
							listviewList
									.add(new City_WideBean(
											MyApp.base_address + goods_photo,
											goods_name,
											good_description,
											good_region
													+ " "
													+ city_region
													+ " "
													+ small_area
													+ "\t\t"
													+ TimeUtils.isTime_Difference(Long
															.valueOf(current_time1)
															- Long.valueOf(ins_time)),
											total_person, surplus_person,
											browse_person));
						}
						handler.sendEmptyMessageDelayed(2, 10);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

	}

	/**
	 * 加载商品数据
	 * */
	private class LoadDataTask extends AsyncTask<Void, Void, Void> {

		protected Void doInBackground(Void... params) {
			listviewList = new ArrayList<City_WideBean>();
			System.out.println("normal_use=" + normal_use + "\n"
					+ "warranty_period=" + warranty_period + "\n"
					+ "no_repair=" + no_repair + "\n" + "brand_new="
					+ brand_new + "\n" + "jingdong=" + jingdong + "\n"
					+ "mainland_licensed=" + mainland_licensed + "\n"
					+ "since=" + since + "\n" + "city_region1=" + city_region1
					+ "\n" + "small_area1=" + small_area1 + "\n"
					+ "twolevel_id=" + twolevel_id + "\n" + "three_id="
					+ three_id + "\n" + "sortorder_id=" + sortorder_id + "\n"
					+ "goods_lprice=" + goods_lprice + "\n" + "goods_hprice="
					+ goods_hprice + "\n" + "fristlevel=" + fristlevel + "\n");
			String result = MyDoPost(MyApp.base_address
					+ "citypages/selectcondition.do", normal_use,
					warranty_period, no_repair, brand_new, jingdong,
					mainland_licensed, since, city_region1, small_area1,
					twolevel_id, three_id, sortorder_id, goods_lprice,
					goods_hprice, fristlevel,
					MySharePreferences.GetUser_ID(getApplicationContext()));
			System.out.println(result);
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("当前还没有数据")) {
						handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
					} else if (string.equals("程序异常")) {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					} else if (string.equals("还没有当前分类")) {
						handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
					} else {
						Gson gson = new Gson();
						json_list = gson.fromJson(string,
								new TypeToken<List<City_WideEntity>>() {
								}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							goods_photo = json_list.get(i).getGoods_photo();
							goods_name = json_list.get(i).getGoods_name();
							good_description = json_list.get(i)
									.getGood_description();
							good_region = json_list.get(i).getGood_region();
							city_region = json_list.get(i).getCity_region();
							small_area = json_list.get(i).getSmall_area();
							ins_time = json_list.get(i).getIns_time();
							current_time1 = json_list.get(i).getCurrent_time1();
							total_person = json_list.get(i).getTotal_person();
							surplus_person = json_list.get(i)
									.getSurplus_person();
							browse_person = json_list.get(i).getBrowse_person();
							goodslevel = json_list.get(i).getGoodslevel();
							buy_userid = json_list.get(i).getBuy_userid();
							listviewList
									.add(new City_WideBean(
											MyApp.base_address + goods_photo,
											goods_name,
											good_description,
											good_region
													+ " "
													+ city_region
													+ " "
													+ small_area
													+ "\t\t"
													+ TimeUtils.isTime_Difference(Long
															.valueOf(current_time1)
															- Long.valueOf(ins_time)),
											total_person, surplus_person,
											browse_person));
						}
						handler.sendEmptyMessageDelayed(2, 10);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else {
				handler.sendEmptyMessageDelayed(
						MyApp.Program_Exception, 10);
			}
			return null;
		}

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 10000:
				city_wide_noDataText.setText("服务器异常,点我重新试试！");
				new LoadDataTask().executeOnExecutor(Executors
						.newCachedThreadPool());
				break;
			case 10001:
				city_wide_noDataText.setText("没有查询到相关商品！");
				break;
			case 0:// 加载viewpager数据
				isViewPager(list, list1);
				new ClassifyTask().executeOnExecutor(Executors
						.newCachedThreadPool());
				break;
			case 1:// 获取定位城市的id和拼音
				cityPinyinText.setText(pinyin);
				System.out.println("进来了1+++++++++start="+start);
				if (start == -1) {
					System.out.println("进来了2+++++++++start="+start);
					new FirstTask().executeOnExecutor(Executors
							.newCachedThreadPool());
					start = 0;
				} else {
					System.out.println("进来了3+++++++++start="+start);
					new LoadDataTask().executeOnExecutor(Executors
							.newCachedThreadPool());
				}
				break;
			case 2:// 获取第一次进来的数据
				city_wide_noDataText.setVisibility(View.GONE);
				listviewadapter = new City_WideAdapter(listviewList,
						getApplicationContext());
				listView.setAdapter(listviewadapter);

				listView.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
					}
				});
				
				break;
			case 3:// 加载一级分类数据
				int size = classifylist.size();
				city_wide_classifyGridview.setColumnWidth(ScreenUtils
						.getScreenWidth(City_WideActivity.this) / 6); // 设置列表项宽
				city_wide_classifyGridview.setHorizontalSpacing(20); // 设置列表项水平间距
				city_wide_classifyGridview.setStretchMode(GridView.NO_STRETCH);
				city_wide_classifyGridview.setNumColumns(size); // 设置列数量=列表集合数
				gridview_itemAdapter = new City_wide_gridview_itemAdapter(
						classifylist, getApplicationContext());
				city_wide_classifyGridview.setAdapter(gridview_itemAdapter);
				Tool.setListViewWidthBasedOnChildren(city_wide_classifyGridview);
				city_wide_classifyGridview
						.setOnItemClickListener(new OnItemClickListener() {

							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								gridview_itemAdapter.isSelect(position);
								gridview_itemAdapter.notifyDataSetInvalidated();
								fristlevel = classifylist.get(position)
										.getFirstlevel_id();
								new LoadDataTask().executeOnExecutor(Executors
										.newCachedThreadPool());
							}
						});
				break;
			case 4:// 加载排序方式
				parent_layout.setAlpha(0.5f);
				City_Wide_Classify_Pop rank_pop = new City_Wide_Classify_Pop(
						City_WideActivity.this, rank_pop_list, sortorder_id);
				rank_pop.showPopupWindow(rankLin);
				scrollView.smoothScrollTo(0, scrollYMap.get(0) / 2);
				rank_pop.setOnDismissListener(new OnDismissListener() {
					public void onDismiss() {
						parent_layout.setAlpha(1f);
					}
				});
				rank_pop.setOnRankWindowClickListener(new OnRankWindowClickListener() {
					public void send(String id, String name) {
						sortorder_id = id;
						new LoadDataTask().executeOnExecutor(Executors
								.newCachedThreadPool());
					}
				});
				break;
			default:
				break;
			}
		};
	};

	/**
	 * 第一次进来网络请求
	 * */
	public String firstDoPost(String url, String parameter) {
		OkHttpClient mOkHttpClient = new OkHttpClient();
		RequestBody formBody = new FormEncodingBuilder()
				.add("city_region", parameter).add("small_area", "0")
				.add("twolevel_id", "1").add("three_id", "15")
				.add("sortorder_id", "1").add("goods_lprice", "0")
				.add("goods_hprice", "800000").add("state", "1")
				.add("pageNow", String.valueOf(pageNow))
				.add("pageSize", String.valueOf(pageSize)).build();

		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				String body = response.body().string();
				return body;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}// execute

		return null;
	}

	/**
	 * 根据条件查询商品的网络请求
	 * */
	public String MyDoPost(String url, String normal_use,
			String warranty_period, String no_repair, String brand_new,
			String jingdong, String mainland_licensed, String since,
			String city_region, String small_area, String twolevel_id,
			String three_id, String sortorder_id, String goods_lprice,
			String goods_hprice, String fristlevel, String buy_userid) {
		OkHttpClient mOkHttpClient = new OkHttpClient();
		RequestBody formBody = new FormEncodingBuilder()
				.add("normal_use", normal_use)
				.add("warranty_period", warranty_period)
				.add("no_repair", no_repair).add("brand_new", brand_new)
				.add("jingdong", jingdong)
				.add("mainland_licensed", mainland_licensed)
				.add("since", since).add("city_region", city_region)
				.add("small_area", small_area).add("twolevel_id", twolevel_id)
				.add("three_id", three_id).add("sortorder_id", sortorder_id)
				.add("goods_lprice", goods_lprice)
				.add("goods_hprice", goods_hprice)
				.add("fristlevel", fristlevel).add("buy_userid", buy_userid)
				.add("pageNow", String.valueOf(pageNow))
				.add("pageSize", String.valueOf(pageSize)).build();

		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				String body = response.body().string();
				return body;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}// execute

		return null;
	}

}
