// Example 7-4: PatientDTO

package com.oreilly.patterns.chapter7;

import java.util.ArrayList;
import java.io.*;

public class PatientDTO implements Serializable {

    public long pat_no = -1;
    public String fname = null;
    public String lname = null;

    public ArrayList addresses = new ArrayList();

    public String getFirstName() {
        return fname;
    }

    public String getLastName() {
        return lname;
    }

    public void setFirstName(String newFirstName) {
        fname = newFirstName;
    }

    public void setLastName(String newLastName) {
        lname = newLastName;
    }
}
