/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasaplicacio;

import java.math.BigInteger;

/**
 *
 * @author DAM
 */
public class CCC {
    private String CCC;

    public CCC(String CCC) {
        this.CCC = CCC;
    }

    
    public CCC() {
    }

    public String getCCC() {
        return CCC;
    }

    public void setCCC(String CCC) {
        this.CCC = CCC;
    }
    
    public boolean validarCCC(String CCC) {

		boolean esValido = false;
		int i = 2;
		int caracterASCII = 0; 
		int resto = 0;
		int dc = 0;
		String cadenaDc = "";
		BigInteger cuentaNumero = new BigInteger("0"); 
		BigInteger modo = new BigInteger("97");

		if(CCC.length() == 24 && CCC.substring(0,1).toUpperCase().equals("E") 
			&& CCC.substring(1,2).toUpperCase().equals("S")) {

			do {
				caracterASCII = CCC.codePointAt(i);
				esValido = (caracterASCII > 47 && caracterASCII < 58);
				i++;
			}
			while(i < CCC.length() && esValido); 
		
		
			if(esValido) {
				cuentaNumero = new BigInteger(CCC.substring(4,24) + "142800");
				resto = cuentaNumero.mod(modo).intValue();
				dc = 98 - resto;
				cadenaDc = String.valueOf(dc);
			} 	
			
			if(dc < 10) {
				cadenaDc = "0" + cadenaDc;
			}

			// Comparamos los caracteres 2 y 3 de la cuenta (dígito de control IBAN) con cadenaDc
			if(CCC.substring(2,4).equals(cadenaDc)) {
				esValido = true;
			} else {
				esValido = false;
			}
		} else if(CCC.length() > 24 && CCC.substring(0,1).toUpperCase().equals("E") 
			&& CCC.substring(1,2).toUpperCase().equals("S")) {
                    System.out.println("ERROR....S'han introduit més dígits dels necessaris");
                } else if(CCC.length() < 24 && CCC.substring(0,1).toUpperCase().equals("E") 
			&& CCC.substring(1,2).toUpperCase().equals("S")) {
                    System.out.println("ERROR....S'han introduit menys digits dels necessaris");
                } else if(CCC.length() == 24 && (!(CCC.substring(0,1).toUpperCase().equals("E") 
			&& CCC.substring(1,2).toUpperCase().equals("S")))) {
                    System.out.println("ERROR....Les dos primeres lletres no son ES");
                } else if(CCC.length() < 24){
                    System.out.println("Incorrecte");
                }

		return esValido;
	}

    @Override
    public String toString() {
        return CCC;
    }
    
}
