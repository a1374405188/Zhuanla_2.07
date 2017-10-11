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
import com.beikbank.android.data.ProjectInfo;
import com.beikbank.android.data.ProjectList_data;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.ProjestListManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.pullrefresh.PullToRefreshListView;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import comc.beikbank.android.R;

//投资项目列表
public class ProjectListActivity extends BaseActivity implements OnClickListener{
   private Activity act=this;
	private final String TAG="ProjectListActivity";
	private TextView titleTv;
	private LinearLayout linear_left;
	private PullToRefreshListView listview_project;
	private ListView mListView;
	private ProjectAdapter adapter;
	private ArrayList<ProjectInfo> list=new ArrayList<ProjectInfo>();
	private String sid;
    int start=0;
    int end=19;
    int count=20;
    /**
     * 传递参数使用
     */
    public static String index="index";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		StateBarColor.init(this,0xffffffff);
		initView();

	}
	public void initView(){
		sid=getIntent().getStringExtra(index);
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.project_list));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		listview_project=(PullToRefreshListView)findViewById(R.id.listview_project);
		listview_project.setPullLoadEnabled(true);
		listview_project.setScrollLoadEnabled(false);
		listview_project.setPullRefreshEnabled(false);
		
		
		adapter=new ProjectAdapter(this,list);
		mListView=listview_project.getRefreshableView();
		mListView.setDivider(getResources().getDrawable(R.color.transparent));
		mListView.setDividerHeight(24);		
		mListView.setScrollingCacheEnabled(false);
		mListView.setFadingEdgeLength(0);
		mListView.setAdapter(adapter);
		listview_project.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//				BeikBankApi.getInstance().getProjectInfoList(ProjectListActivity.this,
//						sid,start+"",end+"", getProjectListInfoHandler);
//				System.out.println(1234);
                new ProjestListManager(act, icb).start(sid,start+"",end+"");
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
			
//				BeikBankApi.getInstance().getProjectInfoList(ProjectListActivity.this,
//						sid,start+"",end+"", getProjectListInfoHandler);
				new ProjestListManager(act, icb).start(sid,start+"",end+"");
				start+=count;
				end+=count;
			}
		});
		listview_project.doPullRefreshing(true, 200);
		
	}

   ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		listview_project.onPullUpRefreshComplete();
		if(obj!=null&&obj instanceof ProjectList_data)
		{
			ProjectList_data pd=(ProjectList_data) obj;
			ArrayList<ProjectInfo> projectInfoList=pd.data;
			if(projectInfoList!=null&&projectInfoList.size()>0)
			{
				list.addAll(projectInfoList);
				adapter.notifyDataSetChanged();
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

//	JsonHttpResponseHandler getProjectListInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();
//		}
//
//		@Override
//		public void onFinish() {
//			super.onFinish();
//			listview_project.onPullUpRefreshComplete();
//		}
//
//		@Override
//		public void onFailure(Throwable error, String content) {
//			Utils.log(TAG, "onFailure()"+content);		
//			listview_project.onPullUpRefreshComplete();
//		}
//
//		@Override
//		public void onSuccess(Response response) {
//			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
//			try
//			{
//			Gson gson=new Gson();
//			ArrayList<ProjectInfo> projectInfoList=gson.fromJson(Utils.getJsonResult(
//					response.getResponseString(), BeikBankConstant.TYPE_JSONDATA), new TypeToken<List<ProjectInfo>>(){  
//			}.getType());
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				//list.clear();
//				if(projectInfoList!=null&&projectInfoList.size()>0)
//				{
//					list.addAll(projectInfoList);
//					adapter.notifyDataSetChanged();
//				}
//			}
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//				MessageManger.showMeg(act,getString(R.string.service_data_error),Toast.LENGTH_SHORT);
//				LogHandler.writeLogFromException(TAG,e);
//			}
//		}
//
//	};

}
