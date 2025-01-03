
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rhnfa
 */
public class AplikasiCekCuacaSederhana extends javax.swing.JFrame {
    private cekCuacaHelper WeatherHelper;
    /**
     * Creates new form AplikasiCekCuacaSederhana
     */
    public AplikasiCekCuacaSederhana() {
        initComponents();
        WeatherHelper = new cekCuacaHelper();
        comboFavorit.addActionListener(new java.awt.event.ActionListener() {
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
    });
    }
    
    private void updateComboFavorit() {
    // Clear current items and re-add them from the comboFavorit in cekCuacaHelper
    comboFavorit.removeAllItems();
    comboFavorit.addItem("Pilih Kota"); // Default option

    // Add cities from WeatherHelper's comboFavorit
    for (int i = 0; i < WeatherHelper.getComboFavorit().getItemCount(); i++) {
        comboFavorit.addItem(WeatherHelper.getComboFavorit().getItemAt(i));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKota = new javax.swing.JTextField();
        labelCuaca = new javax.swing.JLabel();
        btnCek = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        comboFavorit = new javax.swing.JComboBox<>();
        labelImage = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelCuaca = new javax.swing.JTable();
        btnLoad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aplikasi Cek Cuaca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel1.setText("Nama Kota :");

        labelCuaca.setText("Cuaca :");

        btnCek.setText("Cek Cuaca");
        btnCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekActionPerformed(evt);
            }
        });

        jLabel2.setText("Kota Favorit :");

        comboFavorit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kota" }));

        labelImage.setPreferredSize(new java.awt.Dimension(50, 50));

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        tabelCuaca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Kota", "Cuaca", "Suhu", "Tanggal & Waktu"
            }
        ));
        jScrollPane1.setViewportView(tabelCuaca);

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCuaca)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(btnCek))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLoad))
                            .addComponent(txtKota)
                            .addComponent(comboFavorit, 0, 192, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboFavorit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCek)
                    .addComponent(btnSimpan)
                    .addComponent(btnLoad))
                .addGap(22, 22, 22)
                .addComponent(labelCuaca)
                .addGap(18, 18, 18)
                .addComponent(labelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekActionPerformed
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
    }//GEN-LAST:event_btnCekActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        saveDataToCSV();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        loadDataFromCSV();
    }//GEN-LAST:event_btnLoadActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AplikasiCekCuacaSederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplikasiCekCuacaSederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplikasiCekCuacaSederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiCekCuacaSederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiCekCuacaSederhana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCek;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> comboFavorit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCuaca;
    private javax.swing.JLabel labelImage;
    private javax.swing.JTable tabelCuaca;
    private javax.swing.JTextField txtKota;
    // End of variables declaration//GEN-END:variables
}
