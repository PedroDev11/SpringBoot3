package com.course.login.util;

import com.google.gson.Gson;

public class GsonUtils {
	
	public static String serialize(Object src) {
		Gson gson = new Gson();
		return gson.toJson(src); // Formato String Json
	}
	
	// Convertir de un String a una clase a un objeto
	public static <D> D toObject(String json, Class<D> dClass) {
		Gson gson = new Gson();
		// De esa clase es donde hace la conversion, donde hace el automapeo (Automapper)
		return gson.fromJson(json, dClass);
	}
	
	public static <D> D toObject(Object src, Class<D> dClass) {
		Gson gson = new Gson();
		// Serealiza el json
		String srcJson = gson.toJson(src);
		
		// Lo convierte en otra clase
		return gson.fromJson(srcJson, dClass);
	}

}
