package salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";
	private static final String PHYSIO_TAGNAME = "physiotherapies";
	private static final String MEDPRES_TAGNAME = "medicinePresentations";

	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String POSREF_FIELD_TAGNAME = "posologyRef";

	private static final String FIELD_SEPARATOR = ";";

	public DatabaseJSonReader() {
	}

	public String parse(String jsonFileName) throws IOException {

		InputStream usersIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

		//Después de la llave de inicio
		reader.beginObject();
		StringBuffer readData = new StringBuffer();
		while (reader.hasNext()) {
			String name = reader.nextName();

			//Switch que no cuple el principio abierto (a la extensión)-cerrado (a la ampliación)
			if (name.equals(MEDICINES_TAGNAME)) {
				readData.append(readMedicines(reader)).append("\n");
			} else if (name.equals(RESCUEMEDPRES_TAGNAME)) {
				readData.append(readRescueMedicinePresentations(reader)).append("\n");
			} else if (name.equals(PHYSIO_TAGNAME)){
				readData.append(readPhysiotherapies(reader)).append("\n");
			} else if (name.equals(MEDPRES_TAGNAME)){
				readData.append(readMedicinePresentations(reader)).append("\n");
			} else {
				reader.skipValue();
				System.err.println("Category " + name + " not processed.");
			}
		}

		//Antes de la llave de cierre
		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);
	}

	// Parses the contents of a medicine list.
	private StringBuffer readMedicines(JsonReader reader) throws IOException {
		StringBuffer medicineData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			medicineData.append(readMedicineEntry(reader)).append("\n");
			reader.endObject();
		}
		medicineData.append("\n");
		reader.endArray();
		return medicineData;
	}

	// Parses the contents of a medicine.
	private String readMedicineEntry(JsonReader reader) throws IOException {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
		String medName = null;
		while (reader.hasNext()) {
			//No cumple principio abierto cerrado
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return medName;
	}

	// Parses the contents of a medicine list.
	private StringBuffer readRescueMedicinePresentations(JsonReader reader) throws IOException {
		StringBuffer rescueMedicinePresentationData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			rescueMedicinePresentationData.append(readRescueMedicinePresentationEntry(reader)).append("\n");
			reader.endObject();
		}
		rescueMedicinePresentationData.append("\n");
		reader.endArray();
		return rescueMedicinePresentationData;
	}

	// Parses the contents of a rescue medicine presentation entry
	private String readRescueMedicinePresentationEntry(JsonReader reader) throws IOException {
		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;
		while (reader.hasNext()) {
			//No cumple principio abierto cerrado
			String name = reader.nextName();
			if (name.equals(MEDREF_FIELD_TAGNAME)) {
				medRef = reader.nextString();
			} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
				aiRef = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				inhRef = new String(res);
			} else if (name.equals(DOSE_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				dose = new String(res);
			} else {
				reader.skipValue();
			}

		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
	}
	
	private StringBuffer readPhysiotherapies(JsonReader reader) throws IOException {
		StringBuffer physiotherapiesData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			physiotherapiesData.append(readPhysiotherapiesEntry(reader)).append("\n");
			reader.endObject();
		}
		physiotherapiesData.append("\n");
		reader.endArray();
		return physiotherapiesData;
	}
	
	private String readPhysiotherapiesEntry(JsonReader reader) throws IOException {
	
		String physName = null;
		String physImage = null;
		while (reader.hasNext()) {
			//No cumple principio abierto cerrado
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				physName = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				physImage = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return physName + FIELD_SEPARATOR + physImage;
	}

	private StringBuffer readMedicinePresentations(JsonReader reader) throws IOException {
		StringBuffer rescueMedicinePresentationData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			rescueMedicinePresentationData.append(readMedicinePresentationsEntry(reader)).append("\n");
			reader.endObject();
		}
		rescueMedicinePresentationData.append("\n");
		reader.endArray();
		return rescueMedicinePresentationData;
	}
	
	private String readMedicinePresentationsEntry(JsonReader reader) throws IOException {
		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;
		String posRef = null;
		while (reader.hasNext()) {
			//No cumple principio abierto cerrado
			String name = reader.nextName();
			if (name.equals(MEDREF_FIELD_TAGNAME)) {
				medRef = reader.nextString();
			} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
				aiRef = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				inhRef = new String(res);
			} else if (name.equals(DOSE_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				dose = new String(res);
			} else if (name.equals(POSREF_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				posRef = new String(res);
			} else {
				reader.skipValue();
			}

		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose+ FIELD_SEPARATOR + posRef;
	}

}
