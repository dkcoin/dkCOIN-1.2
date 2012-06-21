
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for typeResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeResource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{urn:dkcoin}requiredVarchar200"/>
 *         &lt;element name="internal_id" type="{urn:dkcoin}positiveInteger"/>
 *         &lt;element name="internal_url" type="{urn:dkcoin}optionalVarchar200" minOccurs="0"/>
 *         &lt;element name="resourcetype" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="collection_name" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="gene_id" type="{urn:dkcoin}positiveInteger" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="term_identifier" type="{urn:dkcoin}optionalVarchar200" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pubmed" type="{urn:dkcoin}typePublication" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="description" type="{urn:dkcoin}optionalVarchar2000" minOccurs="0"/>
 *         &lt;element name="internal_create_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeResource", propOrder = {
    "name",
    "internalId",
    "internalUrl",
    "resourcetype",
    "collectionName",
    "geneId",
    "termIdentifier",
    "pubmed",
    "description",
    "internalCreateDate"
})
public class TypeResource {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(name = "internal_id")
    protected long internalId;
    @XmlElement(name = "internal_url")
    protected String internalUrl;
    @XmlElement(required = true)
    protected String resourcetype;
    @XmlElement(name = "collection_name", required = true)
    protected String collectionName;
    @XmlElement(name = "gene_id", type = Long.class)
    protected List<Long> geneId;
    @XmlElement(name = "term_identifier")
    protected List<String> termIdentifier;
    protected List<TypePublication> pubmed;
    protected String description;
    @XmlElement(name = "internal_create_date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar internalCreateDate;

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
     * Gets the value of the internalUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalUrl() {
        return internalUrl;
    }

    /**
     * Sets the value of the internalUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalUrl(String value) {
        this.internalUrl = value;
    }

    /**
     * Gets the value of the resourcetype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourcetype() {
        return resourcetype;
    }

    /**
     * Sets the value of the resourcetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourcetype(String value) {
        this.resourcetype = value;
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
     * Gets the value of the geneId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the geneId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeneId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getGeneId() {
        if (geneId == null) {
            geneId = new ArrayList<Long>();
        }
        return this.geneId;
    }

    /**
     * Gets the value of the termIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the termIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTermIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTermIdentifier() {
        if (termIdentifier == null) {
            termIdentifier = new ArrayList<String>();
        }
        return this.termIdentifier;
    }

    /**
     * Gets the value of the pubmed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pubmed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPubmed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypePublication }
     * 
     * 
     */
    public List<TypePublication> getPubmed() {
        if (pubmed == null) {
            pubmed = new ArrayList<TypePublication>();
        }
        return this.pubmed;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the internalCreateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInternalCreateDate() {
        return internalCreateDate;
    }

    /**
     * Sets the value of the internalCreateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInternalCreateDate(XMLGregorianCalendar value) {
        this.internalCreateDate = value;
    }

}
