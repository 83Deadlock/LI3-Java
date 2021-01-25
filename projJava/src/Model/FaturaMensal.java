package Model;

import java.util.*;

public class FaturaMensal {
    private int vendasMensais;
    private Map<String,FaturaMensalCliente> map; //A key vai ser o codigo do cliente e a Fatura Mensal do Produto

    public FaturaMensal(){
        this.vendasMensais = 0;
        this.map = new HashMap<>();
    }
    public FaturaMensal(int vM,Map<String,FaturaMensalCliente> m){
        setVendasMensais(vM);
        setMap(m);
    }
    public FaturaMensal (FaturaMensal fm){
        this.vendasMensais = fm.getVendasMensais();
        this.map = fm.getMap();
    }

    public int getVendasMensais() {
        return vendasMensais;
    }
    public void setVendasMensais(int vendasMensais) {
        this.vendasMensais = vendasMensais;
    }

    public void setMap(Map<String, FaturaMensalCliente> map) {
        this.map = new HashMap<>();
        for(Map.Entry<String,FaturaMensalCliente> mapentry : map.entrySet()){
            this.map.put(mapentry.getKey(),mapentry.getValue().clone());
        }
    }
    public Map<String, FaturaMensalCliente> getMap(){
        Map<String, FaturaMensalCliente> newM = new HashMap<>();
        for(Map.Entry<String, FaturaMensalCliente> mapentry : this.map.entrySet()){
            newM.put(mapentry.getKey(),mapentry.getValue().clone());
        }
        return newM;
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        FaturaMensal o = (FaturaMensal) obj;
        return (o.map.equals(this.map));
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.vendasMensais + " vendas mensais").append("\n");
        sb.append(this.map.toString());
        return sb.toString();
    }
    public FaturaMensal clone(){
        return new FaturaMensal(this);
    }

    /** Passa para a FaturaMensal os dados referentes à venda recebida como parâmetro.
     *
     * @param v - Venda que vai ser adicionada para a FaturaMensal.
     */
    public void addtoFaturaMensal(Venda v){
        String key = v.getClientCode();
        this.map.putIfAbsent(key, new FaturaMensalCliente());
        this.map.get(key).addtoFaturaMensalCliente(v);
        this.vendasMensais++;
    }
}
