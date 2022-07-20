/*
 * TCPCliente.java
 *
 * Created on 18 de Maio de 2006, 12:05
/*
Trabalho pr�tico sobre sockets UDP,
para disciplina de Sistemas Distribuidos.
Equipe:
Alaim de Jesus Le�o Costa
Manoel Malon Costa de Moura
*/

import java.net.*;
import java.io.*;
public class TCPCliente {
    public static void main(String args[]) {
        Socket s = null;
        BufferedReader bufferedReader=null;
        try {
        	s = new Socket(InetAddress.getByName( "localhost"), 6789); // conecta o socket aa porta remota
            DataInputStream  ent = new DataInputStream(s.getInputStream());
            DataOutputStream sai = new DataOutputStream(s.getOutputStream());
            
            System.out.println("Digite o nome do cliente");
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String nomeCliente = bufferedReader.readLine();
            sai.writeUTF(nomeCliente);
            
            System.out.println("Digite o nome do arquivo:");
            
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String string = bufferedReader.readLine();
            sai.writeUTF(string);
            String recebido = ent.readUTF();
            while (recebido != null) {
                System.out.println(recebido);
                recebido = ent.readUTF();
            }
        } catch (UnknownHostException e) {
            System.out.println("Servidor desconhecido: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("--- FIM DA TRANSFERENCIA ---");
        } catch (IOException e) {
            System.out.println("E/S: " + e.getMessage());
        } finally {
            if (s!=null)
                try {
                    s.close();
                } catch (IOException e){
                    System.out.println("Encerramento do socket falhou: " + e.getMessage());
                }
        }
    }
    
}
