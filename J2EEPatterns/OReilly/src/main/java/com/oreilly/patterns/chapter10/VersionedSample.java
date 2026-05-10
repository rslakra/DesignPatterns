// Example 10-8: VersionedSample.java

package com.oreilly.patterns.chapter10;

public class VersionedSample implements Versioned {
    private String name;
    private String address;
    private String city;
    private long pkey = -1;
    
    private long version = 1;
   
    public VersionedSample(long primaryKey) {
        pkey = primaryKey;
    }
 
    public long getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        version++;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        version++;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        version++;
    }
    
    public boolean equalsVersion(Object o) {
        if (this == o) return true;
        if (!(o instanceof VersionedSample)) return false;

        final VersionedSample versionedSample = (VersionedSample) o;
        if(versionedSample.pkey != this.pkey) return false;

        if (version != versionedSample.version) return false;
        if (address != null ? !address.equals(versionedSample.address) :   
            versionedSample.address != null) return false;
        if (city != null ? !city.equals(versionedSample.city) : 
            versionedSample.city != null) return false;
        if (name != null ? !name.equals(versionedSample.name) :
            versionedSample.name != null) return false;

        return true;
    }
}

