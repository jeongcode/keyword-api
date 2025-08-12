package com.sj.service;

import java.util.List;

import com.sj.model.Keyword;
import com.sj.model.KeywordHistory;

public interface KeywordService {

	List<Keyword> getKeywords();

	String getRelatedKeyword(String keyword);

	List<KeywordHistory> getRelatedKeywordHistory(String keyword);

	int postWord(String keyword);

	int putWord(String keyword_id);

}
