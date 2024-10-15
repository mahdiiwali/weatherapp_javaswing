package presentation;

import org.json.simple.JSONObject;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherApp extends JFrame {
    private JSONObject weatherData;
    private String username; // New field to store the username

    public WeatherApp(String username){ // Modified constructor to accept the username
        // Set the username
        super("weather app");
        this.username = username;

        //title

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,650);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        addComponents();
    }

    private void addComponents(){
        JTextField searchtextfield=new JTextField();
        searchtextfield.setBounds(15,15,351,45);
        searchtextfield.setFont(new Font("Dialog",Font.PLAIN, 24));
        add(searchtextfield);
        JLabel weatherimage=new JLabel(loadImage("src/assets/cloudy.png"));
        weatherimage.setBounds(100,125,450,217);
        add(weatherimage);
        JLabel temperaturetext=new JLabel("10 C");
        temperaturetext.setBounds(100,350,450,54);
        temperaturetext.setFont(new Font("Dialog",Font.BOLD,48));
        temperaturetext.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperaturetext);
        JLabel weatherconditiondesc =new JLabel("Cloudy");
        weatherconditiondesc.setBounds(100,405,450,36);
        weatherconditiondesc.setFont(new Font("Dialog",Font.PLAIN,32));
        weatherconditiondesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherconditiondesc);
        JLabel humidityimage=new JLabel(loadImage("src/assets/humidity.png"));
        humidityimage.setBounds(115,500,74,66);
        add(humidityimage);
        JLabel humiditytext=new JLabel("<html><b>Humidity</b>100%</html>");
        humiditytext.setBounds(190,500,85,55);
        humiditytext.setFont(new Font("Dialog",Font.PLAIN,16));
        add(humiditytext);
        JLabel windspeed=new JLabel(loadImage("src/assets/windspeed.png"));
        windspeed.setBounds(371,500,74,66);
        add(windspeed);
        JLabel windspeedtext=new JLabel("<html><b>Windspeed</b> 15KM/h </html>");
        windspeedtext.setBounds(450,500,85,55);
        windspeedtext.setFont(new Font("Dialog",Font.PLAIN,15));
        add(windspeedtext);
        JButton searchbutton=new JButton(loadImage("src/assets/search.png"));

        ImageIcon icon = new ImageIcon("src/assets/more-info.png");
        Image scaledImage = icon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JButton moreWeatherButton = new JButton(resizedIcon);
        moreWeatherButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        moreWeatherButton.setBounds(425,13,150,45);
        add(moreWeatherButton);
        searchbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchbutton.setBounds(375,13,47,45);

        moreWeatherButton.addActionListener(e->{
            String userInput= searchtextfield.getText();
            try {
                weatherData = WeatherAppapi.getWeatherData(userInput);
                new test(searchtextfield.getText());
            }catch (Exception ee){
                JOptionPane.showMessageDialog(null, "Please enter a valid country name !", "Error", JOptionPane.ERROR_MESSAGE);
                searchtextfield.setText("");
                searchtextfield.requestFocusInWindow();

            }
        });
        searchbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput= searchtextfield.getText();
                if (userInput.replaceAll("\\s" ,"" ).length() <=0 ){
                    return;
                }
                try {
                    weatherData = WeatherAppapi.getWeatherData(userInput);

                    String weathercondition = (String) weatherData.get("weather_condition");

                    if (weathercondition.equals("Clear")) {
                        weatherimage.setIcon(loadImage("src/assets/clear.png"));
                    } else if (weathercondition.equals("Cloudy")) {
                        weatherimage.setIcon(loadImage("src/assets/cloudy.png"));
                    } else if (weathercondition.equals("Rain")) {
                        weatherimage.setIcon(loadImage("src/assets/rain.png"));
                    } else if (weathercondition.equals("Snow")) {
                        weatherimage.setIcon(loadImage("src/assets/snow.png"));
                    } else {
                        throw new IllegalStateException("Unexpected value: " + weathercondition);
                    }
                    double temperature = (double) weatherData.get("temperature");
                    temperaturetext.setText(temperature + " C ");

                    weatherconditiondesc.setText(weathercondition);
                    long humidity = (long) weatherData.get("humidity");
                    humiditytext.setText("<html><b>Humidity</b> " + humidity + "%</html");

                    double windspeed = (double) weatherData.get("windspeed");
                    windspeedtext.setText("<html><b>Windspeed</b> " + windspeed + "KM </html");
                }catch (Exception ee){
                    JOptionPane.showMessageDialog(null, "Please enter a valid country name !", "Error", JOptionPane.ERROR_MESSAGE);
                    searchtextfield.setText("");
                    searchtextfield.requestFocusInWindow();

                }
            }
        });
        add(searchbutton);
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setBounds(115, 70, 400, 30);
        welcomeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel);
    }

    private ImageIcon loadImage(String resourcePath){
        try{
            BufferedImage image= ImageIO.read(new File(resourcePath));
            return new ImageIcon(image);
        } catch (IOException e) {
            System.out.println("hi3");
        }
        System.out.println("could not find resource");
        return null;
    }
}
