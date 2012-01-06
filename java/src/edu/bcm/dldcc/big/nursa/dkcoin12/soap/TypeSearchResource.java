
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeSearchResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeSearchResource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="params" type="{urn:dkcoin}typeSearchResourceParams" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeSearchResource", propOrder = {
    "params"
})
public class TypeSearchResource {

    @XmlElement(required = true)
    protected List<TypeSearchResourceParams> params;

    /**
     * Gets the value of the params property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the params property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeSearchResourceParams }
     * 
     * 
     */
    public List<TypeSearchResourceParams> getParams() {
        if (params == null) {
            params = new ArrayList<TypeSearchResourceParams>();
        }
        return this.params;
    }

}
