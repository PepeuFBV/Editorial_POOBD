package model.entities;

public class Usuario {

    private Long id;
    private String nome;
    private String login;
    private String senha;
    private String endereco;
    private String cpf;

    public Usuario(Long id, String nome, String login, String senha, String endereco, String cpf) { //para a criação de gerente
        setId(id);
        setNome(nome);
        setLogin(login);
        setSenha(senha);
        setEndereco(endereco);
        setCpf(cpf);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }
    
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        if (login != null && !login.isEmpty()) {
            this.login = login;
        }
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.isEmpty()) {
            this.senha = senha;
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        if (id != null && !id.isEmpty()) {
            this.id = id;
        }
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        if (Usuario.isInstanceof(Gerente)) {
            cpf = null;
        } else {
            if (cpf != null && !cpf.isEmpty()) {
                this.cpf = cpf;
            }
        }
    }
    
    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        if (Usuario.isInstanceof(Gerente)) {
            endereco = null;
        } else {
            if (endereco != null && !endereco.isEmpty()) {
                this.endereco = endereco;
            }
        }
    }


    /*
    public boolean logar(String login, String senha, Usuario usuario) {
        return login.equals(usuario.login) && senha.equals(usuario.senha);
    }
    */
}
