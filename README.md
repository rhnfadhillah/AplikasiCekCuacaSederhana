
# Aplikasi Cek Cuaca sederhana   
Tugas 6 - Muhammad Raihan Fadhillah 2210010404

## Daftar Isi
- [Deskripsi](#deskripsi)
- [Prerequisites](#prerequisites)
- [Fitur](#fitur)
- [Cara Menjalankan](#cara-menjalankan)
- [Dokumentasi](#dokumentasi)
- [Screenshots](#screenshots)
- [Contoh Penggunaan](#contoh-penggunaan)
- [Pembuat](#pembuat)

## Deskripsi
Aplikasi ini memungkinkan pengguna untuk mengecek cuaca terkini berdasarkan nama kota.

Aplikasi ini menggunakan API dari OpenWeatherMap untuk mendapatkan informasi cuaca dan menampilkannya di dalam antarmuka pengguna berbasis Java Swing.

Selain itu, aplikasi ini juga menyediakan fitur untuk menyimpan dan memuat data cuaca dari file CSV serta memungkinkan pengguna untuk menandai kota favorit.

## Prerequisites
1. Java Development Kit (JDK) versi 8 atau lebih tinggi.
2. Internet connection untuk mengakses API OpenWeatherMap.

## Fitur   
- **Cek Cuaca**: Pengguna dapat memasukkan nama kota untuk mendapatkan informasi cuaca terkini.

- **Favorit Kota**: Setelah pengguna memasukkan kota sebanyak 3 kali, maka kota akan masuk ke dalam dropdown favorit.

- **Simpan Data Cuaca**: Menyimpan data cuaca ke dalam file CSV.

- **Muatan Data Cuaca**: Memuat data cuaca dari file CSV yang disimpan sebelumnya.

- **Gambar Cuaca**: Menampilkan ikon cuaca sesuai dengan kondisi cuaca kota yang dipilih.

## Cara Menjalankan
1. **Clone atau Download Repository** :
    - Clone repository ini atau download sebagai ZIP dan ekstrak.

2. **Buka Proyek di IDE** :
    - Buka IDE Anda dan import proyek Java yang telah diunduh.

3. **Jalankan Aplikasi**:
    - Jalankan kelas AplikasiCekCuacaSederhana dari IDE Anda.

  
## Dokumentasi
**Main Class (AplikasiCekCuacaSederhana.java)**
- Method tombol simpan
 ``` java
private void saveDataToCSV() {
        DefaultTableModel model = (DefaultTableModel) tabelCuaca.getModel();
        StringBuilder sb = new StringBuilder();

        // Add header
        for (int col = 0; col < model.getColumnCount(); col++) {
            sb.append(model.getColumnName(col)).append(",");
        }
        sb.setLength(sb.length() - 1); // Remove the last comma
        sb.append("\n");

        // Add rows
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                sb.append(model.getValueAt(row, col)).append(",");
            }
            sb.setLength(sb.length() - 1); // Remove the last comma
            sb.append("\n");
        }

        try (FileWriter writer = new FileWriter("cuaca_data.csv")) {
            writer.write(sb.toString());
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke cuaca_data.csv", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        saveDataToCSV();
    }                                                   
 ```
- Method load data
``` java
private void loadDataFromCSV() {
        DefaultTableModel model = (DefaultTableModel) tabelCuaca.getModel();
        model.setRowCount(0); // Clear existing data

        try (BufferedReader br = new BufferedReader(new FileReader("cuaca_data.csv"))) {
            String line;
            br.readLine(); // Skip the header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                model.addRow(values);
            }
            JOptionPane.showMessageDialog(this, "Data berhasil dimuat dari cuaca_data.csv", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

 private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        loadDataFromCSV();
    }       
```
- Method tombol cek
```java
public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Get the selected city from the combo box
            String selectedCity = (String) comboFavorit.getSelectedItem();
            
            // Ensure the city is not "Pilih Kota" or empty before performing the action
            if (selectedCity != null && !selectedCity.equals("Pilih Kota") && !selectedCity.isEmpty()) {
                // Update txtKota with the selected city
                txtKota.setText(selectedCity);
                
                // Trigger the btnCek action programmatically
                btnCekActionPerformed(evt);
            }
        }

 private void addOrUpdateRowInTable(String city, String weather, String temperature) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime = now.format(formatter);

        DefaultTableModel model = (DefaultTableModel) tabelCuaca.getModel();
        int cityIndex = getCityIndexInTable(city);

        if (cityIndex != -1) { // If the city already exists in the table
            String existingDateTime = (String) model.getValueAt(cityIndex, 3);
            if (!existingDateTime.equals(formattedDateTime)) {
                model.setValueAt(weather, cityIndex, 1);
                model.setValueAt(temperature, cityIndex, 2);
                model.setValueAt(formattedDateTime, cityIndex, 3);
            }
        } else { // If the city does not exist in the table
            model.addRow(new Object[]{city, weather, temperature, formattedDateTime});
        }
    }
    
    private int getCityIndexInTable(String city) {
        DefaultTableModel model = (DefaultTableModel) tabelCuaca.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(city)) {
                return i;
            }
        }
        return -1;
    }

private void btnCekActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        String city = txtKota.getText().trim();
    if (city.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Masukkan nama kota", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    try {
        String weatherInfo = cekCuacaHelper.getWeather(city);
        if (weatherInfo == null) {
            throw new RuntimeException("Weather information is null");
        }
        labelCuaca.setText(weatherInfo);
        WeatherHelper.updateCitySearchCount(city);
        updateComboFavorit();
        // Get weather description for image path
        String description = weatherInfo.split(",")[0].split(":")[1].trim();
        String imagePath = cekCuacaHelper.getWeatherImagePath(description);
        java.net.URL imgURL = getClass().getResource(imagePath);
        if (imgURL != null) {
            labelImage.setIcon(new javax.swing.ImageIcon(imgURL));
        } else {
            System.err.println("Gambar ikon cuaca tidak ditemukan: " + imagePath);
        }
         // Extract temperature from weatherInfo (assuming the format includes temperature)
            String temperature = weatherInfo.split(",")[1].split(":")[1].trim();

            // Add data to table
            addOrUpdateRowInTable(city, description, temperature);
        // Add city to the JTable if it doesn't already exist
    } catch (Exception e) {
        e.printStackTrace(); // Print the stack trace for debugging
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

    }
    }                
```

**Helper Class (cekCuacaHelper.java)**
- Method Untuk cek cuaca dengan fetch API OpenWeatherMap
``` java
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
```

- Method terjemah deskripsi ke Bahasa Indonesia
``` java
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
```

- Method untuk menentukan path gambar cuaca
``` java
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
```

- Method untuk update perhitungan kota
``` java
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
```
## Screenshots
![Screenshot 2024-11-16 221556](https://github.com/user-attachments/assets/267d0fea-feac-40d7-b03f-8f0848996ea2)




## Contoh Penggunaan

1. **Masukkan Nama Kota**: Ketik nama kota yang ingin Anda cek cuacanya pada kolom Nama Kota.

2. **Cek Cuaca**: Klik tombol Cek Cuaca untuk mendapatkan informasi cuaca dan suhu terkini dari kota yang dimasukkan.

3. **Pilih Kota Favorit**: Pilih kota favorit dari dropdown Kota Favorit. Ketika Anda memilih kota favorit, aplikasi akan menampilkan cuaca terkini dari kota tersebut secara otomatis.

4. **Simpan Data Cuaca**: Klik tombol Simpan untuk menyimpan data cuaca yang ditampilkan ke dalam file cuaca_data.csv.

5. **Muatan Data Cuaca**: Klik tombol Load untuk memuat data cuaca yang sebelumnya disimpan dari file cuaca_data.csv.


## Pembuat

- Nama : Muhammad Raihan Fadhillah
- NPM : 2210010404

