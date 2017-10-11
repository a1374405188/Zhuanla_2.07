package com.beikbank.android.net.impl;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.DownloadManager.Request;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.Biaoshi;
import com.beikbank.android.data.Biaoshi_data;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.DingqiP_data2;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.HelpInfo;
import com.beikbank.android.data.HelpInfo2;
import com.beikbank.android.data.HelpInfo2_data;
import com.beikbank.android.data.HelpInfo3;
import com.beikbank.android.data.HelpInfo3_data;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data.Hongbao_data;
import com.beikbank.android.data.Huodong;
import com.beikbank.android.data.Huodong_data;
import com.beikbank.android.data.HuoqiReturnR_data;
import com.beikbank.android.data.HuoqiToR_data;
import com.beikbank.android.data.Message2_data;
import com.beikbank.android.data.Message3_data;
import com.beikbank.android.data.Message_data;
import com.beikbank.android.data.PayList_data;
import com.beikbank.android.data.PhoneGet;
import com.beikbank.android.data.PhoneGet_Data;
import com.beikbank.android.data.PhoneQinqiu_Data;
import com.beikbank.android.data.PhoneQueren_Data;
import com.beikbank.android.data.Poundage;
import com.beikbank.android.data.Poundage_data;
import com.beikbank.android.data.Qianbao1_data;
import com.beikbank.android.data.Qianbao7_data;
import com.beikbank.android.data.Qianbao8_data;
import com.beikbank.android.data.QianbaoRecord;
import com.beikbank.android.data.QianbaoRecord_data;
import com.beikbank.android.data.QueRenJiaoYi_data;
import com.beikbank.android.data.Remdom_data;
import com.beikbank.android.data.ReqPayforP_Data;
import com.beikbank.android.data.Share;
import com.beikbank.android.data.Share_data;
import com.beikbank.android.data.ShoushiCre;
import com.beikbank.android.data.ShoushiCre_data;
import com.beikbank.android.data.ShoushiIs;
import com.beikbank.android.data.ShoushiIsSet;
import com.beikbank.android.data.ShoushiIsSet_data;
import com.beikbank.android.data.ShoushiIs_data;
import com.beikbank.android.data.Tuisong_data;
import com.beikbank.android.data.UserRecord3_data;
import com.beikbank.android.data.Win_data;
import com.beikbank.android.data.Yuer_data;
import com.beikbank.android.data2.GetChanPin_data;
import com.beikbank.android.data2.JinXuan;
import com.beikbank.android.data2.Jinxuan_data;
import com.beikbank.android.data2.LunBo_data;
import com.beikbank.android.data2.Register_data;
import com.beikbank.android.data2.UserCheck_data;
import com.beikbank.android.data2.Xiaoxi_data;
import com.beikbank.android.dataparam.BiaoshiParam;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.HeadParam;
import com.beikbank.android.dataparam.HelpCenterParam;
import com.beikbank.android.dataparam.HelpCenterParam2;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.dataparam.HuodongParam;
import com.beikbank.android.dataparam.HuoqiReturnParam;
import com.beikbank.android.dataparam.HuoqiReturnRParam;
import com.beikbank.android.dataparam.HuoqiToParam;
import com.beikbank.android.dataparam.HuoqiToRParam;
import com.beikbank.android.dataparam.MessageParam2;
import com.beikbank.android.dataparam.MessageParam3;
import com.beikbank.android.dataparam.PayListParam;
import com.beikbank.android.dataparam.PhoneGetParam;
import com.beikbank.android.dataparam.PhoneQinqiuParam;
import com.beikbank.android.dataparam.PhoneQuerenParam;
import com.beikbank.android.dataparam.QianbaoParam7;
import com.beikbank.android.dataparam.QianbaoParam8;
import com.beikbank.android.dataparam.QianbaoRecordParam;
import com.beikbank.android.dataparam.QueRengJiaoYiParam;
import com.beikbank.android.dataparam.QueRengJiaoYiParam2;
import com.beikbank.android.dataparam.RegsiterParam;
import com.beikbank.android.dataparam.ReqPayforPParam2;
import com.beikbank.android.dataparam.ShareParam;
import com.beikbank.android.dataparam.ShoushiCreParam;
import com.beikbank.android.dataparam.ShoushiIsParam;
import com.beikbank.android.dataparam.ShoushiIsSetParam;
import com.beikbank.android.dataparam.TuisongParam;
import com.beikbank.android.dataparam.UserParam;
import com.beikbank.android.dataparam.UserRecord3Param;
import com.beikbank.android.dataparam.UserRecord3Param2;
import com.beikbank.android.dataparam.WinParam;
import com.beikbank.android.dataparam.YuerParam;
import com.beikbank.android.dataparam2.GetChanPinParam;
import com.beikbank.android.dataparam2.HeadParam2;
import com.beikbank.android.dataparam2.JinXuanParam;
import com.beikbank.android.dataparam2.LunBoParam;
import com.beikbank.android.dataparam2.RegisterParam;
import com.beikbank.android.dataparam2.UserCheckParam;
import com.beikbank.android.dataparam2.XiaoxiParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HttpUtil;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.RequestUrl;
import com.beikbank.android.security.Jiami;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.google.gson.JsonObject;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-8
 **/
