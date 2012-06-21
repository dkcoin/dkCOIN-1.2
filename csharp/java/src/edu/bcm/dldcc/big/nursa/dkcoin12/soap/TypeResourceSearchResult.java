
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
 * <p>Java class for typeResourceSearchResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeResourceSearchResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resource_id" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="collection_id" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="name" type="{urn:dkcoin}requiredVarchar200"/>
 *         &lt;element name="internal_id" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="internal_url" type="{urn:dkcoin}optionalVarchar200" minOccurs="0"/>
 *         &lt;element name="created_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="modified_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="resourcetype_name" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="resourcetype_title" type="{urn:dkcoin}requiredVarchar200"/>
 *         &lt;element name="collection_name" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="collection_title" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="source_id" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="source_abbrev" type="{urn:dkcoin}optionalVarchar20"/>
 *         &lt;element name="source_name" type="{urn:dkcoin}optionalVarchar200"/>
 *         &lt;element name="description" type="{urn:dkcoin}optionalVarchar2000"/>
 *         &lt;element name="internal_create_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="term" type="{urn:dkcoin}typeTerm" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="publication" type="{urn:dkcoin}typePublication" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="gene" type="{urn:dkcoin}typeGene" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeResourceSearchResult", propOrder = {
    "resourceId",
    "collectionId",
    "name",
    "internalId",
    "internalUrl",
    "createdDate",
    "modifiedDate",
    "resourcetypeName",
    "resourcetypeTitle",
    "collectionName",
    "collectionTitle",
    "sourceId",
    "sourceAbbrev",
    "sourceName",
    "description",
    "internalCreateDate",
    "term",
    "publication",
    "gene"
})
public class TypeResourceSearchResult {

    @XmlElement(name = "resource_id")
    protected Long resourceId;
    @XmlElement(name = "collection_id")
    protected Long collectionId;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(name = "internal_id")
    protected Long internalId;
    @XmlElement(name = "internal_url")
    protected String internalUrl;
    @XmlElement(name = "created_date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "modified_date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifiedDate;
    @XmlElement(name = "resourcetype_name", required = true)
    protected String resourcetypeName;
    @XmlElement(name = "resourcetype_title", required = true)
    protected String resourcetypeTitle;
    @XmlElement(name = "collection_name", required = true)
    protected String collectionName;
    @XmlElement(name = "collection_title", required = true)
    protected String collectionTitle;
    @XmlElement(name = "source_id")
    protected Long sourceId;
    @XmlElement(name = "source_abbrev", required = true)
    protected String sourceAbbrev;
    @XmlElement(name = "source_name", required = true)
    protected String sourceName;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(name = "internal_create_date", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar internalCreateDate;
    protected List<TypeTerm> term;
    protected List<TypePublication> publication;
    protected List<TypeGene> gene;

    /**
     * Gets the value of the resourceId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * Sets the value of the resourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setResourceId(Long value) {
        this.resourceId = value;
    }

    /**
     * Gets the value of the collectionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCollectionId() {
        return collectionId;
    }

    /**
     * Sets the value of the collectionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCollectionId(Long value) {
        this.collectionId = value;
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
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the modifiedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the value of the modifiedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifiedDate(XMLGregorianCalendar value) {
        this.modifiedDate = value;
    }

    /**
     * Gets the value of the resourcetypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourcetypeName() {
        return resourcetypeName;
    }

    /**
     * Sets the value of the resourcetypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourcetypeName(String value) {
        this.resourcetypeName = value;
    }

    /**
     * Gets the value of the resourcetypeTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourcetypeTitle() {
        return resourcetypeTitle;
    }

    /**
     * Sets the value of the resourcetypeTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourcetypeTitle(String value) {
        this.resourcetypeTitle = value;
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
     * Gets the value of the collectionTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollectionTitle() {
        return collectionTitle;
    }

    /**
     * Sets the value of the collectionTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollectionTitle(String value) {
        this.collectionTitle = value;
    }

    /**
     * Gets the value of the sourceId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSourceId() {
        return sourceId;
    }

    /**
     * Sets the value of the sourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSourceId(Long value) {
        this.sourceId = value;
    }

    /**
     * Gets the value of the sourceAbbrev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceAbbrev() {
        return sourceAbbrev;
    }

    /**
     * Sets the value of the sourceAbbrev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceAbbrev(String value) {
        this.sourceAbbrev = value;
    }

    /**
     * Gets the value of the sourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * Sets the value of the sourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceName(String value) {
        this.sourceName = value;
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

    /**
     * Gets the value of the term property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the term property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeTerm }
     * 
     * 
     */
    public List<TypeTerm> getTerm() {
        if (term == null) {
            term = new ArrayList<TypeTerm>();
        }
        return this.term;
    }

    /**
     * Gets the value of the publication property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the publication property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPublication().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypePublication }
     * 
     * 
     */
    public List<TypePublication> getPublication() {
        if (publication == null) {
            publication = new ArrayList<TypePublication>();
        }
        return this.publication;
    }

    /**
     * Gets the value of the gene property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gene property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGene().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeGene }
     * 
     * 
     */
    public List<TypeGene> getGene() {
        if (gene == null) {
            gene = new ArrayList<TypeGene>();
        }
        return this.gene;
    }

}
