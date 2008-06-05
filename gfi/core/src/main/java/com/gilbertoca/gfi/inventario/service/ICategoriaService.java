package com.gilbertoca.gfi.inventario.service;

import com.gilbertoca.gfi.inventario.model.Categoria;
import com.gilbertoca.gfi.service.IService;

public interface ICategoriaService extends IService<Categoria, Integer> {
    /**
     * Busca por chave primária.
     * @param primaryKey
     * @return a instância da entidade encontrada ou null se a entidade não existe
     * @throws IllegalArgumentException se o primeiro argumento é nulo
     */
    public Categoria find(Integer primaryKey);
}
