package WorkPlace;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainFileWithoutNeyrons {
//static float[][] activations = {{0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0}};
//	sloy - {  number of Neyron - {k number of neyron - {znachenie wesa}}}
 static double[][][] weights = {{{1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1}}      ,{{1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1},  {1,1,1,1,1,1}},     {{1,1},  {1,1},  {1,1},  {1,1},  {1,1},  {1,1}},      {{1},  {1}}}; 
 static double[][] baisis = {{0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0}};
static double[][] resActivations = {{0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0}};
static double[][] resActivationsWithoutFunc = resActivations;
//Matrix dense = DenseMatrix.Factory.zeros(4, 4);
static double[][][] dataForStydi = {{{0,0,0,0,0},{1,0,0,0,0},{0,1,0,0,0},{0,0,1,0,0},{0,0,0,1,0},{0,0,0,0,1},{1,1,0,0,0},{1,0,1,0,0},{1,0,0,1,0},{1,0,0,0,1},{1,1,1,0,0},{0,1,1,0,0},{0,0,0,1,1},{0,1,0,1,0},{1,0,1,0,1},{1,1,1,1,0},{1,1,1,1,1},{1,1,0,1,1},{0,1,1,1,1}},		{{1,0},{1,0},{1,0},{1,0},{1,0},{1,0},{1,0},{1,0},{1,0},{1,0},{0,1},{1,0},{1,0},{1,0},{0,1},{0,1},{0,1},{0,1},{0,1}}};
static double[][][] dataForStydi2 = {{{0,0,0,0,0},{0,0,0,0,1},{0,0,0,1,0},{0,0,0,1,1},{0,0,1,0,0},{0,0,1,0,1},{0,0,1,1,0},{0,0,1,1,1},{0,1,0,0,0},{0,1,0,0,1},{0,1,0,1,0},{0,1,0,1,1},{0,1,1,0,0},{0,1,1,0,1},{0,1,1,1,0},{0,1,1,1,1},{1,0,0,0,0},{1,0,0,0,1},{1,0,0,1,0},{1,0,0,1,1},{1,0,1,0,0},{1,0,1,0,1},{1,0,1,1,0},{1,0,1,1,1},{1,1,0,0,0},{1,1,0,0,1},{1,1,0,1,0},{1,1,0,1,1},{1,1,1,0,0},{1,1,1,0,1},{1,1,1,1,0},{1,1,1,1,1}},{{1,0},{1,0},{1,0},{1,0},{1,0},{1,0},{1,0},{0,1},{1,0},{1,0},{1,0},{0,1},{1,0},{0,1},{0,1},{0,1},{1,0},{1,0},{1,0},{0,1},{1,0},{0,1},{0,1},{0,1},{1,0},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}}};
 public static void main(String args[]) throws IOException {
	 
//	 zapolnenie vseh weights na 1
	 for(int i = 0; i < weights.length; i++) {
		 for(int j = 0; j < weights[i].length; j++) {
			 for(int k = 0; k < weights[i][j].length; k ++) {
				 weights[i][j][k] = (double) Math.random();
			 }
		 }
	 }

	 
	 
//	 upravlenie programmoy
		Scanner scan = new Scanner(System.in);
		int controller = -1; 
		double[] input = new double[] {0,1,0,0,1};
		System.out.println("0 - stop; \n1 - get result; \n2 - study; \n3 - new input; \n4 - save weights; \n5 - set weights;");
		while(0 != 1) {
//			try {
			controller = scan.nextInt();
			if(controller == 1) {
				double[] resNeyrons = getResult(input);
				for(int i = 0; i < resNeyrons.length;i++) {
					System.out.println("Neyr " + i + ": "+resNeyrons[i]);
				}
			}else if(controller == 0) {System.exit(0);
			}else if(controller == 2) {
				System.out.println("How many times: ");
				int times = scan.nextInt();
				System.out.println("Styding Speed: ");
				float SpeedA = scan.nextFloat();
				Study(times, SpeedA, dataForStydi2);
				System.out.println("Styding is ready!");
			}else if(controller == 3) {
				String per3 = scan.next();
				for(int qwe = 0; qwe< input.length; qwe++) {
					 Double qwer = Double.parseDouble(per3.split(",")[qwe]);
					input[qwe] = qwer;
				}
//				C:\Users\я\eclipse-workspace\workspace\EE_XAR_StydingV2\src\WorkPlace\weightsDATA\weightsFile.txt
//					"C:\\Users\\я\\eclipse-workspace\\workspace\\EE_XAR_StydingV2\\src\\WorkPlace\\weightsDATA\\weightsFile.txt"
			}else if(controller == 4) {
//
//				List lines = Files.readAllLines(Paths.get("C:\\Users\\я\\eclipse-workspace\\workspace\\EE_XAR_StydingV2\\src\\WorkPlace\\weightsDATA\\weightsFile.txt"));
//				//перебираете циклом
//				for(int i = 0; i< lines.size(); i++)
//				{
//				lines.set(i, "jordijl");
//				}
//				/* Если нужно удалить - для перебора используйте Iterator.
//				Не забудьте : */ 
//				Files.write(Paths.get("C:\\Users\\я\\eclipse-workspace\\workspace\\EE_XAR_StydingV2\\src\\WorkPlace\\weightsDATA\\weightsFile.txt"), lines);
//				//иначе результат не запишется
				
				
		        try{
		            File file = new File("C:\\Users\\я\\eclipse-workspace\\workspace\\EE_XAR_StydingV2\\src\\WorkPlace\\weightsDATA\\weightsFile.txt");
//		            file.createNewFile();
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
//		            FileReader fr = new FileReader(file);
		            
		            
		            
////		            5 - setWeights:
//		            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\я\\eclipse-workspace\\workspace\\EE_XAR_StydingV2\\src\\WorkPlace\\weightsDATA\\weightsFile.txt")));
//		            try {
//		                StringBuilder sb = new StringBuilder();
//		                String line = br.readLine();
//		                while (line != null) {
//		                    sb.append(line);
//		                    sb.append(System.lineSeparator());
//		                    line = br.readLine();
//		                }
//		                String dataWeights = sb.toString();
//		                dataWeights.replace(",;", ";");
//			            for(int i = 0; i < weights.length; i++) {
//			            	for(int j = 0; j < weights[i].length; j++) {
//			            		for(int k = 0; k < weights[i][j].length; k++) {
//			            			
//					            	weights[i][j][k] = Double.parseDouble(dataWeights.split(";")[i].split(",")[j].split(" ")[k]);
//					            	
//					            }
//				            }
//			            }
//		                
//		            } finally {
//		                br.close();
//		            }

		                
		              
		            
		            
		            

		        }catch(IOException e){
		        e.printStackTrace();
		        
		        
		        
		        }
		    
				System.out.println("Saving has been completed!");
		}else if(controller == 5) {
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\я\\eclipse-workspace\\workspace\\EE_XAR_StydingV2\\src\\WorkPlace\\weightsDATA\\weightsFile.txt")));
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

//			}catch(java.util.InputMismatchException err) {
//				System.out.println("Uncorrect input!");
//				
//			}
											
 }
		}



//sdelat obuchenie
//sdelat errors


private static double[] getResult(double[] input) {
	for(int per1 = 0; per1 < resActivations.length; per1++) {
		for(int per2 = 0; per2 < resActivations[per1].length; per2++) {
			resActivations[per1][per2] = 0;
			resActivationsWithoutFunc[per1][per2] = 0;
		}
	}
			resActivations[0] = input;
	resActivationsWithoutFunc[0] = input;
	for(int sl = 1; sl < resActivations.length; sl ++) {
		for(int Nnum = 0; Nnum < resActivations[sl].length; Nnum++) {
			for(int NnumBF = 0; NnumBF < resActivations[sl-1].length; NnumBF ++) {
			resActivationsWithoutFunc[sl][Nnum] += resActivations[sl-1][NnumBF] * weights[sl-1][NnumBF][Nnum];
			resActivationsWithoutFunc[sl][Nnum] += baisis[sl][Nnum];
			}
			resActivations[sl][Nnum] = useSigmaFunction(resActivationsWithoutFunc[sl][Nnum]);
		}
	}
	return resActivations[resActivations.length-1];
}

private static double[] getResultWithoutActivations(double[] input) {
	double[][] ress = resActivations;
	double[][] ressVF = resActivationsWithoutFunc;
	ress[0] = input;

	for(int sl = 1; sl < ress.length; sl ++) {
		for(int Nnum = 0; Nnum < ress[sl].length; Nnum++) {
			for(int NnumBF = 0; NnumBF < ress[sl-1].length; NnumBF ++) {
			ressVF[sl][Nnum] += ress[sl-1][NnumBF] * weights[sl-1][NnumBF][Nnum];
			ressVF[sl][Nnum] += baisis[sl][Nnum];
			}
			ress[sl][Nnum] = useSigmaFunction(ressVF[sl][Nnum]);
		}
	}
	return ress[ress.length-1];
}

public static double useSigmaFunction(double a) {
	return  (double)(1 / (1 + Math.pow(Math.E , -a)));
	
}
public static double useProizvSigmaFunction(double x) {
	return (double)(useSigmaFunction(x)*(1-useSigmaFunction(x)));
}
private static void Study(int times, double speedA,double[][][] dataForStydi) {
	double[][] inp = dataForStydi[0];
	double[] nowRes = {};
	double[] trueRes = {};
	double[][] nowActivations = resActivations;
for(int p = 0; p < times; p++) {
//	ctob bili raznie data 
for(int inpNum = 0; inpNum < inp.length; inpNum ++) {
	nowRes = getResultWithoutActivations(inp[inpNum]);
	trueRes = dataForStydi[1][inpNum];
	
	
	double[][] Errors = new double[weights.length][];
	for(int per = 0; per < Errors.length;per++) {

		Errors[per] = new double[weights[per].length];
	}
		
		for(int q = Errors.length-1; q >= 0 ;q--) {
	
					for(int w = 0; w < Errors[q].length; w++) {
						try {
						for(int e = 0; e < Errors[q+1].length; e++) {
								addX(Errors[q], 0f);
								Errors[q][w] += (Errors[q+1][e] * weights[q][w][e]);
						
						}
							
						}catch(java.lang.ArrayIndexOutOfBoundsException  er) {

									addX(Errors[q], 0f);
							Errors[q][w] += (nowRes[w] - trueRes[w])*useProizvSigmaFunction(resActivationsWithoutFunc[q][w]);
								
									
								
								}
									
						}
						}
					
	
//	posl -2 t.k. poslednee iz output ne menyt 
	for(int slNum = weights.length-2; slNum >= 0; slNum--) {
		
		for(int fN = 0; fN < weights[slNum].length; fN++) {
			for(int sN = 0; sN < weights[slNum+1].length;sN++) {

				if(slNum == weights.length-2) {
					weights[slNum][fN][sN] -= (speedA * Errors[slNum+1][sN] * nowActivations[slNum][fN]);
				}else {
					weights[slNum][fN][sN] -= (speedA * nowActivations[slNum][fN] * (useProizvSigmaFunction(resActivationsWithoutFunc[slNum+1][sN]) * Errors[slNum+1][sN])) ;
					baisis[slNum][fN] += (speedA * Errors[slNum][fN]);
				}
				
			}
		}
	}
	Errors = null;
}
}
	
}

//public static void easierStudy() {
//	
//}
public static double[] addX(double[] arr, double x) {
	double[] arrX = new double[arr.length+1]; 
	for(int i = 0; i < arr.length; i++) {
		arrX[i] = arr[i];
	}
	arrX[arr.length] = x;
	
	
	return arrX;
}
}
