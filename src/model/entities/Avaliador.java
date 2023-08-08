package model.entities;

public class Avaliador {
	private String nome;
	private String endereco;
	private String cpf;
	private String login;
	private String senha;

	public Avaliador() { }
	
	public Avaliador(String nome, String endereco, String cpf, String login, String senha) {
		setNome(nome);
		setEndereco(endereco);
		setCpf(cpf);
		setLogin(login);
		setSenha(senha);
	}

	public Avaliador(String nome, String endereco, String cpf) {
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
	public void cadastrar(Avaliador avaliador) {
		System.out.printf(avaliador.getNome() + " está cadastrado!\n");
	}

	public void alterar(Avaliador avaliador) {
		System.out.printf(avaliador.getNome() + " está alterado!\n");
	}

	public void excluir(Avaliador avaliador) {
		System.out.printf(avaliador.getNome() + " foi excluido!\n");
	}

	public Avaliador[] buscarPorObra(Obra obra) {
		System.out.printf("Avaliador encontrado!\n");
		return;
	}

	public boolean logar(String login, String senha, Avaliador avaliador) {
		if (login.equals(avaliador.login) && senha.equals(avaliador.senha))
			return true;
		else
			return false;
	}
}
