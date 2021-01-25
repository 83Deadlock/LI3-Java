package Model;

public class Query7 {
    private String client;
    private double gasto;

    public Query7(){
        this.client = "";
        this.gasto = 0.0;
    }

    public Query7(String s, double g){
        setClient(s);
        setGasto(g);
    }

    public Query7(Query7 q7){
        this.client = q7.getClient();
        this.gasto = q7.getGasto();
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getGasto() {
        return this.gasto;
    }

    public void setGasto(double gasto) {
        this.gasto = gasto;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(client);
        sb.append("--- Gasto: ").append(gasto);
        return  sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Query7 query7 = (Query7) obj;
        return Double.compare(query7.gasto, gasto) == 0 &&
                this.client.equals(query7.client);
    }

    public Query7 clone(){
        return new Query7(this);
    }
}
