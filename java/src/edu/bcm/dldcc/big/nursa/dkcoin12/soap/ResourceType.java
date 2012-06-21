
package edu.bcm.dldcc.big.nursa.dkcoin12.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resourceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="resourceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="antibody"/>
 *     &lt;enumeration value="adenovirus"/>
 *     &lt;enumeration value="mouse"/>
 *     &lt;enumeration value="esc"/>
 *     &lt;enumeration value="fgs"/>
 *     &lt;enumeration value="protocol"/>
 *     &lt;enumeration value="vector"/>
 *     &lt;enumeration value="bioimg"/>
 *     &lt;enumeration value="news"/>
 *     &lt;enumeration value="document"/>
 *     &lt;enumeration value="histology"/>
 *     &lt;enumeration value="pcr primer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "resourceType")
@XmlEnum
public enum ResourceType {

    @XmlEnumValue("antibody")
    ANTIBODY("antibody"),
    @XmlEnumValue("adenovirus")
    ADENOVIRUS("adenovirus"),
    @XmlEnumValue("mouse")
    MOUSE("mouse"),
    @XmlEnumValue("esc")
    ESC("esc"),
    @XmlEnumValue("fgs")
    FGS("fgs"),
    @XmlEnumValue("protocol")
    PROTOCOL("protocol"),
    @XmlEnumValue("vector")
    VECTOR("vector"),
    @XmlEnumValue("bioimg")
    BIOIMG("bioimg"),
    @XmlEnumValue("news")
    NEWS("news"),
    @XmlEnumValue("document")
    DOCUMENT("document"),
    @XmlEnumValue("histology")
    HISTOLOGY("histology"),
    @XmlEnumValue("pcr primer")
    PCR_PRIMER("pcr primer");
    private final String value;

    ResourceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResourceType fromValue(String v) {
        for (ResourceType c: ResourceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
