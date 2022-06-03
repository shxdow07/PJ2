/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasaplicacio;

import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author DAM
 */
public class Activitat {
    private int ID_a;
    private String descripcio;
    private int reserves;
    private int Durada;
    private int Data;
    private Date Data_fisica;
    private Time h_fi;
    private Time h_inici;
    private int Reserves;
    private String act_nom;

    public Activitat() {
    }

    public int getID_a() {
        return ID_a;
    }

    public void setID_a(int ID_a) {
        this.ID_a = ID_a;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getReserves() {
        return reserves;
    }

    public void setReserves(int reserves) {
        this.reserves = reserves;
    }

    public Activitat(int ID_a, String descripcio, int reserves, int Durada, int Data, Date Data_fisica, Time h_fi, Time h_inici, int Reserves, String act_nom) {
        this.ID_a = ID_a;
        this.descripcio = descripcio;
        this.reserves = reserves;
        this.Durada = Durada;
        this.Data = Data;
        this.Data_fisica = Data_fisica;
        this.h_fi = h_fi;
        this.h_inici = h_inici;
        this.Reserves = Reserves;
        this.act_nom = act_nom;
    }

    public int getDurada() {
        return Durada;
    }

    public void setDurada(int Durada) {
        this.Durada = Durada;
    }

    public int getData() {
        return Data;
    }

    public void setData(int Data) {
        this.Data = Data;
    }

    public Date getData_fisica() {
        return Data_fisica;
    }

    public void setData_fisica(Date Data_fisica) {
        this.Data_fisica = Data_fisica;
    }

    public Time getH_fi() {
        return h_fi;
    }

    public void setH_fi(Time h_fi) {
        this.h_fi = h_fi;
    }

    public Time getH_inici() {
        return h_inici;
    }

    public void setH_inici(Time h_inici) {
        this.h_inici = h_inici;
    }

    public String getAct_nom() {
        return act_nom;
    }

    public void setAct_nom(String act_nom) {
        this.act_nom = act_nom;
    }
    
    
    public ArrayList<Activitat> actDias(){
       try{
           Connection con = new Conexio_bd().connectarBD();
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String consulta2 = String.format("select a.*, s.* from activitat a, sala s where a.ID_s=s.ID AND a.Data_fisica= " + "\"%s\"" + " ORDER BY a.Reserves DESC;", date.format(formatter));
            PreparedStatement sql2 = con.prepareStatement(consulta2);
            sql2.executeQuery();
            ResultSet rs = sql2.executeQuery();
           ArrayList<Activitat> llista = new ArrayList();
           while(rs.next()){
                Activitat a = new Activitat();
               a.setID_a(rs.getInt(1));
               a.setDescripcio(rs.getString(2));
               a.setDurada (rs.getInt(3));
               a.setData(rs.getInt(4));
               a.setData_fisica(rs.getDate(5));
               a.setH_fi(rs.getTime(6));
               a.setH_inici (rs.getTime(7));
               a.setReserves (rs.getInt(8));
               a.setAct_nom (rs.getString(10));
               llista.add(a);
               //System.out.println("-----------------------------------------");
               //System.out.println("El client que busca té el Nom: " + c.nom + " \nCognom: " + c.cognom + " \nDNI: " + c.DNI.getDNI() + " \nCorreu Electrònic: " + c.email.getEmail() + " \nDomicili: " + c.domicili + " \nTelefon: " + c.Telefon.getTelefon() + " \nCompte Bancari:" + c.CCC.getCCC() + " \nContrassenya: " + c.contrassenya + " \nData de Naixement: " + c.fecha.getFecha() + " \nReserves: " + c.reserves);
           }
           return llista;
       } catch (SQLException e){
           System.out.println(e.getMessage());
           return null;
       }
    }

    @Override
    public String toString() {
        return "Activitat{" + "ID_a=" + ID_a +  ", Nom=" + act_nom + ", Descripcio=" + descripcio + ", Reserves=" + reserves + ", Durada=" + Durada + ", Data=" + Data + ", Data_fisica=" + Data_fisica + ", h_fi=" + h_fi + ", h_inici=" + h_inici + '}';
    }
    
    
    
}
