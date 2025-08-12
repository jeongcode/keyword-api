package com.sj.config;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.core5.http.HttpHost;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.httpclient5.ApacheHttpClient5TransportBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenSearchConfig {
	
		@Bean(destroyMethod = "close")
		public OpenSearchTransport openSearchTransport() {
			HttpHost[] hosts = { new HttpHost("https", "localhost", 9200) };

			BasicCredentialsProvider credentials = new BasicCredentialsProvider();
			credentials.setCredentials(
				new AuthScope("localhost", 9200),
				new UsernamePasswordCredentials("admin", "W8z$kVp2Qa1!".toCharArray())
			);

			return ApacheHttpClient5TransportBuilder
				.builder(hosts)
				.setMapper(new JacksonJsonpMapper())
				.setHttpClientConfigCallback(httpClientBuilder ->
					httpClientBuilder.setDefaultCredentialsProvider(credentials)
				)
				.build();
		}

		@Bean
		public OpenSearchClient openSearchClient(OpenSearchTransport transport) {
			return new OpenSearchClient(transport);
		}

}
