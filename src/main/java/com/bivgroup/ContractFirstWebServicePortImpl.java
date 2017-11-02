package com.bivgroup;

import com.bivgroup.ws.ContractFirstWebServicePort;
import com.bivgroup.ws.Report;
import com.bivgroup.ws.University;
import db.HibernateSessionFactory;
import db.UniversitiesEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.jws.WebService;
import javax.xml.bind.*;
import java.io.StringWriter;
import java.util.List;

@WebService(endpointInterface = "com.bivgroup.ws.ContractFirstWebServicePort")
public class ContractFirstWebServicePortImpl implements ContractFirstWebServicePort {

    Logger log = LogManager.getLogger("MyLog4j2");

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
            log.info(e);
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

    @Override
    public String getDB() {
        System.out.println("Hibernate get from base ");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        //Get All Employees
        Criteria criteria = session.createCriteria(UniversitiesEntity.class);
        StringBuilder sb = new StringBuilder();
        List<UniversitiesEntity> empList = criteria.list();
        for(UniversitiesEntity emp : empList){
            sb.append("ID = ").append(emp.getId()).append(", Name = ").append(emp.getName());
        }

        // Rollback transaction to avoid messing test data
        tx.commit();
        // closing hibernate resources
        session.close();
        return sb.toString();
    }
}
