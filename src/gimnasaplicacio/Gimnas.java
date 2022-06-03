/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasaplicacio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DAM
 */
public class Gimnas {
    private String nom;
    private String CIF;
    private String telefon;
    private boolean enrere=false;
    ArrayList<Client> clients;
    ArrayList<Activitat> activitats;
    Scanner teclat = new Scanner (System.in);
    Scanner st = new Scanner(System.in);
    
    Conexio_bd con = new Conexio_bd();
    Client cl = new Client();
    Activitat a = new Activitat();
    public void gestionarGimnas() throws SQLException, ParseException, IOException{
        do{
            System.out.println("**********MENU GIMNAS*********");
        
            System.out.println("1. Gestio client");
            System.out.println("2. Llistar Clients Per Edat");
            System.out.println("3. Llistar Clients Per Cognom");
            System.out.println("4. Llistar Clients Per Reserves");
            System.out.println("5. Activitats del Dia");
            System.out.println("s. Sortir");
            
            String sa = st.next();
            char opcio = sa.charAt(0);  
            System.out.print("Ha premut l'opcio: ");
            switch (opcio){
                case '1':
                    System.out.println(" Gestionar el Client ");
                    cl.Gestio_clients();
                    break;
                 case '2':
                    System.out.println(" Llistar els Clients Per Edat");
                    clients = cl.llistarEdat();
                    for(Client c:clients){
                        System.out.println(c);
                    }
                    break;
                 case '3':
                    System.out.println(" Llistar els Clients Per Cognom");
                    clients = cl.llistarCognom();
                    for(Client c:clients){
                        System.out.println(c);
                    }
                    break;
                 case '4':
                    System.out.println(" Llistar els Clients Per Reserves");
                    clients = cl.llistarReserves();
                    for(Client c:clients){
                        System.out.println(c);
                    }
                    break;
                 case '5':
                     System.out.println(" Activitats Del Dia");
                    activitats = a.actDias();
                    for(Activitat a:activitats){
                        System.out.println(a);
                    }
                    break;
                case 'S':
                    System.out.println(" Tancar Aplicació");
                   enrere=true;
                   break;
                default:
                   System.out.println("L'Opció no és vàlida");
            }
        } 
        while (!enrere);
                  
    }
    
    
 }

