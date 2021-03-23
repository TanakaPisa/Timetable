/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableWebServices;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import org.netbeans.xml.schema.timetable.Event;
import org.netbeans.xml.schema.timetable.Student;

/**
 *
 * @author tomwe
 */
public class studentWrapper {
    String Filepath ="/Users/tom/NetBeansProjects/Timetable/timetablingWS/src/java/xmlData/%s/students/%s.txt";
    static Student student = new Student();
    
    private void load(String studentId, String courseId){
        
       String s = String.format(Filepath,courseId, studentId);
       
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(student.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            student = (Student) unmarshaller.unmarshal(new java.io.File(s)); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
    }
    
    private synchronized void save(String studentId, String courseId){
        
        String s = String.format(Filepath,courseId, studentId);
        
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(student.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(student, new File(s));
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
    
    public boolean checkEquals(String toCheckId , String studentId){
        return toCheckId.equals(studentId);
    }
    
    public void addEvent(String eventId, String courseId, String studentId){
        synchronized(student){
        load(studentId, courseId);
        student.getTimetable().add(eventId);
        save(studentId,courseId);
        }
    }
    
    public void removeEvent(String eventId, String courseId, String studentId){
        synchronized(student){
        load(studentId, courseId);
        
        student.getTimetable().remove(eventId);
        save(studentId,courseId);
        }
    }
   
    public boolean checkStudentSeed(String seed,String courseId,String id){
        load(id,courseId);
        return student.getSeed().equals(seed);
    }
    
    public String updateSeed(String courseId,String id){
        
        
        synchronized(student){
            load(id,courseId);
            
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
        
        
        student.setSeed(generatedString);
        
        save(id,courseId);
        
        return student.getSeed();
        }
        
    }
    
    public boolean login(String id, String password){
        
        try{
            load(id,"cmpsci");
            return student.getPassword().equals(password);
        }catch(Exception e){
           return false;
       }
    }
    
    public String getStudent(String id){
        return student.getStudentID()+":"+student.getCourseId()+":"+student.getSeed();
        
    }
    
    public List<String> getEvents(String id, String courseId){
        load(id, courseId);
        return student.getTimetable();
    }
}
