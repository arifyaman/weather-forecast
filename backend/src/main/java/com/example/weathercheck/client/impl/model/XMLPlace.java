package com.example.weathercheck.client.impl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class XMLPlace {
    private String name;
    private String phenomenon;
    private Integer tempMax;
    private Integer tempMin;

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "phenomenon")
    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    @XmlElement(name = "tempmax")
    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    @XmlElement(name = "tempmin")
    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }
}
