package Model;

import java.io.*;

public class Saver extends IOException implements  Serializable{
    public int guardaDados(String filename, GestVendas gv){
        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(gv);
            oos.flush();
            oos.close();
        }
        catch(FileNotFoundException e){
            return 1;
        }
        catch(IOException e){
            return 2;
        }
        return 0;
    }

    public GestVendas carregaDados(String file) throws IOException, ClassNotFoundException{
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(f);
        GestVendas sgv = (GestVendas) ois.readObject();
        ois.close();
        return sgv;
    }
}
