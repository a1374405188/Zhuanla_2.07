package com.beikbank.android.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.adapter.HelpCenterAdapter;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.data.HelpAndSafety;
import com.beikbank.android.data.HelpAndSafety_data;
import com.beikbank.android.data.HelpInfo;
import com.beikbank.android.dataparam.HelpAndSafetyParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.impl.HelpCenterManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

//帮助中心
public class HelpCenterActivity extends BaseActivity implements OnClickListener{
    private Activity act=this;
    private String TAG="HelpCenterActivity";
	private TextView titleTv;
	private ListView listview_help_center;
	private HelpCenterAdapter adapter;
	private LinearLayout linear_left;
	private ArrayList<HelpInfo> list=new ArrayList<HelpInfo>();
	private String space="    ";
	private String line="\r\n";
	private String[] problemArray={
			"赚啦如何保证用户资金的安全性?",
			"为什么选择赚啦理财？",
			"赚啦理财有哪几款产品？如何购买？",
			"赚啦的年化收益率是多少？会变动吗？",
			"什么是双向T+0购买？",
			"购买后多久确认成功，什么时候开始计算收益？",
			"赎回（取现）需要多久？",
			"节假日是否有收益？",
			"赚啦理财多少起投？最高多少额度？",
			"理财产品的收益率是如何计算的？",
			"赚啦理财支持哪些银行卡？",
			"如何进行银行卡绑卡？能否解绑银行卡？",
			"交易过程中是否有手续费？"
	        };
	private String[] answerArray={
			  space+"模式安全：赚啦不接触您的理财资金，绝非资金池模式，仅作为理财产品的信息交易平台。理财产品均由经过层层筛选且风险率极低的专业p2p机构提供，资金交易流程全部由持有人民银行支付牌照的第三方机构代扣。"
	        +line+
	          space+"资金安全：交易资金全部同卡进出，取现资金只能回到原卡。每笔交易资金均经过严格审核，购买后会有确认冻结期。任何可能存在风险的交易都会被系统冻结，并将资金退回到原卡。对每笔交易资金进行跟踪和风险智能判断，确保每笔资金始终处于封闭，可控，可查的状态。"
	        +line+
	          space+"账户安全：用户开通交易账户时，会对客户信息进行鉴权，所有身份信息都将上传至国家公安部门，征信系统核对，严格审查每个注册用户身份安全性，真实性，风险性。 在任何用户认为必要的情况下，可以通过电话联系我们进行：锁定账户，冻结交易，将资金退回原卡。"+
	        line+
	          space+"信息安全：所有信息均采用顶级信息安全技术SSL加密，确保您交易信息和交易资金的安全性，保密性，真实性，完整性。"+
	        line+
	          space+"我们立体多角度保证您的资金安全，信息安全，做好您的钱管家。"+
	        line
	        ,
			
	        space+"赚啦理财是一家由拥有银行，风投，基金，信贷行业20年以上从业经验的专业团队组成的互联网金融企业。我们基于市面上3300余家投资平台近3年的交易数据， 建立大数据动态风控模型，从中筛选出最优质的三百余家平台分散投资。挑选最安全没有风险可能的平台进行投资。专业投标，风险分散，1元标的都可分散至三百家债券，极大降低坏账风险。另外，我们从每笔交易资金中提取风险准备金， 再从自有资金提出同倍数金额作为补充风险准备金，风险金比例是同类平台的4倍！"
	    	        ,
			
	    	space+"赚啦将优选的百款理财产品封装成1款赚啦理财，您只需点击购买，输入银行卡号即可，不用排队，不用挑选，无需学习，一键购买立刻分散。1元既享百份债券，就是这么简单！",
			
	    	space+"赚啦理财的最高收益率可达15%，随市场变化会有1-2个点左右的浮动。但无论如何，收益率均稳定在余额宝的至少3倍以上，请您放心购买！",
			
	    	  space+"赚啦理财与一般市场的理财产品不同，没有到期概念，支持随存随取。"+
	        line+
	          space+"T+0购买：工作日15点前的购买当天就有利息。"+
	        line+
	          space+"T+0赎回：工作日15点前的赎回，当天就处理。"+
	        line+
	          space+"购买和赎回均当天生效，称为双向T+0购买。由于每个银行到账时间无法控制，一般2-3个工作日左右银行卡到账。"+
	        line
	    	,
			
	    	  space+"申购流程有：购买---确认---起息   3个环节。"+
	       line+
	          space+"用户购买赚啦理财后，交易进入确认状态，系统在工作日15点前进行交易确认，工作日15点后开始计算收益（起息）。15点前的购买交易确认和起息均在当天完成，15点后的购买确认和起息在次日完成，遇到周末或者节假日顺延至下个工作日。"+
	       line+
	          space+"T+0赎回：工作日15点前的赎回，当天就处理。"+
	       line+
	    	  space+"您最新的收益均在手机App端实时刷新。"+
	       line+
	         space+"如周5 当天16点的交易，需周1系统确认。"+
	       line+
	         space+"如周1-周5当天15点前购买的交易，当天就确认并计利息，称为T+0购买。"+
	       line+
	         space+"由于系统确认交易会有一定延迟，为了您更快的获得收益，建议您在周1至周5 下午2点前购买，以便更快的获得收益。"+
	       line
	       ,
			
	          space+"将您已经购买赚啦理财的本金和收益提现到银行卡称为赎回。已经确认的交易支持赎回。赎回需经过系统再次确认。一般情况，工作日当天15点前的赎回当日会处理，工作日15点后的赎回下一个工作日处理。由于每个银行到账时间无法控制，一般2-3个工作日左右银行卡到账。"+
	    	line+
	    	  space+"注：在特殊情况下，赚啦会对赎回交易启动排队机制。"+
	    	line
	    	,
			
	    	  space+"已经确认成功的交易，节假日也将获得收益。未确认的交易，将在下个工作日起息。"+
	        line+
	    	  space+"如周5下午2点购买的交易，周末也有收益。"+
	    	line+
	        	space+"若周5下午4点购买的交易，需下周1确认之后获得收益。"+
	    	line+
	    	   space+"系统在周末和法定节假日均可交易赎回，后台需下个工作日确认交易。"+
	    		    	line,
			
	        space+"赚啦理财起投金额1元，运营初期阶段普通交易3000元封顶。建议您购买100元以上的金额，以便每日有足够的收益显示。如若有更高额度的申购请联系客服申购。",
			

	        space+"A：一年按365天计算，每天均可产生收益。每天收益计算公式为：收益 = 本金*年化收益率/365。如购买当天年化收益14.88%，1800元天收益为=1800*14.88%/365=0.73元。",
	        
	        space+"A：仅支持储蓄卡支付付款，合作银行有：中国工商银行、中国农业银行、中国建设银行、中国银行、中国民生银行、平安银行、广东发展银行、中国光大银行、中国邮政储蓄银行等。目前暂不支持信用卡支付。",
	        
	         space+"在绑卡界面输入银行卡卡号，经后台核对您输入的银行卡身份信息和注册身份信息一致，就完成了绑卡。之后输入交易密码即可进行购买交易。"+
	        line+
	           space+"绑卡是唯一的，成功后不可解绑，请使用您常用的一张储蓄卡进行绑卡，所有取现资金都将回到您绑定的银行卡。 如有任何问题可以联系赚啦官方客服电话。"+
	        line
	        ,
	        
	        space+"交易购买过程没有任何手续费。取现（赎回）普通用户每月免1次手续费，第2笔起每笔2元手续费。所有费用均是第三方支付费用，赚啦理财自身不收取任何申购、赎回、资金保管等手续费。",
	     };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help_center);
		StateBarColor.init(this,0xffffffff);
		initView();
