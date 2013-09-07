<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<%@ include file="../common/header.jsp" %>

<script type="text/javascript">  
$(document).ready(function() {
	// validate signup form on keyup and submit
	var validator = $("#loginForm").validate({
		rules: {
			userName: {
				required: true,
				rangelength: [4,20],
				remote: "validate_loginUserName.action"
			},
			password: {
				required: true,
				rangelength: [6,15]
			}
		},
		messages: {
			userName: {
				required: "请输入您的用户名",
				rangelength: "用户名长度必须在4～20位之间",
				remote: jQuery.format("用户名 {0} 不存在，请重新输入！")
			},
			password: {
				required: "请输入密码",
				rangelength: "密码长度必须在6～15位之间"
			}
		},

		// the errorPlacement has to take the table layout into account
		errorPlacement: function(error, element) {
			error.appendTo( element.parent().next() );
		},
		// set this class to error-labels to indicate valid fields
		success: function(label) {
		// set &nbsp; as text for IE
			label.html("&nbsp;").addClass("checked");
		}
	});  
});	
</script> 

<div id="login" class="grid_10 prefix_1 suffix_1">
	
		<h2>登录</h2>
		<form id="loginForm" name="login" method="post" action="login">
			<p class="label">
					<label for="username">
						<font color="red" size="2">*</font>用户账户:
					</label>
			</p>
			
			<p class="input"><!-- 用户名和邮箱都作为用户账户 -->
				<input type="text"  tabindex="1" size="21" name="userName" value=""/>		
			</p>
			
			<p class="status"></p>
			
			<p class="label">
				<label for="password">
					<font color="red" size="2">*</font>登录密码:
				</label>
			</p>

			<p class="input">
				<input type="password"  tabindex="1" size="21" name="password" value=""/>	
			</p>
			
			<p class="status"></p>

			<p class="submit">
				<input id="submit" class="button" type="submit" value="立&nbsp;&nbsp;即&nbsp;&nbsp;登&nbsp;&nbsp;录" tabindex="5" name="submit" />
			</p>
	
		</form>
	</div>

<%@ include file="../common/footer.jsp" %>