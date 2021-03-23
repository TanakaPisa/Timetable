/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableWebServices;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.xml.schema.timetable.Administrator;
import org.netbeans.xml.schema.timetable.Course;
import org.netbeans.xml.schema.timetable.Courses;
import org.netbeans.xml.schema.timetable.Event;
import org.netbeans.xml.schema.timetable.Group;
import org.netbeans.xml.schema.timetable.Module;
import org.netbeans.xml.schema.timetable.Request;
import org.netbeans.xml.schema.timetable.Room;
import org.netbeans.xml.schema.timetable.Rooms;
import org.netbeans.xml.schema.timetable.Staff;
import org.netbeans.xml.schema.timetable.Student;

/**
 *
 * @author tom
 */
public class generator {
    
    public static void main(String [ ] args){
        
        Course newCourse = new Course();
        
        newCourse.setCourseID("cmpsci");
        newCourse.setCourseName("Computer Science");
        
        Module newModule = new Module();
        
        newModule.setModuleID("SOFT30161");
        newModule.setModuleName("Advanced Software Engineering");
        
        Module newModule1 = new Module();
        
        newModule1.setModuleID("SOFT30121");
        newModule1.setModuleName("Advanced Analysis & Design");
        
        Module newModule2 = new Module();
        
        newModule2.setModuleID("COMP30231");
        newModule2.setModuleName("Service Centric & Cloud Computing");
        
        Module newModule3 = new Module();
        
        newModule3.setModuleID("COMP30151");
        newModule3.setModuleName("Project");
        
        Module newModule4 = new Module();
        
        newModule4.setModuleID("ISYS30221");
        newModule4.setModuleName("Artificial Intelligence");
        
        Module newModule5 = new Module();
        
        newModule5.setModuleID("ISC027");
        newModule5.setModuleName("Interactive Media & Graphics");
        
        
        
        
        Student newStudent = new Student();
      
        newStudent.setStudentID("N0682255");
        newStudent.setPassword("password");
        newStudent.setStudentName("Tom Webb");
        
        Student newStudent1 = new Student();
      
        newStudent1.setStudentID("N0673230");
        newStudent1.setPassword("password");
        newStudent1.setStudentName("Lorna Byrnes");
        
        Student newStudent2 = new Student();
      
        newStudent2.setStudentID("N0690954");
        newStudent2.setPassword("password");
        newStudent2.setStudentName("Sarah Richardson");
        
        Student newStudent3 = new Student();
      
        newStudent3.setStudentID("N0727476");
        newStudent3.setPassword("password");
        newStudent3.setStudentName("Nabeel Mir");
        
        Student newStudent4 = new Student();
      
        newStudent4.setStudentID("N0719344");
        newStudent4.setPassword("password");
        newStudent4.setStudentName("Tanaka Pisa");
        
        Student newStudent5 = new Student();
      
        newStudent5.setStudentID("N0620500");
        newStudent5.setPassword("password");
        newStudent5.setStudentName("Zainab Olawoyin");
        
        Student newStudent6 = new Student();
      
        newStudent6.setStudentID("N0689061");
        newStudent6.setPassword("password");
        newStudent6.setStudentName("Zu Chaudhry");
        
        Student newStudent7 = new Student();
      
        newStudent7.setStudentID("N0669298");
        newStudent7.setPassword("password");
        newStudent7.setStudentName("Edwin Langly");
        
        Student newStudent8 = new Student();
      
        newStudent8.setStudentID("N0687265");
        newStudent8.setPassword("password");
        newStudent8.setStudentName("Ed Celella");
        
        
        
        newCourse.getModuleIds().add("SOFT30161");
        newCourse.getModuleIds().add("SOFT30121");
        newCourse.getModuleIds().add("COMP30231");
        newCourse.getModuleIds().add("COMP30151");
        newCourse.getModuleIds().add("ISYS30221");
        newCourse.getModuleIds().add("ISC027");
        
        newModule.getStudentIds().add("N0682255");
        newModule.getStudentIds().add("N0687265");
        
        newModule1.getStudentIds().add("N0682255");
        newModule1.getStudentIds().add("N0673230");
        newModule1.getStudentIds().add("N0727476");
        newModule1.getStudentIds().add("N0719344");
        newModule1.getStudentIds().add("N0620500");
        newModule1.getStudentIds().add("N0689061");
        newModule1.getStudentIds().add("N0687265");
        
        newModule2.getStudentIds().add("N0682255");
        newModule2.getStudentIds().add("N0673230");
        newModule2.getStudentIds().add("N0620500");
        newModule2.getStudentIds().add("N0689061");
        newModule2.getStudentIds().add("N0687265");
        
        newModule3.getStudentIds().add("N0682255");
        newModule3.getStudentIds().add("N0673230");
        newModule3.getStudentIds().add("N0690954");
        newModule3.getStudentIds().add("N0727476");
        newModule3.getStudentIds().add("N0719344");
        newModule3.getStudentIds().add("N0620500");
        newModule3.getStudentIds().add("N0689061");
        newModule3.getStudentIds().add("N0687265");
        
        newModule4.getStudentIds().add("N0682255");
        newModule4.getStudentIds().add("N0673230");
        newModule4.getStudentIds().add("N0690954");
        newModule4.getStudentIds().add("N0727476");
        newModule4.getStudentIds().add("N0719344");
        newModule4.getStudentIds().add("N0620500");
        newModule4.getStudentIds().add("N0689061");
        newModule4.getStudentIds().add("N0687265");
        
        
        newModule5.getStudentIds().add("N0690954");
        
        Staff newStaff = new Staff();
        newStaff.setStaffID("cmp3kingn");
        newStaff.setPassword("password");
        newStaff.setStaffEmail("cmp3kingn@my.ntu.ac.uk");
        newStaff.setStaffOffice("NHB206");
        newStaff.setStaffName("Nigel King");
        newStaff.setCourse("cmpsci");
        
        Staff newStaff1 = new Staff();
        newStaff1.setStaffID("cmp3lewisj");
        newStaff1.setPassword("password");
        newStaff1.setStaffEmail("cmp3lewisj@my.ntu.ac.uk");
        newStaff1.setStaffOffice("ERD115");
        newStaff1.setStaffName("James Lewis");
        newStaff1.setCourse("cmpsci");
        
        Staff newStaff2 = new Staff();
        newStaff2.setStaffID("cmp3ingdamb");
        newStaff2.setPassword("password");
        newStaff2.setStaffEmail("cmp3ingdamb@my.ntu.ac.uk");
        newStaff2.setStaffOffice("ERD117");
        newStaff2.setStaffName("Benjamin Ingdam");
        newStaff2.setCourse("cmpsci");
        
        
      
        List<Student> students = new ArrayList();
        students.add(newStudent);
        students.add(newStudent1);
        students.add(newStudent2);
        students.add(newStudent3);
        students.add(newStudent4);
        students.add(newStudent5);
        students.add(newStudent6);
        students.add(newStudent7);
        students.add(newStudent8);
        
        /*List<Staff> staff = new ArrayList();
        staff.add(newStaff);
        staff.add(newStaff1);
        staff.add(newStaff2);
        */
        newCourse.getStaffIds().add(newStaff.getStaffID());
        newCourse.getStaffIds().add(newStaff1.getStaffID());
        newCourse.getStaffIds().add(newStaff2.getStaffID());
        
        
        Courses courses = new Courses();
        courses.getCourseCollection().add(newCourse);
       
        
        Group newGroup = new Group();
        newGroup.setGroupID("SOFT30161-A");
        newGroup.getStudentIds().add("N0682255");
        newGroup.getStudentIds().add("N0687265");
        newGroup.setGroupSize(newGroup.getStudentIds().size());
        
        newModule.getModuleGroupIds().add(newGroup.getGroupID());
        
        
        //for(Staff s : staff){
        
        Room newRoom = new Room();
        newRoom.setBuilding("Mary Ann Evens");
        newRoom.setCapacity(120);
        newRoom.setRoomID("MAE001");
        newRoom.setType("Lecture");
        
        Room newRoom1 = new Room();
        newRoom1.setBuilding("Mary Ann Evens");
        newRoom1.setCapacity(60);
        newRoom1.setRoomID("MAE002");
        newRoom1.setType("Lecture");
        
        Room newRoom2 = new Room();
        newRoom2.setBuilding("Mary Ann Evens");
        newRoom2.setCapacity(25);
        newRoom2.setRoomID("MAE101");
        newRoom2.setType("Lab");
        
        Room newRoom3 = new Room();
        newRoom3.setBuilding("Mary Ann Evens");
        newRoom3.setCapacity(25);
        newRoom3.setRoomID("MAE102");
        newRoom3.setType("Lab");
        
        Room newRoom4 = new Room();
        newRoom4.setBuilding("Mary Ann Evens");
        newRoom4.setCapacity(30);
        newRoom4.setRoomID("MAE103");
        newRoom4.setType("Lab");
        
        Room newRoom5 = new Room();
        newRoom5.setBuilding("Mary Ann Evens");
        newRoom5.setCapacity(20);
        newRoom5.setRoomID("MAE104");
        newRoom5.setType("Lab");
        
        Room newRoom6 = new Room();
        newRoom6.setBuilding("Mary Ann Evens");
        newRoom6.setCapacity(2);
        newRoom6.setRoomID("MAE105");
        newRoom6.setType("Lab");
        
        Room newRoom7 = new Room();
        newRoom7.setBuilding("Mary Ann Evens");
        newRoom7.setCapacity(5);
        newRoom7.setRoomID("MAE106");
        newRoom7.setType("Lab");
        
        Room newRoom8 = new Room();
        newRoom8.setBuilding("Mary Ann Evens");
        newRoom8.setCapacity(30);
        newRoom8.setRoomID("MAE201");
        newRoom8.setType("Seminar");
        
        Room newRoom9 = new Room();
        newRoom9.setBuilding("Mary Ann Evens");
        newRoom9.setCapacity(25);
        newRoom9.setRoomID("MAE202");
        newRoom9.setType("Seminar");
        
        Room newRoom10 = new Room();
        newRoom10.setBuilding("Mary Ann Evens");
        newRoom10.setCapacity(3);
        newRoom10.setRoomID("MAE203");
        newRoom10.setType("Seminar");
        
        Room newRoom11 = new Room();
        newRoom11.setBuilding("Mary Ann Evens");
        newRoom11.setCapacity(6);
        newRoom11.setRoomID("MAE204");
        newRoom11.setType("Seminar");
        
        
        Rooms rooms = new Rooms();
        rooms.getRoomCollection().add(newRoom);
        rooms.getRoomCollection().add(newRoom1);
        rooms.getRoomCollection().add(newRoom2);
        rooms.getRoomCollection().add(newRoom3);
        rooms.getRoomCollection().add(newRoom4);
        rooms.getRoomCollection().add(newRoom5);
        rooms.getRoomCollection().add(newRoom6);
        rooms.getRoomCollection().add(newRoom7);
        rooms.getRoomCollection().add(newRoom8);
        rooms.getRoomCollection().add(newRoom9);
        rooms.getRoomCollection().add(newRoom10);
        rooms.getRoomCollection().add(newRoom11);
        
        
        /*Administrator admin = new Administrator();
        admin.setAdminID("ADA123");
        admin.setPassword("Dogs123");
        admin.setSeed("0sigidfsdo12");
        */
        
        Request r = new Request();
        r.setCourseID("cmpsci");
        r.setGroupID("");
        r.setModuleID("");
        r.setStaffID("");
        r.setStatus("");
        r.setRequest("");
        r.setRequestID("");
        
        Event e = new Event();
        
        
            try {                
                javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(rooms.getClass().getPackage().getName());
                javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(rooms, new File("/Users/Tanak/Documents/NetBeansProjects/Timetable/timetablingWS/src/java/xmlData/Rooms.txt"));
            } catch (javax.xml.bind.JAXBException ex) {
                // XXXTODO Handle exception
                java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
            }
        //}
        
        
        
      
    }
}
