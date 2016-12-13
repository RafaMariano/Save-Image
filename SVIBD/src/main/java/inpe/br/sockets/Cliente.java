package inpe.br.sockets;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;
 
public class Cliente {
    public static void main(String[] args) throws IOException {
        //cria um socket com o google na porta 80
        Socket socket = new Socket("localhost",8000);
        //verifica se esta conectado
        if (socket.isConnected()) { 
            //imprime o endereço de IP do servidor
            System.out.println("Conectado a " + socket.getInetAddress());
        }
        
       
        
        String requisicao = ""
                + "GET / HTTP/1.1\r\n"
                + "Host: www.google.com.br\r\n"
                + "\r\n";
            //OutputStream para enviar a requisição
            OutputStream envioServ = socket.getOutputStream();
            //temos que mandar a requisição no formato de vetor de bytes
            byte[] b = requisicao.getBytes();
            //escreve o vetor de bytes no "recurso" de envio 
            envioServ.write(b);
            //marca a finalização da escrita
            envioServ.flush();
            
//            Scanner sc = new Scanner(socket.getInputStream());
//            //enquanto houver algo para ler
//            while (sc.hasNext()) {
//                //imprime uma linha da resposta
//                System.out.println(sc.nextLine());
//            }
    }
}