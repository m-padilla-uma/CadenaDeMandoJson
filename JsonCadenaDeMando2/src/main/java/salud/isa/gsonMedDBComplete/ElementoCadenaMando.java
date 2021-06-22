package salud.isa.gsonMedDBComplete;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ElementoCadenaMando {
	
	protected ElementoCadenaMando sig;
	protected String cmName;
	
	public ElementoCadenaMando(ElementoCadenaMando s) {
		sig = s;
	}
	
	public StringBuffer read(JsonReader reader, String name) throws IOException {
		if (name.equals(cmName)) {
			StringBuffer objectData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				objectData.append(readEntry(reader)).append("\n");
				reader.endObject();
			}
			objectData.append("\n");
			reader.endArray();
			return objectData;
		} else {
			return sig.read(reader, name);
		}
	}
	public String readEntry(JsonReader reader) throws IOException{
		return sig.readEntry(reader);
	}
}