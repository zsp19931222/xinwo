package com.quwu.xinwo.home_page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.quwu.xinwo.R;
import com.quwu.xinwo.mywight.MyListView;
import com.quwu.xinwo.sortlistview.CharacterParser;
import com.quwu.xinwo.sortlistview.ClearEditText;
import com.quwu.xinwo.sortlistview.PinyinComparator;
import com.quwu.xinwo.sortlistview.SideBar;
import com.quwu.xinwo.sortlistview.SideBar.OnTouchingLetterChangedListener;
import com.quwu.xinwo.sortlistview.SortAdapter;
import com.quwu.xinwo.sortlistview.SortModel;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class Area_SwitchOver extends SwipeBackActivity implements
		OnClickListener {
	private GridView mGridView;
	private List<Search_Bean> list;
	private Area_Now_Adapter gridviewadapter;
	private MyListView listView;
//	private Button returntop_btn;
	private ScrollView scrollView;
	private Handler handler = new Handler();

	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.area_switchover);
		FinishActivity.finish(R.id.area_switchover_returnRel,
				Area_SwitchOver.this);
		findId();
		setEvent();
		isGirdView();
		isGridOnClick();
		initViews();
		new Task().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private class Task extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
//			initViews();
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
//			sortAdapter = new SortAdapter(Area_SwitchOver.this, SourceDataList);
//			listView.setAdapter(sortAdapter);
//			editText = (ClearEditText) findViewById(R.id.area_switchover_ed);
//
//			// 根据输入框输入值的改变来过滤搜索
//			editText.addTextChangedListener(new TextWatcher() {
//
//				@Override
//				public void onTextChanged(CharSequence s, int start, int before,
//						int count) {
//					// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
//					filterData(s.toString());
//				}
//
//				@Override
//				public void beforeTextChanged(CharSequence s, int start, int count,
//						int after) {
//
//				}
//
//				@Override
//				public void afterTextChanged(Editable s) {
//				}
//			});
		}
	}
	
	private void isGirdView() {
		list = new ArrayList<Search_Bean>();
		list.add(new Search_Bean("北京"));
		list.add(new Search_Bean("上海"));
		list.add(new Search_Bean("深圳"));
		list.add(new Search_Bean("广州"));
		list.add(new Search_Bean("西安"));
		list.add(new Search_Bean("南京"));
		gridviewadapter = new Area_Now_Adapter(list, getApplicationContext());
		mGridView.setAdapter(gridviewadapter);
	}

	private void findId() {
		scrollView = (ScrollView) findViewById(R.id.area_switchover_scrollview);
//		returntop_btn = (Button) findViewById(R.id.area_switchover_returntop_btn);
//		listView = (MyListView) findViewById(R.id.area_switchover_listview);
		mGridView = (GridView) findViewById(R.id.area_switchover_gridview);
	}

	private void setEvent() {
//		returntop_btn.setOnClickListener(this);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.area_switchover_returntop_btn:
			handler.post(new Runnable() {
				public void run() {
					scrollView.fullScroll(View.FOCUS_UP);
//					returntop_btn.setVisibility(View.GONE);
				}
			});
			break;
		default:
			break;
		}
	}

	private void isGridOnClick() {
		// 传递下标给Adapter来改变背景与字体颜色
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int postion1, long arg3) {
				gridviewadapter.SetSeclection(postion1);
				gridviewadapter.notifyDataSetChanged();
			}
		});
	}

//	private void isScroll() {
//		scrollView.setOnTouchListener(new OnTouchListener() {
//			private int lastY = 0;
//			private int touchEventId = -9983761;
//			@SuppressLint("HandlerLeak")
//			Handler handler = new Handler() {
//				@Override
//				public void handleMessage(Message msg) {
//					super.handleMessage(msg);
//					View scroller = (View) msg.obj;
//					if (msg.what == touchEventId) {
//						if (lastY == scroller.getScrollY()) {
//							handleStop(scroller);
//							// 开始滑动
////							returntop_btn.setVisibility(View.VISIBLE);
//						} else {
//							// 停止滑动
//							handler.sendMessageDelayed(handler.obtainMessage(
//									touchEventId, scroller), 5);
//							lastY = scroller.getScrollY();
//						}
//					}
//				}
//			};
//			public boolean onTouch(View v, MotionEvent event) {
//				if (event.getAction() == MotionEvent.ACTION_UP) {
//					handler.sendMessageDelayed(
//							handler.obtainMessage(touchEventId, v), 5);
//				}
//				return false;
//			}
//
//			private void handleStop(Object view) {
//				ScrollView scroller = (ScrollView) view;
//				lastY = scroller.getScrollY();
//			}
//		});
//	}

