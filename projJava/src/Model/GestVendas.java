package Model;

import java.util.*;
import java.lang.StringBuilder;

public class GestVendas{
    private Set<String> clientCatalog;
    private Set<String> productCatalog;
    private Map<String,List<Venda>> faturacao; //A key será o Código de Produto
    private Map<Integer,Filial> filiais; //Uma lista de 3 elementos (no caso deste trabalho), em que cada elemento será a informação de uma filial.

    public GestVendas(){
        this.clientCatalog = new TreeSet<>();
        this.productCatalog = new TreeSet<>();
        this.faturacao = new HashMap<>();
        this.filiais = new HashMap<>();
    }
    public GestVendas(Set<String> setC, Set<String> setP, Map<String,List<Venda>> setV, HashMap<Integer,Filial> fil){
        setClientCatalog(setC);
        setProductCatalog(setP);
        setFaturacao(setV);
        setFiliais(fil);
    }
    public GestVendas(GestVendas gv){
        this.clientCatalog = gv.getClientCatalog();
        this.productCatalog = gv.getProductCatalog();
        this.faturacao = gv.getFaturacao();
        this.filiais = gv.getFiliais();
    }

    public void setClientCatalog(Set<String> set){
        this.clientCatalog = new TreeSet<>();
        for(String s : set){
            clientCatalog.add(s);
        }
    }
    public Set<String> getClientCatalog() {
        Set<String> newS = new TreeSet<>();
        for(String s : this.clientCatalog){
            newS.add(s);
        }
        return newS;
    }

    public void setProductCatalog(Set<String> set){
        this.productCatalog = new TreeSet<>();
        for(String s : set){
            productCatalog.add(s);
        }
    }
    public Set<String> getProductCatalog() {
        Set<String> newS = new TreeSet<>();
        for(String s : this.productCatalog){
            newS.add(s);
        }
        return newS;
    }

    public void setFaturacao(Map<String,List<Venda>> fat){
        this.faturacao = new HashMap<>();
        for(Map.Entry<String,List<Venda>> mapentry : fat.entrySet()){
            this.faturacao.put(mapentry.getKey(),mapentry.getValue());
        }
    }
    public Map<String,List<Venda>> getFaturacao(){
        Map<String,List<Venda>> newM = new HashMap<>();
        for(Map.Entry<String,List<Venda>> mapentry : this.faturacao.entrySet()){
            newM.put(mapentry.getKey(),mapentry.getValue());
        }
        return newM;
    }

    public Map<Integer, Filial> getFiliais() {
        Map<Integer,Filial> newM = new HashMap<>();
        for(Map.Entry<Integer,Filial> mapentry : this.filiais.entrySet()){
            newM.put(mapentry.getKey(),mapentry.getValue().clone());
        }
        return newM;
    }
    public void setFiliais(Map<Integer, Filial> filiais) {
        this.filiais = new HashMap<>();
        for(Map.Entry<Integer,Filial> mapentry : filiais.entrySet()){
            this.filiais.put(mapentry.getKey(),mapentry.getValue().clone());
        }
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        GestVendas o = (GestVendas) obj;
        return (this.clientCatalog.equals(o.getClientCatalog())) &&
                (this.productCatalog.equals(o.getProductCatalog())) &&
                (this.faturacao.equals(o.getFaturacao())) &&
                (this.filiais.equals(o.getFiliais()));
    }
    public GestVendas clone(){
        return new GestVendas(this);
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("-=Catálogo de Model.Clientes=-\n");
        for(String s : this.clientCatalog){
            sb.append(s);
            sb.append("\n");
        }

        sb.append("-=Catálogo de Produtos=-\n");
        for(String s : this.productCatalog){
            sb.append(s);
            sb.append("\n");
        }

        sb.append("-=Faturação=-\n");
        this.faturacao.toString();

        sb.append("-=Filiais=-\n");
        for(Filial f : this.filiais.values()){
            sb.append(f.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    /** Adiciona um código de cliente ao catálogo de clientes
     *
     * @param s - código de cliente
     */
    public void addClient(String s){ //Adiciona um cliente ao Clientes
        this.clientCatalog.add(s);
    }

    /** Adiciona um código de produto ao catálogo de produtos e à Faturação.
     *
     * @param s - código de produto
     */
    public void addProduto(String s){ //Adiciona um produto ao Catálogo de Produtos
        this.productCatalog.add(s);
        this.faturacao.putIfAbsent(s, new ArrayList<>()); //Inicializa a faturação
    }

    /** Adiciona a informação da Venda v à faturação (addVendaToFat()) e às filiais (addVendaToFilial()).
     *
     * @param v - Venda que vai ser adicionada à faturação e às filiais
     */
    public void addVenda(Venda v){
        addVendaToFat(v);
        addVendaToFilial(v);
    }

    /** Verifica se o cliente e o produto da Venda existem no catálogo dos clientes e produtos, respetivamente.
     *
     * @param v - Venda que vamos verirficar se existe ou não
     * @return existência do cliente e do produtos nos respetivos catálogos
     */
    public boolean validateVenda(Venda v){
        return (this.clientCatalog.contains(v.getClientCode())) && (this.productCatalog.contains(v.getProductCode()));
    }

    /** Adiciona uma Venda v à faturação do SGV, colocando-a na List com elementos do tipo Venda, value da key que é o código de produto da Venda.
     *
     * @param v - Venda que vai ser adicionada à faturação
     */
    public void addVendaToFat(Venda v){
        String key = v.getProductCode();
        this.faturacao.putIfAbsent(key,new ArrayList<>());
        this.faturacao.get(key).add(v);
    }

    /** Adiciona a informação de uma Venda v à estrutura das Filiais, na Filial, mês, cliente e produto respetivos.
     *
     * @param v - Venda cuja informação vai ser registada
     */
    public void addVendaToFilial(Venda v){
        this.filiais.putIfAbsent(v.getFilial(), new Filial());
        this.filiais.get(v.getFilial()).addtoFilial(v);
    }
}