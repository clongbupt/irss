<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="favicon.ico" />
<!-- 更改地址栏图标 -->
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/index.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/min/reset.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/min/text.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/min/960.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/min/demo.css" />
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery.validate.js"
	charset="utf-8"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.lazyload.min.js" charset="utf-8"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style_1/style.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.8.2.custom.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/pirobox_extended_min.js" charset="utf-8"></script>

<link href="<%=request.getContextPath() %>/js/google-code-prettify/prettify.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/google-code-prettify/prettify.js"></script>

<title>随心而阅--RSS个性阅读</title>
</head>
<body>
	
	<script type="text/javascript">
		//图片延迟载入
		jQuery(document).ready(
			function($){
				//加载图片前的占位图片
			$("img").lazyload({
	     		placeholder : "<%=request.getContextPath() %>/images/grey.gif", 
	     		effect      : "fadeIn" 
			}); //加载图片使用的效果(淡入)
		});
		
		
		//实现代码高亮 
		$(document).ready(function(){
			$("pre").attr("class","prettyprint");
			prettyPrint();
		});
</script>

	<div id="wrapper" class="container_12">
		<div id="header" class="grid_12">
			<div id="logo">
				<a href="read.action"><img
					src="<%=request.getContextPath() %>/images/logo_small.png"
					alt="Read The Best RSS of Internet" />
				</a>

				<!-- 用于书写用户登录状态，如果没有登录，为注册、登录；如果已经登录，填写**，欢迎您 -->
				<div id="userForm">
					<s:if test="#session.userid==1 || #session.userid==null">
						<a href="login.action">登录</a> |<a href="register.action">注册</a>
					</s:if>
					<s:else>
						<span><s:property value="#session.username" />，欢迎您！</span>|<a
							href="logout.action">退出</a>
					</s:else>
				</div>

				<s:if test="#session.userid!=1 && #session.userid!=null">
					<div id="searchForm">
						<form method="post" action="search" name="search">
							<input type="text" size="13" name="keyword" value="" /> 
							<input class="button" type="submit" value="" />
						</form>
					</div>
				</s:if>
			</div>

		</div>