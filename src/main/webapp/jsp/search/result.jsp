<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="../common/header.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
	$().piroBox_ext({
		piro_speed : 700,
		bg_alpha : 0.5,
		piro_scroll : true // pirobox always positioned at the center of the page

		
	});
});
</script>


<div id="search" class="grid_12">

	<div class="search_box">
		<span class="search_left">搜索：<strong><s:property value="keyword" /></strong> </span> 
		<span class="search_right">结果：<strong><s:property value="feedItemList.size" /></strong>条</span>
	</div>
	<s:if test="feedItemList.size==0">
		<div class="content_box">
			<h2>
				<strong>sorry！</strong>您的阅读记录中没有任何关于<strong>“<s:property value="keyword" />”</strong>的搜索结果
			</h2>
		</div>
	</s:if>
	<s:iterator value="feedItemList" id="row" status="index">

		<div class="content_box">
				
			<h2 class="title"> <a id="article_title_<s:property value="#index.getIndex()"></s:property>" href="#inline<s:property value="#index.getIndex()"></s:property>" rel="inline-900-500"  class="pirobox_gall1" title="Inline content"><s:property value="#row.feedItemTitle" /></a></h2>
			<div class="entry">
				<p >
					...<s:property value="#row.feedItemDescription" escape="false"/>...
				</p>
			</div>
			
			<div id="article_summary_<s:property value="#index.getIndex()"></s:property>" style="display:none;"><s:property value="#row.feedItemDescription.substring(2,200)+'...'"></s:property></div>
			<div id="feedItemId_<s:property value="#index.getIndex()"></s:property>" style="display:none;"><s:property value="#row.feedItemId"></s:property></div>
			
			
			<div class="inline" id="inline<s:property value="#index.getIndex()"></s:property>" style="display:none; background:white;">
				<h2 class="h2"><s:property value="#row.feedItemTitle" /></h2>
				<div class="entry">
					<s:property value="#row.feedItemContent" escape="false" />	
				</div>
			</div>

			<div class="post_info_t">
				<span class="p_author">By <s:property value="#row.feedItemAuthor" /></span>
				<span class="p_normal">发表于<abbr class="timeago"><s:property value="#row.feedItemPubDate" /></abbr></span>
			    <span class="p_site">文章源自 <a class="website"
					href="<s:property value="#row.feedItemUrl"/>" ><s:property value="feedTitleList[#index.getIndex()]" /></a></span>
			</div>

		</div>
	</s:iterator>

</div>
<!-- end of content-->

<%@ include file="../common/footer.jsp"%>

<!-- JiaThis Button BEGIN -->
	<script type="text/javascript" >
	var jiathis_config={
		url:"http://59.64.131.41/irss/read.action?feedItemId="+ document.getElementById("feedItemId_1").innerHTML,
		title:document.getElementById("article_title_1").innerHTML,
		summary:document.getElementById("article_summary_1").innerHTML,
		marginTop:250,
		showClose:true,
		hideMore:false
	}
	</script>
	<script type="text/javascript" src="http://v2.jiathis.com/code/jiathis_r.js?type=left&btn=l5.gif&move=0" charset="utf-8"></script>
	<!-- JiaThis Button END -->