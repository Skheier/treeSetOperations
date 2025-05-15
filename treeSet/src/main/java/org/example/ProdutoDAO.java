package org.example;

import java.util.Collection;

public interface ProdutoDAO {

    void insere(Produto produto);

    Collection<Produto> lista();

    boolean deleta(Produto produto);

    Produto iterate(Integer id);
}
