package exceptions;
import java.sql.SQLException;

public class InsertException extends SQLException {

	private static final long serialVersionUID = 1L;

	public InsertException(String mensagem) {
		super(mensagem);
	}
}
