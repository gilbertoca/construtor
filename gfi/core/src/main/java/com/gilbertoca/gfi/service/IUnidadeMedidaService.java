package com.gilbertoca.gfi.service;



import com.gilbertoca.gfi.inventario2.model.UnidadeMedida;

public interface IUnidadeMedidaService extends IService<UnidadeMedida, Integer> {
    /**
     * Busca por chave primária.
     * @param primaryKey
     * @return a instância da entidade encontrada ou null se a entidade não existe
     * @throws IllegalArgumentException se o primeiro argumento é nulo
     */
    public UnidadeMedida find(String likeColumn, String columnParameter);
    
}
