package com.quwu.xinwo.home_page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.HP_Good_GoodsAdapter;
import com.quwu.xinwo.adapter.HP_HotAdapter;
import com.quwu.xinwo.adapter.HP_NearbyAdapter;
import com.quwu.xinwo.adapter.HP_Prefecture_ViewPagerAdapter;
import com.quwu.xinwo.baidumap.LocationService;
import com.quwu.xinwo.bean.HP_Good_GoodsBean;
import com.quwu.xinwo.bean.HP_HotBean;
import com.quwu.xinwo.bean.HP_NearbyBean;
import com.quwu.xinwo.bean.HP_Prefecture_ViewPagerBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.HP_BannerEntity;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.mine.Help_CenterActivity;
import com.quwu.xinwo.mine.My_RedpacketActivity;
import com.quwu.xinwo.mine.RechargeActivity;
import com.quwu.xinwo.mine.WelfareActivity;
import com.quwu.xinwo.mywight.MyGridView;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.mywight.MyViewPager;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.product_details.All_price_Product_DetailsActivity;
import com.quwu.xinwo.product_details.Crowdfunding_Product_DetailsActivity;
import com.quwu.xinwo.pull.PullToRefreshLayout;
import com.quwu.xinwo.pull.PullToRefreshLayout.OnRefreshListener;
import com.quwu.xinwo.pull.PullableScrollView;
import com.quwu.xinwo.pull.PullableScrollView.OnScrollListener;
import com.quwu.xinwo.pull.ScrollViewListener;
import com.quwu.xinwo.release.Goods_CategoryActivity;
import com.quwu.xinwo.until.SetW_H;
import com.quwu.xinwo.until.Tool;
import com.quwu.xinwo.webpage.WebPage;

/**
 * 
 * 首页界面
 * 
 * @author ZhouShaoPeng
 * 
 * */
public class Home_Page_Activity extends Fragment implements OnClickListener,
		OnScrollListener, ScrollViewListener {
	/**
	 * 热门
	 * */
	private MyGridView gridView;
	private HP_HotAdapter adapter;

	private View view;
	private MyViewPager prefecture_viewpager;
	private MyViewPager nearby_viewpager;
	private List<View> viewList;// view数组
	private List<View> nearbyList;// view数组
	private View view1, view2;

	private PullableScrollView scrollView;
	private PullToRefreshLayout parent_layout;
	private LinearLayout layout2;
	private LinearLayout layout1;
	private HashMap<Integer, Integer> scrollYMap;

	private LinearLayout hp_usedLin, hp_rent_outLin, hp_freeLin, hp_auctionLin;
	private LinearLayout hp_usedLin1, hp_rent_outLin1, hp_freeLin1,
			hp_auctionLin1;
	private ImageView hp_usedImage, hp_rent_outIamge, hp_freeImage,
			hp_auctionImage;
	private ImageView hp_usedImage1, hp_rent_outIamge1, hp_freeImage1,
			hp_auctionImage1;
	private LinearLayout home_page_areaLin;// 地区选择
	private TextView home_page_areaText;// 定位地区
	private TextView hp_usedText, hp_rent_outText, hp_freeText, hp_auctionText;
	private TextView hp_usedText1, hp_rent_outText1, hp_freeText1,
			hp_auctionText1;
	private Button home_page_classifyBtn;// 分类
	private TextView home_page_Ed;// 搜索框

	private ImageView home_page_prefecture_viewpager_image1;
	private ImageView home_page_prefecture_viewpager_image2;
	private LinearLayout home_page_prefecture_viewpagerLin;

	private ImageView home_page_nearby_viewpager_image1;
	private ImageView home_page_nearby_viewpager_image2;
	private LinearLayout home_page_nearby_viewpager_Lin;

	/**
	 * 分类
	 * */
	private LinearLayout hp_classesLin1;
	private LinearLayout hp_classesLin2;
	private LinearLayout hp_classesLin3;
	private LinearLayout hp_classesLin4;
	private LinearLayout hp_classesLin5;
	private LinearLayout hp_classesLin6;
	private LinearLayout hp_classesLin7;
	private LinearLayout hp_classesLin8;
	private LinearLayout hp_classesLin9;
	private LinearLayout hp_classesLin10;

	private ImageView hp_classesImage1;
	private ImageView hp_classesImage2;
	private ImageView hp_classesImage3;
	private ImageView hp_classesImage4;
	private ImageView hp_classesImage5;
	private ImageView hp_classesImage6;
	private ImageView hp_classesImage7;
	private ImageView hp_classesImage8;
	private ImageView hp_classesImage9;
	private ImageView hp_classesImage10;

	private TextView hp_classesText1;
	private TextView hp_classesText2;
	private TextView hp_classesText3;
	private TextView hp_classesText4;
	private TextView hp_classesText5;
	private TextView hp_classesText6;
	private TextView hp_classesText7;
	private TextView hp_classesText8;
	private TextView hp_classesText9;
	private TextView hp_classesText10;
	/**
	 * banner
	 * */
	private MyViewPager viewPager;
	private ImageView image1, image2, image3, image4, image5, image6, image7,
			image8, image9, image10;
	private List<HP_BannerEntity> urlList;
	private int currentItem = 0; // 当前图片的索引号
	private ScheduledExecutorService scheduledExecutorService;// 定时任务（banner）
	// 服务器返回数据
	private String banner_photo;// //图片路径
	private String banner_jumpmode;
	// 1-跳转到商品详情，
	// 2-跳转到加入清单的页面(指一类商品)，
	// 3-网页页面，
	// 4-跳公告，
	// 5-跳首页，
	// 6-跳活动，
	// 7-跳定位城市的最新商品，
	// 8-跳定位城市的最热商品，
	// 9-什么都不跳
	// 10-跳充值
	// 11-跳红包页面
	// 12-跳积分页面
	private String banner_url;// 网页的网址(如果不跳网页则没有数据)
	private String banner_search;// 搜索关键字
	private String banner_title;// 活动标题
	/**
	 * 分类
	 * */
	private String oneshowpictures_id;// //自增ID
	private String oneshowpictures_url;// //图片路径
	private String oneshowpictures_title;// //图片对应的标题
	private String oneshowpictures_state;// //0-显示，1-不显示(是否使用该图标)
	private String oneshowpictures_frame;// //框架值
	private List<HP_BannerEntity> classifyList;
	/**
	 * 专区
	 * */
	private String twoshowpictures_id;// 自增ID
	private String twoshowpictures_url;// 图片路径
	private String twoshowpictures_title;// 图片对应的标题
	private String twoshowpictures_state;// 0-显示，1-不显示(是否使用该图标)
	private String twoshowpictures_describe;// 描述
	private List<HP_BannerEntity> prefectureList;
	private List<HP_Prefecture_ViewPagerBean> list;
	private List<HP_Prefecture_ViewPagerBean> list1;
	/**
	 * 众筹好货
	 * */
	private String goods_photo;// 图片路径
	private String goods_name;// 商品名称
	private String good_description;// 商品描述
	private String participant_person;// 已经参与的人次
	private String total_person;// 总需
	private String goods_id;// 商品ID
	private List<HP_Good_GoodsBean> Good_Goods_list;
	/**
	 * 附近好货
	 * */
	private String fourshowpictures_id;//自增ID
	private String	fourshowpictures_url;//图片路径
	private String	fourshowpictures_title;//图片对应的标题
	private String	fourshowpictures_state;//0-显示，1-不显示(是否使用该图标)
	List<HP_NearbyBean> Nearby_list ;
	List<HP_NearbyBean> Nearby_list1 ;
	/**
	 * 附近好货
	 * */
	private String	browse_volume;///商品的浏览量
	private String	goods_price;//商品价格

	private List<HP_BannerEntity> json_list;

	/**
	 * 小喇叭
	 * */
	private TextSwitcher hp_TextSwitcher;
	private int curStr;
	

	/**
	 * 定位省份
	 * */
	public static String Province;
	/**
	 * 定位城市
	 * */
	public static String City;
	/**
	 * 定位区县
	 * */
	public static String County;
	/**
	 * 定位成功失败(-1：正在定位中、0：定位成功、1定位失败)
	 * */
	public static int Location_Succeed=-1;
	private LocationService locationService;
	// 要显示的文本
	String[] titles = new String[] { "关注：瑞士维氏军刀 新品满200-50",
			"关注：家居家装焕新季，讲199减100！", "关注：带上相机去春游，尼康低至477", "关注：价格惊呆！电信千兆光纤上市" };

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			if (viewPager != null) {
				viewPager.setCurrentItem(currentItem);
			}
			switch (msg.what) {
			case 1:
				image1.setVisibility(View.VISIBLE);
				break;
			case 2:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				break;
			case 3:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				break;
			case 4:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				break;
			case 5:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				break;
			case 6:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				break;
			case 7:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				image7.setVisibility(View.VISIBLE);
				break;
			case 8:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				image7.setVisibility(View.VISIBLE);
				image8.setVisibility(View.VISIBLE);
				break;
			case 9:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				image7.setVisibility(View.VISIBLE);
				image8.setVisibility(View.VISIBLE);
				image9.setVisibility(View.VISIBLE);
				break;
			case 10:
				image1.setVisibility(View.VISIBLE);
				image2.setVisibility(View.VISIBLE);
				image3.setVisibility(View.VISIBLE);
				image4.setVisibility(View.VISIBLE);
				image5.setVisibility(View.VISIBLE);
				image6.setVisibility(View.VISIBLE);
				image7.setVisibility(View.VISIBLE);
				image8.setVisibility(View.VISIBLE);
				image9.setVisibility(View.VISIBLE);
				image10.setVisibility(View.VISIBLE);
				break;
			case 10000:// 程序异常提示
				MyToast.Toast(getActivity(), MyApp.Program_Exception_Prompt);
				break;
			case 10001:// 没有数据异常提示
				MyToast.Toast(getActivity(), MyApp.NODATA_Prompt);
				break;
			default:
				break;
			}
		};
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.home_page1, container, false);
		findID();
		isTextSwitcher();
		new BannerTask().executeOnExecutor(Executors.newCachedThreadPool());
		return view;
	}
