package model.entities;


//TODO: implementar exceções


public class Usuario {
    private String Id;
    private String nome;
    private String login;
    private String senha;

    public Usuario(String id, String nome, String login, String senha) {
        setId(id);
        setNome(nome);
        setLogin(login);
        setSenha(senha);
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

    public String getId() {
        return this.Id;
    }

    public void setId(String id) {
        if (id != null && id != "") this.Id = id;
    }

    //a serem consertados depois
    public boolean logar(String login, String senha, Usuario usuario) {
		if (login.equals(usuario.login) && senha.equals(usuario.senha))
			return true;
		else
			return false;
	}

}
