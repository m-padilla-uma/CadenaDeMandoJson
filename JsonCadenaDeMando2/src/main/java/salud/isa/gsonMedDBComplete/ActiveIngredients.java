package salud.isa.gsonMedDBComplete;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ActiveIngredients extends ElementoCadenaMando {
	
	private static final String ACTIVEINGREDIENTES_TAGNAME = "activeIngredients";
	
	private static final String NAME_FIELD_TAGNAME = "name";
	
	public ActiveIngredients(ElementoCadenaMando s) {
		super(s);
		cmName = ACTIVEINGREDIENTES_TAGNAME;
	}	
	
	// Parses the contents of a medicine.
	public String readEntry(JsonReader reader) throws IOException {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
		String activeIngredientsName = null;
		while (reader.hasNext()) {
			//No cumple principio abierto cerrado
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				activeIngredientsName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return activeIngredientsName;
	}
}
