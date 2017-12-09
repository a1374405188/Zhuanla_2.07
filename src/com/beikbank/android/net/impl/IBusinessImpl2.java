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

import com.beikbank.android.activity.HuoqiZaiquanActivity;
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
import com.beikbank.android.data2.BindBankQinQiu_data;
import com.beikbank.android.data2.BindBankQueReng_data;
import com.beikbank.android.data2.CheckJiaoYiMiMa_data;
import com.beikbank.android.data2.CheckShouShi;
import com.beikbank.android.data2.CheckShouShi_data;
import com.beikbank.android.data2.ShouShi_data;
import com.beikbank.android.data2.ChongZhiQueReng_data;
import com.beikbank.android.data2.ChongZhi_data;
import com.beikbank.android.data2.CreateDingDan_data;
import com.beikbank.android.data2.CreateShouShi_data;
import com.beikbank.android.data2.GetChanPin_data;
import com.beikbank.android.data2.GetUserZhiChan2_data;
import com.beikbank.android.data2.GetUserZhiChan_data;
import com.beikbank.android.data2.GongGao_data;
import com.beikbank.android.data2.Goumai_data;
import com.beikbank.android.data2.HuTouOpenOrClose_data;
import com.beikbank.android.data2.HuoQiShuHui_data;
import com.beikbank.android.data2.HuoQiShuHui_data2;
import com.beikbank.android.data2.HuoQiYiGou_data;
import com.beikbank.android.data2.JinXuan;
import com.beikbank.android.data2.Jinxuan_data;
import com.beikbank.android.data2.LoginQian_data;
import com.beikbank.android.data2.Login_data;
import com.beikbank.android.data2.LunBo_data;
import com.beikbank.android.data2.MiMaOrDuanXin_data;
import com.beikbank.android.data2.NameRengzhen_data;
import com.beikbank.android.data2.Register_data;
import com.beikbank.android.data2.ShuJuQianYi1_data;
import com.beikbank.android.data2.ShuJuQianYi2;
import com.beikbank.android.data2.ShuJuQianYi2_data;
import com.beikbank.android.data2.TanChuan_data;
import com.beikbank.android.data2.TiXianQingQiu_data;
import com.beikbank.android.data2.TiXianQueReng_data;
import com.beikbank.android.data2.UserCheck2_data;
import com.beikbank.android.data2.UserCheck3_data;
import com.beikbank.android.data2.UserCheck_data;
import com.beikbank.android.data2.XiaoXiGet_data;
import com.beikbank.android.data2.XiaoXiIs_data;
import com.beikbank.android.data2.XiaoXiRead_data;
import com.beikbank.android.data2.XiaoxiLieBiao;
import com.beikbank.android.data2.Xiaoxi_data;
import com.beikbank.android.data2.checkYanZhenMa_data;
import com.beikbank.android.data2.getAllBank_data;
import com.beikbank.android.data2.getAllYouHuiQuan;
import com.beikbank.android.data2.getAllYouHuiQuan_data;
import com.beikbank.android.data2.getChanPinXiangQin_data;
import com.beikbank.android.data2.getDingDanXiangQin_data;
import com.beikbank.android.data2.getDingQiXiangQin_data;
import com.beikbank.android.data2.getDingQiXiangQing_data;
import com.beikbank.android.data2.getHuoQiXiangQin2_data;
import com.beikbank.android.data2.getHuoQiXiangQin_data;
import com.beikbank.android.data2.getJiaoYiJiLv_data;
import com.beikbank.android.data2.getLeiJiShouYi_data;
import com.beikbank.android.data2.getMeiRiShouYi_data;
import com.beikbank.android.data2.getQianBao_data;
import com.beikbank.android.data2.getQvKuai_data;
import com.beikbank.android.data2.getShare_data;
import com.beikbank.android.data2.getShouZhiMinXi_data;
import com.beikbank.android.data2.getTiXianQianZhi_data;
import com.beikbank.android.data2.getUserOrXiuGai_data;
import com.beikbank.android.data2.getXiangMuXinXi_data;
import com.beikbank.android.data2.getYanZhenMa_data;
import com.beikbank.android.data2.getYiGou_data;
import com.beikbank.android.data2.getYouHuiQuan_data;
import com.beikbank.android.data2.getZhiChan_data;
import com.beikbank.android.data2.getZuoRiShouYi_data;
import com.beikbank.android.data2.phoneChangeChenck_data;
import com.beikbank.android.data2.phoneChange_data;
import com.beikbank.android.data2.setJiaoyiPasswd_data;
import com.beikbank.android.data2.setLoginPasswd_data;
import com.beikbank.android.data2.getYuLiuPhone_data;
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
import com.beikbank.android.dataparam.getHuoQiXiangQinParam2;
import com.beikbank.android.dataparam2.BindBankQinQiuParam;
import com.beikbank.android.dataparam2.BindBankQueQengParam;
import com.beikbank.android.dataparam2.CheckJiaoYiMiMaParam;
import com.beikbank.android.dataparam2.CheckShouShiParam;
import com.beikbank.android.dataparam2.ChongZhiParam;
import com.beikbank.android.dataparam2.ChongZhiQueRengParam;
import com.beikbank.android.dataparam2.CreateDingDanParam;
import com.beikbank.android.dataparam2.ShouShiParam;
import com.beikbank.android.dataparam2.GetCanPingLieBiaoParam;
import com.beikbank.android.dataparam2.GetChanPinParam;
import com.beikbank.android.dataparam2.GouMaiParam;
import com.beikbank.android.dataparam2.HeadParam2;
import com.beikbank.android.dataparam2.HuTouOpenOrCloseParam;
import com.beikbank.android.dataparam2.HuoQiShuHuiParam;
import com.beikbank.android.dataparam2.HuoQiShuHuiParam2;
import com.beikbank.android.dataparam2.HuoQiYiGouParam;
import com.beikbank.android.dataparam2.JiaoYiJiLuParam;
import com.beikbank.android.dataparam2.JinXuanParam;
import com.beikbank.android.dataparam2.LoginParam;
import com.beikbank.android.dataparam2.LoginQianParam;
import com.beikbank.android.dataparam2.LunBoParam;
import com.beikbank.android.dataparam2.MiMaOrDuanXinParam;
import com.beikbank.android.dataparam2.NameRengzhenParam;
import com.beikbank.android.dataparam2.RegisterParam;
import com.beikbank.android.dataparam2.ShuJuQianYi1Param;
import com.beikbank.android.dataparam2.ShuJuQianYi2Param;
import com.beikbank.android.dataparam2.TiXianQingQiuParam;
import com.beikbank.android.dataparam2.TiXianQueRengParam;
import com.beikbank.android.dataparam2.UserCheckParam;
import com.beikbank.android.dataparam2.UserCheckParam2;
import com.beikbank.android.dataparam2.UserCheckParam3;
import com.beikbank.android.dataparam2.XiaoXiGetParam;
import com.beikbank.android.dataparam2.XiaoXiIsParam;
import com.beikbank.android.dataparam2.XiaoXiReadParam;
import com.beikbank.android.dataparam2.XiaoxiLieBiaoParam;
import com.beikbank.android.dataparam2.XiaoxiParam;
import com.beikbank.android.dataparam2.checkYanZhenMaParam;
import com.beikbank.android.dataparam2.getAllBankParam;
import com.beikbank.android.dataparam2.getAllYouhuiQuanParam;
import com.beikbank.android.dataparam2.getChanPinXiangQinParam;
import com.beikbank.android.dataparam2.getDingDanXiangQinParam;
import com.beikbank.android.dataparam2.getDingQiXiangQinParam;
import com.beikbank.android.dataparam2.getDingQiXiangQingParam;
import com.beikbank.android.dataparam2.getGongGaoParam;
import com.beikbank.android.dataparam2.getHuoQiXiangQinParam;
import com.beikbank.android.dataparam2.getJiaoYiJiLvParam;
import com.beikbank.android.dataparam2.getLeiJiShouYiParam;
import com.beikbank.android.dataparam2.getMeiRiShouYiParam;
import com.beikbank.android.dataparam2.getQianBaoParam;
import com.beikbank.android.dataparam2.getQvKuaiParam;
import com.beikbank.android.dataparam2.getShareParam;
import com.beikbank.android.dataparam2.getShouZhiMinXiParam;
import com.beikbank.android.dataparam2.getTanChuanParam;
import com.beikbank.android.dataparam2.getTiXianQianZhiParam;
import com.beikbank.android.dataparam2.getUserOrXiuGaiParam;
import com.beikbank.android.dataparam2.getUserZhiChanParam;
import com.beikbank.android.dataparam2.getUserZhiChanParam2;
import com.beikbank.android.dataparam2.getXiangMuXinXiParam;
import com.beikbank.android.dataparam2.getYanZhenMaParam;
import com.beikbank.android.dataparam2.getYiGouParam;
import com.beikbank.android.dataparam2.getYouhuiQuanParam;
import com.beikbank.android.dataparam2.getZaiQuanParam;
import com.beikbank.android.dataparam2.getZhiChanParam;
import com.beikbank.android.dataparam2.getZuoRiShouYiParam;
import com.beikbank.android.dataparam2.phoneChangeCheckParam;
import com.beikbank.android.dataparam2.phoneChangeParam;
import com.beikbank.android.dataparam2.setJiaoyiPasswdParam;
import com.beikbank.android.dataparam2.setLoginPasswdParam;
import com.beikbank.android.dataparam2.getYuLiuPhoneParam;
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
import com.beikbank.android.utils2.TimeUtil;
import com.beikbank.android.dataparam2.*;
import com.beikbank.android.data2.*;
import com.google.gson.JsonObject;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-8
 **/
