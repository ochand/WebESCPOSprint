package webescposprinting;
import com.sun.net.httpserver.Headers;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jesusespinoza
 */
public class MyHandler implements HttpHandler {
        
        String impresora;
        public MyHandler(String impresoraSeleccionada) {
            impresora = impresoraSeleccionada;
        }
        
        @Override
        public void handle(HttpExchange t) throws IOException {
            InputStreamReader isr =  new InputStreamReader(t.getRequestBody(),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            int b;
            StringBuilder buf = new StringBuilder(2048);
            while ((b = br.read()) != -1) {
                 buf.append((char) b);
            }
            
            String result = AsciiBase64Decode(buf.toString());
            System.out.println(result);

            br.close();
            isr.close();
                        
            String response = "Recibido correctamente";
            
            Headers headers = t.getResponseHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "POST, PUT, DELETE");
            headers.add("Access-Control-Allow-Headers", "Content-Type, Accept");
            headers.add("Access-Control-Max-Age", "1728000");

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            t.getResponseHeaders();
            os.write(response.getBytes());
            os.close();
            
            
            try {
                Printer.Print(impresora, result);
            } catch (PrinterException ex) {
                Logger.getLogger(MyHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }


        
        public static String AsciiBase64Decode(String str){
            String result= "";
            try {
                 byte[] base64decodedBytes = Base64.getDecoder().decode(str);
                  result = new String(base64decodedBytes, "ascii");
                } catch (Exception e) {
                }
            return result;
        }
    }
