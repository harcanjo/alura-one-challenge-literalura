package com.harcanjo.literalura.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksData(
		String title,
		@JsonAlias("authors") List<AuthorData> author,
		@JsonAlias("languages") Set<String> language,
		@JsonAlias("download_count") String downloadCount) {
}
