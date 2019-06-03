
public class malTest {
	public static void main(String[] args){
		
		methods Methods = new methods();
		
		Methods.goForward();
		Methods.stepBack();
		Methods.lookLeft();
		Methods.lookRight();
		Methods.lookRight();
		
		while(!Methods.isEscapePressed()){
			
			
		}
		
		
		//No changes below this line plz
		Methods.shutdown();
	}
}