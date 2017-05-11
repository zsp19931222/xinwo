package com.quwu.xinwo.release;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Release_GridViewAdapter;
import com.quwu.xinwo.baidumap.LocationService;
import com.quwu.xinwo.bean.KeyBoardData;
import com.quwu.xinwo.cropimage.CropHelper;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mywight.CustomProgressDialog2;
import com.quwu.xinwo.mywight.MyAlertDialog;
import com.quwu.xinwo.mywight.MyGridView;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.mywight.RecordButton;
import com.quwu.xinwo.mywight.RecordButton.OnFinishedRecordListener;
import com.quwu.xinwo.newphoto.MyAdapter;
import com.quwu.xinwo.pickerview.OptionsPickerView;
import com.quwu.xinwo.pickerview.RegionDAO;
import com.quwu.xinwo.pickerview.RegionInfo;
import com.quwu.xinwo.popupwindow.Release_Photo_Pop;
import com.quwu.xinwo.until.CameraUtils;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.InputFilterSpace;
import com.quwu.xinwo.until.KeyboardUtil1;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.OSUtils;
import com.quwu.xinwo.until.Tool;

/**
 * 
 * 发布界面
 * 
 * @author ZhouShaoPeng
 * 
 * */

@SuppressWarnings("serial")
public class Release_Activity extends Activity implements OnClickListener,
		Serializable {
	private LocationService locationService;

	private Context ctx;
	private Activity act;
	private KeyboardView keyboardView;
	private LinearLayout releaseLin;
	private Button release_confirmBtn;

	/**
	 * 录音路径
	 * */
	private String path = Environment.getExternalStorageDirectory()
			.getAbsolutePath();

	/**
	 * 地区选择器
	 * */
	@SuppressWarnings("rawtypes")
	private OptionsPickerView pvOptions;
	private ArrayList<RegionInfo> item1;
	private ArrayList<ArrayList<RegionInfo>> item2 = new ArrayList<ArrayList<RegionInfo>>();
	private ArrayList<ArrayList<ArrayList<RegionInfo>>> item3 = new ArrayList<ArrayList<ArrayList<RegionInfo>>>();

	/**
	 * 添加图片
	 * */
	private MyGridView gridView;// 装图片的gridview
	private Release_GridViewAdapter adapter;
	private List<String> gridviewList;
	private CropHelper cropHelper;
	private String mTempPhotoPath;
	public static long time;
	private List<Long> times;
	public static List<String> photolist;
	public static List<String> camearlist;

	private EditText titleEd;// 商品标题输入框
	private EditText describeEd;// 商品描述输入框

	private Button playerBtn;// 播放器
	private RecordButton recordBtn;// 录音器

	private Button areaBtn;// 地区

	private CheckBox cordelesBox;// 卖钱
	private CheckBox auctionBox;// 拍卖
	private CheckBox rent_outBox;// 出租
	private CheckBox freeBox;// 免费送

	private LinearLayout classifyLin;// 分类
	private TextView classifyText;// 分类

	private LinearLayout cordelesLin;
	private LinearLayout auctionLin;
	private LinearLayout rent_outLin;
	private LinearLayout freeLin;

	private LinearLayout checkBox;
	private LinearLayout free_checkboxLin;

	private LinearLayout release_cordelesBoxLin;
	private LinearLayout release_auctionBoxLin;
	private LinearLayout release_rent_outBoxLin;
	private LinearLayout release_freeBoxLin;

	private LinearLayout release_cordeles_Box1Lin;
	private LinearLayout release_cordeles_Box2Lin;

	// 标签选项框
	private CheckBox release_label_box1;
	private CheckBox release_label_box2;
	private CheckBox release_label_box3;
	private CheckBox release_label_box4;
	private CheckBox release_label_box5;
	private CheckBox release_label_box6;
	private CheckBox release_label_box7;
	private CheckBox release_free_checkboxLin_box1;
	private CheckBox release_free_checkboxLin_box2;
	/**
	 * 卖钱
	 * */
	private EditText selling_priceEd;
	private CheckBox cordelesBox1, cordelesBox2;
	private ImageView cordeles_questionImage;

	/**
	 * 拍卖
	 * */
	private EditText auction_startPriceEd;
	private EditText auction_retainPriceEd;
	private EditText auction_rangeEd;
	private EditText auction_cash_depositEd;
	private ImageView auction_questionImage;
	/**
	 * 出租
	 * */
	private EditText rent_out_valueEd;
	private EditText rent_out_freightEd;
	private EditText rent_out_rentEd;
	private EditText rent_out_tenancy_termEd;
	private ImageView rent_out_rentImage;
	private TextView release_rent_out_rent_yearText;
	private TextView release_rent_out_tenancy_term_yearText;
	/**
	 * 免费送
	 * */
	private EditText free_freightEd;
	private EditText free_QQEd;

	private List<KeyBoardData> list;

	private static final int CLASSIFY = 1;// 分类返回码
	private static final int KEYBOARD = 2;// 自定义软键盘返回码

	/**
	 * 获取用户输入值
	 * */

	private String goods_name;
	private String good_description;
	private String good_region;
	private String city_region;
	private String small_area;
	private String twolevel_id;
	private String three_id;
	private String goods_price;
	private String normal_use;
	private String warranty_period;
	private String no_repair;
	private String brand_new;
	private String jingdong;
	private String mainland_licensed;
	private String since;
	private String whether_pack;// 是否包邮，1-免运费，0-要运费

	private String startauctionprice;// 起拍价 如 200
	private String retainprice;// 保留价 如2000
	private String increaserange;// 加价幅度 如 10

	private String rentprice;// 租金 如 30元/月时上传 30元/月
	private String renttime;// 租期 如 5个月则上传5月

	private String contactQQ;// QQ号码
	/**
	 * 自定义键盘返回数据
	 * */
	private String selling_price;// 卖价
	private String original_price;// 原价
	private String freight;// 运费
	private String reserve_price;// 保留价
	private String goods_value;// 商品价值

	private boolean check;// 是否包邮
	
	/**
	 * 上传提示框
	 * */
private CustomProgressDialog2 pd;
private LinearLayout layout;

private Button release_saveBtn;//发表按钮

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.release);
		FinishActivity.finish(R.id.release_returnRel, Release_Activity.this);
		path += "/xinwo.mp3";
		findID();
		ctx = this;
		act = this;
		times = new ArrayList<Long>();
		photolist = new ArrayList<String>();
		camearlist = new ArrayList<String>();
		isRecording();
		isGridView();
		isAreaSelector();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// -----------location config ------------
		locationService = ((MyApp) getApplication()).locationService;
		// 获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
		locationService.registerListener(mListener);
		// 注册监听
		int type = getIntent().getIntExtra("from", 0);
		if (type == 0) {
			locationService.setLocationOption(locationService
					.getDefaultLocationClientOption());
		} else if (type == 1) {
			locationService.setLocationOption(locationService.getOption());
		}
		locationService.start();// 定位SDK
	}

	private void findID() {
		release_saveBtn=(Button) findViewById(R.id.release_saveBtn);
		release_saveBtn.setOnClickListener(this);
		
		pd = new CustomProgressDialog2(Release_Activity.this,
				R.style.dialog);
		pd.setCancelable(false);
		
		layout=(LinearLayout) findViewById(R.id.releaseLin);
		
		release_cordelesBoxLin = (LinearLayout) findViewById(R.id.release_cordelesBoxLin);
		release_auctionBoxLin = (LinearLayout) findViewById(R.id.release_auctionBoxLin);
		release_rent_outBoxLin = (LinearLayout) findViewById(R.id.release_rent_outBoxLin);
		release_freeBoxLin = (LinearLayout) findViewById(R.id.release_freeBoxLin);
		release_cordelesBoxLin.setOnClickListener(this);
		release_auctionBoxLin.setOnClickListener(this);
		release_rent_outBoxLin.setOnClickListener(this);
		release_freeBoxLin.setOnClickListener(this);

		release_cordeles_Box1Lin = (LinearLayout) findViewById(R.id.release_cordeles_Box1Lin);
		release_cordeles_Box2Lin = (LinearLayout) findViewById(R.id.release_cordeles_Box2Lin);

		release_cordeles_Box1Lin.setOnClickListener(this);
		release_cordeles_Box2Lin.setOnClickListener(this);

		release_label_box1 = (CheckBox) findViewById(R.id.release_label_box1);
		release_label_box2 = (CheckBox) findViewById(R.id.release_label_box2);
		release_label_box3 = (CheckBox) findViewById(R.id.release_label_box3);
		release_label_box4 = (CheckBox) findViewById(R.id.release_label_box4);
		release_label_box5 = (CheckBox) findViewById(R.id.release_label_box5);
		release_label_box6 = (CheckBox) findViewById(R.id.release_label_box6);
		release_label_box7 = (CheckBox) findViewById(R.id.release_label_box7);
		release_free_checkboxLin_box1 = (CheckBox) findViewById(R.id.release_free_checkboxLin_box1);
		release_free_checkboxLin_box2 = (CheckBox) findViewById(R.id.release_free_checkboxLin_box2);

		release_confirmBtn = (Button) findViewById(R.id.release_confirmBtn);
		release_confirmBtn.setOnClickListener(this);

		releaseLin = (LinearLayout) findViewById(R.id.releaseLin);
		keyboardView = (KeyboardView) findViewById(R.id.keyboard_view);
		gridView = (MyGridView) findViewById(R.id.release_gridview);

		checkBox = (LinearLayout) findViewById(R.id.release_checkBox);
		free_checkboxLin = (LinearLayout) findViewById(R.id.release_free_checkboxLin);

		titleEd = (EditText) findViewById(R.id.release_titleEd);
		describeEd = (EditText) findViewById(R.id.release_decribeEd);
		InputFilterSpace.inputFilterSpace(titleEd, 20);
		InputFilterSpace.inputFilterSpace(describeEd, 50);

		playerBtn = (Button) findViewById(R.id.release_pleyerBtn);
		playerBtn.setOnClickListener(this);
		areaBtn = (Button) findViewById(R.id.release_areaBtn);

		cordelesBox = (CheckBox) findViewById(R.id.release_cordelesBox);
		auctionBox = (CheckBox) findViewById(R.id.release_auctionBox);
		rent_outBox = (CheckBox) findViewById(R.id.release_rent_outBox);
		freeBox = (CheckBox) findViewById(R.id.release_freeBox);

		classifyLin = (LinearLayout) findViewById(R.id.release_classifyLin);
		classifyText = (TextView) findViewById(R.id.release_classifyText);
		classifyLin.setOnClickListener(this);

		cordelesLin = (LinearLayout) findViewById(R.id.release_cordelesLin);
		auctionLin = (LinearLayout) findViewById(R.id.release_auctionLin);
		rent_outLin = (LinearLayout) findViewById(R.id.release_rent_outLin);
		freeLin = (LinearLayout) findViewById(R.id.release_freeLin);

		selling_priceEd = (EditText) findViewById(R.id.release_cordeles_selling_priceEd);
		cordelesBox1 = (CheckBox) findViewById(R.id.release_cordeles_Box1);
		cordelesBox2 = (CheckBox) findViewById(R.id.release_cordeles_Box2);
		cordeles_questionImage = (ImageView) findViewById(R.id.release_cordeles_questionIamge);
		cordeles_questionImage.setOnClickListener(this);
		selling_priceEd.setOnTouchListener(new MyTouchListener(selling_priceEd,
				"1"));

		auction_startPriceEd = (EditText) findViewById(R.id.release_auction_startPriceEd);
		auction_retainPriceEd = (EditText) findViewById(R.id.release_auction_retainPriceEd);
		auction_rangeEd = (EditText) findViewById(R.id.release_auction_rangeEd);
		auction_cash_depositEd = (EditText) findViewById(R.id.release_auction_cash_depositEd);
		auction_questionImage = (ImageView) findViewById(R.id.release_auction_questionImage);
		auction_questionImage.setOnClickListener(this);

		auction_retainPriceEd.setOnTouchListener(new MyTouchListener(
				auction_retainPriceEd, "2"));
		isKeyBoard(auction_startPriceEd);
		isKeyBoard(auction_rangeEd);
		isKeyBoard(auction_cash_depositEd);
		
		/**
		 * 
		 * */
		auction_rangeEd.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (s!=null) {
				
			}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});

		rent_out_valueEd = (EditText) findViewById(R.id.release_rent_out_valueEd);
		rent_out_freightEd = (EditText) findViewById(R.id.release_rent_out_freightEd);
		rent_out_rentEd = (EditText) findViewById(R.id.release_rent_out_rentEd);
		rent_out_tenancy_termEd = (EditText) findViewById(R.id.release_rent_out_tenancy_termEd);
		rent_out_rentImage = (ImageView) findViewById(R.id.release_rent_out_rentImage);
		release_rent_out_rent_yearText = (TextView) findViewById(R.id.release_rent_out_rent_yearText);
		release_rent_out_tenancy_term_yearText = (TextView) findViewById(R.id.release_rent_out_tenancy_term_yearText);
		rent_out_rentImage.setOnClickListener(this);
		release_rent_out_rent_yearText.setOnClickListener(this);
		release_rent_out_tenancy_term_yearText.setOnClickListener(this);

		rent_out_valueEd.setOnTouchListener(new MyTouchListener(
				rent_out_valueEd, "3"));
		rent_out_freightEd.setOnTouchListener(new MyTouchListener(
				rent_out_freightEd, "3"));
		isKeyBoard(rent_out_rentEd);
		isKeyBoard(rent_out_tenancy_termEd);

		free_freightEd = (EditText) findViewById(R.id.release_free_freightEd);
		free_QQEd = (EditText) findViewById(R.id.release_free_QQEd);
		isKeyBoard(free_freightEd);
		isKeyBoard(free_QQEd);

	}
	/*****
	 * @see copy funtion to you project
	 *      定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
	 * 
	 */
	private BDLocationListener mListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			if (null != location
					&& location.getLocType() != BDLocation.TypeServerError) {
				good_region = location.getProvince();
				city_region = location.getCity();
				small_area = location.getDistrict();
				logMsg1(location.getProvince(), location.getCity(),
						location.getDistrict());
			}
		}

	};

	/**
	 * 显示请求字符串
	 * 
	 * @param str
	 */
	public void logMsg1(String province, String city, String district) {
		try {
			if (province != null) {
				String[] province_strings = province.split("");
				String[] city_strings = city.split("");
				String[] district_strings = district.split("");
				StringBuffer province_sb = new StringBuffer(256);
				StringBuffer city_sb = new StringBuffer(256);
				StringBuffer district_sb = new StringBuffer(256);
				for (int i = 0; i < province_strings.length - 1; i++) {
					if (province_strings[i].equals("省")
							|| province_strings[i].equals("市")) {
						province_strings[i] = "";
					}
					if (i < province_strings.length - 2) {
						province_sb.append(province_strings[i]);
					} else {
						province_sb.append(province_strings[i]);
					}
				}
				for (int i = 0; i < city_strings.length - 1; i++) {
					if (city_strings[i].equals("市")) {
						city_strings[i] = "";
					}
					if (i < city_strings.length - 2) {
						city_sb.append(city_strings[i]);
					} else {
						city_sb.append(city_strings[i]);
					}
				}

				for (int i = 0; i < district_strings.length - 1; i++) {
					if (district_strings[i].equals("区")) {
						district_strings[i] = "";
					}
					district_sb.append(district_strings[i]);
				}
				areaBtn.setText(province_sb.toString() + " "
						+ city_sb.toString() + " " + district);
				locationService.stop();
			} else {
				areaBtn.setText("获取地区失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			switch (v.getId()) {
			case R.id.release_pleyerBtn:// 播放录音按钮
				isPlayRecording();
				new CountDownTimer(isRecordingTime(), 1000) {
					public void onTick(long millisUntilFinished) {
						playerBtn.setText((millisUntilFinished / 1000) + "s");
						playerBtn.setEnabled(false);
					}

					public void onFinish() {
						playerBtn.setText((isRecordingTime() / 1000) + "s");
						playerBtn.setEnabled(true);
					}
				}.start();
				break;
			case R.id.release_saveBtn:// 保存按钮（保存所有信息）

				break;
			case R.id.release_cordeles_questionIamge:// 卖钱疑难解释
				isQuestionImage("疑难解释", "拍卖疑难解释说明", "确定", Release_Activity.this);
				break;
			case R.id.release_auction_questionImage:// 拍卖疑难解释
				isQuestionImage("疑难解释", "拍卖疑难解释说明", "确定", Release_Activity.this);
				break;
			case R.id.release_rent_out_rentImage:// 出租疑难解释
				isQuestionImage("疑难解释", "拍卖疑难解释说明", "确定", Release_Activity.this);
				break;
			case R.id.release_classifyLin:// 分类
				// Goods_Category_Pop pop=new
				// Goods_Category_Pop(Release_Activity.this);
				// pop.showPopupWindow(classifyLin);
				Intent intent = new Intent(Release_Activity.this,
						Area_selection1Activity.class);
				startActivityForResult(intent, 20);
				break;
			case R.id.release_rent_out_rent_yearText:// 弹出POP
				showPopupWindow(release_rent_out_rent_yearText, 1);
				releaseLin.setAlpha(0.5f);
				break;
			case R.id.release_rent_out_tenancy_term_yearText:// 弹出POP
				showPopupWindow(release_rent_out_tenancy_term_yearText, 2);
				releaseLin.setAlpha(0.5f);
				break;
			case R.id.release_confirmBtn:// 确定发布
				pd.show();
				layout.setAlpha(0.5f);
				if (cordelesBox.isChecked()) {
					new ConfirmTask().executeOnExecutor(Executors
							.newCachedThreadPool());
				} else if (auctionBox.isChecked()) {
					new ConfirmTask1().executeOnExecutor(Executors
							.newCachedThreadPool());
				} else if (rent_outBox.isChecked()) {
					new ConfirmTask2().executeOnExecutor(Executors
							.newCachedThreadPool());
				} else if (freeBox.isChecked()) {
					new ConfirmTask3().executeOnExecutor(Executors
							.newCachedThreadPool());
				}
				break;
			case R.id.release_cordelesBoxLin:// 卖钱
				cordelesBox.setChecked(true);
				auctionBox.setChecked(false);
				rent_outBox.setChecked(false);
				freeBox.setChecked(false);
				isCheckCordeles();
				break;
			case R.id.release_auctionBoxLin:// 拍卖
				cordelesBox.setChecked(false);
				auctionBox.setChecked(true);
				rent_outBox.setChecked(false);
				freeBox.setChecked(false);
				isCheckAuction();
				break;
			case R.id.release_rent_outBoxLin:// 出租
				cordelesBox.setChecked(false);
				auctionBox.setChecked(false);
				rent_outBox.setChecked(true);
				freeBox.setChecked(false);
				isCheckRentOut();
				break;
			case R.id.release_freeBoxLin:// 免费送
				cordelesBox.setChecked(false);
				auctionBox.setChecked(false);
				rent_outBox.setChecked(false);
				freeBox.setChecked(true);
				isCheckFree();
				break;
			case R.id.release_cordeles_Box1Lin:// 全价出售
				cordelesBox1.setChecked(true);
				cordelesBox2.setChecked(false);
				break;
			case R.id.release_cordeles_Box2Lin:// 众筹出售
				cordelesBox2.setChecked(true);
				cordelesBox1.setChecked(false);
				break;
			default:
				break;
			}
		}
	}

	// 选择卖钱
	private void isCheckCordeles() {
		if (cordelesBox.isChecked()) {
			auctionBox.setChecked(false);
			rent_outBox.setChecked(false);
			freeBox.setChecked(false);
		}
		cordelesLin.setVisibility(View.VISIBLE);
		auctionLin.setVisibility(View.GONE);
		rent_outLin.setVisibility(View.GONE);
		freeLin.setVisibility(View.GONE);
		checkBox.setVisibility(View.VISIBLE);
		free_checkboxLin.setVisibility(View.GONE);
	}

	// 选择拍卖
	private void isCheckAuction() {
		if (auctionBox.isChecked()) {
			cordelesBox.setChecked(false);
			rent_outBox.setChecked(false);
			freeBox.setChecked(false);
		}
		cordelesLin.setVisibility(View.GONE);
		auctionLin.setVisibility(View.VISIBLE);
		rent_outLin.setVisibility(View.GONE);
		freeLin.setVisibility(View.GONE);
		checkBox.setVisibility(View.VISIBLE);
		free_checkboxLin.setVisibility(View.GONE);
	}

	// 选择出租
	private void isCheckRentOut() {
		if (rent_outBox.isChecked()) {
			cordelesBox.setChecked(false);
			auctionBox.setChecked(false);
			freeBox.setChecked(false);
		}
		cordelesLin.setVisibility(View.GONE);
		auctionLin.setVisibility(View.GONE);
		rent_outLin.setVisibility(View.VISIBLE);
		freeLin.setVisibility(View.GONE);
		checkBox.setVisibility(View.VISIBLE);
		free_checkboxLin.setVisibility(View.GONE);
	}

	// 选择免费送
	private void isCheckFree() {
		if (freeBox.isChecked()) {
			cordelesBox.setChecked(false);
			auctionBox.setChecked(false);
			rent_outBox.setChecked(false);
		}
		cordelesLin.setVisibility(View.GONE);
		auctionLin.setVisibility(View.GONE);
		rent_outLin.setVisibility(View.GONE);
		freeLin.setVisibility(View.VISIBLE);
		checkBox.setVisibility(View.GONE);
		free_checkboxLin.setVisibility(View.VISIBLE);
	}

	// 疑难解释
	public static void isQuestionImage(String title, String message,
			String btn, Context context) {
		final MyAlertDialog alertDialog = new MyAlertDialog(context);
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setNegativeButton(btn, new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});
	}

	// 选择分类
	@SuppressWarnings("unchecked")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 20:
			if (data != null) {
				if (requestCode == 20) {
					String sheng = data.getExtras().getString("sheng");
					String shi = data.getExtras().getString("shi");
					twolevel_id = data.getExtras().getString("id1");
					three_id = data.getExtras().getString("id2");
					classifyText.setText(sheng + " " + shi + " ");
				}
			}
			break;
		case CLASSIFY:// 分类返回数据
			if (data != null) {
				String selection_sort = data.getExtras().getString(
						"selection_sort");// 得到新Activity 关闭后返回的数据
				classifyText.setText(selection_sort);
			}
			break;
		case KEYBOARD:// 自定义键盘返回数据
			if (data != null) {
				list = (List<KeyBoardData>) data
						.getSerializableExtra("keyboard_data");
				for (int i = 0; i < list.size(); i++) {
					selling_price = list.get(i).getSelling_price();
					goods_value = list.get(i).getGoods_value();
					original_price = list.get(i).getOriginal_price();
					reserve_price = list.get(i).getReserve_price();
					freight = list.get(i).getFreight();
					check = list.get(i).isCheck();
				}
				if (cordelesBox.isChecked() == true) {
					if (selling_price != null) {
						selling_priceEd.setText(selling_price);
					}
				} else if (auctionBox.isChecked() == true) {
					if (reserve_price != null) {
						auction_retainPriceEd.setText(reserve_price);
					}
				} else if (rent_outBox.isChecked() == true) {
					if (goods_value != null && freight != null) {
						rent_out_valueEd.setText(goods_value);
						if (check == true) {
							rent_out_freightEd.setText("0");
						} else {
							rent_out_freightEd.setText(freight);
						}
					}
				}
			}
			break;
		case 10:// 相册图片
			if (data != null) {
				for (int i = 0; i < MyAdapter.mSelectedImage.size(); i++) {
					photolist.add((String) data.getExtras().get(
							String.valueOf(i)));
				}
			}
			break;
		case 2017:// 拍照图片
			Log.e("onActivityResult", "接收到拍照图片");
			cropHelper.getDataFromCamera(data);
			break;
		case 2108:
			if (data != null && data.getParcelableExtra("data") != null) {
				for (int i = 0; i < times.size(); i++) {
					File f = new File(
							OSUtils.getSdCardDirectory(getApplicationContext())
									+ times.get(i) + "head1.png");
					if (f.exists()) {
						camearlist.add(OSUtils
								.getSdCardDirectory(getApplicationContext())
								+ times.get(i) + "head1.png");
					}
				}
				for (int k = 0; k < camearlist.size() - 1; k++) {
					for (int j = camearlist.size() - 1; j > k; j--) {
						if (camearlist.get(k).equals(camearlist.get(j))) {
							camearlist.remove(j);
						}
					}
				}
				for (int i = 0; i < camearlist.size(); i++) {
					photolist.add(camearlist.get(i));
				}
			}
			break;
		case CameraUtils.RequestCode.FLAG_REQUEST_CAMERA_VIDEO:// 视频
			if (data != null) {
				photolist.add(CameraUtils.CAMERA_VIDEO);
			}
			break;
		default:
			break;
		}
		for (int j = 0; j < photolist.size(); j++) {
			if (photolist.get(j).equals(
					"drawable://" + R.drawable.fb_icn_carema)) {
				photolist.remove(j);
			}
			if (photolist.get(j)
					.equals("drawable://" + R.drawable.fb_icn_video)) {
				photolist.remove(j);
			}
		}
		photolist.add("drawable://" + R.drawable.fb_icn_carema);
		photolist.add("drawable://" + R.drawable.fb_icn_video);
		for (int k = 0; k < photolist.size() - 1; k++) {
			for (int j = photolist.size() - 1; j > k; j--) {
				if (photolist.get(k).equals(photolist.get(j))) {
					photolist.remove(j);
				}
			}
		}
		adapter = new Release_GridViewAdapter(photolist, this,
				Release_Activity.this, times);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (photolist.get(position).equals(
						"drawable://" + R.drawable.fb_icn_carema)) {
					int j = 0;
					for (int i = 0; i < photolist.size(); i++) {
						if (photolist.get(i).equals(CameraUtils.CAMERA_VIDEO)) {
							j++;
						}
					}
					if (j == 1) {
						if (photolist.size() < 13) {
							Date dt = new Date();
							time = dt.getTime();
							times.add(time);
							cropHelper = new CropHelper(
									Release_Activity.this,
									OSUtils.getSdCardDirectory(getApplicationContext())
											+ time + "head1.png");
							mTempPhotoPath = OSUtils
									.getSdCardDirectory(getApplicationContext())
									+ time + "head1.png";
							Release_Photo_Pop pop = new Release_Photo_Pop(
									Release_Activity.this, cropHelper,
									mTempPhotoPath);
							pop.showPopupWindow(gridView);
						} else {
							Toast.makeText(getApplicationContext(),
									"最多添加十张图片哦~", 10).show();
						}
					} else {
						if (photolist.size() < 12) {
							Date dt = new Date();
							time = dt.getTime();
							times.add(time);
							cropHelper = new CropHelper(
									Release_Activity.this,
									OSUtils.getSdCardDirectory(getApplicationContext())
											+ time + "head1.png");
							mTempPhotoPath = OSUtils
									.getSdCardDirectory(getApplicationContext())
									+ time + "head1.png";
							Release_Photo_Pop pop = new Release_Photo_Pop(
									Release_Activity.this, cropHelper,
									mTempPhotoPath);
							pop.showPopupWindow(gridView);
						} else {
							Toast.makeText(getApplicationContext(),
									"最多添加十张图片哦~", 10).show();
						}
					}
				} else if (photolist.get(position).equals(
						"drawable://" + R.drawable.fb_icn_video)) {

					int j = 0;
					for (int i = 0; i < photolist.size(); i++) {
						if (photolist.get(i).equals(CameraUtils.CAMERA_VIDEO)) {
							j++;
						}
					}
					if (j != 1) {
						CameraUtils.openCameraForVideo(Release_Activity.this);
					} else {
						Toast.makeText(getApplicationContext(), "只能添加一部视频哦~",
								10).show();
					}
				} else if (photolist.get(position).equals(
						CameraUtils.CAMERA_VIDEO)) {// 播放录像
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW); // 意图是打开文件
					Uri data = Uri.parse("file:///" + CameraUtils.CAMERA_VIDEO); // 要打开的文件路径和文件名
					intent.setDataAndType(data, "video/mp4"); // 打开文件的格式
					startActivity(intent);
				}
			}
		});
		adapter.notifyDataSetChanged();
	}

	private class MyTouchListener implements OnTouchListener {

		private EditText editText;
		private String state;

		public MyTouchListener(EditText editText, String state) {
			super();
			this.editText = editText;
			this.state = state;
			editText.setFocusable(false);
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			editText.setInputType(InputType.TYPE_NULL); // 关闭软键盘，这样当点击该edittext的时候，不会弹出系统自带的输入法
			// 阻止手指离开时onTouch方法的继续执行
			if (event.getAction() == MotionEvent.ACTION_DOWN
					|| event.getAction() == MotionEvent.ACTION_MOVE) {
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				new KeyboardUtil1(act, ctx, editText).hideKeyboard();
				Intent intent = new Intent(Release_Activity.this,
						KeyBoardActivity.class);
				intent.putExtra("state", state);
				startActivityForResult(intent, KEYBOARD);
			}
			return false;
		}
	}

	private void isKeyBoard(final EditText editText) {
		handler.sendMessage(handler.obtainMessage(0, editText));
	}

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:// 弹起数字键盘
				final EditText editText = (EditText) msg.obj;
				editText.setOnTouchListener(new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if (event.getAction() == MotionEvent.ACTION_UP) {
							int inputback = editText.getInputType();
							editText.setInputType(InputType.TYPE_NULL);
							new KeyboardUtil1(act, ctx, editText)
									.showKeyboard();
							editText.setInputType(inputback);
							editText.setSelection(editText.getText().toString()
									.length());
							return false;
						}
						return false;
					}
				});
				break;
			case 1:
				release_rent_out_rent_yearText.setText((String) msg.obj);
				release_rent_out_tenancy_term_yearText
						.setText((String) msg.obj);
				break;
			case 2:
				release_rent_out_rent_yearText.setText((String) msg.obj);
				release_rent_out_tenancy_term_yearText
						.setText((String) msg.obj);
				break;
			case 3:
				// 三级联动效果
				pvOptions.setPicker(item1, item2, item3, true);
				pvOptions.setCyclic(true, true, true);
				pvOptions.setSelectOptions(0, 0, 0);
				areaBtn.setClickable(true);
				break;
			case 4:
				pd.dismiss();
				layout.setAlpha(1f);
				String string = (String) msg.obj;
				isQuestionImage("温馨提示", string, "我知道了", Release_Activity.this);
				break;
			case 10000:// 程序异常提示
				pd.dismiss();
				layout.setAlpha(1f);
				MyToast.Toast(Release_Activity.this,
						MyApp.Program_Exception_Prompt);
				break;
			case 5:
				pd.dismiss();
				layout.setAlpha(1f);
				String string1 = (String) msg.obj;
				isQuestionImage("温馨提示", string1, "我知道了", Release_Activity.this);
				break;
			default:
				break;
			}

		};
	};

	@SuppressWarnings("rawtypes")
	private void isAreaSelector() {

		// 选项选择器
		pvOptions = new OptionsPickerView(Release_Activity.this);

		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				System.out.println(System.currentTimeMillis());
				if (item1 != null && item2 != null && item3 != null) {
					Message message = new Message();
					message.what = 3;
					handler.sendMessage(message);
					return;
				}
				RegionDAO.queryAllInfo();
				item1 = (ArrayList<RegionInfo>) RegionDAO.getProvencesOrCity(1);
				for (RegionInfo regionInfo : item1) {
					item2.add((ArrayList<RegionInfo>) RegionDAO
							.getProvencesOrCityOnParent(regionInfo.getId()));
				}

				for (ArrayList<RegionInfo> arrayList : item2) {
					ArrayList<ArrayList<RegionInfo>> list2 = new ArrayList<ArrayList<RegionInfo>>();
					for (RegionInfo regionInfo : arrayList) {

						ArrayList<RegionInfo> q = (ArrayList<RegionInfo>) RegionDAO
								.getProvencesOrCityOnParent(regionInfo.getId());
						list2.add(q);

					}
					item3.add(list2);
				}

				Message message = new Message();
				message.what = 3;
				handler.sendMessage(message);

			}
		}).start();
		// 设置选择的三级单位
		// pwOptions.setLabels("省", "市", "区");
		pvOptions.setTitle("选择城市");

		// 设置默认选中的三级项目
		// 监听确定选择按钮

		pvOptions
				.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

					public void onOptionsSelect(int options1, int option2,
							int options3) {
						// 返回的分别是三个级别的选中位置
						String tx = " "
								+ item1.get(options1).getPickerViewText()
								+ " "
								+ item2.get(options1).get(option2)
										.getPickerViewText()
								+ " "
								+ item3.get(options1).get(option2)
										.get(options3).getPickerViewText();
						areaBtn.setText(tx);
						good_region = item1.get(options1).getPickerViewText();
						city_region = item2.get(options1).get(option2)
								.getPickerViewText();
						small_area = item3.get(options1).get(option2)
								.get(options3).getPickerViewText();
					}
				});
		// 点击弹出选项选择器
		areaBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				pvOptions.show();
			}
		});

		areaBtn.setClickable(false);
	}

	/**
	 * 
	 * 监听返回键
	 * 
	 * */
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			int visibility = keyboardView.getVisibility();
			if (visibility == View.VISIBLE || visibility == View.INVISIBLE) {
				keyboardView.setVisibility(View.GONE);
			} else {
				this.finish();
			}
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	};

	/**
	 * 
	 * 隐藏自定义键盘
	 * 
	 * */
	private void isConcealKeyboard() {
		int visibility = keyboardView.getVisibility();
		if (visibility == View.VISIBLE) {
			keyboardView.setVisibility(View.GONE);
		}
	}

	/**
	 * 
	 * 显示PopupWindow
	 * 
	 * */
	private void showPopupWindow(View view, final int i) {

		// 一个自定义的布局，作为显示的内容
		View contentView = LayoutInflater.from(getApplicationContext())
				.inflate(R.layout.release_pop, null);

		final PopupWindow popupWindow = new PopupWindow(contentView,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setOnDismissListener(new OnDismissListener() {

			public void onDismiss() {
				releaseLin.setAlpha(1f);
			}
		});
		popupWindow.setTouchable(true);

		popupWindow.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				return false;
				// 这里如果返回true的话，touch事件将被拦截
				// 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
			}
		});

		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
		// 我觉得这里是API的一个bug
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));

		// 设置好参数之后再show
		int[] location = new int[2];
		view.getLocationOnScreen(location);

		popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
				location[0] + view.getWidth(), location[1] - 150);

		// 设置按钮的点击事件
		TextView year = (TextView) contentView
				.findViewById(R.id.release_pop_yearText);
		year.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				handler.sendMessage(handler.obtainMessage(i, "/年"));
				popupWindow.dismiss();
			}
		});
		TextView month = (TextView) contentView
				.findViewById(R.id.release_pop_monthText);
		month.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				handler.sendMessage(handler.obtainMessage(i, "/月"));
				popupWindow.dismiss();
			}
		});
		TextView day = (TextView) contentView
				.findViewById(R.id.release_pop_dayText);
		day.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				handler.sendMessage(handler.obtainMessage(i, "/日"));
				popupWindow.dismiss();
			}
		});
	}

	/**
	 * 添加图片
	 * */
	private void isGridView() {
		gridviewList = new ArrayList<String>();
		gridviewList.add("drawable://" + R.drawable.fb_icn_carema);
		gridviewList.add("drawable://" + R.drawable.fb_icn_video);
		adapter = new Release_GridViewAdapter(gridviewList,
				getApplicationContext(), Release_Activity.this, times);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (gridviewList.get(position).equals(
						"drawable://" + R.drawable.fb_icn_carema)) {
					Date dt = new Date();
					time = dt.getTime();
					times.add(time);
					cropHelper = new CropHelper(Release_Activity.this, OSUtils
							.getSdCardDirectory(getApplicationContext())
							+ time
							+ "head1.png");
					mTempPhotoPath = OSUtils
							.getSdCardDirectory(getApplicationContext())
							+ time
							+ "head1.png";
					Release_Photo_Pop pop = new Release_Photo_Pop(
							Release_Activity.this, cropHelper, mTempPhotoPath);
					pop.showPopupWindow(gridView);
				} else if (gridviewList.get(position).equals(
						"drawable://" + R.drawable.fb_icn_video)) {
					CameraUtils.openCameraForVideo(Release_Activity.this);
				}
			}
		});
	}

	/**
	 * 录制音频
	 * */
	private void isRecording() {
		recordBtn = (RecordButton) findViewById(R.id.release_recordBtn);
		recordBtn.setSavePath(path);
		System.out.println("path======="+path);
		recordBtn.setOnFinishedRecordListener(new OnFinishedRecordListener() {
			@Override
			public void onFinishedRecord(String audioPath) {
				MediaPlayer mp = MediaPlayer.create(Release_Activity.this,
						Uri.parse(path));
				System.out.println("mp======="+mp);
				if (mp!=null) {
					long duration = mp.getDuration() / 1000;
					String str = duration % 60 + "";
					playerBtn.setText(str + "s");
				}else {
					MyToast.Toast(getApplicationContext(), "请开启录音权限");
				}
			}
		});
	}

	/**
	 * 播放录制音频
	 * */
	private void isPlayRecording() {
		MediaPlayer mPlayer = null;
		long duration = 0;
		mPlayer = new MediaPlayer();
		MediaPlayer mp = MediaPlayer.create(Release_Activity.this,
				Uri.parse(path));
		if (mp != null) {
			duration = mp.getDuration() / 1000;
		}
		try {
			mPlayer.setDataSource(path);
			mPlayer.prepare();
			mPlayer.start();
		} catch (IOException e) {
		}
		String str = duration % 60 + "";
		playerBtn.setText(str + "s");
	}

	private long isRecordingTime() {
		long duration = 0;
		MediaPlayer mp = MediaPlayer.create(Release_Activity.this,
				Uri.parse(path));
		if (mp != null) {
			duration = mp.getDuration() / 1000;
		}
		String str = duration % 60 + "";
		long l = Long.valueOf(str) * 1000;
		return l;
	}

	/**
	 * 确定发布(卖钱)
	 * */
	private class ConfirmTask extends AsyncTask<Void, Void, Void> {
		String string;// 拼接地址
		String result;// 返回数据

		@Override
		protected Void doInBackground(Void... params) {
			if (cordelesBox2.isChecked()) {// 卖钱-众筹
				string = "goods/addcrowdgoods.do";
			} else if (cordelesBox1.isChecked()) {// 卖钱-全价
				string = "goods/addfullpricegoods.do";
			}
			if (release_label_box1.isChecked()) {
				normal_use = "1";
			} else {
				normal_use = "0";
			}
			if (release_label_box2.isChecked()) {
				warranty_period = "1";
			} else {
				warranty_period = "0";
			}
			if (release_label_box3.isChecked()) {
				no_repair = "1";
			} else {
				no_repair = "0";
			}
			if (release_label_box4.isChecked()) {
				brand_new = "1";
			} else {
				brand_new = "0";
			}
			if (release_label_box5.isChecked()) {
				jingdong = "1";
			} else {
				jingdong = "0";
			}
			if (release_label_box6.isChecked()) {
				mainland_licensed = "1";
			} else {
				mainland_licensed = "0";
			}
			if (release_label_box7.isChecked()) {
				since = "1";
			} else {
				since = "0";
			}
			if (check == true) {
				whether_pack = "1";
				freight = "0";
			} else {
				whether_pack = "0";
			}
			if (playerBtn.getText().toString().trim().equals("0s")) {
				path = "";
			}
			goods_name = titleEd.getText().toString().trim();
			good_description = describeEd.getText().toString().trim();

			goods_price = selling_priceEd.getText().toString().trim();
			if (photolist.size() < 3) {
				handler.sendMessage(handler.obtainMessage(4,
						"至少上传一张图片哟亲~好让人家看到您的宝贝"));
			} else if (goods_name == null || goods_name.equals("")) {
				handler.sendMessage(handler.obtainMessage(4,
						"难道不给您家的宝贝取个响亮的名字吗？"));
			} else if (good_description == null || good_description.equals("")) {
				handler.sendMessage(handler.obtainMessage(4, "请简单描述一下您的宝贝吧！"));
			} else if (areaBtn.getText().toString().trim().equals("获取地区失败")) {
				handler.sendMessage(handler.obtainMessage(4, "请选择一下宝贝的所在地吧！"));
			} else if (twolevel_id == null || three_id == null) {
				handler.sendMessage(handler.obtainMessage(4,
						"请帮您家的宝贝选个分类吧，好让我们知道它属于什么。"));
			} else if (goods_price.equals("")) {
				handler.sendMessage(handler.obtainMessage(4,
						"请帮您家的宝贝定个价吧，不然就白送了。"));
			} else {
				result = uploading(MyApp.base_address + string, photolist,
						goods_name, good_description, path,
						Tool.deleteString(good_region), Tool.deleteString(city_region),
						Tool.deleteString(small_area), twolevel_id, three_id,
						original_price, normal_use, warranty_period, no_repair,
						brand_new, jingdong, mainland_licensed, since,
						MySharePreferences.GetUser_ID(getApplicationContext()), selling_price, freight, whether_pack);
				if (result != null) {
					try {
						JSONObject jsonObject = new JSONObject(result);
						String string1 = jsonObject.getString("1");
						if (string1.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else {
							handler.sendMessage(handler.obtainMessage(5,
									string1));
						}
					} catch (JSONException e) {

						e.printStackTrace();
					}
				} else {
					handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
				}
			}
			return null;
		}
	}

	/**
	 * 确定发布(拍卖)
	 * */
	private class ConfirmTask1 extends AsyncTask<Void, Void, Void> {
		String result;// 返回数据

		@Override
		protected Void doInBackground(Void... params) {
			if (release_label_box1.isChecked()) {
				normal_use = "1";
			} else {
				normal_use = "0";
			}
			if (release_label_box2.isChecked()) {
				warranty_period = "1";
			} else {
				warranty_period = "0";
			}
			if (release_label_box3.isChecked()) {
				no_repair = "1";
			} else {
				no_repair = "0";
			}
			if (release_label_box4.isChecked()) {
				brand_new = "1";
			} else {
				brand_new = "0";
			}
			if (release_label_box5.isChecked()) {
				jingdong = "1";
			} else {
				jingdong = "0";
			}
			if (release_label_box6.isChecked()) {
				mainland_licensed = "1";
			} else {
				mainland_licensed = "0";
			}
			if (release_label_box7.isChecked()) {
				since = "1";
			} else {
				since = "0";
			}
			if (playerBtn.getText().toString().trim().equals("0s")) {
				path = "";
			}
			if (check == true) {
				whether_pack = "1";
				freight = "0";
			} else {
				whether_pack = "0";
			}
			goods_name = titleEd.getText().toString().trim();
			good_description = describeEd.getText().toString().trim();

			startauctionprice = auction_startPriceEd.getText().toString()
					.trim();
			retainprice = auction_retainPriceEd.getText().toString().trim();
			increaserange = auction_rangeEd.getText().toString().trim();
			if (photolist.size() < 3) {
				handler.sendMessage(handler.obtainMessage(4,
						"至少上传一张图片哟亲~好让人家看到您的宝贝"));
			} else if (goods_name == null || goods_name.equals("")) {
				handler.sendMessage(handler.obtainMessage(4,
						"难道不给您家的宝贝取个响亮的名字吗？"));
			} else if (good_description == null || good_description.equals("")) {
				handler.sendMessage(handler.obtainMessage(4, "请简单描述一下您的宝贝吧！"));
			} else if (areaBtn.getText().toString().trim().equals("获取地区失败")) {
				handler.sendMessage(handler.obtainMessage(4, "请选择一下宝贝的所在地吧！"));
			} else if (twolevel_id == null || three_id == null) {
				handler.sendMessage(handler.obtainMessage(4,
						"请帮您家的宝贝选个分类吧，好让我们知道它属于什么。"));
			} else if (original_price == null || original_price.equals("")) {
				handler.sendMessage(handler.obtainMessage(4,
						"请帮您家的宝贝原价告诉我们一下吧！"));
			} else if (startauctionprice == null
					|| startauctionprice.equals("")) {
				handler.sendMessage(handler.obtainMessage(4, "请帮您家的宝贝定个起拍价吧！"));
			} else if (retainprice == null || retainprice.equals("")) {
				handler.sendMessage(handler.obtainMessage(4, "请帮您家的宝贝定个保留价吧！"));
			} else if (increaserange == null || increaserange.equals("")) {
				handler.sendMessage(handler.obtainMessage(4, "请帮您家的宝贝定个加价幅度吧！"));
			} else {
				
				try {
					result = uploading1(MyApp.base_address
							+ "goods/addauctiongoods.do", photolist,
							goods_name, good_description, path,
							Tool.deleteString(good_region),
							Tool.deleteString(city_region),
							Tool.deleteString(small_area), twolevel_id, three_id,
							original_price, normal_use, warranty_period,
							no_repair, brand_new, jingdong, mainland_licensed,
							since, MySharePreferences.GetUser_ID(getApplicationContext()), startauctionprice,
							retainprice, increaserange);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (result != null) {
					try {
						JSONObject jsonObject = new JSONObject(result);
						String string1 = jsonObject.getString("1");
						if (string1.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else {
							handler.sendMessage(handler.obtainMessage(5,
									string1));
						}
					} catch (JSONException e) {

						e.printStackTrace();
					}
				} else {
					handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
				}
			}
			return null;
		}
	}

	/**
	 * 确定发布(出租)
	 * */
	private class ConfirmTask2 extends AsyncTask<Void, Void, Void> {
		String result;// 返回数据

		@Override
		protected Void doInBackground(Void... params) {
			if (release_label_box1.isChecked()) {
				normal_use = "1";
			} else {
				normal_use = "0";
			}
			if (release_label_box2.isChecked()) {
				warranty_period = "1";
			} else {
				warranty_period = "0";
			}
			if (release_label_box3.isChecked()) {
				no_repair = "1";
			} else {
				no_repair = "0";
			}
			if (release_label_box4.isChecked()) {
				brand_new = "1";
			} else {
				brand_new = "0";
			}
			if (release_label_box5.isChecked()) {
				jingdong = "1";
			} else {
				jingdong = "0";
			}
			if (release_label_box6.isChecked()) {
				mainland_licensed = "1";
			} else {
				mainland_licensed = "0";
			}
			if (release_label_box7.isChecked()) {
				since = "1";
			} else {
				since = "0";
			}
			if (playerBtn.getText().toString().trim().equals("0s")) {
				path = "";
			}
			goods_name = titleEd.getText().toString().trim();
			good_description = describeEd.getText().toString().trim();
			rentprice = rent_out_rentEd.getText().toString().trim();
			renttime = rent_out_tenancy_termEd.getText().toString().trim();
			if (check == true) {
				whether_pack = "1";
				freight = "0";
			} else {
				whether_pack = "0";
			}
		
			if (photolist.size() < 3) {
				handler.sendMessage(handler.obtainMessage(4,
						"至少上传一张图片哟亲~好让人家看到您的宝贝"));
			} else if (goods_name == null || goods_name.equals("")) {
				handler.sendMessage(handler.obtainMessage(4,
						"难道不给您家的宝贝取个响亮的名字吗？"));
			} else if (good_description == null || good_description.equals("")) {
				handler.sendMessage(handler.obtainMessage(4, "请简单描述一下您的宝贝吧！"));
			} else if (areaBtn.getText().toString().trim().equals("获取地区失败")) {
				handler.sendMessage(handler.obtainMessage(4, "请选择一下宝贝的所在地吧！"));
			} else if (twolevel_id == null || three_id == null) {
				handler.sendMessage(handler.obtainMessage(4,
						"请帮您家的宝贝选个分类吧，好让我们知道它属于什么。"));
			} else if (original_price == null || original_price.equals("")) {
				handler.sendMessage(handler.obtainMessage(4,
						"请帮您家的宝贝原价告诉我们一下吧!"));
			} else if (rentprice == null || rentprice.equals("")) {
				handler.sendMessage(handler.obtainMessage(4, "请帮您家的宝贝定个租金吧！"));
			} else if (renttime == null || renttime.equals("")) {
				handler.sendMessage(handler.obtainMessage(4, "请帮您家的宝贝定个租期吧！"));
			} else {
				System.out.println(freight+"=freight");
				System.out.println(whether_pack+"=whether_pack");
				try {
					result = uploading2(MyApp.base_address
							+ "goods/addleasegoods.do", photolist, goods_name,
							good_description, path, Tool.deleteString(good_region),
							Tool.deleteString(city_region),
							Tool.deleteString(small_area), twolevel_id, three_id,
							original_price, normal_use, warranty_period,
							no_repair, brand_new, jingdong, mainland_licensed,
							since, MySharePreferences.GetUser_ID(getApplicationContext()), freight, rentprice
									+ "/"
									+ release_rent_out_rent_yearText.getText()
											.toString().trim(), renttime
									+ release_rent_out_rent_yearText.getText()
											.toString().trim(), whether_pack);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (result != null) {
					try {
						JSONObject jsonObject = new JSONObject(result);
						String string1 = jsonObject.getString("1");
						if (string1.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else {
							handler.sendMessage(handler.obtainMessage(5,
									string1));
						}
					} catch (JSONException e) {

						e.printStackTrace();
					}
				} else {
					handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
				}
			}
			return null;
		}
	}

	/**
	 * 确定发布(免费送)
	 * */
	private class ConfirmTask3 extends AsyncTask<Void, Void, Void> {
		String result;// 返回数据

		@Override
		protected Void doInBackground(Void... params) {
			if (release_free_checkboxLin_box1.isChecked()) {
				normal_use = "1";
			} else {
				normal_use = "0";
			}
			if (release_free_checkboxLin_box2.isChecked()) {
				since = "1";
			} else {
				since = "0";
			}
			if (playerBtn.getText().toString().trim().equals("0s")) {
				path = "";
			}
			goods_name = titleEd.getText().toString().trim();
			good_description = describeEd.getText().toString().trim();
			contactQQ = free_QQEd.getText().toString().trim();
			freight = free_freightEd.getText().toString().trim();

			if (freight == null) {
				whether_pack = "1";
				freight = "0";
			} else if (freight.equals("")) {
				whether_pack = "1";
				freight = "0";
			} else if (freight.equals("0")) {
				whether_pack = "1";
				freight = "0";
			} else {
				whether_pack = "0";
			}
			if (photolist.size() < 3) {
				handler.sendMessage(handler.obtainMessage(4,
						"至少上传一张图片哟亲~好让人家看到您的宝贝"));
			} else if (goods_name == null || goods_name.equals("")) {
				handler.sendMessage(handler.obtainMessage(4,
						"难道不给您家的宝贝取个响亮的名字吗？"));
			} else if (good_description == null || good_description.equals("")) {
				handler.sendMessage(handler.obtainMessage(4, "请简单描述一下您的宝贝吧！"));
			} else if (areaBtn.getText().toString().trim().equals("获取地区失败")) {
				handler.sendMessage(handler.obtainMessage(4, "请选择一下宝贝的所在地吧！"));
			} else if (twolevel_id == null || three_id == null) {
				handler.sendMessage(handler.obtainMessage(4,
						"请帮您家的宝贝选个分类吧，好让我们知道它属于什么。"));
			} else if (contactQQ == null) {
				contactQQ = "";
			} else {
				try {
					result = uploading3(MyApp.base_address
							+ "goods/addfreedeliverygoods.do", photolist,
							goods_name, good_description, path,
							Tool.deleteString(good_region),
							Tool.deleteString(city_region),
							Tool.deleteString(small_area), twolevel_id, three_id,
							normal_use, since, MySharePreferences.GetUser_ID(getApplicationContext()), freight,
							contactQQ, whether_pack);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (result != null) {
					try {
						JSONObject jsonObject = new JSONObject(result);
						String string1 = jsonObject.getString("1");
						if (string1.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else {
							handler.sendMessage(handler.obtainMessage(5,
									string1));
						}
					} catch (JSONException e) {

						e.printStackTrace();
					}
				} else {
					handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
				}
			}
			return null;
		}
	}

	/**
	 * 卖钱（参数）
	 * */
	private String uploading(String url, List<String> path, String goods_name,
			String good_description, String audiofile, String good_region,
			String city_region, String small_area, String twolevel_id,
			String three_id, String goods_price, String normal_use,
			String warranty_period, String no_repair, String brand_new,
			String jingdong, String mainland_licensed, String since,
			String user_id, String selling_price, String freight,
			String whether_pack) {
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		// httpPost.addHeader("Content-Type", "video/mpeg");
		// httpPost.addHeader("User-Agent", "imgfornote");
		// 设置传递参数
		MultipartEntity reqEntity = new MultipartEntity();

		for (int i = 0; i < path.size(); i++) {// 上传图片和视频
			File file1 = null;
			if (path.get(i).equals("drawable://" + R.drawable.fb_icn_video)
					|| path.get(i).equals(
							"drawable://" + R.drawable.fb_icn_carema)) {

			} else if (path.get(i).equals(CameraUtils.CAMERA_VIDEO)) {
				file1 = new File(path.get(i));
				// if (!file1.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file1);
				reqEntity.addPart("videofile", fileBody);
			} else {
				file1 = new File(path.get(i));
				// if (!file1.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file1);
				reqEntity.addPart("imagefile", fileBody);
				// }
			}
		}
		if (!audiofile.equals("")) {
			File file3 = new File(audiofile);// 上传音频
			if (!file3.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file3);
				reqEntity.addPart("audiofile", fileBody);
			}
		}
		try {
			StringBody type = new StringBody(goods_name);
			reqEntity.addPart("goods_name", type);
			StringBody type1 = new StringBody(good_description);
			reqEntity.addPart("good_description", type1);
			StringBody type2 = new StringBody(good_region);
			reqEntity.addPart("good_region", type2);
			StringBody type3 = new StringBody(city_region);
			reqEntity.addPart("city_region", type3);
			StringBody type4 = new StringBody(small_area);
			reqEntity.addPart("small_area", type4);
			StringBody type6 = new StringBody(twolevel_id);
			reqEntity.addPart("twolevel_id", type6);
			StringBody type19 = new StringBody(three_id);
			reqEntity.addPart("three_id", type19);
			StringBody type7 = new StringBody(goods_price);
			reqEntity.addPart("goods_price", type7);
			StringBody type8 = new StringBody(normal_use);
			reqEntity.addPart("normal_use", type8);
			StringBody type9 = new StringBody(warranty_period);
			reqEntity.addPart("warranty_period", type9);
			StringBody type10 = new StringBody(no_repair);
			reqEntity.addPart("no_repair", type10);
			StringBody type11 = new StringBody(brand_new);
			reqEntity.addPart("brand_new", type11);
			StringBody type12 = new StringBody(jingdong);
			reqEntity.addPart("jingdong", type12);
			StringBody type13 = new StringBody(mainland_licensed);
			reqEntity.addPart("mainland_licensed", type13);
			StringBody type14 = new StringBody(since);
			reqEntity.addPart("since", type14);
			StringBody type15 = new StringBody(user_id);
			reqEntity.addPart("user_id", type15);
			StringBody type16 = new StringBody(selling_price);
			reqEntity.addPart("selling_price", type16);
			StringBody type17 = new StringBody(freight);
			reqEntity.addPart("freight", type17);
			StringBody type18 = new StringBody(whether_pack);
			reqEntity.addPart("whether_pack", type18);

			httpPost.setEntity(reqEntity);
			// 取得默认的HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 取得HttpResponse
			HttpResponse httpResponse;
			try {
				httpResponse = httpclient.execute(httpPost);

				// HttpStatus.SC_OK表示连接成功
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 取得返回的字符串
					result = EntityUtils.toString(httpResponse.getEntity());
				} else {
					pd.dismiss();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 拍卖（参数）
	 * */
	private String uploading1(String url, List<String> path, String goods_name,
			String good_description, String audiofile, String good_region,
			String city_region, String small_area, String twolevel_id,
			String three_id, String goods_price, String normal_use,
			String warranty_period, String no_repair, String brand_new,
			String jingdong, String mainland_licensed, String since,
			String user_id, String startauctionprice, String retainprice,
			String increaserange) throws ParseException, IOException {
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		// httpPost.addHeader("Content-Type", "video/mpeg");
		// httpPost.addHeader("User-Agent", "imgfornote");
		// 设置传递参数
		MultipartEntity reqEntity = new MultipartEntity();

		for (int i = 0; i < path.size(); i++) {// 上传图片和视频
			File file1 = null;
			if (path.get(i).equals("drawable://" + R.drawable.fb_icn_video)
					|| path.get(i).equals(
							"drawable://" + R.drawable.fb_icn_carema)) {

			} else if (path.get(i).equals(CameraUtils.CAMERA_VIDEO)) {
				file1 = new File(path.get(i));
				// if (!file1.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file1);
				reqEntity.addPart("videofile", fileBody);
			} else {
				file1 = new File(path.get(i));
				// if (!file1.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file1);
				reqEntity.addPart("imagefile", fileBody);
				// }
			}
		}
		if (!audiofile.equals("")) {
			File file3 = new File(audiofile);// 上传音频
			if (!file3.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file3);
				reqEntity.addPart("audiofile", fileBody);
			}
		}
		StringBody type = new StringBody(goods_name);
		reqEntity.addPart("goods_name", type);
		StringBody type1 = new StringBody(good_description);
		reqEntity.addPart("good_description", type1);
		StringBody type2 = new StringBody(good_region);
		reqEntity.addPart("good_region", type2);
		StringBody type3 = new StringBody(city_region);
		reqEntity.addPart("city_region", type3);
		StringBody type4 = new StringBody(small_area);
		reqEntity.addPart("small_area", type4);
		StringBody type6 = new StringBody(twolevel_id);
		reqEntity.addPart("twolevel_id", type6);
		StringBody type19 = new StringBody(three_id);
		reqEntity.addPart("three_id", type19);
		StringBody type7 = new StringBody(goods_price);
		reqEntity.addPart("goods_price", type7);
		StringBody type8 = new StringBody(normal_use);
		reqEntity.addPart("normal_use", type8);
		StringBody type9 = new StringBody(warranty_period);
		reqEntity.addPart("warranty_period", type9);
		StringBody type10 = new StringBody(no_repair);
		reqEntity.addPart("no_repair", type10);
		StringBody type11 = new StringBody(brand_new);
		reqEntity.addPart("brand_new", type11);
		StringBody type12 = new StringBody(jingdong);
		reqEntity.addPart("jingdong", type12);
		StringBody type13 = new StringBody(mainland_licensed);
		reqEntity.addPart("mainland_licensed", type13);
		StringBody type14 = new StringBody(since);
		reqEntity.addPart("since", type14);
		StringBody type15 = new StringBody(user_id);
		reqEntity.addPart("user_id", type15);
		StringBody type16 = new StringBody(startauctionprice);
		reqEntity.addPart("startauctionprice", type16);
		StringBody type17 = new StringBody(retainprice);
		reqEntity.addPart("retainprice", type17);
		StringBody type18 = new StringBody(increaserange);
		reqEntity.addPart("increaserange", type18);
		httpPost.setEntity(reqEntity);
		// 取得默认的HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		// 取得HttpResponse
		HttpResponse httpResponse = httpclient.execute(httpPost);
		// HttpStatus.SC_OK表示连接成功
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 取得返回的字符串
			result = EntityUtils.toString(httpResponse.getEntity());
		} else {
			pd.dismiss();
		}
		return result;
	}

	/**
	 * 出租（参数）
	 * */
	private String uploading2(String url, List<String> path, String goods_name,
			String good_description, String audiofile, String good_region,
			String city_region, String small_area, String twolevel_id,
			String three_id, String goods_price, String normal_use,
			String warranty_period, String no_repair, String brand_new,
			String jingdong, String mainland_licensed, String since,
			String user_id, String freight, String rentprice, String renttime,
			String whether_pack) throws ParseException, IOException {
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		// httpPost.addHeader("Content-Type", "video/mpeg");
		// httpPost.addHeader("User-Agent", "imgfornote");
		// 设置传递参数
		MultipartEntity reqEntity = new MultipartEntity();

		for (int i = 0; i < path.size(); i++) {// 上传图片和视频
			File file1 = null;
			if (path.get(i).equals("drawable://" + R.drawable.fb_icn_video)
					|| path.get(i).equals(
							"drawable://" + R.drawable.fb_icn_carema)) {

			} else if (path.get(i).equals(CameraUtils.CAMERA_VIDEO)) {
				file1 = new File(path.get(i));
				// if (!file1.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file1);
				reqEntity.addPart("videofile", fileBody);
			} else {
				file1 = new File(path.get(i));
				// if (!file1.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file1);
				reqEntity.addPart("imagefile", fileBody);
				// }
			}
		}
		if (!audiofile.equals("")) {
			File file3 = new File(audiofile);// 上传音频
			if (!file3.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file3);
				reqEntity.addPart("audiofile", fileBody);
			}
		}
		StringBody type = new StringBody(goods_name);
		reqEntity.addPart("goods_name", type);
		StringBody type1 = new StringBody(good_description);
		reqEntity.addPart("good_description", type1);
		StringBody type2 = new StringBody(good_region);
		reqEntity.addPart("good_region", type2);
		StringBody type3 = new StringBody(city_region);
		reqEntity.addPart("city_region", type3);
		StringBody type4 = new StringBody(small_area);
		reqEntity.addPart("small_area", type4);
		StringBody type6 = new StringBody(twolevel_id);
		reqEntity.addPart("twolevel_id", type6);
		StringBody type19 = new StringBody(three_id);
		reqEntity.addPart("three_id", type19);
		StringBody type7 = new StringBody(goods_price);
		reqEntity.addPart("goods_price", type7);
		StringBody type8 = new StringBody(normal_use);
		reqEntity.addPart("normal_use", type8);
		StringBody type9 = new StringBody(warranty_period);
		reqEntity.addPart("warranty_period", type9);
		StringBody type10 = new StringBody(no_repair);
		reqEntity.addPart("no_repair", type10);
		StringBody type11 = new StringBody(brand_new);
		reqEntity.addPart("brand_new", type11);
		StringBody type12 = new StringBody(jingdong);
		reqEntity.addPart("jingdong", type12);
		StringBody type13 = new StringBody(mainland_licensed);
		reqEntity.addPart("mainland_licensed", type13);
		StringBody type14 = new StringBody(since);
		reqEntity.addPart("since", type14);
		StringBody type15 = new StringBody(user_id);
		reqEntity.addPart("user_id", type15);
		StringBody type16 = new StringBody(freight);
		reqEntity.addPart("freight", type16);
		StringBody type17 = new StringBody(rentprice);
		reqEntity.addPart("rentprice", type17);
		StringBody type18 = new StringBody(renttime);
		reqEntity.addPart("renttime", type18);
		StringBody type20 = new StringBody(whether_pack);
		reqEntity.addPart("whether_pack", type20);
		httpPost.setEntity(reqEntity);
		// 取得默认的HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		// 取得HttpResponse
		HttpResponse httpResponse = httpclient.execute(httpPost);
		// HttpStatus.SC_OK表示连接成功
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 取得返回的字符串
			result = EntityUtils.toString(httpResponse.getEntity());
		} else {
			pd.dismiss();
		}
		return result;
	}

	/**
	 * 免费送（参数）
	 * */
	private String uploading3(String url, List<String> path, String goods_name,
			String good_description, String audiofile, String good_region,
			String city_region, String small_area, String twolevel_id,
			String three_id, String normal_use, String since, String user_id,
			String freight, String contactQQ, String whether_pack)
			throws ParseException, IOException {
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		// httpPost.addHeader("Content-Type", "video/mpeg");
		// httpPost.addHeader("User-Agent", "imgfornote");
		// 设置传递参数
		MultipartEntity reqEntity = new MultipartEntity();

		for (int i = 0; i < path.size(); i++) {// 上传图片和视频
			File file1 = null;
			if (path.get(i).equals("drawable://" + R.drawable.fb_icn_video)
					|| path.get(i).equals(
							"drawable://" + R.drawable.fb_icn_carema)) {

			} else if (path.get(i).equals(CameraUtils.CAMERA_VIDEO)) {
				file1 = new File(path.get(i));
				// if (!file1.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file1);
				reqEntity.addPart("videofile", fileBody);
			} else {
				file1 = new File(path.get(i));
				// if (!file1.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file1);
				reqEntity.addPart("imagefile", fileBody);
				// }
			}
		}
		if (!audiofile.equals("")) {
			File file3 = new File(audiofile);// 上传音频
			if (!file3.getAbsoluteFile().equals("")) {
				FileBody fileBody = new FileBody(file3);
				reqEntity.addPart("audiofile", fileBody);
			}
		}
		StringBody type = new StringBody(goods_name);
		reqEntity.addPart("goods_name", type);
		StringBody type1 = new StringBody(good_description);
		reqEntity.addPart("good_description", type1);
		StringBody type2 = new StringBody(good_region);
		reqEntity.addPart("good_region", type2);
		StringBody type3 = new StringBody(city_region);
		reqEntity.addPart("city_region", type3);
		StringBody type4 = new StringBody(small_area);
		reqEntity.addPart("small_area", type4);
		StringBody type6 = new StringBody(twolevel_id);
		reqEntity.addPart("twolevel_id", type6);
		StringBody type19 = new StringBody(three_id);
		reqEntity.addPart("three_id", type19);
		StringBody type8 = new StringBody(normal_use);
		reqEntity.addPart("normal_use", type8);
		StringBody type14 = new StringBody(since);
		reqEntity.addPart("since", type14);
		StringBody type15 = new StringBody(user_id);
		reqEntity.addPart("user_id", type15);
		StringBody type16 = new StringBody(freight);
		reqEntity.addPart("freight", type16);
		StringBody type17 = new StringBody(contactQQ);
		reqEntity.addPart("contactQQ", type17);
		Log.e("contactQQ", contactQQ);
		Log.e("freight", freight);
		StringBody type20 = new StringBody(whether_pack);
		reqEntity.addPart("whether_pack", type20);
		httpPost.setEntity(reqEntity);
		// 取得默认的HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		// 取得HttpResponse
		HttpResponse httpResponse = httpclient.execute(httpPost);
		// HttpStatus.SC_OK表示连接成功
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 取得返回的字符串
			result = EntityUtils.toString(httpResponse.getEntity());
		} else {
			pd.dismiss();
		}
		return result;
	}
/**
 * 保存用户所存信息
 * */
	private void isSaveInformation() {

	}
}
