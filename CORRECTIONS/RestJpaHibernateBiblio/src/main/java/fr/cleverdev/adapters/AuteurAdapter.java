package fr.cleverdev.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.cleverdev.models.Auteur;
import fr.cleverdev.models.Livre;

public class AuteurAdapter implements JsonSerializer<Auteur> {

	@Override
	public JsonElement serialize(Auteur auteur, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id", auteur.getId());
		json.addProperty("nom", auteur.getNom());
		json.addProperty("prenom", auteur.getPrenom());
		json.addProperty("telephone", auteur.getTelephone());
		json.addProperty("email", auteur.getEmail());

		JsonArray livresJson = new JsonArray();
		JsonObject tmp;
		for(Livre l : auteur.getLivres()) {
			tmp = new JsonObject();
			tmp.addProperty("id", l.getId());
			tmp.addProperty("titre", l.getTitre());
			livresJson.add(tmp);
		}

		json.add("livres", livresJson);

		return json;
	}

}
