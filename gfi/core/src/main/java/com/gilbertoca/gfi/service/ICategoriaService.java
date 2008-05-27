package com.gilbertoca.gfi.service;

import java.io.Serializable;

import net.sourceforge.orbroker.Query;

import com.gilbertoca.gfi.inventario2.model.Categoria;

public interface ICategoriaService extends IService<Categoria, Integer> {
    /**
     * Busca por chave primária.
     * @param primaryKey
     * @return a instância da entidade encontrada ou null se a entidade não existe
     * @throws IllegalArgumentException se o primeiro argumento é nulo
     */
    public Categoria find(Integer primaryKey);
    /*
     * Possivel implementação
    public Categoria find(Integer primaryKey){
    	log.debug("Realizando consulta por entidade. Identificador usado como parâmetro: {} ",primaryKey);
    	if (primaryKey == null || primaryKey.equals("")) {
            throw new IllegalArgumentException(
                    "Identificador não pode ser nulo!");
        }
        Categoria c = new Categoria(primaryKey);
        if (qry.selectOne(c))
        	return c
        else
        	return null;
    }
     * 
     */
}
