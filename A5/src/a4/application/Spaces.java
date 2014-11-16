package a4.application;

public class Spaces implements SpacesImpl {
	private int numSpaces;
	private int usedSpaces;
	
	public Spaces(int s, int u){
		numSpaces = s;
		usedSpaces = u;
	}
	public Spaces(){
		numSpaces = 0;
		usedSpaces = 0;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#incEnter()
	 */
	@Override
	public void incEnter(){
		usedSpaces = usedSpaces + 1;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#decExit()
	 */
	@Override
	public void decExit(){
		usedSpaces = usedSpaces - 1;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#setNumSpaces(int)
	 */
	@Override
	public void setNumSpaces(int s){
		numSpaces = s;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#getNumSpaces()
	 */
	@Override
	public int getNumSpaces(){
		return numSpaces;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#setUsedSpaces(int)
	 */
	@Override
	public void setUsedSpaces(int s){
		usedSpaces = s;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#getUsedSpaces()
	 */
	@Override
	public int getUsedSpaces(){
		return usedSpaces;
	}
}
