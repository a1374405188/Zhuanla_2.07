package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.adapter.ProjectAdapter;
import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.data.DingqiProject_data;
import com.beikbank.android.data.ProjectInfo;
import com.beikbank.android.data.ProjectList_data;
import com.beikbank.android.dataparam.DingqiPIParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.DingQIProjectManager;
import com.beikbank.android.net.impl.ProjestListManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.pullrefresh.PullToRefreshListView;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import coma.beikbank.android.R;



//定期投资项目列表
public class ProjectListActivity2 extends BaseActivity implements OnClickListener{
   private Activity act=this;
	private final String TAG="ProjectListActivity2";
	private TextView titleTv;
	private LinearLayout linear_left;

	private ListView mListView;
	private ProjectAdapter adapter;
	private ArrayList<ProjectInfo> list=new ArrayList<ProjectInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project2);
		StateBarColor.init(this,0xffffffff);
		initView();
        addData();
	}
	/**
	 * 加载数据
	 */
	public void addData()
	{   
		DingqiPIParam dpi=new DingqiPIParam();
		String s=getIntent().getStringExtra("index1");
		if(s==null)
		{
			dpi.termbondCode="";
		}
		else
		{
			dpi.termbondCode=s;
		}
		DingQIProjectManager dpm=new DingQIProjectManager(act, icb, dpi);
		dpm.start();
	}
	public void initView(){
		
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.project_list));
        mListView=(ListView) findViewById(R.id.listview_project);
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

       
		
	
		
	}

   ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		
		if(obj!=null)
		{
			ProjectList_data pd=(ProjectList_data ) obj;
			ArrayList<ProjectInfo> projectInfoList=pd.data;
			
			if(projectInfoList!=null&&projectInfoList.size()>0)
			{
				list.addAll(projectInfoList);
				adapter=new ProjectAdapter(act,list);
				mListView.setAdapter(adapter);
			}
		}
	}
};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
		}
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
