package Model;

public class Query6 {
    private String productCode;
    private int unidadesvendidas;
    private int clientesDistintos;

    public Query6(){
        this.productCode = "";
        this.unidadesvendidas = 0;
        this.clientesDistintos = 0;
    }

    public Query6(String s, int u, int c){
        setProductCode(s);
        setUnidadesVendidas(u);
        setClientesDistintos(c);
    }

    public Query6(Query6 q6){
        this.productCode = q6.getProductCode();
        this.unidadesvendidas = q6.getUnidadesVendidas();
        this.clientesDistintos = q6.getClientesDistintos();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getUnidadesVendidas() {
        return unidadesvendidas;
    }

    public void setUnidadesVendidas(int unidadesvendidas) {
        this.unidadesvendidas = unidadesvendidas;
    }

    public int getClientesDistintos() {
        return clientesDistintos;
    }

    public void setClientesDistintos(int clientesDistintos) {
        this.clientesDistintos = clientesDistintos;
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Query6 query6 = (Query6) obj;
        return this.unidadesvendidas == query6.unidadesvendidas &&
                this.clientesDistintos == query6.clientesDistintos &&
                this.productCode.equals(query6.productCode);
    }

    public String toString() {
        return  "Produto = " + productCode +
                "|| Unidades vendidas = " + unidadesvendidas +
                "|| clientesDistintos = " + clientesDistintos;
    }

    public Query6 clone(){
        return new Query6(this);
    }
}
