/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableWebServices;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.netbeans.xml.schema.timetable.Course;
import org.netbeans.xml.schema.timetable.Event;
import org.netbeans.xml.schema.timetable.Request;
import org.netbeans.xml.schema.timetable.Requests;
import org.netbeans.xml.schema.timetable.Staff;
import org.netbeans.xml.schema.timetable.Student;

/**
 *
 * @author tom
 */
@WebService(serviceName = "timetabling")
@Stateless()
public class timetabling {
    adminWrapper aw = new adminWrapper();
    groupWrapper gw = new groupWrapper();
    staffWrapper sw = new staffWrapper();
    studentWrapper stw = new studentWrapper();
    courseWrapper cw = new courseWrapper();
    moduleWrapper mw = new moduleWrapper();
    eventWrapper ew = new eventWrapper();
    roomsWrapper rw = new roomsWrapper();
    requestsWrapper rqw = new requestsWrapper();

    /*public static void main(String args[]) {
    
        
    }*/
    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getRequests")
    public List<Request> getRequests(String courseId){
        //TODO write your implementation code here:
        
        /*
            1. load request
            2. return list of all requests for a course to admin
        */
        
        return rqw.loadAll(courseId);
    }
    
    @WebMethod(operationName = "getAllEvents")
    public List<Event> getAllEvents(String courseId){
        //TODO write your implementation code here:
        
        /*
            1. load request
            2. return list of all requests for a course to admin
        */
        
        return ew.getAllEvents(courseId);
    }

    /**
     * Web service operation
     * @return 
     */

  
    
    @WebMethod(operationName = "getMyEvents")
    public List<Event> getEvents(String id, String dateFrom , String dateTo , String courseId){
        try {
            //TODO write your implementation code here:
            //get all events for a particular id
            
            
            return ew.getMyEvents(courseId, id, dateFrom, dateTo);
        } catch (ParseException ex) {
            
            Logger.getLogger(timetabling.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList();
        }
    }
    
    
        
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getSentRequests")
    public List<Request> getSentRequests(@WebParam(name = "id") String id, String courseId) {
        //TODO write your implementation code here:
        return rqw.getStaffRequest(courseId, id);
    }
    
    @WebMethod(operationName = "respondToRequest")
    public void respondToRequest(@WebParam(name = "id") String id, String courseId, Boolean decision) {
        //TODO write your implementation code here:
        /*
            1.load all requests
            2.find request.id == id
            3.set status = "Accepted" or "Denied"
            4.Save requests
        */
        
        
        rqw.respondToRequest(courseId, id, decision);
       
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendRequest")
    public boolean sendRequest(@WebParam(name = "newRequest") Request newRequest, String courseId) {
        //TODO write your implementation code here:
        //updates allrequest list and own staff members request list
        /* PSUDO
            1.from requestWrapper load all requests, add new request, save all requests
            2.from staffWrapper, load staffmember from request.getstaffID , 
            add request ID to thier request list and save
        */
        List<Request> temp = new ArrayList();
        temp.add(newRequest);
        rqw.saveRequest(temp, courseId);
        sw.updateStaffRequest(courseId, newRequest.getStaffID(), newRequest.getRequestID() );
        return true;
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkSeed")
    public boolean checkSeed(@WebParam(name = "seed") String seed, String id, String courseId) {
        //TODO write your implementation code here:
        if(id.startsWith("N")){
            return stw.checkStudentSeed(seed, courseId, id);
        }
        else if(id.startsWith("cmp")){
            return sw.checkStaffSeed(seed, courseId, id);
        }
        else{
            return aw.checkSeed(seed, id);
        }
    }
    
    @WebMethod(operationName = "updateSeed")
    public String updateSeed(String courseId, String id) {
        //TODO write your implementation code here:
        if(id.startsWith("N")){
            return stw.updateSeed(courseId, id);
        }
        else if(id.startsWith("cmp")){
            return sw.updateSeed(courseId, id);
        }
        else{
            return aw.updateSeed(id);
        }
    }
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCourseIds")
    public List<String> getCourseIds() {
        //TODO write your implementation code here:
        courseWrapper cw = new courseWrapper();
        return cw.getCourseIds();
    }
    
    @WebMethod(operationName = "getModules")
    public List<String> getModules(String courseId) {
        //TODO write your implementation code here:
        
        return mw.getModules(cw.getCourse(courseId));
        
    }
    
    @WebMethod(operationName = "getFacultyMembers")
    public List<String> getFacultyMembers(String courseId) {
        //TODO write your implementation code here:
        //returns a list of staff members for a particular course
        return sw.getStaff(cw.getCourse(courseId));
    }
    
    @WebMethod(operationName = "getFacultyMembersInfo")
    public String getFacultyMembersInfo(String courseId, String staffId) {
        //TODO write your implementation code here:
        //return staffmember email and office
        return sw.getStaffInfo(cw.getCourse(courseId), staffId);
    }
    
    @WebMethod(operationName = "getGroups")
    public List<String> getGroups(String courseId, String moduleId) {
        //TODO write your implementation code here:
        
        return mw.getGroups(cw.getCourse(courseId), moduleId);
    }
    
   
    
    @WebMethod(operationName = "getRooms")
    public List<String> getRooms(String groupID, String courseID, String type) {
        //TODO write your implementation code here:
       
        return rw.getRooms(gw.getSize(groupID, courseID), type);
    }
    
    @WebMethod(operationName = "addEvents")
    public boolean addEvents(List<Event> events, String courseId){
        //TODO write your implementation code here:
        //adds all events to course save file
        ew.saveEvents(events, courseId);
        return true;
    }
    
    @WebMethod(operationName = "modifyEvents")
    public boolean modifyEvent(Event e, String courseId){
        //TODO write your implementation code here:
        
        ew.modifyEvent(e, courseId);
        return true;
    }
    
    @WebMethod(operationName = "removeEvents")
    public boolean removeEvent(Event e, String courseId){
        //TODO write your implementation code here:
        
        ew.removeEvent(e, courseId);
        return true;
    }
    
    @WebMethod(operationName = "login")
    public boolean login(String id, String password){
        //TODO write your implementation code here:
        if(id.startsWith("N")){
            return stw.login(id, password);
        }
        else if(id.startsWith("cmp")){
            return sw.login(id, password);
        }
        else if (id.startsWith("ADA")){
            return aw.login(id, password);
        }
        else
            return false;
            
    }
    
    @WebMethod(operationName = "getStudent")
    public String getStudent(String studentId){
        return stw.getStudent(studentId);
    }
    
    @WebMethod(operationName = "Null")
    public Student Null(String studentId){
        return new Student();
    }
    
    @WebMethod(operationName = "getStaffLoginInfo")
    public String getStaffLoginInfo(String staffId){
        return sw.getStaffLoginInfo(staffId);
    }
    
    @WebMethod(operationName = "NullStaff")
    public Staff NullStaff(String studentId){
        return new Staff();
    }
    
    
}
