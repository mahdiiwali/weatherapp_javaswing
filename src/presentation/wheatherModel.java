package presentation;

public class wheatherModel {

    private String day;

    private String temperature;
    private String description;
    private String humidity;
    private String speed;


    public wheatherModel(String day,String temperature ,String description, String humidity, String speed) {
        this.day = day;
        this.description = description;
        this.humidity = humidity;
        this.speed = speed;
        this.temperature=temperature;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getHumidity() {
        return humidity;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
