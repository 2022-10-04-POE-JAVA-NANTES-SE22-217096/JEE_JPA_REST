package fr.cleverdev.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.cleverdev.models.Couverture;

public class CouvertureAdapter implements JsonSerializer<Couverture> {

	@Override
	public JsonElement serialize(Couverture arg0, Type arg1, JsonSerializationContext arg2) {
		JsonObject json = new JsonObject();
		json.addProperty("id", arg0.getId());
		json.addProperty("description", arg0.getDescription());

		if(arg0.getLivre() != null)
		{
			JsonObject livreJson = new JsonObject();
			livreJson.addProperty("id", arg0.getLivre().getId());
			livreJson.addProperty("titre", arg0.getLivre().getTitre());

			json.add("livre", livreJson);
		}


		return json;
	}



}
