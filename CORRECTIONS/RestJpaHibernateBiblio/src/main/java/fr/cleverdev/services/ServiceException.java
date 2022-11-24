package fr.cleverdev.services;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 5268445984582596463L;

    public ServiceException( String message ) {
        super( message );
    }

    public ServiceException( String message, Throwable cause ) {
        super( message, cause );
    }

    public ServiceException( Throwable cause ) {
        super( cause );
    }

}
