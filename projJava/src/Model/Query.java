package Model;

import java.util.*;

public class Query {

    /** Percorre a faturacao do GestVendas, e para todos os codigos de produto presentes registados, verifica o tamanho da lista de vendas que se lhe associa. Caso o tamanho desta lista seja 0, adiciona o codigo ao Set de Strings que vai ser retornado.
     *
     * @param gv - GestVendas onde vamos buscar a informacao.
     * @return Set com todos os codigos de produto que foram encontrados com lista de Vendas de tamanho zero (que nunca foram comprados)
     */
    public static Set<String> querie1(GestVendas gv){
        Set<String> products = new TreeSet<>();
        Set<String> keys = new TreeSet<>(gv.getFaturacao().keySet());
        Map<String, List<Venda>> newM = new HashMap<>(gv.getFaturacao());
        for(String s : keys){
            if(newM.get(s).size() == 0){
                products.add(s);
            }
        }
        return products;
    }

    /** Percorre a estrutura das Filiais e soma o numero de vendasMensais em cada Filial para obter o numero global.
     *
     * @param gv - GestVendas onde vamos buscar a informacao.
     * @param mes - Passado pelo utilizador, mes para o qual vamos analisar a quantidade de vendas nas filiais e na faturacao global.
     * @return Uma lista com tantos elementos quanto o numero de filiais + 1 (faturacao global) que em cada elemento tem uma List com dois elementos (o primeiro e o numero de vendas realizadas e o segundo e numero total de clientes que as fizeram).
     */
    public static List<List<Integer>> querie2(GestVendas gv, int mes){

        /*
         * Inicialização da estrutura de retorno
         * Os elementos da lista são listas com 2 elementos em que o primeiro é o numero de vendas realizadas
         *      e o segundo é numero total de clientes que as fizeram
         * A estrutura vai ter tantos elementos como o numero de filiais + 1 (informação relativa à faturação global)
         */
        int size = 1 + gv.getFiliais().keySet().size();
        List<List<Integer>> l = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            l.add(i,new ArrayList<>(2));
        }

        Set<String> clientesunicos = new TreeSet<>(); //Guarda os clientes unicos

        //Tratamento da faturacao global
        int vendas = 0;
        int clientes = 0;

        for(int i = 1; i < size; i++){

            int vendasFil = gv.getFiliais().get(i).getFilial().get(mes).getVendasMensais();
            vendas += vendasFil;
            l.get(i).add(0,vendasFil);

            Set<String> listfm = gv.getFiliais().get(i).getFilial().get(mes).getMap().keySet();

            l.get(i).add(1,listfm.size());
            clientesunicos.addAll(listfm);
            listfm.clear();
        }

        clientes = clientesunicos.size();
        l.get(0).add(0,vendas);
        l.get(0).add(1,clientes);

