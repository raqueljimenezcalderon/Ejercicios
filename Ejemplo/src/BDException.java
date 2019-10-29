

public class BDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1351538479942433456L;
	
	public BDException(String mensaje, Throwable e) {
		super (mensaje,e);
	}
	
}
