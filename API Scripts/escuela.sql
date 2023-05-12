CREATE DATABASE  IF NOT EXISTS `escuela` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `escuela`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: escuela
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) COLLATE utf8mb3_spanish2_ci NOT NULL,
  `apellido` varchar(80) COLLATE utf8mb3_spanish2_ci NOT NULL,
  `edad` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES (1,'Juan','González',20),(2,'María','Martínez',21),(3,'Pedro','Sánchez',22),(4,'Luis','Rodríguez',23),(5,'Ana','Pérez',24),(6,'Carlos','García',25),(7,'Lucía','Hernández',26),(8,'Miguel','López',27),(9,'Sara','Flores',28),(10,'Andrés','Jiménez',29),(11,'Paula','Ruiz',30),(12,'Fernando','Gómez',31),(13,'Lucas','Torres',32),(14,'Elena','Vega',33),(15,'Jorge','Ramírez',34),(16,'Isabel','Díaz',35),(17,'Antonio','Álvarez',36),(18,'Marta','Fernández',37),(19,'Rafael','Moreno',38),(20,'Carmen','Castillo',39),(21,'Diego','Ortega',40),(22,'Natalia','Gutiérrez',41),(23,'Alejandro','Navarro',42),(24,'Cristina','Romero',43),(25,'Mario','Santos',44),(26,'Laura','Estévez',45),(27,'Francisco','Blanco',46),(28,'Sandra','Molina',47),(29,'Juan Carlos','Suárez',48),(30,'Raquel','Castro',49),(31,'Javier','Ortiz',50),(32,'Beatriz','Soria',51),(33,'David','Márquez',52),(34,'Patricia','Vázquez',53),(35,'Emilio','Santiago',54),(36,'Celia','Benítez',55),(37,'Adrián','Collado',56),(38,'Luciana','Núñez',57),(39,'Víctor','Gallego',58),(40,'Alicia','Soto',59),(41,'Roberto','Lara',60),(42,'Esther','Delgado',61),(43,'Rubén','Lorenzo',62),(44,'Eva','Otero',63),(45,'Manuel','Rivas',64),(46,'Marina','Morales',65),(47,'Ángela','Carrasco',66),(48,'Óscar','Guerrero',67),(49,'Cristina','Pascual',68),(50,'Jesús','Moya',69),(51,'María José','Calvo',70),(52,'Ignacio','Iglesias',71),(53,'Clara','Gallardo',72),(54,'Sergio','Herrera',73),(55,'Olivia','Durán',74),(56,'Federico','Vidal',75),(57,'Lidia','Ramos',76),(58,'Guillermo','Ibáñez',77),(59,'Inés','Reyes',78),(60,'Pablo','Alonso',79),(61,'Julio','Guzmán',80),(62,'Lorena','Cruz',81),(63,'Gonzalo','Prieto',82),(64,'Marcela','Álvarez',83),(65,'Simón','Hidalgo',84),(66,'Luciana','Vera',85),(67,'Federico','Dominguez',86),(68,'Julieta','Luna',87),(69,'Diego','Sosa',88),(70,'Natalia','Mansilla',89),(71,'Hernán','Acosta',90),(72,'María Belén','Correa',91),(73,'Damián','Ríos',92),(74,'Candela','Moretti',93),(75,'Lucas','Soto',94),(76,'Sofía','Rey',95),(77,'Luisina','Chaves',96),(78,'Esteban','Giménez',97),(79,'Camila','Vargas',98),(80,'Lautaro','Ledesma',99),(81,'Jazmín','Peralta',100),(82,'Matías','Aguirre',21),(83,'Sol','Romano',22),(84,'Lucas','Rojas',23),(85,'Luna','Villalba',24),(86,'Agustín','López',25),(87,'Martina','Castro',26),(88,'Juan Pablo','Giménez',27),(89,'Julieta','Martínez',28),(90,'Tomás','Pérez',29),(91,'Valentina','García',30),(92,'Santiago','Rodríguez',31),(93,'Camila','Díaz',32),(94,'Facundo','Sánchez',33),(95,'Ailín','Luna',34),(96,'Gastón','Rivas',35),(97,'Mía','Hernández',36),(98,'Emiliano','Moreno',37),(99,'Florencia','Gómez',38),(101,'Renata','Lemus',40),(102,'Aurus','Alfaro',32);
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesores`
--

DROP TABLE IF EXISTS `profesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) COLLATE utf8mb3_spanish2_ci NOT NULL,
  `apellido` varchar(80) COLLATE utf8mb3_spanish2_ci NOT NULL,
  `edad` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesores`
--

LOCK TABLES `profesores` WRITE;
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
INSERT INTO `profesores` VALUES (1,'Jose','Alvarez',35),(2,'Kimberly','Martínez',43),(3,'Carlos','Sánchez',37),(4,'Luis','Rivas',55),(5,'Alma','Pérez',31),(102,'Roberto','Gutierrez',35);
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-12 12:30:07
