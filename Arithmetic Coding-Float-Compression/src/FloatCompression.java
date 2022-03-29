import java.util.ArrayList;

public class FloatCompression {

	
	
	
	
	
	
	public static double compression(  char []charArray, ArrayList<Character>unique, double[]freqs) {
		
		double []updateFreq=new double [freqs.length];//for freq between range
		  
		  for (int i=0;i<unique.size();i++) {
			  
		 	 //System.out.println(freqs[i]);
		 	 updateFreq[i]=freqs[i];/////////////
		  }

     
		  double upper=1,lower=0,range=1;
	  for (int i=0;i<charArray.length;i++)
	  {
		  for (int j=0;j<unique.size();j++) {
		  if (charArray[i]==unique.get(j))
		  {
			 
				  range=updateFreq[j+1]-updateFreq[j];
				  lower=updateFreq[j];
				  upper=updateFreq[j+1];
				  System.out.println(" range of ( "+unique.get(j)+" )>>>>>  "+lower+"     "+upper);
				  updateFreq[updateFreq.length-1]=upper;
 				  updateFreq[0]=lower;
			  

                for(int k=1;k<updateFreq.length-1;k++) //for update 
				  updateFreq[k]=lower+range*freqs[k];
				 
  
              
              break;
		  }

	       }
		  }
	  double code=(lower+upper)/2;
	  
	     System.out.println(" Output code = "+code);
	     return code;
	   
	  }
		
	
	public static void decompression(double code,int numberOfCharacer , ArrayList<Character>unique, double[]freqs) {
		
		double []updateFreq=new double [freqs.length];
      String outputText="";
          //updateFreq[0]=0;
		  for (int i=0;i<unique.size();i++) {
			  
		 	 //System.out.println(freqs[i]);
		 	 updateFreq[i]=freqs[i];/////////////
		  }
		  
		  double upper=1,lower=0,range=1;
		
 	 
         for (int i=0;i<numberOfCharacer;i++)
         {
        	 for (int j=0;j<updateFreq.length;j++)
        	 {
        		 
        			 if (code<=updateFreq[j+1]&&code>updateFreq[j])
        			 {
        				 outputText+=unique.get(j);
        				 lower=updateFreq[j];
        				 upper=updateFreq[j+1];
        				 range=updateFreq[j+1]-updateFreq[j];
        				 updateFreq[updateFreq.length-1]=upper;
        				 updateFreq[0]=lower;
        				 
        				 
        				 
        				 break;
        			 }
        		
        		 
        	 }
        	 for(int k=1;k<updateFreq.length-1;k++)
			  {
				  updateFreq[k]=lower+range*freqs[k];
				  //System.out.println( updateFreq[k]);
			  }
         }

		
        
        
        System.out.println("output of decompression is =    "+outputText);
		
	}
		
	
	public static void main(String[] args) {
		double code;
		
		 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		   String s="mo salah the best";       //text we want to compress it is
		   //the text                                                            
	       char []charArray=s.toCharArray();
	       int numberOfCharacer=s.length();
	       ArrayList<Character>unique=new ArrayList<Character>();
		   ArrayList<Integer>freq=new ArrayList<Integer>();
			
	    
	      System.out.println("the text we want to compress it is >>      "+s);
	      for (int i=0;i<charArray.length;i++)  //make an array for unique character
	      { 
	   	   int yes=0;
	   	   for (int j=0;j<unique.size();j++)
	   	   {
	   		   if (unique.get(j).equals(charArray[i]))
	   		   {
	   			   yes++;
	   			   break;
	   		   }
	   	   }
	   	   if (yes==0)
	   		   unique.add(charArray[i]);
	       }
	    
	      for (int i=0;i<unique.size();i++)   //make an array for frequent character    
	      { 
	   	       int count=0;
	   	       for (int j=0;j<charArray.length;j++)
	   	        {
	   		     if (unique.get(i)==charArray[j])
	   			   count++;
	   	          }
	   	   freq.add(count);
	   	   
	   	   }
	      
	     
	      double[]freqs=new double[freq.size()+1];freqs[0]=0.0;
	      for (int i=0;i<freq.size();i++) {
	    	  freqs[i+1]=(double)freq.get(i)/s.length();
	    	  
	      }
	   
  for (int i=1;i<freqs.length;i++) {	    	  
	    	  freqs[i]+=freqs[i-1];
	      }
 
      //in this scope we will have the array for frequent character (freqs)--and we have array for unique character(uniqe)
      // and we have a array of original character that will be compressed (charArray)
	 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
          
         System.out.println(" -------------------compression output-------------------");
           
		   code = compression(charArray, unique,freqs);  //compression function return the compressed code
		
		System.out.println(" -------------------decompression output for code = "+code+"   -------------------");
		
		  decompression(code,numberOfCharacer,unique,freqs);
	}
	}




   








