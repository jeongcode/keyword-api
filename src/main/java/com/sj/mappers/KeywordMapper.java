package com.sj.mappers;

import java.util.List;

import com.sj.model.Keyword;
import com.sj.model.KeywordHistory;

public interface KeywordMapper {
	
	List<Keyword> getKeywords();

	String getRelatedKeyword(String keyword);

	List<KeywordHistory> getRelatedKeywordHistory(String keyword);

	int insertWord(String keyword);

	int updateWord(String keyword_id);
}
