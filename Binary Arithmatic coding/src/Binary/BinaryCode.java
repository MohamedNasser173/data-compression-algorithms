package Binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BinaryCode {
	
	ArrayList<Character>unique;
    ArrayList<Float>freq,pro;
    String text;
    int k;
    //float code;
    ArrayList<Integer> E = new ArrayList<Integer>();
    
    BinaryCode()
    {
    	unique=new ArrayList<Character>();
        freq=new ArrayList<Float>();
        pro=new ArrayList<Float>();
        text="";
        k=0;
        //code=0;
        E = new ArrayList<Integer>();
    	
    }
    public void getData() {
    	
 	   Scanner input = new Scanner(System.in);
       System.out.println("Welcome to Binary Coding Compression Program: ");
       System.out.println("Enter the text please ");
      
       String data;
       
       data = input.next();
       String s=data;
       text=data;
		char []toCharacter=s.toCharArray();
       
		  for (int i=0;i<toCharacter.length;i++)  //make an array for unique character
	      { 
	   	   int yes=0;
	   	   for (int j=0;j<unique.size();j++)
	   	   {
	   		   if (unique.get(j).equals(toCharacter[i]))
	   		   {
	   			   yes++;
	   			   break;
	   		   }
	   	   }
	   	   if (yes==0)
	   		   unique.add(toCharacter[i]);
	       }
		  
		  
		  for (int i=0;i<unique.size();i++)
		  {
			 
			      System.out.println("Enter Lower  and Probability of "+unique.get(i));
			      float  p=input.nextFloat();
		          pro.add(p);
		           
		  }
		  freq.add((float)0.0);
		 
		  float x=0;
		  for (int i=0;i<pro.size();i++)
		  {
			  
			  x+=pro.get(i);
			  freq.add(x);
			  
		  }
		  
		  for (int i=0;i<freq.size();i++)
			  System.out.println(freq.get(i));
		  
		  if ( freq.get(freq.size()-1)!=1.0)
		  {
			  System.out.println("Error out of rang");
			  System.exit(0);		  }
			  

		
    input.close();
    }

    public void Dr_Example()
    {
    	text="acba";
    	unique.add('a');
    	unique.add('b');
    	unique.add('c');
    	freq.add((float)0);
    	freq.add((float)0.8);
    	freq.add((float)0.82);
    	freq.add((float)1);
    	
    	pro.add((float)0.8);
    	pro.add((float)0.02);
    	pro.add((float)0.15);
    	
    	System.out.println(text);
    	
    	
    	
    	
    }
    
    
    public void getK()
    { float[] temp = new float [pro.size()];
    	for (int i=0;i<pro.size();i++)
   	{	
   				
   				temp[i]=pro.get(i);
   	}
    	
    	Arrays.sort(temp);
    	
    	 float min=temp[0];
   
     
   
  
   	  boolean check = true;
        while (check == true)
         {
            if ((1/(Math.pow(2,k))) < min)
            {
            	
                check = false;
                 break;
            }
             k++;
         }
        
        System.out.println(" k =  "+k);
        
        
    }
    
    public void compression()
    {
    	
    	float []updateFreq=new float [freq.size()];//for freq between range
		  
		  for (int i=0;i<unique.size();i++) {
			  
		 	 updateFreq[i]=freq.get(i);
		  }

   
		  float upper=1,lower=0,range=1;
		  char []charArray=text.toCharArray();
	  for (int i=0;i<charArray.length;i++)
	  {
		  for (int j=0;j<unique.size();j++) {
		  if (charArray[i]==unique.get(j))
		  {
			 
				  
				  lower=updateFreq[j];
				  upper=updateFreq[j+1];
				 
				   boolean check = true;
		            while (check == true)
		            { 
		            	
		               if ((lower > 0.5) && (upper > 0.5))
		               {
		            	   System.out.println(" range of ( "+unique.get(j)+" )>>>>>  "+lower+"     "+upper);
		                
		                   lower = (float) (lower - 0.5) * 2;
		                   upper = (float) (upper - 0.5) * 2;
		                   E.add(1);
		                  
		               }
		               else if ((lower < 0.5) && (upper < 0.5))
		               {
		            	   System.out.println(" range of ( "+unique.get(j)+" )>>>>>  "+lower+"     "+upper);
		                   lower *= 2;
		                   upper *= 2;
		                   E.add(0);
		                 
		               }
		               if (upper>0.5&&lower<0.5)
		                   check = false;
		            }
				  
		           
				  updateFreq[updateFreq.length-1]=upper;
				  updateFreq[0]=lower;
				  range=upper-lower;
			  

              for(int k=1;k<updateFreq.length-1;k++) //for update 
				  updateFreq[k]=lower+range*freq.get(k);
				 

            
            
		  }

	       }
		  }
	  
	    E.add(1);
	    for (int i=1;i<k;i++)E.add(0);
	    
	
	    
    }
    public void print_compress()
    {
        for (int i=0;i<E.size();i++)
	    {
	    	  System.out.print(E.get(i)+" ");
	    }
	    System.out.println(" ");
    }

    public float getCode()
    {
    	 int num=k-1;float code=0;
    	 for (int i=0;i<k;i++)
         {
    		code+=Math.pow(2,num)*E.get(i);
    		num--;
         }
    	 code/=Math.pow(2,k);
    	 return code;
    }
    
    public void deCompression()
    {
    	 float []updateFreq=new float [freq.size()]; String output="";
    	 float lower,upper;
  		  for (int i=0;i<unique.size();i++) {
  		 	 updateFreq[i]=freq.get(i);
  		  }
  		  
  		  
  		  
    	for (int i=0;i<text.length();i++)
    	{
    		
    		
    		for (int j=0;j<freq.size();j++)
    		{
    			if (output.length()==text.length())
        			break;
    			float code=getCode();
    			if (code>updateFreq[j]&&code<=updateFreq[j+1])
    			{
    				output+=unique.get(j);
    				lower=updateFreq[j];
    				upper=updateFreq[j+1];
    				 boolean check=true;
    				  while (check == true)
  		            { 
  		            	
  		               if ((lower > 0.5) && (upper > 0.5))
  		               {
  		                
  		                   lower = (float) (lower - 0.5) * 2;
  		                   upper = (float) (upper - 0.5) * 2;
  		                   E.remove(0);
  		                   
  		                   
  		               }
  		               else if ((lower < 0.5) && (upper < 0.5))
  		               {
  		                   lower *= 2;
  		                   upper *= 2;
  		                   E.remove(0);
  		                   
  		               }
  		               if (upper>0.5&&lower<0.5)
  		                   check = false;
  		            }
    				  updateFreq[updateFreq.length-1]=upper;
    				  updateFreq[0]=lower;
    				float   range=upper-lower;
    			  

                  for(int k=1;k<updateFreq.length-1;k++) //for update 
    				  updateFreq[k]=lower+range*freq.get(k);
    				  
    				  
    				
    				
    			}
    			
    			
    			
    			
    			
    		}
    	}
    	
        
        	 
        	 
       
    	
    	
    	
    	System.out.println("output  = "+output);
    	
    }
    
    
}
