import Controller.Controller;
import Model.*;
import View.Output;

import java.io.IOException;
import java.util.*;

public class GestVendasAppMVC{
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        GestVendas gv = new GestVendas();   //Cria as estruturas de dados que vão receber os dados dos ficheiros
        Controller c = new Controller();

        c.controlador(gv);

        System.exit(0);
    }
}