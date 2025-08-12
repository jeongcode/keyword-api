package com.sj.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch._types.OpenSearchException;
import org.opensearch.client.opensearch._types.query_dsl.Query;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.opensearch.client.opensearch.indices.DeleteIndexResponse;
import org.springframework.stereotype.Service;

import com.sj.service.OpenSearchService;

@Service
public class OpenSearchServiceImpl implements OpenSearchService {
	
	private final OpenSearchClient openSearchClient;

    public OpenSearchServiceImpl(OpenSearchClient openSearchClient) {
        this.openSearchClient = openSearchClient;
    }
	
	/**
     * 검색 쿼리를 이용해 여러 도큐먼트 조회 예제 (keyword 필드에 특정 값이 포함된 도큐먼트 전체)
     * @param index 인덱스명
     * @param keyword 검색할 키워드 값
     * @return 도큐먼트 리스트 (간단히 JSON 문자열로 반환)
     */
    
    @Override
    public List<String> searchDocuments(String index, String keyword) throws IOException {
        SearchRequest searchRequest = SearchRequest.of(b -> b
                .index(index)
                .query(Query.of(q -> q
                    .match(m -> m
                        .field("keyword")  // 검색하고자 하는 필드명
                        .query(FieldValue.of(keyword))
                    )
                ))
                .size(100) // 최대 100건까지 반환
        );

        SearchResponse<Object> response = openSearchClient.search(searchRequest, Object.class);

        // 검색 결과에서 도큐먼트(_source)만 추출해서 리스트로 반환
        return response.hits().hits().stream()
                .map(Hit::source)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

	@Override
	public Boolean deleteIndex(String indexName) {
		DeleteIndexResponse response = null;
		try {
			response = openSearchClient.indices().delete(d -> d.index(indexName));
		} catch (OpenSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.acknowledged();
	}
}
