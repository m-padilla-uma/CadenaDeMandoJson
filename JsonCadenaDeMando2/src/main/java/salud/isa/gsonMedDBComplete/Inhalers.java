package salud.isa.gsonMedDBComplete;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Inhalers extends ElementoCadenaMando {
	
	private static final String INHALERS_TAGNAME = "inhalers";

	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String FIELD_SEPARATOR = ";";
	
	public Inhalers(ElementoCadenaMando s) {
		super(s);
		cmName = INHALERS_TAGNAME;
	}
	
	public String readEntry(JsonReader reader) throws IOException {
	
		String inhalersName = null;
		String inhalersImage = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				inhalersName = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				inhalersImage = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return inhalersName + FIELD_SEPARATOR + inhalersImage;
	}

}
