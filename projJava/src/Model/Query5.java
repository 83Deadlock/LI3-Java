package Model;

public class Query5 {
    private String produto;
    private int qnt;

    public Query5(){
        this.produto = "";
        this.qnt = 0;
    }

    public Query5(String p, int q) {
        setProduto(p);
        setQnt(q);
    }

    public Query5(Query5 q5){
        this.produto = q5.getProduto();
        this.qnt = q5.getQnt();
    }

    public String getProduto(){
        return this.produto;
    }

    public void setProduto(String produto){
        this.produto = produto;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.produto + " comprado "+qnt+" vezes");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Query5 query5 = (Query5) obj;
        return (this.produto == (query5.getProduto()) &&
                (this.qnt == query5.getQnt()));
    }

    public Query5 clone(){
        return new Query5(this);
    }

}


