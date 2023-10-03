package VO;

public class UserVO {

    private int IDUsuario;
    private String Nome;
    private String endereco;
    private String cpf;
    private String email;
    private String senha;

    public UserVO(int iDUsuario, String nome, String endereco, String cpf, String email, String senha) {
        setIDUsuario(iDUsuario);
        setNome(nome);
        setEndereco(endereco);
        setCpf(cpf);
        setEmail(email);
        setSenha(senha);
    }
    
    public UserVO() {
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(int iDUsuario) {
        if (iDUsuario > 0) {
            IDUsuario = iDUsuario;
        } else {
            throw new IllegalArgumentException("ID de usuário deve ser maior que zero.");
        }
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            Nome = nome;
        } else {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco != null && !endereco.isEmpty()) {
            this.endereco = endereco;
        } else {
            throw new IllegalArgumentException("Endereço não pode ser nulo ou vazio.");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf != null && !cpf.isEmpty()) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("E-mail não pode ser nulo ou vazio.");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.isEmpty()) {
            this.senha = senha;
        } else {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia.");
        }
    }
}
