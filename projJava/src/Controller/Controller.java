package Controller;

import Model.*;
import View.Output;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static Controller.Input.lerString;

public class Controller {
    Output out = new Output();
    Input input = new Input();
    String filenameload;
    Query11 q11 = new Query11();

    /** Este metodo cria o controlador para o Menu Inicial do Programa.
     *
     * @param gv - Sistema de Gestao de Vendas
     * @throws IOException Pode resultar em excepcoes.
     * @throws ClassNotFoundException quando  nao consegue encontrar uma class que ia ser carregada.
     */
    public void controlador(GestVendas gv) throws IOException, ClassNotFoundException {
        boolean flag = true;
        Saver saver = new Saver();
        Parser ps = new Parser();
        boolean loadedSGV = false;
        int option;
        while (flag) {
            out.menu();
            option = input.lerInt();
            switch (option) {
                case 1: //Queries estatisticas
                    if(!loadedSGV){
                        out.sgvnotloaded();
                    } else {
                        controladorQueriesE(gv);
                    }
                    break;
                case 2: //Queries interativas
                    if (!loadedSGV) {
                        out.sgvnotloaded();
                    } else {
                        controladorQueriesI(gv);
                    }
                    break;
                case 3: //GuardarFile
                    out.perguntaNomeFile();
                    String filename = input.lerString();
                    saver.guardaDados(filename, gv);
                    break;
                case 4:  //Carregar fileout.perguntaNomeFile();
                    out.perguntaNomeFile();
                    filenameload = input.lerString();
                    gv = saver.carregaDados(filenameload);
                    break;
                case 5:
                    ps.parseClientes(gv);
                    out.clientsLoaded();
                    ps.parseProdutos(gv);
                    out.productsLoaded();
                    out.perguntaNomeFile();
                    String filenamev = input.lerString();
                    Crono.start();
                    ps.parseVendas(filenamev, gv, q11);
                    String time = Crono.getTime();
                    out.salesLoaded();
                    out.runTime(time);
                    loadedSGV = true;
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    out.comandoInvalido();
            }
        }
    }

    /** Este metodo cria o controlador para o Menu das Queries Interativas.
     *
     * @param gv - Sistema de Gestao de Vendas
     */
    public void controladorQueriesI(GestVendas gv) {
        String time;
        boolean flag = true;
        while (flag) {
            out.menuQueriesI();
            int option = input.lerInt();
            boolean valid;
            String client;
            String prod;
            int x;
            switch (option) {
                case 1:
                    Crono.start();
                    Set<String> q1 = Query.querie1(gv);
                    time = Crono.getTImeString();

                    out.printQ1(q1);
                    out.runTime(time);
                    break;

                case 2:
                    valid = false;
                    int mes = 1;
                    while (!valid) {
                        out.perguntaMes();
                        mes = input.lerInt();
                        if (mes >= 1 && mes <= 12) {
                            valid = true;
                        } else {
                            out.mesInvalido();
                        }
                    }
                    Crono.start();
                    List<List<Integer>> q2 = Query.querie2(gv,mes);
                    time = Crono.getTImeString();

                    out.printQ2(q2, mes);
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;

                case 3:
                    valid = false;
                    client = "";
                    while (!valid) {
                        out.perguntaCliente();
                        client = input.lerString();
                        if (gv.getClientCatalog().contains(client)) {
                            valid = true;
                        } else {
                            out.nonexistentclient();
                        }
                    }
                    Crono.start();
                    Map<Integer,Query3> q3 = Query.querie3(gv,client);
                    time = Crono.getTImeString();

                    out.printQ3(q3, client);
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;

                case 4:
                    valid = false;
                    prod = "";
                    while (!valid) {
                        out.perguntaProduto();
                        prod = input.lerString();
                        if (gv.getProductCatalog().contains(prod)) {
                            valid = true;
                        } else {
                            out.nonexistentproduct();
                        }
                    }
                    Crono.start();
                    Map<Integer,Query4> q4 = Query.querie4(gv,prod);
                    time = Crono.getTImeString();

                    out.printQ4(q4, prod);
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;

                case 5:
                    valid = false;
                    client = "";
                    while (!valid) {
                        out.perguntaCliente();
                        client = input.lerString();
                        if (gv.getClientCatalog().contains(client)) {
                            valid = true;
                        } else {
                            out.nonexistentclient();
                        }
                    }
                    Crono.start();
                    List<Query5> q5= Query.querie5(gv,client);
                    time = Crono.getTImeString();

                    out.printQ5(q5, client);
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;

                case 6:
                    out.perguntaQtsProdutos();
                    x = input.lerInt();
                    Crono.start();
                    List<Query6> q6 = Query.querie6(gv,x);
                    time = Crono.getTImeString();

                    out.printQ6(q6);
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;

                case 7:
                    Crono.start();
                    List<List<Query7>> q7 = Query.querie7(gv);
                    time = Crono.getTImeString();

                    out.printQ7(q7);
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;

                case 8:
                    out.perguntaQtsClientes();
                    x = input.lerInt();
                    Crono.start();
                    List<Query8> q8 = Query.querie8(gv,x);
                    time = Crono.getTImeString();

                    out.printQ8(q8);
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;

                case 9:
                    prod = "";
                    valid = false;
                    while (!valid) {
                        out.perguntaProduto();
                        prod = input.lerString();
                        if (gv.getProductCatalog().contains(prod)) {
                            valid = true;
                        } else {
                            out.nonexistentproduct();
                        }
                    }
                    out.perguntaQtsClientes();
                    x = input.lerInt();
                    Crono.start();
                    List<Query9> q9 = Query.querie9(gv,prod,x);
                    time = Crono.getTImeString();
                    out.printQ9(Query.querie9(gv,prod,x));
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;
                case 10:
                    prod = "";
                    valid = false;
                    while (!valid) {
                        out.perguntaProduto();
                        prod = input.lerString();
                        if (gv.getProductCatalog().contains(prod)) {
                            valid = true;
                        } else {
                            out.nonexistentproduct();
                        }
                    }
                    Crono.start();
                    Map<Integer,Map<Integer,Query10>> q10 = Query.querie10(gv,prod);
                    time = Crono.getTImeString();
                    out.printQ10(q10,prod);
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;
                case 0:
                    out.printVoltandoMenu();
                    flag = false;
                    break;
                default:
                    out.comandoInvalido();
                    break;
            }
        }
    }

    /** Este metodo cria o controlador para o Menu das Queries Estatísticas.
     *
     * @param gv - Sistema de Gestao de Vendas
     */
    public void controladorQueriesE(GestVendas gv){
        boolean flag = true;
        String time;
        while(flag){
            out.menuQueriesE();
            int option = input.lerInt();
            switch(option){
                case 1:
                    Crono.start();
                    Query.querie11(gv,q11);
                    time = Crono.getTImeString();
                    out.printQ11(q11);
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;
                case 2:
                    Crono.start();
                    Query12 q12 = Query.querie12(gv);
                    time = Crono.getTImeString();
                    out.printQ12(Query.querie12(gv));
                    out.runTime(time);
                    out.continuar();
                    input.lerString();
                    break;
                case 0:
                    out.printVoltandoMenu();
                    flag = false;
                    break;
            }

        }
    }

    /** Método utilizado para ler input do utilizador na mudanca de paginas no sistema de output.
     *
     * @return input do utilizador
     */
    public static int getInputInt(){
        Input in = new Input();
        int res = in.lerInt();
        return res;
    }
}
