package edu.ntg.backend.dto;

import edu.ntg.backend.framework.database.NTGField;
import edu.ntg.backend.framework.database.NTGTable;

@NTGTable(name = "departments")
public class Department {

    public long getRecId() {
        return recId;
    }

    public void setRecId(long recId) {
        this.recId = recId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NTGField() private Long recId;
    @NTGField() private String name;
}
