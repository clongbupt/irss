<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改邮箱信息</title>
    
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
				   <s:form method="post" action="Modify!modifyinfo.action" >
				       <s:textfield name="email" label="邮箱" />
					   <s:password name="email_password" label="邮箱密码" />
			           <s:submit value="修改信息" align="center" type="button"/>
			       </s:form>
    </div> 
</div>
  </body>
</html>

