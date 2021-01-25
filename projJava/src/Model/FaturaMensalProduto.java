package Model;

public class FaturaMensalProduto {
    private int quantN;
    private double incN;
    private int quantP;
    private double incP;

    public FaturaMensalProduto() {
        this.quantN = 0;
        this.incN = 0.0;
        this.quantP = 0;
        this.incP = 0.0;
    }
    public FaturaMensalProduto(int qn, double in, int qp, double ip){
        setQuantidadeN(qn);
        setIncomeN(in);
        setQuantidadeP(qp);
        setIncomeP(qp);
    }
    public FaturaMensalProduto (FaturaMensalProduto fmp){
        this.quantN = fmp.getQuantidadeN();
        this.incN = fmp.getIncomeN();
        this.quantP = fmp.getQuantidadeP();
        this.incP = fmp.getIncomeP();
    }

    public int getQuantidadeN() {
        return quantN;
    }
    public void setQuantidadeN(int quantN) {
        this.quantN = quantN;
    }

    public double getIncomeN() {
        return incN;
    }
    public void setIncomeN(double incN) {
        this.incN = incN;
    }

    public int getQuantidadeP() {
        return quantP;
    }
    public void setQuantidadeP(int quantP) {
        this.quantP = quantP;
    }

    public double getIncomeP() {
        return incP;
    }
    public void setIncomeP(double incP) {
        this.incP = incP;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Compras normais: "+quantN).append("\n");
        sb.append("Ganhos normais: "+incN).append("\n");
        sb.append("Compras em desconto: "+quantP).append("\n");
        sb.append("Ganhos em desconto: "+incP).append("\n");
        return sb.toString();
    }
    public FaturaMensalProduto clone(){
        return new FaturaMensalProduto(this);
    }
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        FaturaMensalProduto o = (FaturaMensalProduto) obj;
        return (o.getIncomeN() == this.getIncomeN()) &&
                (o.getIncomeP() == this.getIncomeP()) &&
                (o.getQuantidadeN() == this.getQuantidadeN()) &&
                (o.getQuantidadeP() == this.getQuantidadeP());
    }

    /** Adiciona os conteúdos da Venda v à FaturaMensalProduto
     *
     * @param v - Venda que vai ser adicionada
     */
    public void addFaturaMensalProduto(Venda v){
        if(v.getDesconto() == 'N'){
            this.incN = (this.getIncomeN() + v.getPreco());
            this.quantN = (this.getQuantidadeN() + v.getQuantidade());
        } else if (v.getDesconto() == 'P'){
            this.incP = (this.getIncomeP() + v.getPreco());
            this.quantP = (this.getQuantidadeP() + v.getQuantidade());
        }
    }
}
