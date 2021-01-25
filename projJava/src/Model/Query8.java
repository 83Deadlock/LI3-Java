package Model;

public class Query8 {
    private String client;
    private int produtos;

    public Query8(){
        this.client = "";
        this.produtos = 0;
    }

    public Query8(String p, int q) {
        setClient(p);
        setProdutos(q);
    }

    public Query8(Query8 q8){
        this.client = q8.getClient();
        this.produtos = q8.getProdutos();
    }

    public String getClient(){
        return this.client;
    }

    public void setClient(String client){
        this.client = client;
    }

    public int getProdutos() {
        return this.produtos;
    }

    public void setProdutos(int qnt) {
        this.produtos = qnt;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.client + " comprou "+this.produtos+" produtos");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Query8 query8 = (Query8) obj;
        return (this.client == (query8.getClient()) &&
                (this.produtos == query8.getProdutos()));
    }

    public Query8 clone(){
        return new Query8(this);
    }
}
