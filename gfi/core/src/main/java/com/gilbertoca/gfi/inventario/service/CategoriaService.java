/**
 * 
 */
package com.gilbertoca.gfi.inventario.service;


import com.gilbertoca.gfi.inventario.model.Categoria;
import com.gilbertoca.gfi.service.BaseService;

/**
 * @author gilberto
 *
 */
public class CategoriaService extends BaseService<Categoria, Integer> implements ICategoriaService{

	/* (non-Javadoc)
	 * @see com.gilbertoca.gfi.service.BaseService#BaseService(Class<T> classEntity)
	 */
	public CategoriaService(Class<Categoria> classEntity) {
		super(classEntity);
	}
	/* (non-Javadoc)
	 * @see com.gilbertoca.gfi.inventario.service.ICategoriaService#find(java.lang.Integer)
	 */
	public Categoria find(Integer primaryKey) {
    	log.debug("Realizando consulta por entidade. Identificador usado como parâmetro: {} ",primaryKey);

    	if (primaryKey == null) {
            throw new IllegalArgumentException(
                    "primaryKey não pode ser nulo!");
        }
    	Categoria c = new Categoria(primaryKey);
    	return (find(c)? c:null);
	}

}
