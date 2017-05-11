package com.quwu.xinwo.mine;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.Tool;

/**
 * 
 * 关于我们界面
 *
 * @author ZhouShaoPeng
 *
 * */
public class About_USActivity extends SwipeBackActivity implements
		OnClickListener {

	private TextView mine_about_us_versionText;// 版本号
	private RelativeLayout mine_about_us_agreementRel;// 协议
	private RelativeLayout mine_about_us_feedbackRel;// 反馈

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);
		FinishActivity.finish(R.id.mine_about_us_returnRel, this);
		findID();
	}

	private void findID() {
		mine_about_us_versionText = (TextView) findViewById(R.id.mine_about_us_versionText);
		try {
			mine_about_us_versionText.setText(getVersionName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		mine_about_us_agreementRel = (RelativeLayout) findViewById(R.id.mine_about_us_agreementRel);
		mine_about_us_feedbackRel = (RelativeLayout) findViewById(R.id.mine_about_us_feedbackRel);
		mine_about_us_agreementRel.setOnClickListener(this);
		mine_about_us_feedbackRel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.mine_about_us_agreementRel:

				break;
			case R.id.mine_about_us_feedbackRel:
				intent = new Intent(About_USActivity.this,
						FeedBackActivity.class);
				break;

			default:
				break;
			}
			if (intent != null) {
				startActivity(intent);
			}
		}
	}

	/*
	 * 获取当前程序的版本号
	 */
	public String getVersionName() throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),
				0);
		return packInfo.versionName;
	}
}
