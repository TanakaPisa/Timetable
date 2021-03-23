/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableWebServices;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.xml.schema.timetable.Course;
import org.netbeans.xml.schema.timetable.Group;
import org.netbeans.xml.schema.timetable.Module;

/**
 *
 * @author tom
 */
public class groupWrapper {
    
    String Filepath ="/Users/tom/NetBeansProjects/Timetable/timetablingWS/src/java/xmlData/%s/Groups/%s.txt";
    Group group = new Group();
    
    private void load(String groupId, String courseId){
        
       String s = String.format(Filepath,courseId,groupId);
       
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(group.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            group = (Group) unmarshaller.unmarshal(new java.io.File(s)); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
    
    
    public int getSize(String groupId, String courseId){
        load(groupId, courseId);
        
        return group.getGroupSize();
    }
    
    public List<String> getStudents (String groupId, String courseId){
        load(groupId, courseId);
        
        return group.getStudentIds();
    }
      
}
