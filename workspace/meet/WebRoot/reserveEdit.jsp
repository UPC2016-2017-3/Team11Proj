<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增会议室预约</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	function checkTime(time1,time2){
		 var date1 = time1.substring(0,10);
		 var date2 = time2.substring(0,10);
		 time1 = time1.substring(11);
		 time2 = time2.substring(11);
		 if(time1<"08:00:00" || (time1>"12:00:00" && time1<"13:00:00") || (time1 >"18:00:00" && time1<"19:00:00") || time1>"22:00:00"){
			 alert('会议室在该预约时间段不提供预约服务');
			 return false;
		 }
		 if(time2<"08:00:00" || (time2>"12:00:00" && time2<"13:00:00") || (time2 >"18:00:00" && time2<"19:00:00") || time2>"22:00:00"){
			 alert('会议室在该预约时间段不提供预约服务');
			 return false;
		 }
		 if(time1>="19:00:00" || (time1 >="13:00:00" && time2<="18:00:00") || (time1 >="08:00:00" && time2<="12:00:00")){
			 
		 }else{
			 alert('会议室不提供跨时间段预约');
			 return false;
		 }
		 if(date1!=date2){
			 alert('会议室不提供跨天预约');
			 return false;
		 }
		 return true;
	 }
	
	var num = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
	    if($("#paramsReserve\\.room\\.roomId").val()=='0'){
			alert('预约会议室不能为空');
			return;
		}
	    if($("#paramsReserve\\.realName").val()==''){
			alert('预约人不能为空');
			return;
		}
	    if($("#paramsReserve\\.userDept").val()==''){
			alert('预约部门不能为空');
			return;
		}
	    if($("#paramsReserve\\.reserveDate1").val()==''){
			alert('预约开始时间不能为空');
			return;
		}else{
			var date1 = $("#paramsReserve\\.reserveDate1").val();
			$("#paramsReserve\\.reserveDate1").val(date1.substring(0,17)+"00")
		}
		if($("#paramsReserve\\.reserveDate2").val()==''){
			alert('预约结束时间不能为空');
			return;
		}else{
			var date2 = $("#paramsReserve\\.reserveDate2").val();
			$("#paramsReserve\\.reserveDate2").val(date2.substring(0,17)+"00")
		}
		if($("#paramsReserve\\.reserveDate1").val() <= calender()){
			alert('预约开始时间不能小于当前时间');
			return;
		}
		if($("#paramsReserve\\.reserveDate2").val() < $("#paramsReserve\\.reserveDate1").val()){
			alert('预约结束时间不能小于预约开始时间');
			return;
		}
		if($("#paramsReserve\\.reserveEquip").val()=='0'){
			alert('是否预约设备不能为空');
			return;
		}
		if(!checkTime($("#paramsReserve\\.reserveDate1").val(),$("#paramsReserve\\.reserveDate2").val())){
			return;
		}
		$("#paramsReserve\\.reserveId").val(0);
		$("#info").attr('action','Admin_addReserve.action').submit();
		 
	 });
	 
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
		  var hour=toPair(time.getHours());
		  var minute=toPair(time.getMinutes());
		  var second=toPair(time.getSeconds());
		  return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	 }
	 
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">新增会议室预约</span>
</div>
<form id="info" name="info" action="Admin_addReserve.action" method="post">   
<s:hidden id="paramsReserve.reserveId" name="paramsReserve.reserveId" value="0" /> 
<s:hidden id="paramsReserve.userId" name="paramsReserve.userId" value="%{#attr.admin.userId}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增会议室预约</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 预约注意：</td>
          <td width="65%" style="color:red">
          	 会议室每天可供预约的时间段为08:00-12:00、13:00-18:00、19:00-22:00
          </td>
        </tr>
       <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 预约会议室：</td>
          <td width="65%" >
          	<s:select id="paramsReserve.room.roomId" name="paramsReserve.room.roomId" 
          			list="#attr.rooms" listKey="roomId" listValue="%{roomNo+' '+roomName}" 
          			headerKey="0" headerValue="请选择" cssStyle="width:155px">
          	</s:select>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 预约人：</td>
          <td width="65%">
          	<s:textfield name="paramsReserve.realName" id="paramsReserve.realName" value="%{#attr.admin.realName}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 预约部门：</td>
          <td width="65%">
          	<s:textfield name="paramsReserve.userDept" id="paramsReserve.userDept" value="%{#attr.admin.userDept}"/>
          </td>
        </tr> 
       <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 预约开始时间：</td>
          <td width="65%" >
          	<s:textfield name="paramsReserve.reserveDate1" id="paramsReserve.reserveDate1" 
          			onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
          </td>
       </tr> 
       <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 预约结束时间：</td>
          <td width="65%" >
          	<s:textfield name="paramsReserve.reserveDate2" id="paramsReserve.reserveDate2" 
          			onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
          </td>
       </tr> 
       <tr>
          <td width="35%" align="right" style="padding-right:5px">会议内容：</td>
          <td width="65%" >
           	<s:textarea name="paramsReserve.meetContent" id="paramsReserve.meetContent" cssStyle="width:300px;height:60px;">
          	</s:textarea>
          </td>
       </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 是否预约设备：</td>
          <td>
            <s:select id="paramsReserve.reserveEquip" name="paramsReserve.reserveEquip" 
          			list="#{'1':'否','2':'是'}" listKey="key" listValue="value"
          			headerKey="0" headerValue="请选择" cssStyle="width:155px">
          	</s:select>
          </td> 
        </tr>
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>