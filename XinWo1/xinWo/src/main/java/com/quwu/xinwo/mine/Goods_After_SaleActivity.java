package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.adapter.My_Release_GridviewAdapter;
import com.quwu.xinwo.bean.My_Release_GridviewBean;
import com.quwu.xinwo.mywight.MyGridView;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.Tool;

public class Goods_After_SaleActivity extends SwipeBackActivity implements
		OnClickListener {

	private My_Release_GridviewAdapter adapter;
	private List<My_Release_GridviewBean> gridviewlist;
	private MyGridView gridView;

	private Button goods_after_agreeBtn;
	private Button goods_after_disagreeBtn;
	private ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_after_sale);
		imageLoader=ImageLoader.getInstance();
		FinishActivity.finish(R.id.goods_after_sale_returnRel, this);
		findID();
		isGridView();
	}

	private void findID() {
		gridView = (MyGridView) findViewById(R.id.goods_after_sale_gridview);

		goods_after_agreeBtn = (Button) findViewById(R.id.goods_after_agreeBtn);
		goods_after_agreeBtn.setOnClickListener(this);
		goods_after_disagreeBtn = (Button) findViewById(R.id.goods_after_disagreeBtn);
		goods_after_disagreeBtn.setOnClickListener(this);
	}

	private void isGridView() {
		gridviewlist = new ArrayList<My_Release_GridviewBean>();
		for (int i = 0; i < 10; i++) {
			gridviewlist.add(new My_Release_GridviewBean(
					"http://pic1.nipic.com/2008-12-25/2008122510134038_2.jpg"));
		}
		adapter = new My_Release_GridviewAdapter(getApplicationContext(),
				gridviewlist,imageLoader);
		int size = gridviewlist.size();
		int length = 100;
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
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
		gridView.setAdapter(adapter);
		gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.goods_after_agreeBtn:
				intent = new Intent(Goods_After_SaleActivity.this,
						Wipe_CacheActivity.class);
				intent.putExtra("maessage", "是否同意退款？");
				intent.putExtra("title", "退款提示");
				break;
			case R.id.goods_after_disagreeBtn:
				intent = new Intent(Goods_After_SaleActivity.this,
						Goods_After_DissagreeActivity.class);
				break;

			default:
				break;
			}
			if (intent != null) {
				startActivity(intent);
			}
		}
	}
}
