package Model;

import java.util.Objects;

public class Venda{
    private String productCode;
    private double preco;
    private int quantidade;
    private char desconto;
    private String clientCode;
    private int mes;
    private int filial;

    public Venda(){
        this.productCode = "";
        this.preco = 0;
        this.quantidade = 0;
        this.desconto = 'N';
        this.clientCode = "";
        this.mes = 0;
        this.filial = 0;
    }
    public Venda(String pC, double p, int q, char d, String cC, int m, int f){
        setProductCode(pC);
        setPreco(p);
        setQuantidade(q);
        setDesconto(d);
        setClientCode(cC);
        setMes(m);
        setFilial(f);
    }
    public Venda(Venda v){
        this.productCode = v.getProductCode();
        this.preco = v.getPreco();
        this.quantidade = v.getQuantidade();
        this.desconto = v.getDesconto();
        this.clientCode = v.getClientCode();
        this.mes = v.getMes();
        this.filial = v.getFilial();
    }

    public void setProductCode(String s){
        this.productCode = s;
    }
    public String getProductCode(){
        return this.productCode;
    }

    public void setPreco(double p){
        this.preco = p;
    }
    public double getPreco(){
        return this.preco;
    }

    public void setQuantidade(int q){
        this.quantidade = q;
    }
    public int getQuantidade(){
        return this.quantidade;
    }

    public void setDesconto(char d){
        this.desconto = d;
    }
    public char getDesconto(){
        return this.desconto;
    }

    public void setClientCode(String cC){
        this.clientCode = cC;
    }
    public String getClientCode(){
        return this.clientCode;
    }

    public void setMes(int m){
        this.mes = m;
    }
    public int getMes(){
        return this.mes;
    }

    public void setFilial(int f){
        this.filial = f;
    }
    public int getFilial(){
        return this.filial;
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Venda v = (Venda) obj;
        return (this.productCode.equals(v.getProductCode())) &&
                (this.preco == v.getPreco()) &&
                (this.quantidade == v.getQuantidade()) &&
                (this.desconto == (v.getDesconto())) &&
                (this.clientCode.equals(v.getClientCode())) &&
                (this.mes == v.getMes()) && (this.filial == v.getFilial());
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Código do Produto - ").append(this.productCode).append("\n");
        sb.append("Preco - ").append(this.preco).append("\n");
        sb.append("Quantidade - ").append(this.quantidade).append("\n");
        sb.append("Desconto - ").append(this.desconto).append("\n");
        sb.append("Código do Cliente - ").append(this.clientCode).append("\n");
        sb.append("Mes - ").append(this.mes).append("\n");
        sb.append("Model.Filial - ").append(this.filial).append("\n");
        return sb.toString();
    }
    public Venda clone(){
        return new Venda(this);
    }
}