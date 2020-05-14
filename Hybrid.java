

import java.util.Arrays;

public class Hybrid {
	
	int K;
	int [] Hybrid_array;
	int array_size;
	String index;
	int new_index;
	
	
	public Hybrid(int K) {
		
		this.K = K;
	
		array_size= (int) Math.pow(2, K);
		array_size = (int) Math.round(array_size);
		Hybrid_array = new int [array_size];
		Arrays.fill(Hybrid_array, 1);
	}

	public String prediction3( String bin ) {
		String val="true";
		index = bin.substring(bin.length()-K);
		new_index = Integer.parseInt(index,2);
		
		//System.out.println("CT"+new_index);

		//System.out.println("CT "+Hybrid_array[new_index]);
		
		if(this.Hybrid_array[this.new_index]<2) {
			
			val ="true";
			
		}
		else {
			
			val= "false";
		}
		
		return val;
	
	}
	
	public void update_hybrid_bimod() {
		
		if(this.Hybrid_array[this.new_index]!=0) {
			this.Hybrid_array[this.new_index]=this.Hybrid_array[this.new_index]-1;
			//System.out.println("bimod after hybrid "+this.Hybrid_array[this.new_index]);
			
		}
	}
	public void update_hybrid_ghsare() {
		if(this.Hybrid_array[this.new_index]!=3) {
			this.Hybrid_array[this.new_index]=this.Hybrid_array[this.new_index]+1;
		}
		//System.out.println("gshare after hybrid "+this.Hybrid_array[this.new_index]);
		
	}
	
	
}
