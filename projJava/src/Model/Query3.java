package Model;

public class Query3 {
    private int quantidadeCompras;
    private int produtosComprados;
    private double gastoTotal;

    public Query3(){
        this.quantidadeCompras = 0;
        this.produtosComprados = 0;
        this.gastoTotal = 0.0;
    }

    public Query3(int quantidadeCompras, int produtosComprados, double gastoTotal) {
        setQuantidadeCompras(quantidadeCompras);
        setProdutosComprados(produtosComprados);
        setGastoTotal(gastoTotal);
    }

    public Query3(Query3 q3){
        this.quantidadeCompras = q3.getQuantidadeCompras();
        this.produtosComprados = q3.getProdutosComprados();
        this.gastoTotal = q3.getGastoTotal();
    }

    public int getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(int quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
    }

    public int getProdutosComprados() {
        return produtosComprados;
    }

    public void setProdutosComprados(int produtosComprados) {
        this.produtosComprados = produtosComprados;
    }

    public double getGastoTotal() {
        return gastoTotal;
    }

    public void setGastoTotal(double gastoTotal) {
        this.gastoTotal = gastoTotal;
    }

    public String toString() {
        return "Model.Query3{" +
                "quantidadeCompras=" + quantidadeCompras +
                ", produtosComprados=" + produtosComprados +
                ", gastoTotal=" + gastoTotal +
                '}';
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Query3 o = (Query3) obj;
        return ((this.produtosComprados == (o.getProdutosComprados())) &&
                (this.quantidadeCompras == (o.getQuantidadeCompras())) &&
                (this.gastoTotal == (o.getGastoTotal())));
    }

    public Query3 clone(Query3 q){
        return new Query3(q);
    }
}
