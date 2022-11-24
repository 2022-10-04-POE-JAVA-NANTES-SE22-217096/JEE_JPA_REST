package fr.cleverdev.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.cleverdev.adapters.AuteurAdapter;
import fr.cleverdev.adapters.LivreAdapter;
import fr.cleverdev.models.Auteur;
import fr.cleverdev.models.Livre;

public class Utils {
	
	public static Gson getSuperJson() {
		GsonBuilder gsonBuilder = new GsonBuilder()
				.registerTypeAdapter(Auteur.class, new AuteurAdapter())
				.registerTypeAdapter(Livre.class, new LivreAdapter())
				.serializeNulls();
		
		return gsonBuilder.create();
	}
	
}
