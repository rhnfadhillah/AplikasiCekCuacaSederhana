
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
    
    private void addRowToTable(String city, String weather, String temperature) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime = now.format(formatter);

        DefaultTableModel model = (DefaultTableModel) tabelCuaca.getModel();
        model.addRow(new Object[]{city, weather, temperature, formattedDateTime});
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
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelCuaca = new javax.swing.JTable();

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

        jButton1.setText("Simpan");

        tabelCuaca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Kota", "Cuaca", "Suhu", "Tanggal & Waktu"
            }
        ));
        jScrollPane1.setViewportView(tabelCuaca);

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtKota)
                                .addComponent(comboFavorit, 0, 192, Short.MAX_VALUE)))))
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
                    .addComponent(jButton1))
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
            addRowToTable(city, description, temperature);
        // Add city to the JTable if it doesn't already exist
    } catch (Exception e) {
        e.printStackTrace(); // Print the stack trace for debugging
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

    }
    }//GEN-LAST:event_btnCekActionPerformed
    
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
    private javax.swing.JComboBox<String> comboFavorit;
    private javax.swing.JButton jButton1;
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
