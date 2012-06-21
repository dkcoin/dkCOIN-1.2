
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeSearchKeywordParams complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeSearchKeywordParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="start_record" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="end_record" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="query" type="{urn:dkcoin}requiredVarchar500" minOccurs="0"/>
 *         &lt;element name="type" type="{urn:dkcoin}optionalVarchar100" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeSearchKeywordParams", propOrder = {
    "startRecord",
    "endRecord",
    "query",
    "type"
})
public class TypeSearchKeywordParams {

    @XmlElement(name = "start_record")
    protected Long startRecord;
    @XmlElement(name = "end_record")
    protected Long endRecord;
    protected String query;
    protected String type;

    /**
     * Gets the value of the startRecord property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStartRecord() {
        return startRecord;
    }

    /**
     * Sets the value of the startRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStartRecord(Long value) {
        this.startRecord = value;
    }

    /**
     * Gets the value of the endRecord property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEndRecord() {
        return endRecord;
    }

    /**
     * Sets the value of the endRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEndRecord(Long value) {
        this.endRecord = value;
    }

    /**
     * Gets the value of the query property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the value of the query property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuery(String value) {
        this.query = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
