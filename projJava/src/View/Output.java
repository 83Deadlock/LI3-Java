package View;


import Controller.Controller;
import Controller.Input;
import Model.*;

import java.lang.reflect.Array;
import java.util.*;

public class Output {

    /** Imprime o resultado da Query 1
     *
     * @param setS - Estrutura do resultado da Query 1
     */
    public void printQ1(Set<String> setS){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println(setS.size() + " produtos nunca foram comprados.");
        System.out.println("-=Lista dos produtos que ninguém comprou=-");
        List<String> newL = new ArrayList<>(setS.size());
        for(String s: setS){
            newL.add(s);
        }
        //System.out.println(newL.size()); -> estava na versão entregue, my bad
        imprimirPaginado(newL);
    }

    /** Imprime o resultado da Query 2
     *
     * @param l  - Estrutura do resultado da Query 2
     * @param mes - Mês para o qual a Query 2 foi executada
     */
    public void printQ2(List<List<Integer>> l, int mes){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("Informação referente ao mês "+ mes);
        System.out.println("INFORMAÇÃO GLOBAL");
        System.out.println(l.get(0).get(0) + " Vendas");
        System.out.println(l.get(0).get(1) + " Clientes unicos");

        for(int i = 1; i < l.size(); i++){
            System.out.println("INFORMAÇÃO FILIAL "+i);
            System.out.println(l.get(i).get(0) + " Vendas");
            System.out.println(l.get(i).get(1) + " Clientes unicos");
        }
    }

    /** Imprime o resultado da Query 3
     *
     * @param q3 - Estrutura do resultado da Query 1
     * @param client - Cliente para o qual a Query 3 foi executada
     */
    public void printQ3(Map<Integer, Query3> q3, String client){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("Informação sobre o cliente "+client);

        for(int i = 1; i < 13; i++){
            System.out.println("############################################################");
            System.out.println("Mês "+i+":");
            System.out.println("     Número de compras realizadas: "+q3.get(i).getQuantidadeCompras());
            System.out.println("     Número de produtos distintos comprados: "+q3.get(i).getProdutosComprados());
            System.out.printf("     Total gasto: %.2f\n",q3.get(i).getGastoTotal());
        }
    }

    /** Imprime o resultado da Query 4
     *
     * @param q4 - Estrutura do resultado da Query 1
     * @param product - Produto para o qual a QUery 4 foi executada
     */
    public void printQ4(Map<Integer, Query4> q4, String product){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("Informação sobre o produto " + product);

        for(int i = 1; i < 13; i++){
            System.out.println("############################################################");
            System.out.println("Mês "+i+":");
            System.out.println("     Número de vendas: "+q4.get(i).getQuantidadeCompras());
            System.out.println("     Número de clientes distintos: "+q4.get(i).getClientesDistintos());
            System.out.printf("     Total gasto: %.2f\n",q4.get(i).getReceitaTotal());
        }
    }

    /** Imprime o resultado da Query 5
     *
     * @param q5 - Estrutura do resultado da Query 1
     * @param client - Cliente para o qual a Query 5 foi executada
     */
    public void printQ5(List<Query5> q5, String client){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("Informação sobre o cliente "+client);
        for(int i = 0; i < q5.size(); i++){
            System.out.println(q5.get(i).getProduto() + " - " + q5.get(i).getQnt());
        }
    }

    /** Imprime o resultado da Query 6
     *
     * @param q6 - Estrutura do resultado da Query 6
     */
    public void printQ6(List<Query6> q6){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("A imprimir os " + q6.size() + " elementos mais vendidos");
        if(q6.size() > 30){
            List<String> list = new ArrayList<>(q6.size());
            for(int i = 0; i < q6.size(); i++){
                list.add(i,q6.get(i).toString());
            }
            imprimirPaginado(list);
        } else {
            for(int i = 0; i < q6.size(); i++){
                System.out.println("##########" + (i+1) + "º##########");
                System.out.println("Produto = "+q6.get(i).getProductCode());
                System.out.println("Unidades Vendidas = "+q6.get(i).getUnidadesVendidas());
                System.out.println("Clientes distintas = "+q6.get(i).getClientesDistintos());
            }
        }

    }

    /** Imprime o resultado da Query 7
     *
     * @param q7 - Estrutura do resultado da Query 7
     */
    public void printQ7(List<List<Query7>> q7){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        for(int i = 0; i < q7.size(); i++){
            System.out.println("3 maiores clientes na Filial "+(i+1));
            for(int j = 0; j < 3; j++){
                System.out.printf("%d.- Cliente: %s --- Gasto : %.2f \n",(j+1),q7.get(i).get(j).getClient(), q7.get(i).get(j).getGasto());
                //System.out.println((j+1) + ".- " + q7.get(i).get(j).toString());
            }
            System.out.println("----------------------------------------------");
        }
    }

