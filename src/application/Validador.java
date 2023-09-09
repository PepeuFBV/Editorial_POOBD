package application;

public class Validador {

    public static boolean validarLogin(String login) {
        //o login pode conter letras minúsculas, letras maiúsculas, números
        //e pode ter entre 6 e 20 caracteres.
        String regex = "^[a-zA-Z0-9]{6,20}$";
        return login.matches(regex);
    }
    
    public static boolean validarSenha(String senha) {
        //a senha precisa conter pelo menos 8 caracteres, com pelo menos uma
        //letra minúscula, uma letra maiúscula, um número e um caractere especial.
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return senha.matches(regex);
    }
    
}