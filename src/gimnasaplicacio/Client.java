/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasaplicacio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
/**
 *
 * @author DAM
 */
public class Client {
    private DNI DNI;
    private String nom;
    private String cognom;
    private Telefon Telefon;
    private email email;
    private CCC CCC;
    private Data fecha;
    private NIE NIE;
    private String domicili;
    private String contrassenya;
    private int reserves;
    private int id;
    private Date alta;
    private Date baixa;
    private String dni3;
    private Date data1;
    private Date data2;
    private int id5;
    private String nom2;
    private String dni2;
    private int ID_a;
    private String descripcio;
    private int reserves2;
    public static String carpeta="script/script.sh";
    public static String[] command = {"sh",carpeta};
    public static String carpeta2="script/usuari.sh";
    public static String[] command2 = {"sh",carpeta2};
    private char opcio;
    private int reserves_tot;
    //private String[] command = {"sh",carpeta};
    //private String carpeta2="script/usuari.sh";
    //private String[] command2 = {"sh",carpeta2};
    Scanner teclat = new Scanner (System.in);
    Scanner st = new Scanner(System.in);
     private boolean enrere=false;

    public Client() {
    }

    public Client(DNI DNI, String nom, String cognom, Telefon Telefon, email email, CCC CCC, String domicili, NIE NIE, String contrassenya, int reserves, Data fecha, int id) {
        this.DNI = new DNI();
        this.nom = nom;
        this.cognom = cognom;
        this.Telefon = new Telefon();
        this.email = new email();
        this.CCC = new CCC();
        this.domicili = domicili;
        this.NIE = new NIE();
        this.contrassenya = contrassenya;
        this.reserves = reserves;
        this.fecha = fecha;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public Data getFecha() {
        return fecha;
    }

    public void setFecha(Data fecha) {
        this.fecha = fecha;
    }

    public int getReserves() {
        return reserves;
    }

    public void setReserves(int reserves) {
        this.reserves = reserves;
    }
    
    
    public String getContrassenya() {
        return contrassenya;
    }

    public void setContrassenya(String contrassenya) {
        this.contrassenya = contrassenya;
    }

    
    public NIE getNIE() {
        return NIE;
    }

    public void setNIE(NIE NIE) {
        this.NIE = NIE;
    }
    
    public DNI getDNI() {
        return DNI;
    }

    public void setDNI(DNI DNI) {
        this.DNI = DNI;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public Telefon getTelefon() {
        return Telefon;
    }

    public void setTelefon(Telefon Telefon) {
        this.Telefon = Telefon;
    }

    public email getEmail() {
        return email;
    }

    public void setEmail(email email) {
        this.email = email;
    }

    public CCC getCCC() {
        return CCC;
    }

    public void setCCC(CCC CCC) {
        this.CCC = CCC;
    }

    public String getDomicili() {
        return domicili;
    }

    public void setDomicili(String domicili) {
        this.domicili = domicili;
    }

    
    
    
    
    public void Gestio_clients() throws SQLException, ParseException, IOException{
        do{
            System.out.println("**********MENU GIMNAS*********");
        
            System.out.println("1. Afegir Client");
            System.out.println("2. Buscar Client");
            System.out.println("3. Modificar Client");
            System.out.println("4. Donar de Baixa");
            System.out.println("5. Donar de Alta");
            System.out.println("s. Sortir");

            String sa = st.next();
            char opcio = sa.charAt(0);  
            System.out.print("Ha premut l'opcio: ");

           switch (opcio){
                case '1':
                    System.out.println(" Afegir Clients");
                    Afegir_clients();
                    break;
                case '2':
                    System.out.println(" Consultar Clients");
                    Consultar_clients();
                    break;
                case '3':
                   System.out.println(" Modificar Clients");
                   modificarClients();
                    break;
                case '4':
                   System.out.println(" Donar De Baixa un Client");
                   donarBaixa();
                    break;
                case '5':
                   System.out.println(" Donar De Alta");
                   donarAlta();
                    break;
                case 's':
                    System.out.println(" Sortir");
                    enrere = true;
                    break;    
                case 'S':
                    System.out.println(" Sortir");
                enrere = true;
                   break;

                default:
                   System.out.println("L'Opció no és vàlida");
            }
        } 
        while (!enrere);

    }
    public boolean Afegir_clients() throws SQLException, ParseException, IOException{
            
            Connection con = new Conexio_bd().connectarBD();
            encoder e = new encoder();
            Statement stmt = con.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            stmt.close();
            String consulta = "select * from historial ORDER BY ID DESC";
            PreparedStatement sql4 = con.prepareStatement(consulta);
            sql4.executeQuery();
            ResultSet rs = sql4.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("ID");
            }
            PreparedStatement sql = con.prepareStatement("insert into client "
                    + "(Nom, Cognom, DNI, Correu, Adreça, Telefon, CC, Contrassenya, Data) "
                    + "values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?);");
            System.out.println("--------------------------------");
            System.out.println("Introduir el Nom del nou client");
            nom = teclat.next();
            System.out.println("--------------------------------");
            System.out.println("Introdueix el Cognom del nou client");
            cognom = teclat.next();
            DNI dniObj = new DNI();
            String DNI;
            Telefon telObj = new Telefon();
            String tel;
            email emaObj = new email();
            String ema;
            CCC cccObj = new CCC();
            String ccc;
            NIE nieObj = new NIE();
            String nie;
            Data datObj = new Data();
            int dia;
            int mes;
            int año;
            int i = 0;
            do{
                System.out.println("--------------------------------");
                System.out.println("Introdueix el dia de Naixement");
                dia = teclat.nextInt();
                System.out.println("Introdueix el mes de Naixement");
                mes = teclat.nextInt();
                System.out.println("Introdueix l'any de Naixement");
                año = teclat.nextInt();
                if(!datObj.validarFecha(dia, mes, año)){
                System.out.println("Data de Naixement Incorrecta");
                }
            }while(!datObj.validarFecha(dia, mes, año));
            String fecha = (año + "-" + mes + "-" + dia);
            datObj.setFecha(fecha);
            do{
                System.out.println("--------------------------------");
                System.out.println("Introdueix el DNI del client o si es NIE escriu NIE");
                DNI = teclat.next();
                if(DNI.equals("NIE")){
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Introdueix el NIE");
                        nie = teclat.next();
                        if(!nieObj.validarNIE(nie)){
                            System.out.println("NIE INCORRECTE");
                        }
                    } while(!nieObj.validarNIE(nie));
                    nieObj.setNIE(nie);
                    this.NIE = nieObj;
                }else if(!dniObj.validarDNI(DNI) && !DNI.equals("NIE")){
                    System.out.println("DNI INCORRECTE");
                }
                
            }
            while(!dniObj.validarDNI(DNI) && !DNI.equals("NIE"));
            dniObj.setDNI(DNI);
            do{
                System.out.println("--------------------------------");
                System.out.println("Introdueix el Correu Electrònic");
                ema = teclat.next();
                if(!emaObj.validarEmail(ema)){
                System.out.println("Correu Electrònic Incorrecte");
                }
            }while(!emaObj.validarEmail(ema));
            emaObj.setEmail(ema);
            System.out.println("--------------------------------");
            System.out.println("Introdueix el Domicili del client");
            domicili = teclat.next();
            do{
                System.out.println("--------------------------------");
                System.out.println("Introdueix el Telefon del Client");
                tel = teclat.next();
                if(!telObj.validarTelefon(tel)){
                System.out.println("Telefon Incorrecte");
                }
            }while(!telObj.validarTelefon(tel));
            telObj.setTelefon(tel);
            do{
                System.out.println("--------------------------------");
                System.out.println("Introdueix el Compte Bancari");
                ccc = teclat.next();
                if(!cccObj.validarCCC(ccc)){
                System.out.println("Compte Bancari Incorrecte");
                }
            }while(!cccObj.validarCCC(ccc));
            cccObj.setCCC(ccc);
            System.out.println("--------------------------------");
            System.out.println("Introdueix la Contrassenya del Client");
            contrassenya = teclat.next();
            this.DNI = dniObj;
            this.Telefon = telObj;
            this.email = emaObj;
            this.CCC = cccObj;
            this.fecha = datObj;
            sql.setString(1, this.nom);
            sql.setString(2, this.cognom);
            if(DNI.equals("NIE")){
                sql.setString(3, this.NIE.getNIE());
            }else{
                sql.setString(3, this.DNI.getDNI());
            }
            sql.setString(4, this.email.getEmail());
            sql.setString(5, this.domicili);
            sql.setString(6, this.Telefon.getTelefon());
            sql.setString(7, this.CCC.getCCC());
            sql.setString(8, e.MD5(this.contrassenya));
            sql.setDate(9, java.sql.Date.valueOf(this.fecha.getFecha()));
            if(DNI.equals("NIE")){
                System.out.println("-----------------------------------");
                System.out.println("S'ha creat el client amb Nom: " + this.nom + " \nCognom: " + this.cognom + " \nNIE: " + this.NIE.getNIE() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement: " + this.fecha.getFecha()+ " \nEdat: " + datObj.calcularEdad(fecha) + " anys");   
                System.out.println("-----------------------------------");
            }else{
                System.out.println("-----------------------------------");
                System.out.println("S'ha creat el client amb Nom: " + this.nom + " Cognom: " + this.cognom + " \nDNI: " + this.DNI.getDNI() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement: " + this.fecha.getFecha()+ " \nEdat: " + datObj.calcularEdad(fecha) + " anys");
                System.out.println("-----------------------------------");
            }
            i++;
            int ID = i+this.id;
            int act = 1;
            PreparedStatement sql3 = con.prepareStatement("insert into historial "
                    + "(ID) "
                    + "values "
                    + "(?);");
            sql3.setInt(1, ID);
            PreparedStatement sql2 = con.prepareStatement("insert into apuntar "
                    + "(ID_ALTA, ID_BAIXA, ID_h, DNI_c) "
                    + "values "
                    + "(?, ?, ?, ?);");
            
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            sql2.setDate(1, java.sql.Date.valueOf(date.format(formatter)));
            sql2.setDate(2, null);
            sql2.setInt(3, ID);
            sql2.setString(4, this.DNI.getDNI());
            int res = sql.executeUpdate();
            int res2 = sql2.executeUpdate();
            int res3 = sql3.executeUpdate();
            Statement stmt2 = con.createStatement();
            stmt2.execute("SET FOREIGN_KEY_CHECKS=1");
            stmt2.close(); 
            if(res==1 && res2==1 && res3==1){
                    File fitxer = new File(carpeta2);
                    if (!fitxer.exists()) {
                        fitxer.createNewFile();
                    }
                    FileWriter fw = new FileWriter (fitxer);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("New-ADUser -Name " + this.nom +" "+ this.cognom + " -GivenName " + this.nom + " -Surname " + this.cognom + " -SamAccountName " + this.nom + " -UserPrincipalName "+ this.email.getEmail()+" -Path OU=Users,OU=Accounts,OU=Berlin,OU=DE,DC=woshub,DC=com -AccountPassword "+this.contrassenya+ " -Enabled $true");
                    bw.close();
                    //Process process = Runtime.getRuntime().exec(command2);
                    //process.destroy();
                return true;
            }else{
                return false;
            }
            
            
            
        } 
    

    
    public void Consultar_clients() throws SQLException{
            Connection con = new Conexio_bd().connectarBD();
            System.out.println("Buscar Client amb DNI o NIE: ");
            String dni = teclat.next();
            String consulta = String.format("select * from client where DNI=" + "\"%s\"" + ";", dni);
            PreparedStatement sql = con.prepareStatement(consulta);
            sql.executeQuery();
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                        this.nom = rs.getString("Nom");
                        this.cognom = rs.getString("Cognom");
                        this.DNI = new DNI(rs.getString("DNI"));
                        this.email = new email(rs.getString("Correu"));
                        this.domicili = rs.getString("Adreça");
                        this.Telefon = new Telefon(rs.getString("Telefon"));
                        this.CCC = new CCC(rs.getString("CC"));
                        this.contrassenya = rs.getString("Contrassenya");
                        this.fecha = new Data(rs.getString("Data"));
                        System.out.println("El client que busca té el Nom: " + this.nom + " \nCognom: " + this.cognom + " \nDNI: " + this.DNI.getDNI() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement:" + this.fecha.getFecha());
            } else {
                System.out.println("No s'ha trobat al Client");
            }
    }
    
    public void modificarClients() throws SQLException, IOException{
        
        Connection con = new Conexio_bd().connectarBD();
        
         do {

            System.out.println("Com vols identificar el Client a modificar");
            System.out.println("1. Per Nom");
            System.out.println("2. Per DNI");
            System.out.println("s. sortir");
            String sa = st.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
            switch (opcio2) {
                 case '1':
                   
                    System.out.println("Ficar el Nom del Client:");
                    nom = teclat.next();
                    String consulta = String.format("select * from client where Nom= " + "\"%s\"" + ";", nom);
                    PreparedStatement sql = con.prepareStatement(consulta);
                    sql.executeQuery();
                    ResultSet rs = sql.executeQuery();
                    if (rs.next()) {
                        this.nom = rs.getString("Nom");
                        this.cognom = rs.getString("Cognom");
                        this.DNI = new DNI(rs.getString("DNI"));
                        this.email = new email(rs.getString("Correu"));
                        this.domicili = rs.getString("Adreça");
                        this.Telefon = new Telefon(rs.getString("Telefon"));
                        this.CCC = new CCC(rs.getString("CC"));
                        this.contrassenya = rs.getString("Contrassenya");
                        this.fecha = new Data(rs.getString("Data"));
                        System.out.println("El client que vol modificar té: " + this.nom + " \nCognom: " + this.cognom + " \nDNI: " + this.DNI.getDNI() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement:" + this.fecha.getFecha());
                        ModificarClients3();
                    }else{
                        System.out.println("No existeix ningu amb aquest Nom");
                    } 
                    enrere = false;

                    break;
                case '2':
                    System.out.println("Ficar el DNI del Client:");
                    String dni = teclat.next();
                    String consulta2 = String.format("select * from client where DNI= " + "\"%s\"" + ";", dni);
                    PreparedStatement sql2 = con.prepareStatement(consulta2);
                    sql2.executeQuery();
                    ResultSet rs2 = sql2.executeQuery();
                    if (rs2.next()) {
                        this.nom = rs2.getString("Nom");
                        this.cognom = rs2.getString("Cognom");
                        this.DNI = new DNI(rs2.getString("DNI"));
                        this.email = new email(rs2.getString("Correu"));
                        this.domicili = rs2.getString("Adreça");
                        this.Telefon = new Telefon(rs2.getString("Telefon"));
                        this.CCC = new CCC(rs2.getString("CC"));
                        this.contrassenya = rs2.getString("Contrassenya");
                        this.fecha = new Data(rs2.getString("Data"));
                        System.out.println("El client que vol modificar té: " + this.nom + " \nCognom: " + this.cognom + " \nDNI: " + this.DNI.getDNI() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement:" + this.fecha.getFecha());
                        ModificarClients2();
                    }else{
                        System.out.println("No existeix ningu amb aquest DNI");
                    } 
                    enrere = false;
                    
                    
                    break;
                case 's':
                    enrere = true;
                    break;
                case 'S':
                    enrere = true;
                    break;
            }
        } while(!enrere);
    }
    public void Llistar_Client(){
        do{
            System.out.println("**********MENU LLISTAR CLIENTS*********");
            System.out.println("Com vols llistar els clients?");
            System.out.println("1. Per Cognom");
            System.out.println("2. Per Edat");
            System.out.println("3. Per Reserves");
            System.out.println("s. Sortir");

            
            String sa = st.next();
            char opcio2 = sa.charAt(0);
            System.out.print("Ha premut l'opcio: ");
             switch (opcio2) {
                case'1':
                    System.out.println(" Llistar Per Cognom");
                    llistarCognom();
                    break;
                case'2':
                    System.out.println(" Llistar Per Edat");
                    llistarEdat();
                    break;
                case'3':
                    System.out.println(" Llistar Per Reserves");
                    llistarReserves();
                    break;
                case 's':
                    System.out.println(" Sortir");
                    enrere = true;
                    break;
                case 'S':
                    System.out.println(" Sortir");
                    enrere = true;
                    break;
             }
        }while (!enrere);
    }

    @Override
    public String toString() {
        return "Client{" + "DNI=" + DNI + ", nom=" + nom + ", cognom=" + cognom + ", Telefon=" + Telefon + ", email=" + email + ", CCC=" + CCC + ", fecha=" + fecha + ", domicili=" + domicili + ", contrassenya=" + contrassenya + '}';
    }
     //arraylist per llistar per edat
    public ArrayList<Client> llistarEdat(){
       try{
           Connection con = new Conexio_bd().connectarBD();
           String consulta = "select * from client ORDER by Data";
           PreparedStatement sql = con.prepareStatement(consulta);
           ResultSet rs = sql.executeQuery();
           Data datObj = new Data();
           ArrayList<Client> llista = new ArrayList();
           
           while(rs.next()){
               Client c = new Client();
               c.setNom(rs.getString("Nom"));
               c.setCognom(rs.getString("Cognom"));
               c.setDNI (new DNI(rs.getString("DNI")));
               c.setEmail (new email(rs.getString("Correu")));
               c.setDomicili(rs.getString("Adreça"));
               c.setTelefon (new Telefon(rs.getString("Telefon")));
               c.setCCC (new CCC(rs.getString("CC")));
               c.setContrassenya (rs.getString("Contrassenya"));
               c.setFecha (new Data(rs.getString("Data")));
               llista.add(c);
               
               //System.out.println("-----------------------------------------");
               //System.out.println("El client que busca té el Nom: " + c.nom + " \nCognom: " + c.cognom + " \nDNI: " + c.DNI.getDNI() + " \nCorreu Electrònic: " + c.email.getEmail() + " \nDomicili: " + c.domicili + " \nTelefon: " + c.Telefon.getTelefon() + " \nCompte Bancari:" + c.CCC.getCCC() + " \nContrassenya: " + c.contrassenya+ " \nData de Naixement: " + c.fecha.getFecha() + " \nEdat: " + datObj.calcularEdad(c.fecha.getFecha()) + " anys");
           } 
           return llista;
       } catch (SQLException e){
           System.out.println(e.getMessage());
           return null;
       }
    }
    //arraylist per llistar per cognom
    public ArrayList<Client> llistarCognom(){
       try{
           Connection con = new Conexio_bd().connectarBD();
           String consulta = "select * from client ORDER by Cognom";
           PreparedStatement sql = con.prepareStatement(consulta);
           ResultSet rs = sql.executeQuery();
           
           ArrayList<Client> llista = new ArrayList();
           while(rs.next()){
               Client c = new Client();
               c.setNom(rs.getString("Nom"));
               c.setCognom(rs.getString("Cognom"));
               c.setDNI (new DNI(rs.getString("DNI")));
               c.setEmail (new email(rs.getString("Correu")));
               c.setDomicili(rs.getString("Adreça"));
               c.setTelefon (new Telefon(rs.getString("Telefon")));
               c.setCCC (new CCC(rs.getString("CC")));
               c.setContrassenya (rs.getString("Contrassenya"));
               c.setFecha (new Data(rs.getString("Data")));
               llista.add(c);
               //System.out.println("-----------------------------------------");
               //System.out.println("El client que busca té el Nom: " + c.nom + " \nCognom: " + c.cognom + " \nDNI: " + c.DNI.getDNI() + " \nCorreu Electrònic: " + c.email.getEmail() + " \nDomicili: " + c.domicili + " \nTelefon: " + c.Telefon.getTelefon() + " \nCompte Bancari:" + c.CCC.getCCC() + " \nContrassenya: " + c.contrassenya+ " \nData de Naixement: " + c.fecha.getFecha());
           } 
           return llista;
       } catch (SQLException e){
           System.out.println(e.getMessage());
           return null;
       }
    }
     //arraylist per llistar per Reserves
    public ArrayList<Client> llistarReserves(){
       try{
           Connection con = new Conexio_bd().connectarBD();
           String consulta = "select distinct cl.Nom, cl.Cognom, cl.DNI, cl.Correu, cl.Adreça, cl.Telefon, cl.CC, cl.Contrassenya, cl.Data, COUNT(res.DNI) FROM client cl, reserves res WHERE cl.DNI=res.DNI ORDER by res.Num_Reserves ASC";
           PreparedStatement sql = con.prepareStatement(consulta);
           ResultSet rs = sql.executeQuery();
           ArrayList<Client> llista = new ArrayList();
           while(rs.next()){
                Client c = new Client();
               c.setNom(rs.getString(1));
               c.setCognom(rs.getString(2));
               c.setDNI (new DNI(rs.getString(3)));
               c.setEmail (new email(rs.getString(4)));
               c.setDomicili(rs.getString(5));
               c.setTelefon (new Telefon(rs.getString(6)));
               c.setCCC (new CCC(rs.getString(7)));
               c.setContrassenya (rs.getString(8));
               c.setFecha (new Data(rs.getString(9)));
               c.setReserves(rs.getInt(10));
               llista.add(c);
               //System.out.println("-----------------------------------------");
               //System.out.println("El client que busca té el Nom: " + c.nom + " \nCognom: " + c.cognom + " \nDNI: " + c.DNI.getDNI() + " \nCorreu Electrònic: " + c.email.getEmail() + " \nDomicili: " + c.domicili + " \nTelefon: " + c.Telefon.getTelefon() + " \nCompte Bancari:" + c.CCC.getCCC() + " \nContrassenya: " + c.contrassenya + " \nData de Naixement: " + c.fecha.getFecha() + " \nReserves: " + c.reserves);
           }
           return llista;
       } catch (SQLException e){
           System.out.println(e.getMessage());
           return null;
       }
    }
    //Funció per modificar al client segons el DNI introduit
    public void ModificarClients2() throws SQLException, IOException{
        
        String dni2 = this.DNI.getDNI();
        encoder e = new encoder();
        DNI dniObj = new DNI();
        String dni;
        Telefon telObj = new Telefon();
        String tel;
        email emaObj = new email();
        String ema;
        CCC cccObj = new CCC();
        String ccc;
        NIE nieObj = new NIE();
        String nie;
        Data datObj = new Data();
        String fecha;
        int dia;
        int mes;
        int año;
        do {

            System.out.println("Que vols modificar ?");
            System.out.println("1. Nom");
            System.out.println("2. Cognom");
            System.out.println("3. DNI");
            System.out.println("4. Correu");
            System.out.println("5. Adreça");
            System.out.println("6. Telefon");
            System.out.println("7. CC");
            System.out.println("8. Contrassenya");
            System.out.println("9. Data");
            System.out.println("s. Sortir");
            String sa = st.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
        switch (opcio2) {
            //Aqui li diem quin valor vol modificar fent comprobacions de DNI, Email, CC, Telefon..
                case '1':
                    System.out.println("Canviar el Nom");
                    nom = teclat.next();
                    break;
                case '2':
                    System.out.println("Canviar el Cognom");
                    cognom = teclat.next();
                    break;
                case '3':
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Canviar el DNI o Escriu NIE si vols afegir un NIE");
                        dni = teclat.next();
                        
                        if(dni.equals("NIE")){
                            do{
                                System.out.println("--------------------------------");
                                System.out.println("Introdueix el NIE");
                                nie = teclat.next();
                                if(!nieObj.validarNIE(nie)){
                                System.out.println("Modificació Incorrecta---NIE Incorrecte");
                            }
                            }while(!nieObj.validarNIE(nie));
                            nieObj.setNIE(nie);
                            this.NIE = nieObj;
                        }else if(!dniObj.validarDNI(dni) && !dni.equals("NIE")){
                            System.out.println("Modificació Incorrecta---DNI Incorrecte");
                        }
                    }while(!dniObj.validarDNI(dni) && !dni.equals("NIE"));
                    dni2 = dni;
                    break;
                case '4':
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Canviar el Correu");
                        ema = teclat.next();//variable email
                        if(!emaObj.validarEmail(ema)){
                            System.out.println("Modificació Incorrecta---Correu Incorrecte");
                        }
                    }while(!emaObj.validarEmail(ema));//validem
                    emaObj.setEmail(ema);
                    this.email = emaObj;
                    break;
                case '5':
                    System.out.println("--------------------------------");
                    System.out.println("Canviar l'adreça");
                    domicili = teclat.next();
                    break;
                case '6':
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Canviar el Telefon");
                        tel = teclat.next();
                        if(!telObj.validarTelefon(tel)){
                            System.out.println("Modificació Incorrecta---Telefon Incorrecte");
                        }
                    }while(!telObj.validarTelefon(tel));
                    telObj.setTelefon(tel);
                    this.Telefon = telObj;
                    break;
                case '7':
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Canviar el CC");
                        ccc = teclat.next();
                        if(!cccObj.validarCCC(ccc)){
                            System.out.println("Modificació Incorrecta---Compte Bancari Incorrecte");
                        }
                    }while(!cccObj.validarCCC(ccc));
                    cccObj.setCCC(ccc);
                    this.CCC = cccObj;
                    break;
                case '8':
                    System.out.println("--------------------------------");
                    System.out.println("Canviar la Contrasenya");
                    contrassenya = teclat.next();
                    File fitxer = new File(carpeta);
                    if (!fitxer.exists()) {
                        fitxer.createNewFile();
                    }
                    FileWriter fw = new FileWriter (fitxer);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Set-ADAccountPassword " + this.nom + " -NewPassword " + contrassenya);
                    bw.close();
                    //Process process = Runtime.getRuntime().exec(command);
                    //process.destroy();
                    break;
                case '9':
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Canviar la Data");
                        System.out.println("Dia");
                        dia = teclat.nextInt();
                        System.out.println("Mes");
                        mes = teclat.nextInt();
                        System.out.println("Any");
                        año = teclat.nextInt();
                        if(!datObj.validarFecha(dia, mes, año)){
                            System.out.println("Modificació Incorrecta---Data Incorrecta");
                        }
                    }while(!datObj.validarFecha(dia, mes, año));
                    fecha = (dia + "/" + mes + "/" + año);
                    datObj.setFecha(fecha);
                    this.fecha = datObj;
                    break;
                case 's':
                    enrere = true;
                    break;
                case 'S':
                    enrere = true;
                    break;
            }
        //Aqui executem els canvis que s'han realitzat
            Connection con = new Conexio_bd().connectarBD();
            Statement stmt = con.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            stmt.close();
            String consulta3 = String.format("Select * from reserves where DNI =  " + "\"%s\"" + ";", this.DNI.getDNI());
            PreparedStatement sql3 = con.prepareStatement(consulta3);
            sql3.executeQuery();
            ResultSet rs = sql3.executeQuery();
            if (rs.next()) {
                String id4 = rs.getString("DNI");
                this.reserves = rs.getInt("Num_Reserves");
            }
            String consulta5 = String.format("Select * from apuntar where DNI_c =  " + "\"%s\"" + " ORDER BY DNI_c ASC;", this.DNI.getDNI());
            PreparedStatement sql5 = con.prepareStatement(consulta5);
            sql5.executeQuery();
            ResultSet rs2 = sql5.executeQuery();
            if (rs2.next()) {
                data1 = rs2.getDate("ID_ALTA");
                data2 = rs2.getDate("ID_BAIXA");
                id5 = rs2.getInt("ID_h");
                String dni4 = rs2.getString("DNI_c");
            }
            String consulta4 = String.format("Update apuntar Set " 
                    + "DNI_c = ? where DNI_c =  " + "\"%s\"" + ";", this.DNI.getDNI());
            String consulta2 = String.format("Update reserves Set " 
                    + "DNI = ?, Num_Reserves = ? where DNI =  " + "\"%s\"" + ";", this.DNI.getDNI());
            String consulta = String.format("Update client Set " 
                    + "Nom = ?, Cognom = ?, DNI = ?, Correu = ?, Adreça = ?, Telefon = ?, CC = ?, Contrassenya = ?, Data = ? where DNI =  " + "\"%s\"" + ";", this.DNI.getDNI());
            if(dni2 !=null){
                dniObj.setDNI(dni2);
                this.DNI = dniObj;
            }
            PreparedStatement sql2 = con.prepareStatement(consulta2);
            PreparedStatement sql = con.prepareStatement(consulta);
            PreparedStatement sql4 = con.prepareStatement(consulta4);
            sql4.setString(1, dni2);
            sql2.setString(1, dni2);
            sql2.setInt(2, this.reserves);
            sql.setString(1, this.nom);
            sql.setString(2, this.cognom);
            sql.setString(3, dni2);
            sql.setString(4, this.email.getEmail());
            sql.setString(5, this.domicili);
            sql.setString(6, this.Telefon.getTelefon());
            sql.setString(7, this.CCC.getCCC());
            sql.setString(8, e.MD5(this.contrassenya));
            sql.setString(9, this.fecha.getFecha());
            System.out.println("------------------------------------");
            System.out.println("Modificació: " + this.nom + " \nCognom: " + this.cognom + " \nDNI: " + dni2 + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement:" + this.fecha.getFecha());
            if (sql2.executeUpdate() != 0) {
                System.out.println(" ");
            } else {
                System.out.println("no insertat");
            }
             if (sql.executeUpdate() != 0) {
                System.out.println(" ");
            } else {
                System.out.println("no insertat");
            }
             if (sql4.executeUpdate() != 0) {
                System.out.println(" ");
            } else {
                System.out.println("no insertat");
            }
            Statement stmt2 = con.createStatement();
            stmt2.execute("SET FOREIGN_KEY_CHECKS=1");
            stmt2.close(); 
        } while (!enrere);
    }
    
