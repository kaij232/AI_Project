package WorkPlace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class mainWithoutNeyronsVersion2 {
	 static double[][][] weights = {{{1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1}}      ,{{1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1}},     {{1,1},  {1,1},  {1,1},  {1,1},  {1,1},  {1,1}},      {{1},  {1}}}; 
	 static double[][][] dataForStydi2 = {{{0,0,0,0,0},{0,0,0,0,1},{0,0,0,1,0},{0,0,0,1,1},{0,0,1,0,0},{0,0,1,0,1},{0,0,1,1,0},{0,0,1,1,1},{0,1,0,0,0},{0,1,0,0,1},{0,1,0,1,0},{0,1,0,1,1},{0,1,1,0,0},{0,1,1,0,1},{0,1,1,1,0},{0,1,1,1,1},{1,0,0,0,0},{1,0,0,0,1},{1,0,0,1,0},{1,0,0,1,1},{1,0,1,0,0},{1,0,1,0,1},{1,0,1,1,0},{1,0,1,1,1},{1,1,0,0,0},{1,1,0,0,1},{1,1,0,1,0},{1,1,0,1,1},{1,1,1,0,0},{1,1,1,0,1},{1,1,1,1,0},{1,1,1,1,1}},{{1,0},{1,0},{1,0},{1,0},{1,0},{1,0},{1,0},{0,1},{1,0},{1,0},{1,0},{0,1},{1,0},{0,1},{0,1},{0,1},{1,0},{1,0},{1,0},{0,1},{1,0},{0,1},{0,1},{0,1},{1,0},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}}};
