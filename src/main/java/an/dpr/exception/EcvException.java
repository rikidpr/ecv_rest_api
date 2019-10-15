package an.dpr.exception;

/**
 * This exception is only to manage messages to the api client in case of error
 * @author riki
 *
 */
public class EcvException extends Exception{

	public EcvException(String string) {
		super(string);
	}
	
	public EcvException(Throwable t) {
		super(t);
	}
	
	public EcvException(String msg, Throwable t) {
		super(msg, t);
	}

	private static final long serialVersionUID = -3087362481584178534L;

	
}
