package com.harcanjo.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosBuscaLivro(
		@JsonAlias("results") List<DadosLivro> resultados
		) {
}
