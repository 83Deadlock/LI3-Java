package Model;

import java.util.*;

public class Query12 {
    private Map<Integer,Integer> comprasPorMes;
    private Map<Integer,Map<Integer,Double>> faturacaoPorMes;
    private double faturacaoTotal;
    private Map<Integer,Map<Integer,Integer>> clientesPorMes;

    public Query12(){
        this.comprasPorMes = new HashMap<>();
        this.faturacaoPorMes = new HashMap<>();
        this.faturacaoTotal = 0;
        this.clientesPorMes = new HashMap<>();
        for(int mes = 1; mes < 13; mes++){
            comprasPorMes.put(mes,0);
        }
    }

    public Query12 (Map<Integer,Integer> cpm, Map<Integer,Map<Integer,Double>> fpm, double ft, Map<Integer,Map<Integer,Integer>> clpm){
        setComprasPorMes(cpm);
        setFaturacaoPorMes(fpm);
        setFaturacaoTotal(ft);
        setClientesPorMes(clpm);
    }

    public Query12 (Query12 q12){
        setComprasPorMes(q12.getComprasPorMes());
        setFaturacaoPorMes(q12.getFaturacaoPorMes());
        setFaturacaoTotal(q12.getFaturacaoTotal());
        setClientesPorMes(q12.getClientesPorMes());
    }

    public void setComprasPorMes(Map<Integer,Integer> fat){
        this.comprasPorMes = new HashMap<>();
        for(Integer i : fat.keySet()){
            this.comprasPorMes.put(i,fat.get(i));
        }
    }

    public Map<Integer,Integer> getComprasPorMes(){
        Map<Integer,Integer> newM = new HashMap<>();
        for(Integer i : this.comprasPorMes.keySet()){
            newM.put(i,this.comprasPorMes.get(i));
        }
        return newM;
    }

    public void setFaturacaoPorMes(Map<Integer,Map<Integer,Double>> fat){
        this.faturacaoPorMes = new HashMap<>();
        for(Map.Entry<Integer,Map<Integer,Double>> mapentry : fat.entrySet()){
            this.faturacaoPorMes.put(mapentry.getKey(),mapentry.getValue());
        }
    }

    public Map<Integer,Map<Integer,Double>> getFaturacaoPorMes(){
        Map<Integer,Map<Integer,Double>> newM = new HashMap<>();
        for(Map.Entry<Integer,Map<Integer,Double>> mapentry : this.faturacaoPorMes.entrySet()){
            newM.put(mapentry.getKey(),mapentry.getValue());
        }
        return newM;
    }

    public void setFaturacaoTotal(double faturacaoTotal) {
        this.faturacaoTotal = faturacaoTotal;
    }

    public double getFaturacaoTotal() {
        return faturacaoTotal;
    }

    public void setClientesPorMes(Map<Integer,Map<Integer,Integer>> fat){
        this.clientesPorMes = new HashMap<>();
        for(Map.Entry<Integer,Map<Integer,Integer>> mapentry : fat.entrySet()){
            this.clientesPorMes.put(mapentry.getKey(),mapentry.getValue());
        }
    }

    public Map<Integer,Map<Integer,Integer>> getClientesPorMes(){
        Map<Integer,Map<Integer,Integer>> newM = new HashMap<>();
        for(Map.Entry<Integer,Map<Integer,Integer>> mapentry : this.clientesPorMes.entrySet()){
            newM.put(mapentry.getKey(),mapentry.getValue());
        }
        return newM;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Query12 query12 = (Query12) obj;
        return Double.compare(query12.faturacaoTotal, faturacaoTotal) == 0 &&
                Objects.equals(comprasPorMes, query12.comprasPorMes) &&
                Objects.equals(faturacaoPorMes, query12.faturacaoPorMes) &&
                Objects.equals(clientesPorMes, query12.clientesPorMes);
    }

    public Query12 clone(){
        return new Query12(this);
    }

    public String toString() {
        return "Query12{" +
                "comprasPorMes=" + comprasPorMes +
                ", faturacaoPorMes=" + faturacaoPorMes +
                ", faturacaoTotal=" + faturacaoTotal +
                ", clientesPorMes=" + clientesPorMes +
                '}';
    }
}
