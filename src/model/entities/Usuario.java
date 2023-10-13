package model.entities;

public abstract class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
    }

    public Usuario() { }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.isEmpty() && senha.length() >= 6) { //talvez fazer requesitos de senha em exception
            this.senha = senha;
        }
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }
    
}
