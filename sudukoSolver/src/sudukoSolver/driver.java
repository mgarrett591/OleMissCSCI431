package sudukoSolver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class driver {
	
	
	public static void main (String[] args) throws FileNotFoundException{
		
		
		
		
		File inputFile = new File("soln.txt");
		Scanner scanner = new Scanner(inputFile); 
		String[][] grid = new String[9][9];
		
		boolean[][] rows = new boolean[9][9];
		boolean[][] cols = new boolean[9][9];
		boolean[][] cels = new boolean[9][9];
		
		
		
		for(int row=0; row<9; row++){
			for(int col=0; col<9; col++){
				rows[row][col]=false;
				cols[row][col]=false;
				cels[row][col]=false;
			}
		}
		
		
		
		Pa.in(grid);
		//Pa.in(cels);
		//Pa.in(rows);
		//Pa.in(cols);
		
		System.out.println("Stop now.");
		
		state Start = new state(grid, cels, rows, cols, true);
		StateStack ss = new StateStack();
		state cur = Start; 
		ss.push(Start);
		
		int index=0;
		while(cur.heuristic()!=28*81&&index<81){
			ss.push(cur);
			cur.setStateIndex(cur.getStateIndex()+1);
			state next=cur.getNextState();
			if(next==null){
				cur=ss.pop();
			}
			if(cur.head){
				index++;
			}	
			cur=next;
			while(cur==null||ss.peak()!=null){
				cur=ss.pop();
			}
		}
		
		scanner.close();
		
	}
	
	
	
	public static int getCellIndex(int row, int col){
			
		if(row < 3 && col < 3){
			return 0;
		}
		else if(row < 3 && col < 6){
			return 1;
		}
		else if(row < 6 && col < 3){
			return 3;
		}
		else if(row < 6 && col < 6){
			return 4;
		}
		else if(row < 3){
			return 2;
		}
		else if(col < 3){
			return 6;
		}
		else if(row < 6){
			return 5;
		}
		else if(col < 6){
			return 7;
		}
		else{
			return 8;
		}
	}
	//a cell is a 3x3 box
	
}

	
	
	
	
	
		
		