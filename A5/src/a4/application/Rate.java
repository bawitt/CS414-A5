package a4.application;

public class Rate implements RateImpl {
	private double rate;
	private double flatRate;
	
	public Rate(double r, double fr){
		rate = r;
		flatRate = fr;
	}
	public Rate(){
		rate = 0;
		flatRate = 0;
	}
	
	/* (non-Javadoc)
	 * @see a4.application.RateImpl#getStandardRate()
	 */
	@Override
	public double getStandardRate(){
		return rate;
	}
	/* (non-Javadoc)
	 * @see a4.application.RateImpl#setStandardRate(double)
	 */
	@Override
	public void setStandardRate(double r){
		rate = r;
	}
	
	/* (non-Javadoc)
	 * @see a4.application.RateImpl#getFlatRate()
	 */
	@Override
	public double getFlatRate(){
		return flatRate;
	}
	/* (non-Javadoc)
	 * @see a4.application.RateImpl#setFlatRate(double)
	 */
	@Override
	public void setFlatRate(double r){
		flatRate = r;
	}
}
