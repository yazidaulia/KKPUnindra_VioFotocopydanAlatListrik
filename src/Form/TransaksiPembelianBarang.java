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

public class TransaksiPembelianBarang extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private UserID lk = new UserID();
    private DefaultTableModel tabmode;
    public String tanggal;
    String id_admin;
    String id_barang;
    String harga_barang;
    String idlap;
    String jenis_lap;
    String periode;
    int total_transaksi, jumlah_awal, jumlah;

    public TransaksiPembelianBarang() {
        initComponents();
        kosong();
        aktif();
        datatable();
        autonumber();
        this.setLocationRelativeTo(null);
    }
    
    public void itemTerpilih(){
        popupbarang2 pb = new popupbarang2();
        pb.PB = this;
        txtidb.setText(id_barang);
        txthargab.setText(harga_barang);
    }
    
    public void hitung(){
        int total = 0;
        for (int i =0; i< tbltransaksi.getRowCount(); i++){
            int amount = Integer.valueOf(tbltransaksi.getValueAt(i, 6).toString());
            total += amount;
        }
        txttotalh.setText(Integer.toString(total));
    }    
    
    protected void aktif(){
    txtqty.requestFocus();
    Object[] Baris = {"ID Admin","ID Transaksi","Tanggal Transaksi","ID Barang","Harga Barang","QTY","Total"};
        tabmode = new DefaultTableModel(null, Baris);
        tbltransaksi.setModel(tabmode);
    }
    
    protected void kosong(){
    txtida.setText(lk.getUserLogin());
    txtidt.setText("");
    txtidb.setText("");
    txthargab.setText("");
    txtqty.setText("");
    txttotalt.setText("");
    datetransaksi.setDateFormatString(tanggal);
}
    
    protected void datatable(){
    Object[] Baris={"ID Admin","ID Transaksi","Tanggal Transaksi","ID Barang","Harga Barang","QTY","Total"};
    tabmode = new DefaultTableModel(null,Baris);
}
    
    public void cetak(){
        try {
            String path="src/iReport/iReporttransaksi.jasper";
            HashMap parameter = new HashMap();
            parameter.put("id_transaksi", txtidt.getText());
            JasperPrint print = JasperFillManager.fillReport(path, parameter, conn);
            JasperViewer.viewReport(print, false);
        }catch(Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Data tidak dapat di cetak !!!"+"\n"+e.getMessage(), "Cetak data", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    protected void autonumber(){
        try {
            String sql = "SELECT id_transaksi from transaksi order by id_transaksi asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txtidt.setText("TRX-0001");
            while (rs.next()) {
                String no_transaksi = rs.getString("id_transaksi").substring(4);
                int AN = Integer.parseInt(no_transaksi) + 1;
                String Nol = "";
                
                if(AN<10)
                {Nol = "000";}
                else if(AN<100)
                {Nol = "00";}
                else if(AN<1000)
                {Nol = "0";}
                else if(AN<10000)
                {Nol = "";}
                
                txtidt.setText("TRX-" + Nol + AN);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Auto Number Gagal"+e);
        }
    }
    
    public void tambah_stok(){
        int t = tbltransaksi.getRowCount();
           for(int i=0; i < t ; i++)
           {
                String xkdb = (String)tbltransaksi.getValueAt(i, 3).toString();
                String xqty = (String)tbltransaksi.getValueAt(i, 5).toString();
                String sql = "UPDATE barang set stok = stok+'"+xqty+"' where id_barang = '"+xkdb+"'";
                try{
                    PreparedStatement stat = conn.prepareStatement(sql);
          
                    stat.executeUpdate();
                    txtidt.requestFocus();
                }
                catch (SQLException e){
                    JOptionPane.showMessageDialog(null, "Stok gagal diubah"+e);
                }   
           }
    }
    
    protected void autonumber_lap(){
        try {
            String sql = "SELECT id_laporan from laporan order by id_laporan asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String no_transaksi = rs.getString("id_laporan").substring(3);
                int AN = Integer.parseInt(no_transaksi) + 1;
                String Nol = "";
                
                if(AN<10)
                {Nol = "000";}
                else if(AN<100)
                {Nol = "00";}
                else if(AN<1000)
                {Nol = "0";}
                else if(AN<10000)
                {Nol = "";}
                
                idlap = "LP-" + Nol + AN;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Auto Number Gagal"+e);
        }
    }
    
    public void simpan_laporan(){
        int t = tbltransaksi.getRowCount();
        String xkdt1 = txtidt.getText();
           for(int i=0; i < t ; i++)
           {
               autonumber_lap();
               String xkda = (String)tbltransaksi.getValueAt(i, 0).toString();
               String xbulan = (String)tbltransaksi.getValueAt(i, 2).toString().substring(5,7);
               String xhrg = (String)tbltransaksi.getValueAt(i, 6).toString();
               jenis_lap = "Pembelian";
               total_transaksi = total_transaksi + Integer.parseInt(xhrg);
               //fungsi if
               if(xbulan.equalsIgnoreCase("01")){
                   periode = "Januari";
               }
               else if(xbulan.equalsIgnoreCase("02")){
                   periode = "Februari";
               }
               else if(xbulan.equalsIgnoreCase("03")){
                   periode = "Maret";
               }
               else if(xbulan.equalsIgnoreCase("04")){
                   periode = "April";
               }
               else if(xbulan.equalsIgnoreCase("05")){
                   periode = "Mei";
               }
               else if(xbulan.equalsIgnoreCase("06")){
                   periode = "Juni";
               }
               else if(xbulan.equalsIgnoreCase("07")){
                   periode = "Juli";
               }
               else if(xbulan.equalsIgnoreCase("08")){
                   periode = "Agustus";
               }
               else if(xbulan.equalsIgnoreCase("09")){
                   periode = "September";
               }
               else if(xbulan.equalsIgnoreCase("10")){
                   periode = "Oktober";
               }
               else if(xbulan.equalsIgnoreCase("11")){
                   periode = "November";
               }
               else{
                   periode = "Desember";
               }
                String sql = "insert into laporan (id_laporan,id_admin,id_transaksi,jenis_laporan,periode_laporan,total) values ('"+idlap+"','"+xkda+"','"+xkdt1+"','"+jenis_lap+"','"+periode+"','"+total_transaksi+"')";
                try{
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();;
                }
                catch (SQLException e){
                    JOptionPane.showMessageDialog(null, "Data gagal disimpan ke laporan penjualan karena "+e);
                }
           }
    }
    
    public void konversi(){
    String satuan_awal = cb_brg.getSelectedItem().toString();
        jumlah_awal = Integer.parseInt(txtqty.getText());
        if (satuan_awal == "Dus"){
            jumlah = jumlah_awal * 24;
        }else if(satuan_awal == "Kodi"){
            jumlah = jumlah_awal * 20;
        }else{
            jumlah = jumlah_awal *12;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbltransaksi = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtida = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtidb = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtidt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txttotalt = new javax.swing.JTextField();
        bcarib = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txttotalh = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txthargab = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtqty = new javax.swing.JTextField();
        btambah = new javax.swing.JButton();
        datetransaksi = new com.toedter.calendar.JDateChooser();
        cb_brg = new javax.swing.JComboBox<>();
        bsimpan = new javax.swing.JButton();
        bbatal = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bexit = new javax.swing.JButton();
        buttonRiwayat = new javax.swing.JButton();
        header1 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        tbltransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbltransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltransaksiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbltransaksi);

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel4.setText("ID Admin");

        jLabel5.setText(":");

        jLabel10.setText("Tanggal Transaksi");

        jLabel11.setText(":");

        jLabel12.setText("ID Barang");

        jLabel13.setText(":");

        jLabel6.setText("ID Transaksi");

        jLabel7.setText(":");

        jLabel16.setText("Total");

        jLabel17.setText(":");

        bcarib.setText("cari");
        bcarib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcaribActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Total Harga");

        jLabel14.setText("Harga Barang");

        jLabel15.setText(":");

        jLabel18.setText("QTY");

        jLabel19.setText(":");

        txtqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtqtyActionPerformed(evt);
            }
        });

        btambah.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btambah.setText("Tambah");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });

        cb_brg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pcs", "Dus", "Kodi", "Lusin" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel19))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17))
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txttotalt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtida, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txthargab))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtidb)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(bcarib))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtidt))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(datetransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txttotalh, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(92, 92, 92))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(btambah, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(txtida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(txtidt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(txttotalh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11))
                    .addComponent(datetransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bcarib)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtidb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txthargab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cb_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txttotalt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btambah)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtida, txtidb, txtidt});

        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/rsz_icons8-create-26.png"))); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        bbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/rsz_icons8-reset-26.png"))); // NOI18N
        bbatal.setText("Batal");
        bbatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });

        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/rsz_icons8-delete-file-26.png"))); // NOI18N
        bhapus.setText("Hapus");
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/rsz_icons8-close-window-26.png"))); // NOI18N
        bexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexitActionPerformed(evt);
            }
        });

        buttonRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/rsz_icons8-search-26.png"))); // NOI18N
        buttonRiwayat.setText("Riwayat Transaksi");
        buttonRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRiwayatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bsimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bbatal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bhapus)
                                .addGap(162, 162, 162)
                                .addComponent(buttonRiwayat)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bexit, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bexit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsimpan)
                    .addComponent(bbatal)
                    .addComponent(bhapus)
                    .addComponent(buttonRiwayat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        header1.setBackground(new java.awt.Color(0, 102, 255));
        header1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setBackground(new java.awt.Color(0, 153, 0));
        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("VIO PHOTOCOPY");

        jLabel27.setBackground(new java.awt.Color(0, 153, 0));
        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Jl.Toyogiri Sel. No.87, Jatimulya");

        jLabel28.setBackground(new java.awt.Color(0, 153, 0));
        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Kec.Tambun Sel, Kab.Bekasi");

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/lg.png"))); // NOI18N

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29)
                    .addGroup(header1Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel28)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tbltransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltransaksiMouseClicked
        int bar = tbltransaksi.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        txtida.setText(a);
        txtidt.setText(b);
        txthargab.setText(e);
        txtidb.setText(d);
        txtqty.setText(f);
        txttotalt.setText(g);
    }//GEN-LAST:event_tbltransaksiMouseClicked

    private void bexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexitActionPerformed
        dispose();
    }//GEN-LAST:event_bexitActionPerformed

    private void datetransaksiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datetransaksiPropertyChange
        if(datetransaksi.getDate()!=null){
            SimpleDateFormat format_tanggal = new SimpleDateFormat("yyyy-MM-dd");
            tanggal = format_tanggal.format(datetransaksi.getDate());
        }
    }//GEN-LAST:event_datetransaksiPropertyChange

    private void bcaribActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcaribActionPerformed
        popupbarang2 pbb = new popupbarang2();
        pbb.PB = this;
        pbb.setVisible(true);
        pbb.setResizable(false);
    }//GEN-LAST:event_bcaribActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        try{
            String sql = "insert into transaksi(id_admin, id_transaksi, tanggal, id_barang, harga_barang, qty, total) values (?,?,?,?,?,?,?)";
            PreparedStatement stat2 = conn.prepareStatement(sql);
            int t = tbltransaksi.getRowCount();
            for(int i=0; i < t ; i++)
            {
                String xkda = (String)tbltransaksi.getValueAt(i, 0).toString();
                String xkdt = (String)tbltransaksi.getValueAt(i, 1).toString();
                String xtgl = (String)tbltransaksi.getValueAt(i, 2).toString();
                String xkdb = (String)tbltransaksi.getValueAt(i, 3).toString();
                String xhrg = (String)tbltransaksi.getValueAt(i, 4).toString();
                String xqty = (String)tbltransaksi.getValueAt(i, 5).toString();
                String xttl = (String)tbltransaksi.getValueAt(i, 6).toString();

                stat2.setString(1, xkda);
                stat2.setString(2, xkdt);
                stat2.setString(3, xtgl);
                stat2.setString(4, xkdb);
                stat2.setString(5, xhrg);
                stat2.setString(6, xqty);
                stat2.setString(7, xttl);
                
                stat2.addBatch();
            }
            stat2.executeBatch();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            //method untuk save data ke laporan penjualan
            simpan_laporan();
            tambah_stok();
            cetak();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan karena "+e);
        }
        kosong();
        aktif();
        autonumber();
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        kosong();
        datatable();
        autonumber();
    }//GEN-LAST:event_bbatalActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"Apakah anda yakin ingin menghapus data ini ?","Konfirmasi dialog",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            int[] rows = tbltransaksi.getSelectedRows();
            for (int i =0; i<rows.length; i++){
                tabmode.removeRow(rows[i]);
            }
            autonumber();
            hitung();
            kosong();
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        try {
            String kodea = txtida.getText();
            String kodet = txtidt.getText();
            String idb = txtidb.getText();
            int harga = Integer.parseInt(txthargab.getText());
            //konversi mamang
            if(cb_brg.getSelectedItem() == "Pcs"){
                jumlah = Integer.parseInt(txtqty.getText());
            }else{
                konversi();
            }
            int total = Integer.parseInt(txttotalt.getText());
            SimpleDateFormat format_tanggal = new SimpleDateFormat("yyyy-MM-dd");
            tanggal = format_tanggal.format(datetransaksi.getDate());

            tabmode.addRow(new Object[]{kodea, kodet,tanggal,idb, harga, jumlah,total});
            tbltransaksi.setModel(tabmode);
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e);
        }
        txtida.setText("");
        txtidt.setText("");
        txtidb.setText("");
        datetransaksi.setDateFormatString(tanggal);
        txthargab.setText("");
        txtqty.setText("");
        txttotalt.setText("");
        hitung();
        autonumber();
    }//GEN-LAST:event_btambahActionPerformed

    private void txtqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtqtyActionPerformed
        int xhrg=Integer.parseInt(txthargab.getText());
        if(cb_brg.getSelectedItem() == "Pcs"){
                jumlah = Integer.parseInt(txtqty.getText());
        }else{
                konversi();
        }
        int xjml=xhrg*jumlah;
        txttotalt.setText(String.valueOf(xjml));
    }//GEN-LAST:event_txtqtyActionPerformed

    private void buttonRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRiwayatActionPerformed
        popuptransaksi put = new popuptransaksi();
        put.PB = this;
        put.setVisible(true);
        put.setResizable(false);
    }//GEN-LAST:event_buttonRiwayatActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiPembelianBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bcarib;
    private javax.swing.JButton bexit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btambah;
    private javax.swing.JButton buttonRiwayat;
    private javax.swing.JComboBox<String> cb_brg;
    private com.toedter.calendar.JDateChooser datetransaksi;
    private javax.swing.JPanel header1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbltransaksi;
    private javax.swing.JTextField txthargab;
    private javax.swing.JTextField txtida;
    private javax.swing.JTextField txtidb;
    private javax.swing.JTextField txtidt;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txttotalh;
    private javax.swing.JTextField txttotalt;
    // End of variables declaration//GEN-END:variables
}
