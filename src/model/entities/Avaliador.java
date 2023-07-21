package model.entities;

public class Avaliador implements Identify {

	private int id;
	private String name;
	private String address;
	private String cpf;
	
	public Avaliador () {
	}
	
	public Avaliador(int id, String name, String address, String cpf) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.cpf = cpf;
	}
	
	@Override
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
