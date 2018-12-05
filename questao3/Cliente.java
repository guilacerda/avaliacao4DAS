import java.rmi.*;
import javax.swing.*;
import java.util.Scanner;
import java.lang.Thread.*;
import java.util.ArrayList;
import java.rmi.RemoteException;

public class Cliente {

    public static void main( String args[] ) {
        try {
            final ServidorChat chat = (ServidorChat) Naming.lookup( "rmi://localhost:1098/ServidorChat" );

            String nome;
            String msg = "";
            //String id;
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite seu nome:");

            nome = scanner.nextLine();
            
            //System.out.println("Fale o nome de quem você quer enviar uma mensagem: ");
            //System.out.println("Mandar mensagem no chat privado = Nome do usuário");
            //System.out.println("Mandar mensagem no chat geral = all");
            
            //id = scanner.nextLine();
            
            Thread thread = new Thread(new Runnable() {
                int cont = chat.lerMensagem().size();
                @Override
                public void run() {
                    try {
                        while(true){
                            if (chat.lerMensagem().size() > cont){
                                System.out.println(chat.lerMensagem().get(chat.lerMensagem().size()-1));
                                cont++;
                            }
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

            while(msg != "exit"){

                msg = scanner.nextLine();
                
                String s = nome + ": " + msg;
                
                chat.enviarMensagem(s);
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