    //Funció per Modificar per Nom del Client
    public void ModificarClients3() throws SQLException, IOException{
        String carpeta="script/script.txt";
        String nom2 = this.nom;
        encoder e = new encoder();
        DNI dniObj = new DNI();
        String dni;
        Telefon telObj = new Telefon();
        String tel;
        email emaObj = new email();
        String ema;
        CCC cccObj = new CCC();
        String ccc;
        NIE nieObj = new NIE();
        String nie;
        Data datObj = new Data();
        String fecha;
        int dia;
        int mes;
        int año;
        do {

            System.out.println("Que vols modificar ?");
            System.out.println("1. Nom");
            System.out.println("2. Cognom");
            System.out.println("3. DNI");
            System.out.println("4. Correu");
            System.out.println("5. Adreça");
            System.out.println("6. Telefon");
            System.out.println("7. CC");
            System.out.println("8. Contrassenya");
            System.out.println("9. Data");
            System.out.println("s. Sortir");
            String sa = st.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
        switch (opcio2) {
            //Aqui li diem quin valor vol modificar fent comprobacions de DNI, Email, CC, Telefon..
                case '1':
                    System.out.println("Canviar el Nom");
                    nom = teclat.next();

                    break;
                case '2':
                    System.out.println("Canviar el Cognom");
                    cognom = teclat.next();
                    break;
                case '3':
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Canviar el DNI o Escriu NIE si vols afegir un NIE");
                        dni = teclat.next();
                        
                        if(dni.equals("NIE")){
                            do{
                                System.out.println("--------------------------------");
                                System.out.println("Introdueix el NIE");
                                nie = teclat.next();
                                if(!nieObj.validarNIE(nie)){
                                System.out.println("Modificació Incorrecta---NIE Incorrecte");
                            }
                            }while(!nieObj.validarNIE(nie));
                            nieObj.setNIE(nie);
                            this.NIE = nieObj;
                        }else if(!dniObj.validarDNI(dni) && !dni.equals("NIE")){
                            System.out.println("Modificació Incorrecta---DNI Incorrecte");
                        }
                    }while(!dniObj.validarDNI(dni) && !dni.equals("NIE"));
                    dni2 = dni;
                    break;
                case '4':
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Canviar el Correu");
                        ema = teclat.next();
                        if(!emaObj.validarEmail(ema)){
                            System.out.println("Modificació Incorrecta---Correu Incorrecte");
                        }
                    }while(!emaObj.validarEmail(ema));
                    emaObj.setEmail(ema);
                    this.email = emaObj;
                    break;
                case '5':
                    System.out.println("--------------------------------");
                    System.out.println("Canviar l'adreça");
                    domicili = teclat.next();
                    break;
                case '6':
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Canviar el Telefon");
                        tel = teclat.next();
                        if(!telObj.validarTelefon(tel)){
                            System.out.println("Modificació Incorrecta---Telefon Incorrecte");
                        }
                    }while(!telObj.validarTelefon(tel));
                    telObj.setTelefon(tel);
                    this.Telefon = telObj;
                    break;
                case '7':
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Canviar el CC");
                        ccc = teclat.next();
                        if(!cccObj.validarCCC(ccc)){
                            System.out.println("Modificació Incorrecta---Compte Bancari Incorrecte");
                        }
                    }while(!cccObj.validarCCC(ccc));
                    cccObj.setCCC(ccc);
                    this.CCC = cccObj;
                    break;
                case '8':
                    System.out.println("--------------------------------");
                    System.out.println("Canviar la Contrasenya");
                    contrassenya = teclat.next();
                    File fitxer = new File(carpeta);
                    FileWriter fw = new FileWriter (fitxer);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);
                    pw.println("Set-ADAccountPassword " + this.nom + " -NewPassword " + contrassenya);
                    pw.close();
                    Process process2 = Runtime.getRuntime().exec(command);
                    process2.destroy();
                    //Process process = Runtime.getRuntime().exec(command);
                    //process.destroy();
                    break;
                case '9':
                    do{
                        System.out.println("--------------------------------");
                        System.out.println("Canviar la Data");
                        System.out.println("Dia");
                        dia = teclat.nextInt();
                        System.out.println("Mes");
                        mes = teclat.nextInt();
                        System.out.println("Any");
                        año = teclat.nextInt();
                        if(!datObj.validarFecha(dia, mes, año)){
                            System.out.println("Modificació Incorrecta---Data Incorrecta");
                        }
                    }while(!datObj.validarFecha(dia, mes, año));
                    fecha = (dia + "/" + mes + "/" + año);
                    datObj.setFecha(fecha);
                    this.fecha = datObj;
                    break;
                case 's':
                    enrere = true;
                    break;
                case 'S':
                    enrere = true;
                    break;
            }
        //Aqui executem els canvis
            Connection con = new Conexio_bd().connectarBD();
            Statement stmt = con.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            stmt.close();
            String consulta4 = String.format("Update apuntar Set " 
                    + "ID_ALTA = ?, ID_BAIXA = ?, ID_h = ?, DNI_c = ? where DNI_c =  " + "\"%s\"" + ";", this.DNI.getDNI());
            String consulta2 = String.format("Update reserves Set " 
                    + "DNI = ?, Num_Reserves = ? where DNI =  " + "\"%s\"" + ";", this.DNI.getDNI());
            String consulta = String.format("Update client Set " 
                    + "Nom = ?, Cognom = ?, DNI = ?, Correu = ?, Adreça = ?, Telefon = ?, CC = ?, Contrassenya = ?, Data = ? where Nom =  " + "\"%s\"" + ";", nom2);

            if(nom !=null){
                nom2=this.nom;
            }
            if(dni2 !=null){
                dniObj.setDNI(dni2);
                this.DNI = dniObj;
            }
            PreparedStatement sql2 = con.prepareStatement(consulta2);
            PreparedStatement sql = con.prepareStatement(consulta);
            PreparedStatement sql4 = con.prepareStatement(consulta4);
            sql4.setString(4, dni2);
            sql2.setString(1, dni2);
            sql2.setInt(2, this.reserves);
            sql.setString(1, nom2);
            sql.setString(2, this.cognom);
            sql.setString(3, this.DNI.getDNI());
            sql.setString(4, this.email.getEmail());
            sql.setString(5, this.domicili);
            sql.setString(6, this.Telefon.getTelefon());
            sql.setString(7, this.CCC.getCCC());
            sql.setString(8, e.MD5(this.contrassenya));
            sql.setString(9, this.fecha.getFecha());
            System.out.println("------------------------------------");
            System.out.println("Modificació: " + nom2 + " \nCognom: " + this.cognom + " \nDNI: " + this.DNI.getDNI() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement:" + this.fecha.getFecha());
           
            if (sql2.executeUpdate() != 0) {
                System.out.println(" ");
            } else {
                System.out.println("no insertat");
            }
             if (sql.executeUpdate() != 0) {
                System.out.println(" ");
            } else {
                System.out.println("no insertat");
            }
             if (sql4.executeUpdate() != 0) {
                System.out.println(" ");
            } else {
                System.out.println("no insertat");
            }
            Statement stmt2 = con.createStatement();
            stmt2.execute("SET FOREIGN_KEY_CHECKS=1");
            stmt2.close(); 
        } while (!enrere);
    }
    //Creem una funcio per donar de baixa la cual comproba si estas donat de alta pero no de baixa funciona pero si ja estas donat de baixa no et deixa
    public void donarBaixa() throws SQLException{
        Connection con = new Conexio_bd().connectarBD();
        System.out.println("Ficar el DNI del Client:");
        String dni = teclat.next();
        int i=0;
        Statement stmt = con.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            stmt.close();
        String consulta2 = String.format("select * from client where DNI= " + "\"%s\"" + ";", dni);
        PreparedStatement sql2 = con.prepareStatement(consulta2);
        sql2.executeQuery();
        ResultSet rs2 = sql2.executeQuery();
        String consulta5 = "select * from historial ORDER BY ID DESC";
            PreparedStatement sql4 = con.prepareStatement(consulta5);
            sql4.executeQuery();
            ResultSet rs = sql4.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("ID");
            }
        if (rs2.next()) {
            this.nom = rs2.getString("Nom");
            this.cognom = rs2.getString("Cognom");
            this.DNI = new DNI(rs2.getString("DNI"));
            this.email = new email(rs2.getString("Correu"));
            this.domicili = rs2.getString("Adreça");
            this.Telefon = new Telefon(rs2.getString("Telefon"));
            this.CCC = new CCC(rs2.getString("CC"));
            this.contrassenya = rs2.getString("Contrassenya");
            this.fecha = new Data(rs2.getString("Data"));
            }else{
                System.out.println("No s'ha trobat el client amb el DNI: "+ dni);
            }
        String consulta3 = String.format("select * from apuntar where DNI_c= " + "\"%s\"" + " ORDER BY ID_BAIXA ASC;", dni);
        PreparedStatement sql3 = con.prepareStatement(consulta3);
        sql3.executeQuery();
        ResultSet rs3 = sql3.executeQuery();
        if (rs3.next()) {
            alta = rs3.getDate("ID_ALTA");
            baixa = rs3.getDate("ID_BAIXA");
            id = rs3.getInt("ID_h");
            dni3 = rs3.getString("DNI_c");
        }else{
            System.out.println("-----------------------");
            System.out.println("El client No Existeix");
            System.out.println("-----------------------");
        }
        if(baixa==null){
            System.out.println("El client que vol donar de baixa es el seguent : " + this.nom + " \nCognom: " + this.cognom + " \nDNI: " + this.DNI.getDNI() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement:" + this.fecha.getFecha());
            
            do{
                System.out.println("-------------------");
                System.out.println("Segur que el vol donar de baixa?");
                System.out.println("-------------------");
        
                System.out.println("1. Si");
                System.out.println("2. No");
                System.out.println("s. Sortir");
            
                String sa = st.next();
                opcio = sa.charAt(0);  
                System.out.print("Ha premut l'opcio: ");
                switch (opcio){
                    case '1':
                        System.out.println("Si");
                        System.out.println("--------");
                        i++;
                        int ID = i+this.id;
                            String consulta = String.format("Update apuntar Set " 
                                + "ID_ALTA = ?, ID_BAIXA = ?, ID_h = ?, DNI_c = ? where ID_BAIXA IS NULL AND DNI_c =  " + "\"%s\"" + ";", dni);
                            PreparedStatement sql = con.prepareStatement(consulta);
                            LocalDate date = LocalDate.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            sql.setDate(1, alta);
                            sql.setDate(2, java.sql.Date.valueOf(date.format(formatter)));
                            sql.setInt(3, ID);
                            sql.setString(4, dni3);
                            int res = sql.executeUpdate();
                            if(res==1){
                                System.out.println("El client : " + this.nom + " \nCognom: " + this.cognom + " \nDNI: " + this.DNI.getDNI() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement:" + this.fecha.getFecha());
                                System.out.println("-----------------------");
                                System.out.println("S'ha Donat de Baixa");
                                System.out.println("-----------------------");
                                enrere=true;
                            }else{
                                System.out.println("");
                            }
                        break;
                    case '2':
                        System.out.println("No");
                        enrere=true;
                        break;
                    case 's':
                    System.out.println(" Sortir");
                    enrere = true;
                    break;    
                    case 'S':
                        System.out.println(" Sortir");
                        enrere=true;
                        break;
                    default:
                    System.out.println("L'Opció no és vàlida");
                }
            }while (!enrere);
            
        }else if(baixa!=null && this.DNI.getDNI().equals(dni)){
            System.out.println("El client : " + this.nom + " \nCognom: " + this.cognom + " \nDNI: " + this.DNI.getDNI() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement:" + this.fecha.getFecha());
            System.out.println("-----------------------");
            System.out.println("Ja esta donat de Baixa");
            System.out.println("-----------------------");
        }
         Statement stmt2 = con.createStatement();
            stmt2.execute("SET FOREIGN_KEY_CHECKS=1");
            stmt2.close(); 
        

    }
    //Creem una funció per donar de alta la cual comproba si estas donat de baixa 
    public void donarAlta() throws SQLException{
        int i=0;
        Connection con = new Conexio_bd().connectarBD();
        Statement stmt = con.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            stmt.close();
            String consulta = "select * from historial ORDER BY ID DESC";
            PreparedStatement sql4 = con.prepareStatement(consulta);
            sql4.executeQuery();
            ResultSet rs = sql4.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("ID");
            }
        System.out.println("Ficar el DNI del Client que vol tornar a donar de Alta:");
        String dni = teclat.next();
        String consulta2 = String.format("select * from client where DNI= " + "\"%s\"" + ";", dni);
        PreparedStatement sql2 = con.prepareStatement(consulta2);
        sql2.executeQuery();
        ResultSet rs2 = sql2.executeQuery();
        if (rs2.next()) {
            this.nom = rs2.getString("Nom");
            this.cognom = rs2.getString("Cognom");
            this.DNI = new DNI(rs2.getString("DNI"));
            this.email = new email(rs2.getString("Correu"));
            this.domicili = rs2.getString("Adreça");
            this.Telefon = new Telefon(rs2.getString("Telefon"));
            this.CCC = new CCC(rs2.getString("CC"));
            this.contrassenya = rs2.getString("Contrassenya");
            this.fecha = new Data(rs2.getString("Data"));
            System.out.println("El client que vol tornar a donar d'alta es el seguent: " + this.nom + " \nCognom: " + this.cognom + " \nDNI: " + this.DNI.getDNI() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement:" + this.fecha.getFecha());
            }else{
                System.out.println("No s'ha trobat el client amb el DNI: "+ dni);
            }
        
        String consulta3 = String.format("select * from apuntar where DNI_c= " + "\"%s\"" + " ORDER BY ID_BAIXA ASC;", dni);
        PreparedStatement sql3 = con.prepareStatement(consulta3);
        sql3.executeQuery();
        ResultSet rs3 = sql3.executeQuery();
        if (rs3.next()) {
            alta = rs3.getDate("ID_ALTA");
            baixa = rs3.getDate("ID_BAIXA");
            int id2 = rs3.getInt("ID_h");
            dni3 = rs3.getString("DNI_c");
        }else{
            System.out.println("-----------------------");
            System.out.println("El client No Existeix");
            System.out.println("-----------------------");
        }
        
        if(alta!=null && baixa!=null){
            i++;
            int ID = i+this.id;
            PreparedStatement sql5 = con.prepareStatement("insert into historial "
                    + "(ID) "
                    + "values "
                    + "(?);");
            sql5.setInt(1, ID);
            PreparedStatement sql6 = con.prepareStatement("insert into apuntar "
                    + "(ID_ALTA, ID_BAIXA, ID_h, DNI_c) "
                    + "values "
                    + "(?, ?, ?, ?);");
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            sql6.setDate(1, java.sql.Date.valueOf(date.format(formatter)));
            sql6.setDate(2, null);
            sql6.setInt(3, ID);
            sql6.setString(4, this.DNI.getDNI());
            int res = sql5.executeUpdate();
            int res2 = sql6.executeUpdate();
            if(res==1 && res2==1){
                System.out.println("El client : " + this.nom + " \nCognom: " + this.cognom + " \nDNI: " + this.DNI.getDNI() + " \nCorreu Electrònic: " + this.email.getEmail() + " \nDomicili: " + this.domicili + " \nTelefon: " + this.Telefon.getTelefon() + " \nCompte Bancari:" + this.CCC.getCCC() + " \nContrassenya: " + this.contrassenya+ " \nData de Naixement:" + this.fecha.getFecha());
                System.out.println("-----------------------");
                System.out.println("S'ha Donat de Alta");
                System.out.println("-----------------------");
                enrere=true;
            }else{
                System.out.println("");
            }
        }else if(alta!=null && baixa==null){
            System.out.println("El client ja esta donat de alta");
        }
        Statement stmt2 = con.createStatement();
            stmt2.execute("SET FOREIGN_KEY_CHECKS=1");
            stmt2.close(); 
        
    }
   

}
