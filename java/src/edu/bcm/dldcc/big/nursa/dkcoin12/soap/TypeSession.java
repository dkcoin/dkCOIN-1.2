
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeSession complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeSession">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionkey" type="{urn:dkcoin}requiredVarchar100"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeSession", propOrder = {
    "sessionkey"
})
public class TypeSession {

    @XmlElement(required = true)
    protected String sessionkey;

    /**
     * Gets the value of the sessionkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionkey() {
        return sessionkey;
    }

    /**
     * Sets the value of the sessionkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionkey(String value) {
        this.sessionkey = value;
    }

}
