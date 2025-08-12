package com.sj.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.mappers.KeywordMapper;
import com.sj.model.Keyword;
import com.sj.model.KeywordHistory;
import com.sj.service.KeywordService;

@Service
public class KeywordServiceImpl implements KeywordService {

	@Autowired
	private KeywordMapper keywordMapper;

	@Override
	public List<Keyword> getKeywords() {
		// TODO Auto-generated method stub
		return keywordMapper.getKeywords();
	}

	@Override
	public String getRelatedKeyword(String keyword) {
		// TODO Auto-generated method stub
		return keywordMapper.getRelatedKeyword(keyword);
	}

	@Override
	public List<KeywordHistory> getRelatedKeywordHistory(String keyword) {
		return keywordMapper.getRelatedKeywordHistory(keyword);
	}

	@Override
	public int postWord(String keyword) {
		return keywordMapper.insertWord(keyword);
	}

	@Override
	public int putWord(String keyword_id) {
		return keywordMapper.updateWord(keyword_id);
	}

}