static double[][] activations = {{0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0}};
static double[][] activationsWithoutFunction = {{0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0}};
	 public static void main(String args[]) throws IOException {
		 
//		 zapolnenie vseh weights na 1
		 for(int i = 0; i < weights.length; i++) {
			 for(int j = 0; j < weights[i].length; j++) {
				 for(int k = 0; k < weights[i][j].length; k ++) {
					 weights[i][j][k] = (double) Math.random();
				 }
			 }
		 }

		 
		 
//		 upravlenie programmoy
			Scanner scan = new Scanner(System.in);

			int controller = -1; 
			double[] input = new double[] {0,1,0,0,1};
			System.out.println("0 - stop; \n1 - get result; \n2 - study; \n3 - new input; \n4 - save weights; \n5 - set weights;");
			while(0 != 1) {
//				try {
				controller = scan.nextInt();
				if(controller == 1) {
					String InputPrint = "";
					double[] resNeyrons = getResult(input);
					for(int i = 0; i < resNeyrons.length;i++) {
						System.out.println("Neyr " + i + ": "+resNeyrons[i]);
					}
					for(int i = 0; i < input.length;i++) {
						InputPrint  +=  " "+ input[i];
					}
					System.out.println("input:" + InputPrint);					
					if(resNeyrons[0] > resNeyrons[1]) {
						System.out.println("Result: 0");
					}else {
						System.out.println("Result: 1");
					}
				}else if(controller == 0) {System.exit(0);
				}else if(controller == 2) {
					System.out.println("How many times: ");
					int times = scan.nextInt();
					System.out.println("Styding Speed: ");
					double SpeedA = Double.parseDouble(scan.next());
					Study(times, SpeedA, dataForStydi2);
					System.out.println("Styding is ready!");
				}else if(controller == 3) {
					System.out.println("input like \"1,1,1,1,1 \" ");
					String per3 = scan.next();
					for(int qwe = 0; qwe< input.length; qwe++) {
						 Double qwer = Double.parseDouble(per3.split(",")[qwe]);
						input[qwe] = qwer;
					}
//					C:\Users\я\eclipse-workspace\workspace\EE_XAR_StydingV2\src\WorkPlace\weightsDATA\weightsFile.txt
//						"C:\\Users\\я\\eclipse-workspace\\workspace\\EE_XAR_StydingV2\\src\\WorkPlace\\weightsDATA\\weightsFile.txt"
				}else if(controller == 4) {

					
					
			        try{
			            File file = new File("C:\\Users\\я\\eclipse-workspace\\workspace\\EE_XAR_StydingV2\\src\\WorkPlace\\weightsDATA\\weightsFile.txt");

			            FileWriter fw = new FileWriter(file);
			            BufferedWriter bw = new BufferedWriter(fw);
			            String per4 = "";
			            for(int i = 0; i < weights.length; i++) {
			            	for(int j = 0; j < weights[i].length; j++) {
			            		for(int k = 0; k < weights[i][j].length; k++) {
			            			
					            	per4 += ("" + weights[i][j][k]) + " ";
					            	
					            }
			            		per4 += ",";
				            }
			            	per4 += ";";
			            }
			            bw.write(per4);
			            bw.flush();
			            bw.close();


			                
			              
			            
			            
			            

			        }catch(IOException e){
			        e.printStackTrace();
			        
			        
			        
			        }
			    
					System.out.println("Saving has been completed!");
			}else if(controller == 5) {
	            BufferedReader br = new BufferedReader(new FileReader(new File("D:\\DevelopingPrograms\\eclipse\\workspace\\EE_XAR_StydingV2\\src\\WorkPlace\\weightsDATA\\weightsFile.txt")));
	            try {
	                StringBuilder sb = new StringBuilder();
	                String line = br.readLine();
	                while (line != null) {
	                    sb.append(line);
	                    sb.append(System.lineSeparator());
	                    line = br.readLine();
	                }
	                String dataWeights = sb.toString();
	                dataWeights.replace(",;", ";");
		            for(int i = 0; i < weights.length; i++) {
		            	for(int j = 0; j < weights[i].length; j++) {
		            		for(int k = 0; k < weights[i][j].length; k++) {
		            			
				            	weights[i][j][k] = Double.parseDouble(dataWeights.split(";")[i].split(",")[j].split(" ")[k]);
				            	
				            }
			            }
		            }
	                
	            } finally {
	                br.close();
	            }
			}

												
	 }
			}
	 
	 private static double SigmoidFunction(double x) {
		 return (double)(1/(1+Math.pow(Math.E, (-x))));
	 }
	 
	 private static double dirativeSigmoidFunction(double x) {
		 return (double)(SigmoidFunction(x)*(1 - SigmoidFunction(x)));
	 }

	private static void Study(int times, double speedA, double[][][] dataForStydi22) {
		for(int i = 0; i <= times; i++) {
			backPropogation(speedA,dataForStydi22);
		}
		
	}

	private static void backPropogation(double speedA, double[][][] dataForStydi22) {
		for(int dataNum = 0; dataNum < dataForStydi22[0].length; dataNum++) {
			double[][][] DeltaWeights  = weights;
			double[][] Errors = activations;
			getResult(dataForStydi22[0][dataNum]);
			getResultWithoutFunction(dataForStydi22[0][dataNum]);
			
//			Last sloy oshibki
			for(int LSNum = 0;LSNum <  Errors[Errors.length-1].length; LSNum++){
				Errors[Errors.length-1][LSNum] = (dataForStydi22[1][dataNum][LSNum] - activations[activations.length-1][LSNum])*dirativeSigmoidFunction(activationsWithoutFunction[activationsWithoutFunction.length-1][LSNum]);
				
			}
			
//			Hiden and first sloy oshibki
			for(int HSl = 0; HSl < Errors.length-1;HSl++) {
				for(int HSNum = 0; HSNum < Errors[HSl].length; HSNum ++) {
					double sum_Err = 0;
					for(int HNSNum = 0; HNSNum < Errors[HSl+1].length; HNSNum ++) {
//						double hjk = Errors[HSl+1][HNSNum];
//						double[][] qa = weights[HSl];
//						double[] qb = qa[HSNum];
//						double qwe = qb[HNSNum];
//						sum_Err += (hjk*qwe);
						sum_Err += (Errors[HSl+1][HNSNum]*weights[HSl][HSNum][HNSNum]);
					}
					Errors[HSl][HSNum] = (sum_Err * dirativeSigmoidFunction(activationsWithoutFunction[HSl][HSNum])) ;
				}
			}
//		vichislenie DeltaWeights i zanesenie nowih weights
		for(int HSl = 0; HSl < Errors.length-1;HSl++) {
			for(int HSNum = 0; HSNum < Errors[HSl].length; HSNum ++) {
				for(int HNSNum = 0; HNSNum < Errors[HSl+1].length; HNSNum ++) {
					DeltaWeights[HSl][HSNum][HNSNum] = speedA* activations[HSl][HSNum]*Errors[HSl+1][HNSNum];
					weights[HSl][HSNum][HNSNum] += DeltaWeights[HSl][HSNum][HNSNum];
						}
					}
				}
	}
	}
	private static double[] getResult(double[] input) {
	activations[0] = input;
	
//	obnulenie vseh
	for(int sl = 1; sl < activations.length; sl++) {
		for(int slNum = 0; slNum < activations[sl].length; slNum++) {
				activations[sl][slNum] = 0; 
			
		}
	}
	
//	weights and Sigma using
	for(int sl = 1; sl < activations.length; sl++) {
		for(int slNum = 0; slNum < activations[sl].length; slNum++) {
			for(int bslNum = 0; bslNum < activations[sl-1].length; bslNum++) {
//				double w  = weights[sl-1][slNum][bslNum];
//				double a = activations[sl-1][slNum];
//				activations[sl][bslNum] += (a * w );
				activations[sl][slNum] +=(activations[sl-1][bslNum]*weights[sl-1][bslNum][slNum]);
			}
			activations[sl][slNum] = SigmoidFunction(activations[sl][slNum]);
		}
	}
	
	
	return activations[activations.length-1];
	}
	
	private static void getResultWithoutFunction(double[] input) {
	
//	obnulenie vseh
	for(int sl = 1; sl < activationsWithoutFunction.length; sl++) {
		for(int slNum = 0; slNum < activationsWithoutFunction[sl].length; slNum++) {
			activationsWithoutFunction[sl][slNum] = 0; 
			
		}
		
		
	}
			getResult(input);
			activationsWithoutFunction[0] = input;
			
			
//	weights using
	for(int sl = 1; sl < activationsWithoutFunction.length; sl++) {
		for(int slNum = 0; slNum < activationsWithoutFunction[sl].length; slNum++) {
			for(int bslNum = 0; bslNum < activationsWithoutFunction[sl-1].length; bslNum++) {
//				double w  = weights[sl-1][slNum][bslNum];
//				double a = activations[sl-1][slNum];
//				activations[sl][bslNum] += (a * w );
				activationsWithoutFunction[sl][slNum] +=(activations[sl-1][bslNum]*weights[sl-1][bslNum][slNum]);
			}

		}
	}
	
	
	}

}
