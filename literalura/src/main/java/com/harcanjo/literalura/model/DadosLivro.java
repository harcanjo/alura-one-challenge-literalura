package com.harcanjo.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosLivro(@JsonAlias("title") String livro,
						 @JsonAlias("authors") String autor) {
}
