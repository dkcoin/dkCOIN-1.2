
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeGene complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeGene">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gene_id" type="{urn:dkcoin}positiveInteger"/>
 *         &lt;element name="tax_id" type="{urn:dkcoin}positiveInteger"/>
 *         &lt;element name="symbol" type="{urn:dkcoin}requiredVarchar100"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeGene", propOrder = {
    "geneId",
    "taxId",
    "symbol"
})
public class TypeGene {

    @XmlElement(name = "gene_id")
    protected long geneId;
    @XmlElement(name = "tax_id")
    protected long taxId;
    @XmlElement(required = true)
    protected String symbol;

    /**
     * Gets the value of the geneId property.
     * 
     */
    public long getGeneId() {
        return geneId;
    }

    /**
     * Sets the value of the geneId property.
     * 
     */
    public void setGeneId(long value) {
        this.geneId = value;
    }

    /**
     * Gets the value of the taxId property.
     * 
     */
    public long getTaxId() {
        return taxId;
    }

    /**
     * Sets the value of the taxId property.
     * 
     */
    public void setTaxId(long value) {
        this.taxId = value;
    }

    /**
     * Gets the value of the symbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymbol(String value) {
        this.symbol = value;
    }

}
