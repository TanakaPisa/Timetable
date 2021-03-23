
package timetableWebServices;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.xml.schema.timetable.Course;
import org.netbeans.xml.schema.timetable.Module;
import org.netbeans.xml.schema.timetable.Student;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.mmm
package timetableWebServices;

import org.netbeans.xml.schema.timetable.Module;

/**
 *
 * @author tom
 */
public class moduleWrapper {
    static String Filepath = "/Users/tom/NetBeansProjects/Timetable/timetablingWS/src/java/xmlData/%s/Modules/%s.txt";
   static Module module = new Module();
    
    private static void load(String moduleId, String courseId){
        
        String s = String.format(Filepath,courseId,moduleId);
       
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(module.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            module = (Module) unmarshaller.unmarshal(new java.io.File(s)); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
    
    public List<String> getModules(Course c){
        
        List<String> modules = new ArrayList();
        
        for(String id : c.getModuleIds()){
            load(id, c.getCourseID());
            
            modules.add(module.getModuleID()+"-"+module.getModuleName());
        }
        
        return modules;
    }
    
    
    public List<String> getGroups(Course c, String moduleId){
        
        List<String> groups = new ArrayList();
        
        load(moduleId, c.getCourseID());
        
        for(String s : module.getModuleGroupIds()){
        groups.add(s);
        }

        return groups;
    }
    
    public List<String> getStudents (String courseID , String  moduleID){
        load(moduleID, courseID);
        
        return module.getStudentIds();
    }
   
}
