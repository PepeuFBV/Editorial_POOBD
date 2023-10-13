package exceptions;
import java.sql.SQLException;

public class DeletionException extends SQLException {

	private static final long serialVersionUID = 1L;

	public DeletionException(String mensagem) {
		super(mensagem);
	}
}