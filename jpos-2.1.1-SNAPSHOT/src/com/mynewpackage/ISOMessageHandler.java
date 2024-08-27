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

            // Set the response MTI based on the received message
            m.setResponseMTI();

            // Set a response code (e.g., "00" for success)
            m.set(39, "00");

            // Send the response back to the source
            source.send(m);

        } catch (ISOException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static void main(String[] args) throws Exception {
        // Set up logging
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));

        // Set up the channel with XMLPackager
        ServerChannel channel = new XMLChannel(new XMLPackager());
        ((LogSource) channel).setLogger(logger, "channel");

        // Set up the server
        ISOServer server = new ISOServer(8000, channel, null);
        server.setLogger(logger, "server");

        // Add the request listener
        server.addISORequestListener(new ISOMessageHandler());

        // Start the server in a new thread
        new Thread(server).start();

        System.out.println("ISOMessageServer is running...");
    }
}
