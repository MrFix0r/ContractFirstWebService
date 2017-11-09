package com.bivgroup;

import com.bivgroup.ws.ContractFirstWebServicePort;
import com.bivgroup.ws.Report;
import com.bivgroup.ws.University;
import db.HibernateSessionFactory;
import db.UniversitiesEntity;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.bind.*;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "com.bivgroup.ws.ContractFirstWebServicePort")
//@HandlerChain(file="../../myHandler.xml")
public class ContractFirstWebServicePortImpl implements ContractFirstWebServicePort {

    @Resource
    private WebServiceContext wsctx;

//    Logger log = LogManager.getLogger("MyLog4j2");

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
//            log.info(e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getPath() {

        MessageContext mctx = wsctx.getMessageContext();
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");

        if ( userList != null)
        return userList.get(0).toString();
        else
            return "empty";
//        return this.getClass().getResource("/classes/uni.xml").getPath();
//        return "Hello";
    }

    @Override
    public String getDB() {
        System.out.println("Hibernate get from base ");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();


//        FacultiesEntity fac1 = new FacultiesEntity();
//        FacultiesEntity fac2 = new FacultiesEntity();
//        db2.UniversitiesEntity uni2 = new db2.UniversitiesEntity();
//        uni2.setName("FromGetDB");
//        uni2.getFaculties().add(fac1);
//        uni2.getFaculties().add(fac2);
//        session.persist(uni2);
//        session.flush();


        Transaction tx = session.beginTransaction();

        //Get All Unies
        Criteria criteria = session.createCriteria(UniversitiesEntity.class);
        StringBuilder sb = new StringBuilder();
        List<UniversitiesEntity> uniList = criteria.list();
        for(UniversitiesEntity uni : uniList){
            sb.append("ID = ").append(uni.getId()).append(", Name = ").append(uni.getName()).append(" ");
        }

        // Rollback transaction to avoid messing test data
        tx.commit();
        // closing hibernate resources
        session.close();
        return sb.toString();
    }
}
