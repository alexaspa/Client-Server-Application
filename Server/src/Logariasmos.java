// SPANOU ALEXANDRA
// ICSD09134
// KATANEMHMENA SYSTHMATA
// DS_2st_exercise

import java.io.Serializable;
public  class  Logariasmos  implements Serializable{
    
    private String onoma;
    private String id_account;
    private double upoloipo;
    
   public Logariasmos(){
	   
   onoma=null;
   id_account=null;
   upoloipo=0;
   }
   public Logariasmos(String id_account,String onoma,double upoloipo ){
    
    	this.onoma=onoma;
    	this.upoloipo=upoloipo;
    	this.id_account=id_account;
    }
 
    public Logariasmos(String onoma,double upoloipo , int i){
    
    	this.onoma=onoma;
    	this.upoloipo=upoloipo;
    	this.id_account=null;
    }
    
    
    public Logariasmos(String id_account,double upoloipo ){
    	
    
    	this.upoloipo=upoloipo;
    	this.id_account=id_account;
    }
    public Logariasmos(String id_account){
    	
    	
    	this.id_account=id_account;
    }
   public String get_onoma(){
   return onoma;
   }
   public String get_id_account(){
   return id_account;
   }
   public double get_upoloipo(){
   return upoloipo;
   }
   
   public void set_id_account(String id_account){
	   
	   this.id_account=id_account;
	   
   }
   public double withdraw(double poso){
	   double temp1, temp2;
	   
	   temp1 = poso;
	   temp2 = this.upoloipo;
	   if(temp2<temp1){
		   return -1;// an zhth8ei analhpsh megaluterou posou ap to upoloipo
	       
	   }else
		   return temp2-temp1;
   }
   public void set_withdraw(double neo_upoloipo){
	  
	   this.upoloipo=neo_upoloipo;
   }
   
   
   public double deposit(double poso){
	   double temp1,temp2;
	   
	   temp1 = poso;
	   temp2 = this.upoloipo;
	   return temp2+temp1;
	   
   }
   public void set_deposit(double neo_upoloipo){
	   
	  
	   this.upoloipo=neo_upoloipo;
   }
    public String toString(){        
    return "\nOnoma : "+this.onoma+"\nId account : "+this.id_account+"\nYpoloipo : "+this.upoloipo+"\n";      
    }
  
   
}
