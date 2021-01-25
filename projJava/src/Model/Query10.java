package Model;

import java.util.HashMap;
import java.util.Map;

public class Query10 {
    private String prod;
    private double lucro;

    public Query10(){
        this.prod = "";
    }

    public Query10(String s, double l){
        setProd(s);
        setLucro(l);
    }

    public Query10(Query10 q10){
        this.prod = q10.getProd();
        this.lucro = q10.getLucro();
    }

    public void setProd(String s){
        this.prod = s;
    }

    public String getProd(){
        return this.prod;
    }

    public void setLucro(double l){
        this.lucro = l;
    }

    public double getLucro(){
        return this.lucro;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Query10 query10 = (Query10) obj;
        return (this.prod.equals(query10.getProd()) &&
                this.lucro == query10.getLucro());
    }

    public Query10 clone(){
        return new Query10(this);
    }

    public String toString(){
        return "Prod ["+this.getProd()+"] = "+this.lucro;
    }
}
