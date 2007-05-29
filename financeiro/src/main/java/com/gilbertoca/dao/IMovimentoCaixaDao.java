package com.gilbertoca.dao;

import java.util.Collection;
import java.util.Date;

import com.gilbertoca.model.financeiro.MovimentoCaixa;

public interface IMovimentoCaixaDao {

    /**
     * Retrieves all of the movimentoCaixas
     */
    public Collection getMovimentoCaixas();
    
    /**
     * Retrieves all of the movimentoCaixas between 
     * @param dtMovimentoCaixaIni
     * @param dtMovimentoCaixaFim
     */
    
    public Collection findMovimentoCaixa(Date dtMovimentoCaixaIni, Date dtMovimentoCaixaFim);

    /**
     * Gets movimentoCaixa's information based on id. An 
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param cdMovimentoCaixa the movimentoCaixa's id
     * @return movimentoCaixa populated movimentoCaixa object
     */
    public MovimentoCaixa getMovimentoCaixa(Integer cdMovimentoCaixa);

    /**
     * Saves a new movimentoCaixa's information
     * @param movimentoCaixa the object to be saved
     * @return MovimentoCaixa the persisted movimentoCaixa object
     */	
    public void insertMovimentoCaixa(MovimentoCaixa movimentoCaixa);
    
    /**
     * Saves a movimentoCaixa's information
     * @param movimentoCaixa the object to be saved
     * @return MovimentoCaixa the persisted movimentoCaixa object
     */	
    public void updateMovimentoCaixa(MovimentoCaixa movimentoCaixa);


	/**
     * Removes a movimentoCaixa from the database by id
     * @param cdMovimentoCaixa the movimentoCaixa's id
     */
    public void deleteMovimentoCaixa(Integer cdMovimentoCaixa);

	/**
     * Removes a movimentoCaixa from the database by movimentoCaixa object
     * @param movimentoCaixa the movimentoCaixa's object
     */
    public void deleteMovimentoCaixa(MovimentoCaixa movimentoCaixa);
    
    /**
     * @return MovimentoCaixa Retorna um objeto MovimentoCaixa, com o atributo flFechado igual a false.
     */
    public MovimentoCaixa getMovimentoCaixaAberto();

    /**
     * @return
     */
    public boolean isThereAnyMovimentoCaixaAberto();

}

