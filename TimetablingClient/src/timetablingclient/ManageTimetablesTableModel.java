/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablingclient;

import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import org.netbeans.xml.schema.timetable.Event;

/**
 *
 * @author N0682255
 */
public class ManageTimetablesTableModel extends AbstractTableModel {

    private List<Event> cList = new ArrayList();
    private String[] columnNames = {"Course", "Module", "Group", "Room Location", "Type", "Date", "Time", "Staff"};

    ManageTimetablesTableModel(List<Event> cList) {
        this.cList = cList;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return cList.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Event event = cList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return event.getCourseId();
            case 1:
                return event.getModuleName();
            case 2:
                return event.getGroup();
            case 3:
                return event.getRoomId();
            case 4:
                return event.getType();
            case 5:
                return event.getDate();
            case 6:
                return event.getTimeStamp();
            case 7:
                return event.getTutor();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
        }
        return null;
    }

    public Event getEvent(int index) {
        return cList.get(index);
    }

    public void addEvent(Event event) {
        if (!checkEvent(event)) {
            cList.add(event);
            List<Event> events = new ArrayList();
            events.add(event);
            addEvents(events, event.getCourseId());
        }
    }

    public void removeEvent(Event event) {
        cList.remove(event);
        removeEvents(event , event.getCourseId());
        
    }


    public boolean checkEvent(Event event) {
        Boolean repeat = false;
        String StartTime = event.getTimeStamp().split("-")[0].split(":")[0].trim();
        String EndTime = event.getTimeStamp().split("-")[1].split(":")[0].trim();

        if (Integer.parseInt(EndTime) > 19) {
            JOptionPane.showMessageDialog(null, "Class cannot end after 7");
            repeat = true;
        }

        for (Event e : cList) {
            String eStartTime = e.getTimeStamp().split("-")[0].split(":")[0].trim();
            String eEndTime = e.getTimeStamp().split("-")[1].split(":")[0].trim();
            if (e.getRoomId().equals(event.getRoomId()) && (eStartTime.equals(StartTime) || eEndTime.equals(EndTime)) && e.getDate().equals(event.getDate())) {
                JOptionPane.showMessageDialog(null, "A room cannot be booked twice for the same time");
                repeat = true;
            } else if (e.getTutor().equals(event.getTutor()) && (eStartTime.equals(StartTime) || eEndTime.equals(EndTime)) && e.getDate().equals(event.getDate())) {
                JOptionPane.showMessageDialog(null, "Staff member is busy during this time");
                repeat = true;
            } else if (e.getGroup().equals(event.getGroup()) && (eStartTime.equals(StartTime) || eEndTime.equals(EndTime)) && e.getDate().equals(event.getDate())) {
                JOptionPane.showMessageDialog(null, "Group is busy during this time");
                repeat = true;
            } else {
                //do nothing
            }
        }
        return repeat;
    }

    private static boolean addEvents(java.util.List<org.netbeans.xml.schema.timetable.Event> arg0, java.lang.String arg1) {
        timetablewebservices.Timetabling_Service service = new timetablewebservices.Timetabling_Service();
        timetablewebservices.Timetabling port = service.getTimetablingPort();
        return port.addEvents(arg0, arg1);
    }

    private static boolean removeEvents(org.netbeans.xml.schema.timetable.Event arg0, java.lang.String arg1) {
        timetablewebservices.Timetabling_Service service = new timetablewebservices.Timetabling_Service();
        timetablewebservices.Timetabling port = service.getTimetablingPort();
        return port.removeEvents(arg0, arg1);
    }

}
