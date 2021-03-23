/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableWebServices;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.netbeans.xml.schema.timetable.Course;
import org.netbeans.xml.schema.timetable.Module;
import org.netbeans.xml.schema.timetable.Staff;
import static timetableWebServices.studentWrapper.student;

/**
 *
 * @author tomwe
 */
public class staffWrapper {
    String Filepath ="/Users/tom/NetBeansProjects/Timetable/timetablingWS/src/java/xmlData/%s/staffMembers/%s.txt";
    static Staff staff = new Staff();
    
    private void load(String courseId, String staffId){
        
       String s = String.format(Filepath,courseId,staffId);
       
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(staff.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            staff = (Staff) unmarshaller.unmarshal(new java.io.File(s)); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
    private synchronized void save(String staffId, String courseId){
        
        String s = String.format(Filepath,courseId, staffId);
        
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(staff.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(staff, new File(s));
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
    
    public List<String> getStaff(Course c){
        
        List<String> staffInfo = new ArrayList();
        
        for(String s : c.getStaffIds()){
            load(c.getCourseID(),s);
            
            staffInfo.add(staff.getStaffID()+"-"+staff.getStaffName());
        }
        
        return staffInfo;
    }
    
    
    public String getStaffInfo(Course c , String staffId){
        load(c.getCourseID(), staffId);
        
        return staff.getStaffEmail() +"-"+ staff.getStaffOffice();
    }
    
    public void updateStaffRequest(String courseId, String staffId, String requestId){
        
        load(courseId, staffId);
        staff.getPendingRequestsIds().add(requestId);
        save(staffId,courseId);
        save(staffId, courseId);
    }
    
   public boolean login(String id, String password){
       try{
            load("cmpsci", id);
            return staff.getPassword().equals(password);
       }catch(Exception e){
           return false;
       }
   }
   
   public boolean checkStaffSeed(String seed,String courseId,String id){
        load(courseId, id);
        return staff.getSeed().equals(seed);
    }
    
    public String updateSeed(String courseId,String id){
        
        
        synchronized(staff){
            load(courseId, id);
            
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            
            String generatedString = buffer.toString();
        
        
        staff.setSeed(generatedString);
        
        save(id,courseId);
        return staff.getSeed();
        }
    }
    
    public void addEvent(String eventId, String courseId, String staffId){
        synchronized(staff){
        load(courseId, staffId);
        
        staff.getTimetable().add(eventId);
        save(staffId,courseId);
        }
    }
    public void removeEvent(String eventId, String courseId, String staffId){
        synchronized(staff){
        load(courseId, staffId);
        
        staff.getTimetable().remove(eventId);
        save(staffId,courseId);
        }
    }
    
    public List<String> getEvents(String id, String courseId){
        load(courseId, id);
        return staff.getTimetable();
    }
    
    public String getStaffLoginInfo(String id){
        load("cmpsci", id);
        return staff.getStaffID()+":"+staff.getCourse()+":"+staff.getSeed();
        
    }
    
    
    public static void main(String [ ] args){
        staffWrapper sw = new staffWrapper();
        courseWrapper cw = new courseWrapper();
        sw.getStaff(cw.getCourse("cmpsci"));
    }
}
