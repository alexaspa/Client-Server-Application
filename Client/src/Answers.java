// SPANOU ALEXANDRA
// ICSD09134
// KATANEMHMENA SYSTHMATA
// DS_2st_exercise

import java.io.Serializable;


public class Answers  implements Serializable{//Klash pou dhmiourgei antikeimena gia th sunomilia tou client-server
    protected String apanthsh;
    
public Answers(String apanthsh)
{
   this.apanthsh=apanthsh; 
}

public void set_apanthsh(String apanthsh){
	
	this.apanthsh=apanthsh;
	
	
}
public  String get_apanthsh()
{
    return apanthsh;
}
public String toString(){        
    return "\nAnswer : "+this.apanthsh+"\n";      
    }
}
