#!/bin/bash

# Define the download URL
JAVA_DOWNLOAD_URL="https://download.java.net/openjdk/jdk8u43/ri/openjdk-8u43-linux-x64.tar.gz"

# Define the directory where Java will be installed
INSTALL_DIR="/opt/java"

# Create the directory if it doesn't exist
sudo mkdir -p $INSTALL_DIR

# Change directory to the installation directory
cd $INSTALL_DIR

# Download Java 8 JDK
echo "Downloading Java 8 JDK..."
sudo wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" $JAVA_DOWNLOAD_URL

# Extract the downloaded file
echo "Extracting Java 8 JDK..."
sudo tar -zxvf openjdk-8u43-linux-x64.tar.gz

# Remove the downloaded tar file
sudo rm openjdk-8u43-linux-x64.tar.gz

# Set Java environment variables
echo "$INSTALL_DIR/java-se-8u43-ri"
JAVA_HOME="$INSTALL_DIR/java-se-8u43-ri"
export JAVA_HOME
PATH="$JAVA_HOME/bin:$PATH"
export PATH

# Print installation directory
echo "Java 8 JDK has been downloaded and installed to $INSTALL_DIR"
echo "Java 8 JDK is now set to PATH"

