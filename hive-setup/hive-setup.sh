#!/bin/bash

# Define the download URL
HIVE_DOWNLOAD_URL='https://dlcdn.apache.org/hive/hive-3.1.3/apache-hive-3.1.3-bin.tar.gz'


# Define the directory where Java will be installed
INSTALL_DIR="/home/hive/hive"

# Create the directory if it doesn't exist
sudo mkdir -p $INSTALL_DIR

# Change directory to the installation directory
cd $INSTALL_DIR


# Download Java 8 JDK
echo "Downloading Hive 3.1.3"
sudo wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" $HIVE_DOWNLOAD_URL

# Extract the downloaded file
echo "Extracting Hive 3.1.3"
sudo tar -zxvf apache-hive-3.1.3-bin.tar.gz

# Remove the downloaded tar file
sudo rm apache-hive-3.1.3-bin.tar.gz
sudo apt-get install mysql-server
