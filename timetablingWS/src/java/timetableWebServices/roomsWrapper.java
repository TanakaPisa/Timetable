/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableWebServices;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.xml.schema.timetable.Room;
import org.netbeans.xml.schema.timetable.Rooms;

/**
 *
 * @author tomwe
 */
public class roomsWrapper {
     String Filepath = "/Users/tom/NetBeansProjects/Timetable/timetablingWS/src/java/xmlData/Rooms.txt";
    Rooms rooms = new Rooms();
    
    private void load(){
       
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(rooms.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            rooms = (Rooms) unmarshaller.unmarshal(new java.io.File(Filepath)); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
    }
   
    public List<String> getRooms(int capacity, String type){
        load();
        
        List<String> ids = new ArrayList();
        
        for(Room r : rooms.getRoomCollection()){
            if(r.getCapacity() >= capacity && r.getType().equals(type))
                ids.add(r.getRoomID());
        }
        
        return ids;
    }
}
