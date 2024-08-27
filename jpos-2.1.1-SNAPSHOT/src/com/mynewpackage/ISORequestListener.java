package com.mynewpackage;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;

public interface ISORequestListener {
    /**
     * Processes an incoming ISO 8583 message.
     *
     * @param source the source of the ISO message
     * @param msg the ISO message
     * @return true if the message was processed successfully, false otherwise
     */
    boolean process(ISOSource source, ISOMsg msg);
}


