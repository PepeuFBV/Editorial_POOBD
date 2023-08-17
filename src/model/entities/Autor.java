package model.entities;

public class Autor {
	private String nome;
	private String endereco;
	private String cpf;
	private Obra[] obras;
	private String login;
	private String senha;

	public Autor() { }

	public Autor(String nome, String endereco, String cpf, Obra[] obras, String login, String senha) {
		setNome(nome);
		setEndereco(endereco);
		setCpf(cpf);
		setObras(obras);
		setLogin(login);
		setSenha(senha);
	}

	public Autor(String nome, String endereco, String cpf) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null && nome != "") {
			this.nome = nome;
		}
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		if (endereco != null && endereco != "") {
			this.endereco = endereco;
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf != null && cpf != "") {
			this.cpf = cpf;
		}
	}

	public Obra[] getObras() {
		return obras;
	}

	public void setObras(Obra[] obras) {
		if (obras != null) {
			this.obras = obras;	
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (login != null && login != "") {
			this.login = login;
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if (senha != null && senha != "") {
			this.senha = senha;
		}
	}

	//alterar metódos depois
	public void cadastrar(Autor autor) {
		System.out.printf(autor.getNome() + " está cadastrado!\n");
	}
	
	public void alterar(Autor autor) {
		System.out.printf(autor.getNome() + " foi alterado!\n");
	}
	
	public void excluir(Autor autor) {
		System.out.printf(autor.getNome() + " foi excluido!\n");
	}
	
	public Autor[] buscarPorNome(Autor autor) {
		System.out.printf("Autor encontrado!\n");
		return;
	}

	public Autor[] buscarPorObra(Obra obra) {
		System.out.printf("Autor encontrado!\n");
		return;
	}

	public boolean logar(String login, String senha, Autor autor) {
		if (login.equals(autor.login) && senha.equals(autor.senha))
			return true;
		else
			return false;
	}
}
