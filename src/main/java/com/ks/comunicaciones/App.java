package com.ks.comunicaciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class App
{
    public static void main(String[] args)
    {
        String ip = System.getProperty("ip", "localhost");
        String port = System.getProperty("port", "9090");
        String path = System.getProperty("file", null);
        boolean recursive = Boolean.parseBoolean(System.getProperty("recursive", "false"));
        int time = Integer.parseInt(System.getProperty("time", "1000"));

        int contador = 0;

        if (ip != null && port != null)
        {
            System.out.println("Iniciando aplicacion");
            clienteTCP cliente = new clienteTCP();
            cliente.setIP(ip);
            cliente.setPuerto(Integer.parseInt(port));
            try
            {
                cliente.conectar();
                Thread.sleep(100);
                if (path != null && path.trim().length() > 0)
                {
                    do
                    {
                        Thread.sleep(2000);
                    } while (!cliente.isConnected());
                    try
                    {
                        File file = new File(path);
                        if (file.exists())
                        {
                            BufferedReader reader;
                            String line;
                            do
                            {
                                reader = new BufferedReader(new FileReader(file));
                                while ((line = reader.readLine()) != null)
                                {
                                    if (line.trim().length() > 0)
                                    {
                                        contador += 1;
                                        cliente.enviar(line);
                                        System.out.println("Mensaje enviado " + contador + ": " + line);
                                        Thread.sleep(time);
                                    }
                                    while (!cliente.isConnected())
                                    {
                                        Thread.sleep(100);
                                    }
                                }
                                reader.close();
                            } while (recursive);
                        }
                        else
                        {
                            System.out.println("No se encuentra el archivo para leer: " + path);
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
