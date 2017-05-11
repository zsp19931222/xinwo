package com.quwu.xinwo.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.quwu.xinwo.R;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.Tool;

/**
 * 
 * 设置界面
 * 
 * @author ZhouShaoPeng
 * 
 * */

public class Mine_SettingActivity extends SwipeBackActivity implements
		OnClickListener {
	private RelativeLayout mine_setting_personalRel;// 个人资料
	private RelativeLayout mine_setting_helpRel;// 帮助中心
	private RelativeLayout mine_setting_aboutRel;// 关于心窝
	private RelativeLayout mine_setting_shareRel;// 分享
	private RelativeLayout mine_setting_pushRel;// 推送
	private RelativeLayout mine_setting_updateRel;// 更新
	private RelativeLayout mine_setting_cacheRel;// 缓存

	private Button mine_setting_exitBtn;// 退出按钮

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_setting);
		FinishActivity.finish(R.id.mine_setting_returnRel,
				Mine_SettingActivity.this);
		findID();
	}

	private void findID() {
		mine_setting_exitBtn = (Button) findViewById(R.id.mine_setting_exitBtn);
		mine_setting_exitBtn.setOnClickListener(this);

		mine_setting_personalRel = (RelativeLayout) findViewById(R.id.mine_setting_personalRel);
		mine_setting_helpRel = (RelativeLayout) findViewById(R.id.mine_setting_helpRel);
		mine_setting_aboutRel = (RelativeLayout) findViewById(R.id.mine_setting_aboutRel);
		mine_setting_shareRel = (RelativeLayout) findViewById(R.id.mine_setting_shareRel);
		mine_setting_pushRel = (RelativeLayout) findViewById(R.id.mine_setting_pushRel);
		mine_setting_updateRel = (RelativeLayout) findViewById(R.id.mine_setting_updateRel);
		mine_setting_cacheRel = (RelativeLayout) findViewById(R.id.mine_setting_cacheRel);

		mine_setting_personalRel.setOnClickListener(this);
		mine_setting_helpRel.setOnClickListener(this);
		mine_setting_aboutRel.setOnClickListener(this);
		mine_setting_shareRel.setOnClickListener(this);
		mine_setting_pushRel.setOnClickListener(this);
		mine_setting_updateRel.setOnClickListener(this);
		mine_setting_cacheRel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.mine_setting_personalRel:
				intent = new Intent(Mine_SettingActivity.this,
						Personal_DataActivity.class);
				break;
			case R.id.mine_setting_helpRel:
				intent = new Intent(Mine_SettingActivity.this,
						Help_CenterActivity.class);
				break;
			case R.id.mine_setting_aboutRel:
				intent = new Intent(Mine_SettingActivity.this,
						About_USActivity.class);
				break;
			case R.id.mine_setting_shareRel:
				intent = new Intent(Mine_SettingActivity.this,
						ShareActivity.class);
				break;
			case R.id.mine_setting_pushRel:

				break;
			case R.id.mine_setting_updateRel:

				break;
			case R.id.mine_setting_cacheRel:
				intent = new Intent(Mine_SettingActivity.this,
						Wipe_CacheActivity.class);
				intent.putExtra("maessage", "是否清除缓存");
				intent.putExtra("title", "温馨提示");
				break;
			case R.id.mine_setting_exitBtn:

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
