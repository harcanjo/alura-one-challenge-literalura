package com.harcanjo.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
		@JsonProperty("results") List<ResultadoLivro> resultados) {
}
