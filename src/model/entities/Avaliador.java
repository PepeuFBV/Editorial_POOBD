package model.entities;

public class Avaliador extends Usuario {
	private String endereco;
	private String cpf;

	public Avaliador() { }
	
	public Avaliador(String nome, String endereco, String cpf, String login, String senha) {
		super(nome,login,senha);
		setEndereco(endereco);
		setCpf(cpf);
	}

	public Avaliador(String nome, String endereco, String cpf) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
	}

	public String getEndereco() {
		return ehis.endereco;
	}

	public void setEndereco(String endereco) {
		if (endereco != null && endereco != "") {
			this.endereco = endereco;
		}
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		if (cpf != null && cpf != "") {
			this.cpf = cpf;
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

}