    /** Imprime o resultado da Query 8
     *
     * @param q8 - Estrutura do resultado da Query 8
     */
    public void printQ8(List<Query8> q8){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        if(q8.size() > 30){
            List<String> s = new ArrayList<>(q8.size());
            for(int i = 0; i < q8.size(); i++){
                s.add(i,q8.get(i).toString());
            }
            imprimirPaginado(s);
        } else {
            for(int i = 0; i < q8.size(); i++){
                System.out.println(q8.get(i).toString());
            }
        }
    }

    /** Imprime o resultado da Query 9
     *
     * @param q9 - Estrutura do resultado da Query 9
     */
    public void printQ9(List<Query9> q9){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        if(q9.size() > 30){
            List<String> s = new ArrayList<>(q9.size());
            for(int i = 0; i < q9.size(); i++){
                s.add(i,q9.get(i).toString());
            }
        } else {
            for(int i = 0; i < q9.size(); i++){
                if(q9.get(i) != null){
                    System.out.printf("Cliente: %s - %d unidades, lucro = %.2f \n",q9.get(i).getCliente(),q9.get(i).getQuantidadeCompras(),q9.get(i).getGastoTotal());
                } else {
                    i = q9.size();
                }
            }
        }
    }

    /** Imprime o resultado da Query 10
     *
     * @param q10 - Estrutura do resultado da Query 10
     * @param prod - produto para o qual a Query 10 foi executado.
     */
    public void printQ10(Map<Integer,Map<Integer, Query10>> q10, String prod){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        boolean exit = false;
        System.out.println("Faturacao do produto "+prod);
        int filiais = q10.get(1).keySet().size();

        int mes = 1; //Inicialização dos dois valores simplesmente para evitar o warning de não ser inicializado
        int fil = q10.get(1).keySet().size();

        for(int i = 1; i < 13; i++){
            System.out.println("Mês "+i);
            for(int j = 1; j < fil+1; j++){
                System.out.printf("    Filial %d -> %.2f\n",j,q10.get(i).get(j).getLucro());
            }
        }


    }

    /** Imprime o resultado da Query 11
     *
     * @param q11 - Estrutura do resultado da Query 11
     */
    public void printQ11(Query11 q11){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println(q11.toString());
    }

    /** Imprime o resultado da Query 12
     *
     * @param q12 - Estrutura do resultado da Query 12
     */
    public void printQ12(Query12 q12) {
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("-----===Número total de compras por mês===-----\n");
        System.out.println("|| Mês ||   Quantidade");
        for (int mes = 1; mes < 13; mes++) {
            System.out.println("||  " + mes + "  ||" + "  " + q12.getComprasPorMes().get(mes));
            System.out.println("||++++++++++++++++++++++++++++++++++");
        }

        System.out.println("------------------------//---------------------\n");

        int filiais = q12.getFaturacaoPorMes().keySet().size();

        System.out.println("-----===Faturação por mês===-----\n");
        for (int id = 1; id < filiais + 1; id++) {
            System.out.println("Filial " + id);
            System.out.println("|| Mês ||   Faturação");
            for (int mes = 1; mes < 13; mes++) {
                System.out.println("||  " + mes + "  ||" + "  " + q12.getFaturacaoPorMes().get(id).get(mes));
                System.out.println("||+++++++++++++++++++++++++++++");
            }
        }
        System.out.println("------------------------//---------------------\n");

        System.out.println("-----===Faturação Global===-----\n");
        System.out.println("Faturação global = " + q12.getFaturacaoTotal());
        System.out.println("------------------------//---------------------\n");

        System.out.println("-----===Número de Clientes que comprou por mês===-----\n");
        for (int id = 1; id < filiais + 1; id++) {
            System.out.println("Filial " + id);
            System.out.println("|| Mês ||   Quantidade");
            for (int mes = 1; mes < 13; mes++) {
                System.out.println("||  " + mes + "  ||" + "  " + q12.getClientesPorMes().get(id).get(mes));
                System.out.println("||+++++++++++++++++++++++++++++");
            }
        }
    }

    /** Serve-se do tamanho da lista parâmetro para calcular o número de páginas necessário para imprimir 30 linhas por página.
     *
     * @param listS - Lista das strings que vão ser apresentadas ao utilizador
     */
    public void imprimirPaginado(List<String> listS){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");

        int currPag = 0;
        int itemsporpag = 30;
        int maxpag  = (listS.size()/itemsporpag);
        int flag = 0;
        int index = 0;
        boolean valid = false;
        int size = listS.size();

        while(flag != 3) { //sinaliza o final da visualização
            index = currPag * 30;
            for (int i = 0; i < itemsporpag; i++) {
                if(index >= size){i = itemsporpag;}
                else {
                    String s = listS.get(index);
                    if (s != null) {
                        System.out.println(s);
                        index++;
                    }
                }

            }
            System.out.println("Página " + (currPag + 1) + "/" + (maxpag + 1));
            if (currPag == 0) {
                System.out.println("2 - Página Seguinte | 3 - Sair");
            } else if (currPag == maxpag) {
                System.out.println("1 - Página anterior | 3 - Sair");
            } else {
                System.out.println("1 - Página anterior | 2 - Página Seguinte | 3 - Sair");
            }

            while (!valid) {
                flag = Controller.getInputInt(); //Lê a opção do user
                if (flag == 1 && currPag == 0) { //User quer ver pag anterior mas nao existe
                    System.out.println("Não existe página anterior!");
                    System.out.println("2 - Página Seguinte | 3 - Sair");
                } else if (flag == 1) { //User quer ver pag anterior
                    currPag--; //recuamos uma pagina
                    valid = true; //Validamos o comando
                } else if(flag == 2 && currPag == maxpag){
                    System.out.println("Não existe página seguinte!");
                    System.out.println("1 - Página anterior | 3 - Sair");
                } else if (flag == 2){
                    currPag++;
                    valid = true;
                } else if(flag == 3){
                    System.out.println("Saindo da visualização!");
                    valid = true;
                } else {
                    System.out.println("Comando não reconhecido!");
                    if(currPag == 0){
                        System.out.println("2 - Página Seguinte | 3 - Sair");
                    } else if (currPag == maxpag) {
                        System.out.println("1 - Página anterior | 3 - Sair");
                    } else {
                        System.out.println("1 - Página anterior | 2 - Página Seguinte | 3 - Sair");
                    }
                }
            }
            valid = false;
        }

    }

