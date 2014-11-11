package com.example.primitiva;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	TextView cad1, cad2, cad3, cad4, cad5, cad6;

	public static final String MisPreferencias = "MisPrefs";
	public static final String Cadena1 = "KEYcad1";
	public static final String Cadena2 = "KEYcad2";
	public static final String Cadena3 = "KEYcad3";
	public static final String Cadena4 = "KEYcad4";
	public static final String Cadena5 = "KEYcad5";
	public static final String Cadena6 = "KEYcad6";

	String aleat1, aleat2, aleat3, aleat4, aleat5, aleat6;

	SharedPreferences sharedpreferences;

	private ArrayList<String> numeros;

	public ArrayList<String> generarNumeros() {

		ArrayList<String> numeros = new ArrayList();
		boolean repetido = false;

		do {

			for (int i = 0; i < 6; i++) {
				numeros.add(String.valueOf((int) (Math.floor(Math.random() * 50 + 1)))); // Añado
																							// los
																							// numeros
																							// aleatorios
																							// al
																							// array
			}

			repetido = comprobarNumeros(numeros); // Compruebo si hay numeros
													// repetidos

			if (repetido == true) {
				numeros.clear(); // Vacio el arrayList si hay numeros repetidos
			}
		} while (repetido == true); // No saldrá del bucle hasta que el flag
									// este a false (no haya repetidos)
		
		numeros = ordenarNumeros(numeros);

		return numeros;

	}

	public ArrayList<String> ordenarNumeros(ArrayList<String> numeros) {
		ArrayList<Integer> numerosINT = new ArrayList();

		for (String number : numeros) {
			numerosINT.add(Integer.parseInt(number)); //Paso a Integer para ordenar por numeros y no por carácteres
		}
		Collections.sort(numerosINT);

		numeros.clear();
		for (Integer number : numerosINT) {
			numeros.add(String.valueOf(number));
		}

		return numeros;
	}

	public static boolean comprobarNumeros(ArrayList<String> numeros) {
		boolean noRepes = false; // false = no hay repetidos. true = existen repetidos
		ArrayList<String> aux = numeros; // Array auxiliar para comprobar números iguales
		
		for (int i = 0; i < numeros.size(); i++) {
			for (int j = 0; j < numeros.size(); j++) {
				if (i != j) { // Como los dos arrays son iguales no quiero
								// comprobar la primera posición ya que,
								// evidentemente, contendrán el mismo numero
					if (numeros.get(i).equals(aux.get(j))) {
						noRepes = true;
					}
				}
			}

		}

		return noRepes;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cad1 = (TextView) findViewById(R.id.textViewCadena1);
		cad2 = (TextView) findViewById(R.id.textViewCadena2);
		cad3 = (TextView) findViewById(R.id.textViewCadena3);
		cad4 = (TextView) findViewById(R.id.textViewCadena4);
		cad5 = (TextView) findViewById(R.id.textViewCadena5);
		cad6 = (TextView) findViewById(R.id.textViewCadena6);

		sharedpreferences = getSharedPreferences(MisPreferencias,
				Context.MODE_PRIVATE);

		cad1.setText(sharedpreferences.getString(Cadena1, ""));
		cad2.setText(sharedpreferences.getString(Cadena2, ""));
		cad3.setText(sharedpreferences.getString(Cadena3, ""));
		cad4.setText(sharedpreferences.getString(Cadena4, ""));
		cad5.setText(sharedpreferences.getString(Cadena5, ""));
		cad6.setText(sharedpreferences.getString(Cadena6, ""));

	}

	public void onButtonClickGenerar(View v) {

		numeros = generarNumeros();

		
		Editor editor = sharedpreferences.edit();		
		editor.putString(Cadena1, numeros.get(0));
		editor.putString(Cadena2, numeros.get(1));
		editor.putString(Cadena3, numeros.get(2));
		editor.putString(Cadena4, numeros.get(3));
		editor.putString(Cadena5, numeros.get(4));
		editor.putString(Cadena6, numeros.get(5));
		editor.commit();

		cad1.setText(sharedpreferences.getString(Cadena1, ""));
		cad2.setText(sharedpreferences.getString(Cadena2, ""));
		cad3.setText(sharedpreferences.getString(Cadena3, ""));
		cad4.setText(sharedpreferences.getString(Cadena4, ""));
		cad5.setText(sharedpreferences.getString(Cadena5, ""));
		cad6.setText(sharedpreferences.getString(Cadena6, ""));

		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // Vibracion
																				// al
																				// pulsar
		vib.vibrate(100);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
