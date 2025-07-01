/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webescposprinting;

import java.awt.Component;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.rmi.server.LogStream.log;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;
import javax.swing.JOptionPane;

/**
 *
 * @author desarrollo
 */
public class Printer {
    public static void Print(String impresora, String result) throws PrinterException {
        try {
            PrintService mPrinter = null;
            Boolean bFoundPrinter = false;
            PrintService[] printServices = PrinterJob.lookupPrintServices();

            PrinterJob pj = PrinterJob.getPrinterJob();

            for (PrintService printService : printServices) {
                String sPrinterName = printService.getName();
                if (sPrinterName.equals(impresora)) {
                    mPrinter = printService;
                    bFoundPrinter = true;
                }
            }
            
            pj.setPrintService(mPrinter);

            // Open the image file
            //String testData = "Hello World !";
            String testData = result;
            InputStream is = new ByteArrayInputStream(testData.getBytes());
            DocFlavor flavor =  DocFlavor.INPUT_STREAM.AUTOSENSE;

            // Find the default service            
            PrintService service = pj.getPrintService();
     
            System.out.println("Found print service: "+service);
            for( Attribute a : service.getAttributes().toArray() ) {
                System.out.println("*"+a.getName()+": "+a);
            }
                        
            //Validar el si la impresora esta disponible
            System.out.println(service.getAttribute(PrinterIsAcceptingJobs.class).getValue());
            PrinterState prnState =  (PrinterState)service.getAttribute(PrinterState.class);
            if (prnState == PrinterState.STOPPED /*|| prnState == null*/) {
                PrinterStateReasons prnStateReasons =(PrinterStateReasons)service.getAttribute(PrinterStateReasons.class);
                if ((prnStateReasons == null) || (prnStateReasons.containsKey(PrinterStateReason.SHUTDOWN)))
                {
                    throw new PrintException("PrintService is no longer available.");
                }
            }
            
           
            // Create the print job
            DocPrintJob printJob = service.createPrintJob();
            
            Doc doc= new SimpleDoc(is, flavor, null);
            
            // Monitor print job events; for the implementation of PrintJobWatcher,
            PrintJobWatcher pjDone = new PrintJobWatcher(printJob);

            // Print it
            printJob.print(doc, null);
		
            // Wait for the print job to be done
            pjDone.waitForDone();

            // It is now safe to close the input stream
            is.close();
        } catch (PrintException e) {
            e.printStackTrace();
            Component frame = null;
            JOptionPane.showMessageDialog(frame,
                    "Llego una solicitud para impresión, pero la impresora no se encuentra disponible.",
                    "Oops! algo salio mal",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 
    static class PrintJobWatcher{
        // true iff it is safe to close the print job's input stream
        boolean done = false;

        PrintJobWatcher(DocPrintJob job) {
            // Add a listener to the print job
            job.addPrintJobListener(new PrintJobAdapter() {
                public void printJobCanceled(PrintJobEvent pje) {
                    System.out.println("m1");
                    allDone();
                }
                public void printJobCompleted(PrintJobEvent pje) {
                                        System.out.println("m2");

                    allDone();
                }
                public void printJobFailed(PrintJobEvent pje) {
                                        System.out.println("m3");

                    allDone();
                }
                public void printJobNoMoreEvents(PrintJobEvent pje) {
                                        System.out.println("m4");

                    allDone();
                }
                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }
        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }
}