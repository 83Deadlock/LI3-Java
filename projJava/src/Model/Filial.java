package Model;

import java.util.*;

public class Filial{
    private int id;
    private Map<Integer,FaturaMensal> filial; //Cada elemento da Lista será uma Fatura Mensal (12 meses -> 12 elementos)

    public Filial(){
        this.id = 0;
        this.filial = new HashMap<>();
    }
    public Filial(int id, HashMap<Integer,FaturaMensal> m){
        setId(id);
        setFilial(m);
    }
    public Filial(Filial f){
        this.id = f.getId();
        this.filial = f.getFilial();
    }

    public Map<Integer, FaturaMensal> getFilial() {
        Map<Integer,FaturaMensal> newM = new HashMap<>();
        for(Map.Entry<Integer,FaturaMensal> mapentry : this.filial.entrySet()){
            newM.put(mapentry.getKey(),mapentry.getValue().clone());
        }
        return newM;
    }
    public void setFilial(Map<Integer, FaturaMensal> filial) {
        this.filial = filial;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Filial o = (Filial) obj;
        return (this.id == o.getId()) &&
                (this.filial.equals(o.getFilial()));
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(this.id).append("\n");
        sb.append(this.filial.toString());
        return sb.toString();
    }
    public Filial clone(){
        return new Filial(this);
    }

    /** Adiciona à Filial os conteúdos da Venda v
     *
     * @param v - Venda que vai ser adicionada à filial
     */
    public void addtoFilial(Venda v){
        this.id = (v.getFilial());
        this.filial.putIfAbsent(v.getMes(),new FaturaMensal());
        this.filial.get(v.getMes()).addtoFaturaMensal(v);

    }
}