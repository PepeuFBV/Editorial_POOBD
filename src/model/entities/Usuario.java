public class Usuario {
    private int id;
    private String nome;
    private String login;
    private String senha;

    public Usuario() { }

    public Usuario(String nome, String login, String senha) {
        setNome(nome);
        setLogin(login);
        setSenha(senha);
        setId();
    }

    public int getId() {
        return this.id
    }

    public void setId(int id) {
        if (id == null) {
            this.id = 1;
        }
        else {
            this.id += 1;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome != null && nome != "") {
            this.nome = nome;
        }
    }
    
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        if (login != null && login != "") {
            this.login = login;
        }
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        if (senha != null && senha != "") {
            this.senha = senha;
        }
    }

    //a serem consertados depois
    public boolean logar(String login, String senha, Usuario usuario) {
		if (login.equals(usuario.login) && senha.equals(usuario.senha))
			return true;
		else
			return false;
	}

}
