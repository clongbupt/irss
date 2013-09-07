<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改密码</title>
    <script type="text/javascript">
      function checkPwd(){
     //该javascript函数是用来验证表单格式的正确性的
		fr = document.lzform;
		if(fr.oldpwd.value=="")
		{
			alert("原密码不能为空！");
			fr.oldpwd.focus();
			return;
		}
		if((fr.password0.value != "") && (fr.password1.value != ""))//两次密码输入必须一致
		{
			if(fr.password1.value!=fr.password0.value)
			{
				alert("密码不一致,请重新输入并验证密码！");
				fr.password1.focus();
				return;
			}
		}
		else {//密码也不能为空
			alert("密码不能为空！");
			fr.password0.focus();
			return;
		}
	    fr.submit();	
	}
    </script>
    
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
  <body>
<div id="container">
       <div id="content"> 
    	<s:property value="#session.user.username"/>的帐号
				    <s:if test="hasErrors()">
			            <font color="red">
			            ERROR:<s:actionerror/>
			            <s:fielderror />
			            </font>
		            </s:if>
				       
					    <form name="lzform" method="post" action="Modify!modifypwd.action">
						    你的当前密码:<input name="oldpwd" type="password" size="10" maxlength="20"/><br/><br/> 
						    你的新口令(英文字母，符号或数字):<input name="password0" type="password" size="10" maxlength="20" value=""/><br/><br/> 
						    再输一次:<input name="password1" type="password" size="10" maxlength="20" value=""/><br/><br/> 
						    <input class="butt" type="button" value="确认修改密码" onClick="checkPwd()" /> 
					    </form> 
   
			</div> 
	       
	    </div> 

  </body>
</html>
