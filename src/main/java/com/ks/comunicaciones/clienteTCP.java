package com.ks.comunicaciones;

import com.ks.lib.Configuracion;
import com.ks.lib.tcp.Cliente;
import com.ks.lib.tcp.EventosTCP;
import com.ks.lib.tcp.Tcp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by migue on 16/10/2015.
 */
public class clienteTCP extends Cliente implements EventosTCP
{
    private String path;
    private File file;
    private BufferedWriter writer;

    private int VMintContador;

    public clienteTCP()
    {
        VMintContador = 0;
        this.setEventos(this);
        path = Configuracion.getRuta();
        path += (path.contains("/") ? "log/" : "log\\");
        file = new File(path);
        if (!file.exists())
        {
            file.mkdir();
        }
    }

    public void conexionEstablecida(Cliente cliente)
    {
        VMintContador = 0;
        System.out.println("Se conecto con el servidor " + super.getIP() + ":" + super.getPuerto());
        try
        {
            writer = new BufferedWriter(new FileWriter(path + "client-" + super.getPuerto() + ".log", true));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void errorConexion(String s)
    {
        VMintContador = 0;
        System.out.println("Problema al conectarse con el cliente: " + s);
        if (writer != null)
        {
            try
            {
                writer.flush();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void datosRecibidos(java.lang.String s, byte[] bytes, Tcp tcp)
    {
        VMintContador += 1;
        System.out.println("Mensaje recibido " + VMintContador + ": " + s);
        if (writer != null)
        {
            try
            {
                writer.write(s);
                writer.newLine();
                writer.flush();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void cerrarConexion(Cliente cliente)
    {
        VMintContador = 0;
    }
}
