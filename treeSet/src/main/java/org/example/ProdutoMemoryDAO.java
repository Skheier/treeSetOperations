package org.example;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class ProdutoMemoryDAO implements ProdutoDAO {
    private Collection<Produto> produtos = new TreeSet<>();

    @Override
    public void insere(Produto produto) {
        produtos.add(produto);
    }

    public boolean deleta(Produto produto) {
        if (produtos.remove(produto)){
            return true;
        }else {
            return false;
        }
    }


    public Collection<Produto> lista(){
        return produtos.stream().toList();
    }

    public Produto iterate(Integer id) {

        Iterator<Produto> iterator = produtos.iterator();
        while(iterator.hasNext()) {
            Produto produto = iterator.next();
            if(produto.getId() == id)
                return produto;
        }
        return null;
    }
}
