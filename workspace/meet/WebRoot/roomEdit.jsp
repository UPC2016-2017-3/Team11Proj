<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.room!=null && #attr.room.roomId!=0">编辑</s:if><s:else>添加</s:else>会议室信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsRoom\\.roomNo").val()==''){
			alert('区域不能为空');
			return;
		}
		if($("#paramsRoom\\.roomName").val()==''){
			alert('会议室名称不能为空');
			return;
		}
		if(!num.exec($("#paramsRoom\\.roomPerson").val())){
			alert('容纳人数必须为数字');
			return;
		}
		$("#paramsRoom\\.roomId").val(0);
		$("#info").attr('action','Admin_addRoom.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
	    if($("#paramsRoom\\.roomNo").val()==''){
			alert('区域不能为空');
			return;
		}
		if($("#paramsRoom\\.roomName").val()==''){
			alert('会议室名称不能为空');
			return;
		}
		if(!num.exec($("#paramsRoom\\.roomPerson").val())){
			alert('容纳人数必须为数字');
			return;
		}
		$("#info").attr('action','Admin_saveRoom.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">会议室管理&gt;&gt;<s:if test="#attr.room!=null && #attr.room.roomId!=0">编辑</s:if><s:else>新增</s:else>会议室</span>
</div>
<form id="info" name="info" action="Admin_addRoom.action" method="post">   
<s:hidden id="paramsRoom.roomId" name="paramsRoom.roomId" value="%{#attr.room.roomId}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.room!=null && #attr.room.roomId!=0">编辑</s:if><s:else>新增</s:else>会议室</TD>
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 区域：</td>
          <td width="65%">
          	<s:textfield name="paramsRoom.roomNo" id="paramsRoom.roomNo" value="%{#attr.room.roomNo}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 会议室名称：</td>
          <td width="65%">
          	<s:textfield name="paramsRoom.roomName" id="paramsRoom.roomName" value="%{#attr.room.roomName}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 容纳人数：</td>
          <td width="65%">
          	<s:textfield name="paramsRoom.roomPerson" id="paramsRoom.roomPerson" value="%{#attr.room.roomPerson}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px">备注：</td>
          <td width="65%">
          	<s:textarea name="paramsRoom.roomNote" id="paramsRoom.roomNote" value="%{#attr.room.roomNote}" cssStyle="width:300px;height:60px;">
          	</s:textarea>
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
          	<s:if test="#attr.room!=null && #attr.room.roomId!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
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