public class IBusinessImpl implements IBusiness{
	private boolean debug=true;
    private String TAG="IBusinessImpl";
    private String base_service=RequestUrl.SERVER;
    private String base_service_443=RequestUrl.SERVER_443;
    Context context;
    public IBusinessImpl(Context context)
    {
    	this.context=context;
    }
    
    
    public Object execute(Object params)throws Exception
    {
    	Object obj=null;

    	if(params instanceof QianbaoParam7)
    	{
    		obj=qianbao7(Qianbao7_data.class,null,params);
    	}
    	else if(params instanceof QianbaoParam8)
    	{   
    		((QianbaoParam8) params).tradePassword=MD5.md5s32(((QianbaoParam8) params).tradePassword);
    		obj=qianbao8(Qianbao1_data.class,null,params);
    	}else if(params instanceof YuerParam)
    	{
    		obj=yuer(Yuer_data.class,null,params);
    	}
    	else if(params instanceof PayListParam)
    	{
    		obj=payList(PayList_data.class,null,params);
    	}
    	else if(params instanceof HuoqiToRParam)
    	{
    		obj=huoqiToR(HuoqiToR_data.class,null,params);
    	}
    	else if(params instanceof HuoqiToParam)
    	{   
    		((HuoqiToParam) params).tradePassword=MD5.md5s32(((HuoqiToParam) params).tradePassword);
    		obj=huoqiTo(Remdom_data.class,null,params);
    	}
    	else if(params instanceof HuoqiReturnRParam)
    	{
    		obj=huoqiReturnR(HuoqiReturnR_data.class,null,params);
    	}
    	else if(params instanceof HuoqiReturnParam)
    	{   
    		((HuoqiReturnParam) params).tradePassword=MD5.md5s32(((HuoqiReturnParam) params).tradePassword);
    		obj=huoqiReturn(Remdom_data.class,null,params);
    	}
    	else if(params instanceof QianbaoRecordParam)
    	{   
    		
    		obj=qianbaoRecord(QianbaoRecord_data.class,null,params);
    	}
    	else if(params instanceof TuisongParam)
    	{
    		obj=tuiSong(Tuisong_data.class,null,params);
    	}
    	else if(params instanceof UserParam)
    	{
    		obj=getDingqiPByUser(DingqiP_data2.class,null,params);
    	}
    	else if(params instanceof HongbaoParam)
    	{
    		obj=getHongbao(Hongbao_data.class,null,params);
    	}
    	else if(params instanceof ReqPayforPParam2)
    	{
    		obj=reqPayforPv2(ReqPayforP_Data.class,null,params);
    	}
    	else if(params instanceof Share)
    	{
    		obj=getShare(Share_data.class,null,null);
    	}
    	else if(params instanceof ShareParam)
    	{
    		obj=getShare2(Share_data.class,null,null);
    	}
    	else if(params instanceof UserRecord3Param2)
    	{
    		obj=getDingQiShouYi(UserRecord3_data.class,null,params);
    	}
    	else if(params instanceof WinParam)
    	{
    		obj=getWin(Win_data.class,null,null);
    	}
    	else if(params instanceof HuodongParam)
    	{
    		obj=getHuodong(Huodong_data.class,null,params);
    	}
    	else if(params instanceof MessageParam2)
    	{
    		obj=getMessage2(Message_data.class,null,params);
    	}
    	else if(params instanceof MessageParam3)
    	{
    		obj=readMessage3(Message3_data.class,null,params);
    	}
    	else if(params instanceof BiaoshiParam)
    	{
    		obj=getBiaoshi(Biaoshi_data.class,null,params);
    	}
    	else if(params instanceof CheckBankParam)
    	{
    		obj=checkBank(CheckBank_data.class,null, params);
    	}
    	else if(params instanceof QueRengJiaoYiParam)
    	{
    		obj=queRenJiaoyi(QueRenJiaoYi_data.class,null, params);
    	}
    	else if(params instanceof QueRengJiaoYiParam2)
    	{
    		obj=queRenJiaoyi2(Qianbao1_data.class,null, params);
    	}
    	else if(params instanceof XiaoxiParam)
    	{
    		obj=getXiaoxi(Xiaoxi_data.class,null,params);
    	}
    	else if(params instanceof LunBoParam)
    	{
    		obj=getLunBo(LunBo_data.class,null,params);
    	}
    	else if(params instanceof RegisterParam)
    	{
    		obj=register2(Register_data.class,null,params);
    	}
    	else if(params instanceof UserCheckParam)
    	{
    		obj=UserCheck(UserCheck_data.class,null,params);
    	}
    	else if(params instanceof GetChanPinParam)
    	{
    		obj=getChanPin(GetChanPin_data.class,null, params);
    	}
    	else if(params instanceof JinXuanParam)
    	{
    		obj=getTuiJian(Jinxuan_data.class,null,params);
    	}
    	else if(params instanceof HelpCenterParam)
    	{
    		obj=help1(HelpInfo2_data.class,null,null);
    	}
    	else if(params instanceof HelpCenterParam2)
    	{
    		obj=help2(HelpInfo3_data.class,null,params);
    	}
    	else if(params instanceof PhoneGetParam)
    	{
    		obj=phoneGet(PhoneGet_Data.class,null,params);
    	}
    	else if(params instanceof PhoneQinqiuParam)
    	{
    		obj=phoneQinqiu(PhoneQinqiu_Data.class,null,params);
    	}
    	else if(params instanceof PhoneQuerenParam)
    	{
    		obj=phoneQueren(PhoneQueren_Data.class,null,params);
    	}
    	else if(params instanceof ShoushiIsParam)
    	{
    		obj=shoushiIs(ShoushiIs_data.class,null,params);
    	}
    	else if(params instanceof ShoushiIsSetParam)
    	{
    		obj=shoushiIsSet(ShoushiIsSet_data.class,null,params);
    	}
    	else if(params instanceof ShoushiCreParam)
    	{
    		obj=shoushiCre(ShoushiCre_data.class,null,params);
    	}
    	return obj;
    }
    
    
    /**
     * 创建修改手势密码
     *@return
     */
     public Object shoushiCre(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.shoushi_cre;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    
    /**
     * 是否设置手势密码
     *@return
     */
     public Object shoushiIsSet(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.shoushi_isset;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    
    /**
     * 手势密码是否正确
     *@return
     */
     public Object shoushiIs(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.shoushi_is;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    
    
    
    
    
    /**
     * 查询预留手机号
     *@return
     */
     public Object phoneGet(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.yuliu_phone_get;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
     /**
      * 请求修改预留手机号
      *@return
      */
      public Object phoneQinqiu(Class<?> returnClass, String url, Object paramClass)throws Exception
      {
   	  
   	     Object obj=null;
   		 TreeMap<String,String> map=null;
   		 if(url==null)
   		 {
   			 url=RequestUrl.SERVER+RequestUrl.yuliu_phone_qinqiu;
   		 }
   		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getPostData(returnClass, url, map);
   	   return obj;
      }
      /**
       * 确认修改预留手机号
       *@return
       */
       public Object phoneQueren(Class<?> returnClass, String url, Object paramClass)throws Exception
       {
    	  
    	     Object obj=null;
    		 TreeMap<String,String> map=null;
    		 if(url==null)
    		 {
    			 url=RequestUrl.SERVER+RequestUrl.yuliu_phone_queren;
    		 }
    		 RequestParamManager rpm=new RequestParamManager();
    	     map=rpm.getMapString(paramClass);
    	     obj=getPostData(returnClass, url, map);
    	   return obj;
       }
    
    /**
     * 帮助中心一级界面请求
     *@return
     */
     public Object help1(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.help1;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
     /**
      * 帮助中心二级界面请求
      *@return
      */
      public Object help2(Class<?> returnClass, String url, Object paramClass)throws Exception
      {
   	  
   	     Object obj=null;
   		 TreeMap<String,String> map=null;
   		 if(url==null)
   		 {
   			 url=RequestUrl.SERVER+RequestUrl.help2;
   		 }
   		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getGetData(returnClass, url, map);
   	   return obj;
      }
    /**
     * 精选推荐
     *@return
     */
     public Object getTuiJian(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=SystemConfig.kHOST_URL2+RequestUrl.tongyong;
  		 }
  		// RequestParamManager rpm=new RequestParamManager();
  		
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam hp=new HeadParam();
  	     
  	     hp.tra_code="050021";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.rep_seq="1";
  	     hp.gps="";
  	     String json=Jiami.getValue(hp,paramClass);
  	     obj=getJsonData(returnClass, url, json);
  	   return obj;
     }
     
     
     /**
      * 根据id得到产品
      *@return
      */
      public Object getChanPin(Class<?> returnClass, String url, Object paramClass)throws Exception
      {
   	  
   	     Object obj=null;
   		 TreeMap<String,String> map=new TreeMap<String, String>();
   		 if(url==null)
   		 {
   			 //url=SystemConfig.kHOST_URL2+RequestUrl.tongyong;
   			 url="http://114.55.5.66:10025/productAPI/product/productAPI.do?report=";
   		 }
   		// RequestParamManager rpm=new RequestParamManager();
   		
   	     //map=rpm.getMapString(paramClass);
   		 HeadParam2 hp=new HeadParam2();
   	     
   	     hp.tra_code="030110";
   	     //hp.rep_seq=Jiami.getLiushui();
   	     Date d=new Date();
   	    
   	     hp.request_seq="99999999";
   	   
   	    String json=Jiami.getValue(hp,paramClass);
   	    url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	   String ss=  hu.getRequest(url);
	   
	     System.out.println(123);
   	   return obj;
      }
      
      
      /**
       * 判断用户是否注册
       *@return
       */
       public Object UserCheck(Class<?> returnClass, String url, Object paramClass)throws Exception
       {
    	  
    	     Object obj=null;
    		 TreeMap<String,String> map=new TreeMap<String, String>();
    		 if(url==null)
    		 {
    			 //url=SystemConfig.kHOST_URL2+RequestUrl.tongyong;
    			 url="http://114.55.5.66:10028/user/userApi";
    		 }
    		// RequestParamManager rpm=new RequestParamManager();
    		
    	     //map=rpm.getMapString(paramClass);
    		 HeadParam2 hp=new HeadParam2();
    	     
    	     hp.tra_code="070051";
    	     //hp.rep_seq=Jiami.getLiushui();
    	     hp.request_seq="10001";
    	   
    	    String json=Jiami.getValue(hp,paramClass);
    	    url=url+"?"+URLEncoder.encode(json);
 	     HttpUtil hu=new HttpUtil(context);
 	   String ss=  hu.getRequest(url);
 	   
 	     System.out.println(123);
    	   return obj;
       }
     /**
      * 得到轮播
      *@return
      */
      public Object register2(Class<?> returnClass, String url, Object paramClass)throws Exception
      {
   	  
   	     Object obj=null;
   		 TreeMap<String,String> map=new TreeMap<String, String>();
   		 if(url==null)
   		 {
   			 //url=SystemConfig.kHOST_URL2+RequestUrl.tongyong;
   			 url="http://172.16.200.111:8080/user/userApi";
   		 }
   		// RequestParamManager rpm=new RequestParamManager();
   		
   	     //map=rpm.getMapString(paramClass);
   		 HeadParam2 hp=new HeadParam2();
   	     
   	     hp.tra_code="070054";
   	     //hp.rep_seq=Jiami.getLiushui();
   	     hp.request_seq="10001";
   	   
   	    String json=Jiami.getValue(hp,paramClass);
   	    url=url+"?"+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	   String ss=  hu.getRequest(url);
	   
	     System.out.println(123);
   	   return obj;
      }
    /**
     * 得到轮播
     *@return
     */
     public Object getLunBo(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=SystemConfig.kHOST_URL2+RequestUrl.tongyong;
  		 }
  		// RequestParamManager rpm=new RequestParamManager();
  		
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam hp=new HeadParam();
  	     
  	     hp.tra_code="050011";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.rep_seq="1";
  	     hp.gps="";
  	     String json=Jiami.getValue(hp,paramClass);
  	     obj=getJsonData(returnClass, url, json);
  	    
  	   return obj;
     }
    /**
     * 得到消息
     *@return
     */
     public Object getXiaoxi(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.tongyong;
  		 }
  		 url="http://172.16.100.139:8081/beikbankMessage/appapi";
  		// RequestParamManager rpm=new RequestParamManager();
  		 
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam hp=new HeadParam();
  	     
  	     hp.tra_code="040013";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.rep_seq="1";
  	     hp.gps="";
  	     String json=Jiami.getValue(hp,paramClass);
  	     obj=getJsonData(returnClass, url, json);
  	   return obj;
     }
     /**
 	 * 发送json格式的参数请求
 	 * @param returnClass
 	 * @param url
 	 * @param json
 	 * @return
 	 * @throws Exception
 	 */
     public Object getJsonData(Class<?> returnClass, String url,String json)
 			throws Exception
     {
     	HttpUtil hu=new HttpUtil(context);
     	
     	
     	
     	String responce=hu.doPost(url,json);
     	//检验签名
     	//Jiami.checkSign(responce);
     	
     	if(SystemConfig.isDebug())
     	{
     		Log.d("json",responce);
     	}
     	ResponseParamManager rpm=new ResponseParamManager();
 		Object obj=null;
 		
 		obj=rpm.getObjectFromJson(returnClass,responce);
 		
 		return obj;
     }
    /**
     * 富有短线充值验证交易确认
     *@return
     */
     public Object queRenJiaoyi2(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER_443+RequestUrl.queRenJiaoyi2;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 富有短线验证交易确认
     *@return
     */
     public Object queRenJiaoyi(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER_443+RequestUrl.queRenJiaoyi;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 检查是否需要绑定银行卡
     *@return
     */
     public Object checkBank(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER_443+RequestUrl.checkBank;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 得到标识
     *@return
     */
     public Object getBiaoshi(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.getBiaoshi;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 标记公告(即通知)已读
     *@return
     */
     public Object readMessage3(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.readMessage3;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 得到消息v2
     *@return
     */
     public Object getMessage2(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.getMessage2;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    
    /**
     * 得到活动
     *@return
     */
     public Object getHuodong(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.getHuodong;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 得到弹出窗口
     *@return
     */
     public Object getWin(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.getWin;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 得到定期收益记录
     *@return
     */
     public Object getDingQiShouYi(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.getDingQiShouYi;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 得到红包
     *@return
     */
     public Object getHongbao(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.getHongbao;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
     /**
      * 得到邀请分享
      *@return
      */
      public Object getShare2(Class<?> returnClass, String url, Object paramClass)throws Exception
      {
   	  
   	     Object obj=null;
   		 TreeMap<String,String> map=null;
   		 if(url==null)
   		 {
   			 url=RequestUrl.SERVER+RequestUrl.getShare2;
   		 }
   		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getGetData(returnClass, url, map);
   	   return obj;
      }
    /**
     * 得到分享
     *@return
     */
     public Object getShare(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.getShare;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    /**
     *第一次打开推送
     *@return
     */
     public Object tuiSong(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.tuisong;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
  	     return obj;
     }
    /**
     *钱包交易记录
     *@return
     */
     public Object qianbaoRecord(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER_443+RequestUrl.qianbaoRecord;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 活期赎回确认
     *@return
     */
     public Object huoqiReturn(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER_443+RequestUrl.huoqiReturn;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 活期赎回请求
     *@return
     */
     public Object huoqiReturnR(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER_443+RequestUrl.huoqiReturnR;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 活期转定期确认
     *@return
     */
     public Object huoqiTo(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER_443+RequestUrl.huoqiTo;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 活期转定期请求
     *@return
     */
     public Object huoqiToR(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER_443+RequestUrl.huoqiToR;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
  	   return obj;
     }
    /**
     * 得到余额
     *@return
     */
     public Object yuer(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.yuer;
  		 }
  		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
  	   return obj;
     }
   /**
   * 得到购买人数
   *@return
   */
   public Object payList(Class<?> returnClass, String url, Object paramClass)throws Exception
   {
	  
	   Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.payList;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	   return obj;
   }
    /**
     * 得到活期，定期，充值金额，及手续费
     * @param returnClass
     * @param url
     * @param paramClass
     * @return
     * @throws Exception
     */
	public Object qianbao7(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.qianbao7;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
	     return obj;
	}
	 /**
     * 余额提现确认
     * @param returnClass
     * @param url
     * @param paramClass
     * @return
     * @throws Exception
     */
	public Object qianbao8(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.qianbao8;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
	     return obj;
	}

//	@Override
//	public Object getUserPoundage(String...strings) throws Exception {
//		Poundage poundage;
//		HttpUtil hu=new HttpUtil();
//		TreeMap<String,String> map=new TreeMap<String, String>();
//		map.put("memberID",strings[0]);
//		
//		TreeMap tm=new TreeMap();
//		tm.put("memberID",strings[0]);
//		map.put("sign",Utils.getSign(tm));
//		
//		String url=RequestUrl.SERVER+RequestUrl.GET_USER_POUNDAGE;
//		url=addMapToUrl(url, map);
//		if(debug)
//		{
//			Log.d(TAG,url);
//		}
//	    String responce=hu.getRequest(url);
//	    ResponseParamManager rpm=new ResponseParamManager();
//	    Object obj= rpm.getObjectFromJson(Poundage_data.class,responce);
//		return obj;
//	}
    private String addMapToUrl(String url,TreeMap<String,String> map)
    {   
    	if(map==null)
    	{
    		return url;
    	}
    	int a=1;
    	for(String key : map.keySet())
		{   
    		if(a==1)
    		{
    			url+="?"+key+"="+map.get(key);
    			a=-1;
    		}
    		else
    		{
    			url+="&"+key+"="+map.get(key);
    		}
		}
    	return url;
    }
  
	@Override
	public Object getProductFundList(Class<?> returnClass,String url,Object paramClass) throws Exception {
		TreeMap<String,String> map=null;
		if(url==null)
		{
			 url=base_service+RequestUrl.GETPRODUCTFUND;
		}
		 if(paramClass==null)
		 {
			 map=new TreeMap();
		 }
		 else if(!(paramClass instanceof TreeMap))
	      {   
	    	  RequestParamManager rpm=new RequestParamManager();
	    	  map=rpm.getMapString(paramClass);
	      }	
		Object obj= getGetData(returnClass, url, map);
		return obj;
	}

    @Override
	public Object getGetData(Class<?> returnClass, String url,TreeMap<String,String> map)
			throws Exception {
		HttpUtil hu=new HttpUtil(context);
        if(map==null)
        {
        	map=new TreeMap<String, String>();
        }
		map.put("sign",Utils.getSign(map));
		if(url==null)
		{
			 url=base_service+RequestUrl.GETPRODUCTFUND;
		}
 
		
		url=addMapToUrl(url, map);
		//dodebug(url);
		String responce=hu.getRequest(url);
		if(SystemConfig.isDebug())
		{
			Log.d("json",returnClass.getName()+":"+responce);
			LogHandler.writeLogFromString("json",responce);
		}
		if(responce.length()==6&&NumberManager.isNumeric(responce))
		{
			ErrorMessage rcr=new ErrorMessage();
			rcr.message=responce;
			return rcr;
		}
		ResponseParamManager rpm=new ResponseParamManager();
		Object obj=null;
		try
		{
		  obj=rpm.getObjectFromJson(returnClass,responce);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LogHandler.writeLogFromException(TAG, e);
			//obj=rpm.getObjectError(returnClass,responce);
			throw new Exception(responce+e.toString());
		}
		return obj;
	}
    @Override
	public Object getPostData(Class<?> returnClass, String url,TreeMap<String,String> map)
			throws Exception {
	   
		//dodebug(url);
		if(map==null)
		{
			map=new TreeMap<String, String>();
		}
		HttpUtil hu=new HttpUtil(context);
		map.put("sign",Utils.getSign(map));
		String responce=hu.postRequest(url,map);
		if(SystemConfig.isDebug())
		{
			Log.d("json",returnClass.getName()+":"+responce);
		}
		if(responce.length()==6&&NumberManager.isNumeric(responce))
		{
			ErrorMessage rcr=new ErrorMessage();
			rcr.message=responce;
			return rcr;
		}
		ResponseParamManager rpm=new ResponseParamManager();
		Object obj=null;
		try
		{
	      obj=rpm.getObjectFromJson(returnClass, responce);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LogHandler.writeLogFromException(TAG, e);
			//obj=rpm.getObjectError(returnClass,responce);
			throw new Exception(responce+e.toString());
		}
		return obj;
	}
	@Override
	public Object redeemMoney(Class<?> returnClass, String url,
			Object paramClass) throws Exception {	
		  Object obj=null;
		  TreeMap<String,String> map=null;
	      if(url==null)
	      {
	    	url=RequestUrl.SERVER_443+RequestUrl.RETURNMONEY;
	      }
	      
	      if(!(paramClass instanceof TreeMap))
	      {   
	    	  RequestParamManager rpm=new RequestParamManager();
	    	  map=rpm.getMapString(paramClass);
	      }	 
	      obj=getPostData(returnClass, url, map);
	      return  obj;
	}
	
	private void dodebug(String url)
	{
		if(SystemConfig.isDebug())
		{   
			Log.e(TAG,url);
			LogHandler.writeLogFromString(TAG,url);
		}
	}
	@Override
	public Object register(Class<?> returnClass, String url, Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.CHECKPASSWORD;
		 }
		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getPostData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object login(Class<?> returnClass, String url, Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.CHECKLOGIN;
		 }
		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getPostData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object checkPhoneNumber(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.CHECKPHONEINFO;
		 }
		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getPostData(returnClass, url, map);
		return obj;
	}
	
	@Override
	public Object bindBankCard(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.CHECKBINDBANK;
		 }
		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object authenticateRealName(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.realname;
		 }
		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object helpandSafety(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.get_helpandsafety;
			// url="http://172.16.100.25:80/beikbankapp/appapi/"+RequestUrl.get_helpandsafety;
		 }
		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getGetData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object checkUpdate(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.CHECKUPDATE;
		 }
		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getGetData(returnClass, url, map);
   	  return obj;
	}
	@Override
	public Object oneKey(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.one_key;
			// url="http://172.16.100.25:80/beikbankapp/appapi/"+RequestUrl.one_key;
		 }
		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getPostData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object getBankList(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.BANKLIST;
		 }
		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getGetData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object getTotalMoney(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.TOTALCAPITAL;
		 }
		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getGetData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object getMoneyInfo(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.INCOME;
		 }
		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getGetData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object checkLoginPasswd(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.CHECKUSERPASSWORD;
		 }
		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getPostData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object updateLoginPasswd(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.UPDATEPASSWORD;
		 }
		 RequestParamManager rpm=new RequestParamManager();
   	     map=rpm.getMapString(paramClass);
   	     obj=getPostData(returnClass, url, map);
		return obj;
	}
	@Override
	public Object isName(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.CHECKAUTH;
		 }
		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
		 return obj;
	}
	@Override
	public Object SendCode(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.GETIDENTIFYCODE;
		 }
		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
		 return obj;
	}
	@Override
	public Object CheckCode(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.CHECKVERTIFY;
		 }
		 RequestParamManager rpm=new RequestParamManager();
  	     map=rpm.getMapString(paramClass);
  	     obj=getPostData(returnClass, url, map);
		 return obj;
	}
	@Override
	public Object CheckCode2(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.CHECKCODE;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getPostData(returnClass, url, map);
		 return obj;
	}
	@Override
	public Object setTPasswd(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.SETTRANSACTIONPWD;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
		 return obj;
	}
	@Override
	public Object updateTPasswd(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.UPDATETRANSACTIONPWD;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
		 return obj;
	}
	@Override
	public Object playFund(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.PAYFORPRODUCT;
			// url="https://172.16.100.29:8443/beikbankapp/appapi/"+RequestUrl.PAYFORPRODUCT;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
		 return obj;
	}
	@Override
	public Object projectList(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.PROJECTLIST;
			 //url="http://172.16.100.25:80/beikbankapp/appapi/"+RequestUrl.PAYFORPRODUCT;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
		 return obj;
	}
	@Override
	public Object TranList(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=base_service_443+RequestUrl.TRANSACTIONLIST;
			 //url="http://172.16.100.25:80/beikbankapp/appapi/"+RequestUrl.PAYFORPRODUCT;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object getUserPoundage(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		Object obj=null;
		TreeMap<String,String> map=null;
		if(url==null)
		{
			 url=base_service+RequestUrl.GET_USER_POUNDAGE;
		}
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
		return obj;
	}

	@Override
	public Object OneKey(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		Object obj=null;
		TreeMap<String,String> map=null;
		if(url==null)
		{
			 url=base_service+RequestUrl.FEEDBACK;
		}
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
		return obj;
	}

	@Override
	public Object IsChekBnak(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		Object obj=null;
		TreeMap<String,String> map=null;
		if(url==null)
		{
			 url=base_service_443+RequestUrl.ischeckbank;
		}
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
		 return obj;
	}

	@Override
	public Object CheckAndSend(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		Object obj=null;
		TreeMap<String,String> map=null;
		if(url==null)
		{
			 url=base_service+RequestUrl.chekandsend;
			 //url=base_service_443+RequestUrl.chekandsend;
		}
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
		return obj;
	}

	@Override
	public Object getNotice(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		Object obj=null;
		TreeMap<String,String> map=null;
		if(url==null)
		{
			 url=base_service+RequestUrl.notice;
		}
		// RequestParamManager rpm=new RequestParamManager();
	    // map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
		 return obj;
	}

	@Override
	public Object CheckCode3(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.CHECKPHONEVETIFY;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getPostData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object getFundLimit(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		Object obj=null;
		TreeMap<String,String> map=null;
		if(url==null)
		{
			 url=base_service+RequestUrl.getFundLimit;
		}
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
		 return obj;
	}

	@Override
	public Object getMessage(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getMessage;
			 //url="http://172.16.100.15:80/beikbankapp/appapi/"+RequestUrl.getMessage;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getGetData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object readMessage(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.readMessage;
			 //url="http://172.16.100.15:80/beikbankapp/appapi/"+RequestUrl.readMessage;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getPostData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object delMessage(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.delMessage;
			 //url="http://172.16.100.15:80/beikbankapp/appapi/"+RequestUrl.delMessage;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getPostData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object getActivity(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getAction;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getGetData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object getActionList(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getActionList;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getGetData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object getActionInfo(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getActionInfo;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getGetData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object getSysNotice(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getSysNotice;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getGetData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object getUserRecord(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getUserRecord;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getGetData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object getUserCapital(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getUserCapital;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object getCommonInfo(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getCommonInfo;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object getDingqiPI(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getDingqiPI;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}
	/**
	 * 通过用户id得到定期产品列表
	 * @param returnClass
	 * @param url
	 * @param paramClass
	 * @return
	 * @throws Exception
	 */
	public Object getDingqiPByUser(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getDingqiPByUser;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object getDingqiP(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getDingqiP;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}
   
	/**
	 * 请求交易2
	 * @param returnClass
	 * @param url
	 * @param paramClass
	 * @return
	 * @throws Exception
	 */
	public Object reqPayforPv2(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.reqPayforPv2;
			 //url="http://172.16.100.15:80/beikbankapp/appapi/"+RequestUrl.delMessage;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getPostData(returnClass, url, map);
 	     return obj;
	}
	@Override
	public Object reqPayforP(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.reqPayforP;
			 //url="http://172.16.100.15:80/beikbankapp/appapi/"+RequestUrl.delMessage;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getPostData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object confirmPayforP(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.confirmPayforP;
			 //url="http://172.16.100.15:80/beikbankapp/appapi/"+RequestUrl.delMessage;
		 }
		 RequestParamManager rpm=new RequestParamManager();
 	     map=rpm.getMapString(paramClass);
 	     obj=getPostData(returnClass, url, map);
 	     return obj;
	}

	@Override
	public Object getUserCapitalInfo(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getUserCapitalInfo;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object getUserCapital2(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getUserCapital2;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object checkBankAndPhone(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.checkBankAndPhone;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object checkBankAndPhoneR(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.checkBankAndPhoneR;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object getDingqiProject(Class<?> returnClass, String url,
			Object paramClass) throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getProductFundBondList;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object getConfigQQ(Class<?> returnClass, String url,
			Object paramClass) throws Exception 
	{
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.getConfigQQ;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object qianbao1(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.qianbao1;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object qianbao2(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.qianbao2;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object qianbao3(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.qianbao3;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object qianbao4(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.qianbao4;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getPostData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object qianbao5(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER_443+RequestUrl.qianbao5;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}

	@Override
	public Object qianbao6(Class<?> returnClass, String url, Object paramClass)
			throws Exception {
		 Object obj=null;
		 TreeMap<String,String> map=null;
		 if(url==null)
		 {
			 url=RequestUrl.SERVER+RequestUrl.qianbao6;
		 }
		 RequestParamManager rpm=new RequestParamManager();
	     map=rpm.getMapString(paramClass);
	     obj=getGetData(returnClass, url, map);
	     return obj;
	}
}