public class IBusinessImpl2 {
	private boolean debug=true;
    private String TAG="IBusinessImpl";
    /**
     * 用户
     */
    private String base_service=SystemConfig.base_url;
    
    
    private String base_service_443=RequestUrl.SERVER_443;
    private static String url1=":10028/user/userApi?report=";
    private static String url2=":10039/beikbank-settlement/settlementApi.do";
    private static String url3=":10025/productAPI/product/productAPI.do";
    private static String url4=":10023/beikbank-order-api/orderApi.do";
    private static String url5=":10024/beikbank-trade-api/tradeApi.do?report=";
    private static String url6=":10022/beikbank-pay-api/payApi.do?report=";
    private static String url7=":10026/managerment/manager/managerApi.do?report=";
    private static String url8=":10026/beikbank-message-api/msg.do?report=";
    /**
     * 红包系统
     */
    private static String url9=":10044/beikbank-bonus-api/bonusApi.do?report=";
    static 
    {
    	  if(SystemConfig.index==1)
    	   {
    		  url1=":10028/user/userApi?report=";
    		  url2=":10039/beikbank-settlement/settlementApi.do";
    		  url3=":10025/productAPI/product/productAPI.do";
    		  url4=":10023/beikbank-order-api/orderApi.do";
    		  url5=":10024/beikbank-trade-api/tradeApi.do?report=";
    		  url6=":10022/beikbank-pay-api/payApi.do?report=";
    		  url7=":10026/managerment/manager/managerApi.do?report=";
    		  url8=":10040/beikbank-message-api/msg.do?report=";
    	   }
    	   else if(SystemConfig.index==2)
    	   {
    		    url1=":5055/user/userApi?report=";
    		    url2=":5058/beikbank-settlement/settlementApi.do";
    		    url3=":5053/productAPI/product/productAPI.do";
    		    url4=":5051/beikbank-order-api/orderApi.do";
    		    url5=":5052/beikbank-trade-api/tradeApi.do?report=";
    		    url6=":5050/beikbank-pay-api/payApi.do?report=";
    		    url7=":5054/managerment/manager/managerApi.do?report=";
    		    url8=":5059/beikbank-message-api/msg.do?report=";
    	   }
    	   else if(SystemConfig.index==3)
    	   {
//    		    url1=":13028/user/userApi?report=";
//    		    url2=":13039/beikbank-settlement/settlementApi.do";
//    		    url3=":13025/productAPI/product/productAPI.do";
//    		    url4=":13023/beikbank-order-api/orderApi.do";
//    		    url5=":13024/beikbank-trade-api/tradeApi.do?report=";
//    		    url6=":13043/recharge/recharge.do?report=";
//    		    url7=":13026/managerment/manager/managerApi.do?report=";
//    		    url8=":13040/beikbank-message-api/msg.do?report=";
    		    
    		    
    		    
    		    url1=":13128/user/userApi?report=";
    		    url2=":13139/beikbank-settlement/settlementApi.do";
    		    url3=":13125/productAPI/product/productAPI.do";
    		    url4=":13123/beikbank-order-api/orderApi.do";
    		    url5=":13124/beikbank-trade-api/tradeApi.do?report=";
    		    url6=":13143/recharge/recharge.do?report=";
    		    url7=":13126/managerment/manager/managerApi.do?report=";
    		    url8=":13140/beikbank-message-api/msg.do?report=";
    		    
    		    
    		    
    	   }
    	
    }
   
	   

    
    
    
//    private String url1=":5055/user/userApi?report=";
//    private String url2=":5058/beikbank-settlement/settlementApi.do";
//    private String url3=":5053/productAPI/product/productAPI.do";
//    private String url4=":5051/beikbank-order-api/orderApi.do";
//    private String url5=":5052/beikbank-trade-api/tradeApi.do?report=";
//    private String url6=":5050/beikbank-pay-api/payApi.do?report=";
//    private String url7=":5054/managerment/manager/managerApi.do?report=";
  
