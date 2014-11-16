package a5.application;

public class Sign implements SignImpl {
	private SignStatus status;
	
	public Sign(){
		status = SignStatus.vacancy;
	}
	/* (non-Javadoc)
	 * @see a4.application.SignImpl#refreshSign(a4.application.Spaces)
	 */
	@Override
	public void refreshSign(SpacesImpl s){
		if(s.getUsedSpaces()<s.getNumSpaces()){
			status = SignStatus.vacancy;
		}
		else status = SignStatus.full;
	}
	/* (non-Javadoc)
	 * @see a4.application.SignImpl#getSignStatus()
	 */
	@Override
	public SignStatus getSignStatus(){
		return status;
	}
	
	/* (non-Javadoc)
	 * @see a4.application.SignImpl#setSignStatus(a4.application.SignStatus)
	 */
	@Override
	public void setSignStatus(SignStatus s){
		status = s;
	}
}
