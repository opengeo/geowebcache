package org.geowebcache.service.wmts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.geowebcache.layer.TileLayerDispatcher;

public class WMTSGetCapabilities {
    
    //private TileLayerDispatcher tld;
    
    //private String urlStr;
    
    protected WMTSGetCapabilities(TileLayerDispatcher tld, HttpServletRequest servReq) {
        //this.tld = tld;
        //urlStr = servReq.getRequestURL().toString() + "?SERVICE=WMS&amp;";
    }
    
    protected void writeResponse(HttpServletResponse response) {
        
    }
}