package calculator;

import java.util.Random;

public class TheCalculator {
	
	Random rand = new Random();
	
	public TheCalculator(){}
	
	//Function to generate and return valid set of numbers
	public int[] getNewQuestion(){
		int[] setofNumbers = {};
		
		setofNumbers = generateNumbers();
		while(validateEquation(setofNumbers)==false)
			setofNumbers = generateNumbers();
			
		return setofNumbers;
	}
	
	
	//Function to random four numbers
	public int[] generateNumbers(){
		int int1 = rand.nextInt(10);
		int int2 = rand.nextInt(10);
		int int3 = rand.nextInt(10);
		int int4 = rand.nextInt(10);
		int[] array = {int1,int2,int3,int4};
		return array;
	}
	
	
	//Function to validate set of numbers that has been random
	public boolean validateEquation(int[] arr) {
		// + = 0, - = 1, x = 2, / = 3, _ = 4
		boolean isValid = false;
		double leftFinal = 0, rightFinal = 0;
		
		for(int i=0;i<=4;i++) {
			if(i==3 && arr[1]==0)
				continue;
			
			if(i==0) {
				leftFinal = arr[0]+arr[1];
			}
			else if(i==1) {
				leftFinal = arr[0]-arr[1];
			}
			else if(i==2) {
				leftFinal = arr[0]*arr[1];
			}
			else if(i==3) {
				leftFinal = ((double)arr[0])/((double)arr[1]);
			}
			else if(i==4) {
				leftFinal = 10*arr[0] + arr[1];
			}
			
			for(int j=0;j<=4;j++) {
				if(j==3 && arr[3]==0)
					continue;
				
				if(j==0) {
					rightFinal = arr[2]+arr[3];
				}
				else if(j==1) {
					rightFinal = arr[2]-arr[3];
				}
				else if(j==2) {
					rightFinal = arr[2]*arr[3];
				}
				else if(j==3) {
					rightFinal = ((double)arr[2])/((double)arr[3]);
				}
				else if(j==4) {
					rightFinal = 10*arr[2] + arr[3];
				}

				
				if (Math.abs(leftFinal-rightFinal) <= 0.000001){
					isValid = true;
					break;
				}
			}
			
			if(isValid)
				break;
		}
		
		return isValid;
	}
	
	//Function to check the answer of player after submission
	public int checkEquation(int[] arr, String symbol1, String symbol2) {
		double leftFinal = 0, rightFinal = 0;
		
		if(symbol1=="/" && arr[1]==0) {
			return 3;
			}
		else {
			if(symbol1=="+") {
				leftFinal = arr[0]+arr[1];
			}
			else if(symbol1=="-") {
				leftFinal = arr[0]-arr[1];
			}
			else if(symbol1=="x") {
				leftFinal = arr[0]*arr[1];
			}
			else if(symbol1=="/") {
				leftFinal = ((double)arr[0])/((double)arr[1]);
			}
			else if(symbol1==" ") {
				leftFinal = 10*arr[0] + arr[1];
			}
		}
		
		
		if(symbol2=="/" && arr[3]==0) {
			return 3;
		}
		else {
			if(symbol2=="+") {
				rightFinal = arr[2]+arr[3];
			}
			else if(symbol2=="-") {
				rightFinal = arr[2]-arr[3];
			}
			else if(symbol2=="x") {
				rightFinal = arr[2]*arr[3];
			}
			else if(symbol2=="/") {
				rightFinal = ((double)arr[2])/((double)arr[3]);
			}
			else if(symbol2==" ") {
				rightFinal = 10*arr[2] + arr[3];
			}
		}
		
		if (Math.abs(leftFinal-rightFinal) <= 0.000001)
			return 1;
		else
			return 2;
	}
}
