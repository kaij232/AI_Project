package WorkPlace;
import java.util.Random;
import java.util.Scanner;

import Neyrons.*;

public class MainFile {
private final static InputNeyron[] inputNeyrons = new InputNeyron[] {new InputNeyron(),new InputNeyron(),new InputNeyron(),new InputNeyron(),new InputNeyron()};
private final static HidenNeyron[][] hidenNeyrons = new HidenNeyron[][] {{new HidenNeyron(),new HidenNeyron(),new HidenNeyron(),new HidenNeyron(),new HidenNeyron()},{new HidenNeyron(),new HidenNeyron()}};
private final static OutputNeyron[] outputNeyrons = new OutputNeyron[] {new OutputNeyron(),new OutputNeyron()};




public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int controller = -1; 
		int[] input = new int[] {0,1,1,0,1};
				setConnections();
				setStandartWeights();
				setStandartBaisds();
		System.out.println("0 - stop; \n1 - get result; \n2 - study; ");
		while(0 != 1) {
			controller = scan.nextInt();
			if(controller == 1) {
								float[] resNeyrons = getResult(input);
				for(int i = 0; i < resNeyrons.length;i++) {
					System.out.println("Neyr " + i + ": "+resNeyrons[i]);
				}
			}else if(controller == 0) {System.exit(0);
			}else if(controller == 2) {
				System.out.println("How many times: ");
				int times = scan.nextInt();
				System.out.println("Styding Speed: ");
				int SpeedA = scan.nextInt();
				Study(times, SpeedA);
			}
		}

//sdelat upravlenie cherez konsol i back propogation function
	}





	public static float[] getResult(int[] input) {
		float[] res = new float[outputNeyrons.length];
		for(int inputSl = 0; inputSl < inputNeyrons.length; inputSl++) {
			try {
				inputNeyrons[inputSl].setActivity(input[inputSl]);
			}catch(ArrayIndexOutOfBoundsException e) {
				inputNeyrons[inputSl].setActivity(0);
			}
		}
		for(int hidenSlNumb = 0; hidenSlNumb < hidenNeyrons.length; hidenSlNumb++) {
			for(int hidenSl = 0; hidenSl< hidenNeyrons[hidenSlNumb].length; hidenSl++) {
//				System.out.println(inputNeyrons[inputSl].getActivity() + " - inp: " + inputSl);
//				System.out.println(inputNeyrons[inputSl].getAfterWeights()[hidenSl] + " - inp: " + inputSl + " weith");
//				System.out.println((inputNeyrons[inputSl].getActivity()*inputNeyrons[inputSl].getAfterWeights()[hidenSl]) + " - inp: " + inputSl + " || hiden: "+ hidenSl);
				if(hidenSlNumb == 0) {
					for(int inputSl = 0; inputSl < inputNeyrons.length;inputSl++ ) {
						float deltaActivity = inputNeyrons[inputSl].getActivity()*inputNeyrons[inputSl].getAfterWeights()[hidenSl];
						hidenNeyrons[hidenSlNumb][hidenSl].addToActivity((deltaActivity));
					}
				}else {
					hidenNeyrons[hidenSlNumb][hidenSl].addToActivity((hidenNeyrons[hidenSlNumb-1][hidenSl].getActivity()*hidenNeyrons[hidenSlNumb-1][hidenSl].getAfterWeights()[hidenSl]));
				}
			
//				System.out.println(hidenNeyrons[hidenSl].getActivity());
				hidenNeyrons[hidenSlNumb][hidenSl].addToActivity(hidenNeyrons[hidenSlNumb][hidenSl].getBaisd());
				hidenNeyrons[hidenSlNumb][hidenSl].useFunction();

			}
		}
		
//		System.out.println("HidenSl: ");
//		for(int hidenSl = 0; hidenSl < hidenNeyrons.length; hidenSl++) {
//			System.out.println(hidenNeyrons[hidenSl].getActivity());
//		}	
//		System.out.println("OutputSl:");
		
		for(int outputSl = 0; outputSl< outputNeyrons.length; outputSl++) {
			for(int hidenSl = 0; hidenSl < hidenNeyrons[hidenNeyrons.length-1].length;hidenSl++ ) {
				outputNeyrons[outputSl].addToActivity((hidenNeyrons[hidenNeyrons.length-1][hidenSl].getActivity()*hidenNeyrons[hidenNeyrons.length-1][hidenSl].getAfterWeights()[outputSl]));
			}
			outputNeyrons[outputSl].addToActivity(outputNeyrons[outputSl].getBaisd());
			outputNeyrons[outputSl].useFunction();

		}
		for(int outputSl = 0; outputSl < outputNeyrons.length; outputSl++) {
//			System.out.println(outputNeyrons[outputSl].getActivity());
			res[outputSl] = outputNeyrons[outputSl].getActivity();
		}	
		return res;
		
	}
	
	
	
	
	public static float[][] getHidenActivations(int[] input) {
		float[][] res = new float[hidenNeyrons.length+1][10];
		for(int inputSl = 0; inputSl < inputNeyrons.length; inputSl++) {
			try {
				inputNeyrons[inputSl].setActivity(input[inputSl]);
			}catch(ArrayIndexOutOfBoundsException e) {
				inputNeyrons[inputSl].setActivity(0);
			}
		}
		for(int hidenSlNumb = 0; hidenSlNumb < hidenNeyrons.length; hidenSlNumb++) {
			for(int hidenSl = 0; hidenSl< hidenNeyrons[hidenSlNumb].length; hidenSl++) {
				if(hidenSlNumb == 0) {
					for(int inputSl = 0; inputSl < inputNeyrons.length;inputSl++ ) {
						hidenNeyrons[hidenSlNumb][hidenSl].addToActivity((inputNeyrons[inputSl].getActivity()*inputNeyrons[inputSl].getAfterWeights()[hidenSl]));
					}
				}else {
					hidenNeyrons[hidenSlNumb][hidenSl].addToActivity((hidenNeyrons[hidenSlNumb-1][hidenSl].getActivity()*hidenNeyrons[hidenSlNumb-1][hidenSl].getAfterWeights()[hidenSl]));
				}
				hidenNeyrons[hidenSlNumb][hidenSl].addToActivity(hidenNeyrons[hidenSlNumb][hidenSl].getBaisd());
				hidenNeyrons[hidenSlNumb][hidenSl].useFunction();

				res[hidenSlNumb][hidenSl] = hidenNeyrons[hidenSlNumb][hidenSl].getActivity();
			}
		}
		return res;
	}
	
	
	
	
	
	
	
	public static float[] getOutputActivations(int[] input) {
		float[] res = new float[outputNeyrons.length];
		for(int inputSl = 0; inputSl < inputNeyrons.length; inputSl++) {
			try {
				inputNeyrons[inputSl].setActivity(input[inputSl]);
			}catch(ArrayIndexOutOfBoundsException e) {
				inputNeyrons[inputSl].setActivity(0);
			}
		}
		for(int hidenSlNumb = 0; hidenSlNumb < hidenNeyrons.length; hidenSlNumb++) {
			for(int hidenSl = 0; hidenSl< hidenNeyrons[hidenSlNumb].length; hidenSl++) {
				if(hidenSlNumb == 0) {
					for(int inputSl = 0; inputSl < inputNeyrons.length;inputSl++ ) {
						hidenNeyrons[hidenSlNumb][hidenSl].addToActivity((inputNeyrons[inputSl].getActivity()*inputNeyrons[inputSl].getAfterWeights()[hidenSl]));
					}
				}else {
					hidenNeyrons[hidenSlNumb][hidenSl].addToActivity((hidenNeyrons[hidenSlNumb-1][hidenSl].getActivity()*hidenNeyrons[hidenSlNumb-1][hidenSl].getAfterWeights()[hidenSl]));
				}
			
				hidenNeyrons[hidenSlNumb][hidenSl].addToActivity(hidenNeyrons[hidenSlNumb][hidenSl].getBaisd());
				hidenNeyrons[hidenSlNumb][hidenSl].useFunction();

			}
		}
		
		
		for(int outputSl = 0; outputSl< outputNeyrons.length; outputSl++) {
			for(int hidenSl = 0; hidenSl < hidenNeyrons[hidenNeyrons.length-1].length;hidenSl++ ) {
				outputNeyrons[outputSl].addToActivity((hidenNeyrons[hidenNeyrons.length-1][hidenSl].getActivity()*hidenNeyrons[hidenNeyrons.length-1][hidenSl].getAfterWeights()[outputSl]));
			}
			outputNeyrons[outputSl].addToActivity(outputNeyrons[outputSl].getBaisd());

		}
		for(int outputSl = 0; outputSl < outputNeyrons.length; outputSl++) {
			res[outputSl] = outputNeyrons[outputSl].getActivity();
		}	
		return res;
		
	}
	
	
	
	
	public static void setConnections() {
		for(int i = 0; i < inputNeyrons.length; i++) {
			inputNeyrons[i].setOutputConnections(hidenNeyrons.length);
		}
		for(int i = 0; i < hidenNeyrons.length; i++) {
			for(int j = 0; j < hidenNeyrons[i].length; j++) {
				if(i == hidenNeyrons.length-1) {
					hidenNeyrons[i][j].setOutputConnections(outputNeyrons.length);
			}else {
				hidenNeyrons[i][j].setOutputConnections(hidenNeyrons[i+1].length);
			}
		}
		}
	}
	
	
	
	
	
	public static void setStandartWeights() {
		for(int i = 0; i < inputNeyrons.length; i ++) {
			inputNeyrons[i].setAfterWeights(new float[] {1.0f,1.0f,1.0f,1.0f,1.0f});
		}
		for(int i = 0; i < hidenNeyrons.length; i ++) {
			for(int j = 0; j < hidenNeyrons[i].length; j++) {
				if(i == hidenNeyrons.length-1) {
					float[] add = new float[outputNeyrons.length];
					for(int k = 0; k < add.length;k++) {
						add[k] = 1.0f;
					}
					hidenNeyrons[i][j].setAfterWeights(add);
				}else {
					float[] add = new float[hidenNeyrons[i+1].length];
					for(int k = 0; k < add.length;k++) {
						add[k] = 1.0f;
					}
					hidenNeyrons[i][j].setAfterWeights(add);
				}
				
			}}}
	
	
	
	
	
	
	
	
	public static void setStandartBaisds() {
		for(int i = 0; i < hidenNeyrons.length; i ++) {
			for(int j = 0; j<hidenNeyrons[i].length; j++) {
				hidenNeyrons[i][j].setBaisd(0.0f);
			}
		}
		for(int i = 0; i < outputNeyrons.length; i ++) {
			outputNeyrons[i].setBaisd(0.0f);
		}}
	
	
	public static void Study(int times, float speedA) {
		Random ran = new Random();
		int[][] inputs = new int[][] {{0,0,0,0,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,0,0},{1,1,1,1,0},{1,1,1,1,1},{0,1,1,1,1},{0,0,1,1,1},{0,0,0,1,1},{0,0,0,0,1},{0,1,0,1,0},{1,0,1,0,1}};
		float[][] actual = new float[][] {{1,0},{1,0},{1,0},{0,1},{0,1},{0,1},{0,1},{0,1},{1,0},{1,0},{1,0},{0,1}};
		while(times > 0) {
			int i = ran.nextInt(12);
			BackPropogation(inputs[i],actual[i],speedA);
			times--;
		}
	}
	
	
	
	
	
	public static void BackPropogation(int[] input, float[] actual, float a) {
		float[] prediction = getResult(input);
		final int lastHiden = hidenNeyrons.length-1;
		float[] outputError = new float[outputNeyrons.length];
		float[][] hidenError = new float[hidenNeyrons.length][10];
		float[] inputError = new float[inputNeyrons.length];
		float[] delta = new float[outputNeyrons.length];
		float[][] hidenActivations = getHidenActivations(input);
		float[] outputActivations = getOutputActivations(input);
		for(int i = 0; i < outputError.length; i++) {
			outputError[i] = (float)(((prediction[i]-actual[i]) * (outputNeyrons[i].getUseFunctionDirative(outputActivations[i]))));
			delta[i] = prediction[i]-actual[i];
		}
		
		
		for(int outputNumb = 0; outputNumb < outputNeyrons.length; outputNumb++) {
			for(int hidenNumb = 0; hidenNumb < hidenNeyrons[lastHiden].length; hidenNumb++) {
				float newWeight = hidenNeyrons[lastHiden][hidenNumb].getAfterWeights()[outputNumb] + (a * outputError[outputNumb] * hidenActivations[lastHiden][hidenNumb]);
				float newBaisd = outputNeyrons[outputNumb].getBaisd() + (a*outputError[outputNumb]);
				hidenNeyrons[lastHiden][hidenNumb].setAfterWeight(newWeight,outputNumb);
				outputNeyrons[outputNumb].setBaisd(newBaisd);
			}
//				sdelat uchenie dlz ostalnih hiden i input sloev i metod "Styding" and save algoritm
		}
		
		
		
		for(int hidenSlNumb = hidenError.length-2;hidenSlNumb >= 0; hidenSlNumb--) {
			for(int hidenNumb = 0; hidenNumb < hidenError[hidenSlNumb].length; hidenNumb++) {
				float sumError = 0;
				float sumActivities = 0;
				
				if(hidenSlNumb != hidenError.length-1) {
					for(int i = 0; i < hidenNeyrons[hidenSlNumb+1].length;i++ ) {
						for(int j = 0; j < hidenNeyrons[hidenSlNumb].length; j++) {
						sumActivities += hidenActivations[hidenSlNumb+1][i];
					sumError += (hidenError[hidenSlNumb+1][i] * hidenNeyrons[hidenSlNumb][j].getAfterWeights()[hidenNumb]);
					}
					}
				}else {
					for(int i = 0; i < outputError.length; i++) {
						sumActivities += outputActivations[i];
						sumError += (outputError[i] * hidenNeyrons[lastHiden][hidenNumb].getAfterWeights()[i]);
					}
					
				}
				hidenError[hidenSlNumb][hidenNumb] = (sumError * hidenNeyrons[0][0].getUseFunctionDirative(sumActivities));
				for(int i = 0; i < hidenNeyrons[hidenSlNumb+1].length; i++) {
//					sdelat ctob ne biolo outOfBounds - nado sdelat proverky na sloy i esli eto sloy okolo output, to delat podrugomy
					float newWeith = hidenNeyrons[hidenSlNumb][hidenNumb].getAfterWeights()[i] +  (a * hidenError[hidenSlNumb][hidenNumb] * hidenActivations[hidenSlNumb][hidenNumb]);
					hidenNeyrons[hidenSlNumb][hidenNumb].setAfterWeight(newWeith, i);
//				hidenNeyrons[hidenSl][hidenNumb] = hidenNeyrons[hidenSl][hidenNumb].getAfterWeights()[i]*a*
				}
				hidenNeyrons[hidenSlNumb][hidenNumb].setBaisd(hidenNeyrons[hidenSlNumb][hidenNumb].getBaisd()+(hidenError[hidenSlNumb][hidenNumb]*a));
			}
			}
		
		
		

		for(int inputNumb = 0; inputNumb < inputNeyrons.length; inputNumb++) {
			float sumError = 0; 
			float sumActivation = 0;
			for(int i = 0; i < hidenNeyrons[0].length; i++) {
				sumError += (hidenError[0][i] * inputNeyrons[inputNumb].getAfterWeights()[i]);
				sumActivation += hidenActivations[0][i];
			}
			inputError[inputNumb] = sumError*Neyron.getUseFunctionDirative(sumActivation);
			
			for(int i = 0; i < hidenNeyrons[0].length; i++) {
			float newWeight = inputNeyrons[inputNumb].getAfterWeights()[i] + (a*inputError[inputNumb]*input[inputNumb]);
			inputNeyrons[inputNumb].setAfterWeight(newWeight, i);
			}
		}
		
		
		
	}
}
