package Model;

public class Query9 {
    private int quantidadeCompras;
    private double gastoTotal;
    private String cliente;

    /** Construtores
     *
     */
    public Query9(){
        this.quantidadeCompras = 0;
        this.gastoTotal = 0.0;
        this.cliente = "";
    }

    public Query9(int quantidadeCompras, double gastoTotal,String s) {
        setQuantidadeCompras(quantidadeCompras);
        setGastoTotal(gastoTotal);
        setCliente(s);
    }

    public Query9(Query9 q9){
        this.quantidadeCompras = q9.getQuantidadeCompras();
        this.gastoTotal = q9.getGastoTotal();
        this.cliente = q9.getCliente();
    }

    public int getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(int quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
    }

    public double getGastoTotal() {
        return gastoTotal;
    }

    public void setGastoTotal(double gastoTotal) {
        this.gastoTotal = gastoTotal;
    }

    public String getCliente() {
        return this.cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String toString() {
        return cliente + "= " + quantidadeCompras +" unidades, lucro= " + gastoTotal;
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Query9 o = (Query9) obj;
        return ((this.quantidadeCompras == (o.getQuantidadeCompras())) &&
                (this.gastoTotal == (o.getGastoTotal())) &&
                (this.cliente.equals(o.getCliente())));
    }

    public Query9 clone(Query9 q){
        return new Query9(q);
    }

    public static Query9 sumInfoQuery9(Query9 q9, int qnt, double gasto, String client){
        return new Query9(q9.getQuantidadeCompras() + qnt, q9.getGastoTotal() + gasto,client);
    }
}
