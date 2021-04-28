-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2021 at 10:46 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_pencatatan`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` varchar(7) NOT NULL,
  `nama_admin` varchar(20) NOT NULL,
  `jenis_kelamin` varchar(9) NOT NULL,
  `telepon` varchar(13) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `nama_admin`, `jenis_kelamin`, `telepon`, `password`) VALUES
('ADM-001', 'Yusuf', 'Laki-Laki', '1234567890', '0'),
('ADM-002', 'Fariq', 'Laki-Laki', '1234567890', '0'),
('ADM-003', 'Yazid', 'Laki-Laki', '1234567890', 'E00CF25AD42683B3DF678C61F42C6BDA'),
('ADM-004', 'Auli', 'Laki-Laki', '1234567890', '0');

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_supplier` varchar(10) NOT NULL,
  `id_barang` varchar(10) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `keterangan` varchar(10) NOT NULL,
  `stok` int(10) NOT NULL,
  `harga` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_supplier`, `id_barang`, `nama`, `keterangan`, `stok`, `harga`) VALUES
('SPR-001', 'BRG-0003', 'TL PHILIPS 10 WATT', 'Barang', 18, 10500),
('SPR-001', 'BRG-0004', 'TL PHILIPS 18 WATT', 'Barang', 17, 18900),
('SPR-001', 'BRG-0005', 'TL PHILIPS 36 WATT', 'Barang', 18, 26250),
('SPR-001', 'BRG-0006', 'PHILIPS E55 5 WATT', 'Barang', 16, 36750),
('SPR-001', 'BRG-0007', 'PHILIPS E55 8 WATT', 'Barang', 17, 39900),
('SPR-001', 'BRG-0008', 'PHILIPS E55 11 WATT', 'Barang', 19, 42000),
('SPR-001', 'BRG-0009', 'PHILIPS E55 14 WATT', 'Barang', 20, 47250),
('SPR-001', 'BRG-0010', 'PHILIPS E55 18 WATT', 'Barang', 20, 52500),
('SPR-001', 'BRG-0011', 'PHILIPS E55 23 WATT', 'Barang', 20, 57750),
('SPR-001', 'BRG-0012', 'HANNOCH BASIC 3 W', 'Barang', 15, 31500),
('SPR-001', 'BRG-0013', 'HANNOCH BASIC 5 W', 'Barang', 15, 36750),
('SPR-001', 'BRG-0014', 'HANNOCH BASIC 7 W', 'Barang', 15, 39900),
('SPR-001', 'BRG-0015', 'HANNOCH BASIC 9 W', 'Barang', 15, 42000),
('SPR-001', 'BRG-0016', 'HANNOCH BASIC 11 W', 'Barang', 15, 47250),
('SPR-001', 'BRG-0017', 'HANNOCH BASIC 14 W', 'Barang', 15, 57750),
('SPR-001', 'BRG-0018', 'HANNOCH BASIC 17 W', 'Barang', 20, 68250),
('SPR-001', 'BRG-0019', 'HANNOCH BASIC 20 W', 'Barang', 20, 78750),
('SPR-001', 'BRG-0020', 'HANNOCH PREM 3 W', 'Barang', 15, 31500),
('SPR-001', 'BRG-0021', 'HANNOCH PREM 5 W', 'Barang', 15, 36750),
('SPR-001', 'BRG-0022', 'HANNOCH PREM 7 W', 'Barang', 15, 42000),
('SPR-001', 'BRG-0023', 'HANNOCH PREM 9 W', 'Barang', 15, 47250),
('SPR-001', 'BRG-0024', 'HANNOCH PREM 12 W', 'Barang', 15, 52500),
('SPR-001', 'BRG-0025', 'HANNOCH PREM 16 W', 'Barang', 15, 68250),
('SPR-001', 'BRG-0026', 'HANNOCH VARIO 12 W', 'Barang', 15, 52500),
('SPR-001', 'BRG-0027', 'HANNOCH VARIO 18 W', 'Barang', 15, 68250),
('SPR-002', 'BRG-0028', 'LED HORI 3 WATT', 'Barang', 20, 26250),
('SPR-002', 'BRG-0029', 'LED HORI 6.5 WATT', 'Barang', 20, 36750),
('SPR-002', 'BRG-0030', 'LED HORI 7.5 WATT', 'Barang', 20, 36750),
('SPR-002', 'BRG-0031', 'LED HORI 9.5 WATT', 'Barang', 20, 39900),
('SPR-002', 'BRG-0032', 'LED HORI 11 WATT', 'Barang', 20, 42000),
('SPR-002', 'BRG-0033', 'LED HORI 13 WATT', 'Barang', 20, 47250),
('SPR-002', 'BRG-0034', 'LED HORI 16 WATT', 'Barang', 20, 57750),
('SPR-002', 'BRG-0035', 'LED HORI 18 WATT', 'Barang', 20, 68250),
('SPR-002', 'BRG-0036', 'LED HORI 20 WATT', 'Barang', 20, 78750),
('SPR-002', 'BRG-0037', 'LED HORI 30 WATT', 'Barang', 20, 147000),
('SPR-002', 'BRG-0038', 'LED BIASA 3 WATT', 'Barang', 30, 8400),
('SPR-002', 'BRG-0039', 'LED BIASA 5 WATT', 'Barang', 30, 10500),
('SPR-002', 'BRG-0040', 'LED BIASA 7 WATT', 'Barang', 30, 12600),
('SPR-002', 'BRG-0041', 'LED BIASA 9 WATT', 'Barang', 30, 14700),
('SPR-002', 'BRG-0042', 'LED BIASA 12 WATT', 'Barang', 30, 15750),
('SPR-002', 'BRG-0043', 'LED BIASA 15 WATT', 'Barang', 30, 21000),
('SPR-002', 'BRG-0044', 'LED BIASA 18 WATT', 'Barang', 30, 26250),
('SPR-001', 'BRG-0045', 'PHILIPS SITRANG 5 W', 'Barang', 20, 31500),
('SPR-001', 'BRG-0046', 'PHILIPS SITRANG 8 W', 'Barang', 20, 36750),
('SPR-001', 'BRG-0047', 'PHILIPS SITRANG 11 W', 'Barang', 20, 42000),
('SPR-001', 'BRG-0048', 'PHILIPS SITRANG 14 W', 'Barang', 20, 47250),
('SPR-001', 'BRG-0049', 'PHILIPS SITRANG 18 W', 'Barang', 20, 52500),
('SPR-001', 'BRG-0050', 'PHILIPS SITRANG 23 W', 'Barang', 20, 57750),
('SPR-001', 'BRG-0051', 'ARASHI 3 WATT', 'Barang', 20, 15750),
('SPR-001', 'BRG-0052', 'ARASHI 5 WATT', 'Barang', 20, 21000),
('SPR-001', 'BRG-0053', 'ARASHI 7 WATT', 'Barang', 20, 26250),
('SPR-001', 'BRG-0054', 'ARASHI 9 WATT', 'Barang', 20, 31500),
('SPR-001', 'BRG-0055', 'ARASHI 11 WATT', 'Barang', 20, 34650),
('SPR-001', 'BRG-0056', 'ARASHI 14 WATT', 'Barang', 20, 36750),
('SPR-001', 'BRG-0057', 'ARASHI 17 WATT', 'Barang', 20, 42000),
('SPR-001', 'BRG-0058', 'ARASHI 20 WATT', 'Barang', 20, 47250),
('SPR-002', 'BRG-0059', 'IN LITE 5 WATT', 'Barang', 15, 28350),
('SPR-002', 'BRG-0060', 'IN LITE 7 WATT', 'Barang', 15, 31500),
('SPR-002', 'BRG-0061', 'IN LITE 9 WATT', 'Barang', 15, 36750),
('SPR-002', 'BRG-0062', 'IN LITE 11 WATT', 'Barang', 15, 42000),
('SPR-002', 'BRG-0063', 'IN LITE 15 WATT', 'Barang', 15, 47250),
('SPR-001', 'BRG-0064', 'PLC LED BIASA 20 WATT', 'Barang', 20, 15700),
('SPR-001', 'BRG-0065', 'PLC LED BIASA 30 WATT', 'Barang', 20, 26250),
('SPR-002', 'BRG-0066', 'PLC 2 U', 'Barang', 30, 10500),
('SPR-002', 'BRG-0067', 'PLC 3 U', 'Barang', 30, 21000),
('SPR-001', 'BRG-0068', 'BOHLAM 5 WATT', 'Barang', 30, 5250),
('SPR-001', 'BRG-0069', 'BOHLAM 5 WATT PHILIPS', 'Barang', 30, 10500),
('SPR-001', 'BRG-0070', 'CYODIA', 'Barang', 20, 10500),
('SPR-001', 'BRG-0071', 'MASCO 10 W', 'Barang', 20, 5250),
('SPR-002', 'BRG-0072', 'KAP ELELTRIK 10 WATT', 'Barang', 20, 26250),
('SPR-002', 'BRG-0073', 'KAP ELELTRIK 20 WATT', 'Barang', 20, 36250),
('SPR-002', 'BRG-0074', 'KAP ELELTRIK 30 WATT', 'Barang', 20, 47250),
('SPR-002', 'BRG-0075', 'KAP SET 10 WATT', 'Barang', 20, 47250),
('SPR-002', 'BRG-0076', 'KAP SET 20 WATT', 'Barang', 20, 57750),
('SPR-002', 'BRG-0077', 'KAP SET 40 WATT', 'Barang', 20, 68250),
('SPR-001', 'BRG-0078', 'BALAS BIASA 10 WATT', 'Barang', 20, 26250),
('SPR-001', 'BRG-0079', 'BALAS BIASA 20 WATT', 'Barang', 20, 26250),
('SPR-001', 'BRG-0080', 'BALAS BIASA 40 WATT', 'Barang', 20, 26250),
('SPR-001', 'BRG-0081', 'BALAS PHILIPS 10 WATT', 'Barang', 20, 52500),
('SPR-001', 'BRG-0082', 'BALAS PHILIPS 20 WATT', 'Barang', 20, 52500),
('SPR-001', 'BRG-0083', 'BALAS PHILIPS 40 WATT', 'Barang', 20, 52500);

