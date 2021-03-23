/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableWebServices;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.xml.schema.timetable.Course;
import org.netbeans.xml.schema.timetable.Courses;

/**
 *
 * @author tom
 */
public class courseWrapper {
    String Filepath = "/Users/tom/NetBeansProjects/Timetable/timetablingWS/src/java/xmlData/allCourses.txt";
    Courses courses = new Courses();
    
    private void load(){
       
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(courses.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            courses = (Courses) unmarshaller.unmarshal(new java.io.File(Filepath)); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
   
    public List<String> getCourseIds(){
        load();
        
        List<String> ids = new ArrayList();
        
        for(Course c : courses.getCourseCollection()){
            ids.add(c.getCourseID());
        }
        
        return ids;
    }
    
    
    public Course getCourse(String courseId){
        load();
        
        for(Course c : courses.getCourseCollection()){
            if(c.getCourseID().equals(courseId)){
                return c;
            }
        }
        return null;
    }
    
    public List<String> getStaffIds(String courseId){
        load();
        
        List<String> ids = new ArrayList();
        
        for(Course c : courses.getCourseCollection()){
            if(c.getCourseID().equals(courseId))
                return c.getStaffIds();
        }
        
        return null;
    }
    
}
