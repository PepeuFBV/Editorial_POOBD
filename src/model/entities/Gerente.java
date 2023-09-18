package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends User {

    private static int contador = 1;

    public Gerente(String nome, String login, String senha) {
        super(contador, nome, login, senha);
    }
    
}
