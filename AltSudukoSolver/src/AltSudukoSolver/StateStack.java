package AltSudukoSolver;

import java.util.ArrayList;

public class StateStack{
	ArrayList<state> stateStack;
	int indexOfLast;
	StateStack(){
		stateStack = new ArrayList<state>();
		indexOfLast=-1;
	}
	public void push(state NewState){
		stateStack.add(NewState);
		indexOfLast++;
	}
	public state pop(){
		if(indexOfLast==-1){
			return null;
		}
		state state = stateStack.get(indexOfLast);
		stateStack.remove(indexOfLast);
		indexOfLast--;
		return state;
	}
	public state peak(){
		if(indexOfLast==-1){
			return null;
		}
		return stateStack.get(indexOfLast);
	}
	
	public void voidPop(){
		if(indexOfLast>0){
			stateStack.remove(indexOfLast);
			indexOfLast--;
		}
	}
}