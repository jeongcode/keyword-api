package com.sj.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KeywordHistory {
	private int id;
	private String word;
	private Timestamp createAt;
	private String relationUrls;
	private String insert_timestamp;
}