//	private void initViews() {
//		// 实例化汉字转拼音类
//		characterParser = CharacterParser.getInstance();
//
//		pinyinComparator = new PinyinComparator();
//
//		sideBar = (SideBar) findViewById(R.id.area_switchover_sideBar);
//		dialog = (TextView) findViewById(R.id.dialog);
//		sideBar.setTextView(dialog);
//		// 设置右侧触摸监听
//				sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
//
//					@Override
//					public void onTouchingLetterChanged(String s) {
//						// 该字母首次出现的位置
//						int position = sortAdapter.getPositionForSection(s.charAt(0));
//						Log.e("position", position+"");
//						if (position != -1) {
//							listView.setSelection(position);
//						}
//
//					}
//				});
//
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
//				Toast.makeText(getApplication(),
//						((SortModle) sortAdapter.getItem(position)).getName(),
//						Toast.LENGTH_SHORT).show();
//			}
//		});
//
//		SourceDataList = filledData(getResources().getStringArray(R.array.zsp));
//
//		// 根据a-z进行排序源数据
//		Collections.sort(SourceDataList, pinyinComparator);
//	}
//
//	/**
//	 * 为ListView填充数据
//	 * 
//	 * @param date
//	 * @return
//	 */
//	private List<SortModle> filledData(String[] date) {
//		List<SortModle> mSortList = new ArrayList<SortModle>();
//
//		for (int i = 0; i < date.length; i++) {
//			SortModle sortModel = new SortModle();
//			sortModel.setName(date[i]);
//			// 汉字转换成拼音
//			String pinyin = characterParser.getSelling(date[i]);
//			String sortString = pinyin.substring(0, 1).toUpperCase();
//
//			// 正则表达式，判断首字母是否是英文字母
//			if (sortString.matches("[A-Z]")) {
//				sortModel.setSortLetters(sortString.toUpperCase());
//			} else {
//				sortModel.setSortLetters("#");
//			}
//
//			mSortList.add(sortModel);
//		}
//		return mSortList;
//
//	}
//
//	/**
//	 * 根据输入框中的值来过滤数据并更新ListView
//	 * 
//	 * @param filterStr
//	 */
//	private void filterData(String filterStr) {
//		List<SortModle> filterDateList = new ArrayList<SortModle>();
//
//		if (TextUtils.isEmpty(filterStr)) {
//			filterDateList = SourceDataList;
//		} else {
//			filterDateList.clear();
//			for (SortModle sortModel : SourceDataList) {
//				String name = sortModel.getName();
//				if (name.toUpperCase().indexOf(
//						filterStr.toString().toUpperCase()) != -1
//						|| characterParser.getSelling(name).toUpperCase()
//								.startsWith(filterStr.toString().toUpperCase())) {
//					filterDateList.add(sortModel);
//				}
//			}
//		}
//
//		// 根据a-z进行排序
//		Collections.sort(filterDateList, pinyinComparator);
//		sortAdapter.updateListView(filterDateList);
//	}
	private void initViews() {
		//实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		
		pinyinComparator = new PinyinComparator();
		
		sideBar = (SideBar) findViewById(R.id.area_switchover_sideBar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		
		//设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
			@Override
			public void onTouchingLetterChanged(String s) {
				//该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position);
				}
			}
		});
		
		sortListView = (ListView) findViewById(R.id.area_switchover_listview);
		setListViewHeightBasedOnChildren(sortListView);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//这里要利用adapter.getItem(position)来获取当前position所对应的对象
				Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
			}
		});
		
		SourceDateList = filledData(getResources().getStringArray(R.array.zsp));
		
		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);
		
		
		mClearEditText = (ClearEditText) findViewById(R.id.area_switchover_ed);
		
		//根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}


	/**
	 * 为ListView填充数据
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String [] date){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		
		for(int i=0; i<date.length; i++){
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
			//汉字转换成拼音
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			
			// 正则表达式，判断首字母是否是英文字母
			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}
			
			mSortList.add(sortModel);
		}
		return mSortList;
		
	}
	
	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<SortModel> filterDateList = new ArrayList<SortModel>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(SortModel sortModel : SourceDateList){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		
		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
	/**
	* 动态设置ListView的高度
	* @param listView
	*/
	public static void setListViewHeightBasedOnChildren(ListView listView) {
	    if(listView == null) return;
	    ListAdapter listAdapter = listView.getAdapter();
	    if (listAdapter == null) {
	        // pre-condition
	        return;
	    }
	    int totalHeight = 0;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        View listItem = listAdapter.getView(i, null, listView);
	        listItem.measure(0, 0);
	        totalHeight += listItem.getMeasuredHeight();
	    }
	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	}
}
