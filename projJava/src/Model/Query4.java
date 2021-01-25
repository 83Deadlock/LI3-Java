package Model;

public class Query4 {
    private int quantidadeCompras;
    private int clientesDistintos;
    private double receitaTotal;

    public Query4(){
        this.quantidadeCompras = 0;
        this.clientesDistintos = 0;
        this.receitaTotal = 0.0;
    }

    public Query4(int quantidadeCompras, int clientesDistintos, double receitaTotal) {
        setQuantidadeCompras(quantidadeCompras);
        setClientesDistintos(clientesDistintos);
        setReceitaTotal(receitaTotal);
    }

    public Query4(Query4 q4){
        this.quantidadeCompras = q4.getQuantidadeCompras();
        this.clientesDistintos = q4.getClientesDistintos();
        this.receitaTotal = q4.getReceitaTotal();
    }

    public int getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(int quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
    }

    public int getClientesDistintos() {
        return clientesDistintos;
    }

    public void setClientesDistintos(int clientesDistintos) {
        this.clientesDistintos = clientesDistintos;
    }

    public double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public String toString() {
        return "Model.Query4{" +
                "quantidadeCompras=" + quantidadeCompras +
                ", clientes distintos=" + clientesDistintos +
                ", ReceitaTotal=" + receitaTotal +
                '}';
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Query4 o = (Query4) obj;
        return ((this.clientesDistintos == (o.getClientesDistintos())) &&
                (this.quantidadeCompras == (o.getQuantidadeCompras())) &&
                (this.receitaTotal == (o.getReceitaTotal())));
    }

    public Query4 clone(Query4 q){
        return new Query4(q);
    }
}
