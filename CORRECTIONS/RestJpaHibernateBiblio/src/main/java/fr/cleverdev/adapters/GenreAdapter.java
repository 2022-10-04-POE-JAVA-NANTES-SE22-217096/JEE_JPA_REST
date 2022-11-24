package fr.cleverdev.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.cleverdev.models.Genre;
import fr.cleverdev.models.Livre;

public class GenreAdapter implements JsonSerializer<Genre> {

	@Override
	public JsonElement serialize(Genre arg0, Type arg1, JsonSerializationContext arg2) {
		JsonObject json = new JsonObject();
		json.addProperty("id", arg0.getId());
		json.addProperty("nom", arg0.getNom());

		JsonArray livresJson = new JsonArray();
		JsonObject livreJson;
		for(Livre livre : arg0.getLivres()) {
			livreJson = new JsonObject();
			livreJson.addProperty("id", livre.getId());
			livreJson.addProperty("titre", livre.getTitre());

			livresJson.add(livreJson);
		}

		json.add("livres", livresJson);

		return json;
	}



}