        return l;
    }

    /** Percorre a estrutura das Filiais e junta as compras e produtos distintos e o gasto total em cada mes para o cliente dado.
     *
     * @param gv - GestVendas onde vamos buscar a informacao.- GestVendas onde vamos buscar a informacao.
     * @param clientCode - Cliente cuja informacao vai ser recolhida na estrutura das Filiais.
     * @return Map em que a key (Integer) e o mes e o value e uma Query 3 (Query3) com a informacao referente ao mes (key) e ao cliente recebido.
     */
    public static Map<Integer,Query3> querie3(GestVendas gv, String clientCode){
        Map<Integer,Query3> q3 = new HashMap<>(); //A key será o mês
        Map<Integer,Integer> comprasDistintas = new HashMap<>();
        Map<Integer,Set<String>> produtosDistintos = new HashMap<>(); //Key = mês
        Map<Integer,Double> gastoTotal = new HashMap<>();

        for(int i = 1; i < 13; i++){
            q3.put(i,new Query3());
            comprasDistintas.put(i, 0);
            produtosDistintos.put(i, new TreeSet<>());
            gastoTotal.put(i, 0.0);
        }

        double gastomensal = 0.0;
        int vendasmensais = 0;

        for(int i = 1; i < gv.getFiliais().keySet().size()+1; i++){
            for(int mes = 1; mes < 13; mes++){

                FaturaMensalCliente aux = gv.getFiliais().get(i).getFilial().get(mes).getMap().get(clientCode);
                if(aux != null){
                    vendasmensais = comprasDistintas.get(mes) + gv.getFiliais().get(i).getFilial().get(mes).getMap().get(clientCode).getComprasMensais();
                    comprasDistintas.put(mes,vendasmensais);

                    Set<String> prods;
                    prods = aux.getProducts().keySet();
                    produtosDistintos.get(mes).addAll(prods);

                    gastomensal = aux.getGasto() + gastoTotal.get(mes);
                    gastoTotal.put(mes,gastomensal);
                }
            }
        }

        for(int i = 1; i < 13; i++){
            vendasmensais = comprasDistintas.get(i);
            q3.get(i).setQuantidadeCompras(vendasmensais);

            q3.get(i).setProdutosComprados(produtosDistintos.get(i).size());

            q3.get(i).setGastoTotal(gastoTotal.get(i));
        }

        return q3;
    }

    /** Percorre a estrutura das Filiais e junta as compras e clientes distintos que compraram o produto dado assim como o gasto total.
     *
     * @param gv - Cliente cuja informação vai ser recolhida na estrutura das Filiais.
     * @param productCode - código do produto que vamos analisar
     * @return Map em que a key (Integer) é o mês e o value é uma Query 3 (Query3) com a informação referente ao mês (key) e ao cliente recebido.
     */
    public static Map<Integer,Query4> querie4(GestVendas gv, String productCode){
        Map<Integer, Query4> q4 = new HashMap<>();
        Map<Integer,Integer> comprasDistintas = new HashMap<>();
        Map<Integer,Set<String>> clientesDistintos = new HashMap<>();
        Map<Integer,Double> receitaTotal = new HashMap<>();

        for(int i = 1; i < 13; i++){
            q4.put(i,new Query4());
            comprasDistintas.put(i, 0);
            clientesDistintos.put(i, new TreeSet<>());
            receitaTotal.put(i, 0.0);
        }

        List<Venda> list = gv.getFaturacao().get(productCode);
        for(int i = 0; i < list.size(); i++){
            Venda aux = list.get(i);
            int mes = aux.getMes();
            comprasDistintas.put(mes,comprasDistintas.get(mes) + 1);
            clientesDistintos.get(mes).add(aux.getClientCode());
            double receita = receitaTotal.get(mes) + aux.getPreco();
            receitaTotal.put(mes,receita);
        }

        for(int i = 1; i < 13; i++){
            q4.get(i).setQuantidadeCompras(comprasDistintas.get(i));
            q4.get(i).setClientesDistintos(clientesDistintos.get(i).size());
            q4.get(i).setReceitaTotal(receitaTotal.get(i));
        }

        return q4;
    }

    /** Percorre a estrutura das Filiais e junta a quantidade de compras pelo cliente dado para cada produto, para cada mês em cada filial.
     *
     * @param gv - GestVendas onde vamos buscar a informação.
     * @param client - Cliente cuja informação vai ser recolhida na estrutura das Filiais.
     * @return Lista ordenada de Query5 pela quantidade de compras feitas pelo cliente para cada produto.
     */
    public static List<Query5> querie5(GestVendas gv, String client){
        Map<String,Integer> map = new HashMap<>();
        for(int i = 1; i < gv.getFiliais().size()+1; i++){
            for(int mes = 1; mes < 13; mes++){
                if(gv.getFiliais().get(i).getFilial().get(mes).getMap().get(client) != null) {
                    Map<String,FaturaMensalProduto> mapAux2 = gv.getFiliais().get(i).getFilial().get(mes).getMap().get(client).getProducts();
                    for(String s1 : mapAux2.keySet()){
                        map.putIfAbsent(s1,0);
                        map.put(s1,map.get(s1)+ mapAux2.get(s1).getQuantidadeP() + mapAux2.get(s1).getQuantidadeN());
                    }
                }
            }
        }
        int size = map.keySet().size();
        List<Query5> list = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            String s = getMaior(map);
            list.add(i,new Query5(s,map.get(s)));
            map.remove(s);
        }
        return list;
    }

    /** Recebe um Map de String para Inteiros e procura qual a key que tem como valor correspondente o maior de todos os valores.
     *
     * @param m - Map de String (produtos) para Inteiros (quantidades de compras)
     * @return String do produto com mais vendas
     */
    public static String getMaior(Map<String,Integer> m){ //auxiliar da q5 que encontra o produto com mais compras
        int max = 0;
        String sR = "";
        for(String s : m.keySet()){
            if(m.get(s) == max){
                if(s.compareTo(sR) > 0){
                    max = m.get(s);
                    sR = s;
                }
            }
            if(m.get(s) > max){
                max = m.get(s);
                sR = s;
            }
        }
        return sR;
    }

    /** Determina os produtos com mais vendas durante o ano indicando o número total de clientes distintos que os compraram.
     *
     * @param gv - GestVendas onde vamos buscar a informação.
     * @param x - Número de produtos que vão ser apresentados
     * @return Lista ordenada de Query6 que contém o número de vendas unitárias do produto e o número de clientes distintos que o compraram.
     */
    public static List<Query6> querie6(GestVendas gv, int x){
        List<Query6> ls = new ArrayList<>(x);

        List<String> prods = new ArrayList<>(x); //Os top x produtos mais vendidos são adicionados aqui
        Map<String,List<Venda>> fat = gv.getFaturacao();
        for(int i = 0; i < x; i++){
            String s = new String(getMaiorVenda(fat));
            prods.add(s);
            fat.remove(s);
        }

        for(int i = 0; i < x; i++){
            String prod = prods.get(i);
            int quant = 0;
            Set<String> cli = new TreeSet<>();
            int size = gv.getFaturacao().get(prod).size();
            for(int j = 0; j < size; j++){
                quant += gv.getFaturacao().get(prod).get(j).getQuantidade();
                cli.add(gv.getFaturacao().get(prod).get(j).getClientCode());
            }
            ls.add(i,new Query6(prod,quant,cli.size()));
        }
        return ls;
    }

    /** Dado um Map de produtos para a lista de vendas desse mesmo produto, encontra o produto com mais unidades vendidas somando a quantidade vendida em cada Venda da lista.
     *
     * @param fat - Map que a cada produto faz corresponder a lista das vendas correspondentes.
     * @return - código do produto com mais unidades vendidas
     */
    public static String getMaiorVenda(Map<String,List<Venda>> fat){
        int max = 0;
        String sR = "";
        for(String s : fat.keySet()){
            int quant = 0;
            for(int i = 0; i < fat.get(s).size();i++){
                quant += fat.get(s).get(i).getQuantidade();
            }
            if(quant > max){
                max = quant;
                sR = s;
            }
        }
        return sR;
    }

    /** Percorre o registo das filiais para todas as filiais e todos os meses e guarda a informação de quanto gastou cada cliente num dado mês, numa dada filial. Por fim, encontram-se os 3 com maior valor de gasto.
     *
     * @param gv - GestVendas onde vamos buscar a informação.
     * @return Lista (1 elemento para cada filial) com uma lista de 12 elementos Query7 (1 por mês)
     */
    public static List<List<Query7>> querie7(GestVendas gv){
        int filiais = gv.getFiliais().keySet().size();
        List<List<Query7>> lR = new ArrayList<>();
        for(int i = 0; i < filiais; i++){
            lR.add(i, new ArrayList<>(3));
        }

        for(int i = 1; i < (filiais +1); i++){
            Map<String,Double> track = new HashMap<>();
            for(int mes = 1; mes < 13; mes++){
                Map<String,FaturaMensalCliente> mensal = gv.getFiliais().get(i).getFilial().get(mes).getMap();
                for(String s : mensal.keySet()){
                    track.putIfAbsent(s,0.0);
                    track.put(s,track.get(s) + mensal.get(s).getGasto());
                }
            }
            for(int top = 0; top < 3; top++){
                String c = getTop(track);
                double g = track.get(c);
                lR.get(i-1).add(top, new Query7(c,g));
                track.remove(c,g);
            }
        }
        return lR;
    }

    /** Dado um map que a cada cliente faz corresponder o seu gasto, encontra o cliente com maior gasto.
     *
     * @param track Map que a cada cliente faz corresponder o gasto desse cliente
     * @return Código do cliente com maior gasto
     */
    public static String getTop(Map<String,Double> track){
        double max = 0.0;
        String sr = "";
        for(String s : track.keySet()){
            if(track.get(s) > max){
                max = track.get(s);
                sr = s;
            }
        }
        return sr;
    }

    /** Determina os x clientes que compraram mais produtos diferentes.
     *
     * @param gv - GestVendas onde vamos buscar a informação.
     * @param x - Número de clientes que vai ser apresentado.
     * @return Lista de Query8 que contém o número de produtos diferentes pelos x clientes que mais produtos diferentes compraram.
     */
    public static List<Query8> querie8(GestVendas gv, int x){
        List<Query8> lR = new ArrayList<>(x);

        int filiais = gv.getFiliais().keySet().size();
        Map<String,Integer> track = new HashMap<>();
        Map<String,Set<String>> prods = new HashMap<>();

        for(int i = 1; i < (filiais +1); i++){
            for(int mes = 1; mes < 13; mes++){
                Map<String,FaturaMensalCliente> mensal = gv.getFiliais().get(i).getFilial().get(mes).getMap();
                for(String s : mensal.keySet()){
                    track.put(s,0);
                    prods.putIfAbsent(s, new TreeSet<>());
                    prods.get(s).addAll(mensal.get(s).getProducts().keySet());
                }
            }
        }

        for(String s : prods.keySet()){
            track.put(s,prods.get(s).size());
        }

        for(int top = 0; top < x; top++){
            String c = getTopInt(track);
            int g = track.get(c);
            lR.add(top,new Query8(c,g));
            track.remove(c,g);
        }
        return lR;
    }

    /** Dado um map que a cada cliente faz corresponder a quantidade de produtos distintos comprados.
     *
     * @param track Map que a cada cliente faz corresponder a quantidade de produtos diferentes comprados
     * @return Código do cliente com maior quantidade de produtos distintos comprados
     */
    public static String getTopInt(Map<String,Integer> track){
        int max = 0;
        String sr = "";
        for(String s : track.keySet()){
            if(track.get(s) > max){
                max = track.get(s);
                sr = s;
            }
        }
        return sr;
    }

    /** Percorre a faturação, para a lista de vendas correspondente ao código de produto dado, recolhe os codigos dos clientes, a quantidade e o gasto nesse produto.
     *
     * @param gv - GestVendas onde vamos buscar a informação.
     * @param prod - código do produto a ser analisado.
     * @param x - número de clientes que vai ser apresentado
     * @return Lista dos x elementos da Query9 que guarda a informação do cliente, quantidade e gasto de compras.
     */
    public static List<Query9> querie9(GestVendas gv, String prod, int x){ //Key vai ser o cliente, Query 9 tem a info do gasto e da quantidade comprada
        Map<String, Query9> map = new HashMap<>();
        List<Venda> list = gv.getFaturacao().get(prod);
        Map<String,Integer> cliQuant = new HashMap<>();

        for(int i = 0; i < list.size(); i++){
            Venda newv = list.get(i);
            String client = newv.getClientCode();

            map.putIfAbsent(client,new Query9());
            map.put(client,Query9.sumInfoQuery9(map.get(client), newv.getQuantidade(),newv.getPreco(),client));

            cliQuant.putIfAbsent(client,0);
            cliQuant.put(client,cliQuant.get(client) + newv.getQuantidade());
        }

        List<Query9> lR = new ArrayList<>(x);

        for(int i = 0; i < x; i++){
            String client = getMaior(cliQuant);
            lR.add(i,map.get(client));
            cliQuant.remove(client,cliQuant.get(client));
        }
        return lR;
    }

    /** Percorre as filiais, mês a mês, calcula a faturação total do produto para todos os clientes registados.
     *
     * @param gv - GestVendas onde vamos buscar a informação.
     * @param prod - codigo do produto que vai ser analisado
     * @return Map que a cada mês faz corresponder um map, no qual a cada ID de filial faz corresponder a Query 10 que tem o codigo do produto e a sua faturação.
     */
    public static Map<Integer,Map<Integer,Query10>> querie10(GestVendas gv,String prod){
        Map<Integer,Map<Integer,Query10>> mapR = new HashMap<>();
        for(int i = 1; i < 13; i++){
            Map<Integer, Query10> aux = new HashMap<>();
            for(int j = 1; j < gv.getFiliais().keySet().size()+1; j++){
                Map<String,FaturaMensalCliente> clients = gv.getFiliais().get(j).getFilial().get(i).getMap();
                Query10 q10 = new Query10();
                for(String s: clients.keySet()){
                    Map<String,FaturaMensalProduto> products = clients.get(s).getProducts();
                    if(products.get(prod) != null){
                        double income = products.get(prod).getIncomeN() + products.get(prod).getIncomeP();
                        q10.setLucro(q10.getLucro() + income);
                        q10.setProd(prod);
                    } else {
                        q10.setProd(prod);
                        q10.setLucro(q10.getLucro());
                    }
                }
                aux.put(j,q10);
            }
            mapR.put(i,aux);
        }
        return mapR;
    }

    /** Através da informação lida aquando da leitura do ficheiro, calculamos as restantes informações necessárias da Query 11.
     *
     * @param gv - GestVendas onde vamos buscar a informação.
     * @param q11 - Query 11 preenchida aquando da leitura do ficheiro
     * @return Query11 com as informações necessárias.
     */
    public static Query11 querie11(GestVendas gv, Query11 q11){
        Query11 q = new Query11();
        q.setFilename(q11.getFilename());
        q.setVendas_erradas(q11.getVendas_erradas());
        int prods_n_comprados = querie1(gv).size();
        q.setQtd_produtos(q11.getQtd_produtos());
        q.setQtd_produtos_comprados((q11.getQtd_produtos()) - prods_n_comprados);
        q.setQtd_nao_comprados(prods_n_comprados);
        q.setQtd_clientes(q11.getQtd_clientes());
        q.setQtd_clientes_nao_compraram(q11.getQtd_clientes_nao_compraram());
        q.setQtd_clientes_compraram(q.getQtd_clientes() - q.getQtd_clientes_nao_compraram());
        q.setCompras_valor_0(q11.getCompras_valor_0());
        q.setFaturacao_global(q11.getFaturacao_global());
        return q;
    }

    /** Analisa os dados do GestVendas para encontrar a informação pedida, dado que praticamente toda a informação necessária se econtra gravada nas estruturas do SGV.
     *
     * @param gv - - GestVendas onde vamos buscar a informação.
     * @return Query12 que guarda a informação de quantas compras foram feitas, faturação (para cada filial e global) e número de clientes que compraram para cada mês.
     */
    public static Query12 querie12(GestVendas gv){
        Query12 q12 = new Query12();


        int filiais = gv.getFiliais().keySet().size();

        Map<Integer,Integer> auxVendasMensais = new HashMap<>();

        Map<Integer,Map<Integer,Double>> auxFaturacaoMensal = new HashMap<>();

        Map<Integer,Map<Integer,Integer>> auxClientesPorMes = new HashMap<>();

        double auxfat = 0;

        for(int i = 1; i < filiais+1; i++){
            auxFaturacaoMensal.put(i,new HashMap<>());
            Map<Integer,Double> hm = new HashMap<>();
            auxClientesPorMes.put(i,new HashMap<>());
            Map<Integer,Integer> cliMensais = new HashMap<>();
            for(int mes = 1; mes < 13; mes++){
                auxVendasMensais.putIfAbsent(mes,0);
                auxVendasMensais.put(mes, auxVendasMensais.get(mes) + gv.getFiliais().get(i).getFilial().get(mes).getVendasMensais());

                double gastoMensal = 0.0;
                hm.put(mes,gastoMensal);
                Map<String,FaturaMensalCliente> map = gv.getFiliais().get(i).getFilial().get(mes).getMap();

                for(String s : map.keySet()){
                    double gasto = map.get(s).getGasto();
                    gastoMensal += gasto;
                    auxfat += gasto;
                }

                hm.put(mes,gastoMensal);
                cliMensais.put(mes,map.keySet().size());

            }
            auxFaturacaoMensal.put(i,hm);
            auxClientesPorMes.put(i,cliMensais);
        }

        q12.setComprasPorMes(auxVendasMensais);
        q12.setFaturacaoPorMes(auxFaturacaoMensal);
        q12.setClientesPorMes(auxClientesPorMes);
        q12.setFaturacaoTotal(auxfat);
        return q12;
    }
}
