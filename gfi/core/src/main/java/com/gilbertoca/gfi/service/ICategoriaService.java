package com.gilbertoca.gfi.service;

import java.io.Serializable;

import net.sourceforge.orbroker.Query;

import com.gilbertoca.gfi.inventario.model.Categoria;

public interface ICategoriaService extends IService<Categoria, Integer> {
    /**
     * Busca por chave primária.
     * @param primaryKey
     * @return a instância da entidade encontrada ou null se a entidade não existe
     * @throws IllegalArgumentException se o primeiro argumento é nulo
     */
    public Categoria find(Integer primaryKey);
}
