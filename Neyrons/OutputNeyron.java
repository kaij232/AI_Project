package Neyrons;

public class OutputNeyron extends Neyron{
	
	protected int inputConnections;
	protected final int outputConnections = 1;
	protected float baisd;
	
	public void setInputConnections(int connections) {
	this.inputConnections = connections;
	}
	public void setBaisd(float baisd) {
		this.baisd = baisd;
	}
	public float getBaisd() {
		return this.baisd;
	}
}
