
import java.util.*;
import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;


public class Gshare {
	int M1;
	int N1;
	String index;
	String status;
	
	String gbh;
	int misspred=0;
	double size1;
	int size;
	int[] gshare_array;
	int gs_index1=0;


	public Gshare(int M2, int N) {
		
		this.M1=M2;
		this.N1 = N;
		size1= Math.pow(2, M1);
		size = (int) Math.round(size1);
		
		gshare_array = new int [size];
		Arrays.fill(gshare_array, 4);
		
		
		StringBuilder sb = new StringBuilder();
		for (int j =0;j< N;j++) {
			sb.append(0);
			
		}
		gbh= sb.toString();
		
		
	}
		
		public void prediction2(String new_binary, int l) {
			
			index = new_binary.substring(new_binary.length()-M1);
			String gshare_n = index.substring(index.length()-N1);
			String gshare_mn = index.substring(0,l);
			
			StringBuilder s1 = new StringBuilder();
			
			for (int i = 0; i< gshare_n.length();i++) {
				
				s1.append(gshare_n.charAt(i) ^ gbh.charAt(i));
			}
			 
			String gshare_index = gshare_mn+s1;
			
			gs_index1 = Integer.parseInt(gshare_index,2);

			//System.out.println("Gshare"+gs_index1);

//			System.out.println("gshare val"+gshare_array[gs_index1]);
		
	}
		
		public void check_missprediction(String status) {
			
			if(status.equals("t") && this.gshare_array[this.gs_index1]<4) {
				
				misspred++;

			}
			else if (status.equals("n") && this.gshare_array[this.gs_index1]>3) {
				
				misspred++;

			}
		}
		
		
		public void check_index(String status) {
			
			if(status.equals("t")) {
				
				if(this.gshare_array[this.gs_index1]<7 ) {
					
					this.gshare_array[this.gs_index1]=this.gshare_array[this.gs_index1]+1;
					update_taken();
		
					}
				
				else if(this.gshare_array[this.gs_index1]==7) {
					
					update_taken();
					
				}
				
			}
			
			if( status.equals("n")) {
				
				
				if ( this.gshare_array[this.gs_index1]>0) {
					
					this.gshare_array[this.gs_index1]=this.gshare_array[this.gs_index1]-1;
					update_nottaken();
					
				}
				
				else if( this.gshare_array[this.gs_index1]==0) {
				
					update_nottaken();
					
				}
			}
		//	System.out.println("gshare after pred "+this.gshare_array[this.gs_index1]);
			
		}
		
	  public void update_taken() {
			
			gbh= gbh.substring(0,gbh.length()-1);
			gbh = "1"+gbh;

		}
		
	   public void update_nottaken() {
		   
		   gbh= gbh.substring(0,gbh.length()-1);
			gbh = "0"+gbh;
	   }
	   
	   public String just_check(String status) {
		   String pred = "";
		   /*
		   if(status.equals("t") && this.gshare_array[this.gs_index1]<4) {
				return 1;
			}
			else if((status.equals("n") && this.gshare_array[this.gs_index1]>3)){
				return 1;
			}
			else {
				return 2;
			}
			
			*/
		   if (this.gshare_array[this.gs_index1]<4) {
				pred = "n";
			}
			else {
				pred = "t";
			}
			
			return pred;
		   
	   }
		


	
	
}
