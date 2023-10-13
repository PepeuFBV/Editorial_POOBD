package exceptions;

public class ErroLoginException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
    public ErroLoginException() {
        super("Login inválido.");
    }

    public ErroLoginException(String string) {
        super(string);
    }
}
