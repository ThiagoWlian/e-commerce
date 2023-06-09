package com.thiagowlian.MSPRODUTO.dto;

import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import com.thiagowlian.MSPRODUTO.model.ProdutoTipo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ProdutoDto extends RepresentationModel<ProdutoDto> {

    private String codigoBarras;
    private String nome;
    private ProdutoTipo produtoTipo;
    private double valor;
    private long estoque;

    public ProdutoDto(ProdutoModel produto) {
        this.codigoBarras = produto.getCodigoBarras();
        this.nome = produto.getNome();
        this.produtoTipo = produto.getProdutoTipo();
        this.valor = produto.getValor();
        this.estoque = produto.getEstoque();
    }
}
