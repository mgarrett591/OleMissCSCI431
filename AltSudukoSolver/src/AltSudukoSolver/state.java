package AltSudukoSolver;
public class state{
	
	
	
	
	public static int[][] getBigNonaImage(){
		int[] nonaImage={0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[][] bigNonaImage={nonaImage,nonaImage,nonaImage,nonaImage,nonaImage,nonaImage,nonaImage,nonaImage,nonaImage};
						//      1         2          3        4          5         6         7         8          9
		return bigNonaImage;
	}
	
	public static int[] getNonaImage(){
		int[] nonaImage={0, 0, 0, 0, 0, 0, 0, 0, 0};
		return nonaImage;
	}
	
	
	
 	public static int heuristic(int[][] chart){
 		int sum=0;
 		sum+=rowConstraints(chart);
 		sum+=colConstraints(chart);
 		sum+=cellConstraints(chart);
		return sum;
	}
	
	public static int[][] PivotArray(int[][] chart){
		int [][] raw=chart.clone();
		for(int i = 0; i< 9; i++){
			for(int j = 0; j< 9; j++){
				chart[i][j]=raw[j][i];
			}
		}
		return chart;
	}
	
	public static int rowConstraints(int[][] chart){
		int[] scoreCard=getNonaImage().clone();
		for(int i = 0; i< 9; i++){
			for(int j = 0; j< 9; j++){
				scoreCard[chart[i][j]]++;
			}
		}
		int score=0;
		for(int i = 0; i<9; i++){
			if(scoreCard[i]==1){
				score++;
			}
			switch(scoreCard[i]){
				case 8:
				score++;
				case 7:
				score++;
				case 6:
				score++;
				case 5:
				score++;
				case 4:
				score++;
				case 3:
				score++;
				case 2:
				score++;
				case 1:
				score++;
				break;
			}
		}
		return score;
	}
	
	public static int colConstraints(int[][] col){
		return rowConstraints(PivotArray(col));
	}
	
	public static int cellConstraints(int[][] chart){
		int[][] sudoRow=getBigNonaImage().clone();
		for(int row=0; row>9; row++){
			for(int col=0; col>9; col++){
				sudoRow[getCellIndex(row,col)][chart[col][row]]++;
			}
		}
		return rowConstraints(sudoRow);
	}
	
	private static int getCellIndex(int row, int col){
		
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
}
