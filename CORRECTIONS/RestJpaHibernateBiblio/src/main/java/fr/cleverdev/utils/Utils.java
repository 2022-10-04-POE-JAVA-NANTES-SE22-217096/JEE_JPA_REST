package fr.cleverdev.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.cleverdev.adapters.AuteurAdapter;
import fr.cleverdev.models.Auteur;

public class Utils {
	
	public static Gson getSuperJson() {
		GsonBuilder gsonBuilder = new GsonBuilder()
				.registerTypeAdapter(Auteur.class, new AuteurAdapter())
				.serializeNulls();
		
		return gsonBuilder.create();
	}
	
}
