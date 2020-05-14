
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;


import java.io.*;
public class sim {
	
	static int w=0,x=0,y=0,z=0;
	static int K=0,M1=0,N=0,M2=0;
	static String tracefile=null, mode;
	public static int pred_count=0;
	public static void main(String args[]) 
	{
		
		String k,m1,n1,m2;
		m1= Integer.toString(x);
		n1= Integer.toString(y);
		m2= Integer.toString(w);
		k= Integer.toString(z);
		mode=args[0];
		
		if(mode.equals("bimodal")) {
			m2= args[1];
			tracefile=args[2];
			
			
			//K=Integer.parseInt(k);
			//M2 = Integer.parseInt(m2); 
			M2=Integer.parseInt(m2);
			//N = Integer.parseInt(n1);
			
		}
		else if(mode.equals("gshare")) {
			m1=args[1];
			n1= args[2];
			tracefile=args[3];
			M1=Integer.parseInt(m1);
			N = Integer.parseInt(n1);
			int l = M1-N;
			
		}
		else if(mode.equals("hybrid")) {
			k= args[1];
			m1=args[2];
			n1= args[3];
			m2= args[4];
			tracefile=args[5];
			K=Integer.parseInt(k);
			M2 = Integer.parseInt(m2); 
			M1=Integer.parseInt(m1);
			N = Integer.parseInt(n1);
			
			
		}
		int l = M1-N;
		bimodal n = new bimodal(M2);
		Gshare g = new Gshare(M1, N);
		Hybrid h= new Hybrid(K);
		String t1="";
		String t2="";
		String val1="";
		int ms_hybrid=0;
		
		
		
		
		
		
		
		int new_index = 0;
		String status = null;
		String[] a = null ;
		
		try {
			Scanner s= new Scanner(new File(tracefile));
			//StringBuffer sb = new StringBuffer();
			
			
			while(s.hasNextLine()) {
				pred_count++;
				String line= s.nextLine();
			    a = line.split(" ");
				status = a[1];
				int decimal= Integer.parseInt(a[0],16);
				String binary= Integer.toBinaryString(decimal);
				String new_binary = binary.substring(0, binary.length()-2);
				
				
				
			    if(mode.equals("bimodal")){
			    	//System.out.println(new_binary);
			    	
			    	n.prediction(new_binary);
			    	n.check_misspred(status);
			    	n.check_index(status);
			    	
			    	
			    	
			    }
			    else if(mode.equals("gshare")) {
			    	
			    	g.prediction2(new_binary,l);
			    	g.check_missprediction(status);
			    	g.check_index(status);
			    	
			    }
			    
			    else if(mode.equals("hybrid")){
			    	
			    	val1=h.prediction3(new_binary);
			    	n.prediction(new_binary);
			    	g.prediction2(new_binary, l);
			    	
			    	t1= n.just_check(status);
			    	t2= g.just_check(status);
			    	
			    	//sb.append("CT index:     "+h.new_index+"\n");
			    	//sb.append("CT value:      "+h.Hybrid_array[h.new_index]+"\n");
			    	
			    	
					
			    	if(val1.equals("true")) {
			    		
			    		if(!t1.equals(status)) {
				    		
				    		ms_hybrid++;
				    		
				    	}
			    		n.check_index(status);
				    	
				    	//sb.append("prediction    "+t1+"\n");
			    		if(status.equals("t")) {
				    		g.update_taken();
				    	}
				    	else if(status.equals("n")){
				    		g.update_nottaken();
				    	}
				    	
				    	
			    	}
			    	else if( val1.equals("false")) {
			    		
				    	
				    	//sb.append("prediction    "+t2+"\n");
				    	if(!t2.equals(status)) {
				    	
				    		ms_hybrid++;
				    		
				    	}
				    	g.check_index(status);
			    	}
			    	
			    	
			    	 if ( t1.equals(status) && !t2.equals(status) ) {
			    		 h.update_hybrid_bimod();
			    		 
			    	 }
			    	 else if( !t1.equals(status) && t2.equals(status)) {
			    		 h.update_hybrid_ghsare();
			    		}
			    	
			    }
					
			  
		    }
			
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("file not found");
		}
		
		
		
		Path path= Paths.get(tracefile);
		Path file= path.getFileName();
		System.out.println("COMMAND");
		System.out.print("./sim ");
		System.out.print(mode+" ");
		if(mode.equals("bimodal")) {
			System.out.print(M2+" ");
		}
		else if(mode.equals("gshare")) {
			System.out.print(M1+" ");
			System.out.print(N+" ");
		}
		else if(mode.equals("hybrid")) {
			System.out.print(K+" ");
			System.out.print(M1+" ");
			System.out.print(N+" ");
			System.out.print(M2+" ");
		}
		System.out.println(file);
		
		System.out.println("OUTPUT");
		System.out.println("number of predictions:    \t"+pred_count);
		 if(mode.equals("bimodal")){
			 System.out.println("number of mispredictions:\t"+n.misspred);
			 
			 double miss= n.misspred;
		    	double pred = pred_count;
				 double ms_rate= (miss/pred)*100;
				 DecimalFormat df = new DecimalFormat("misprediction rate:\t\t"+"#.00");
				
				 System.out.println(df.format(ms_rate)+"%");
				 System.out.println("FINAL BIMODAL CONTENTS");
				 for( int i=0;i<n.size;i++) {
						
						System.out.print(i+"\t"+n.a[i]);
						
						
						System.out.println(" ");
					}
		    	
		    }
		    else if ( mode.equals("gshare")) {
		    	 System.out.println("number of mispredictions:\t"+g.misspred);
		    	double miss= g.misspred;
		    	double pred = pred_count;
				 double ms_rate= (miss/pred)*100;
				 DecimalFormat df = new DecimalFormat("misprediction rate:\t\t"+"#.00");
				 
			    	
				 System.out.println(df.format(ms_rate)+"%");
				 System.out.println("FINAL GSHARE CONTENTS");
				 for( int i=0;i<g.size;i++) {
					
						System.out.print(i+"\t"+g.gshare_array[i]);
						
						
						System.out.println(" ");
					}
		    }
		    else if(mode.equals("hybrid")) {
		    	
		    	 System.out.println("number of mispredictions:\t"+ms_hybrid);
		    	double miss= ms_hybrid;
		    	double pred = pred_count;
				 double ms_rate= (miss/pred)*100;
				 DecimalFormat df = new DecimalFormat("misprediction rate:\t\t"+"#.00");
				 
				 System.out.println(df.format(ms_rate)+"%");
				 
				 System.out.println("FINAL HYBRID CONTENTS");
				 for( int i=0;i<h.array_size;i++) {
					
						System.out.print(i+"\t"+h.Hybrid_array[i]);
						
						
						System.out.println(" ");
					}
		    }
		 
		
			
		
		
	}
	
}
