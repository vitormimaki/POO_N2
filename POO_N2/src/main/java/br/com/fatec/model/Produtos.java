/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author Aluno
 */
public class Produtos {
    private int codProd;
    private Fornecedor fornecedor;
    private String descricao;
    private char tipo;
    private float qtde;
    private float vlrUnit;

    public Produtos(int codProd, String descricao, char tipo, float qtde, float vlrUnit) {
        this.codProd = codProd;
        this.descricao = descricao;
        this.tipo = tipo;
        this.qtde = qtde;
        this.vlrUnit = vlrUnit;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public float getQtde() {
        return qtde;
    }

    public void setQtde(float qtde) {
        this.qtde = qtde;
    }

    public float getVlrUnit() {
        return vlrUnit;
    }

    public void setVlrUnit(float vlrUnit) {
        this.vlrUnit = vlrUnit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.codProd;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produtos other = (Produtos) obj;
        return this.codProd == other.codProd;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

}
