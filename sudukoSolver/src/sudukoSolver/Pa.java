package sudukoSolver;
//PRINT 2D ARRAYS IN JAVA, IT PROBABLY WON'T HURT THAT MUCH.
public class Pa {
	
	public static void in(String[][] data){
		System.out.println("[");
		for(int i = 0; i<data.length; i++){
			System.out.print("[");
			for(int j = 0; j<data.length; j++){
				if(j!=0){
					System.out.print(",");
				}
				System.out.print(data[i][j]);
			}
			System.out.print("]");
			if(i!=data.length-1){
				System.out.println(",");
			}
			else{
				System.out.println("");
			}
		}
		System.out.println("]");
	}
	
	public static void in(int[][] data){
		System.out.println("[");
		for(int i = 0; i<data.length; i++){
			System.out.print("[");
			for(int j = 0; j<data.length; j++){
				if(j!=0){
					System.out.print(",");
				}
				System.out.print(data[i][j]);
			}
			System.out.print("]");
			if(i!=data.length-1){
				System.out.println(",");
			}
			else{
				System.out.println("");
			}
		}
		System.out.println("]");
	}
	
	public static void in(boolean[][] data){
		System.out.println("[");
		for(int i = 0; i<data.length; i++){
			System.out.print("[");
			for(int j = 0; j<data.length; j++){
				if(j!=0){
					System.out.print(",");
				}
				if(data[i][j]){
					System.out.print("1");
				}
				else{
					System.out.print("0");
				}
			}
			System.out.print("]");
			if(i!=data.length-1){
				System.out.println(",");
			}
			else{
				System.out.println("");
			}
		}
		System.out.println("]");
	}
}
