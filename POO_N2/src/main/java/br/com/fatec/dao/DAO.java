/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.fatec.dao;

import java.sql.SQLException;
import java.util.Collection;
import javafx.print.Collation;

/**
 *
 * @author Vitor Aurélio Saccone Mimaki
 */
public interface DAO <MODEL> {
    
    public boolean insere(MODEL obj) throws SQLException;
    public boolean remove(MODEL obj) throws SQLException;
    public boolean altera(MODEL obj) throws SQLException;
    public MODEL buscaID(MODEL obj) throws SQLException;
    public Collection<MODEL> lista(String criterio) throws SQLException;
}