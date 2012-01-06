
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeResourceTypesResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeResourceTypesResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resource_type" type="{urn:dkcoin}typeResourceType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeResourceTypesResults", propOrder = {
    "resourceType"
})
public class TypeResourceTypesResults {

    @XmlElement(name = "resource_type", required = true)
    protected List<TypeResourceType> resourceType;

    /**
     * Gets the value of the resourceType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeResourceType }
     * 
     * 
     */
    public List<TypeResourceType> getResourceType() {
        if (resourceType == null) {
            resourceType = new ArrayList<TypeResourceType>();
        }
        return this.resourceType;
    }

}