    public void perguntaNomeFile(){
        System.out.print("\n");

        System.out.print("Nome do ficheiro -> ");
    }

    public void perguntaNomeFileVendas(){
        System.out.print("\n");

        System.out.print("Nome do ficheiro de vendas -> ");
    }

    public void clientsLoaded(){

        System.out.println("Catálogo de Clientes carregado.");
    }

    public void productsLoaded(){

        System.out.println("Catálogo de Produtos carregado.");
    }

    public void salesLoaded(){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("Catálogo de Vendas carregado.");
    }

    public void comandoInvalido(){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("Comando inválido. Tente novamente.");
    }

    public void menu(){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("#################################################################");
        System.out.println("###                          SGV                              ###");
        System.out.println("#################################################################");
        System.out.println("1. Consultas estatísticas");
        System.out.println("2. Consultas interativas");
        System.out.println("3. Guardar ficheiro");
        System.out.println("4. Carregar ficheiro");
        System.out.println("5. Carregar Sistema de Gestão de Vendas");
        System.out.println("0. Sair");
    }

    public void sgvnotloaded(){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("O Sistema de Gestão de Vendas ainda não foi carregado!");
    }

    public void menuQueriesI(){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("#####################################################################################################");
        System.out.println("###                                     Consultas Interativas                                     ###");
        System.out.println("#####################################################################################################");
        System.out.println("1.  Lista e quantidade de produtos nunca comprados");
        System.out.println("2.  Número  de vendas realizadas e clientes disintos para um mês (Global e por Filial)");
        System.out.println("3.  Número de compras, produtos distintos e gasto total para um cliente ao longo do ano");
        System.out.println("4.  Número de vezes que um produto foi comprado mensalmente, por quantos clientes e o valor faturado");
        System.out.println("5.  Lista de produtos que um cliente mais comprou");
        System.out.println("6.  Produtos mais vendidos em todo ano e número total de clientes que os comprou");
        System.out.println("7.  Três maiores compradores para cada filial");
        System.out.println("8.  Clientes que compraram mais produtos diferentes");
        System.out.println("9.  Conjunto dos clientes que mais compraram um produto e o valor gasto total no mesmo");
        System.out.println("10. Faturação total de cada filial para um produto");
        System.out.println("0. Voltar ao menu Inicial");
    }

    public void perguntaMes(){
        System.out.println("Insira o mês a analisar: ");
    }

    public void mesInvalido(){
        System.out.println("Mês inválido! Insira um valor entre 1 e 12, inclusive.");
    }

    public void perguntaCliente(){
        System.out.println("Insira o código do cliente: ");
    }

    public void nonexistentclient(){
        System.out.println("O cliente que inseriu não existe no catálogo do SGV! Tente novamente.");
    }

    public void perguntaProduto(){
        System.out.println("Insira o código do produto: ");
    }

    public void nonexistentproduct(){
        System.out.println("O produto que inseriu não existe no catálogo do SGV! Tente novamente.");
    }

    public void perguntaQtsProdutos(){
        System.out.println("Quantos produtos deseja ver?");
    }

    public void perguntaQtsClientes(){
        System.out.println("Quantos clientes deseja ver?");
    }

    public void printVoltandoMenu(){
        System.out.println("Voltando ao menu principal");
    }

    public void menuQueriesE(){
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("#################################################################");
        System.out.println("###                  Consultas Estatísticas                   ###");
        System.out.println("#################################################################");
        System.out.println("1. Dados Referentes ao último ficheiro de vendas lido");
        System.out.println("2. Dados Registados no SGV");
        System.out.println("0. Sair");
    }

    public void continuar(){
        System.out.println("\n Presseione qualquer tecla + Enter para voltar ao menu");
    }

    /** Imprime para o utilizador o tempo lido no controlador.
     *
     * @param time - tempo lido no controlador
     */
    public void runTime(String time){
        System.out.println("\n Elapsed time: "+time);
    }
}
