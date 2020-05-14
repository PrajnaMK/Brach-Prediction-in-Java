
import java.util.*;
import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;

public class bimodal {
	int M1;
	String index;
	String status;
	String temp;
	int misspred=0;
	
	int new_index;
	double size1;
	int size;
	int[] a;

	public bimodal(int m1) {
		this.M1=m1;
		size1= Math.pow(2, M1);
		size = (int) Math.round(size1);
		
		a = new int [size];
		Arrays.fill(a, 4);
	
	}
	
	
	public void prediction(String new_binary) {
		
		index = new_binary.substring(new_binary.length()-M1);
		new_index = Integer.parseInt(index,2);

		//System.out.println("Bimod"+new_index);

		//System.out.println("Bimodeval"+a[new_index]);
		}
	
	
	public void check_index(String status) {
		
		if(status.equals("t")) {
			if(this.a[this.new_index]<7 ) {

				this.a[this.new_index]=this.a[this.new_index]+1;
		}
	}
		if( status.equals("n")) {
			
			if ( this.a[this.new_index]>0) {

				this.a[this.new_index]=this.a[this.new_index]-1;
			}
		}
		//System.out.println("bimod after pred "+this.a[this.new_index]);
		
	}
	
	public void check_misspred(String status) {
		
		if(status.equals("t") && this.a[this.new_index]<4) {
			misspred++;

		}
		else if  ( status.equals("n") && this.a[this.new_index]>3) {
			misspred++;

		}
		
		
		
	}
	public String just_check(String status) {
		
		String pred = "";
	
		
		if (this.a[this.new_index]<4) {
			pred = "n";
		}
		else {
			pred = "t";
		}
		
		return pred;
	}

	
	
	
	
}
	

