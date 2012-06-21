
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeSearchResourceParams complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeSearchResourceParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="not_source_id" type="{urn:dkcoin}positiveInteger" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="source_id" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="not_source" type="{urn:dkcoin}requiredVarchar100" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="source" type="{urn:dkcoin}requiredVarchar100" minOccurs="0"/>
 *         &lt;element name="name" type="{urn:dkcoin}requiredVarchar200" minOccurs="0"/>
 *         &lt;element name="resourcetype" type="{urn:dkcoin}requiredVarchar100" minOccurs="0"/>
 *         &lt;element name="gene_id" type="{urn:dkcoin}positiveInteger" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="term_identifier" type="{urn:dkcoin}optionalVarchar200" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pubmed" type="{urn:dkcoin}typePublication" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="start_record" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="end_record" type="{urn:dkcoin}positiveInteger" minOccurs="0"/>
 *         &lt;element name="query" type="{urn:dkcoin}optionalVarchar500" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeSearchResourceParams", propOrder = {
    "notSourceId",
    "sourceId",
    "notSource",
    "source",
    "name",
    "resourcetype",
    "geneId",
    "termIdentifier",
    "pubmed",
    "startRecord",
    "endRecord",
    "query"
})
public class TypeSearchResourceParams {

    @XmlElement(name = "not_source_id", type = Long.class)
    protected List<Long> notSourceId;
    @XmlElement(name = "source_id")
    protected Long sourceId;
    @XmlElement(name = "not_source")
    protected List<String> notSource;
    protected String source;
    protected String name;
    protected String resourcetype;
    @XmlElement(name = "gene_id", type = Long.class)
    protected List<Long> geneId;
    @XmlElement(name = "term_identifier")
    protected List<String> termIdentifier;
    protected List<TypePublication> pubmed;
    @XmlElement(name = "start_record")
    protected Long startRecord;
    @XmlElement(name = "end_record")
    protected Long endRecord;
    protected String query;

    /**
     * Gets the value of the notSourceId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notSourceId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotSourceId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getNotSourceId() {
        if (notSourceId == null) {
            notSourceId = new ArrayList<Long>();
        }
        return this.notSourceId;
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
     * Gets the value of the notSource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notSource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNotSource() {
        if (notSource == null) {
            notSource = new ArrayList<String>();
        }
        return this.notSource;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
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

}
