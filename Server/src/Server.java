// SPANOU ALEXANDRA
// ICSD09134
// KATANEMHMENA SYSTHMATA
// DS_2st_exercise

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
    
    public static void main(String[] args) {
    	
    	ArrayList<Logariasmos> logariasmoi = new ArrayList<Logariasmos>();
        logariasmoi.add(new Logariasmos("","",0));// vazw ena stoixeio sth lista gia na mhn kollaei sta deposit, withdraw xwris create.. 
    	Logariasmos logariasmos;
        try{
            
            ServerSocket server = new ServerSocket(5555,50);
            System.out.println("Waiting Incoming Connection...");            
            System.out.println("Local Address :"+server.getInetAddress()+" Port :"+server.getLocalPort());
            Socket sock = server.accept();//o server perimenei gia syndesi apo client kai molis prospathisei ena client tin "akouei" kai tin apodexetai me apotelesma tin dimiourgia enos socket
            System.out.println("Client found");

            ObjectInputStream input = new ObjectInputStream(sock.getInputStream()); //diavazei apo to socket ena antikeimeno
            ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());//grafei sto socket ena antikeimeno
            //epeidi sto socket mporoume na grapsoume ena typou arxeiou epilegoume kai ta string na ta perasoume me to ObjectInputStream kai to ObjectOutputStream
         
           Answers strin = null;
        		 strin= (Answers)input.readObject();//diavazoume ti egrapse o xristis!
            System.out.println("The Client Says : " + strin.get_apanthsh());//emfanizw gia test ti eipe o xristis 
            //akolouthoume to protokolo 
            
              if (strin.get_apanthsh().equalsIgnoreCase("START")){ //ean grapsei start xwris na mas noiazoun ta kefalaia i mh
                
                output.writeObject(new Answers("WAITING"));//kai grafw sto xristi waiting
                output.flush();//"katharizei" to output 
             	
                while(true){
                	
                	   strin = (Answers)input.readObject();	
                	
                            System.out.println("The Client Says :" + strin.get_apanthsh());
                	
                	if(strin.get_apanthsh().equalsIgnoreCase("CREATE")){//an steilei create
                		
                		 logariasmos = (Logariasmos)input.readObject();
                                 System.out.println("The Client Says :" + logariasmos.get_onoma()+" "+ logariasmos.get_upoloipo());
                		 logariasmos.set_id_account("BANK"+logariasmoi.size());
                		logariasmoi.add(logariasmos);
                		output.writeObject(new Logariasmos(logariasmos.get_id_account(),logariasmos.get_upoloipo()));
                		output.flush();
                		output.writeObject(new Answers("OK"));
                		output.flush();
                	}
                	
                	if(strin.get_apanthsh().equalsIgnoreCase("WITHDRAW")){//an steilei withdraw
                	
                		
                		 int counter=0;
                		logariasmos = (Logariasmos)input.readObject();
                		
                		 System.out.println("The Client Says :" + logariasmos.get_id_account());
                		
                		
                		for(int i=0;i<logariasmoi.size();i++){//elegxw sth lista mou, an uparxei logariasmos me kritirio to id_account
                			 
                                   
                                    
                                    if(logariasmoi.get(i).get_id_account().equals(logariasmos.get_id_account())){
                                        counter++;// to xrhsimopoiw gia na dwsw minima an de bre8ei o logariasmos
                				output.writeObject(new Logariasmos(logariasmos.get_id_account()));
                                                output.flush();
                                           logariasmos = (Logariasmos)input.readObject();
                                             
                				
                				if(logariasmoi.get(i).withdraw(logariasmos.get_upoloipo())==-1){//epistrefw -1 se periptwsh analhpshs megaluterou posou
                				                					
                					output.writeObject(new Answers("Den uparxei arketo paradaki ston logariasmo "+ "\n"+"Ypoloipo: "+ logariasmoi.get(i).get_upoloipo()));
                					output.flush();
                                                        output.writeObject(new Answers("OK"));
                                                        output.flush();
                					break;
                				}else{
                   					logariasmoi.get(i).set_withdraw(logariasmoi.get(i).withdraw(logariasmos.get_upoloipo()));
                   					output.writeObject(new Logariasmos(logariasmoi.get(i).get_id_account(),logariasmoi.get(i).get_upoloipo()));
                					output.flush();
                                                        output.writeObject(new Answers("OK"));
                                                        output.flush();
                                                        
                					break;
                				}//end else
                				
                			}//end if
                			 
                		}//end for
                		 if(counter==0){//an den uparxei o logariasmos sth lista
                                    
                                         output.writeObject(new Answers("ERROR"));
                                         output.flush();
                                         output.writeObject(new Answers("Den brethike o logariasmos"));
                                         output.flush();                                      
                                    }           		               		
                	}
                	if(strin.get_apanthsh().equalsIgnoreCase("DEPOSIT")){//an steilei deposit
                		
                		int counter=0;
                		 logariasmos = (Logariasmos) input.readObject();
                		
                		 System.out.println("The Client Says : " + logariasmos.get_id_account());                		
                		
                		for(int i=0;i<logariasmoi.size();i++){ //elegxw sth lista mou, an uparxei logariasmos me kritirio to id_account                                  
                                                   			 
                			if(logariasmoi.get(i).get_id_account().equals(logariasmos.get_id_account())){
                                            counter++;
                				output.writeObject(new Logariasmos(logariasmos.get_id_account()));
                                                output.flush();
                                           logariasmos = (Logariasmos)input.readObject();      	   				
                				   
                					logariasmoi.get(i).set_deposit(logariasmoi.get(i).deposit(logariasmos.get_upoloipo()));                				
                					output.writeObject(new Logariasmos(logariasmoi.get(i).get_id_account(),logariasmoi.get(i).get_upoloipo()));
                   					output.flush();
                                                        output.writeObject(new Answers("OK"));
                                                        output.flush();        				
                			}//end if                			 
                		}//end for
                		
                		   if(counter==0){//an den uparxei o logariasmos sth lista
                                    
                                         output.writeObject(new Answers("ERROR"));
                                         output.flush();
                                         output.writeObject(new Answers("Den brethike o logariasmos"));
                                         output.flush();
                                        break;
                                    }          		
                	}
                        if(strin.get_apanthsh().equalsIgnoreCase("END"))
                			break;     	
                	
                }//end while
                input.close();//kleinoume ola ta stream kai to socket
	            output.close();
	            sock.close();                		
            }
            else { //ean den grapsei hello tote
               output.writeObject(new Answers("Not welcomed"));//stamataei kai emfanizei to katallilo mynima
               output.flush(); //"katharizei" to output 
            }
            input.close();//kleinoume ola ta stream kai to socket
            output.close();
            sock.close();            
            }
            catch (Exception ex){//exception
                System.out.println("!!!Error during I/O!!!");
                ex.getMessage();
                ex.printStackTrace();
            } 
    }           
}
