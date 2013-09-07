<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<%@ include file="../common/header.jsp" %>

<script type="text/javascript">

var time = 2; //时间,秒
function Redirect(){
    window.location = "<%=request.getContextPath() %>/read.action";
}
var i = 0;
function dis(){
    document.all.s.innerHTML = "还剩" + (time - i) + "秒";
    i++;
}
timer=setInterval('dis()', 1000); //显示时间
timer=setTimeout('Redirect()',time * 1000); //跳转
</script>

<div id="success" class="grid_10 prefix_1 suffix_1">
	
		<h2><s:property value="#session.username"></s:property>,恭喜您注册成功！</h2>
		<h2>您可以前往邮箱激活账号或者修改密码。</h2>
		<h2>页面将于3秒后自动跳转至首页。</h2>
		<h2 id="s"></h2>
		
</div>

<%@ include file="../common/footer.jsp" %>