//		dialog=Utils.createPorgressDialog(act, null);
//		dialog.show();
//		new HelpLoad().start();
		HelpCenterManager hcm=new HelpCenterManager(act, icb);
		hcm.init("1");
		hcm.start();
	}
	public void initView(){
		//initData();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.help_center));
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		
		listview_help_center=(ListView)findViewById(R.id.listview_help_center);
		//adapter=new HelpCenterAdapter(this,list);
		//listview_help_center.setAdapter(adapter);
		
	}
	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			HelpAndSafety_data hsd=(HelpAndSafety_data) obj;
			ArrayList<HelpAndSafety> list2=hsd.data;
			for(int i=0;i<list2.size();i++)
			{   
				HelpAndSafety hs=list2.get(i);
				HelpInfo hi=new HelpInfo();
				hi.setProblem(hs.title);
				hi.setAnswer(hs.content);
				list.add(hi);
			}
			adapter=new HelpCenterAdapter(act,list,listview_help_center);
			listview_help_center.setAdapter(adapter);
		}
	};
//	private void initData()
//	{
//		adapter=new HelpCenterAdapter(this,list);
//		listview_help_center.setAdapter(adapter);
//	}
	public void initData(){
		for(int i=0;i<problemArray.length;i++){
			HelpInfo hi=new HelpInfo();
			hi.setProblem(problemArray[i]);
			hi.setAnswer(answerArray[i]);
			list.add(hi);
		}
	}
	


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
	//---------------------------------------------------------------------------------------------------------------------------
	private Dialog dialog;
	class HelpLoad extends Thread
	{
		@Override
		public void run()
		{    
		  try
		  {
			Message msg=new Message();
			IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
			if(!isNetworkConnected(act))
	 		{  
	 			   msg.what=HandlerBase.nonet;
	 			   handler.sendMessage(msg);
	 			   return ;
	 		}
			
			HelpAndSafetyParam hp=new HelpAndSafetyParam();
			hp.type="1";
			HelpAndSafety_data has=(HelpAndSafety_data) im.helpandSafety(HelpAndSafety_data.class,null,hp);
			if(!HandlerBase.success.equals(has.result))
    		{   
    			String s="服务器数据错误";
		    	if(has!=null&&has.message!=null)
		    	{
		    		s+=has.message;
		    	}
		    	LogHandler.writeLogFromString(TAG,s);
    			 msg.what=HandlerBase.data_error;
    			 msg.obj=has;
	 			 handler.sendMessage(msg);
	 			 return;
    		}
			
			ArrayList<HelpAndSafety> listh=has.data;
			list=new ArrayList<HelpInfo>();
			HelpInfo hi;
			for(int i=0;i<listh.size();i++)
			{
				hi=new HelpInfo();
				hi.problem=listh.get(i).title;
				hi.answer=listh.get(i).content;
				list.add(hi);
			}
			
			msg.what=HandlerBase.success1;
			handler.sendMessage(msg);
		  }
		  catch(Exception e)
		  {   
			  Message msg=new Message();
			  msg.what=HandlerBase.error;
			  handler.sendMessage(msg);
			  e.printStackTrace();
			  LogHandler.writeLogFromException(TAG,e);
		  }
		}
	}
	Handler handler=new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			if(dialog!=null)
			{
				dialog.dismiss();
			}
			switch(msg.what)
			{
			   case HandlerBase.nonet:
				   MessageManger.showMeg(act,getString(R.string.no_net),Toast.LENGTH_SHORT);
				   break;
			   case HandlerBase.data_error:
				   HelpAndSafety_data has=(HelpAndSafety_data)msg.obj;
				   String s=getString(R.string.service_data_error);
				   if(!"".equals(has.message))
				   {
					   s=has.message;
				   }
				   MessageManger.showMeg(act,s,Toast.LENGTH_SHORT);
				   break;
			   case HandlerBase.success1:
				   initData();
				   break;
			}
		}
		
	};
}
