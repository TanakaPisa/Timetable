/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableWebServices;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;
import org.netbeans.xml.schema.timetable.AllEvents;
import org.netbeans.xml.schema.timetable.Event;

/**
 *
 * @author tom
 */
public class eventWrapper {
    String Filepath="/Users/tom/NetBeansProjects/Timetable/timetablingWS/src/java/xmlData/%s/allEvents.txt";
    AllEvents allEvents = new AllEvents();
    studentWrapper sw = new studentWrapper();
    staffWrapper staffwrap = new staffWrapper();
    adminWrapper aw = new adminWrapper();
    groupWrapper gw = new groupWrapper();
    moduleWrapper mw = new moduleWrapper();
    
    private synchronized void load(String courseId){
        
       String s = String.format(Filepath,courseId);
       
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(allEvents.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            allEvents = (AllEvents) unmarshaller.unmarshal(new java.io.File(s)); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
    
    
    
    private synchronized void save(String courseId){
        
        String s = String.format(Filepath,courseId);
        
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(allEvents.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(allEvents, new File(s));
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
    
    public List<Event> getAllEvents(String courseId){
        load(courseId);
        return allEvents.getAllEvents();
              
    }
    

    public void saveEvents(List<Event> events, String courseId){
        
        load(courseId);
        allEvents.getAllEvents().addAll(events);
        List<String> students = new ArrayList();
        for(Event e : events){
            System.out.println(e.getModuleId()+"-"+e.getGroup());
            if(!"".equals(e.getGroup())){
                students = gw.getStudents(e.getModuleId()+"-"+e.getGroup(), e.getCourseId());
            }
            else{
                students = mw.getStudents(e.getCourseId(), e.getModuleId());
            }
            System.out.println(students.isEmpty());
            for(String s : students){
                sw.addEvent(e.getEventId(), courseId, s);
                sw.updateSeed(courseId, s);
            }
            
            staffwrap.addEvent(e.getEventId(), courseId, e.getTutor());
            staffwrap.updateSeed(courseId, e.getTutor());
        }
        
        save(courseId);
    }
    
    public void modifyEvent(Event e, String courseId){
        load(courseId);
        for(Event event : allEvents.getAllEvents()){
            if(e.getEventId().equals(event.getEventId()))
                event = e;
        }
        save(courseId);
    }
    
    public void removeEvent(Event e, String courseId){
        load(courseId);
        
        for(int i = 0; i< allEvents.getAllEvents().size(); i++){
            if(e.getEventId().equals(allEvents.getAllEvents().get(i).getEventId())){
                List<String> students = gw.getStudents(e.getModuleId()+"-"+e.getGroup(), e.getCourseId());
                for(String s : students){
                    sw.removeEvent(e.getEventId(), courseId, s);
                    sw.updateSeed(courseId, s);
                }
            
                staffwrap.removeEvent(e.getEventId(), courseId, e.getTutor());
                staffwrap.updateSeed(courseId, e.getTutor());
        
                System.out.println("Removing");
                allEvents.getAllEvents().remove(i);
                
            }
        }
        
        save(courseId);
    }
    
    public List<Event> getMyEvents(String courseID, String ID, String dateFrom, String dateTo) throws ParseException{
        load(courseID);
        List<String> events = new ArrayList();
        if(ID.startsWith("N")){
            events = sw.getEvents(ID, courseID);
        }
        else{
            events = staffwrap.getEvents(ID, courseID);
        }
        List<Event> weeklyEvents = new ArrayList();
        
        for(Event e : allEvents.getAllEvents()){
            for(String s : events){
                if(s.equals(e.getEventId())){
                    Date dateEvent=new SimpleDateFormat("dd-MM-yyyy").parse(e.getDate());  
                    Date DateFrom=new SimpleDateFormat("dd-MM-yyyy").parse(dateFrom);  
                    Date DateTo= new SimpleDateFormat("dd-MM-yyyy").parse(dateTo);  
                    if(dateEvent.after(DateFrom) && dateEvent.before(DateTo)){
                        weeklyEvents.add(e);
                    }
                }
            }
        }
        
        return weeklyEvents;
    }
    
    public static void main(String [ ] args){
        
        try {
            eventWrapper ew = new eventWrapper();
            ew.getMyEvents("cmpsci", "N0682255", "10-02-2019", "17-02-2019");
        } catch (ParseException ex) {
            Logger.getLogger(eventWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
