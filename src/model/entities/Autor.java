package model.entities;

public class Autor extends Usuario {
	private String endereco;
	private String cpf;
	private Obra[] obras;

	public Autor() { }

	public Autor(String nome, String endereco, String cpf, Obra[] obras, String login, String senha) {
		super(nome,login,senha);
		setEndereco(endereco);
		setCpf(cpf);
		setObras(obras);
	}

	public Autor(String nome, String endereco, String cpf) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
	}
	
	public String getEndereco() {
		return this.endereco;
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

	public Obra[] getObras() {
		return this.obras;
	}

	public void setObras(Obra[] obras) {
		if (obras != null) {
			this.obras = obras;	
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

}
