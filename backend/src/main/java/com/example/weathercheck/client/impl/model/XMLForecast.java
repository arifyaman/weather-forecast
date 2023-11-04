package com.example.weathercheck.client.impl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class XMLForecast {
    private String date;

    private XMLStatement day;
    private XMLStatement night;

    @XmlAttribute(name = "date")
    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(name = "day")
    public void setDay(XMLStatement day) {
        this.day = day;
    }

    @XmlElement(name = "night")
    public void setNight(XMLStatement night) {
        this.night = night;
    }
}
