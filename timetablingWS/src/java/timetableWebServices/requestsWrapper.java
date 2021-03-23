/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableWebServices;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.xml.schema.timetable.Request;
import org.netbeans.xml.schema.timetable.Requests;

/**
 *
 * @author n0620500
 */
public class requestsWrapper {
     String Filepath="/Users/tom/NetBeansProjects/Timetable/timetablingWS/src/java/xmlData/%s/allRequests.txt";
    Requests allRequests = new Requests();
    
    private synchronized void load(String courseId){
        
       String s = String.format(Filepath,courseId);
       
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(allRequests.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            allRequests = (Requests) unmarshaller.unmarshal(new java.io.File(s)); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
    
    
    
    private synchronized void save(String courseId){
        
        String s = String.format(Filepath,courseId);
        
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(allRequests.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(allRequests, new File(s));
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }

    public void saveRequest(List<Request> request, String courseId){
        load(courseId);
        allRequests.getRequestCollection().addAll(request);
        save(courseId);
    }
    
    public List<Request> loadAll(String courseId){
    
        load(courseId);
        List<Request> temp = new ArrayList();
        for(Request r : allRequests.getRequestCollection()){
            if(r.getStatus().equals("Pending")){
                temp.add(r);
            }
            else{
                
            }
        }
        
    return  temp;
    }
    
    public List<Request> getStaffRequest(String courseId, String staffId){ 
        List<Request> requests = new ArrayList();
        load(courseId);
        for(Request r: allRequests.getRequestCollection()){
            if(r.getStaffID().equals(staffId)){ 
                requests.add(r);
            }
                
        }
        
    
      return requests;  
    }
    
   public void respondToRequest(String courseId, String requestId, Boolean status ){
       List<Request> requests = new ArrayList();
       load(courseId);
        
       for(Request rq : allRequests.getRequestCollection()){
           if(rq.getRequestID().equals(requestId)){
               if(status){
                   rq.setStatus("Accepted");
               } else{
                   rq.setStatus("Denied");
               }
           }
        }
        save(courseId);
        
   }
   
   public static void main(String [ ] args){
        requestsWrapper rw = new requestsWrapper();
        rw.getStaffRequest("cmpsci", "cmp3kingn");
    }
}
    
