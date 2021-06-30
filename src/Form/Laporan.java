package Form;

import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Laporan extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    String id_admin;
    String id_barang;
    String jenis_lap;
    String periode;
    String tanggal;

    public Laporan() {
        initComponents();
        datatable();
        this.setLocationRelativeTo(null);
    }
    
    protected void datatable(){
    Object[] Baris={"ID Laporan","ID Admin","ID Transaksi","Jenis Laporan","Periode Laporan","Total Harga"};
    tabmode = new DefaultTableModel(null,Baris);
    String cariitem=txtcari.getText();
    try {
    String sql = "Select * from laporan where id_laporan like '%"+cariitem+"%' or periode_laporan like '%"+cariitem+"%' order by id_laporan asc";
    Statement stat = conn.createStatement();
    ResultSet hasil = stat.executeQuery(sql);
    while (hasil.next()){
        tabmode.addRow(new Object[]{
            hasil.getString(1),
            hasil.getString(2),
            hasil.getString(3),
            hasil.getString(4),
            hasil.getString(5),
            hasil.getString(6)
        });
    }
    tbllaporan.setModel(tabmode);
    } catch (Exception e){
        JOptionPane.showConfirmDialog(null,"data gagal dipanggil"+e);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtcari = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbllaporan = new javax.swing.JTable();
        bcari = new javax.swing.JButton();
        cetakpembelian = new javax.swing.JButton();
        bexit = new javax.swing.JButton();
        cetakpenjualan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbPeriode = new javax.swing.JComboBox<>();
        header1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });

        tbllaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbllaporan);

        bcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/rsz_icons8-search-26.png"))); // NOI18N
        bcari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });
        bcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bcariKeyPressed(evt);
            }
        });

        cetakpembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/rsz_icons8-print-file-26.png"))); // NOI18N
        cetakpembelian.setText("Cetak Laporan Pembelian");
        cetakpembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakpembelianActionPerformed(evt);
            }
        });

        bexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/rsz_icons8-close-window-26.png"))); // NOI18N
        bexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexitActionPerformed(evt);
            }
        });

        cetakpenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/rsz_icons8-print-file-26.png"))); // NOI18N
        cetakpenjualan.setText("Cetak Laporan Penjualan");
        cetakpenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakpenjualanActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Laporan");

        jLabel4.setText("Periode Laporan");

        jLabel5.setText(":");

        cbPeriode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(544, 544, 544)
                .addComponent(bexit, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cetakpembelian)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cetakpenjualan))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bexit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bcari, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(cbPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cetakpembelian)
                    .addComponent(cetakpenjualan))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        header1.setBackground(new java.awt.Color(0, 102, 255));
        header1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setBackground(new java.awt.Color(0, 153, 0));
        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("VIO PHOTOCOPY");

        jLabel19.setBackground(new java.awt.Color(0, 153, 0));
        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Jl.Toyogiri Sel. No.87, Jatimulya");

        jLabel20.setBackground(new java.awt.Color(0, 153, 0));
        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Kec.Tambun Sel, Kab.Bekasi");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/lg.png"))); // NOI18N

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addGroup(header1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel20)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        datatable();
    }//GEN-LAST:event_txtcariKeyPressed

    private void bexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexitActionPerformed
        dispose();
    }//GEN-LAST:event_bexitActionPerformed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable();
    }//GEN-LAST:event_bcariActionPerformed

    private void bcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bcariKeyPressed
        if (evt.getKeyCode()== KeyEvent.VK_ENTER){
            datatable();
        }
    }//GEN-LAST:event_bcariKeyPressed

    private void cetakpembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakpembelianActionPerformed
        try {
            String Periode = cbPeriode.getSelectedItem().toString();
            HashMap parameter = new HashMap();
            parameter.put("periode", Periode);
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/db_pencatatan","root","");
            File file = new File("src/iReport/iReportpembelian.jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, connect);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        }catch(Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Data tidak dapat di cetak !!!"+"\n"+e.getMessage(), "Cetak data", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cetakpembelianActionPerformed

    private void cetakpenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakpenjualanActionPerformed
          try{
            String Periode = cbPeriode.getSelectedItem().toString();
            String path="src/iReport/iReportpenjualan.jasper";
            HashMap parameter = new HashMap();
            parameter.put("periode", Periode);
            JasperPrint print = JasperFillManager.fillReport(path, parameter, conn);
            JasperViewer.viewReport(print, false);
        }catch(Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Data tidak dapat di cetak !!!"+"\n"+e.getMessage(), "Cetak data", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cetakpenjualanActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcari;
    private javax.swing.JButton bexit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbPeriode;
    private javax.swing.JButton cetakpembelian;
    private javax.swing.JButton cetakpenjualan;
    private javax.swing.JPanel header1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbllaporan;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
