package WorkPlace;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RimskiyNumberEE_V2 {
	 final static int DataLength = 20;
 	 final static int Layers = 6;
	 final static int[] Neyrons =  new int[]{6, 7, 8, 8, 7, 60};
	 
 public static void main(String[] args) {
	 double[][][] Data = getData();
	 String out = "{";
	 for(int i = 0;i < Data[0].length;i++) {
		 out+= "{";
		 for(int j = 0; j< Data[0][i].length;j++) {
			 if(j == Data[0][i].length-1) {
				 out += Data[0][i][j];
			 }else {
			out += Data[0][i][j]+",";
			 }
		 }
		 if(i == Data[0].length-1) {
			 out += "}";
		 }else {
		 out+= "},";
		 }
	 }
	 out+= "},{";
	 for(int i = 0;i < Data[1].length;i++) {
		 out+= "{";
		 for(int j = 0; j< Data[1][i].length;j++) {
			 if(j == Data[0][i].length-1) {
				 out += Data[1][i][j];
			 }else {
			out += Data[1][i][j]+",";
			 }
		 }
		 if(i == Data[1].length-1) {
			 out += "}";
		 }else {
		 out+= "},";
		 }
	 }
	 out+="}";
System.out.println(out);
	 
  Scanner scan = new Scanner(System.in);
  EiskustveniyIntelekt ee = new EiskustveniyIntelekt(Layers, Neyrons);
  
  int con = -1;
  while(0!= 1){
      con = scan.nextInt();
      if(con == 0){
      System.exit(1);
      }else if(con == 1){
    	  String input = scan.next();
    	  double[] doubleInput = convertToStandrtInputViewToUseSigmoid(input, true);
          double[] res = ee.getRes(doubleInput);
          String Sres = "";
          for(int i = 0; i < res.length;i++){
              Sres += "" + Math.round(Functions.UnSigmoidFunc(res[i]));
          }
          System.out.println(Sres);
      }else if(con == 2) {
    	  try {
    	  ee.Study(scan.nextInt(), scan.nextDouble());
    	  System.out.println("Studing is ready!");
    	  }catch(java.util.InputMismatchException e) {
    		  System.out.println("Speed is writing by \",\"");
    		  scan.next();
    	  }
      }
  }
 }
 
 
 public static double[][][] getData(){
	 
	 double[][] Data1 = new double[DataLength][Neyrons[Neyrons.length - 1]];
	 double[][] Data0 = new double[DataLength][Neyrons[Neyrons.length - 1]];
//	 int[][] Data0 = new int[][] {{1},{2},{3},{4},{5},{6},{7},{8},{9},{10},{12},{13},{14},{15},{16},{17},{18},{19},{20}};
	 String[] Data0R = new String[] {"O","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX"};
	 double[][][] Data = new double[2][DataLength][Neyrons[Neyrons.length - 1]];
	 
//	 for(int i = 0; i < Data0.length; i++) {
//		 char[] num = (""+ i).toCharArray(); 
//		  for(int j = Data0[i].length-1; j >= 0; j--) {
////			  for(int k = num.length-1; k >= 0; k--) {
//			  try {
//				  Data0[i][j] = Integer.parseInt(""+num[j]);	  
//			  }catch(java.lang.ArrayIndexOutOfBoundsException e) {
//				  Data0[i][j] = 0;
//			  }
//			  Data[0][i][j] = Data0[i][j];
////			  }
//		  }
//	 }
	 
	 for(int i = 0; i < Data1.length;i++) {
		 for(int j = 0; j < Data1[i].length; j++){
			 Data0[i][j] = 0;
			 Data1[i][j] = 0;
		 	}
	 }	 
	 
	 
	 
	 

		 for(int i = 0; i < Data1.length;i++) {
			 Data1[i] = convertToStandrtInputViewToUseSigmoid(i+"", false);
			 Data[0][i] = convertToStandrtInputViewToUseSigmoid(Data0R[i],true);
		 }	

	 Data[1] = Data1;
	 
	 return Data;
 }
 private static double[] convertToStandrtInputView(String inp, boolean ConvertToHash) {
	 double[] outp = new double[Neyrons[0]];
	 char[] num = inp.toCharArray();
	 for(int i = 0; i<outp.length; i++) {
		 outp[i] = 0;
	 }
	 if(!ConvertToHash) {
	 for(int j = 0; j< num.length; j++) {
		 outp[outp.length-num.length+j] = Integer.parseInt(num[j]+"");
	 }
	 }else {
		 for(int j = 0; j< num.length; j++) {
			 outp[outp.length-num.length+j] = (num[j]+"").hashCode();
		 } 
	 }
	 return outp;
 }
 private static double[] convertToStandrtInputViewToUseSigmoid(String inp, boolean ConvertToHash) {
	 double[] outp = new double[Neyrons[0]];
	 char[] num = inp.toCharArray();
	 for(int i = 0; i<outp.length; i++) {
		 outp[i] = Functions.SigmoidFunc(0.0d);
		
	 }
	 if(!ConvertToHash) {
	 for(int j = 0; j< num.length; j++) {
		 outp[outp.length-num.length+j] = Functions.SigmoidFunc((Integer.parseInt(num[j]+"")));
	 }
	 }else {
		 for(int j = 0; j< num.length; j++) {
			 outp[outp.length-num.length+j] = 1/(1+Math.pow(Math.E,-0.01*(num[j]+"").hashCode()));
		 } 
	 }
	 return outp;
 }
 
 
 
 
 static class EiskustveniyIntelekt{
	 
	final private double[][][] Data = getData();
     private double[][][] weights;
     private double[][] activations; 
     private double[][] actWithoutSigm;
     
     public EiskustveniyIntelekt(int NeyronLauersCount, int[] NeyronsCount){
         
         activations = new double[NeyronLauersCount][];
         weights = new double[NeyronLauersCount-1][][];
         
         for(int i = 0; i < activations.length; i++){
             activations[i] = new double[NeyronsCount[i]];
             try {
             weights[i] = new double[NeyronsCount[i]][];
             }catch(java.lang.ArrayIndexOutOfBoundsException e) {

             }
             for(int j = 0; j < activations[i].length; j++){
                 activations[i][j] = 0;
             }
             
         }
         actWithoutSigm = activations;
         
         
         
         for(int i = 0; i < weights.length; i++){
            for(int j = 0; j < weights[i].length; j++){
               weights[i][j] = new double[activations[i+1].length];
               for(int k = 0; k < weights[i][j].length; k++){
                   weights[i][j][k] = (double)ThreadLocalRandom.current().nextDouble(0, 1);
               }
            }
         }
     }
     
     
     
     private double[] getRes(double[] input){
         zeroingActAndActWS();
         activations[0] = input;
         actWithoutSigm[0] = input;
         
         for(int i = 0; i < weights.length; i ++){
             for(int j = 0; j < weights[i].length;j++){
                 for(int k = 0; k < weights[i][j].length; k++){
                     actWithoutSigm[i+1][k] += activations[i][j]*weights[i][j][k];
                       
                 }
                 
             }
       for(int k = 0; k < activations[i+1].length; k++){
             activations[i+1][k] = Functions.SigmoidFunc(actWithoutSigm[i+1][k]);    
             }
         }
         return activations[activations.length-1];
     }
     private void zeroingActAndActWS(){
         for(int i = 0; i < activations.length;i++){
            for(int j = 0; j < activations[i].length; j++){
                activations[i][j] = 0;
                actWithoutSigm[i][j] = 0;
                 } 
         }
     }
     
     public void Study(int Times, double ASpeed) {
    	 for(int i = 0; i < Times; i++) {
    		 BackPropogationsFunction(ASpeed);
    	 }
     }
     
     
     
     
     private void BackPropogationsFunction(double aSpeed) {

//		for(int i = 0; i < errors.length; i ++) {
//			errors[i] = new double[Neyrons[i]];
//			for(int j = 0; j < errors[i].length; j++) {
//				errors[i][j] = 0;
//			}
//		}
		for(int dataNum = 0; dataNum < Data[0].length; dataNum++) {
			
			
			zeroingActAndActWS();
			double[][] errors = activations;
			
			getRes(Data[0][dataNum]);
			for(int i = 0; i< errors[errors.length-1].length;i++) {
				errors[errors.length-1][i] = (Data[1][dataNum][i] - activations[activations.length-1][i])*Functions.DirativeSigmoidFunc(actWithoutSigm[activations.length-1][i]);
			}
			for(int i = errors.length-2; i <= 0;i--) {
				for(int j = 0; j < errors[i].length; j++) {
					double errSumm = 0;
					for(int k = 0; k < errors[i+1].length;k++) {					
						errSumm += errors[i+1][k];
					}
					errors[i][j] = errSumm*Functions.DirativeSigmoidFunc(actWithoutSigm[i][j]);
				}
			}
			
			for(int i = 0; i < weights.length;i++) {
				for(int j = 0; j < weights[i].length;j++) {
					for(int k = 0; k < weights[i][j].length; k++) {

						weights[i][j][k] += aSpeed*errors[i+1][k]*activations[i][j];

					}
				}
			}
		}
	}




     
     
 }
 class Functions{
		private static double SigmoidFunc(double x){
         return (double)(1/(1+Math.pow(Math.E, -x)));
     }
     private static double DirativeSigmoidFunc(double x){
         return (double)(SigmoidFunc(x)*(1-SigmoidFunc(x)));
     }
     
     private static double UnSigmoidFunc(double x){
         return (double)(-Math.log((1/x)-1));
     } 
 }
}