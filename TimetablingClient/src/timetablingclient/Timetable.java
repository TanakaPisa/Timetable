/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablingclient;

import java.util.*;
import javax.swing.table.*;
import org.netbeans.xml.schema.timetable.Event;
import org.netbeans.xml.schema.timetable.Request;

/**
 *
 * @author n0719344
 */
public class Timetable extends AbstractTableModel {

    private List<Event> cList = new ArrayList();
    private String[] columnNames = {"Day", "From", "To", "Room", "Lecturer", "Type", "Module Name", "Module Code", "Group"};

    Timetable(List<Event> cList) {
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
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        //Request request = cList.get(rowIndex);
        switch (columnIndex) {
            case 0:
            //return Event.Day();
            case 1:
            //return Event.From();
            case 2:
            //return Event.To();
            case 3:
            //return Event.Room();
            case 4:
            //return Event.Lecturer();
            case 5:
            //return Event.Type();
            case 6:
            //return Event.Module Name();
            case 7:
            //return Event.Module Code();
            case 8:
            //return Event.Group();
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
        }
        return null;
    }
}
