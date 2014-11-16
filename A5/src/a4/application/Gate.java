package a4.application;

public class Gate implements GateImpl {
	private GateType type;
	private boolean gateOpenStatus;
	
	public Gate(GateType t){
		type = t;
		gateOpenStatus = false;
	}
	
	/* (non-Javadoc)
	 * @see a4.application.GateImpl#closeGate()
	 */
	@Override
	public void closeGate(){
		gateOpenStatus = false;
		System.out.println(type + " gate closed.\n");
	}
	/* (non-Javadoc)
	 * @see a4.application.GateImpl#openGate()
	 */
	@Override
	public void openGate(){
		gateOpenStatus = true;
		System.out.println(type + " gate open.");
	}	
	/* (non-Javadoc)
	 * @see a4.application.GateImpl#getType()
	 */
	@Override
	public GateType getType(){
		return type;
	}
	/* (non-Javadoc)
	 * @see a4.application.GateImpl#getGateOpenStatus()
	 */
	@Override
	public boolean getGateOpenStatus(){
		return gateOpenStatus;
	}
	

}
