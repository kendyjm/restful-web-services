package com.in28minutes.rest.webservices.restfulwebservices.filtered.staticaly;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
see @JsonIgnore  on field3Password
 */
public class SomeBeanStaticalyFiltered {

    private String field1;
    private  String field2;

    @JsonIgnore
    private  String field3Password;

    public SomeBeanStaticalyFiltered(String field1, String field2, String field3Password) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3Password = field3Password;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3Password() {
        return field3Password;
    }

    public void setField3Password(String field3Password) {
        this.field3Password = field3Password;
    }
}
