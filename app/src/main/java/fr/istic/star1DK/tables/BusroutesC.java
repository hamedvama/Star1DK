package fr.istic.star1DK.tables;

import fr.istic.star1DK.StarContract;

/**
 * Created by hamed on 21/11/2017.
 */
public class BusroutesC implements StarContract {

    private String short_name = BusRoutes.BusRouteColumns.SHORT_NAME;
    private String long_name = BusRoutes.BusRouteColumns.LONG_NAME;
    private String description = BusRoutes.BusRouteColumns.DESCRIPTION;
    private String type = BusRoutes.BusRouteColumns.TYPE;
    private String color = BusRoutes.BusRouteColumns.COLOR;
    private String text_color = BusRoutes.BusRouteColumns.TEXT_COLOR;

    public BusroutesC(){
    }

    public BusroutesC(String short_name, String long_name, String description, String type, String color, String text_color) {
        this.short_name = short_name;
        this.long_name = long_name;
        this.description = description;
        this.type = type;
        this.color = color;
        this.text_color = text_color;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

}
