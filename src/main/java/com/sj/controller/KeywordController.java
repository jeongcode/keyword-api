package com.sj.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sj.model.Keyword;
import com.sj.model.KeywordHistory;
import com.sj.service.KeywordService;
import com.sj.service.OpenSearchService;

//@CrossOrigin(origins = "*") 
@RestController
public class KeywordController {
	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private OpenSearchService opensearchService;
	
	@RequestMapping(value = "/words", method = RequestMethod.GET)
	public ResponseEntity<List<Keyword>> getWords() {
		List<Keyword> list = keywordService.getKeywords();
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value = "/words", method = RequestMethod.POST)
	public ResponseEntity<Integer> postWord(
			@RequestBody String keyword) {
		int success = keywordService.postWord(keyword);
		return ResponseEntity.ok(success);
	}
	
	@RequestMapping(value = "/words", method = RequestMethod.PUT)
	public ResponseEntity<Integer> putWord(
			@RequestBody String keyword_id) {
		int success = keywordService.putWord(keyword_id);
		return ResponseEntity.ok(success);
	}
	
	@RequestMapping(value = "/related/words", method = RequestMethod.GET)
	public ResponseEntity<String> getRelatedWords(
			@RequestParam(name = "keyword", required = true) String keyword) {
		
		String relatedKeywordsInfo = keywordService.getRelatedKeyword(keyword);
		return ResponseEntity.ok(relatedKeywordsInfo);
	}
	
	@RequestMapping(value = "/related/words/history", method = RequestMethod.GET)
	public ResponseEntity<List<KeywordHistory>> getRelatedWordsHistoryList(
			@RequestParam(name = "keyword", required = true) String keyword) {
		
		List<KeywordHistory> relatedKeywordsHistory = keywordService.getRelatedKeywordHistory(keyword);
		return ResponseEntity.ok(relatedKeywordsHistory);
	}
	
	@RequestMapping(value = "/opensearch/index", method = RequestMethod.GET)
	public ResponseEntity<?> getOpensearchIndex(
			@RequestParam(name = "keyword", required = true) String keyword) {
		
		List<String> list = new ArrayList<>();
		try {
			list = opensearchService.searchDocuments("kakao_*", keyword);
			return ResponseEntity.ok(list);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("OpenSearch 조회 중 에러: " + e.getMessage());
		}
	}
	
	@RequestMapping(value = "/opensearch/index", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteOpensearchIndex() {
		
		Boolean acknowledged = false;
		acknowledged = opensearchService.deleteIndex("kakao_*");
		return ResponseEntity.ok(acknowledged);
	}
}