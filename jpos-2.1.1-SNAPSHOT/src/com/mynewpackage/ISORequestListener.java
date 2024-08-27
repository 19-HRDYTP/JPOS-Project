package com.mynewpackage;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;

public interface ISORequestListener {
    
    boolean process(ISOSource source, ISOMsg msg);
}


