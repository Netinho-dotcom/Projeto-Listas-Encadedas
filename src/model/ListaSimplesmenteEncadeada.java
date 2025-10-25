package model;

public class ListaSimplesmenteEncadeada {

    private class No {
        int valor;
        No proximo;

        public No(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    private No inicio;
    private int tamanho;

    public ListaSimplesmenteEncadeada() {
        this.inicio = null;
        this.tamanho = 0;
    }

    // CREATE - Inserir elemento
    public void inserir(int valor) {
        No novoNo = new No(valor);

        if (inicio == null) {
            inicio = novoNo;
        } else {
            No atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
        tamanho++;
    }

    // READ - Buscar elemento por valor
    public boolean buscar(int valor) {
        No atual = inicio;

        while (atual != null) {
            if (atual.valor == valor) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;

    }

    // UPDATE - Atualizar elemento (substitui primeira ocorrência do valor antigo pelo novo)
    public boolean atualizar(int valorAntigo, int valorNovo) {
        No atual = inicio;

        while (atual != null) {
            if (atual.valor == valorAntigo) {
                atual.valor = valorNovo;
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    // DELETE - Remover elemento por valor
    public boolean remover(int valor) {
        if (inicio == null) {
            return false;
        }

        if (inicio.valor == valor) {
            inicio= inicio.proximo;
            tamanho--;
            return true;
        }

        No atual = inicio;
        while (atual.proximo != null) {
            if (atual.proximo.valor == valor) {
                atual.proximo = atual.proximo.proximo;
                tamanho--;
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    // Métodos auxiliares
    public boolean estaVazia() {
        return inicio == null;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void limpar() {
        inicio = null;
        tamanho = 0;
    }

    public String toString() {
        if (inicio == null) {
            return "Lista vazia";
        }

        String resultado = "";
        No atual = inicio;

        while (atual != null) {
            resultado += atual.valor;
            if (atual.proximo != null) {
                resultado += " -> ";
            }
            atual = atual.proximo;
        }
        return resultado;
    }
}

