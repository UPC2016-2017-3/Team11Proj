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


function serch()
{
   document.info.action="Admin_listReservesSum.action";
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
  document.info.action="Admin_listReservesSum.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listReservesSum.action";
  document.info.submit();
}
$(document).ready(function(){
	$("#export").bind('click',function(){
		var aQuery = $("#info").serialize();  
		$("#info").attr('action','exportReservesSum.action').submit();;
	});
});
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">会议室预约管理&gt;&gt;会议室预约统计</span>
</div>
<form name="info" id="info" action="Admin_listReservesSum.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">会议室预约列表</td>
    <td width="" align="right">
      <!-- 
            会议室名称：
      <input type="text" style="width:80px;" id="paramsReserve.room.roomName" name="paramsReserve.room.roomName" 
      		value="${paramsReserve.room.roomName}" class="inputstyle"/>&nbsp;
            预约人：
      <input type="text" style="width:80px;" id="paramsReserve.realName" name="paramsReserve.realName" 
      		value="${paramsReserve.realName}" class="inputstyle"/>&nbsp;
            预约部门：
      <input type="text" style="width:80px;" id="paramsReserve.userDept" name="paramsReserve.userDept" 
      		value="${paramsReserve.userDept}" class="inputstyle"/>&nbsp;
            预约日期：
      <s:textfield name="paramsReserve.reserveDateMin" id="paramsReserve.reserveDateMin" 
					 value="%{#attr.paramsReserve.reserveDateMinDesc}"  cssStyle="width:80px"
					 onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>&nbsp;
	  -
	  <s:textfield name="paramsReserve.reserveDateMax" id="paramsReserve.reserveDateMax" 
					 value="%{#attr.paramsReserve.reserveDateMaxDesc}"  cssStyle="width:80px"
					 onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
       -->
      <input type="button" value="导出" id="export" class="btnstyle"/>&nbsp;
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">会议室名称</td>
     <td width="" align="center">总计预约次数</td>
     <td width="" align="center">总计预约时间(小时)</td>
   </tr>
   <s:if test="#attr.reserves!=null && #attr.reserves.size()>0">
   <s:iterator value="#attr.reserves" id="reserve" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:property value="#status.index+#attr.paramsReserve.start+1"/></td>
     <td width="" align="center"><s:property value="#reserve.roomName"/></td>  
     <td width="" align="center"><s:property value="#reserve.reserveCount"/></td>  
     <td width="" align="center"><s:property value="#reserve.reserveLengthH"/></td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="10" align="center"><b>&lt;不存在会议室预约统计信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>