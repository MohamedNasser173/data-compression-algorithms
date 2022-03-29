package huffman;

import java.util.PriorityQueue; 
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator; 

class HuffmanNode { 

	int freq; 
	char character; 

	HuffmanNode left; 
	HuffmanNode right; 
} 

class charNode{
	
	char character;
	String bits;
}

class MyComparator implements Comparator<HuffmanNode> { 
	public int compare(HuffmanNode x, HuffmanNode y) 
	{ 

		return x.freq - y.freq; 
	} 
} 

public class Huffman { 

	public static void print(HuffmanNode root, String bits) throws Exception 
	{ 

		if (root.left == null&& root.right == null) { 

			System.out.println(root.character + ":" + bits);
			String s=root.character + ":" + bits;
			writeInFile("output.txt",s );

			return; 
		} 

		print(root.left, bits + "0"); 
		print(root.right, bits + "1"); 
	}
	
	public static void HuffmanMain(char[]charArray,int[]charFreq,int n) throws Exception
	{
		PriorityQueue<HuffmanNode> q 
		= new PriorityQueue<HuffmanNode>(n, new MyComparator()); 

	for (int i = 0; i < n; i++) { 

		HuffmanNode hn = new HuffmanNode(); 

		hn.character = charArray[i]; 
		hn.freq = charFreq[i]; 

		hn.left = null; 
		hn.right = null; 
		q.add(hn); 
	} 
	HuffmanNode root = null; 
	while (q.size() > 1) { 
		HuffmanNode x = q.poll(); 
		HuffmanNode y = q.poll(); 
		HuffmanNode internal = new HuffmanNode(); 
		internal.freq = x.freq + y.freq; 
		internal.character = '^';  
		internal.left = x; 
		internal.right = y; 
		root = internal; 
		q.add(internal); 
	} 

	
	print(root, ""); 
		
	}
	public static String readFile(String filename) throws Exception
	{
		
		ArrayList<String>arr=new ArrayList<String>();
		String s="";
		
		//File f=new File(filename);
		 FileReader fileReader= new FileReader(filename);
		 BufferedReader bufferedReaderr= new BufferedReader(fileReader);
		 String line ;
		 while ((line = bufferedReaderr.readLine()) != null) {
			 arr.add(line);            
	        }
		 bufferedReaderr.close();
		 for(int i=0;i<arr.size();i++)
		 { 
			  s=s+arr.get(i); 
		 }
		 return s;
	}
	public static void writeInFile(String filename,String s) throws Exception{
		
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
		    out.println(s);
		    out.close();
		} catch (IOException e) {
		   
		}
	
		
	}
	public static void toBits(String output,char []s) throws IOException  /////////////////////////////////////////////
	{
		ArrayList<String>arr=new ArrayList<String>();
		
		 FileReader fileReader= new FileReader(output);
		 BufferedReader bufferedReaderr= new BufferedReader(fileReader);
		 String line ;
		 while ((line = bufferedReaderr.readLine()) != null) {
			 arr.add(line);            
	        }
		 
		 char []characters=new char[arr.size()];
		 String  []bits =new String [arr.size()];
		 
		 for (int i=0;i<arr.size();i++)
		 {char chr=arr.get(i).charAt(0);
		 
		   String bt= arr.get(i).substring(2, arr.get(i).length());
		  characters[i]=chr;
		  bits[i]=bt;   
		 }
		 String BitsOfText="";
		for (int i=0;i<s.length;i++)   //to compress the text to 1 and 0
		{ Character ch1;
			ch1=s[i];
			for (int j=0;j<characters.length;j++)
			{
				char ch2;
				ch2=characters[j];	
				
				if  (ch1.equals(ch2)) 
				{	
					BitsOfText=BitsOfText+ bits[j];
					break;
				}
			}
		}
		
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(output, false)));
		    
		    out.println(BitsOfText);
		    for (int i=0;i<characters.length;i++)
		    	out.println(characters[i]+":"+bits[i]);
		    
		    out.close();
		} catch (IOException e) {
		   
		}
		
		
		 
		 
		 
	}

	public static void compression() throws Exception
	{
		ArrayList<Character>unique=new ArrayList<Character>();
		ArrayList<Integer>freq=new ArrayList<Integer>();
		
      String text=readFile("text.txt"); //read the  input file 
     
      char []charArray = text.toCharArray();
       System.out.print(charArray);
      // writeInFile("output.txt",text );
       System.out.print("\n"); 
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
    	   {
    		   unique.add(charArray[i]);
    	   }
       }
     
       for (int i=0;i<unique.size();i++)   //make an array for frequent character    
       { 
    	 int count=0;
    	   for (int j=0;j<charArray.length;j++)
    	   {
    		   if (unique.get(i)==charArray[j])
    		   {
    			   count++;
    		   }
    	   }
    	   freq.add(count);
    	   
    	}
       char uniquechar[]=new char[unique.size()];  //to be send in function
       int freqs[]=new int[freq.size()];
      for (int i=0;i<freq.size();i++)
      {
    	  uniquechar[i]=unique.get(i);
    	  freqs[i]=freq.get(i);
      }
    
      HuffmanMain(uniquechar, freqs, freq.size());
      
      
      toBits("output.txt", charArray);
       
      }
	

	public static void decompression() throws Exception
	{
		ArrayList<String>arr=new ArrayList<String>();
		
		 FileReader fileReader= new FileReader("output.txt");
		 BufferedReader bufferedReaderr= new BufferedReader(fileReader);
		 String line ;
		 while ((line = bufferedReaderr.readLine()) != null) {
			 arr.add(line);            
	        }
		 
		 String SeriesOfbits;   //bits that we compressed
		 SeriesOfbits=arr.get(0);
		 
		 char []characters=new char[arr.size()];
		 String  []bits =new String [arr.size()];
		 
		 for (int i=1;i<arr.size();i++)
		 {char chr=arr.get(i).charAt(0);
		 
		   String bt= arr.get(i).substring(2, arr.get(i).length());
		  characters[i]=chr;
		  bits[i]=bt;   
		 }
		 char []array = SeriesOfbits.toCharArray();String text="",s="";
		 for (int i=0;i<SeriesOfbits.length();i++)
		 { 
			 s+=array[i];
			 for (int j=0;j<bits.length;j++)
			 {
				
				 if (s.equals(bits[j]))
				 {
					 text+=characters[j];
					 
					s=""; 
				 }
			 }
		 }
		 
		 System.out.println(text);
		 
		 try {
			 
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outputDecom.txt", false)));
			    out.println(text);
			    out.close();
			} catch (IOException e) {
			   
			}
	}
	


	public static void main(String[] args) throws Exception 
	{ 
		compression();
		//decompression();
   }
	


}
	 


