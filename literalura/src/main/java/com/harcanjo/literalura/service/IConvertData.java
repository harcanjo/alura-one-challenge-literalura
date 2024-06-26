package com.harcanjo.literalura.service;

public interface IConvertData {

	<T> T getData(String json, Class<T> classe);
}
