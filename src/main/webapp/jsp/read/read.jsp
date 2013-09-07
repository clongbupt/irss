<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<%@ include file="../common/header.jsp" %>
	
		<div id="content" class="grid_12">
		
			<div class="content_box">
				<h2 class="title" id="article_title"><s:property value="feedItem.feedItemTitle" /></h2>
				
				<div class="post_info_t">
					<span class="p_author"><s:property value="feedItem.feedItemAuthor" /></span>
					<span class="p_normal" >发表于 <abbr class="timeago"><s:property value="feedItem.feedItemPubDate" /></abbr></span>
					<span class="p_site" >文章源自<a class="website" href="<s:property value="feedItem.feedItemUrl" />"><s:property value="feedTitle"/></a></span>
				</div>
				
				<div class="entry">
					<s:property value="feedItem.feedItemContent" escape="false" />	
				</div>
			</div>
			
		</div>  <!-- end of content-->
		
		<div id="sidebar">
			<a class="like" href="read.action?level=3&number=<s:property value="%{number + 1}"/>&feedItemId=<s:property value="%{feedItem.feedItemId}"/>"></a>
			<a class="hate" href="read.action?level=1&number=<s:property value="%{number + 1}"/>&feedItemId=<s:property value="%{feedItem.feedItemId}"/>"></a>
			<a class="next" href="read.action?level=2&number=<s:property value="%{number + 1}"/>&feedItemId=<s:property value="%{feedItem.feedItemId}"/>"></a>
		</div>
		
		<div id="feedItemId" style="display:none;"><s:property value="feedItem.feedItemId"></s:property></div>
		<div id="article_summary" style="display:none;"><s:property value="feedItem.feedItemContent.substring(2,200)+'...'"></s:property></div>
		
<%@ include file="../common/footer.jsp" %>
	
	<!-- JiaThis Button BEGIN -->
	<script type="text/javascript" >
	var jiathis_config={
		url:"http://59.64.131.41/irss/read.action?feedItemId="+ document.getElementById("feedItemId").innerHTML,
		title:document.getElementById("article_title").innerHTML,
		summary:document.getElementById("article_summary").innerHTML,
		marginTop:250,
		showClose:true,
		hideMore:false
	}
	</script>
	<script type="text/javascript" src="http://v2.jiathis.com/code/jiathis_r.js?type=left&btn=l5.gif&move=0" charset="utf-8"></script>
	<!-- JiaThis Button END -->	