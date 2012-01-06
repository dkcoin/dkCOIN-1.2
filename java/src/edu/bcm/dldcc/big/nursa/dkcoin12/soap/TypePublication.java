
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typePublication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typePublication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pubmed_id" type="{urn:dkcoin}positiveInteger"/>
 *         &lt;element name="citation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="title" type="{urn:dkcoin}requiredVarchar100" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typePublication", propOrder = {
    "pubmedId",
    "citation",
    "title"
})
public class TypePublication {

    @XmlElement(name = "pubmed_id")
    protected long pubmedId;
    protected Boolean citation;
    protected String title;

    /**
     * Gets the value of the pubmedId property.
     * 
     */
    public long getPubmedId() {
        return pubmedId;
    }

    /**
     * Sets the value of the pubmedId property.
     * 
     */
    public void setPubmedId(long value) {
        this.pubmedId = value;
    }

    /**
     * Gets the value of the citation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCitation() {
        return citation;
    }

    /**
     * Sets the value of the citation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCitation(Boolean value) {
        this.citation = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

}
