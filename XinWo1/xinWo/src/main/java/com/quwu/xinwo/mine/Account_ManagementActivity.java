package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Account_ManagementAdapter;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Account_ManagementEntity;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;

public class Account_ManagementActivity extends SwipeBackActivity {

	private List<Account_ManagementEntity> list;
	private ListView listView;
	private Account_ManagementAdapter adapter;

	private Button accounts_management_addBtn;
	private TextView accounts_management_titleText;

	/**
	 * 返回数据
	 * */
	private String transaction_id;// 收款信息表的ID
	private String transaction_num;// 账号
	private String transaction_phone;// 绑定的手机号码
	private String transaction_name;// 姓名
	private String account_name;// 银行卡名称或者支付宝
	private List<Account_ManagementEntity> json_list;

	private String state;//上个页面传过来的，0-账户管理，1-选择提现账户
	private String money;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accounts_management);
		FinishActivity.finish(R.id.accounts_management_returnRel,
				Account_ManagementActivity.this);
		findID();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {
		state=this.getIntent().getExtras().getString("state");
		
		listView = (ListView) findViewById(R.id.accounts_management_listview);
		accounts_management_titleText=(TextView) findViewById(R.id.accounts_management_titleText);
		accounts_management_addBtn = (Button) findViewById(R.id.accounts_management_addBtn);
	
		if (state.equals("0")) {
			accounts_management_titleText.setText("账户管理");
			accounts_management_addBtn.setVisibility(View.VISIBLE);
			accounts_management_addBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Account_ManagementActivity.this,
							Add_AccountActivity.class);
					startActivity(intent);
				}
			});
		}else {
			money=this.getIntent().getExtras().getString("money");
			accounts_management_titleText.setText("选择提现账户");
			accounts_management_addBtn.setVisibility(View.GONE);
		}
	}

	private class LoadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			list = new ArrayList<Account_ManagementEntity>();
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "myAccount/selectAccount.do", "user_id",
					MySharePreferences
							.GetUser_ID(Account_ManagementActivity.this));
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					if (string2.equals("该用户还没有绑定账户")) {
						handler.sendMessage(handler.obtainMessage(1,
								"您还没有相关的账户哦~请先去添加账户吧！"));
					} else if (string2.equals("程序异常")) {
						handler.sendMessage(handler.obtainMessage(1,
								MyApp.Program_Exception_Prompt));
					} else {
						Gson gson = new Gson();
						json_list = gson
								.fromJson(
										string2,
										new TypeToken<List<Account_ManagementEntity>>() {
										}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							transaction_id = json_list.get(i)
									.getTransaction_id();
							transaction_num = json_list.get(i)
									.getTransaction_num();
							transaction_phone = json_list.get(i)
									.getTransaction_phone();
							transaction_name=json_list.get(i).getTransaction_name();
							account_name = json_list.get(i).getAccount_name();
							list.add(new Account_ManagementEntity(
									transaction_id, transaction_num,
									transaction_phone,transaction_name, account_name));
						}
						handler.sendEmptyMessageDelayed(0, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				handler.sendMessage(handler.obtainMessage(1,
						MyApp.Program_Exception_Prompt));
			}
			return null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				adapter = new Account_ManagementAdapter(list,
						Account_ManagementActivity.this);
				listView.setAdapter(adapter);
				
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (state.equals("0")) {
						Intent intent = new Intent(
								Account_ManagementActivity.this,
								Account_DetailActivity.class);
							intent.putExtra("account_name", list.get(position).getAccount_name());
							intent.putExtra("transaction_id", list.get(position).getTransaction_id());
							intent.putExtra("transaction_num", list.get(position).getTransaction_num());
							intent.putExtra("transaction_phone", list.get(position).getTransaction_phone());
							intent.putExtra("transaction_name", list.get(position).getTransaction_name());
						startActivity(intent);
						}else {
							Intent intent=new Intent(Account_ManagementActivity.this,WithdrawActivity.class);
							intent.putExtra("account_name", list.get(position).getAccount_name());
							intent.putExtra("transaction_id", list.get(position).getTransaction_id());
							intent.putExtra("transaction_num", list.get(position).getTransaction_num());
							intent.putExtra("money", money);
							setResult(1, intent);
							finish();
						}
					}
				});
				break;
			case 1:
				String string = (String) msg.obj;
				MyToast.Toast(Account_ManagementActivity.this, string);
				break;

			default:
				break;
			}
		};
	};
}
