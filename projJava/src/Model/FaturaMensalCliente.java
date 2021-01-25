package Model;

import java.util.*;

public class FaturaMensalCliente {
    int comprasMensais;
    private double gasto;
    private Map<String,FaturaMensalProduto> produtos;

    public FaturaMensalCliente(){
        this.comprasMensais= 0;
        this.gasto = 0.0;
        this.produtos = new HashMap<>();
    }
    public FaturaMensalCliente(int vendas, int qN, int iN, int qP, int iP, Map<String, FaturaMensalProduto> c){
        setComprasMensais(vendas);
        setGasto(iP);
        setProducts(c);
    }
    public FaturaMensalCliente(FaturaMensalCliente fm){
        this.comprasMensais = fm.getComprasMensais();
        this.gasto= fm.getGasto();
        this.produtos = fm.getProducts();
    }

    public int getComprasMensais() {
        return this.comprasMensais;
    }
    public void setComprasMensais(int vendasMensais) {
        this.comprasMensais = vendasMensais;
    }

    public void setGasto(double iP){
        this.gasto = iP;
    }
    public double getGasto(){
        return this.gasto;
    }

    public void setProducts(Map<String,FaturaMensalProduto> produtos){
        this.produtos = new HashMap<>();
        for(Map.Entry<String,FaturaMensalProduto> mapentry : produtos.entrySet()){
            this.produtos.put(mapentry.getKey(),mapentry.getValue().clone());
        }
    }
    public Map<String,FaturaMensalProduto> getProducts(){
        Map<String,FaturaMensalProduto> newM = new HashMap<>();
        for(Map.Entry<String,FaturaMensalProduto> mapentry : this.produtos.entrySet()){
            newM.put(mapentry.getKey(),mapentry.getValue().clone());
        }
        return newM;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Quantidade de compras : ").append(this.comprasMensais).append("\n");
        sb.append("Valor gasto em compras: ").append(this.gasto).append("\n");
        sb.append("Produtos: ").append(this.produtos.toString()).append("\n");
        return sb.toString();
    }
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        FaturaMensalCliente o = (FaturaMensalCliente) obj;
        return  (o.getGasto() == this.gasto) &&
                (o.getComprasMensais() == this.comprasMensais) &&
                (o.getProducts().equals(this.produtos));
    }
    public FaturaMensalCliente clone(){
        return new FaturaMensalCliente(this);
    }

    /** Adiciona os conteúdos da Venda v à FaturaMensalCliente.
     *
     * @param v - Venda que vai ser adicionada à FaturaMensalCliente
     */
    public void addtoFaturaMensalCliente(Venda v){
        this.gasto += v.getPreco();
        String key = v.getProductCode();
        this.produtos.putIfAbsent(key, new FaturaMensalProduto());
        this.produtos.get(v.getProductCode()).addFaturaMensalProduto(v);
        this.comprasMensais++;
    }
}