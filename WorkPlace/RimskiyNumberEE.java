package WorkPlace;

import java.util.Scanner;

public class RimskiyNumberEE {
	
	static double[][][] weights = null;
	static double[][] activations = null;
	static double[][] activationsWithoutSigmoid = null;
	static String[][][] StudyData = new String[][][] {{{"I"},{"II"},{"III"},{"IV"},{"V"},{"VI"},{"VII"},{"VIII"},{"IX"},{"X"},{"XI"},{"XII"},{"XIII"},{"XIV"},{"XV"},{"XVI"},{"XVII"},{"XVIII"},{"XIX"},{"XX"}},     	 {{"0","0","0","0","0","1"},{"0","0","0","0","0","2"},{"0","0","0","0","0","3"},{"0","0","0","0","0","4"},{"0","0","0","0","0","5"},{"0","0","0","0","0","6"},{"0","0","0","0","0","7"},{"0","0","0","0","0","8"},{"0","0","0","0","0","9"},{"0","0","0","0","1","0"},{"0","0","0","0","1","1"},{"0","0","0","0","1","2"},{"0","0","0","0","1","3"},{"0","0","0","0","1","4"},{"0","0","0","0","1","5"},{"0","0","0","0","1","6"},{"0","0","0","0","1","7"},{"0","0","0","0","1","8"},{"0","0","0","0","1","9"},{"0","0","0","0","2","0"}}};
	static double[][][] StudyDataUseHash = new double[2][StudyData[0].length][1];
//	static double[][] emptyActivations = {{},{},{},{},{}};
//			*	*	*	
//			*	*	*		*
//			*	*	*		*
//	*		*	*	*		*
//			*	*	*		*
//			*	*	*		*
//			*	*	*		*

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "v";
		String[] input_ = new String[1];
		
		input_[0] = "";
		for(int chNum = 0; chNum < input.length(); chNum++) {
			input_[0] += "" + ("" + input.charAt(chNum)).hashCode();
		}
		

		for(int i = 0; i < StudyData[0].length; i++) {
			String iDataHash = "";
			for(int j = 0; j < StudyData[0][i][0].length();j++) {
				iDataHash +=  "" + ("" + StudyData[0][i][0].charAt(j)).hashCode();
			}
			StudyDataUseHash[0][i][0] = Double.parseDouble(iDataHash);
		}
		
//		String jq = "q";
//		String kq = "qw";
//		String kq_ = "";
//		for(int er = 0; er < kq.length(); er++) {
//			kq_ +=( "" + kq.charAt(er)).hashCode();
//		}
//		System.out.println(jq.hashCode());	
//		System.out.println(kq_);	
		activations = new double[5][];
		weights = new double[activations.length][][];

		for(int i = 0; i < activations.length; i++) {
			if(i == 1) {
				activations[i] = new double[1];
			}else if(i == activations.length-1) {
//				otveti do 6 znakow 
				activations[i] = new double[6];
			}else {
				activations[i] = new double[7];
			}
			if(i != 0) {
			weights[i-1] = new double[activations[i-1].length][activations[i].length];
			}
			for(int j = 0; j< activations[i].length; j++ ) {
				activations[i][j] = 0;
				if(i != 0) {
				for(int k = 0; k < activations[i-1].length; k++) {
					weights[i-1][k][j] = Math.random();
				}
				}
			}
				}
			activationsWithoutSigmoid = activations;
			
			
			
