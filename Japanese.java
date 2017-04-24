/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package japanese;
//Fuente: Droid sans fallback

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author absa
 */
public class Japanese {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Japanese j = new Japanese();        
        int option;
        int testSize=20;
        try{
           
            Random r= new Random();                        
            Scanner userInput= new Scanner(System.in);            
            ArrayList<String> jap = new ArrayList<String>();
            ArrayList<String> eng = new ArrayList<String>();            
            System.out.println("Bienvenido, para practicar vocabulario introduzca 1.");
            System.out.println("Para practicar verbos introduzca 2");
            option=userInput.nextInt();            
            if(option==1){
                System.out.println("Ha escogido vocabulario");
                j.vocabulario(jap, eng);
            }
            else{
                System.out.println("Ha escogido verbos");
                j.verbos(jap, eng);
            }
            System.out.println("Test Size?");
            testSize=userInput.nextInt();
            userInput.nextLine();
                for (int i = 0; i < testSize; i++) {
                    System.out.println("---------------------------------");
                    int num= r.nextInt(jap.size());                    
                    System.out.println("Siguiente prueba: "+jap.get(num));
                    System.out.println("Presione enter para mostrar traducción");
                    String s= userInput.nextLine();                     
                    System.out.println("Traducción: "+eng.get(num));
                    System.out.println("");


                }  
        }
        catch(Exception e){
            System.out.println("File not found");
        }   
       
    }
    public void verbos(ArrayList<String> original, ArrayList<String> traducido){      
        String path = "/home/absa/Documentos/Proyectos/Lab/Cuestionarios/Idiomas/verbosJapones.txt";
        File f= new File(path);
        String regex= "[^\\s]";
        String line[];
        try{
            Scanner scv= new Scanner(f);
            Pattern p = Pattern.compile(regex);
            Matcher m;
            int count=0;            
            while(scv.hasNext()){
                line= scv.nextLine().split("-");                                
               original.add(line[0]);
               traducido.add(line[2]);
               
            }              
                
            scv.close();
        }
        catch(Exception E){
        
            System.out.println("Fallaron verbos");}
               
    }
    public void vocabulario(ArrayList<String> jap,ArrayList<String> eng){
        try{
            String address="/home/absa/Documentos/Proyectos/Lab/Cuestionarios/Idiomas/palabrasjapones.txt";           
            File file = new File(address);
            Scanner sc = new Scanner(file);            
            String toMatch;
            Pattern p = Pattern.compile("(\\w|[\\p{IsHiragana}\\p{IsHan}\\p{IsKatakana}"
                    + "\u2E80-\u2FD5\uFF5F-\uFF9F\u3000-\u303F\u31F0-\u31FF\u3220-\u3243\u3280-\u337F"
                    + "\u30A0-\u30FF])+");            
            String tecla="";
            String empty="";
            int testSize=20;
            while(sc.hasNextLine()){
                toMatch= sc.nextLine();
                //System.out.println("Line: "+toMatch);
                Matcher m = p.matcher(toMatch);
                //if(m.find())
                 //   System.out.println("Found something");
               if(m.find()) 
                   empty=m.group();
               if(m.find()) 
                jap.add(m.group());
               if(m.find()) 
                eng.add(m.group());
                }
            
  sc.close();
        }
        catch(Exception e){
            System.out.println("Falló vocabularios");
        }
    }
    
}
