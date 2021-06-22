package salud.isa.gsonMedDBComplete;
/*
 * 		  "stepTitle": "titulo.1.genuair",
          "stepImage": "genuair1",
          "stepText": "texto.1.genuair",
          "inhalerRef": "genuairName"
 */

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualSteps extends ElementoCadenaMando{

	private static final String USERMANUALSTEPS_TAGNAME = "userManualSteps";

	private static final String STEPTI_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIM_FIELD_TAGNAME = "stepimage";
	private static final String STEPTE_FIELD_TAGNAME = "stepText";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String FIELD_SEPARATOR = ";";
	
	public UserManualSteps(ElementoCadenaMando s) {
		super(s);
		cmName = USERMANUALSTEPS_TAGNAME;
	}
	
	public String readEntry(JsonReader reader) throws IOException {
		String stpTitle = null;
		String stpImage = null;
		String stpText = null;
		String inhRef = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STEPTI_FIELD_TAGNAME)) {
				stpTitle = reader.nextString();
			} else if (name.equals(STEPIM_FIELD_TAGNAME)) {
				stpImage = reader.nextString();
			} else if (name.equals(STEPTE_FIELD_TAGNAME)) {
				stpText = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				inhRef = reader.nextString();
			}

		}
		return stpTitle + FIELD_SEPARATOR + stpImage + FIELD_SEPARATOR + stpText + FIELD_SEPARATOR + inhRef;
	}
}
