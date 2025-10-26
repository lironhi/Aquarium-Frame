/*
 * Liron Himbert - 337731797
 * Yossef Simhon - 311217020*/
public class Singleton {

	 private static Singleton instance = null;
	 /* A private Constructor prevents any other
	 * class from instantiating.*/
	 private Singleton(){ }
	 /* Static 'instance' method */
	 public static Singleton getInstance( ){
		 if(instance == null)
			 synchronized(Singleton.class) {}
		 return instance;
	 }
	 
	 public void setSingleton()
		{
			this.instance=null;	
		}
	 
}
