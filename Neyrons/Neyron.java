package Neyrons;

public abstract class Neyron {
protected float activity;
protected int outputConnections;

public void useFunction() {
	this.activity = (float)(1 / (1 + Math.pow(Math.E, (-1*this.activity))));
}
public static float getUseFunction(float inp) {
	return (float)(1 / (1 + Math.pow(Math.E, (-1*inp))));
}

public static float getUseFunctionDirative(float inp) {
	return (float)(getUseFunction(inp)*(1-getUseFunction(inp)));
}
public void setActivity(float input) {
	this.activity = input;
}
public void addToActivity(float input) {
	this.activity += input;
}
public float getActivity() {
	return this.activity;
}
public int getOutputConnections() {
	return this.outputConnections;
}
}
