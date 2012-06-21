
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeTerm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeTerm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identifier" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="name" type="{urn:dkcoin}requiredVarchar100"/>
 *         &lt;element name="ontology" type="{urn:dkcoin}requiredVarchar100"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeTerm", propOrder = {
    "identifier",
    "name",
    "ontology"
})
public class TypeTerm {

    @XmlElement(required = true)
    protected String identifier;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String ontology;

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifier(String value) {
        this.identifier = value;
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
     * Gets the value of the ontology property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOntology() {
        return ontology;
    }

    /**
     * Sets the value of the ontology property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOntology(String value) {
        this.ontology = value;
    }

}
