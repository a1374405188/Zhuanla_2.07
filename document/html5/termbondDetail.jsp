<html> 
<head> 
<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html"; charset="utf-8">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no" />
<link rel="stylesheet" type="text/css" href="css/scrollbar.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<script type="application/javascript" src="js/iscroll.js"></script>
<style type="text/css" media="all">     

#wrapper {
	position:absolute; z-index:1;
	width:100%;
	top:0px; bottom:0px; left:0;
	background:#fff;
    overflow:auto;
}

#scroller {
	position:relative;
/*	-webkit-touch-callout:none;*/
	-webkit-tap-highlight-color:rgba(0,0,0,0);

	float:left;
	width:100%;
	padding:0;
	
}






</style>   
<SCRIPT type="text/javascript" src="js/jquery-1.11.3.min.js"></SCRIPT>
<script LANGUAGE="javascript">
//滑动的框架
var myScroll,
	pullDownEl, pullDownOffset,
	pullUpEl, pullUpOffset,
	generatedCount = 0;
	/**
 * 下拉刷新 （自定义实现此方法）
 * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
 */
function pullDownAction () {
	 setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
		
		
		myScroll.refresh();		//数据加载完成后，调用界面更新方法   Remember to refresh when contents are loaded (ie: on ajax completion)
	}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
}

/**
 * 滚动翻页 （自定义实现此方法）
 * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
 */
function pullUpAction () {
	setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
		var el, li, i;
		
		addData();
		//myScroll.refresh();		// 数据加载完成后，调用界面更新方法 Remember to refresh when contents are loaded (ie: on ajax completion)
	}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
}
/**
 * 初始化iScroll控件
 */
function loaded() {
	pullDownEl = document.getElementById('pullDown');
	pullDownOffset = pullDownEl.offsetHeight;
	pullUpEl = document.getElementById('pullUp');	
	pullUpOffset = pullUpEl.offsetHeight;
	
	myScroll = new iScroll('wrapper', {
		scrollbarClass: 'myScrollbar', /* 重要样式 */
		useTransition: false, /* 此属性不知用意，本人从true改为false  hideScrollbar:true,*/
		topOffset: pullDownOffset,
		onRefresh: function () {
			if (pullDownEl.className.match('loading')) {
				pullDownEl.className = '';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
			} else if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
			}
		},
		onScrollMove: function () {
			if (this.y > 5 && !pullDownEl.className.match('flip')) {
				pullDownEl.className = 'flip';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
				this.minScrollY = 0;
			} else if (this.y < 5 && pullDownEl.className.match('flip')) {
				pullDownEl.className = '';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
				this.minScrollY = -pullDownOffset;
			} else if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullDownEl.className.match('flip')) {
				pullDownEl.className = 'loading';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中...';				
				pullDownAction();	// Execute custom function (ajax call?)
			} else if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';				
				pullUpAction();	// Execute custom function (ajax call?)
			}
		}
	});
	
	setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
}

