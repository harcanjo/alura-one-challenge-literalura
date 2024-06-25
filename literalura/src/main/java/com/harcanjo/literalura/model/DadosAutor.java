package com.harcanjo.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
		@JsonProperty("name") String nome,
		@JsonProperty("birth_year") String nascimentoAno,
		@JsonProperty("death_year") String morteAno
		) {
}
