
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeStartSessionResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeStartSessionResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sessionkey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeStartSessionResults", propOrder = {
    "result",
    "sessionkey"
})
public class TypeStartSessionResults {

    @XmlElement(required = true)
    protected String result;
    @XmlElement(required = true)
    protected String sessionkey;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResult(String value) {
        this.result = value;
    }

    /**
     * Gets the value of the sessionkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionkey() {
        return sessionkey;
    }

    /**
     * Sets the value of the sessionkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionkey(String value) {
        this.sessionkey = value;
    }

}
