import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public Servidor(){
        try {
            Registry registry = LocateRegistry.createRegistry(1098);
            ServidorChat server = new ServidorChatImpl();
            Naming.rebind("rmi://localhost:1098/ServidorChat",server);
        	System.out.println("Servidor ativo! Já é possível enviar mensagens ao(s) usuário(s)!");
        } catch (Exception e){
            System.out.println("Problema: "+e);
        }

    }

    public static void main (String args[]){
        new Servidor();
    }

}
