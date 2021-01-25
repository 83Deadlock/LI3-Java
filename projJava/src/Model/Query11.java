package Model;

import java.util.Objects;

public class Query11 {
    private String filename;
    private int vendas_erradas;
    private int qtd_produtos;
    private int qtd_produtos_comprados;
    private int qtd_nao_comprados;
    private int qtd_clientes;
    private int qtd_clientes_compraram;
    private int qtd_clientes_nao_compraram;
    private int compras_valor_0;
    private double faturacao_global;

    public Query11() {
        this.filename = "";
        this.qtd_produtos = 0;
        this.qtd_produtos_comprados = 0;
        this.qtd_nao_comprados = 0;
        this.qtd_clientes = 0;
        this.qtd_clientes_compraram = 0;
        this.qtd_clientes_nao_compraram = 0;
        this.compras_valor_0 = 0;
        this.faturacao_global = 0;
    }

    public Query11(String filename, int vendas_erradas, int qtd_produtos, int qtd_produtos_comprados, int qtd_nao_comprados, int qtd_clientes, int qtd_clientes_compraram, int qtd_clientes_nao_compraram, int compras_valor_0, double faturacao_global) {
        setFilename(filename);
        setVendas_erradas(vendas_erradas);
        setQtd_produtos(qtd_produtos);
        setQtd_produtos_comprados(qtd_produtos_comprados);
        setQtd_nao_comprados(qtd_nao_comprados);
        setQtd_clientes(qtd_clientes);
        setQtd_clientes_compraram(qtd_clientes_compraram);
        setQtd_clientes_nao_compraram(qtd_clientes_nao_compraram);
        setCompras_valor_0(compras_valor_0);
        setFaturacao_global(faturacao_global);
    }

    public Query11(Query11 q11){
        this.filename = q11.getFilename();
        this.vendas_erradas = q11.getVendas_erradas();
        this.qtd_produtos = q11.getQtd_produtos();
        this.qtd_produtos_comprados = q11.getQtd_produtos_comprados();
        this.qtd_nao_comprados = q11.getQtd_nao_comprados();
        this.qtd_clientes = q11.getQtd_clientes();
        this.qtd_clientes_compraram = q11.getQtd_clientes_compraram();
        this.qtd_clientes_nao_compraram = q11.getQtd_clientes_nao_compraram();
        this.compras_valor_0 = q11.getCompras_valor_0();
        this.faturacao_global = q11.getFaturacao_global();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getVendas_erradas() {
        return vendas_erradas;
    }

    public void setVendas_erradas(int vendas_erradas) {
        this.vendas_erradas = vendas_erradas;
    }

    public int getQtd_produtos() {
        return qtd_produtos;
    }

    public void setQtd_produtos(int qtd_produtos) {
        this.qtd_produtos = qtd_produtos;
    }

    public int getQtd_produtos_comprados() {
        return qtd_produtos_comprados;
    }

    public void setQtd_produtos_comprados(int qtd_produtos_comprados) {
        this.qtd_produtos_comprados = qtd_produtos_comprados;
    }

    public int getQtd_nao_comprados() {
        return qtd_nao_comprados;
    }

    public void setQtd_nao_comprados(int qtd_nao_comprados) {
        this.qtd_nao_comprados = qtd_nao_comprados;
    }

    public int getQtd_clientes() {
        return qtd_clientes;
    }

    public void setQtd_clientes(int qtd_clientes) {
        this.qtd_clientes = qtd_clientes;
    }

    public int getQtd_clientes_compraram() {
        return qtd_clientes_compraram;
    }

    public void setQtd_clientes_compraram(int qtd_clientes_compraram) {
        this.qtd_clientes_compraram = qtd_clientes_compraram;
    }

    public int getQtd_clientes_nao_compraram() {
        return qtd_clientes_nao_compraram;
    }

    public void setQtd_clientes_nao_compraram(int qtd_clientes_nao_compraram) {
        this.qtd_clientes_nao_compraram = qtd_clientes_nao_compraram;
    }

    public int getCompras_valor_0() {
        return compras_valor_0;
    }

    public void setCompras_valor_0(int compras_valor_0) {
        this.compras_valor_0 = compras_valor_0;
    }

    public double getFaturacao_global() {
        return faturacao_global;
    }

    public void setFaturacao_global(double faturacao_global) {
        this.faturacao_global = faturacao_global;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Query11 query11 = (Query11) obj;
        return this.vendas_erradas == query11.vendas_erradas &&
                this.qtd_produtos == query11.qtd_produtos &&
                this.qtd_nao_comprados == query11.qtd_nao_comprados &&
                this.qtd_clientes == query11.qtd_clientes &&
                this.qtd_clientes_compraram == query11.qtd_clientes_compraram &&
                this.compras_valor_0 == query11.compras_valor_0 &&
                query11.faturacao_global == this.faturacao_global &&
                this.filename.equals(query11.filename);
    }

    public Query11 clone(){
        return new Query11(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("| Nome do Ficheiro - ").append(this.filename).append("\n");
        sb.append("| Vendas erradas - ").append(this.vendas_erradas).append("\n");
        sb.append("| Total de produtos - ").append(this.qtd_produtos).append("\n");
        sb.append("| Produtos não comprados - ").append(this.qtd_nao_comprados).append("\n");
        sb.append("| Total de clientes - ").append(this.qtd_clientes).append("\n");
        sb.append("| Clientes que compraram - ").append(this.qtd_clientes_compraram).append("\n");
        sb.append("| Clientes que não compraram - ").append(this.qtd_clientes_nao_compraram).append("\n");
        sb.append("| Compras de valor 0.0 - ").append(this.compras_valor_0).append("\n");
        sb.append("| Faturação global - ").append(this.faturacao_global).append("\n");
        return sb.toString();
    }

}
