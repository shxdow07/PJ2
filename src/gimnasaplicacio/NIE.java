/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasaplicacio;

/**
 *
 * @author DAM
 */
public class NIE {
    private String NIE;

    public NIE(String NIE) {
        this.NIE = NIE;
    }

    public NIE() {
    }

    public String getNIE() {
        return NIE;
    }

    public void setNIE(String NIE) {
        this.NIE = NIE;
    }
    
    public boolean validarNIE(String NIE) {

		boolean esValido = false;
		int i = 1;
		int caracterASCII = 0;
		char letra = ' ';
		int miNIE = 0;
		int resto = 0;
		char[] asignacionLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X','B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};


		if(NIE.length() == 9 && Character.isLetter(NIE.charAt(8))
			&& NIE.substring(0,1).toUpperCase().equals("X")
			|| NIE.substring(0,1).toUpperCase().equals("Y")
			|| NIE.substring(0,1).toUpperCase().equals("Z")) {

			do {
				caracterASCII = NIE.codePointAt(i);
				esValido = (caracterASCII > 47 && caracterASCII < 58);
				i++;
			} while(i < NIE.length() - 1 && esValido);
		}

		if(esValido && NIE.substring(0,1).toUpperCase().equals("X")) {
			NIE = "0" + NIE.substring(1,9);
		} else if(esValido && NIE.substring(0,1).toUpperCase().equals("Y")) {
			NIE = "1" + NIE.substring(1,9);
		} else if(esValido && NIE.substring(0,1).toUpperCase().equals("Z")) {
			NIE = "2" + NIE.substring(1,9);
		}

		if(esValido) {
			letra = Character.toUpperCase(NIE.charAt(8));
			miNIE = Integer.parseInt(NIE.substring(1,8));
			resto = miNIE % 23;
			esValido = (letra == asignacionLetra[resto]);
		}

		return esValido;
	}
}
