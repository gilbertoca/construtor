package com.gilbertoca.gfi;
/**
 * Constant values used throughout the application.
 *
 * <p>
 * <a href="Constants.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class Constants {
    //~ Static fields/initializers =============================================
	   /**
	    * O identificador da UF utilizado pelo logradouro
	    * UF=Estado do Tocantins.
	    */    
	  public static final String FAIXAUF_UFESG = "TO";
	   /**
	    * O identificador da tipoLogradouro utilizado pelo logradouro
	    * Rua=Rua.
	    */    
	  public static final String TIPOLOGR_TIPOLOGRADOURO = "Rua";	  

	/**
     * O identificador da natureza da operação utilizado pela venda.
     * 5.101=Vendas de prod. própr. ou terc. p/ o Est.
     */
    public static final String NATUREZAOPERACAO_VENDA = "5.101";
    /**
     * O identificador da conta caixa utilizado pela venda, em uma venda a vista.
     * 4=Vendas.
     */    
   public static final String CAIXACONTA_VENDA = "4";
    /**
     * O identificador da conta caixa utilizado pela venda, em uma venda a prazo com cheque-pre.
     * 85=Vendas com Cheque-Pré.
     */    
   public static final String CAIXACONTA_VENDA_CHEQUE = "85";   

   /**
    * O identificador da conta caixa utilizado pela Conta a Receber, em uma venda a prazo
    * 5=Recebimentos de Contas.
    */    
  public static final String CAIXACONTA_CONTA_RECEBER = "5";

  /**
   * O identificador da conta caixa utilizado pela Conta a Pagar, em uma compra a prazo
   * 5=Pagamentos de Contas.
   */    
 public static final String CAIXACONTA_CONTA_PAGAR = "68";
  
  /**
   * O identificador da cidade utilizado no componente Endereço.
   * 9902=Paraíso do Tocantins.
   */    
 public static final String LOCALIDADE = "9902";  
}