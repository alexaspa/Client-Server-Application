// SPANOU ALEXANDRA
// ICSD09134
// KATANEMHMENA SYSTHMATA
// DS_2st_exercise

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
  
    public static void main(String[] args) throws IOException, ClassNotFoundException {
   Logariasmos logariasmos = null;
   Socket sock = null;
   String onoma=null;
   double upoloipo;
   String id_logariasmou;
   double poso;
   String strin;
    try {
            sock = new Socket("localhost", 5555);
            System.out.println("Client ip : "+sock.getLocalAddress());
            System.out.println("Client port: "+sock.getLocalPort());
            System.out.println("IP Server: "+sock.getInetAddress());
            System.out.println("Port Server: "+sock.getPort()); //emfanizoun ta stoixeia tou server kai tou client

            ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream()); //metavliti pou voithaei stin eggrafi twn antikeimenwn apo to socket 
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream()); // metavliti pou voithaei na diavazei antikeimena apo to socket 
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //metavliti pou diavazei ti grafei o xristis me to pliktrologio
         
            
            Answers userInput = null; //diavazei tin prwti grammi pou grafei o xristis
            System.out.println("Patiste START gia na ksekinisete...");
                    
               userInput=new Answers(stdIn.readLine());
               if(userInput.get_apanthsh().equalsIgnoreCase("START")){
            output.writeObject(new Answers("START")); //to pernaw to parapanw ws antikeimeno typou Answers ston server
            output.flush();
               }else{
               output.writeObject(new Answers(userInput.get_apanthsh()));
               output.flush();
               }
               
            Answers talogiatouserver ;
                   talogiatouserver = (Answers)in.readObject();//diavazei ws antikeimeno typou string ti apantaei o server
            System.out.println("The Server Says : " + talogiatouserver.get_apanthsh());//kai ta emfanizw stin perioxi tou client
      
            if (talogiatouserver.get_apanthsh().equals("WAITING")){ //ean einai waiting
                       		
            		do{
            			
                    System.out.println("Ti tha thelate na kanete?");
                    
                    userInput=new Answers(stdIn.readLine());                    
                    
                    if(userInput.get_apanthsh().equalsIgnoreCase("CREATE")){ //an dialeksei create
                        
                         output.writeObject(new Answers("CREATE"));
                    	 output.flush();
                         System.out.println("Dwste to onomatepwnumo tou logariasmou sas:");
                    	 onoma=stdIn.readLine();
                      	 System.out.println("Dwste to upoloipo tou logariasmou sas");
                    	 upoloipo= Double.parseDouble(stdIn.readLine());                    	                    
                    	 output.writeObject(new Logariasmos(onoma,upoloipo,1));
                    	 output.flush();
                    	
                    	while(true){
                          	Object a =  in.readObject();
                        	
                        	if(a instanceof Answers){	
                        		
                           		System.out.println("The Server Says : "+ ((Answers) a).get_apanthsh());
                        	if(((Answers) a).get_apanthsh().equals("OK")){
                        		                            
                            	break;
                        	}}
                        	else 
                        		System.out.println("The Server Says : " + ((Logariasmos)a).get_id_account() + " YPOLOIPO:" + ((Logariasmos)a).get_upoloipo());
                        	}
                    	
                    }else if(userInput.get_apanthsh().equalsIgnoreCase("WITHDRAW")){ //an dialeksei withdraw                    	

                    	output.writeObject(new Answers("WITHDRAW"));
                    	output.flush();
                    	System.out.println("Dwse ton arithmo tou logariasmou sou: ");
                    	id_logariasmou=stdIn.readLine(); 
                        output.writeObject(new Logariasmos(id_logariasmou));
                        output.flush();
                      	Object a =  in.readObject();
                      
                        if(a instanceof Answers){
                            System.out.println("The Server Says : "+ ((Answers) a).get_apanthsh());
                            if(((Answers) a).get_apanthsh().equals("ERROR")){
                                a = in.readObject();
                                System.out.println("The Server Says : "+ ((Answers) a).get_apanthsh());
                    		output.writeObject(new Answers("END"));
                                output.flush();
                                break;                                
                            }                        
                        }else{                        
                        
                    	System.out.println("Dwse to poso analipsis: ");
                    	poso=Double.parseDouble(stdIn.readLine());
                    	output.writeObject(new Logariasmos(id_logariasmou,poso));
                    	output.flush();    
                    	while(true){
                          a =  in.readObject();
                    	
                    	if(a instanceof Answers){	
                    		
                       		System.out.println("The Server Says : "+ ((Answers) a).get_apanthsh());
                    	if(((Answers) a).get_apanthsh().equals("OK")){
                    		
                        	break;
                        	
                    	}}
                    	else 
                    	    System.out.println("The Server Says : "+ "Ypoloipo:" +((Logariasmos) a).get_upoloipo());
                    	}}
                    }else if(userInput.get_apanthsh().equals("DEPOSIT")||userInput.get_apanthsh().equals("deposit")){
                    	
                    	output.writeObject(new Answers("DEPOSIT"));
                    	output.flush();
                    	System.out.println("Dwse ton arithmo tou logariasmou sou: ");
                    	id_logariasmou=stdIn.readLine();
                        output.writeObject(new Logariasmos(id_logariasmou));
                        output.flush();
                        Object a =  in.readObject();
                      
                        if(a instanceof Answers){
                            System.out.println("The Server Says : "+ ((Answers) a).get_apanthsh());
                            if(((Answers) a).get_apanthsh().equals("ERROR")){
                                 a = in.readObject();
                                System.out.println("The Server Says : "+ ((Answers) a).get_apanthsh());
                    		output.writeObject(new Answers("END"));
                                output.flush();
                                break;
                            }
                        
                        }else{
                        
                    	System.out.println("Dwse to poso katathesis: ");
                    	poso=Double.parseDouble(stdIn.readLine());                   	
                     	output.writeObject(new Logariasmos(id_logariasmou,poso));
                    	output.flush(); 
                    	
                    	while(true){
                          	 a =  in.readObject();
                        	
                        	if(a instanceof Answers){                        		
                           		System.out.println("The Server Says : "+ ((Answers) a).get_apanthsh());
                        	if(((Answers) a).get_apanthsh().equals("OK")){                        		
                            	break;
                        	}}
                        	else 
                        	    System.out.println("The Server Says : "+ "Ypoloipo:" +((Logariasmos) a).get_upoloipo());
                                
                        	}}
                    	
                    }else if(userInput.get_apanthsh().equalsIgnoreCase("END")){//an dwsei end diakoptetai h sundesh..
                    	
                    	
                    	output.writeObject(new Answers("END"));
                    	output.flush();
                      	break;
                    }else{//an grapsei otidhpote allo diakoptetai h sundesh..
                       System.out.println("LA8OS EPILOGH!!");
                       output.writeObject(new Answers("END"));
                       output.flush();
                       break;}
                    }while(true);                   
                                       
                    in.close();//kleinoume ola ta stream kai to socket
                    output.close();
                    stdIn.close();
                    sock.close();  
                                          
            }else{
            System.out.println(talogiatouserver.get_apanthsh());
            output.close();
            in.close();
            stdIn.close();
            sock.close();            
            }
                    
        } catch (UnknownHostException e) {
            System.err.println("!!!Host not found!!!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("!!!Error during I/O!!!");
            System.exit(1);
        }
   
    }
    
}
