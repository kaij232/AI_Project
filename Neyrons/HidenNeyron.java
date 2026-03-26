package Neyrons;

public class HidenNeyron extends Neyron{
	protected int inputConnections;
	protected float[] afterWeights = new float[outputConnections];
	protected float baisd;
	
	public void setOutputConnections(int connections) {
	this.inputConnections = connections;
	}
	public void setInputConnections(int connections) {
	this.inputConnections = connections;
	}
	public void setBaisd(float baisd) {
		this.baisd = baisd;
	}
	public float[] getAfterWeights() {
		return this.afterWeights;
	}
	public float getBaisd() {
		return this.baisd;
	}
	public void setAfterWeights(float[] weight) {
		this.afterWeights = weight;
	}
	public void setAfterWeight(float weight, int index) {
		this.afterWeights[index] = weight;
	}
}
