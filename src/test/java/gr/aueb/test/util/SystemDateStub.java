package gr.aueb.test.util;

import gr.aueb.mscis.sample.util.SimpleCalendar;
import gr.aueb.mscis.sample.util.SystemDate;

public class SystemDateStub {

    public static void setStub(SimpleCalendar stub) {
        SystemDate.setStub(stub);
    }
    
    public static void reset() {
        SystemDate.removeStub();
    }
    
}
