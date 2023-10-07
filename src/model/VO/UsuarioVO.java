package model.VO;

public class UsuarioVO {

    private Long IDUsuario;
    private String nome;
    private String endereco;
    private String cpf;
    private String email;
    private String senha;
    private String tipo;

    public UsuarioVO(Long iDUsuario, String nome, String endereco, String cpf, String email, String senha, String tipo) {
        setIDUsuario(iDUsuario);
        setNome(nome);
        setEndereco(endereco);
        setCpf(cpf);
        setEmail(email);
        setSenha(senha);
        setTipo(tipo);
    }
    
    public UsuarioVO() {
    }

    public Long getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(Long iDUsuario) {
        if (iDUsuario > 0) {
            this.IDUsuario = iDUsuario;
        } else {
            throw new IllegalArgumentException("ID de usuário deve ser maior que zero.");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo != null && !tipo.isEmpty()) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("Tipo não pode ser nulo ou vazio.");
        }
    }
}
