package a4.application;

public interface GateImpl {

	void closeGate();

	void openGate();

	GateType getType();

	boolean getGateOpenStatus();

}