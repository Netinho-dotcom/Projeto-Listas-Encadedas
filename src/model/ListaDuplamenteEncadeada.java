package model;

public class ListaDuplamenteEncadeada {

    private class No {
        int valor;
        No proximo;
        No anterior;

        public No(int valor) {
            this.valor = valor;
            this.proximo = null;
            this.anterior = null;
        }
    }

    private No inicio;
    private No cauda;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        this.inicio = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    // CREATE - Inserir elemento
    public void inserir(int valor) {
        No novoNo = new No(valor);

        if (cauda == null) {
            inicio = cauda = novoNo;
        } else {
            cauda.proximo = novoNo;
            novoNo.anterior = cauda;
            cauda = novoNo;
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
        No atual = inicio;

        while (atual != null) {
            if (atual.valor == valor) {
                if (atual == inicio && atual == cauda) {
                    // Único elemento
                    inicio = cauda = null;
                } else if (atual == inicio) {
                    // Primeiro elemento
                    inicio = inicio.proximo;
                    inicio.anterior = null;
                } else if (atual == cauda) {
                    // Último elemento
                    cauda = cauda.anterior;
                    cauda.proximo = null;
                } else {
                    // Elemento do meio
                    atual.anterior.proximo = atual.proximo;
                    atual.proximo.anterior = atual.anterior;
                }
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
        cauda = null;
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
                resultado += " <-> ";
            }
            atual = atual.proximo;
        }
        return resultado;
    }

    public String toStringReverso() {
        if (cauda == null) {
            return "Lista vazia";
        }

        String resultado = "";
        No atual = cauda;

        while (atual != null) {
            resultado += atual.valor;
            if (atual.anterior != null) {
                resultado += " <-> ";
            }
            atual = atual.anterior;
        }
        return resultado;
    }
}
