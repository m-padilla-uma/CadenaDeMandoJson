package salud.isa.gsonMedDBComplete;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Posologies extends ElementoCadenaMando {

	private static final String POSOLOGIES_TAGNAME = "posologies";
	
	private static final String DESC_FIELD_TAGNAME = "description";

	public Posologies(ElementoCadenaMando s) {
		super(s);
		cmName = POSOLOGIES_TAGNAME;
	}
	
	public String readEntry(JsonReader reader) throws IOException {
	
		String posName = null;
		while (reader.hasNext()) {
			//No cumple principio abierto cerrado
			String name = reader.nextName();
			if (name.equals(DESC_FIELD_TAGNAME)) {
				posName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return posName;
	}

}
