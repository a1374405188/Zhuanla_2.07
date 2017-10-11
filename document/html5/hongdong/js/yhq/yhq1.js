 $(document).ready(function(){
	   var s=$(document).height();
       var a= $("#head_p1").height(s/10);
	  
	  $("#head").height(s/15+1);
	  $("#head_img1").height(s/15-20);
	  $("#head_img1").width(s/15-20);
	  $("#head_img1").css("paddingTop",10+"px");
	  $("#div_img").height(s/15);
	
	  $("#head_p1").height(s/15);
	  $("#head_p2").height(s/15);
	  $("#head_p1").css("line-height",s/15+"px");
	  $("#head_p2").css("line-height",s/15+"px");
	  
	 
	  
    });
 function getId()
 {
    var id=window.wv.getid();
	return id;
 }	
	