package Binary;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class main {




	public static void main(String[] args) {
		
		BinaryCode obj=new BinaryCode();
		 System.out.println("-------------------------------compression-------------------------------");

	//obj.Dr_Example();      
	 obj.getData();
     obj.getK();
	 obj.compression();
	 obj.print_compress();
	 System.out.println("-------------------------------Decompression-------------------------------");
	 obj.deCompression();
	 
	 
	


	}

}
