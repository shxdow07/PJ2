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
public class DNI {
    private String DNI;
    private int numero;
    private char lletra;
    Client cl = new Client();
    public DNI(String DNI) {
        this.DNI = DNI;
    }

    public DNI() {
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    
    
    public boolean validarDNI(String DNI){
        String letraMayuscula;
        if(DNI.length() != 9 || Character.isLetter(DNI.charAt(8)) == false ){
        return false;
        }
        letraMayuscula = (DNI.substring(8)).toUpperCase();
        
        if(soloNumeros(DNI) == true && letraDNI(DNI).equals(letraMayuscula)){
            return true;
        } else{
            return false;
        }
        
    }
    
    public boolean soloNumeros(String DNI){
        int i, j = 0;
        String numero = "";
        String miDNI = "";
        String[] unoNueve = {"0","1","2","3","4","5","6","7","8","9"};
        
        for( i=0; i<DNI.length() - 1; i++){
            numero = DNI.substring(i, i+1);
            
            for (j=0; j<unoNueve.length;j++){
                if(numero.equals(unoNueve[j])){
                    miDNI += unoNueve[j];
                }
            }
        }
        
        if(miDNI.length() != 8){
            return false;
        } else{
            return true;
        }
    }
    
    private String letraDNI(String DNI){
        int miDNI = Integer.parseInt(DNI.substring(0,8));
        int resto = 0;
        String miLetra = "";
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        resto = miDNI % 23;
        miLetra = asignacionLetra[resto];
        return miLetra;
        
    }

    @Override
    public String toString() {
        return DNI;
    }
    
}
