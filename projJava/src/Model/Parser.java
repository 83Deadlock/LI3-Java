package Model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Character.isUpperCase;

public class Parser{
    /** Após ler o ficheiro dos Clientes, este método adiciona as linhas lidas pelo método lerFile() ao catálogo de clientes do SGV dado como argumento. Os códigos lidos só serão adicionados caso sejam válidos.
     *
     * @param gv - Estrutura que contém a informação do SGV ao qual vamos adicionar a informação lida pelo parser.
     */
    public void parseClientes(GestVendas gv){
        List<String> linhas = lerFile( "../input_files/" + "Clientes.txt");
        for(String s : linhas){
            if(validateClient(s)){
                gv.addClient(s);
            }
        }
    }

    /** Após ler o ficheiro dos Produtos, este método adiciona as linhas lidas pelo método lerFile() ao catálogo de produtos do SGV dado como argumento. Os códigos lidos só serão adicionados caso sejam válidos.
     *
     * @param gv - Estrutura que contém a informação do SGV ao qual vamos adicionar a informação lida pelo parser.
     */
    public void parseProdutos(GestVendas gv){
        List<String> linhas = lerFile("../input_files/" + "Produtos.txt");
        for(String s : linhas){
            if(validateProduct(s)){
                gv.addProduto(s);
            }
        }
    }

    /** Após ler o ficheiro de Vendas indicado pelo utilizador, este método faz o parse da informação da linha para as diferentes componentes da classe Venda. Ao mesmo tempo guarda essa informação na Query 11. Se a venda for válida, adiciona-a ao GestVendas.
     *
     * @param name - Nome do ficheiro indicado pelo utilizador
     * @param gv - Estrutura que contém a informação do SGV ao qual vamos adicionar a informação lida pelo parser.
     * @param q11 - Query11 é uma classe que é usada para resolver a query 11, que vai sendo atualizada aquando da leitura do ficheiro de vendas.
     */
    public void parseVendas(String name, GestVendas gv, Query11 q11){
        List<String> linhas = lerFile("../input_files/" + name);
        Scanner input;
        q11.setFilename(name);
        Set<String> produtos = new TreeSet<>();
        Set<String> produtos_compraram = new TreeSet<>();
        Set<String> clientes = new TreeSet<>();
        Set<String> clientes_compraram = new TreeSet<>();
        for(String s : linhas){
            input = new Scanner(s).useDelimiter("\\s");
            Venda v = new Venda();

            v.setProductCode(input.next());
            produtos.add(v.getProductCode());
            //System.out.println(v.getProductCode()); //Debug

            double preco = (Double.parseDouble(input.next()));
            //System.out.println(v.getPreco()); //Debug
            if(preco == 0.0){
                q11.setCompras_valor_0(q11.getCompras_valor_0() + 1);
            }

            int quant = (Integer.parseInt(input.next()));
            //System.out.println(v.getQuantidade()); //Debug

            v.setPreco(preco * quant);
            v.setQuantidade(quant);
            q11.setFaturacao_global(q11.getFaturacao_global() + v.getPreco());

            v.setDesconto(input.next().charAt(0));
            //System.out.println(v.getDesconto()); //Debug

            v.setClientCode(input.next());
            //System.out.println(v.getClientCode()); //Debug
            clientes.add(v.getClientCode());

            v.setMes(Integer.parseInt(input.next()));
            //System.out.println(v.getMes()); //Debug

            v.setFilial(Integer.parseInt(input.next()));
            //System.out.println(v.getFilial()); //Debug

            if(gv.validateVenda(v)){
                gv.addVenda(v);
                clientes_compraram.add(v.getClientCode());
                produtos_compraram.add(v.getProductCode());
            } else {
                q11.setVendas_erradas(q11.getVendas_erradas() + 1);

            }
        }
        q11.setQtd_produtos(produtos.size());
        q11.setQtd_nao_comprados(q11.getQtd_produtos()-produtos_compraram.size());
        q11.setQtd_clientes(clientes.size());
        q11.setQtd_clientes_compraram(clientes_compraram.size());
        q11.setQtd_clientes_nao_compraram(clientes.size() - clientes_compraram.size());
    }

    /** Metodo que dado uma String com o caminho para o ficheiro, vai ler as linhas do mesmo e colocá-las numa List de Strings.
     *
     * @param ficheiro - caminho para o ficheiro + nome do ficheiro
     * @return - List de Strings que contém todas as linhas lidas do ficheiro pelo método
     */
    public List<String> lerFile(String ficheiro){
        List<String> linhas = new ArrayList<>();
        try {
            linhas = Files.readAllLines(Paths.get(ficheiro), StandardCharsets.UTF_8);
        }
        catch(IOException exc) {
            System.out.println("Impossível abrir ficheiro " + ficheiro);
        }

        return linhas;
    }

    /** Verifica a validade de um dado código de cliente ao ver se tem o tamanho certo e se o 1º caracter é uma letra maiúscula e os restantes são números.
     *
     * @param s - código de cliente que vai ser analisado.
     * @return - true caso seja válido, false caso seja inválido
     */
    public boolean validateClient(String s){
        return ((s.length() == 5) && (Character.isUpperCase(s.charAt(0))) && (Character.isDigit(s.charAt(1))) &&
        (Character.isDigit(s.charAt(2))) && (Character.isDigit(s.charAt(3))) && (Character.isDigit(s.charAt(4))));
    }

    /** Verifica a validade de um dado código de produto ao ver se tem o tamanho certo e se os 1ºs e o 2ºs caracteres são letras maiúsculas e os restantes são números.
     *
     * @param s - código de produto que vai ser analisado.
     * @return - true caso seja válido, false caso seja inválido
     */
    public boolean validateProduct(String s){
        return ((s.length() == 6) && (Character.isUpperCase(s.charAt(0))) && (Character.isUpperCase(s.charAt(1))) &&
                (Character.isDigit(s.charAt(2))) && (Character.isDigit(s.charAt(3))) &&
                (Character.isDigit(s.charAt(4))) && (Character.isDigit(s.charAt(5))));
    }
}