
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="displayname" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="urltemplate" type="{urn:dkcoin}optionalVarchar300" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeCollection", propOrder = {
    "name",
    "displayname",
    "urltemplate"
})
public class TypeCollection {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String displayname;
    protected String urltemplate;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the displayname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayname() {
        return displayname;
    }

    /**
     * Sets the value of the displayname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayname(String value) {
        this.displayname = value;
    }

    /**
     * Gets the value of the urltemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrltemplate() {
        return urltemplate;
    }

    /**
     * Sets the value of the urltemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrltemplate(String value) {
        this.urltemplate = value;
    }

}
