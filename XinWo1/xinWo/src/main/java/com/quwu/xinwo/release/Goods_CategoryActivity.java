package com.quwu.xinwo.release;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.PinnedHeaderExpandableBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Goods_CategoryEntity;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.pinnedheaderexpandable.PinnedHeaderExpandableAdapter;
import com.quwu.xinwo.pinnedheaderexpandable.PinnedHeaderExpandableListView;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class Goods_CategoryActivity extends SwipeBackActivity {

	private PinnedHeaderExpandableListView explistview;
//	private String[][] childrenData=new String[9][1];
//	private String[] groupData=new String[9];
	private List<String> list1;
	private List<String> list2;
	private HashMap<String, List<String>> hashMap;
	private int expandFlag = -1;// 控制列表的展开
	private PinnedHeaderExpandableAdapter adapter;

	/**
	 * 服务器数据
	 * */
	private String twolevel_id;// 分类ID
	private String twolevel_name;// 分类名称
	private String images_url;// 图片路径
	private List<Goods_CategoryEntity> json_list;
	private List<Goods_CategoryEntity> superclass_ID;// 存放父类ID集合
	private List<PinnedHeaderExpandableBean> subclass_ID;// 存放父类ID集合

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_category);
		FinishActivity.finish(R.id.goods_category_returnRel,
				Goods_CategoryActivity.this);
		initView();
//		new One_LevelTask().executeOnExecutor(Executors.newCachedThreadPool());
		 initData();
	}

	/**
	 * 初始化VIEW
	 */
	private void initView() {
		explistview = (PinnedHeaderExpandableListView) findViewById(R.id.explistview);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
//		groupData[0] = "热门";
//		groupData[1] = "数码电子";
//		groupData[2] = "生活家居";
//		groupData[3] = "热门";
//		groupData[4] = "数码电子";
//		groupData[5] = "生活家居";
//		groupData[6] = "热门";
//		groupData[7] = "数码电子";
//		groupData[8] = "生活家居";
		list1=new ArrayList<String>();
		list2=new ArrayList<String>();
		hashMap=new HashMap<String, List<String>>();
				for (int i = 0; i < 10; i++) {
					list1.add(i+"");
					if (i==0) {
						for (int j = 0; j < i; j++) {
							list2.add(i+"0"+j);
						}
			}
					hashMap.put(i+"", list2);
			}
//		for (int j = 0; j < 9; j++) {
//			childrenData[j][0] = "热门";
//			childrenData[j][0] = "数码电子";
//			childrenData[j][0] = "生活家居";
//			childrenData[j][0] = "热门";
//			childrenData[j][0] = "数码电子";
//			childrenData[j][0] = "生活家居";
//			childrenData[j][0] = "热门";
//			childrenData[j][0] = "数码电子";
//			childrenData[j][0] = "生活家居";
//		}
		// 设置悬浮头部VIEW
		explistview.setHeaderView(getLayoutInflater().inflate(
				R.layout.group_head, explistview, false));
		adapter = new PinnedHeaderExpandableAdapter(hashMap, list1,list2,
				getApplicationContext(), explistview,
				Goods_CategoryActivity.this);
		explistview.setAdapter(adapter);

		// 设置单个分组展开
		// explistview.setOnGroupClickListener(new GroupClickListener());
	}

	class GroupClickListener implements OnGroupClickListener {
		@Override
		public boolean onGroupClick(ExpandableListView parent, View v,
				int groupPosition, long id) {
			if (expandFlag == -1) {
				// 展开被选的group
				explistview.expandGroup(groupPosition);
				// 设置被选中的group置于顶端
				explistview.setSelectedGroup(groupPosition);
				expandFlag = groupPosition;
			} else if (expandFlag == groupPosition) {
				explistview.collapseGroup(expandFlag);
				expandFlag = -1;
			} else {
				explistview.collapseGroup(expandFlag);
				// 展开被选的group
				explistview.expandGroup(groupPosition);
				// 设置被选中的group置于顶端
				explistview.setSelectedGroup(groupPosition);
				expandFlag = groupPosition;
			}
			return true;
		}
	}

	private class One_LevelTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			superclass_ID = new ArrayList<Goods_CategoryEntity>();
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "lableaction/selectgoodscategory.do", "", "");
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					Log.e("", string2);
					if (string2 != null) {
						if (string2.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (string2.equals("当前还没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson
									.fromJson(
											string2,
											new TypeToken<List<Goods_CategoryEntity>>() {
											}.getType());
							
							for (int i = 0; i < json_list.size(); i++) {
								twolevel_id = json_list.get(i).getTwolevel_id();
								twolevel_name = json_list.get(i)
										.getTwolevel_name();
								images_url = json_list.get(i).getImages_url();
								superclass_ID.add(new Goods_CategoryEntity(twolevel_id, twolevel_name, images_url));
								
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
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			new Tow_LevelTask().executeOnExecutor(Executors.newCachedThreadPool(), "1");
		}
	}

	private class Tow_LevelTask extends AsyncTask<String, Void, Void> {
		protected Void doInBackground(String... params) {
			subclass_ID=new ArrayList<PinnedHeaderExpandableBean>();
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "lableaction/selecThreegoodscategory.do", "parent_id",
					params[0]);
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					Log.e("string2", string2);
					if (string2 != null) {
						if (string2.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (string2.equals("当前还没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson
									.fromJson(
											string2,
											new TypeToken<List<Goods_CategoryEntity>>() {
											}.getType());
							
							for (int i = 0; i < json_list.size(); i++) {
								twolevel_id = json_list.get(i).getTwolevel_id();
								twolevel_name = json_list.get(i)
										.getTwolevel_name();
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
			// TODO Auto-generated method stub
			super.onPostExecute(result);
//			groupData=new  String[superclass_ID.size()];
//			for (int i = 0; i < superclass_ID.size(); i++) {
//				groupData[i]=superclass_ID.get(i).getTwolevel_name();
//			}
//			childrenData=new String[superclass_ID.size()][1];
//			for (int j = 0; j < superclass_ID.size(); j++) {
//				for (int j2 = 0; j2 < subclass_ID.size(); j2++) {
//					childrenData[j][0]=subclass_ID.get(j2).getString();
//				}
//			}
			// 设置悬浮头部VIEW
						explistview.setHeaderView(getLayoutInflater().inflate(
								R.layout.group_head, explistview, false));
//						adapter = new PinnedHeaderExpandableAdapter(childrenData, groupData,
//								getApplicationContext(), explistview,
//								Goods_CategoryActivity.this);
						explistview.setAdapter(adapter);
						explistview.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
								Log.e("", "点击了"+position);
							}
						});
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 10000:
				MyToast.Toast(Goods_CategoryActivity.this,
						MyApp.Program_Exception_Prompt);
				break;
			case 10001:
				MyToast.Toast(Goods_CategoryActivity.this, MyApp.NODATA_Prompt);
				break;
			case 0:
				
				break;

			default:
				break;
			}
		};
	};
}
