# 🧩 Projeto de Listas Encadeadas em Java

Este projeto tem como objetivo **implementar e demonstrar o funcionamento das principais estruturas de listas encadeadas** em **Java**, com uma interface gráfica que permite ao usuário realizar operações de forma interativa.

O sistema foi desenvolvido como um estudo prático de **estruturas de dados**, abordando **listas simplesmente encadeadas, duplamente encadeadas e circulares**, todas com suas respectivas operações de **CRUD (Create, Read, Update e Delete)**.

---

## 🧠 Estrutura do Projeto

```
listas-encadeadas/
│
├── model/
│   ├── ListaSimplesmenteEncadeada/
│   ├── ListaDuplamenteEncadeada/
│   └── ListaEncadeadaCircular/
│
└── view/
    └── GerenciadorListas/
```

- **model/** → Contém as classes responsáveis pela lógica das listas encadeadas.  
  - **ListaSimplesmenteEncadeada**: implementação básica, com nós conectados em sequência.  
  - **ListaDuplamenteEncadeada**: permite navegação nos dois sentidos (anterior e próximo).  
  - **ListaEncadeadaCircular**: o último nó aponta novamente para o primeiro, formando um ciclo.  
- **view/** → Contém a interface gráfica criada com **Java Swing**, permitindo ao usuário:  
  - Inserir novos elementos;  
  - Exibir os elementos da lista;  
  - Atualizar valores existentes;  
  - Remover elementos.  

---

## 🖥️ Interface Gráfica

A interface foi construída utilizando **Java Swing**, permitindo:  
- Selecionar o tipo de lista a ser manipulada;  
- Executar operações de CRUD de forma visual;  
- Exibir o estado atual da lista após cada operação.  

---

## ⚙️ Tecnologias Utilizadas

- **Linguagem:** Java  
- **Biblioteca gráfica:** Swing  
- **Paradigma:** Programação orientada a objetos  

---

## 🧩 Funcionalidades Principais

| Tipo de Lista | Operações Disponíveis |
|----------------|------------------------|
| Simplesmente Encadeada | Inserir, Listar, Atualizar, Remover |
| Duplamente Encadeada | Inserir, Listar, Atualizar, Remover |
| Circular | Inserir, Listar, Atualizar, Remover |

---

## 📦 Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Netinho-dotcom/Projeto-Listas-Encadedas.git
   ```

2. **Abra o projeto em sua IDE Java** (Ex: IntelliJ, Eclipse ou VSCode com extensão Java).  

3. **Compile e execute o arquivo principal** da interface:  
   ```
   view/GerenciadorListas.java
   ```

4. **Interaja com as listas** através da interface gráfica.  

---

## 🎯 Objetivo de Aprendizado

Este projeto foi desenvolvido com foco em:  
- Compreender o funcionamento interno de estruturas encadeadas;  
- Praticar o uso de referências e nós em Java;  
- Aplicar conceitos de **encapsulamento** e **reutilização de código**;  
- Criar uma interface gráfica simples para manipular estruturas de dados.  

---

## 👨‍💻 Autor

**José Alves**  
📧 [GitHub - Netinho-dotcom](https://github.com/Netinho-dotcom)

Vídeo explicativo - https://youtu.be/xOmTEa9afME
