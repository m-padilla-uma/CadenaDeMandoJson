package salud.isa.gsonMedDBComplete;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try{
			DatabaseJSonReader dbjp = new DatabaseJSonReader();
			ElementoCadenaMando sig0 = null;
			ElementoCadenaMando sig1 = new UserManualSteps(sig0);
			ElementoCadenaMando sig2 = new UserManualPhysioSteps(sig1);
			ElementoCadenaMando sig3 = new RecueMedicinesPresentation(sig2);
			ElementoCadenaMando sig4 = new MedicinePresentations(sig3);
			ElementoCadenaMando sig5 = new Posologies(sig4);
			ElementoCadenaMando sig6 = new Inhalers(sig5);
			ElementoCadenaMando sig7 = new Physiotherapies(sig6);
			ElementoCadenaMando sig8 = new ActiveIngredients(sig7);
			ElementoCadenaMando cm = new Medicines(sig8);

			try {
				System.out.println(dbjp.parse("./src/main/resources/datos.json", cm));
			} finally {
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}

}
