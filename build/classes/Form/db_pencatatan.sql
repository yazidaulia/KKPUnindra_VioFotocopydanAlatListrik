-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Apr 2021 pada 08.07
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.10

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
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `jenis_kelamin` varchar(9) NOT NULL,
  `telepon` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id`, `nama`, `jenis_kelamin`, `telepon`) VALUES
('ADM-001', 'Yusuf', 'Laki-Laki', '1234567890'),
('ADM-002', 'Fariq', 'Laki-Laki', '1234567890'),
('ADM-003', 'Yazid', 'Laki-Laki', '1234567890'),
('ADM-004', 'Auli', 'Laki-Laki', '1234567890');

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_supplier` varchar(10) NOT NULL,
  `id_barang` varchar(10) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `keterangan` varchar(10) NOT NULL,
  `stok` int(10) NOT NULL,
  `harga` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_supplier`, `id_barang`, `nama`, `keterangan`, `stok`, `harga`) VALUES
('SPR-001', 'BRG-0001', 'Tali Rapia', 'Jenis 1', 100, 100000),
('SPR-001', 'BRG-0002', 'Token Listrik', 'Jenis 2', 100, 500000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `laporan`
--

CREATE TABLE `laporan` (
  `id_laporan` varchar(10) NOT NULL,
  `id_admin` varchar(10) NOT NULL,
  `id_barang` varchar(10) NOT NULL,
  `jenis_laporan` varchar(15) NOT NULL,
  `periode_laporan` varchar(20) NOT NULL,
  `total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `laporan`
--

INSERT INTO `laporan` (`id_laporan`, `id_admin`, `id_barang`, `jenis_laporan`, `periode_laporan`, `total`) VALUES
('LP-0001', 'ADM-001', 'BRG-0001', 'Pembelian', 'Senin', 500000),
('LP-0002', 'ADM-001', 'BRG-0001', 'Pembelian', 'Senin', 200000),
('LP-0003', 'ADM-001', 'BRG-0002', 'Penjualan', 'Rabu', 150000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

CREATE TABLE `supplier` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `telepon` varchar(12) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`id`, `nama`, `telepon`, `alamat`) VALUES
('SPR-001', 'Argus', '1234567890', 'Jl. Cawang'),
('SPR-002', 'Can Can', '1234567890', 'Jl. Mars');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_admin` varchar(10) NOT NULL,
  `id_transaksi` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `id_barang` varchar(10) NOT NULL,
  `total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_admin`, `id_transaksi`, `tanggal`, `id_barang`, `total`) VALUES
('ADM-001', 'TRX-0001', '2021-03-29', 'SPR-001', 9000),
('ADM-001', 'TRX-0002', '2021-03-29', 'SPR-001', 100000),
('ADM-001', 'TRX-0003', '2021-03-29', 'SPR-001', 16000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `laporan`
--
ALTER TABLE `laporan`
  ADD PRIMARY KEY (`id_laporan`);

--
-- Indeks untuk tabel `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
