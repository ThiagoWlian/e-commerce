package com.thiagowlian.MSPRODUTO.messageBroker;

public class FilasMensageria {

    public final static String VENDA_REALIZADA_PRODUTO_REDUZIR_ESTOQUE_EXCHANGE = "produtos.v1.venda-realizada.reduzir-estoque";
    public final static String VENDA_REALIZADA_EXCHANGE = "venda.v1.venda-realizada";
    public final static String REALIZAR_VENDA_FEEDBACK_ERRO_TRANSACTION = "produtos.v1.realizar-venda.transaction.erro";
    public final static String VENDA_FEEDBACK_EXCHANGE = "venda.v1.venda-feedback";
    public final static String VENDA_FEEDBACK_ERRO_ROUTING_KEY = "venda.v1.venda-transaction.erro";
    public final static String VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE = "vendas.v1.realizar-vendas";
    public final static String VENDA_REALIZADA_PRODUDO_REDUCAO_ESTOQUE_EXCHANGE = "produto.v1.venda-realizada.estoque-reduzido";
    public final static String VENDA_REALIZADA_PRODUDO_REDUCAO_ESTOQUE_ROUTING_KEY = "nota-fiscal.v1.venda-realizada.estoque-reduzido";
    public final static String PRODUDO_UPDATE_EXCHANGE = "produto.v1.update";
    public final static String PRODUDO_NOVO_EXCHANGE = "produto.v1.novo";
    public final static String PRODUTO_NOVO_QUERY_TABLE_QUEUE = "produto.v1.novo.querytable";
    public final static String PRODUTO_UPDATE_QUERY_TABLE_QUEUE = "produto.v1.update.querytable";
    public final static String NULL_ROUNTING_KEY = "";
}