package com.thiagowlian.MSPRODUTO.messageBroker.listener;

import com.thiagowlian.MSPRODUTO.dto.*;
import com.thiagowlian.MSPRODUTO.exception.InvalidProductsVendaException;
import com.thiagowlian.MSPRODUTO.messageBroker.producer.VendaSagaTransactionProducer;
import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import com.thiagowlian.MSPRODUTO.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.VENDA_REALIZADA_PRODUTO_REDUZIR_ESTOQUE_EXCHANGE;

@Slf4j
@Component
public class EstoqueListener {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private VendaSagaTransactionProducer producerVendaFeedback;

    @RabbitListener(queues = VENDA_REALIZADA_PRODUTO_REDUZIR_ESTOQUE_EXCHANGE)
    public void onVendaCreated(ReducaoEstoqueWithListProductsDto reducaoEstoqueWithListProductsDto) {
        try {
            List<ProdutoModel> produtos = produtoService.buscarProdutoPorListaCodigoBarra(reducaoEstoqueWithListProductsDto.produtosCodigoBarra());
            if (produtoService.reducaoIsValid(reducaoEstoqueWithListProductsDto.produtosCodigoBarra(), produtos)) {
                produtoService.reduzirEstoqueProdutoEmUm(produtos);
                producerVendaFeedback.producerReducaoEstoque(
                        createMensageReducao(
                                reducaoEstoqueWithListProductsDto.vendaId(),
                                produtos));
            }
            throw new InvalidProductsVendaException();

        } catch (Exception ex) {
            producerVendaFeedback.producerVendaFeedbackError(
                    new VendaFeedbackErrorDto(reducaoEstoqueWithListProductsDto.vendaId(),
                            reducaoEstoqueWithListProductsDto.produtosCodigoBarra()));
        }
    }

    public ReducaoEstoqueTransactionEventDto createMensageReducao(long vendaId, List<ProdutoModel> produtos) {
        List<ProdutoNfDto> produtoNfDtos = produtos.stream().map(ProdutoNfDto::new).toList();
        return new ReducaoEstoqueTransactionEventDto(vendaId, produtoNfDtos);
    }
}
