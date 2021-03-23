/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableWebServices;

import java.io.File;
import java.util.Random;
import org.netbeans.xml.schema.timetable.Administrator;
import static timetableWebServices.studentWrapper.student;



/**
 *
 * @author tom
 */
public class adminWrapper {
    String Filepath ="/Users/tom/NetBeansProjects/Timetable/timetablingWS/src/java/xmlData/admin/%s.txt";
    Administrator admin = new Administrator();
    
    private void load(String adminId){
        
       String s = String.format(Filepath, adminId);
       
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(admin.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            admin = (Administrator) unmarshaller.unmarshal(new java.io.File(s)); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
    }
    
    private synchronized void save(String adminId){
        
        String s = String.format(Filepath, adminId);
        
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(admin.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(admin, new File(s));
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
    
    public boolean login(String id, String password){
        try{
            load(id);
            return admin.getPassword().equals(password);
        }catch(Exception e){
           return false;
       }
    }
    
    public boolean checkSeed(String seed,String id){
        load(id);
        return student.getSeed().equals(seed);
    }
    
    public String updateSeed(String id){
        
        
        synchronized(admin){
            load(id);
            
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
        
        save(id);
        
        return admin.getSeed();
        }
        
    }
    
    public static void main(String [ ] args){
        adminWrapper aw = new adminWrapper();
        aw.login("ADA123", "Dogs123");
    }
}
