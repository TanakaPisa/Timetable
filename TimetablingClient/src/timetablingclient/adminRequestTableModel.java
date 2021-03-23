/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablingclient;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.netbeans.xml.schema.timetable.Request;

/**
 *
 * @author tom
 */
public class adminRequestTableModel extends AbstractTableModel {

    private List<Request> cList = new ArrayList();
    private String[] columnNames = {"Request ID", "courseID", "moduleID", "groupID", "staffID", "Status","Request"};

    adminRequestTableModel(List<Request> cList) {
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
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Request request = cList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return request.getRequestID();
            case 1:
                return request.getCourseID();
            case 2:
                return request.getModuleID();
            case 3:
                return request.getGroupID();
            case 4:
                return request.getStaffID();
            case 5:
                return request.getStatus();
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

    public String req(int index) {
        return cList.get(index).getRequest();
    }
}
