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
 * @author N0682255
 */
public class facultyRequestTableModel extends AbstractTableModel {

    private List<Request> cList = new ArrayList();
    private String[] columnNames = {"Request ID", "Course"};

    facultyRequestTableModel(List<Request> cList) {
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
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Request request = cList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return request.getStaffID();
            case 1:
                return request.getCourseID();
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
        }
        return null;
    }
}
