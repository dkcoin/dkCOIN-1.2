
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeResourceResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeResourceResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="action" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="name" type="{urn:dkcoin}requiredVarchar200" minOccurs="0"/>
 *         &lt;element name="internal_id" type="{urn:dkcoin}positiveInteger"/>
 *         &lt;element name="collection_name" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="count" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="messages" type="{urn:dkcoin}optionalVarchar500" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeResourceResult", propOrder = {
    "action",
    "name",
    "internalId",
    "collectionName",
    "count",
    "messages"
})
public class TypeResourceResult {

    @XmlElement(required = true)
    protected String action;
    protected String name;
    @XmlElement(name = "internal_id")
    protected long internalId;
    @XmlElement(name = "collection_name", required = true)
    protected String collectionName;
    protected Long count;
    protected List<String> messages;

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
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

    /**
     * Gets the value of the internalId property.
     * 
     */
    public long getInternalId() {
        return internalId;
    }

    /**
     * Sets the value of the internalId property.
     * 
     */
    public void setInternalId(long value) {
        this.internalId = value;
    }

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
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCount(Long value) {
        this.count = value;
    }

    /**
     * Gets the value of the messages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMessages() {
        if (messages == null) {
            messages = new ArrayList<String>();
        }
        return this.messages;
    }

}
