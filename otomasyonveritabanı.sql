CREATE DATABASE  IF NOT EXISTS `online_egitim` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `online_egitim`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: online_egitim
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ders`
--

DROP TABLE IF EXISTS `ders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ad` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ders`
--

LOCK TABLES `ders` WRITE;
/*!40000 ALTER TABLE `ders` DISABLE KEYS */;
INSERT INTO `ders` VALUES (1,'Türk Dili ve Edebiyatı'),(2,'Matematik'),(3,'Fizik'),(4,'Kimya'),(5,'Biyoloji'),(6,'Tarih'),(7,'Coğrafya'),(8,'Din Kültürü ve Ahlak Bilgisi');
/*!40000 ALTER TABLE `ders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dersprogrami`
--

DROP TABLE IF EXISTS `dersprogrami`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dersprogrami` (
  `Alan` varchar(20) NOT NULL,
  `Gun` varchar(10) NOT NULL,
  `Saat` varchar(20) NOT NULL,
  `Ders` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Alan`,`Gun`,`Saat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dersprogrami`
--

LOCK TABLES `dersprogrami` WRITE;
/*!40000 ALTER TABLE `dersprogrami` DISABLE KEYS */;
INSERT INTO `dersprogrami` VALUES ('AYT SÖZ','Çarşamba','09:00-10:00','Coğrafya'),('AYT SÖZ','Çarşamba','10:00-11:00','Din'),('AYT SÖZ','Çarşamba','11:00-12:00','Türk Dili'),('AYT SÖZ','Çarşamba','13:00-14:00','Tarih'),('AYT SÖZ','Çarşamba','14:00-15:00','Coğrafya'),('AYT SÖZ','Cuma','09:00-10:00','Türk Dili'),('AYT SÖZ','Cuma','10:00-11:00','Tarih'),('AYT SÖZ','Cuma','11:00-12:00','Coğrafya'),('AYT SÖZ','Cuma','13:00-14:00','Din'),('AYT SÖZ','Cuma','14:00-15:00','Tarih'),('AYT SÖZ','Pazartesi','09:00-10:00','Türk Dili'),('AYT SÖZ','Pazartesi','10:00-11:00','Tarih'),('AYT SÖZ','Pazartesi','11:00-12:00','Coğrafya'),('AYT SÖZ','Pazartesi','13:00-14:00','Din'),('AYT SÖZ','Pazartesi','14:00-15:00','Türk Dili'),('AYT SÖZ','Perşembe','09:00-10:00','Din'),('AYT SÖZ','Perşembe','10:00-11:00','Türk Dili'),('AYT SÖZ','Perşembe','11:00-12:00','Tarih'),('AYT SÖZ','Perşembe','13:00-14:00','Coğrafya'),('AYT SÖZ','Perşembe','14:00-15:00','Din Kültürü'),('AYT SÖZ','Salı','09:00-10:00','Tarih'),('AYT SÖZ','Salı','10:00-11:00','Coğrafya'),('AYT SÖZ','Salı','11:00-12:00','Din'),('AYT SÖZ','Salı','13:00-14:00','Türk Dili'),('AYT SÖZ','Salı','14:00-15:00',''),('TYT','Çarşamba','09:00-10:00','Fizik'),('TYT','Çarşamba','10:00-11:00','Kimya'),('TYT','Çarşamba','11:00-12:00','Biyoloji'),('TYT','Çarşamba','13:00-14:00','Coğrafya'),('TYT','Çarşamba','14:00-15:00','Din Kültürü'),('TYT','Cuma','09:00-10:00','Biyoloji'),('TYT','Cuma','10:00-11:00','Matematik'),('TYT','Cuma','11:00-12:00','Coğrafya'),('TYT','Cuma','13:00-14:00','Türk Dili'),('TYT','Cuma','14:00-15:00','Tarih'),('TYT','Pazartesi','09:00-10:00','Matematik'),('TYT','Pazartesi','10:00-11:00','Fizik'),('TYT','Pazartesi','11:00-12:00','Kimya'),('TYT','Pazartesi','13:00-14:00','Türk Dili'),('TYT','Pazartesi','14:00-15:00','Biyoloji'),('TYT','Perşembe','09:00-10:00','Kimya'),('TYT','Perşembe','10:00-11:00','Biyoloji'),('TYT','Perşembe','11:00-12:00','Tarih'),('TYT','Perşembe','13:00-14:00','Matematik'),('TYT','Perşembe','14:00-15:00','Kimya'),('TYT','Salı','09:00-10:00','Türk Dili'),('TYT','Salı','10:00-11:00','Matematik'),('TYT','Salı','11:00-12:00','Fizik'),('TYT','Salı','13:00-14:00','Tarih'),('TYT','Salı','14:00-15:00','Coğrafya');
/*!40000 ALTER TABLE `dersprogrami` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `konu`
--

DROP TABLE IF EXISTS `konu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `konu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `unite_id` int DEFAULT NULL,
  `ad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `unite_id` (`unite_id`),
  CONSTRAINT `konu_ibfk_1` FOREIGN KEY (`unite_id`) REFERENCES `unite` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `konu`
--

LOCK TABLES `konu` WRITE;
/*!40000 ALTER TABLE `konu` DISABLE KEYS */;
/*!40000 ALTER TABLE `konu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanici`
--

DROP TABLE IF EXISTS `kullanici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanici` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `sifre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanici`
--

LOCK TABLES `kullanici` WRITE;
/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` VALUES (1,'test@test.com','1234');
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanicilar`
--

DROP TABLE IF EXISTS `kullanicilar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanicilar` (
  `KullaniciID` int NOT NULL AUTO_INCREMENT,
  `Ad` varchar(50) DEFAULT NULL,
  `Soyad` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Sifre` varchar(50) DEFAULT NULL,
  `Rol` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`KullaniciID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanicilar`
--

LOCK TABLES `kullanicilar` WRITE;
/*!40000 ALTER TABLE `kullanicilar` DISABLE KEYS */;
INSERT INTO `kullanicilar` VALUES (1,'Admin','User','admin@gmail.com','1234','admin');
/*!40000 ALTER TABLE `kullanicilar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ogrenci`
--

DROP TABLE IF EXISTS `ogrenci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ogrenci` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tc` varchar(11) DEFAULT NULL,
  `ad` varchar(50) DEFAULT NULL,
  `soyad` varchar(50) DEFAULT NULL,
  `numara` varchar(20) DEFAULT NULL,
  `kurs` varchar(100) DEFAULT NULL,
  `kayit_tarihi` date DEFAULT NULL,
  `ucret` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ogrenci`
--

LOCK TABLES `ogrenci` WRITE;
/*!40000 ALTER TABLE `ogrenci` DISABLE KEYS */;
INSERT INTO `ogrenci` VALUES (5,'10293847562','Ahmet','Yılmaz','2024001','AYT-Sayısal','2024-08-15',40000),(6,'92837465104','Zeynep','Kaya','2024002','AYT-Eşit Ağırlık','2024-02-11',38000),(7,'45612378950','Elif','Şahin','2024003','AYT-Sözel','2024-06-20',36000),(8,'55667788990','Caner','Özkan','2024004','TYT','2024-04-05',42000);
/*!40000 ALTER TABLE `ogrenci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ogrenci_not`
--

DROP TABLE IF EXISTS `ogrenci_not`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ogrenci_not` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ogrenci_id` int DEFAULT NULL,
  `ders` varchar(100) DEFAULT NULL,
  `notu` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ogrenci_id` (`ogrenci_id`),
  CONSTRAINT `ogrenci_not_ibfk_1` FOREIGN KEY (`ogrenci_id`) REFERENCES `ogrenci` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ogrenci_not`
--

LOCK TABLES `ogrenci_not` WRITE;
/*!40000 ALTER TABLE `ogrenci_not` DISABLE KEYS */;
INSERT INTO `ogrenci_not` VALUES (1,5,'Matematik',82),(2,5,'Fizik',70);
/*!40000 ALTER TABLE `ogrenci_not` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ogretmen`
--

DROP TABLE IF EXISTS `ogretmen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ogretmen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tc` varchar(11) DEFAULT NULL,
  `ad` varchar(50) DEFAULT NULL,
  `soyad` varchar(50) DEFAULT NULL,
  `brans` varchar(50) DEFAULT NULL,
  `maas` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ogretmen`
--

LOCK TABLES `ogretmen` WRITE;
/*!40000 ALTER TABLE `ogretmen` DISABLE KEYS */;
INSERT INTO `ogretmen` VALUES (1,'21098475612','Ayşe','Çekiç','Türk Dili ve Edebiyatı',38000),(2,'34567890122','Hakan','Yılmaz','Matematik',45000),(3,'45678901234','Murat','Kaya','Fizik',42000),(4,'56789012346','Selin','Öztürk','Kimya',39000),(5,'67890123458','Erkan','Şahin','Biyoloji',38000),(6,'78901234560','Hikmet','Yıldız','Tarih',35000),(7,'89012345672','Kemal','Aydın','Coğrafya',36000),(9,'90123456784','Ömer Ali','Parlak','Din Kültürü ve Ahlak Bilgisi',30000);
/*!40000 ALTER TABLE `ogretmen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unite`
--

DROP TABLE IF EXISTS `unite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unite` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ders_id` int DEFAULT NULL,
  `ad` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ders_id` (`ders_id`),
  CONSTRAINT `unite_ibfk_1` FOREIGN KEY (`ders_id`) REFERENCES `ders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unite`
--

LOCK TABLES `unite` WRITE;
/*!40000 ALTER TABLE `unite` DISABLE KEYS */;
INSERT INTO `unite` VALUES (1,1,'Anlam Bilgisi'),(2,1,'Dil Bilgisi'),(3,1,'Şiir Bilgisi ve Nazım Biçimleri'),(4,1,'İslamiyet Öncesi ve Geçiş Dönemi Edebiyatı'),(5,1,'Halk Edebiyatı ve Divan Edebiyatı'),(6,1,'Tanzimat, Servet-i Fünun ve Fecr-i Ati Edebiyatı'),(7,1,'Milli Edebiyat ve Cumhuriyet Dönemi Edebiyatı'),(8,1,'Edebiyat Türleri'),(9,2,'Temel Kavramlar'),(10,2,'Denklem ve Eşitsizlikler'),(11,2,'Üslü, Köklü Sayılar ve Çarpanlara Ayırma'),(12,2,'Fonksiyonlar ve Polinomlar'),(13,2,'Logaritma ve Diziler'),(14,2,'Trigonometri'),(15,2,'Türev, İntegral ve Limit'),(16,2,'Geometri'),(17,2,'Veri, Sayma ve Olasılık'),(18,3,'Fizik Bilimine Giriş'),(19,3,'Madde ve Özellikleri'),(20,3,'Hareket ve Kuvvet'),(21,3,'Enerji, İş ve Güç'),(22,3,'Isı ve Sıcaklık'),(23,3,'Elektrostatik ve Elektrik Akımı'),(24,3,'Optik ve Dalgalar'),(25,3,'Modern Fizik ve Teknolojideki Uygulamaları'),(26,4,'Kimya Bilimi ve Atomun Yapısı'),(27,4,'Periyodik Sistem ve Kimyasal Türler'),(28,4,'Maddenin Halleri ve Karışımlar'),(29,4,'Kimyasal Hesaplamalar ve Yasalar'),(30,4,'Modern Atom Teorisi'),(31,4,'Sıvı Çözeltiler ve Enerji'),(32,4,'Kimyasal Tepkimelerde Hız ve Denge'),(33,4,'Organik Kimyaya Giriş ve Bileşikler'),(34,5,'Yaşam Bilimi Biyoloji ve Hücre'),(35,5,'Canlılar Dünyası ve Sınıflandırma'),(36,5,'Hücre Bölünmeleri ve Üreme'),(37,5,'Kalıtım ve Genetik'),(38,5,'Ekosistem ve Güncel Çevre Sorunları'),(39,5,'İnsan Fizyolojisi'),(40,5,'Genden Proteine'),(41,5,'Bitki Biyolojisi'),(42,6,'Tarih ve Zaman'),(43,6,'İlk ve Orta Çağlarda Türk Dünyası'),(44,6,'İslam Medeniyetinin Doğuşu'),(45,6,'Orta Çağ ve Yerleşik Devletler'),(46,6,'Osmanlı Devleti'),(47,6,'Yüzyıl Başlarında Dünya ve Osmanlı'),(48,6,'Milli Mücadele'),(49,6,'Atatürkçülük ve İnkılap Tarihi'),(50,7,'Doğa ve İnsan'),(51,7,'Atmosfer ve İklim'),(52,7,'İç ve Dış Kuvvetler'),(53,7,'Beşeri Sistemler'),(54,7,'Türkiye’nin Coğrafi Özellikleri'),(55,7,'Ekonomik Faaliyetler ve Bölgeler'),(56,7,'Çevre ve Toplum'),(57,8,'Kader İnancı'),(58,8,'Zekat ve Sadaka'),(59,8,'Din ve Hayat'),(60,8,'Hz. Muhammed’in Örnekliği'),(61,8,'Kur’an-ı Kerim ve Özellikleri'),(62,8,'İslam ve Bilim / Güzel Ahlak');
/*!40000 ALTER TABLE `unite` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-27 15:49:42
