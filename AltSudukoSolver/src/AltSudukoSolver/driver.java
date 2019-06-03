package AltSudukoSolver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;



public class driver {
	
	
	public static void main (String[] args) throws InterruptedException, IOException{
		
		
		
		
		File inputFile = new File("soln.txt");
		Scanner scanner = new Scanner(inputFile); 
		int[][] chart = new int[9][9];
		boolean[][] fixed = new boolean[9][9];
		
		for(int row=0; row<9; row++){
			String[] tokkens=scanner.nextLine().split(" ");
			for(int col = 0; col<9; col++){
				chart[row][col]=Integer.parseInt(tokkens[col])-1;
				if(chart[row][col]==-1){
					chart[row][col]=0;
					fixed[row][col]=false;
				}
				else{
					fixed[row][col]=true;
				}
			}
		}
		scanner.close();
		Random random = new Random();
		
		
		int[][] parent=chart.clone();
		int[][] child=chart.clone();
		Pa.in(child);
		int initialNumGensTillReset=20000000;
		int numGensTillReset=initialNumGensTillReset;
		while(state.heuristic(child)!=(243*8)){
			int row=random.nextInt(9);
			int col=random.nextInt(9);
			int val=random.nextInt(9);
			while(fixed[row][col]){
				row=random.nextInt(9);
				col=random.nextInt(9);
			}
			//System.out.println(fixed[row][col]);
			child[row][col]=val;
			if(state.heuristic(child)>state.heuristic(parent)){
				
				System.out.println(state.heuristic(child));
				System.out.println(state.heuristic(parent));
				System.out.println(state.heuristic(child)>state.heuristic(parent));
				parent=child.clone();
				System.out.println(state.heuristic(child));
				System.out.println(state.heuristic(parent));
				System.out.println(state.heuristic(child)>state.heuristic(parent));
				System.out.println("Child heurostic score: "+state.heuristic(child)+"/243");
				Pa.in(child);
			}
			//numGensTillReset--;
			if(numGensTillReset==0){
				parent=chart;
				numGensTillReset=initialNumGensTillReset;
			}
		
		Pa.in(child);
		
		FileWriter fw = new FileWriter("out.txt");
		PrintWriter pw = new PrintWriter(fw);
	    for(int row1=0; row1<9; row1++){
	    	for(int col1=0; col1<9; col1++){
		    	pw.print(child[row1][col1]+1);
		    	if(col1!=9){
		    		pw.print(" ");
		    	}
		    }
	    	pw.println("");
	    }
	    pw.close();
	}		
}
	
	
	
	
	
		
		