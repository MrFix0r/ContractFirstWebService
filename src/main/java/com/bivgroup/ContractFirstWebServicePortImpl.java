package com.bivgroup;

import com.bivgroup.ws.ContractFirstWebServicePort;
import xmlSecondStructure.University;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.*;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "com.bivgroup.ws.ContractFirstWebServicePort")
public class ContractFirstWebServicePortImpl implements ContractFirstWebServicePort {

    WebServiceContext wsctx;

    @Override
    public String getXML() {

        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(University.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File XMLfile = new File("../applications/ContractFirstWebService/WEB-INF/classes/uni.xml");
            University uni = (University) jaxbUnmarshaller.unmarshal(XMLfile);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(uni, sw);
        }
        catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
