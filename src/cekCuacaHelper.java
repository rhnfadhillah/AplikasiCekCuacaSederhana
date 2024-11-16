
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import org.json.JSONObject;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rhnfa
 */
public class cekCuacaHelper {
    private static final String API_KEY = "c9d53802f7ee3305524423bcd965a7e1";
    private static final String  WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s";
    private static HashMap<String, Integer> citySearchCount = new HashMap<>();
    private static JComboBox<String> comboFavorit = new JComboBox<>();

    public static String getWeather(String city) throws Exception {
        updateCitySearchCount(city);
        String urlString = String.format(WEATHER_URL, city, API_KEY);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        JSONObject weatherJson = new JSONObject(content.toString());
        String weatherDescription = weatherJson.getJSONArray("weather").getJSONObject(0).getString("description");
        double temperature = weatherJson.getJSONObject("main").getDouble("temp");

        // Mengubah deskripsi cuaca ke dalam bahasa Indonesia
        String weather = translateWeatherDescription(weatherDescription);

        return String.format("Cuaca: %s, Suhu: %.2f °C", weather, temperature);
    }
    
     static String translateWeatherDescription(String description) {
        switch (description.toLowerCase()) {
            case "clear sky":
                return "Langit cerah";
            case "few clouds":
                return "Sedikit berawan";
            case "scattered clouds":
                return "Awan tersebar";
            case "broken clouds":
                return "Awan terputus";
            case "overcast clouds":
                return "Awan mendung";
            case "shower rain":
                return "Hujan gerimis";
            case "light rain":
                return "Hujan ringan";
            case "rain":
                return "Hujan";
            case "thunderstorm":
                return "Badai petir";
            case "snow":
                return "Salju";
            case "mist":
            case "haze" :
                return "Kabut";
            default:
                return description; // jika tidak ada terjemahan yang cocok, gunakan deskripsi asli
        }
    }
     
     public static String getWeatherImagePath(String description) {
        String path;
        switch (description) {
            case "Langit cerah":
                path = "/images/clearSky.png";
                break;
            case "Sedikit berawan":
                path = "/images/fewClouds.png";
                break;
            case "Awan tersebar":
            case "Awan terputus":
                path = "/images/scatteredClouds.png";
                break;
            case "Awan mendung":
                path = "/images/overcast.png";
                break;
            case "Hujan gerimis":
                path = "/images/showerRain.png";
                break;
            case "Hujan Ringan":
            case "Hujan":
                path = "/images/rain.png";
                break;
            case "Badai Petir":
                path = "/images/thunderstorm.png";
                break;
            case "Salju":
                path = "/images/snow.png";
                break;
            case "Kabut":
                path = "/images/mist.png";
                break;
            default:
                path = "/images/default.png"; // Default image if no match found
                break;
        }
        return path;
    }
     
    static void updateCitySearchCount(String city) {
       int count = citySearchCount.getOrDefault(city, 0);
        count++;
        citySearchCount.put(city, count);
        // If the city has been searched 3 times or more, add it to comboFavorit if not already present
        if (count == 3) {
            if (!isCityInComboBox(city)) {
                comboFavorit.addItem(city);
                System.out.println("Kota " + city + " telah dicari sebanyak 3 kali dan ditambahkan ke favorit.");
            }
        }
    }
    private static boolean isCityInComboBox(String city) {
        for (int i = 0; i < comboFavorit.getItemCount(); i++) {
            if (comboFavorit.getItemAt(i).equals(city)) {
                return true;
            }
        }
        return false;
    }
    
    public static void printFrequentlySearchedCities() {
        System.out.println("Kota yang sering dicari:");
        for (String city : citySearchCount.keySet()) {
            if (citySearchCount.get(city) >= 3) {
                System.out.println(city + ": " + citySearchCount.get(city) + " kali");
            }
        }
    }
    
    public static JComboBox<String> getComboFavorit() {
        return comboFavorit;
    }
}

