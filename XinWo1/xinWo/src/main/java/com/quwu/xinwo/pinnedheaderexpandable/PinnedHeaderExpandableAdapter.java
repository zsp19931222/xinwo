package com.quwu.xinwo.pinnedheaderexpandable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quwu.xinwo.R;
import com.quwu.xinwo.mywight.MyGridView;
import com.quwu.xinwo.pinnedheaderexpandable.PinnedHeaderExpandableListView.HeaderAdapter;

public class PinnedHeaderExpandableAdapter extends BaseExpandableListAdapter
		implements HeaderAdapter {
//	private String[][] childrenData;
	private HashMap<String, List<String>>hashMap;
	private List<String> list1;
	private List<String> list2;
//	private String[] groupData;
	private Context context;
	private PinnedHeaderExpandableListView listView;
	private LayoutInflater inflater;
	private MyGridView gridView;
	private List<Bean> list;
	private Activity activity;

	public PinnedHeaderExpandableAdapter(HashMap<String, List<String>>hashMap,
			List<String> list1,List<String> list2,  Context context,
			PinnedHeaderExpandableListView listView,Activity activity) {
		this.hashMap=hashMap;
		this.list1 = list1;
		this.list2 = list2;
		this.context = context;
		this.listView = listView;
		this.activity=activity;
		inflater = LayoutInflater.from(this.context);
		
	}
	

	public Object getChild(int groupPosition, int childPosition) {
		return 0;
	}

	
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView != null) {
			view = convertView;
		} else {
			view = createChildrenView();
		}
		Log.e("groupPosition", groupPosition+"");
		Log.e("childPosition", childPosition+"");
		list=new ArrayList<PinnedHeaderExpandableAdapter.Bean>();
		gridView = (MyGridView) view.findViewById(R.id.gridview);
//		if (groupPosition%2==0) {
//			for (int i = 0; i < list2.size(); i++) {
//				list.add(new Bean(list1.get(i)));
//			}
		for (int i = 0; i < hashMap.get(groupPosition+"").size(); i++) {
			list.add(new Bean(hashMap.get(groupPosition+"").get(i)));
		}
//		}else {
//			for (int i = 0; i < 5; i++) {
//			}
//		}
		Adapter adapter = new Adapter();
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				   //数据是使用Intent返回
//                Intent intent = new Intent();
//                //把返回数据存入Intent
//                intent.putExtra("selection_sort", childrenData[groupPosition][childPosition]+":"+position);
//                //设置返回数据
//               activity.setResult(1, intent);
//                //关闭Activity
//                activity.finish();
//				Toast.makeText(context, "点击了"+childrenData[groupPosition][childPosition]+"第"+position+"项", 10).show();
			}
			});
				return view;
	}

	
	private int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	
	public Object getGroup(int groupPosition) {
		return list1.get(groupPosition);
	}

	
	public int getGroupCount() {
		return list1.size();
	}

	
	public long getGroupId(int groupPosition) {
		return 0;
	}

	
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = null;
		if (convertView != null) {
			view = convertView;
		} else {
			view = createGroupView();
		}

		ImageView iv = (ImageView) view.findViewById(R.id.groupIcon);

		if (isExpanded) {
			iv.setImageResource(R.drawable.ic_launcher);
		} else {
			iv.setImageResource(R.drawable.ic_launcher);
		}

		TextView text = (TextView) view.findViewById(R.id.groupto);
		text.setText(list1.get(groupPosition));
		return view;
	}

	
	public boolean hasStableIds() {
		return true;
	}

	
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	private View createChildrenView() {
		return inflater.inflate(R.layout.child, null);
	}

	private View createGroupView() {
		return inflater.inflate(R.layout.group, null);
	}

	
	public int getHeaderState(int groupPosition, int childPosition) {
		final int childCount = getChildrenCount(groupPosition);
		if (childPosition == childCount - 1) {
			return PINNED_HEADER_PUSHED_UP;
		} else if (childPosition == -1
				&& !listView.isGroupExpanded(groupPosition)) {
			return PINNED_HEADER_GONE;
		} else {
			return PINNED_HEADER_VISIBLE;
		}
	}

	
	public void configureHeader(View header, int groupPosition,
			int childPosition, int alpha) {
		String groupData = this.list1.get(groupPosition);
		((TextView) header.findViewById(R.id.groupto)).setText(groupData);
	}

	private SparseIntArray groupStatusMap = new SparseIntArray();

	
	public void setGroupClickStatus(int groupPosition, int status) {
		groupStatusMap.put(groupPosition, status);
	}

	
	public int getGroupClickStatus(int groupPosition) {
		if (groupStatusMap.keyAt(groupPosition) >= 0) {
			return groupStatusMap.get(groupPosition);
		} else {
			return 0;
		}
	}


	class Bean {
		String string;

		public Bean(String string) {
			super();
			this.string = string;
		}

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}
	}

	class Adapter extends BaseAdapter {

		
		public int getCount() {
			if (list==null) {
				return 0;
			}
			return list.size();
		}

		
		public Object getItem(int position) {
			
			return null;
		}

		
		public long getItemId(int position) {
			
			return 0;
		}

		
		public View getView(int position, View convertView, ViewGroup parent) {
			if (list!=null) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.gridview_item, null);
				holder.textView = (TextView) convertView
						.findViewById(R.id.gridview_item_Text);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.textView.setText(list.get(position).getString());
			}
			return convertView;
		}

		class ViewHolder {
			private TextView textView;
		}
	}
}
