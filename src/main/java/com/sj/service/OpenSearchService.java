package com.sj.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface OpenSearchService {
	List<String> searchDocuments(String index, String keyword) throws IOException;

	Boolean deleteIndex(String string);
}
