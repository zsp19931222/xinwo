package com.quwu.xinwo.home_page;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.Search_ListviewAdapter;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.SearchEntity;
import com.quwu.xinwo.mywight.MyListView;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.sqlite.SearchSQLiteOpenHelper;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class SearchActivity extends SwipeBackActivity {

	private List<String> list;
	private List<String> result_list;
	private List<String> lately_list;
	private Search_ListviewAdapter listViewAdapter;
	private MyListView listView;
	private ListView result_listview;
	private LinearLayout layout;
	private EditText search_Ed;
	private LinearLayout search_hotLin;
	private Button search_searchBtn;//搜索按钮
	private Button search_clearBtn;//清空按钮
	
private TextView search_latelyText;
private LinearLayout search_latelyLin;
	
	private List<SearchEntity> json_list;

	  private SearchSQLiteOpenHelper helper = new SearchSQLiteOpenHelper(this);;
	    private SQLiteDatabase db;
	    private BaseAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		FinishActivity.finish(R.id.search_returnRel, SearchActivity.this);
		findID();
		
		new GridViewTask().executeOnExecutor(Executors.newCachedThreadPool());
	}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	isListview();
}
	private void findID() {
		search_latelyText=(TextView) findViewById(R.id.search_latelyText);
		search_latelyLin=(LinearLayout) findViewById(R.id.search_latelyLin);
		search_Ed=(EditText) findViewById(R.id.search_Ed);
		listView = (MyListView) findViewById(R.id.search_listview);
		layout=(LinearLayout) findViewById(R.id.search_Lin);
		search_hotLin=(LinearLayout) findViewById(R.id.search_hotLin);
		result_listview=(ListView) findViewById(R.id.search_result);
		search_searchBtn=(Button) findViewById(R.id.search_searchBtn);
		search_clearBtn=(Button) findViewById(R.id.search_clearBtn);
		
		search_Ed.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.toString() == null || "".equals(s.toString())) {
					search_hotLin.setVisibility(View.VISIBLE);
					result_listview.setVisibility(View.GONE);
				
				} else {
					search_hotLin.setVisibility(View.GONE);
					result_listview.setVisibility(View.VISIBLE);
					new ResultTask().executeOnExecutor(Executors.newCachedThreadPool(),s.toString());
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
		
//		 // 搜索框的键盘搜索键点击回调
//		home_page_classifyBtn.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键
// 
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
//                    // 先隐藏键盘
//                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
//                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
//                    boolean hasData = hasData(search_Ed.getText().toString().trim());
//                    if (!hasData) {
//                        insertData(search_Ed.getText().toString().trim());
//                        queryData("");
//                    }
//                    // TODO 根据输入的内容模糊查询商品，并跳转到另一个界面，由你自己去实现
//                    MyToast.Toast(SearchActivity.this, "clicked!");
//                }
//                return false;
//            }
//        });
		search_searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                boolean hasData = hasData(search_Ed.getText().toString().trim());
                if (!hasData) {
                    insertData(search_Ed.getText().toString().trim());
                    queryData("");
                }
                // TODO 根据输入的内容模糊查询商品，并跳转到另一个界面，由你自己去实现
                MyToast.Toast(SearchActivity.this, "clicked!");
			}
		});
		search_clearBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				deleteData();
				isListview();
			}
		});
	}
	  /**
     * 插入数据
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }
 
    /**
     * 模糊查询数据
     */
    private void queryData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
                new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    /**
     * 检查数据库中是否已经有该条记录
     */
    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }
 
    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }
 
	private void isListview() {
		lately_list = new ArrayList<String>();
		Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records order by id desc", null);
		while(cursor.moveToNext()){  
            String name = cursor.getString(cursor.getColumnIndex("name"));  
            lately_list.add(name);
        }  
		if (lately_list.size()==0) {
			search_latelyText.setVisibility(View.GONE);
			search_latelyLin.setVisibility(View.GONE);
			search_clearBtn.setVisibility(View.GONE);
		}else {
			search_latelyText.setVisibility(View.VISIBLE);
			search_latelyLin.setVisibility(View.VISIBLE);
			search_clearBtn.setVisibility(View.VISIBLE);
			listViewAdapter = new Search_ListviewAdapter(lately_list,
					getApplicationContext());
			listView.setAdapter(listViewAdapter);
		}
	}

	//热搜数据
	private class GridViewTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			list = new ArrayList<String>();
			String result=OKHTTP_POST.doPost1(MyApp.base_address+"searchaction/gethotsearch.do", "", "");
			if (result!=null) {
				try {
					JSONObject jsonObject=new JSONObject(result);
					String string=jsonObject.getString("1");
					Gson gson = new Gson();
					json_list = gson.fromJson(string,
							new TypeToken<List<SearchEntity>>() {
							}.getType());
					for (int i = 0; i < json_list.size(); i++) {
						list.add(json_list.get(i).getSearch());
					}
					handler.sendEmptyMessageDelayed(0, 10);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
	}
	
	private class ResultTask extends AsyncTask<String, Void, Void>{
		String string;
		@Override
		protected Void doInBackground(String... params) {
			if (result_list!=null) {
				result_list.clear();
			}
			result_list=new ArrayList<String>();
			String result=OKHTTP_POST.doPost1(MyApp.base_address+"searchaction/znsearch.do", "search", params[0]);
			if (result!=null) {
				Log.e("", result);
				try {
					JSONObject jsonObject=new JSONObject(result);
					 string=jsonObject.getString("1");
					 if (!string.equals("没有数据")) {
						 Gson gson = new Gson();
						 json_list = gson.fromJson(string,
								 new TypeToken<List<SearchEntity>>() {
						 }.getType());
						 for (int i = 0; i < json_list.size(); i++) {
							 result_list.add(json_list.get(i).getSearch());
						 }
						 handler.sendEmptyMessageDelayed(1, 10);
					}else {
						handler.sendMessage(handler.obtainMessage(2, string));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		
		}
	}
	
	private Handler handler=new Handler(){
		@SuppressWarnings("deprecation")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				
				for (int i = 0; i < list.size(); i++) {  //动态添加textview
		            TextView text = new TextView(SearchActivity.this);  
		           text.setText(list.get(i));
		           text.setTextColor(getResources().getColor(R.color.六六六));  
		           text.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg)); 
		           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		        		    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		           params.setMargins(10,10, 10, 10);
		           text.setLayoutParams(params);
		           text.setPadding(16, 5, 16, 5);
		            layout.addView(text);  
		       }  
				break;
			case 1:
				listViewAdapter = new Search_ListviewAdapter(result_list,
						getApplicationContext());
				result_listview.setAdapter(listViewAdapter);
				break;
			case 2:
				break;
			default:
				break;
			}
		};
	};
	
}
