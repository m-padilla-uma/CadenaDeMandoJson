package salud.isa.gsonMedDBComplete;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualPhysioSteps extends ElementoCadenaMando {

	private static final String USERMANUALPHYSIOSTEPS_TAGNAME = "userManualPhysioSteps";

	private static final String STEPTI_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIM_FIELD_TAGNAME = "stepimage";
	private static final String STEPTE_FIELD_TAGNAME = "stepText";
	private static final String PHYSREF_FIELD_TAGNAME = "inhalerRef";
	private static final String FIELD_SEPARATOR = ";";

	public UserManualPhysioSteps(ElementoCadenaMando s) {
		super(s);
		cmName = USERMANUALPHYSIOSTEPS_TAGNAME;
	}
	
	public String readEntry(JsonReader reader) throws IOException {
		String stpTitle = null;
		String stpImage = null;
		String stpText = null;
		String physRef = null;
		while (reader.hasNext()) {
			//No cumple principio abierto cerrado
			String name = reader.nextName();
			if (name.equals(STEPTI_FIELD_TAGNAME)) {
				stpTitle = reader.nextString();
			} else if (name.equals(STEPIM_FIELD_TAGNAME)) {
				stpImage = reader.nextString();
			} else if (name.equals(STEPTE_FIELD_TAGNAME)) {
				stpText = reader.nextString();
			} else if (name.equals(PHYSREF_FIELD_TAGNAME)) {
				physRef = reader.nextString();
			}

		}
		return stpTitle + FIELD_SEPARATOR + stpImage + FIELD_SEPARATOR + stpText + FIELD_SEPARATOR + physRef;
	}
}
