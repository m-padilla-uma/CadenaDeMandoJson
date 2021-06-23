package salud.isa.gsonMedDBComplete;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Medicines extends ElementoCadenaMando {
	
	private static final String MEDICINES_TAGNAME = "medicines";
	
	private static final Object NAME_FIELD_TAGNAME = "name";

	public Medicines(ElementoCadenaMando s) {
		super(s);
		cmName = MEDICINES_TAGNAME;
	}

	// Parses the contents of a medicine.
	public String readEntry(JsonReader reader) throws IOException {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
		String medName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return medName;
	}
}