			int controller = -1;
			while(1 != 0) {
				controller = scan.nextInt();
				if(controller == 0) {
					System.exit(1);
				}else if(controller == 1) {
							getResult(new double[] {Double.parseDouble(input_[0])});
							for(int resNum = 0; resNum < activations[activations.length-1].length; resNum++) {
								System.out.println(activations[activations.length-1][resNum]);
				}
			}else if(controller == 2) {
				Study(scan.nextDouble(), scan.nextInt());
			
			}else if(controller == 3) {
				input = scan.next();
				
				input_[0] = "";
				for(int chNum = 0; chNum < input.length(); chNum++) {
					input_[0] += ("" + input.charAt(chNum)).hashCode();
				}
		}
			}
			
				
				
	}
	private static void getResult(double[] input) {
		
		for(int i = 0; i < activations.length; i++) {
			if(i == 1) {
				activations[i] = new double[1];
			}else if(i == activations.length-1) {
//				otveti do 6 znakow 
				activations[i] = new double[6];
			}else {
				activations[i] = new double[7];
			}
			for(int j = 0; j< activations[i].length; j++ ) {
				activations[i][j] = 0;
			}
		}
				activationsWithoutSigmoid = activations;
				
		activations[0] = input;
		activationsWithoutSigmoid[0] = activations[0];
		for(int Sl = 0; Sl < activations.length; Sl++) {
			for(int SlNum = 0; SlNum < activations[Sl].length; SlNum++) {
				if(Sl == activations.length-1) {
					break;
				}
				for(int NSlNum  = 0; NSlNum < activations[Sl+1].length; NSlNum++) {
					activationsWithoutSigmoid[Sl+1][NSlNum] += activations[Sl][SlNum] * weights[Sl][SlNum][NSlNum];
					if(SlNum ==  activations[Sl].length-1) {
					activations[Sl+1][NSlNum] = SigmoidFunction(activationsWithoutSigmoid[Sl+1][NSlNum]);
					}
				
				}
			}
		}
	}
	private static void Study(double SpeedA, int timesDo) {
		for(int i = 0; i < timesDo; i++) {
			
			BackPropogation(SpeedA, StudyDataUseHash);
		}
	}
	private static void BackPropogation(double SpeedA, double[][][] StData) {
		for(int dataNum = 0; dataNum < StData[0].length; dataNum++) {
		for(int i = 0; i < activations.length; i++) {
			if(i == 1) {
				activations[i] = new double[1];
			}else if(i == activations.length-1) {
//				otveti do 6 znakow 
				activations[i] = new double[6];
			}else {
				activations[i] = new double[7];
			}
			for(int j = 0; j< activations[i].length; j++ ) {
				activations[i][j] = 0;
			}
		}
				activationsWithoutSigmoid = activations;
				
				
		double[][] Errors = activations;
		
//		String[] input = new String[StData[0][dataNum].length];
//		for(int i = 0; i < StData[0][dataNum].length;i++) {
//			for(int j = 0; j < StData[0][dataNum][i].length; )
//			input[i] += "" + ;
//		}
		getResult(StData[0][dataNum]);
		for(int LSNum = 0; LSNum < activations[activations.length-1].length;LSNum++) {
			double q = StData[1][dataNum][LSNum];
			double w = activations[activations.length-1][LSNum];
			double e = activationsWithoutSigmoid[activations.length-1][LSNum];
			Errors[Errors.length-1][LSNum] = (q-w)*DirativeSigmoidFunction(e);
			
		}
		for(int Sl = 0; Sl < Errors.length-1; Sl++) {
			for(int SlNum = 0; SlNum < Errors[Sl].length; SlNum++) {
				double Err_sum = 0;
				
				for(int NSlNum = 0; NSlNum < Errors[Sl+1].length; NSlNum++) {
					Err_sum += (Errors[Sl+1][NSlNum]*weights[Sl][SlNum][NSlNum]);
				}
				Errors[Sl][SlNum] = Err_sum*DirativeSigmoidFunction(activationsWithoutSigmoid[Sl][SlNum]);
				
//				esli ne budet rabotat, to sdelat eto otdelno
				for(int NSlNum = 0; NSlNum < Errors[Sl+1].length; NSlNum++) {
					weights[Sl][SlNum][NSlNum] += SpeedA*activations[Sl][SlNum]*Errors[Sl+1][NSlNum];
				}
			}
		}
		
		}
		
	}
	private static double SigmoidFunction(double x) {
		return (double)(1/(1+(Math.exp(-x))));
	}
	private static double DirativeSigmoidFunction(double x) {
		return (double)(SigmoidFunction(x)*(1-SigmoidFunction(x)));
	}
	private static double backSigm(double x) {
		return (double)(-Math.log(((1/x) - 1)));
	}
	
}
