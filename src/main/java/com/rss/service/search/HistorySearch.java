package com.rss.service.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.rss.dao.FeedItemDAO;
import com.rss.dao.UserReadItemDAO;
import com.rss.pojos.FeedItem;

public class HistorySearch {

	private FeedItemDAO feedItemDAO;
	private UserReadItemDAO userReadItemDAO;  

	static private Directory indexDir = new RAMDirectory();
	private Map<String,FeedItem>feedItemMap;


	//获取用户的阅读记录
	@SuppressWarnings("unchecked")
	public List<FeedItem> GetHistory(int userId){

		System.out.println("userId : " + userId);

		List<Integer>feedItemIdList = (List<Integer>)userReadItemDAO.findFeedItemIdByUserId(userId);
		List<FeedItem>feedItemList = new ArrayList<FeedItem>();
		if (feedItemIdList != null){
			for(Integer feedItemId : feedItemIdList){
				System.out.println("feedItemId : " + feedItemId);
				feedItemList.add(feedItemDAO.findById(feedItemId));
			}
		}
		return feedItemList;
	}

	@SuppressWarnings("deprecation")
	//建立索引
	public void buildIndex(int userId) { 
		
		feedItemMap = new HashMap<String,FeedItem>();//初始化feedItemMap
		
		try { 
			//System.out.println("here");
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);  

			IndexWriter writer = new IndexWriter(indexDir, analyzer, true, IndexWriter.MaxFieldLength.LIMITED);  
			long beginTime = new Date().getTime(); 

			List<FeedItem>feedItemList = GetHistory(userId);//获取用户的阅读记录

			System.out.println("success : " + feedItemList.size());

			for(FeedItem feedItem : feedItemList){

				//System.out.println(feedItem.getFeedItemTitle());
				String id = String.valueOf(feedItem.getFeedItemId());
				feedItemMap.put(id,feedItem);


				Document d = new Document();

				Field feedItemTitle = new Field("feedItemTitle",feedItem.getFeedItemTitle(),
						Field.Store.YES, Field.Index.ANALYZED);
				Field feedItemId = new Field("feedItemId",id,
						Field.Store.YES, Field.Index.NOT_ANALYZED);
				Field feedItemContent = new Field("feedItemContent",feedItem.getFeedItemDescription(),
						Field.Store.YES, Field.Index.ANALYZED);
				d.add(feedItemContent);
				d.add(feedItemId);
				d.add(feedItemTitle);
				writer.addDocument(d);
			}
			writer.close();  
			long endTime = new Date().getTime();  
			System.out.println("消耗时间： " + (endTime - beginTime) + " 毫秒");  

		} catch (Exception ex) {  
			ex.printStackTrace();    
		}  
	}  

	
	//实现搜索，返回FeedItem对象的列表
	public List<FeedItem> search(int userId, String keyword) throws Exception {  

		buildIndex(userId);//建立索引
		
		List<FeedItem> list = new ArrayList<FeedItem>();  
		
		if(feedItemMap.isEmpty())return list;
		
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);  

		IndexSearcher searcher = new IndexSearcher(indexDir);  

		final String[] field_list = {"feedItemContent", "feedItemTitle","feedItemId"};  
		if (keyword == null || keyword.length()==0) {  
			return list;  
		}  

		QueryParser multi_parser = new MultiFieldQueryParser(Version.LUCENE_CURRENT, field_list, analyzer);
		Query query = multi_parser.parse(keyword);  
		 
		TopScoreDocCollector collector = TopScoreDocCollector.create(1000, true);  
		searcher.search(query, collector);  
		ScoreDoc[] hits = collector.topDocs().scoreDocs;   //获得选中结果  

		//高亮关键字  
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<strong><font color='red'>", "</font></strong>");  
		Highlighter highlighter = new Highlighter(formatter, new QueryScorer(query));  
		highlighter.setTextFragmenter(new SimpleFragmenter(200));  

		//搜索出结果，并封装到bean中，  
		for (int i = 0; i < hits.length; ++i) {
			//找到这个Document原来的索引值  
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);//查找原文档  
			String text = d.get("feedItemContent");
			TokenStream tokenStream = TokenSources.getAnyTokenStream(searcher.getIndexReader(), docId, "feedItemContent", analyzer);  
			TextFragment[] frag = highlighter.getBestTextFragments(tokenStream, text, false, 50); 
			//提取简介
			String brief = "";  
			for (int j = 0; j < frag.length; j++) {  
				if ((frag[j] != null && (frag[j].getScore() > 0))) {  
					brief += frag[j].toString();
				}  
			}
			System.out.println("docId : " + docId);

			String feedItemId = d.get("feedItemId");

			FeedItem item = feedItemMap.get(feedItemId);
			item.setFeedItemDescription(brief);
			System.out.println("feedItemId : " + item.getFeedItemId());
			System.out.println("Title : " + item.getFeedItemTitle());
			list.add(item);  
			if (i > 98) {  
				break;  
			}  
		}  
		searcher.close();  
		// if(list.size()>200) return (ArrayList<DocDetailBean>) list.subList(0, 199);  
		return list;  
	} 

	public void setFeedItemDAO(FeedItemDAO feedItemDAO) {
		this.feedItemDAO = feedItemDAO;
	}

	public FeedItemDAO getFeedItemDAO() {
		return feedItemDAO;
	}
	public UserReadItemDAO getUserReadItemDAO() {
		return userReadItemDAO;
	} 

	public void setUserReadItemDAO(UserReadItemDAO userReadItemDAO) {
		this.userReadItemDAO = userReadItemDAO;
	}
}
