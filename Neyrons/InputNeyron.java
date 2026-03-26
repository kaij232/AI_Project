package Neyrons;

public class InputNeyron extends Neyron {
	
	protected final int inputConnections = 1;
	protected int outputConnections;
	protected float[] afterWeights = new float[outputConnections];
	
	
	public void setOutputConnections(int connections) {
	this.outputConnections = connections;
	}
	public void setAfterWeights(float[] weight) {
		this.afterWeights = weight;
	}
	public float[] getAfterWeights() {
		return this.afterWeights;
	}
	public void setAfterWeight(float weight, int index) {
		this.afterWeights[index] = weight;
	}

}
