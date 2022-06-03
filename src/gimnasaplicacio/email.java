/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasaplicacio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DAM
 */
public class email {
    private String email;

    public email(String email) {
        this.email = email;
    }

    public email() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean validarEmail(String email){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = pattern.matcher(email);
        return (m.matches());
    }

    @Override
    public String toString() {
        return email;
    }
    
}