//初始化绑定iScroll控件 
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', loaded, false); 
	
	
 //add div 
 function addDiv(data)
 {     
      //
	  var count=data.data.detailCount;
	  $("#d1_1_2").text(count);
 
      var array=data.data.showDetail;
	 
	  var obj;
	   for(var i=0;i<array.length;i++)
	   {
     
       obj=array[i];
       var d2=$("#d2");
     
	   var s1='<div id="d2_1" class="itemt a">';
	   //var s1='<div class="outer"><div class="middle"><div class="inner a">';
	   s1=s1+obj.bondName;
	   s1=s1+'</div>';
	   d2.append(s1);
	   
	   var s2='<div id="d2_2" class="item b"><div id="d2_2_1" class="item b1">';
	   s2=s2+'债权金额:';
	   s2=s2+'</div><div id="d2_2_2" class="item c1">';
	   s2=s2+obj.amount;
	   s2=s2+'</div><div  class="line">p</div><div id="d2_2_4" class="item b2">';
	   s2=s2+'资产提供:';
	   s2=s2+'</div><div id="d2_2_5" class="item c2">';
	   s2=s2+obj.bondOffer;
	   s2=s2+'</div></div>';
	    d2.append(s2);
	   
	   var s2='<div id="d2_3" class="item c"><div id="d2_3_1" class="item b1">';
	   s2=s2+'还款方式:';
	   s2=s2+'</div><div id="d2_3_2" class="item c1">';
	   s2=s2+obj.repaymentType;
	   s2=s2+'</div><div  class="line">p</div><div id="d2_3_4" class="item b2">';
	   s2=s2+'债权类型:';
	   s2=s2+'</div><div id="d2_3_5" class="item c2">';
	   s2=s2+obj.bondType;
	   s2=s2+'</div></div>';
	   d2.append(s2);
	   
	   var s2='<div id="d2_4" class="item b"><div id="d2_4_1" class="item b1">';
	   s2=s2+'保障方式:';
	   s2=s2+'</div><div id="d2_4_2" class="item c3">';
	   s2=s2+obj.safeguard;
	   s2=s2+'</div></div>';
	   d2.append(s2);
	 
	 }
     initDiv();
	 myScroll.refresh();
	 
 }
 
 
 //初始化div高度函数
 function  initDiv()
 {
   var s=$(document).height();
   var a= $(".item").height(s/15);
   $(".item").css("line-height",s/15+"px");
   //$(".itemt").css("line-height",s/10+"px");
   
   
   $(".line").css("line-height",s/15-16+"px");
   $(".line").css("line-height",s/15-16+"px");
   
   
   $(".item_top1").css("line-height",s/10+"px");
   //$(".item_top2").css("line-height",s/12+"px");
   $(".item").css("font-size",11+"px");
   $(".a").css("font-size",14+"px");
 }
 //载入是调用
 $(document).ready(function(){
       
       //var d2=$("#d2");
	  // var s1='<div id="d2_1" class="item a">';
	   //s1=s1+'浙江贝壳财富';
	   // s1=s1+'</div>';
	   
	  // d2.append(s1);
	   
	   
	   //d2.append('<div id="d2_2" class="item b"><div id="d2_2_1" class="item b1">债券金额</div><div id="d2_2_2" class="item c1">200000</div><div  class="line">p</div><div id="d2_2_4" class="item b1">资产提供</div><div id="d2_2_5" class="item c1">灰灰带</div></div>');
	  // d2.append('<div id="d2_3" class="item c"><div id="d2_3_1" class="item b1">返款方式</div><div id="d2_3_2" class="item c1">等额本息</div><div  class="line">p</div><div id="d2_3_4" class="item b1">债券类型</div><div id="d2_3_5" class="item c1">消费信贷</div></div>');
	  // d2.append(' <div id="d2_4" class="item b"><div id="d2_4_1" class="item b1">保障方式:</div><div id="d2_4_2" class="item c1">资产方回购</div></div>');
	   
	   
	   addData();
	   
    });
	
//get data
 var start=0;
 var end=19;
 function addData()
   {  
      var host1=window.location.host;
	  host1="http://"+host1+"/beikbankapp/appapi/product/getTermbondShowDetail.action";
      $.get(host1,{termbondCode:"",startLine:start+"",endLine:end+"",productType:"0"},function(data){
	        if(data.result=="error")
			{
			   // alert(JSON.stringify(data));
			    // $("#e4").text(data.message);
			    // $("#e4").show();
				alert(data.message);
			}
			if(data.result=="success")
			{
			    
				addDiv(data);
				start+=20;
				end+=20;
			}
           
			
       },"json");
       
   }
</script>
</head>
<body>
 <div id="wrapper">
	<div id="scroller">
	<!--top-->
		<div id="pullDown">
			<span class="pullDownIcon"></span><span class="pullDownLabel">下拉刷新...</span>
		</div>
   <!--资产信息-->
   <!--
   <div id="d1" >
      <div id="d1_1">
        <div id="d1_1_1" class="item_top1">
	     债权分散(项):
	    </div>
	  
	     <div id="d1_1_2" class="item_top1">
	      2000000
	    </div>
	  </div>
   </div>
   -->
   <!--资产详细信息-->
     <div id="d2">
	       
	       
	       
	 </div>
	 
    <!--end-->
        <div id="pullUp">
		<span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>
		</div>
		
	</div>
</div>


	
   
</body>
</html>