package com.bivgroup;

import com.bivgroup.ws.ContractFirstWebServicePort;
import com.bivgroup.ws.Report;
import com.bivgroup.ws.University;

import javax.jws.WebService;
import javax.xml.bind.*;
import javax.xml.ws.WebServiceContext;
import java.io.File;
import java.io.StringWriter;

@WebService(endpointInterface = "com.bivgroup.ws.ContractFirstWebServicePort")
public class ContractFirstWebServicePortImpl implements ContractFirstWebServicePort {

    @Override
    public String getXML() {

        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(University.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ClassLoader loader = this.getClass().getClassLoader();
            University uni = (University) jaxbUnmarshaller.unmarshal(loader.getResource("uni.xml"));
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

    @Override
    public University getUni() {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Report.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ClassLoader loader = this.getClass().getClassLoader();
            Report uni = (Report) jaxbUnmarshaller.unmarshal(loader.getResource("uni.xml"));
            return uni.getUniversity();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getPath() {
        return "hello";
//        return this.getClass().getResource("/classes/uni.xml").getPath();
//        return "Hello";
    }
}