-- --------------------------------------------------------

--
-- Table structure for table `laporan`
--

CREATE TABLE `laporan` (
  `id_laporan` varchar(10) NOT NULL,
  `id_admin` varchar(10) NOT NULL,
  `id_barang` varchar(10) NOT NULL,
  `jenis_laporan` varchar(15) NOT NULL,
  `periode_laporan` varchar(20) NOT NULL,
  `QTY` int(11) NOT NULL,
  `total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `laporan`
--

INSERT INTO `laporan` (`id_laporan`, `id_admin`, `id_barang`, `jenis_laporan`, `periode_laporan`, `QTY`, `total`) VALUES
('LP-0001', 'ADM-001', 'BRG-0001', 'Pembelian', 'Maret', 0, 500000),
('LP-0002', 'ADM-001', 'BRG-0001', 'Pembelian', 'Maret', 2, 200000),
('LP-0003', 'ADM-001', 'BRG-0002', 'Pembelian', 'April', 0, 150000),
('LP-0004', 'ADM-003', 'BRG-0007', 'Penjualan', 'April', 1, 39900);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `telepon` varchar(13) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `nama`, `telepon`, `alamat`) VALUES
('SPR-001', 'PT Honoris Industry', '(0251)8240322', 'Jl. Raya Sukabumi KM. 2, Ciawi, Bogor'),
('SPR-002', 'UD Mitra Baru', '(021)31902226', 'Jl. Kramat Raya, No 101, Paseban, Kec. Senen, Kota Jakarta Pusat'),
('SPR-003', 'PT Bintang Bunut', '02182615000', 'Jl. Raya Mustika Jaya Kota Legenda RT 002/ RW 12'),
('SPR-004', 'Tujuh Bintang Dunia', '768912', 'ITC Cempaka Mas, Mega Grosir, LT 4 Blok G No. 529,530,531'),
('SPR-005', 'Sinar Agung Bekasi', '1234567890', 'Jl. Kimangun Sarkoro No. 12 C, RT 003/RW 005');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_admin` varchar(10) NOT NULL,
  `id_transaksi` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `id_barang` varchar(10) NOT NULL,
  `harga_barang` int(11) NOT NULL,
  `qty` int(4) NOT NULL,
  `total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_admin`, `id_transaksi`, `tanggal`, `id_barang`, `harga_barang`, `qty`, `total`) VALUES
('ADM-003', 'TRX-0001', '2021-04-19', 'BRG-0005', 26250, 2, 52500),
('ADM-003', 'TRX-0002', '2021-04-21', 'BRG-0008', 42000, 1, 42000),
('ADM-003', 'TRX-0003', '2021-04-21', 'BRG-0007', 39900, 1, 39900);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `laporan`
--
ALTER TABLE `laporan`
  ADD PRIMARY KEY (`id_laporan`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
