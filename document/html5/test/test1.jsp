
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML lang="en">
<HEAD>
 
<SCRIPT type="text/javascript" src="jquery-2.1.4.min.js"></SCRIPT>
<SCRIPT type="text/javascript">
 
     alert("aSSDF");
    
     $(
	 function(){
	   alert("aSSDF6");
	 }
	 );
   
</SCRIPT>
</HEAD>
<BODY>
 <P>TETT1</P>
 <button>��ҳ�淢�� HTTP GET ����Ȼ���÷��صĽ��</button>
 
 <script>
$(document).ready(function(){
  $("button").click(function(){
      alert("aSSDF6");
    $.get("http://172.16.100.188:8080/beikbankapp/appapi/product/getProductBondList.action/",function(data,status){
      alert("���ݣ�" + data + "\n״̬��" + status);
    });
  });
});

 

</script>
</BODY>
</HTML>