    Context context;
    public IBusinessImpl2(Context context)
    {
    	this.context=context;
    }
    
    
    public Object execute(Object paramClass)throws Exception
    {    
         Object obj=null;
        
       
         if(paramClass instanceof XiaoxiParam)
         {
        	 obj=getXiaoxi(null,null, paramClass);
         }
         else if(paramClass instanceof XiaoxiLieBiaoParam)
         {
        	 obj=XiaoxiLieBiao(null,null, paramClass);
         }
         else if(paramClass instanceof getZaiQuanParam)
         {
        	 obj=getZaiQuan(null,null, paramClass);
         }else if(paramClass instanceof JiaoYiJiLuParam)
         {
        	 obj=JiaoYiJiLu(null,null, paramClass);
         }
         else if(paramClass instanceof LunBoParam)
         {
        	 obj=getLunBo(LunBo_data.class,null, paramClass);
         }
         else if(paramClass instanceof LoginQianParam)
         {   
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070049";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(LoginQian_data.class, url, paramClass, hp);
         }
        
         else if(paramClass instanceof setLoginPasswdParam)
         {   
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070055";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(setLoginPasswd_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof NameRengzhenParam)
         {   
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070056";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(NameRengzhen_data.class, url, paramClass, hp);
         }
         
         else if(paramClass instanceof LoginParam)
         {   
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070126";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(Login_data.class, url, paramClass, hp);
         }
         
         else if(paramClass instanceof setJiaoyiPasswdParam)
         {   
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070115";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(setJiaoyiPasswd_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof RegisterParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070054";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(Register_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof UserCheckParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070116";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(UserCheck_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof UserCheckParam3)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070051";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(UserCheck3_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof UserCheckParam2)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070117";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(UserCheck2_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof GetCanPingLieBiaoParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="000020";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(UserCheck_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof GetChanPinParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="030050";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url3;
        	 obj=getTongYong2(GetChanPin_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof BindBankQinQiuParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070112";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(BindBankQinQiu_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof CreateDingDanParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="010010";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url4+"?report=";
      	   JSONObject json1=new JSONObject(); 
      	   JSONObject json2=new JSONObject();  
      	   JSONArray body=new JSONArray();
      	  CreateDingDanParam cd= (CreateDingDanParam) paramClass;
  	       for(int i=0;i<cd.red_packet_list.size();i++)
  	      {   
  	    	 //JSONObject json=new JSONObject();
  	    	 //json.put("trade_type",h2.trade_type.get(i));
  	    	 json2=new JSONObject();  
  	    	 json2.put("coupon_no",cd.red_packet_list.get(i));
  	    	 body.put(json2);
  	      }
  	     json1.put("acc_id", cd.acc_id);
  	     json1.put("acc_number",cd.acc_number);
  	     json1.put("amount", cd.amount);
  	     json1.put("assets_id",cd.assets_id);
  	     json1.put("order_source",cd.order_source);    
  	     json1.put("order_type",cd.order_type);    
  	     json1.put("pro_id",cd.pro_id);   
  	     json1.put("pro_type",cd.pro_type);     
  	     json1.put("red_packet_list", body);      
  	    json1.put("user_code",cd.user_code);      
      	     
        	 obj=getTongYong(CreateDingDan_data.class, url, json1, hp);
         }
         else if(paramClass instanceof getQianBaoParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070113";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(getQianBao_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof BindBankQueQengParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070090";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(BindBankQueReng_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof ChongZhiParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="000010";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url5;
        	 obj=getTongYong(ChongZhi_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof ChongZhiQueRengParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="000011";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url5;
        	 obj=getTongYong(ChongZhiQueReng_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof GouMaiParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="000012";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url5;
        	 obj=getTongYong(Goumai_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getZuoRiShouYiParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="000016";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(getZuoRiShouYi_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getLeiJiShouYiParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="000015";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
      	     
      	     
      	   
      	    
        	 obj=getTongYong2(getLeiJiShouYi_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getDingDanXiangQinParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="010060";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url4+"?report=";
        	 obj=getTongYong(getDingDanXiangQin_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof MiMaOrDuanXinParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="020025";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url6;
        	 obj=getTongYong(MiMaOrDuanXin_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof MiMaOrDuanXinParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="020025";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url6;
        	 obj=getTongYong(MiMaOrDuanXin_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getTiXianQianZhiParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070118";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(getTiXianQianZhi_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof TiXianQingQiuParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="000018";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url5;
        	 obj=getTongYong(TiXianQingQiu_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof TiXianQueRengParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="000019";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url5;
        	 obj=getTongYong(TiXianQueReng_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getUserOrXiuGaiParam)
         {
        	 HeadParam2 hp=new HeadParam2();
      	     hp.tra_code="070070";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(getUserOrXiuGai_data.class, url, paramClass, hp);
         }
        
         else if(paramClass instanceof getYanZhenMaParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="070052";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(getYanZhenMa_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof checkYanZhenMaParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="070053";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(checkYanZhenMa_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getUserZhiChanParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000012";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(GetUserZhiChan_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getUserZhiChanParam2)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000032";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(GetUserZhiChan2_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getJiaoYiJiLvParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="010110";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url4+"?report=";
      	   JSONObject json1=new JSONObject();  
      	   JSONArray body=new JSONArray();
      	   getJiaoYiJiLvParam h2= (getJiaoYiJiLvParam) paramClass;
  	     for(int i=0;i<h2.trade_type.size();i++)
  	     {   
  	    	 //JSONObject json=new JSONObject();
  	    	 //json.put("trade_type",h2.trade_type.get(i));
  	    	 body.put(h2.trade_type.get(i));
  	     }
  	     json1.put("limit", h2.limit);
  	     json1.put("offset", h2.offset);
  	     json1.put("user_code", h2.user_code);
  	     json1.put("trade_type", body);
         obj=getTongYong(getJiaoYiJiLv_data.class, url,json1, hp);
         }
         else if(paramClass instanceof getQvKuaiParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="050021";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url7;
        	 obj=getTongYong(getQvKuai_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getXiangMuXinXiParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="030030";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url3+"?report=";
        	 obj=getTongYong(getXiangMuXinXi_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getYiGouParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="010050";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url4;
        	 obj=getTongYong2(getYiGou_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getDingQiXiangQinParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000022";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(getDingQiXiangQin_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getHuoQiXiangQinParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000019";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(getHuoQiXiangQin_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof HuoQiShuHuiParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000014";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url5;
        	 obj=getTongYong(HuoQiShuHui_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof CheckJiaoYiMiMaParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="070114";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(CheckJiaoYiMiMa_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof HuTouOpenOrCloseParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000025";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(HuTouOpenOrClose_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getMeiRiShouYiParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000014";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(getMeiRiShouYi_data.class, url, paramClass, hp);
         } else if(paramClass instanceof getDingQiXiangQingParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000021";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(getDingQiXiangQing_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getTanChuanParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="040001";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url7;
        	 obj=getTongYong(TanChuan_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getGongGaoParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="050031";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url7;
        	 obj=getTongYong(GongGao_data.class, url, paramClass, hp);
         }
         
         else if(paramClass instanceof getHuoQiXiangQinParam2)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000019";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(getHuoQiXiangQin2_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof ShouShiParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="070072";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(ShouShi_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof CheckShouShiParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="070124";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(CheckShouShi_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getAllBankParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="020027";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url6;
        	 obj=getTongYong(getAllBank_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof HuoQiShuHuiParam2)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000033";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
      	     JSONArray body=new JSONArray();
      	     HuoQiShuHuiParam2 h2=(HuoQiShuHuiParam2) paramClass;
      	     for(int i=0;i<h2.assets_id.size();i++)
      	     {   
      	    	 JSONObject json=new JSONObject();
      	    	 json.put("assets_id",h2.assets_id.get(i));
      	    	 body.put(json);
      	     }
        	 obj=getTongYong2(HuoQiShuHui_data2.class, url,body, hp);
         }
         else if(paramClass instanceof getZhiChanParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000024";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(getZhiChan_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof HuoQiYiGouParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="000034";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url2;
        	 obj=getTongYong2(HuoQiYiGou_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getShareParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="050041";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url7;
        	 obj=getTongYong(getShare_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof XiaoXiIsParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="030150";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url8;
        	 obj=getTongYong(XiaoXiIs_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof XiaoXiGetParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="030120";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url8;
        	 obj=getTongYong(XiaoXiGet_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof XiaoXiReadParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="030130";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url8;
        	 obj=getTongYong(XiaoXiRead_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getChanPinXiangQinParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="030111";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url3+"?report=";
        	 obj=getTongYong(getChanPinXiangQin_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getShouZhiMinXiParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="070129";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(getShouZhiMinXi_data.class, url, paramClass, hp);
         }
         
         else if(paramClass instanceof phoneChangeCheckParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="070130";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(phoneChangeChenck_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof phoneChangeParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="070057";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url1;
        	 obj=getTongYong(phoneChange_data.class, url, paramClass, hp);
         }
         else if(paramClass instanceof getAllYouhuiQuanParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="180010";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url9;
        	 obj=getTongYong(getAllYouHuiQuan_data.class, url, paramClass, hp);
         }
        
         else if(paramClass instanceof getYouhuiQuanParam)
         {
        	 hp=new HeadParam2();
      	     hp.tra_code="180011";
      	     hp.request_seq=TimeUtil.getTime2();
      	     String url=base_service+url9;
        	 obj=getTongYong(getAllYouHuiQuan_data.class, url, paramClass, hp);
         }
		 else if(paramClass instanceof ShuJuQianYi1Param)
		 {
			 hp=new HeadParam2();
			 hp.tra_code="070133";
			 hp.request_seq=TimeUtil.getTime2();
			 String url=base_service+url1;
			 obj=getTongYong(ShuJuQianYi1_data.class, url, paramClass, hp);
		 }
		 else if(paramClass instanceof ShuJuQianYi2Param)
		 {
			 hp=new HeadParam2();
			 hp.tra_code="070134";
			 hp.request_seq=TimeUtil.getTime2();
			 String url=base_service+url1;
			 obj=getTongYong(ShuJuQianYi2_data.class, url, paramClass, hp);
		 }
		 else if(paramClass instanceof getYuLiuPhoneParam)
		 {
			 hp=new HeadParam2();
			 hp.tra_code="020028";
			 hp.request_seq=TimeUtil.getTime2();
			 String url=base_service+url6;
			 obj=getTongYong(getYuLiuPhone_data.class, url, paramClass, hp);
		 }
		 else if(paramClass instanceof unBindParam)
		 {
			 hp=new HeadParam2();
			 hp.tra_code="070125";
			 hp.request_seq=TimeUtil.getTime2();
			 String url=base_service+url1;
			 obj=getTongYong(unBind_data.class, url, paramClass, hp);
		 }
         return obj;
    }
    HeadParam2 hp;
    
    
    /**
     * 通用请求get
     *@return
     */
     public Object getTongYong3(Class<?> returnClass, String url, Object JSONObject,HeadParam2 hp)throws Exception
     {
  	  
  	     Object obj=null;
  	     String json=Jiami.getValue(hp,JSONObject);
  	     if(SystemConfig.isDebug())
	     {
	         Log.d("json_qinqiu",json);
	     }
 	     url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	     String ss=  hu.getRequest(url);
	     if(SystemConfig.isDebug())
	     {
	         Log.d("json"+"-", ss);
	     }
	 	 ResponseParamManager rpm=new ResponseParamManager();
	 	 obj=rpm.getObjectFromJson(returnClass,ss);
	    
  	     return obj;
     }
     
    /**
     * 通用请求get
     *@return
     */
     public Object getTongYong(Class<?> returnClass, String url, Object paramClass,HeadParam2 hp)throws Exception
     {
  	  
  	     Object obj=null;
  	     String json=Jiami.getValue(hp,paramClass);
  	     if(SystemConfig.isDebug())
	     {
			 Log.e("json_qinqiu"+"_"+paramClass.getClass().getName(),json);
	     }
 	     url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	     String ss=  hu.getRequest(url);
	     if(SystemConfig.isDebug())
	     {
	         Log.e("json"+"-"+paramClass.getClass().getName(), ss);
	     }
	 	 ResponseParamManager rpm=new ResponseParamManager();
	 	 obj=rpm.getObjectFromJson(returnClass,ss);
	    
  	     return obj;
     }
     
     
     
     /**
      * 通用请求post
      *@return
      */
      public Object getTongYong2(Class<?> returnClass, String url, Object paramClass,HeadParam2 hp)throws Exception
      {
   	  
   	     Object obj=null;
   	    HttpUtil hu=new HttpUtil(context);
   
   	    String json=Jiami.getValue(hp,paramClass);
   	 if(SystemConfig.isDebug())
     {
         Log.e("json_qinqiu"+"_"+paramClass.getClass().getName(),json);
     }
   	     TreeMap<String,String> map=new TreeMap<String, String>();
   	     map.put("report",json);
    	 String ss=hu.postRequest(url, map);
 	     if(SystemConfig.isDebug())
 	     {
 	    	 Log.e("json"+"-"+paramClass.getClass().getName(), ss);
 	     }
 	 	 ResponseParamManager rpm=new ResponseParamManager();
 	 	 obj=rpm.getObjectFromJson(returnClass,ss);
 	    
   	     return obj;
      }
    
    /**
     * 得到验证嘛
     *@return
     */
     public Object getYanZhenMa(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.tongyong;
  		 }
  		 url=base_service+"user/userApi.do?report=";
  		// RequestParamManager rpm=new RequestParamManager();
  		 
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam2 hp=new HeadParam2();
  	     
  	     hp.tra_code="070052";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.request_seq="1";
  	     
  	    String json=Jiami.getValue(hp,paramClass);
 	    url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	    String ss=  hu.getRequest(url);
  	   return obj;
     }
    /**
     * 登录前调用
     *@return
     */
     public Object loginQian(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.tongyong;
  		 }
  		 url=base_service+"user/userApi.do?report=";
  		// RequestParamManager rpm=new RequestParamManager();
  		 
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam2 hp=new HeadParam2();
  	     
  	     hp.tra_code="070049";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.request_seq="1";
  	     
  	    String json=Jiami.getValue(hp,paramClass);
 	    url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	    String ss=  hu.getRequest(url);
  	   return obj;
     }
    /**
     * 登录
     *@return
     */
     public Object login(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.tongyong;
  		 }
  		 url=base_service+"user/userApi.do?report=";
  		// RequestParamManager rpm=new RequestParamManager();
  		 
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam2 hp=new HeadParam2();
  	     
  	     hp.tra_code="070050";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.request_seq="1";
  	     
  	    String json=Jiami.getValue(hp,paramClass);
 	    url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	    String ss=  hu.getRequest(url);
  	   return obj;
     }
    
    /**
     * 得到交易记录
     *@return
     */
     public Object JiaoYiJiLu(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.tongyong;
  		 }
  		 url="http://114.55.5.66:10024/beikbank-trade-api/tradeApi.do?report=";
  		// RequestParamManager rpm=new RequestParamManager();
  		 
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam2 hp=new HeadParam2();
  	     
  	     hp.tra_code="000017";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.request_seq="1";
  	     
  	    String json=Jiami.getValue(hp,paramClass);
 	    url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	    String ss=  hu.getRequest(url);
  	   return obj;
     }
    
    
    
    /**
     * 得到消息
     *@return
     */
     public Object XiaoxiLieBiao(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=null;
  		 if(url==null)
  		 {
  			 url=RequestUrl.SERVER+RequestUrl.tongyong;
  		 }
  		 url="http://114.55.5.66:10040/beikbank-message-api/msg.do?report=";
  		// RequestParamManager rpm=new RequestParamManager();
  		 
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam2 hp=new HeadParam2();
  	     
  	     hp.tra_code="030120";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.request_seq="1";
  	     
  	    String json=Jiami.getValue(hp,paramClass);
 	    url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	    String ss=  hu.getRequest(url);
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
  		 url="http://114.55.5.66:10040/beikbank-message-api/msg.do?report=";
  		// RequestParamManager rpm=new RequestParamManager();
  		 
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam hp=new HeadParam();
  	     
  	     hp.tra_code="040013";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.rep_seq="1";
  	     hp.gps="";
  	    String json=Jiami.getValue(hp,paramClass);
 	    url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	    String ss=  hu.getRequest(url);
  	   return obj;
     }
    /**
     * 得到用户资产
     *@return
     */
     public Object getUserZhiChan(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=new TreeMap<String, String>();
  		 if(url==null)
  		 {
  			 //url=SystemConfig.kHOST_URL2+RequestUrl.tongyong;
  			 url="http://114.55.5.66:10039/beikbank-settlement/settlementApi.do?report=";
  		 }
  		// RequestParamManager rpm=new RequestParamManager();
  		
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam2 hp=new HeadParam2();
  	     
  	     hp.tra_code="000012";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.request_seq="10001";
  	   
  	    String json=Jiami.getValue(hp,paramClass);
  	    url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	   String ss=  hu.getRequest(url);
	   
	     System.out.println(123);
  	   return obj;
     }
    
     /**
      * 得到分散债券
      *@return
      */
      public Object getZaiQuan(Class<?> returnClass, String url, Object paramClass)throws Exception
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
   	     
   	     hp.tra_code="030030";
   	     //hp.rep_seq=Jiami.getLiushui();
   	     hp.request_seq="030030";
   	   
   	    String json=Jiami.getValue(hp,paramClass);
   	    url=url+URLEncoder.encode(json);
 	     HttpUtil hu=new HttpUtil(context);
 	   String ss=  hu.getRequest(url);
 	   
 	     System.out.println(123);
   	   return obj;
      }
     
     
    /**
     * 得到已购用户
     *@return
     */
     public Object getYiGou(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=new TreeMap<String, String>();
  		 if(url==null)
  		 {
  			 //url=SystemConfig.kHOST_URL2+RequestUrl.tongyong;
  			 url="http://114.55.5.66:10023/beikbank-order-api/orderApi.do?report=";
  		 }
  		// RequestParamManager rpm=new RequestParamManager();
  		
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam2 hp=new HeadParam2();
  	     
  	     hp.tra_code="010050";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.request_seq="10001";
  	   
  	    String json=Jiami.getValue(hp,paramClass);
  	    url=url+URLEncoder.encode(json);
	     HttpUtil hu=new HttpUtil(context);
	   String ss=  hu.getRequest(url);
	   
	     System.out.println(123);
  	   return obj;
     }
    
    
    
    /**
     * 注册
     *@return
     */
     public Object register2(Class<?> returnClass, String url, Object paramClass)throws Exception
     {
  	  
  	     Object obj=null;
  		 TreeMap<String,String> map=new TreeMap<String, String>();
  		 if(url==null)
  		 {
  			 //url=SystemConfig.kHOST_URL2+RequestUrl.tongyong;
  			 url="http://172.16.200.111:8080/user/userApi?report=";
  		 }
  		// RequestParamManager rpm=new RequestParamManager();
  		
  	     //map=rpm.getMapString(paramClass);
  		 HeadParam2 hp=new HeadParam2();
  	     
  	     hp.tra_code="070054";
  	     //hp.rep_seq=Jiami.getLiushui();
  	     hp.request_seq="10001";
  	   
  	    String json=Jiami.getValue(hp,paramClass);
  	    url=url+URLEncoder.encode(json);
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
     			 url=base_service+"user/userApi";
     		 }
     		// RequestParamManager rpm=new RequestParamManager();
     		
     	     //map=rpm.getMapString(paramClass);
     		 HeadParam2 hp=new HeadParam2();
     	     
     	     hp.tra_code="070117";
     	     //hp.rep_seq=Jiami.getLiushui();
     	     hp.request_seq=TimeUtil.getTime2();
     	   
     	    String json=Jiami.getValue(hp,paramClass);
     	    url=url+"?"+URLEncoder.encode(json);
  	     HttpUtil hu=new HttpUtil(context);
  	   String ss=  hu.getRequest(url);
  	   
  	     System.out.println(123);
     	   return obj;
        }
        /**
         * 判断用户是否注册
         *@return
         */
         public Object UserCheck2(Class<?> returnClass, String url, Object paramClass)throws Exception
         {
      	  
      	     Object obj=null;
      		 TreeMap<String,String> map=new TreeMap<String, String>();
      		 if(url==null)
      		 {
      			 //url=SystemConfig.kHOST_URL2+RequestUrl.tongyong;
      			 url=base_service+"http://114.55.5.66:10028/user/userApi";
      		 }
      		// RequestParamManager rpm=new RequestParamManager();
      		
      	     //map=rpm.getMapString(paramClass);
      		 HeadParam2 hp=new HeadParam2();
      	     
      	     hp.tra_code="070049";
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
}
