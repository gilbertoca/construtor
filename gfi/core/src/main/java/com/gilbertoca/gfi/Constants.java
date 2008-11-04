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
    // ~ Static fields/initializers
    // =============================================
    /**
     * Constante usada para capturar, atraves de propriedades do sistema, o nível de log
     * a ser ajustado para o mecanismo de persistẽncia OrBroker.
     */
    public static final String ORBROKER_LOG_LEVEL = "log.level";
    /**
     * O nome do arquivo de propriedades para construção do DBCPDataSource
     */
    public static final String DBCPCONFIGFILE = "/dbcp.properties";
    /**
     * O nome do DataSource local
     */
    public static final String DBCPDATASOURCE = "dbcp";
    /**
     * O nome do DataSource remoto
     */
    public static final String DATASOURCE = "gfi";
    /**
     * O nome do Módulo gerenciado pelo ORM OrBroker. Podemos dividi-lo em mais
     * modulos, como: orbroker-financeiro.xml, orbroker-inventario.xml
     */
    public static final String ORBROKER_INVENTARIO = "/META-INF/orbroker-inventario.xml";
    public static final String ORBROKER_GERAL = "/META-INF/orbroker-geral.xml";    
    public static final String ORBROKER_SCHEMA = "gfi";
    /**
     * O identificador da UF utilizado pelo logradouro UF=Estado do Tocantins.
     */
    public static final String FAIXAUF_UFESG = "TO";
    /**
     * O identificador da tipoLogradouro utilizado pelo logradouro Rua=Rua.
     */
    public static final String TIPOLOGR_TIPOLOGRADOURO = "Rua";

    /**
     * O identificador da natureza da opera��o utilizado pela venda.
     * 5.101=Vendas de prod. pr�pr. ou terc. p/ o Est.
     */
    public static final String NATUREZAOPERACAO_VENDA = "5.101";
    /**
     * O identificador da conta caixa utilizado pela venda, em uma venda a
     * vista. 4=Vendas.
     */
    public static final String CAIXACONTA_VENDA = "4";
    /**
     * O identificador da conta caixa utilizado pela venda, em uma venda a prazo
     * com cheque-pre. 85=Vendas com Cheque-Pr�.
     */
    public static final String CAIXACONTA_VENDA_CHEQUE = "85";

    /**
     * O identificador da conta caixa utilizado pela Conta a Receber, em uma
     * venda a prazo 5=Recebimentos de Contas.
     */
    public static final String CAIXACONTA_CONTA_RECEBER = "5";

    /**
     * O identificador da conta caixa utilizado pela Conta a Pagar, em uma
     * compra a prazo 5=Pagamentos de Contas.
     */
    public static final String CAIXACONTA_CONTA_PAGAR = "68";

    /**
     * O identificador da cidade utilizado no componente Endere�o. 9902=Para�so
     * do Tocantins.
     */
    public static final String LOCALIDADE = "9902";


}