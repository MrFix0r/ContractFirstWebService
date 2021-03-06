
package com.bivgroup.ws;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ContractFirstWebServicePort", targetNamespace = "http://www.bivgroup.com/ContractFirstWebService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ContractFirstWebServicePort {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getXML")
    @WebResult(name = "returnXMLString", partName = "returnXMLString")
    public String getXML();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getPath")
    @WebResult(name = "path", partName = "path")
    public String getPath();

    /**
     * 
     * @return
     *     returns com.bivgroup.ws.University
     */
    @WebMethod(action = "getUni")
    @WebResult(name = "uni", partName = "uni")
    public University getUni();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getDB")
    @WebResult(name = "dbString", partName = "dbString")
    public String getDB();

}