@Override
public void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
	// -----------location config ------------
			locationService = ((MyApp) getActivity().getApplication()).locationService;
			// 获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
			locationService.registerListener(mListener);
			// 注册监听
			int type = getActivity().getIntent().getIntExtra("from", 0);
			if (type == 0) {
				locationService.setLocationOption(locationService
						.getDefaultLocationClientOption());
			} else if (type == 1) {
				locationService.setLocationOption(locationService.getOption());
			}
			locationService.start();// 定位SDK
}
	private BDLocationListener mListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (null != location
					&& location.getLocType() != BDLocation.TypeServerError) {
				logMsg(location.getProvince(), location.getCity(), location.getDistrict());
			}
		}
	};
	/**
	 * 显示请求字符串
	 * 
	 * @param str
	 */
	public void logMsg(String province, String city, String district) {
		if (province!=null||city!=null||district!=null) {
			Province=Tool.deleteString(province);
			City=Tool.deleteString(city);
			County=Tool.deleteString(district);
			locationService.stop();
		}else {
			Province="北京(定位失败)";
			City="北京(定位失败)";
			County="北京(定位失败)";
		}
		home_page_areaText.setText(City);
	}
	/**
	 * 
	 * 加载banner图片
	 * 
	 * */
	private class BannerTask extends AsyncTask<LayoutInflater, Void, Void> {
		@Override
		protected Void doInBackground(LayoutInflater... params) {
			scrollYMap = new HashMap<Integer, Integer>();
			urlList = new ArrayList<HP_BannerEntity>();
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "banner/selectBanner.do", "", "");
			if (string != null) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(string);
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
									new TypeToken<List<HP_BannerEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								banner_photo = json_list.get(i)
										.getBanner_photo();
								banner_jumpmode = json_list.get(i)
										.getBanner_jumpmode();
								banner_url = json_list.get(i).getBanner_url();
								banner_search = json_list.get(i)
										.getBanner_search();
								banner_title = json_list.get(i)
										.getBanner_title();
								urlList.add(new HP_BannerEntity(
										MyApp.base_address + banner_photo,
										banner_jumpmode, banner_url,
										banner_search, banner_title));
							}
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

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			isViewPager(urlList);// 加载banner图片
			new ClassifyTask().executeOnExecutor(Executors
					.newCachedThreadPool());
		}
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (scheduledExecutorService == null) {
			scheduledExecutorService = Executors
					.newSingleThreadScheduledExecutor();
			// 当Activity显示出来后，每5秒钟切换一次图片显示
			scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1,
					5, TimeUnit.SECONDS);
		}
	}

	private void findID() {
		gridView = (MyGridView) view.findViewById(R.id.hot_gridview);
		viewPager = (MyViewPager) view.findViewById(R.id.hp_vp);
		home_page_nearby_viewpager_image1 = (ImageView) view
				.findViewById(R.id.home_page_nearby_viewpager_image1);
		home_page_nearby_viewpager_image2 = (ImageView) view
				.findViewById(R.id.home_page_nearby_viewpager_image2);
		home_page_nearby_viewpager_Lin=(LinearLayout) view.findViewById(R.id.home_page_nearby_viewpager_Lin);

		home_page_prefecture_viewpager_image1 = (ImageView) view
				.findViewById(R.id.home_page_prefecture_viewpager_image1);
		home_page_prefecture_viewpager_image2 = (ImageView) view
				.findViewById(R.id.home_page_prefecture_viewpager_image2);
		home_page_prefecture_viewpagerLin = (LinearLayout) view
				.findViewById(R.id.home_page_prefecture_viewpagerLin);

		layout2 = (LinearLayout) view.findViewById(R.id.home_page_layout1);
		layout1 = (LinearLayout) view.findViewById(R.id.home_page_layout2);
		scrollView = (PullableScrollView) view
				.findViewById(R.id.home_page_scrollView);
		parent_layout = (PullToRefreshLayout) view
				.findViewById(R.id.home_page_parent);
		scrollView.setOnScrollListener(this);
		scrollView.setScrollViewListener(this);
		parent_layout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
				new BannerTask().executeOnExecutor(Executors
						.newCachedThreadPool());
				scheduledExecutorService=null;
			}

			@Override
			public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

			}
		});
		parent_layout.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					public void onGlobalLayout() {
						onScroll(scrollView.getScrollY());
					}
				});

		hp_classesLin1 = (LinearLayout) view.findViewById(R.id.hp_classesLin1);
		hp_classesLin2 = (LinearLayout) view.findViewById(R.id.hp_classesLin2);
		hp_classesLin3 = (LinearLayout) view.findViewById(R.id.hp_classesLin3);
		hp_classesLin4 = (LinearLayout) view.findViewById(R.id.hp_classesLin4);
		hp_classesLin5 = (LinearLayout) view.findViewById(R.id.hp_classesLin5);
		hp_classesLin6 = (LinearLayout) view.findViewById(R.id.hp_classesLin6);
		hp_classesLin7 = (LinearLayout) view.findViewById(R.id.hp_classesLin7);
		hp_classesLin8 = (LinearLayout) view.findViewById(R.id.hp_classesLin8);
		hp_classesLin9 = (LinearLayout) view.findViewById(R.id.hp_classesLin9);
		hp_classesLin10 = (LinearLayout) view
				.findViewById(R.id.hp_classesLin10);
		hp_classesLin1.setOnClickListener(this);
		hp_classesLin2.setOnClickListener(this);
		hp_classesLin3.setOnClickListener(this);
		hp_classesLin4.setOnClickListener(this);
		hp_classesLin5.setOnClickListener(this);
		hp_classesLin6.setOnClickListener(this);
		hp_classesLin7.setOnClickListener(this);
		hp_classesLin8.setOnClickListener(this);
		hp_classesLin9.setOnClickListener(this);
		hp_classesLin10.setOnClickListener(this);

		hp_classesImage1 = (ImageView) view.findViewById(R.id.hp_classesImage1);
		hp_classesImage2 = (ImageView) view.findViewById(R.id.hp_classesImage2);
		hp_classesImage3 = (ImageView) view.findViewById(R.id.hp_classesImage3);
		hp_classesImage4 = (ImageView) view.findViewById(R.id.hp_classesImage4);
		hp_classesImage5 = (ImageView) view.findViewById(R.id.hp_classesImage5);
		hp_classesImage6 = (ImageView) view.findViewById(R.id.hp_classesImage6);
		hp_classesImage7 = (ImageView) view.findViewById(R.id.hp_classesImage7);
		hp_classesImage8 = (ImageView) view.findViewById(R.id.hp_classesImage8);
		hp_classesImage9 = (ImageView) view.findViewById(R.id.hp_classesImage9);
		hp_classesImage10 = (ImageView) view
				.findViewById(R.id.hp_classesImage10);
		SetW_H.setLinearLayout(getActivity(), hp_classesImage1, 0.08);
		SetW_H.setLinearLayout(getActivity(), hp_classesImage2, 0.08);
		SetW_H.setLinearLayout(getActivity(), hp_classesImage3, 0.08);
		SetW_H.setLinearLayout(getActivity(), hp_classesImage4, 0.08);
		SetW_H.setLinearLayout(getActivity(), hp_classesImage5, 0.08);
		SetW_H.setLinearLayout(getActivity(), hp_classesImage6, 0.08);
		SetW_H.setLinearLayout(getActivity(), hp_classesImage7, 0.08);
		SetW_H.setLinearLayout(getActivity(), hp_classesImage8, 0.08);
		SetW_H.setLinearLayout(getActivity(), hp_classesImage9, 0.08);
		SetW_H.setLinearLayout(getActivity(), hp_classesImage10, 0.08);

		hp_classesText1 = (TextView) view.findViewById(R.id.hp_classesText1);
		hp_classesText2 = (TextView) view.findViewById(R.id.hp_classesText2);
		hp_classesText3 = (TextView) view.findViewById(R.id.hp_classesText3);
		hp_classesText4 = (TextView) view.findViewById(R.id.hp_classesText4);
		hp_classesText5 = (TextView) view.findViewById(R.id.hp_classesText5);
		hp_classesText6 = (TextView) view.findViewById(R.id.hp_classesText6);
		hp_classesText7 = (TextView) view.findViewById(R.id.hp_classesText7);
		hp_classesText8 = (TextView) view.findViewById(R.id.hp_classesText8);
		hp_classesText9 = (TextView) view.findViewById(R.id.hp_classesText9);
		hp_classesText10 = (TextView) view.findViewById(R.id.hp_classesText10);

		home_page_classifyBtn = (Button) view
				.findViewById(R.id.home_page_classifyBtn);
		home_page_classifyBtn.setOnClickListener(this);

		prefecture_viewpager = (MyViewPager) view
				.findViewById(R.id.home_page_prefecture_viewpager);
		nearby_viewpager = (MyViewPager) view
				.findViewById(R.id.home_page_nearby_viewpager);

		home_page_areaLin = (LinearLayout) view
				.findViewById(R.id.home_page_areaLin);
		home_page_areaText = (TextView) view
				.findViewById(R.id.home_page_areaText);
	
		home_page_areaLin.setOnClickListener(this);

		View view1 = view.findViewById(R.id.home_page_layout2);
		View view2 = view.findViewById(R.id.home_page_layout1);

		hp_usedText = (TextView) view1.findViewById(R.id.hp_usedText);
		hp_rent_outText = (TextView) view1.findViewById(R.id.hp_rent_outText);
		hp_freeText = (TextView) view1.findViewById(R.id.hp_freeText);
		hp_auctionText = (TextView) view1.findViewById(R.id.hp_auctionText);
		hp_usedText1 = (TextView) view2.findViewById(R.id.hp_usedText);
		hp_rent_outText1 = (TextView) view2.findViewById(R.id.hp_rent_outText);
		hp_freeText1 = (TextView) view2.findViewById(R.id.hp_freeText);
		hp_auctionText1 = (TextView) view2.findViewById(R.id.hp_auctionText);

		hp_usedLin = (LinearLayout) view1.findViewById(R.id.hp_usedLin);
		hp_rent_outLin = (LinearLayout) view1.findViewById(R.id.hp_rent_outLin);
		hp_freeLin = (LinearLayout) view1.findViewById(R.id.hp_freeLin);
		hp_auctionLin = (LinearLayout) view1.findViewById(R.id.hp_auctionLin);
		hp_usedLin1 = (LinearLayout) view2.findViewById(R.id.hp_usedLin);
		hp_rent_outLin1 = (LinearLayout) view2
				.findViewById(R.id.hp_rent_outLin);
		hp_freeLin1 = (LinearLayout) view2.findViewById(R.id.hp_freeLin);
		hp_auctionLin1 = (LinearLayout) view2.findViewById(R.id.hp_auctionLin);

		hp_usedLin.setOnClickListener(this);
		hp_rent_outLin.setOnClickListener(this);
		hp_freeLin.setOnClickListener(this);
		hp_auctionLin.setOnClickListener(this);
		hp_usedLin1.setOnClickListener(this);
		hp_rent_outLin1.setOnClickListener(this);
		hp_freeLin1.setOnClickListener(this);
		hp_auctionLin1.setOnClickListener(this);

		hp_usedImage = (ImageView) view1.findViewById(R.id.hp_usedImage);
		hp_rent_outIamge = (ImageView) view1
				.findViewById(R.id.hp_rent_outImage);
		hp_freeImage = (ImageView) view1.findViewById(R.id.hp_freeImage);
		hp_auctionImage = (ImageView) view1.findViewById(R.id.hp_auctionImage);
		hp_usedImage1 = (ImageView) view2.findViewById(R.id.hp_usedImage);
		hp_rent_outIamge1 = (ImageView) view2
				.findViewById(R.id.hp_rent_outImage);
		hp_freeImage1 = (ImageView) view2.findViewById(R.id.hp_freeImage);
		hp_auctionImage1 = (ImageView) view2.findViewById(R.id.hp_auctionImage);

		hp_TextSwitcher = (TextSwitcher) view
				.findViewById(R.id.hp_TextSwitcher);
		home_page_Ed = (TextView) view.findViewById(R.id.home_page_Ed);
		home_page_Ed.setOnClickListener(this);

		image1 = (ImageView) view.findViewById(R.id.hp_vp_image1);
		image2 = (ImageView) view.findViewById(R.id.hp_vp_image2);
		image3 = (ImageView) view.findViewById(R.id.hp_vp_image3);
		image4 = (ImageView) view.findViewById(R.id.hp_vp_image4);
		image5 = (ImageView) view.findViewById(R.id.hp_vp_image5);
		image6 = (ImageView) view.findViewById(R.id.hp_vp_image6);
		image7 = (ImageView) view.findViewById(R.id.hp_vp_image7);
		image8 = (ImageView) view.findViewById(R.id.hp_vp_image8);
		image9 = (ImageView) view.findViewById(R.id.hp_vp_image9);
		image10 = (ImageView) view.findViewById(R.id.hp_vp_image10);
	}

	/**
	 * 
	 * 专区
	 * 
	 * */
	private void isPrefecture_ViewPager(List<HP_Prefecture_ViewPagerBean> list,
			List<HP_Prefecture_ViewPagerBean> list1) {
		MyGridView gridView;
		MyGridView gridView1;
		HP_Prefecture_ViewPagerAdapter adapter;
		HP_Prefecture_ViewPagerAdapter adapter1;

		viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
		LayoutInflater inflater = getActivity().getLayoutInflater();
		view1 = inflater.inflate(R.layout.prefecture_viewpager, null);
		view2 = inflater.inflate(R.layout.prefecture_viewpager1, null);

		gridView = (MyGridView) view1
				.findViewById(R.id.prefecture_viewpager_gridview);
		gridView1 = (MyGridView) view2
				.findViewById(R.id.prefecture_viewpager_gridview1);

		adapter = new HP_Prefecture_ViewPagerAdapter(list, getActivity());
		adapter1 = new HP_Prefecture_ViewPagerAdapter(list1, getActivity());

		gridView.setAdapter(adapter);
		gridView1.setAdapter(adapter1);

		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 去掉点击时候的背景色
		gridView1.setSelector(new ColorDrawable(Color.TRANSPARENT));

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(), "点击了gridView" + position, 100)
						.show();
			}
		});
		gridView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(), "点击了gridView1" + position, 100)
						.show();
			}
		});

		viewList.add(view1);
		if (this.list1.size() == 0) {// 当只有四条数据的时候，第二个页面就不要了
			home_page_prefecture_viewpagerLin.setVisibility(View.GONE);
		} else {
			viewList.add(view2);
		}

		prefecture_viewpager.setAdapter(new MyPagerAdaptere(viewList));
		prefecture_viewpager
				.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						ImageView[] imageViews = initializeIamgeBg1();
						for (int j = 0; j < imageViews.length; j++) {
							imageViews[arg0]
									.setBackgroundResource(R.drawable.goods_details_point_yellow);
							prefecture_viewpager.setCurrentItem(arg0);
							if (arg0 != j) {
								imageViews[j]
										.setBackgroundResource(R.drawable.point_mor);
							}
						}
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {

					}
				});
	}

	public class MyPagerAdaptere extends PagerAdapter {

		private List<View> list;

		public MyPagerAdaptere(List<View> list) {
			super();
			this.list = list;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {

			return list.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(list.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(list.get(position));
			return list.get(position);
		}
	}

	/**
	 * 
	 * 众筹好货
	 * 
	 * */
	private void isGood_GoodsGridview(List<HP_Good_GoodsBean> Good_Goods_list) {

		MyGridView gridView = null;
		HP_Good_GoodsAdapter adapter;

		gridView = (MyGridView) view.findViewById(R.id.hp_good_goods_gridview);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

		int size = Good_Goods_list.size();
		int length = 100;
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int gridviewWidth = (int) (size * (length + 4) * density);
		int itemWidth = (int) (length * density);

		@SuppressWarnings("deprecation")
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
		gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
		gridView.setColumnWidth(itemWidth); // 设置列表项宽
		gridView.setHorizontalSpacing(20); // 设置列表项水平间距
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setNumColumns(size); // 设置列数量=列表集合数

		adapter = new HP_Good_GoodsAdapter(getActivity(), Good_Goods_list);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(),
						Crowdfunding_Product_DetailsActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 
	 * 附近好货
	 * 
	 * **/
	private void isNearby(List<HP_NearbyBean> list,List<HP_NearbyBean> list1 ) {

		MyGridView gridView;
		MyGridView gridView1;
	

		HP_NearbyAdapter adapter;
		HP_NearbyAdapter adapter1;

		nearbyList = new ArrayList<View>();// 将要分页显示的View装入数组中
		LayoutInflater inflater = getActivity().getLayoutInflater();
		view1 = inflater.inflate(R.layout.nearby, null);
		view2 = inflater.inflate(R.layout.nearby1, null);
		gridView = (MyGridView) view1.findViewById(R.id.nearby_gridview);
		gridView1 = (MyGridView) view2.findViewById(R.id.nearby_gridview1);

		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridView1.setSelector(new ColorDrawable(Color.TRANSPARENT));

		if (list!=null) {
			gridView.setNumColumns(list.size());
			gridView1.setNumColumns(list1.size());
		
		}
		adapter = new HP_NearbyAdapter(getActivity(), list);
		adapter1 = new HP_NearbyAdapter(getActivity(), list1);

		gridView.setAdapter(adapter);
		gridView1.setAdapter(adapter1);

		nearbyList.add(view1);
		if (this.list1.size()==0) {
			home_page_nearby_viewpager_Lin.setVisibility(View.GONE);
		}else {
			nearbyList.add(view2);
		}

		nearby_viewpager.setAdapter(new MyPagerAdaptere(nearbyList));
		
		nearby_viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				ImageView[] imageViews = initializeIamgeBg2();
				for (int j = 0; j < imageViews.length; j++) {
					imageViews[arg0]
							.setBackgroundResource(R.drawable.goods_details_point_yellow);
					nearby_viewpager.setCurrentItem(arg0);
					if (arg0 != j) {
						imageViews[j]
								.setBackgroundResource(R.drawable.point_mor);
					}
				}
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), DigitalActivity.class);
				startActivity(intent);
			}
		});
		gridView1.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), DigitalActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 
	 * 热门
	 * 
	 * */
	private void isHot(int a) {

		final List<HP_HotBean> list = new ArrayList<HP_HotBean>();
		isTextColor();
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridView.setHorizontalSpacing(35); // 设置列表项水平间距
		if (a == 0) {
			hp_usedText.setTextColor(getActivity().getResources().getColor(
					R.color.淡红色));
			for (int i = 0; i < 10; i++) {
				list.add(new HP_HotBean(
						"http://www.wed114.cn/jiehun/uploads/allimg/160426/39_160426110624_1.jpg",
						"风景美如画1", "¥8888", "345浏览", "我要买"));
			}
		} else if (a == 1) {
			hp_rent_outText.setTextColor(getActivity().getResources().getColor(
					R.color.淡红色));
			for (int i = 0; i < 7; i++) {
				list.add(new HP_HotBean(
						"http://img0.pchouse.com.cn/pchouse/1502/09/1018216_130.jpg",
						"风景美如画2", "¥8888", "456浏览", "出个价"));
			}
		} else if (a == 2) {
			hp_freeText.setTextColor(getActivity().getResources().getColor(
					R.color.淡红色));
			for (int i = 0; i < 3; i++) {
				list.add(new HP_HotBean(
						"http://img3.duitang.com/uploads/item/201605/25/20160525093455_Qa2yR.thumb.700_0.jpeg",
						"风景美如画3", "¥8888", "567想要", "我想要"));
			}
		} else if (a == 3) {
			hp_auctionText.setTextColor(getActivity().getResources().getColor(
					R.color.淡红色));
			for (int i = 0; i < 4; i++) {
				list.add(new HP_HotBean(
						"http://pic10.nipic.com/20101103/5063545_000227976000_2.jpg",
						"风景美如画4", "¥999", "567出价", "出个价"));
			}
		}
		adapter = new HP_HotAdapter(getActivity(), list, a);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (Tool.isFastDoubleClick()) {
					return;
				} else {
					Intent intent = new Intent(getActivity(),
							All_price_Product_DetailsActivity.class);
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.hp_usedLin:
				isHot(0);
				hp_usedImage.setVisibility(View.VISIBLE);
				hp_rent_outIamge.setVisibility(View.INVISIBLE);
				hp_freeImage.setVisibility(View.INVISIBLE);
				hp_auctionImage.setVisibility(View.INVISIBLE);
				hp_usedImage1.setVisibility(View.VISIBLE);
				hp_rent_outIamge1.setVisibility(View.INVISIBLE);
				hp_freeImage1.setVisibility(View.INVISIBLE);
				hp_auctionImage1.setVisibility(View.INVISIBLE);
				break;
			case R.id.hp_rent_outLin:
				isHot(1);
				hp_usedImage.setVisibility(View.INVISIBLE);
				hp_rent_outIamge.setVisibility(View.VISIBLE);
				hp_freeImage.setVisibility(View.INVISIBLE);
				hp_auctionImage.setVisibility(View.INVISIBLE);
				hp_usedImage1.setVisibility(View.INVISIBLE);
				hp_rent_outIamge1.setVisibility(View.VISIBLE);
				hp_freeImage1.setVisibility(View.INVISIBLE);
				hp_auctionImage1.setVisibility(View.INVISIBLE);
				break;
			case R.id.hp_freeLin:
				isHot(2);
				hp_usedImage.setVisibility(View.INVISIBLE);
				hp_rent_outIamge.setVisibility(View.INVISIBLE);
				hp_freeImage.setVisibility(View.VISIBLE);
				hp_auctionImage.setVisibility(View.INVISIBLE);
				hp_usedImage1.setVisibility(View.INVISIBLE);
				hp_rent_outIamge1.setVisibility(View.INVISIBLE);
				hp_freeImage1.setVisibility(View.VISIBLE);
				hp_auctionImage.setVisibility(View.INVISIBLE);
				break;
			case R.id.hp_auctionLin:
				isHot(3);
				hp_usedImage.setVisibility(View.INVISIBLE);
				hp_rent_outIamge.setVisibility(View.INVISIBLE);
				hp_freeImage.setVisibility(View.INVISIBLE);
				hp_auctionImage.setVisibility(View.VISIBLE);
				hp_usedImage1.setVisibility(View.INVISIBLE);
				hp_rent_outIamge1.setVisibility(View.INVISIBLE);
				hp_freeImage1.setVisibility(View.INVISIBLE);
				hp_auctionImage1.setVisibility(View.VISIBLE);
				break;
			case R.id.home_page_areaLin:
				intent = new Intent(getActivity(), AreaActivity.class);
				break;
			case R.id.home_page_classifyBtn:
				intent = new Intent(getActivity(), Goods_CategoryActivity.class);
				break;
			case R.id.home_page_Ed:
				intent = new Intent(getActivity(), SearchActivity.class);
				break;
			case R.id.hp_classesLin1:
				intent = new Intent(getActivity(), City_WideActivity.class);
				break;
			case R.id.hp_classesLin2:
				intent = new Intent(getActivity(), Crowd_FundingActivity.class);
				break;
			case R.id.hp_classesLin3:
				intent = new Intent(getActivity(), RentActivity.class);
				break;
			case R.id.hp_classesLin4:
				intent = new Intent(getActivity(), AuctionActivity.class);
				break;
			case R.id.hp_classesLin5:
				intent = new Intent(getActivity(), Free_DeliveryActivity.class);
				break;
			case R.id.hp_classesLin6:
				intent = new Intent(getActivity(), DigitalActivity.class);
				intent.putExtra("state", "0");
				break;
			case R.id.hp_classesLin7:
				intent = new Intent(getActivity(), DigitalActivity.class);
				intent.putExtra("state", "1");
				break;
			case R.id.hp_classesLin8:
				intent = new Intent(getActivity(), QCActivity.class);
				break;
			case R.id.hp_classesLin9:
				intent = new Intent(getActivity(), ChoicenessActivity.class);
				break;
			case R.id.hp_classesLin10:
				intent = new Intent(getActivity(), RechargeActivity.class);
				break;

			default:
				break;
			}
			if (intent != null) {
				startActivity(intent);
			}
		}
	}

	private void isTextColor() {
		hp_auctionText.setTextColor(getActivity().getResources().getColor(
				R.color.黑字体色));
		hp_freeText.setTextColor(getActivity().getResources().getColor(
				R.color.黑字体色));
		hp_rent_outText.setTextColor(getActivity().getResources().getColor(
				R.color.黑字体色));
		hp_usedText.setTextColor(getActivity().getResources().getColor(
				R.color.黑字体色));
	}

	/**
	 * 
	 * banner
	 * 
	 * */
	private void isViewPager(final List<HP_BannerEntity> url) {
		Message message = new Message();// 根据图片张数来显示下标小圆（最多十张）
		message.what = url.size();
		handler.sendMessage(message);
		viewPager.setAdapter(new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {

				return arg0 == arg1;
			}

			@Override
			public int getCount() {

				return url.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View) object);
			}

			@Override
			public Object instantiateItem(ViewGroup container,
					final int position) {
				View view1 = getActivity().getLayoutInflater().inflate(
						R.layout.hp_vp_image, null);
				ImageView bg = (ImageView) view1
						.findViewById(R.id.hp_vp_imageBg);
				ImageloaderUtils.BannerImageLoader(url.get(position)
						.getBanner_photo(), bg, getActivity());
				bg.setOnClickListener(new OnClickListener() {// 点击banner图片跳转到对应的页面

					@Override
					public void onClick(View v) {
						Intent intent = null;
						if (url.get(position).getBanner_jumpmode().equals("1")) {// 跳转到商品详情
							intent = new Intent(getActivity(),
									Crowdfunding_Product_DetailsActivity.class);
						} else if (url.get(position).getBanner_jumpmode()
								.equals("2")) {// 跳转到加入清单的页面(指一类商品)

						} else if (url.get(position).getBanner_jumpmode()
								.equals("3")) {// 网页页面
							intent = new Intent(getActivity(), WebPage.class);
							intent.putExtra("url", url.get(position)
									.getBanner_url());
							intent.putExtra("title", url.get(position)
									.getBanner_title());
						} else if (url.get(position).getBanner_jumpmode()
								.equals("4")) {// 跳帮助中心
							intent = new Intent(getActivity(),
									Help_CenterActivity.class);
						} else if (url.get(position).getBanner_jumpmode()
								.equals("5")) {// 跳首页

						} else if (url.get(position).getBanner_jumpmode()
								.equals("6")) {// 跳活动
							intent = new Intent(getActivity(), WebPage.class);
							intent.putExtra("url", url.get(position)
									.getBanner_url());
							intent.putExtra("title", url.get(position)
									.getBanner_title());
						} else if (url.get(position).getBanner_jumpmode()
								.equals("7")) {// 跳定位城市的最新商品
							intent = new Intent(getActivity(),
									City_WideActivity.class);
						} else if (url.get(position).getBanner_jumpmode()
								.equals("8")) {// 跳定位城市的最热商品
							intent = new Intent(getActivity(),
									City_WideActivity.class);
						} else if (url.get(position).getBanner_jumpmode()
								.equals("9")) {// 什么都不跳

						} else if (url.get(position).getBanner_jumpmode()
								.equals("10")) {// 跳充值
							intent = new Intent(getActivity(),
									RechargeActivity.class);
						} else if (url.get(position).getBanner_jumpmode()
								.equals("11")) {// 跳红包页面
							intent = new Intent(getActivity(),
									My_RedpacketActivity.class);
						} else if (url.get(position).getBanner_jumpmode()
								.equals("12")) {// 跳积分页面
							intent = new Intent(getActivity(),
									WelfareActivity.class);
						}
						if (intent != null) {
							getActivity().startActivity(intent);
						}
					}
				});
				container.addView(view1);
				return view1;
			}

		});
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				ImageView[] imageViews = initializeIamgeBg();
				for (int j = 0; j < imageViews.length; j++) {
					imageViews[arg0]
							.setBackgroundResource(R.drawable.point_current);
					viewPager.setCurrentItem(arg0);
					currentItem = arg0;
					if (arg0 != j) {
						imageViews[j].setBackgroundResource(R.drawable.point);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	/**
	 * 初始化banner标识圆
	 * */
	private ImageView[] initializeIamgeBg() {
		ImageView imageViews[] = { image1, image2, image3, image4, image5,
				image6, image7, image8, image9, image10 };
		return imageViews;
	}

	/**
	 * 初始化活动标识圆
	 * */
	private ImageView[] initializeIamgeBg1() {
		ImageView imageViews[] = { home_page_prefecture_viewpager_image1,
				home_page_prefecture_viewpager_image2 };
		return imageViews;
	}

	/**
	 * 初始化附近标识圆
	 * */
	private ImageView[] initializeIamgeBg2() {
		ImageView imageViews[] = { home_page_nearby_viewpager_image1,
				home_page_nearby_viewpager_image2 };
		return imageViews;
	}

	/**
	 * banner自动跳动定时器
	 * */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % urlList.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	/**
	 * 小喇叭
	 * */
	private void isTextSwitcher() {
		hp_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
			@Override
			public View makeView() {
				final TextView tv = new TextView(getActivity());
				tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
				// tv.setPadding(20, 20, 20, 20);
				return tv;
			}
		});
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				hp_TextSwitcher.setText(titles[curStr++ % titles.length]);
				handler.postDelayed(this, 5000);
			}
		}, 1000);
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

	private class HotTask extends AsyncTask<String, Void, Integer>{

		@Override
		protected Integer doInBackground(String... params) {
			String string=OKHTTP_POST.doPost1(MyApp.base_address+"showpictures/selectfive.do", "firstlevel_id", params[0]);
			if (string != null) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(string);
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
									new TypeToken<List<HP_BannerEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								goods_name=json_list.get(i).getGoods_name();
							}
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
		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
		
		}
	}
	
	/**
	 * 分类图标(图标与标题的获取)
	 * */
	private class ClassifyTask extends AsyncTask<Void, Void, Void> {
		private String classifString;// 分类返回的数据
		private String PrefectureString;// 专区返回的数据
		private String Goodgoods_one;// 众筹好货第一部分的数据
		private String Goodgoods_two;// 众筹好货第二部分的数据
		private String nearby;// 众筹好货第二部分的数据

		@Override
		protected Void doInBackground(Void... params) {
			classifyList = new ArrayList<HP_BannerEntity>();
			prefectureList = new ArrayList<HP_BannerEntity>();
			Good_Goods_list=new ArrayList<HP_Good_GoodsBean>();
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "showpictures/selectshowpictures.do", "", "");
			if (string != null) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(string);
					classifString = jsonObject.getString("1");
					PrefectureString = jsonObject.getString("2");
					Goodgoods_one = jsonObject.getString("31");
					Goodgoods_two = jsonObject.getString("32");
					nearby = jsonObject.getString("4");
					if (classifString != null) {
						if (classifString.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (classifString.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(classifString,
									new TypeToken<List<HP_BannerEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								oneshowpictures_id = json_list.get(i)
										.getOneshowpictures_id();
								oneshowpictures_url = json_list.get(i)
										.getOneshowpictures_url();
								oneshowpictures_title = json_list.get(i)
										.getOneshowpictures_title();
								oneshowpictures_state = json_list.get(i)
										.getOneshowpictures_state();
								oneshowpictures_frame = json_list.get(i)
										.getOneshowpictures_frame();
								classifyList.add(new HP_BannerEntity(
										oneshowpictures_id, MyApp.base_address
												+ oneshowpictures_url,
										oneshowpictures_title,
										oneshowpictures_state,
										oneshowpictures_frame, ""));
							}
						}
					}
					if (PrefectureString != null) {
						if (PrefectureString.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (PrefectureString.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(PrefectureString,
									new TypeToken<List<HP_BannerEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								twoshowpictures_id = json_list.get(i)
										.getTwoshowpictures_id();
								twoshowpictures_url = json_list.get(i)
										.getTwoshowpictures_url();
								twoshowpictures_title = json_list.get(i)
										.getTwoshowpictures_title();
								twoshowpictures_state = json_list.get(i)
										.getTwoshowpictures_state();
								twoshowpictures_describe = json_list.get(i)
										.getTwoshowpictures_describe();
								prefectureList.add(new HP_BannerEntity(
										twoshowpictures_id, MyApp.base_address
												+ twoshowpictures_url,
										twoshowpictures_title,
										twoshowpictures_state,
										twoshowpictures_describe, 0));
							}
						}
					}
					if (Goodgoods_one != null) {
						if (Goodgoods_one.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (Goodgoods_one.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(Goodgoods_one,
									new TypeToken<List<HP_BannerEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								goods_photo = json_list.get(i).getGoods_photo();
								goods_name = json_list.get(i).getGoods_name();
								good_description = json_list.get(i)
										.getGood_description();
								participant_person = json_list.get(i)
										.getParticipant_person();
								total_person = json_list.get(i)
										.getTotal_person();
								goods_id = json_list.get(i).getGoods_id();
								Good_Goods_list.add(new HP_Good_GoodsBean(
										MyApp.base_address + goods_photo,
										goods_name, good_description,
										participant_person, total_person,
										goods_id));
							}
						}
					} 
					if (Goodgoods_two != null) {
						if (Goodgoods_two.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (Goodgoods_two.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(Goodgoods_two,
									new TypeToken<List<HP_BannerEntity>>() {
							}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								goods_photo = json_list.get(i).getGoods_photo();
								goods_name = json_list.get(i).getGoods_name();
								good_description = json_list.get(i)
										.getGood_description();
								participant_person = json_list.get(i)
										.getParticipant_person();
								total_person = json_list.get(i)
										.getTotal_person();
								goods_id = json_list.get(i).getGoods_id();
								Good_Goods_list.add(new HP_Good_GoodsBean(
										MyApp.base_address + goods_photo,
										goods_name, good_description,
										participant_person, total_person,
										goods_id));
							}
						}
					} 
					if (nearby != null) {
						Nearby_list=new ArrayList<HP_NearbyBean>();
						Nearby_list1=new ArrayList<HP_NearbyBean>();
						if (nearby.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (nearby.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(nearby,
									new TypeToken<List<HP_BannerEntity>>() {
							}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								fourshowpictures_id = json_list.get(i).getFourshowpictures_id();
								fourshowpictures_url = json_list.get(i).getFourshowpictures_url();
								fourshowpictures_title = json_list.get(i).getFourshowpictures_title();
								fourshowpictures_state = json_list.get(i).getFourshowpictures_state();
								if (i<7) {
									Nearby_list.add(new HP_NearbyBean(MyApp.base_address+fourshowpictures_url, fourshowpictures_title));
								}else {
									Nearby_list1.add(new HP_NearbyBean(MyApp.base_address+fourshowpictures_url, fourshowpictures_title));
								}
							}
						}
					} 
					else {
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

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (classifString != null) {
				for (int i = 0; i < classifyList.size(); i++) {
					if (classifyList.get(0).getOneshowpictures_state()
							.equals("1")) {
						hp_classesLin1.setVisibility(View.GONE);
					} else {
						ImageloaderUtils.BannerImageLoader(classifyList.get(0)
								.getOneshowpictures_url(), hp_classesImage1,
								getActivity());
						hp_classesText1.setText(classifyList.get(0)
								.getOneshowpictures_title());
					}

					if (classifyList.get(1).getOneshowpictures_state()
							.equals("1")) {
						hp_classesLin2.setVisibility(View.GONE);
					} else {
						ImageloaderUtils.BannerImageLoader(classifyList.get(1)
								.getOneshowpictures_url(), hp_classesImage2,
								getActivity());
						hp_classesText2.setText(classifyList.get(1)
								.getOneshowpictures_title());
					}

					if (classifyList.get(2).getOneshowpictures_state()
							.equals("1")) {
						hp_classesLin3.setVisibility(View.GONE);
					} else {
						ImageloaderUtils.BannerImageLoader(classifyList.get(2)
								.getOneshowpictures_url(), hp_classesImage3,
								getActivity());
						hp_classesText3.setText(classifyList.get(2)
								.getOneshowpictures_title());
					}

					if (classifyList.get(3).getOneshowpictures_state()
							.equals("1")) {
						hp_classesLin4.setVisibility(View.GONE);
					} else {
						ImageloaderUtils.BannerImageLoader(classifyList.get(3)
								.getOneshowpictures_url(), hp_classesImage4,
								getActivity());
						hp_classesText4.setText(classifyList.get(3)
								.getOneshowpictures_title());
					}

					if (classifyList.get(4).getOneshowpictures_state()
							.equals("1")) {
						hp_classesLin5.setVisibility(View.GONE);
					} else {
						ImageloaderUtils.BannerImageLoader(classifyList.get(4)
								.getOneshowpictures_url(), hp_classesImage5,
								getActivity());
						hp_classesText5.setText(classifyList.get(4)
								.getOneshowpictures_title());
					}

					if (classifyList.get(5).getOneshowpictures_state()
							.equals("1")) {
						hp_classesLin6.setVisibility(View.GONE);
					} else {
						ImageloaderUtils.BannerImageLoader(classifyList.get(5)
								.getOneshowpictures_url(), hp_classesImage6,
								getActivity());
						hp_classesText6.setText(classifyList.get(5)
								.getOneshowpictures_title());
					}
					if (classifyList.get(6).getOneshowpictures_state()
							.equals("1")) {
						hp_classesLin7.setVisibility(View.GONE);
					} else {
						ImageloaderUtils.BannerImageLoader(classifyList.get(6)
								.getOneshowpictures_url(), hp_classesImage7,
								getActivity());
						hp_classesText7.setText(classifyList.get(6)
								.getOneshowpictures_title());
					}

					if (classifyList.get(7).getOneshowpictures_state()
							.equals("1")) {
						hp_classesLin8.setVisibility(View.GONE);
					} else {
						ImageloaderUtils.BannerImageLoader(classifyList.get(7)
								.getOneshowpictures_url(), hp_classesImage8,
								getActivity());
						hp_classesText8.setText(classifyList.get(7)
								.getOneshowpictures_title());
					}
					if (classifyList.get(8).getOneshowpictures_state()
							.equals("1")) {
						hp_classesLin9.setVisibility(View.GONE);
					} else {
						ImageloaderUtils.BannerImageLoader(classifyList.get(8)
								.getOneshowpictures_url(), hp_classesImage9,
								getActivity());
						hp_classesText9.setText(classifyList.get(8)
								.getOneshowpictures_title());
					}
					if (classifyList.get(9).getOneshowpictures_state()
							.equals("1")) {
						hp_classesLin10.setVisibility(View.GONE);
					} else {
						ImageloaderUtils.BannerImageLoader(classifyList.get(9)
								.getOneshowpictures_url(), hp_classesImage10,
								getActivity());
						hp_classesText10.setText(classifyList.get(9)
								.getOneshowpictures_title());
					}
				}
			}
			if (PrefectureString != null) {
				list = new ArrayList<HP_Prefecture_ViewPagerBean>();
				list1 = new ArrayList<HP_Prefecture_ViewPagerBean>();
				for (int i = 0; i < prefectureList.size(); i++) {
					if (i < 4) {
						list.add(new HP_Prefecture_ViewPagerBean(prefectureList
								.get(i).getTwoshowpictures_title(),
								prefectureList.get(i)
										.getTwoshowpictures_describe(),
								prefectureList.get(i).getTwoshowpictures_url()));
					} else {
						list1.add(new HP_Prefecture_ViewPagerBean(
								prefectureList.get(i)
										.getTwoshowpictures_title(),
								prefectureList.get(i)
										.getTwoshowpictures_describe(),
								prefectureList.get(i).getTwoshowpictures_url()));
					}
				}
				isPrefecture_ViewPager(list, list1);
			}
			if (Good_Goods_list!=null) {
				isGood_GoodsGridview(Good_Goods_list);
			}
				if (Nearby_list!=null||Nearby_list1!=null) {
					isNearby(Nearby_list,Nearby_list1);
				}
			parent_layout.refreshFinish(PullToRefreshLayout.SUCCEED);// 刷新完成
		}
	}
	
}
