package com.gilbertoca.gfi.inventario.service;

import com.gilbertoca.gfi.inventario.model.UnidadeMedida;
import com.gilbertoca.gfi.service.IService;

public interface IUnidadeMedidaService extends IService<UnidadeMedida, String> {
    /**
     * Busca por chave primária.
     * @param primaryKey
     * @return a instância da entidade encontrada ou null se a entidade não existe
     * @throws IllegalArgumentException se o primeiro argumento é nulo
     */
    public UnidadeMedida find(String primaryKey);
    
}
