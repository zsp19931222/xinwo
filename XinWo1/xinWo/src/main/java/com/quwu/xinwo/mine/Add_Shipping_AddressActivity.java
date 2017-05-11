package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.pickerview.OptionsPickerView;
import com.quwu.xinwo.pickerview.RegionDAO;
import com.quwu.xinwo.pickerview.RegionInfo;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.IsCheckPhoneNum;
import com.quwu.xinwo.until.MyInputMethodManager;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.MyTextWatcher;

public class Add_Shipping_AddressActivity extends SwipeBackActivity implements
		OnClickListener {

	private EditText consigneeEd;
	private EditText phoneNumEd;
	private EditText particularAreaEd;

	/**
	 * 获取输入值
	 * */
	private String consigneestr;
	private String phoneNumstr;
	private String particularAreatr;
	private String areastr;
	
	
	private TextView areaText;
	private TextView titleText;

	private Button confirmBtn;

	private LinearLayout consigneeLin;
	private LinearLayout phoneNumLin;
	private LinearLayout areaLin;
	private LinearLayout particularAreaLin;

	// private CheckBox box;
	/**
	 * 上个页面传送过来的数据
	 */
	private String state;// 判断是添加还是修改地址（0-添加，1-修改）
	private String name;
	private String phoneNum;
	private String area;
	private String particular_area;

	// 地区选择器
	private LinearLayout add_shipping_address_areaLin;
	@SuppressWarnings("rawtypes")
	private OptionsPickerView pvOptions;
	private static ArrayList<RegionInfo> item1;
	private static ArrayList<ArrayList<RegionInfo>> item2 = new ArrayList<ArrayList<RegionInfo>>();
	private static ArrayList<ArrayList<ArrayList<RegionInfo>>> item3 = new ArrayList<ArrayList<ArrayList<RegionInfo>>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_shipping_address);
		FinishActivity.finish(R.id.add_shipping_address_returnRel,
				Add_Shipping_AddressActivity.this);
		findID();
		isAreaSelector();
		isTitleText();
	}

	private void findID() {
		// box=(CheckBox) findViewById(R.id.add_shipping_address_checkbox);

		add_shipping_address_areaLin = (LinearLayout) findViewById(R.id.add_shipping_address_areaLin);

		consigneeEd = (EditText) findViewById(R.id.add_shipping_address_consigneeEd);
		phoneNumEd = (EditText) findViewById(R.id.add_shipping_address_phoneNumEd);
		particularAreaEd = (EditText) findViewById(R.id.add_shipping_address_particularAreaEd);

		areaText = (TextView) findViewById(R.id.add_shipping_address_areaText);
		titleText = (TextView) findViewById(R.id.add_shipping_address_titleText);

		consigneeLin = (LinearLayout) findViewById(R.id.add_shipping_address_consigneeDeleteLin);
		phoneNumLin = (LinearLayout) findViewById(R.id.add_shipping_address_phoneNumDeleteLin);
		areaLin = (LinearLayout) findViewById(R.id.add_shipping_address_areaDeleteLin);
		particularAreaLin = (LinearLayout) findViewById(R.id.add_shipping_address_particularAreaDeleteLin);

		confirmBtn = (Button) findViewById(R.id.add_shipping_address_comfirBtn);

		consigneeLin.setOnClickListener(this);
		phoneNumLin.setOnClickListener(this);
		areaLin.setOnClickListener(this);
		particularAreaLin.setOnClickListener(this);

		confirmBtn.setOnClickListener(this);

		consigneeEd.addTextChangedListener(new MyTextWatcher(consigneeEd, 15,
				consigneeLin));
		phoneNumEd.addTextChangedListener(new MyTextWatcher(phoneNumEd, 11,
				phoneNumLin));
		// areaEd.addTextChangedListener(new MyTextWatcher(areaEd, 15,
		// areaLin));
		particularAreaEd.addTextChangedListener(new MyTextWatcher(
				particularAreaEd, 30, particularAreaLin));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_shipping_address_consigneeDeleteLin:// 清空收货人
			consigneeEd.setText("");
			break;
		case R.id.add_shipping_address_phoneNumDeleteLin:// 清空手机号
			phoneNumEd.setText("");
			break;
		case R.id.add_shipping_address_areaDeleteLin:// 清空所在地
			areaText.setText("");
			break;
		case R.id.add_shipping_address_particularAreaDeleteLin:// 清空详细地址
			particularAreaEd.setText("");
			break;
		case R.id.add_shipping_address_comfirBtn:// 确定
			consigneestr=consigneeEd.getText().toString();
			phoneNumstr=phoneNumEd.getText().toString();
			particularAreatr=particularAreaEd.getText().toString();
			if (consigneestr.equals("")) {
				Toast.makeText(getApplicationContext(), "请填写收货人", 10).show();
			} else if (phoneNumstr.equals("")) {
				Toast.makeText(getApplicationContext(), "请填写手机号码", 10).show();
			} else if (!IsCheckPhoneNum.judgePhoneNums(phoneNumEd.getText()
					.toString())) {
				Toast.makeText(getApplicationContext(), "请填写正确手机号码", 10).show();
			} else if (areaText.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(), "请填写所在地", 10).show();
			} else if (particularAreatr.equals("")) {
				Toast.makeText(getApplicationContext(), "请填写详细地址", 10).show();
			} else {
				// 填写参数完成
				new AddAddressTask().executeOnExecutor(Executors.newCachedThreadPool());
			}
			break;

		default:
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	private void isAreaSelector() {
		// 选项选择器
		pvOptions = new OptionsPickerView(Add_Shipping_AddressActivity.this);

		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				System.out.println(System.currentTimeMillis());
				if (item1 != null && item2 != null && item3 != null) {
					handler.sendEmptyMessageDelayed(1, 10);
					return;
				}
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

				handler.sendEmptyMessageDelayed(1, 10);

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
						areastr = item1.get(options1).getPickerViewText()
								+ item2.get(options1).get(option2)
										.getPickerViewText()
								+ item3.get(options1).get(option2)
										.get(options3).getPickerViewText();
						areaText.setText(areastr);
					}
				});
		// 点击弹出选项选择器
		add_shipping_address_areaLin
				.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {
						pvOptions.show();
						MyInputMethodManager.MyInputMethodManager1(add_shipping_address_areaLin, Add_Shipping_AddressActivity.this);
					}
				});
		add_shipping_address_areaLin.setClickable(false);
	}

	private Handler handler = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				System.out.println(System.currentTimeMillis());
				// 三级联动效果
				pvOptions.setPicker(item1, item2, item3, true);
				pvOptions.setCyclic(true, true, true);
				pvOptions.setSelectOptions(0, 0, 0);
				add_shipping_address_areaLin.setClickable(true);
				break;
			case 10000:
				MyToast.Toast(getApplicationContext(),
						MyApp.Program_Exception_Prompt);
				break;
			case 10001:
				MyToast.Toast(getApplicationContext(), MyApp.NODATA_Prompt);
				break;
			case 0:
				String string = (String) msg.obj;
				MyToast.Toast(getApplicationContext(), string);
				finish();
				break;
			default:
				break;
			}
			
		};
	};

	private void isTitleText() {
		state = this.getIntent().getExtras().getString("state");
		if (state != null) {
			if (state.equals("0")) {
				titleText.setText("添加收货地址");
			} else {
				titleText.setText("修改收货地址");
				name = this.getIntent().getExtras().getString("name");
				phoneNum = this.getIntent().getExtras().getString("phoneNum");
				area = this.getIntent().getExtras().getString("area");
				particular_area = this.getIntent().getExtras().getString("particular_area");
				consigneeEd.setText(name);
				phoneNumEd.setText(phoneNum);
				particularAreaEd.setText(particular_area);
				areaText.setText(area);
			}
		}
	}

	private class AddAddressTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Log.e("areastr", areastr);
			Log.e("particularAreatr", particularAreatr);
			Log.e("consigneestr", consigneestr);
			Log.e("phoneNumstr", phoneNumstr);
			String result=OKHTTP_POST.doPost6(MyApp.base_address+"usersaction/insreceiptaddress.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()), "receipt_address", particularAreatr, "receipt_area", areastr, "receipt_name", consigneestr, "receipt_phone", phoneNumstr,"state ","0");
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("程序异常")) {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					} else {
						handler.sendMessage(handler.obtainMessage(0, string));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
			}
			return null;
		}
	}
}
