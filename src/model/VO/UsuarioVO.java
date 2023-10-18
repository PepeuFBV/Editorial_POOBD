package model.VO;

public class UsuarioVO {

    private Long IDUsuario;
    private String nome;
    private String endereco;
    private String cpf;
    private String email;
    private String senha;
    private String tipo;

    public UsuarioVO(Long iDUsuario, String nome, String endereco, String cpf, String email, String senha,
            String tipo) {
        setIDUsuario(iDUsuario);
        setNome(nome);
        setTipo(tipo);
        setEndereco(endereco);
        setCpf(cpf);
        setEmail(email);
        setSenha(senha);
    }
    
    public UsuarioVO(String nome, String endereco, String cpf, String email, String senha, String tipo) {
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
        if (cpf != null) {
            //remove todos os caracteres não numéricos da entrada do usuário.
            String cpfNumerico = cpf.replaceAll("\\D", "");

            if (cpfNumerico.length() == 11) {
                this.cpf = cpfNumerico;
            } else {
                throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos numéricos.");
            }
        } else {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.matches("^[A-Za-z0-9+_.-]+@gmail\\.com$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("E-mail não é válido.");
        }
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{8,}$"; 
        // deve possuir no mínimo: 1 letra maiuscula, 1 letra minuscula, 1 digito, 1 caractere especial e 8 dígitos
        
        if (senha != null && senha.matches(regex)) {
            this.senha = senha;
        } else {
            throw new IllegalArgumentException("Senha não atende aos critérios de segurança.");
        }
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo != null && !tipo.isEmpty()) {
            //tipo = tipo.trim().toLowerCase(); //TODO apagar

            if (tipo.equals("Gerente") || tipo.equals("Autor") || tipo.equals("Avaliador")) {
                this.tipo = tipo;
            } else {
                throw new IllegalArgumentException("Tipo deve ser 'Gerente', 'Autor' ou 'Avaliador'");
            }
        } else {
            throw new IllegalArgumentException("Tipo não pode ser nulo ou vazio");
        }
    }

}
