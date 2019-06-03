package sudukoSolver;

import java.util.ArrayList;


public class state{
	public boolean head;
	public ArrayList<state> children; 
	private int stateIndex;
	private String[][] grid;
	private boolean[][] cels;
	private boolean[][] rows;
	private boolean[][] cols;
	public int HeuristicValue;
	private int constraintGrid[][];
	public state(String Grid[][], boolean Cels[][], boolean Rows[][], boolean Cols[][], boolean head){
		stateIndex = 0;
		HeuristicValue = 0;
		grid = Grid;
		cels = Cels;
		cols = Cols;
		rows = Rows;
		children=new ArrayList<state>();
		constraintGrid=new int[9][9];
		for(int row=0; row<9; row++){
			for(int col=0; col<9; col++){
				int temp=getConstraintCount(row, col);
				HeuristicValue+=temp;
				constraintGrid[row][col]=temp;
			}
		}
	}
	public String[][] getGrid(){
		return grid;
	}
	public boolean[][] getCels(){
		return cels;
	}
	public boolean[][] getCols(){
		return cols;
	}
	public boolean[][] getRows(){
		return rows;
	}
	public int heuristic(){
		return HeuristicValue;
	}
	
	public int getStateIndex(){
		return stateIndex;
	}
	
	public void setStateIndex(int index){
		stateIndex=index;
	}
	
	public void nextGen(){
		for(int row=0; row<9; row++){
			for(int col=0; col<9; col++){
				for(int val=0; val<9; val++){
					if(grid[row][col].equals(" ")&&!rows[row][val]&&!cols[col][val]&&!cels[getCellIndex(row, col)][val]){
						String[][] NewGrid = grid.clone();
						boolean[][] NewRows = rows.clone();
						boolean[][] NewCols = cols.clone();
						boolean[][] NewCels = cels.clone();
						NewGrid[col][row] = new String(""+(val+1));
						Pa.in(NewGrid);
						NewRows[row][val] = true;
						NewCols[col][val] = true;
						NewCels[getCellIndex(row, col)][val] = true;
						children.add(new state(NewGrid, NewCels, NewRows, NewCols, false));
					}		
				}
			}
		}
		//children.sort(Comparator.comparingInt(state::HeuristicValue));
	}
	
	
	private int getConstraintCount(int row, int col){
		int sum = 0;
		if(!grid[row][col].equals(" ")){
			return 28;
		}
		for(int i=0; i<9; i++){
			if(cols[col][i]){
				sum++;
			}
		}
		for(int i=0; i<9; i++){
			if(rows[row][i]){
				sum++;
			}
		}
		for(int i=0; i<9; i++){
			
			if(cels[getCellIndex(row, col)][i]){
				sum++;
			}
		}
		return sum;
	}
	
	public state getNextState(){
		if(children.isEmpty()){
			nextGen();
		}
		if(children.size()<=stateIndex||stateIndex<0){
			return null;
		}
		return children.get(stateIndex);
	}
	
	private int getCellIndex(int row, int col){
		
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
	public void IncramentStateIndex() {
		stateIndex+=1;
	}
	
	
}
