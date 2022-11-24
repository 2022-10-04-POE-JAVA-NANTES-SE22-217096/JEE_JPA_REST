package fr.cleverdev.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.cleverdev.models.Genre;
import fr.cleverdev.models.Livre;

public class LivreAdapter implements JsonSerializer<Livre> {

	@Override
	public JsonElement serialize(Livre livre, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		
		json.addProperty("id", livre.getId());
		json.addProperty("titre", livre.getTitre());
		json.addProperty("nbPages", livre.getNbPages());
		json.addProperty("categorie", livre.getCategorie());
		
		JsonObject auteur = null;
		if(livre.getAuteur() != null) {
			auteur = new JsonObject();
			auteur.addProperty("id", livre.getAuteur().getId());
			auteur.addProperty("nom", livre.getAuteur().getNom());
			auteur.addProperty("prenom", livre.getAuteur().getPrenom());
		}
		json.add("auteur", auteur);
		
		JsonObject couverture = null;
		if(livre.getCouverture() != null) {
			couverture = new JsonObject();
			couverture.addProperty("id", livre.getCouverture().getId());
		}
		json.add("couverture", couverture);
		
		JsonArray genres = new JsonArray();
		JsonObject tmp;
		for(Genre g : livre.getGenres()) {
			tmp = new JsonObject();
			tmp.addProperty("id", g.getId());
			tmp.addProperty("nom", g.getNom());
			genres.add(tmp);
		}
		
		json.add("genres", genres);
		
		return json;
	}

	
	
}
