package com.beikbank.android.utils2;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

/***
 * 
 * @author Administrator
 * 位置工具
 */
public class WeizhiUtil 
{  
	String locationProvider;
	/**
	 * 得到经纬度
	 * 如果得不到经纬度返回""
	 * @return
	 */
   public String getWz(Context con)
   {   
	  
       //获取地理位置管理器  
	   LocationManager   locationManager = (LocationManager) con.getSystemService(Context.LOCATION_SERVICE);  
       //获取所有可用的位置提供器  
       List<String> providers = locationManager.getProviders(true);  
       if(providers.contains(LocationManager.GPS_PROVIDER)){  
           //如果是GPS  
         locationProvider = LocationManager.GPS_PROVIDER;  
       }else if(providers.contains(LocationManager.NETWORK_PROVIDER)){  
           //如果是Network  
           locationProvider = LocationManager.NETWORK_PROVIDER;  
       }else{  
           Toast.makeText(con, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();  
           
       }  
	   
       //获取Location  
       Location location = locationManager.getLastKnownLocation(locationProvider);  
       if(location!=null){  
           //不为空,显示地理位置经纬度  
    	  //纬度
          String s1=location.getLatitude()+"";
          //经度
          String s2=location.getLongitude()+""; 
          return s1+","+s2;
       }  
       //监视地理位置变化  
       //locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);  
	   
	   
	   return "";
   }
}
