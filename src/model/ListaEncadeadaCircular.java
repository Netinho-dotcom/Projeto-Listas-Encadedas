package model;

public class ListaEncadeadaCircular {

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

    public ListaEncadeadaCircular() {
        this.inicio = null;
        this.tamanho = 0;
    }

    // CREATE - Inserir elemento
    public void inserir(int valor) {
        No novoNo = new No(valor);

        if (inicio == null) {
            inicio = novoNo;
            novoNo.proximo = inicio;
        } else {
            No ultimo = inicio;
            while (ultimo.proximo != inicio) {
                ultimo = ultimo.proximo;
            }
            ultimo.proximo = novoNo;
            novoNo.proximo = inicio;
        }
        tamanho++;
    }

    // READ - Buscar elemento por valor
    public boolean buscar(int valor) {
        if (inicio == null) {
            return false;
        }

        No atual = inicio;
        do {
            if (atual.valor == valor) {
                return true;
            }
            atual = atual.proximo;
        } while (atual != inicio);

        return false;
    }

    // UPDATE - Atualizar elemento (substitui primeira ocorrência do valor antigo pelo novo)
    public boolean atualizar(int valorAntigo, int valorNovo) {
        if (inicio == null) {
            return false;
        }

        No atual = inicio;
        do {
            if (atual.valor == valorAntigo) {
                atual.valor = valorNovo;
                return true;
            }
            atual = atual.proximo;
        } while (atual != inicio);

        return false;
    }

    // DELETE - Remover elemento por valor
    public boolean remover(int valor) {
        if (inicio == null) {
            return false;
        }

        // Caso especial: único elemento
        if (inicio.proximo == inicio && inicio.valor == valor) {
            inicio = null;
            tamanho--;
            return true;
        }

        // Caso especial: remover início
        if (inicio.valor == valor) {
            No ultimo = inicio;
            while (ultimo.proximo != inicio) {
                ultimo = ultimo.proximo;
            }
            inicio = inicio.proximo;
            ultimo.proximo = inicio;
            tamanho--;
            return true;
        }

        // Remover do meio ou fim
        No atual = inicio;
        do {
            if (atual.proximo.valor == valor) {
                atual.proximo = atual.proximo.proximo;
                tamanho--;
                return true;
            }
            atual = atual.proximo;
        } while (atual != inicio);

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

        do {
            resultado += atual.valor;
            atual = atual.proximo;
            if (atual != inicio) {
                resultado += " -> ";
            }
        } while (atual != inicio);

        resultado += " -> [circular]";
        return resultado;
    }
}

