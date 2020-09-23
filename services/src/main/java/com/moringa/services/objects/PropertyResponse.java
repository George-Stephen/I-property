
package com.moringa.services.objects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyResponse {

    @SerializedName("properties")
    @Expose
    private List<Property> properties = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PropertyResponse() {
    }

    /**
     * 
     * @param properties
     */
    public PropertyResponse(List<Property> properties) {
        super();
        this.properties = properties;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

}
