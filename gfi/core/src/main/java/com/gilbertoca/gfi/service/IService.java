/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import java.io.Serializable;
import java.util.Collection;

import net.sourceforge.orbroker.Broker;

/**
 * Interface geral para uso com o mescanimo de persistência OrBroker. Estabelece
 * assinaturas de métodos essenciais para operações de acesso a banco de dados.
 * 
 * @author Gilberto Caetano de Andrade
 */
public interface IService<T, PK extends Serializable> {
    /**
     * Expõe o mecanismo de persistência. .
     */
    public Broker getBroker();

    public Class<T> getClassEntity();

    /**
     * Método genérico usado para obter todas as entidades de uma classe
     * particular. Essa classe particular é definida no construtor da
     * implementação desta interface.
     * 
     * @return Collection de entidades preenchidas
     */
    public Collection<T> getAll();

    /**
     * Método genérico para obter uma entidade.
     * 
     * @param entity
     *                objeto ao qual se aplica o retorno da pesquisa.
     * @return true e o preenchimento da entidade passada como parâmetro. false
     *         caso contrário.
     */
    public boolean find(T entity);

    /**
     * Método genérico para inserção de uma nova entidade.
     * 
     * @param entity
     *                entidade a ser inserida.
     */
    public void insert(T entity);

    /**
     * Método genérico para atualização de uma entidade.
     * 
     * @param entity
     *                entidade a ser alterada.
     */
    public void update(T entity);

    /**
     * Método genérico para deleção de uma entidade.
     * 
     * @param entity
     *                entidade a ser deletada.
     */
    public void delete(T entity);

    /**
     * Método genérico para consulta nomeada preexistente.
     * 
     * @param queryName
     *                nome da consulta.
     */
    public Collection<T> findByNamedQuery(String queryName);

    /**
     * Método genérico para personalizada com a cláusula SQL LIKE.
     * 
     * @param likeColumn
     *                campo a ser usado na cláusula SQL.
     * @param likeValue
     *                valor para pesquisa.
     */
    public Collection<T> findLike(String likeColumn, String likeValue);
}