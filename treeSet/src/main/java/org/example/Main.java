package org.example;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Scanner;

public class Main {

    private static Scanner s = new Scanner(System.in);
    private static ProdutoDAO dao = new ProdutoMemoryDAO();

    public static void main(String[] args) {
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("Cadastro de produtos");
            System.out.println(" 1 - Inserir");
            System.out.println(" 2 - Listar");
            System.out.println(" 3 - Alterar");
            System.out.println(" 4 - Excluir");
            System.out.println(" 5 - Pesquisar");
            System.out.println(" 6 - Sair");

            System.out.print("Digite a opção desejada: ");
            opcao = s.nextInt();
            switch (opcao) {
                case 1 -> insere();
                case 2 -> listar();
                case 3 -> editar();
                case 4 -> deletar();
                case 5 -> pesquisar();
                case 6 -> System.out.println("Desligando...");
                default -> System.out.println("Opcao invalida!\n");
            }
        }
        s.close();
    }

    private static void insere() {
        System.out.print("Digite o id: ");
        Integer id = s.nextInt();

        System.out.print("Digite o produto: ");
        String produto = s.next();

        System.out.print("Digite o preco: ");
        String preco = s.next();

        Produto newProd = new Produto(id, produto, new BigDecimal(preco));
        dao.insere(newProd);
        System.out.println(newProd.getProduto()+ " adicionado com sucesso!\n");
    }

    private static void editar() {
        dao.lista();

        System.out.print("Digite o id do produto que deseja editar: ");
        Integer id = s.nextInt();

        Produto deleProd = new Produto(id, null, null);
        if(!dao.deleta(deleProd) ){ // já checa e deleta
            System.out.println("Produto com o id "+id+" não encontrado.\n");
            return;
        }

        System.out.print("Digite o nome do produto: ");
        String produto = s.next();

        System.out.print("Digite o preco do produto: ");
        String preco = s.next();

        Produto updatedProd = new Produto(id, produto, new BigDecimal(preco));
        dao.insere(updatedProd);
        System.out.println(updatedProd.getProduto()+ " alterado com sucesso!\n");
    }

    private static void deletar() {
        dao.lista();

        System.out.print("Digite o id: ");
        Integer id = s.nextInt();

        Produto deleProd = new Produto(id, null, null);

        if( dao.deleta(deleProd) ){
            System.out.println("Produto com o id "+id+" deletado...\n");
        } else {
            System.out.println("Produto com o id "+id+" não encontrado.\n");
        }
    }

    private static void listar(){
        Collection<Produto> values = dao.lista();

        for(Produto p: values) {
            System.out.println(p);
        }
        System.out.println();
    }

    private static void pesquisar(){
        System.out.print("Digite o id do produto: ");
        Integer id = s.nextInt();

        if(dao.iterate(id) == null){
            System.out.println("Produto com o id "+id+" não encontrado.\n");
            return;
        }

        Produto returnedProd = dao.iterate(id);
        System.out.println("Produto encontrado: "+returnedProd+"\n");
    }
}