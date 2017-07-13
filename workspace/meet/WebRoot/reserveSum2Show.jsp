<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议室预约统计</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}

function toPair(str){
	if(Number(str)<10){
		return "0"+str;
	}else{
		return str;
	}
}
 function calender(){
	  var time=new Date();
	  var year=time.getFullYear();
	  var month=toPair(time.getMonth()+1);
	  var day=toPair(time.getDate()); 
	  return year+"-"+month+"-"+day;
 }

function serch()
{
   var reserveDateMin = document.getElementById("paramsReserve.reserveDateMin").value;
   var reserveDateMax = document.getElementById("paramsReserve.reserveDateMax").value;
   if(reserveDateMin=="" || reserveDateMax==""){
	   alert("预约日期段不能为空");
	   return;
   }
   if(reserveDateMin<calender()){
	   alert('预约开始日期不能小于今天');
	   return;
   }
   if(reserveDateMin > reserveDateMax){
	   alert("预约起始日期不能大于结束日期");
	   return;
   }
   document.info.action="Admin_listReservesSum2.action";
   document.info.submit();
}

function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listReservesSum2.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listReservesSum2.action";
  document.info.submit();
}
$(document).ready(function(){
	$("#export").bind('click',function(){
		var aQuery = $("#info").serialize();  
		$("#info").attr('action','exportReservesSum2.action').submit();;
	});
});
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">会议室查询</span>
</div>
<form name="info" id="info" action="Admin_listReservesSum2.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">会议室预约列表</td>
    <td width="" align="right">
            会议室名称：
      <s:select id="paramsReserve.room.roomId" name="paramsReserve.room.roomId" 
      			list="#attr.rooms" listKey="roomId" listValue="%{roomNo+' '+roomName}"   value="%{#attr.paramsReserve.room.roomId}"
      			headerKey="0" headerValue="请选择" cssStyle="width:155px" cssClass="selectstyle">
      </s:select>&nbsp;
            预约日期：
      <s:textfield name="paramsReserve.reserveDateMin" id="paramsReserve.reserveDateMin" 
					 value="%{#attr.paramsReserve.reserveDateMinDesc}"  cssStyle="width:80px"
					 onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>&nbsp;
	  -
	  <s:textfield name="paramsReserve.reserveDateMax" id="paramsReserve.reserveDateMax" 
					 value="%{#attr.paramsReserve.reserveDateMaxDesc}"  cssStyle="width:80px"
					 onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <%--<input type="button" value="导出" id="export" class="btnstyle"/>&nbsp;--%>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="" align="center">日期</td>
     <td width="" align="center">星期</td>
     <td width="" align="center">上午(08:00-12:00)</td>
     <td width="" align="center">下午(13:00-18:00)</td>
     <td width="" align="center">晚上(19:00-22:00)</td>
   </tr>
   <s:if test="#attr.reserves!=null && #attr.reserves.size()>0">
   <s:iterator value="#attr.reserves" id="reserve" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:property value="#reserve.reserve_date"/></td>
     <td width="" align="center"><s:property value="#reserve.reserve_weekDesc"/></td>  
     <s:if test="#reserve.items1!=null && #reserve.items1.size()>0">
     <s:if test="#reserve.items1_color==3">
     <td width="" align="center" style="background-color:red;color:white">
     	<s:iterator value="#reserve.items1" id="item1" status="status">
     	<s:property value="#item1.user_dept"/>(<s:property value="#item1.reserve_flag"/>)<s:property value="#item1.real_name"/>
     	<br/>用途：<s:property value="#item1.meet_content"/>
     	<br/><span style="text-decoration:underline">时间：<s:property value="#item1.reserve_time"/></span>
     	<s:if test="!#status.last"><br/><br/></s:if>
     	</s:iterator>
     </td>  
     </s:if>
     <s:else>
     <td width="" align="center" style="background-color:orange;color:white">
     	<s:iterator value="#reserve.items1" id="item1" status="status">
     	<s:property value="#item1.user_dept"/>(<s:property value="#item1.reserve_flag"/>)<s:property value="#item1.real_name"/>
     	<br/>用途：<s:property value="#item1.meet_content"/>
     	<br/><span style="text-decoration:underline">时间：<s:property value="#item1.reserve_time"/></span>
     	<s:if test="!#status.last"><br/><br/></s:if>
     	</s:iterator>
     </td>  
     </s:else>
     </s:if>
     <s:elseif test="#reserve.items1_color==1">
     <td width="" align="center" style="background-color:green;color:white">可预约</td>  
     </s:elseif>
     <s:else>
     <td width="" align="center" style="background-color:gray;color:white">已过期</td>  
     </s:else>
     
     <s:if test="#reserve.items2!=null && #reserve.items2.size()>0">
     <s:if test="#reserve.items2_color==3">
     <td width="" align="center" style="background-color:red;color:white">
     	<s:iterator value="#reserve.items2" id="item2" status="status">
     	<s:property value="#item2.user_dept"/>(<s:property value="#item2.reserve_flag"/>)<s:property value="#item2.real_name"/>
     	<br/>用途：<s:property value="#item2.meet_content"/>
     	<br/><span style="text-decoration:underline">时间：<s:property value="#item2.reserve_time"/></span>
     	<s:if test="!#status.last"><br/><br/></s:if>
     	</s:iterator>
     </td>  
     </s:if>
     <s:else>
     <td width="" align="center" style="background-color:orange;color:white">
     	<s:iterator value="#reserve.items2" id="item2" status="status">
     	<s:property value="#item2.user_dept"/>(<s:property value="#item2.reserve_flag"/>)<s:property value="#item2.real_name"/>
     	<br/>用途：<s:property value="#item2.meet_content"/>
     	<br/><span style="text-decoration:underline">时间：<s:property value="#item2.reserve_time"/></span>
     	<s:if test="!#status.last"><br/><br/></s:if>
     	</s:iterator>
     </td>  
     </s:else>
     </s:if>
     <s:elseif test="#reserve.items2_color==1">
     <td width="" align="center" style="background-color:green;color:white">可预约</td>  
     </s:elseif>
     <s:else>
     <td width="" align="center" style="background-color:gray;color:white">已过期</td>  
     </s:else>
     
     <s:if test="#reserve.items3!=null && #reserve.items3.size()>0">
     <s:if test="#reserve.items3_color==3">
     <td width="" align="center" style="background-color:red;color:white">
     	<s:iterator value="#reserve.items3" id="item3" status="status">
     	<s:property value="#item3.user_dept"/>(<s:property value="#item3.reserve_flag"/>)<s:property value="#item3.real_name"/>
     	<br/>用途：<s:property value="#item3.meet_content"/>
     	<br/><span style="text-decoration:underline">时间：<s:property value="#item3.reserve_time"/></span>
     	<s:if test="!#status.last"><br/><br/></s:if>
     	</s:iterator>
     </td>  
     </s:if>
     <s:else>
     <td width="" align="center" style="background-color:orange;color:white">
     	<s:iterator value="#reserve.items3" id="item3" status="status">
     	<s:property value="#item3.user_dept"/>(<s:property value="#item3.reserve_flag"/>)<s:property value="#item3.real_name"/>
     	<br/>用途：<s:property value="#item3.meet_content"/>
     	<br/><span style="text-decoration:underline">时间：<s:property value="#item3.reserve_time"/></span>
     	<s:if test="!#status.last"><br/><br/></s:if>
     	</s:iterator>
     </td>  
     </s:else>
     </s:if>
     <s:elseif test="#reserve.items3_color==1">
     <td width="" align="center" style="background-color:green;color:white">可预约</td>  
     </s:elseif>
     <s:else>
     <td width="" align="center" style="background-color:gray;color:white">已过期</td>  
     </s:else>
     
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="10" align="center"><b>&lt;<s:property value="#attr.tip!=null?#attr.tip:'不存在会议室预约统计信息'"/>&gt;</b></td>
   </tr>
   </s:else>
   
</table>
</form> 
</body>
</html>