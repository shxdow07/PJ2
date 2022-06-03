/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasaplicacio;

import java.io.*;
import java.util.regex.*;

/**
 *
 * @author DAM
 */
public class Telefon {
    private String Telefon;

    public Telefon(String Telefon) {
        this.Telefon = Telefon;
    }

    public Telefon() {
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }
    
    
    
    public boolean validarTelefon(String Telefon) {
    Pattern p = Pattern.compile("^\\d{9}$");
    Matcher m = p.matcher(Telefon);
    return (m.matches());
    }

    @Override
    public String toString() {
        return Telefon;
    }
    
}
