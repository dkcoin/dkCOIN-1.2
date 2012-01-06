
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeDeleteResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeDeleteResource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="collection_name" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="internal_id" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="name" type="{urn:dkcoin}requiredVarchar200" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeDeleteResource", propOrder = {
    "collectionName",
    "internalId",
    "name"
})
public class TypeDeleteResource {

    @XmlElement(name = "collection_name", required = true)
    protected String collectionName;
    @XmlElement(name = "internal_id")
    protected Long internalId;
    protected String name;

    /**
     * Gets the value of the collectionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * Sets the value of the collectionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollectionName(String value) {
        this.collectionName = value;
    }

    /**
     * Gets the value of the internalId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInternalId() {
        return internalId;
    }

    /**
     * Sets the value of the internalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInternalId(Long value) {
        this.internalId = value;
    }

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

}
