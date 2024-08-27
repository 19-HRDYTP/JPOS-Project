package com.mynewpackage;

import java.io.*;
import org.jpos.iso.*;
import org.jpos.util.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.packager.*;

public class ISOMessageHandler implements ISORequestListener, org.jpos.iso.ISORequestListener {
    public ISOMessageHandler() {
        super();
    }

    @Override
    public boolean process(ISOSource source, ISOMsg m) {
        try {

            
            m.setResponseMTI();
            m.set(39, "00");
            source.send(m);

        } catch (ISOException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static void main(String[] args) throws Exception {
       
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));

       
        ServerChannel channel = new XMLChannel(new XMLPackager());
        ((LogSource) channel).setLogger(logger, "channel");

       
        ISOServer server = new ISOServer(8000, channel, null);
        server.setLogger(logger, "server");

       
        server.addISORequestListener(new ISOMessageHandler());

        
        new Thread(server).start();

        System.out.println("ISOMessageServer is running...");
    }
}
