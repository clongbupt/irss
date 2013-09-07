<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<%@ include file="../common/header.jsp" %>

<script type="text/javascript">  
$(document).ready(function() {
	// validate signup form on keyup and submit
	var validator = $("#registerForm").validate({
		rules: {
			userName: {
				required: true,
				rangelength: [4,20],
				remote: "validate_registerUserName.action"
			},
			password: {
				required: true,
				rangelength: [6,15]
			},
			password2: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true,
				remote: "validate_registerEmail.action"
			},
		},
		messages: {
			userName: {
				required: "请输入您的用户名",
				rangelength: "用户名长度必须在4～20位之间",
				remote: jQuery.format("用户名 {0} 已经存在，请重新输入！")
			},
			password: {
				required: "请输入一次",
				rangelength: "密码长度必须在6～15位之间"
			},
			password2: {
				required: "请再次输入密码",
				equalTo: "两次输入密码不相同"
			},
			email: {
				required: "请输入您的邮箱地址",
				email: "请输入正确格式的邮箱地址",
				remote: jQuery.format("邮箱地址{0}已经被使用！")
			},
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
	
		<h2>注册</h2>
		<form id="registerForm" name="register" method="post" action="register">
			
			<p class="label">
					<label for="username">
						<font color="red" size="2">*</font>别人将称呼您:
					</label>
			</p>
			<p class="input"><!-- 用户名 -->
				<input type="text"  tabindex="1" size="21" name="userName" id="userName" value=""/>		
			</p>
			<p class="status"></p>
			
			
			<p class="label">
					<label for="email">
						<font color="red" size="2">*</font>您常用的邮箱:
					</label>
			</p>
			<p class="input"><!-- 邮箱 -->
				<input type="text"  tabindex="1" size="21" name="email" id="email" value=""/>		
			</p>
			<p class="status"></p>
			
			
			<p class="label">
				<label for="password">
					<font color="red" size="2">*</font>您在本站的密码:
				</label>
			</p>
			<p class="input">
				<input type="password"  tabindex="1" size="21" name="password" id="password" value=""/>	
			</p>
			<p class="status"></p>
			
			
			<p class="label">
				<label for="password2">
					<font color="red" size="2">*</font>确认密码:
				</label>
			</p>
			<p class="input">
				<input type="password"  tabindex="1" size="21" name="password2" id="password2" value=""/>	
			</p>
			<p class="status"></p>
			

			<p class="submit">
				<input id="submit" class="button" type="submit" value="立&nbsp;&nbsp;即&nbsp;&nbsp;注&nbsp;&nbsp;册" tabindex="5" name="submit" />
			</p>
	
		</form>
	</div>

<%@ include file="../common/footer.jsp" %>