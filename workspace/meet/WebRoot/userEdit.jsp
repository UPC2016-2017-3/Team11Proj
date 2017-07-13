<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.user!=null && #attr.user.userId!=0">编辑</s:if><s:else>添加</s:else>用户信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var userSex = "<s:property value='#attr.user.userSex' />";
	 if(userSex!=''){
		 $("#sex"+userSex).attr('checked','checked');
	 }else{
		 $("#sex1").attr('checked','checked');
	 }
	 
	 var num = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsUser\\.userName").val()==''){
			alert('用户名不能为空');
			return;
		}
		if($("#paramsUser\\.userPass").val()==''){
			alert('密码不能为空');
			return;
		}
		if($("#paramsUser\\.userCode").val()==''){
			alert('员工编号不能为空');
			return;
		}
		if($("#paramsUser\\.realName").val()==''){
			alert('姓名不能为空');
			return;
		}
		/*
		if($("#paramsUser\\.userAge").val()!=''){
			if(!num.exec($("#paramsUser\\.userAge").val())){
				alert('年龄必须为数字');
				return;
			}
		}else{
			$("#paramsUser\\.userAge").val(0);
		}*/
		if($("#paramsUser\\.userDept").val()==''){
			alert('部门不能为空');
			return;
		}
		$("#paramsUser\\.userId").val(0);
		$("#info").attr('action','Admin_addUser.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		if($("#paramsUser\\.userCode").val()==''){
			alert('员工编号不能为空');
			return;
		}
		if($("#paramsUser\\.realName").val()==''){
			alert('姓名不能为空');
			return;
		}
		/*
		if($("#paramsUser\\.userAge").val()!=''){
			if(!num.exec($("#paramsUser\\.userAge").val())){
				alert('年龄必须为数字');
				return;
			}
		}else{
			$("#paramsUser\\.userAge").val(0);
		}*/
		if($("#paramsUser\\.userDept").val()==''){
			alert('部门不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveUser.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">用户管理&gt;&gt;<s:if test="#attr.user!=null && #attr.user.userId!=0">编辑</s:if><s:else>新增</s:else>用户</span>
</div>
<form id="info" name="info" action="Admin_addUser.action" method="post">   
<s:hidden id="paramsUser.userId" name="paramsUser.userId" value="%{#attr.user.userId}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.user!=null && #attr.user.userId!=0">编辑</s:if><s:else>新增</s:else>用户</TD>
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
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font> 用户名：</td>
          <td width="35%" >
          	<s:if test="#attr.user!=null && #attr.user.userId!=0"><s:property value="#attr.user.userName" /></s:if>
          	<s:else><s:textfield name="paramsUser.userName" id="paramsUser.userName" value="%{#attr.user.userName}"/> </s:else>
          </td>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 密码：</td>
          <td>
            <s:if test="#attr.user!=null && #attr.user.userId!=0">
          	<s:password name="paramsUser.userPass" id="paramsUser.userPass" value=""  showPassword="true"/>
          	</s:if>
          	<s:else>
          	<s:password name="paramsUser.userPass" id="paramsUser.userPass" value="111111"  showPassword="true"/>
          	<span id="passTip" style="color:red;">初始密码：111111</span>
          	</s:else>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 员工编号：</td>
          <td>
            <s:textfield name="paramsUser.userCode" id="paramsUser.userCode" value="%{#attr.user.userCode}"/>
          </td>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 姓名：</td>
          <td>
            <s:textfield name="paramsUser.realName" id="paramsUser.realName" value="%{#attr.user.realName}"/>
          </td>
        </tr>   
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 性别：</td>
          <td colspan="3">
            <input type="radio" id="sex1" name="paramsUser.userSex" value="1"/>男&nbsp;&nbsp;
            <input type="radio" id="sex2" name="paramsUser.userSex" value="2"/>女
          </td>
          <!-- 
          <td align="right" style="padding-right:5px">年龄：</td>
          <td>
            <s:textfield name="paramsUser.userAge" id="paramsUser.userAge" value="%{#attr.user.userAge}"/>
          </td> 
           -->
        </tr>
        <tr>
          <td align="right" style="padding-right:5px">联系方式：</td>
          <td>
            <s:textfield name="paramsUser.userPhone" id="paramsUser.userPhone" value="%{#attr.user.userPhone}"/>
          </td>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 部门：</td>
          <td>
            <s:textfield name="paramsUser.userDept" id="paramsUser.userDept" value="%{#attr.user.userDept}"/>
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
          	<s:if test="#attr.user!=null && #attr.user.userId!=0">
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