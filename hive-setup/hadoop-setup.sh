#!/bin/bash

# Define the directory where Hadoop will be installed
EXEC_FILE_PATH=`pwd`
INSTALL_DIR="/home/hive/hadoop"

# Create the directory if it doesn't exist
sudo mkdir -p $INSTALL_DIR

# Change directory to the installation directory
cd $INSTALL_DIR

# Download Hadoop 3.3.5
echo "Downloading Hadoop 3.3.5"
sudo wget https://archive.apache.org/dist/hadoop/common/hadoop-3.3.5/hadoop-3.3.5.tar.gz

# Extract the downloaded archive
echo "Extracting Hadoop 3.3.5"
sudo tar -xzvf hadoop-3.3.5.tar.gz

# Remove the downloaded tar file
sudo rm hadoop-3.3.5.tar.gz

# Set up environmental variables
export HADOOP_HOME=$INSTALL_DIR/hadoop-3.3.5
export HADOOP_MAPRED_HOME=$HADOOP_HOME
export HADOOP_COMMON_HOME=$HADOOP_HOME
export HADOOP_HDFS_HOME=$HADOOP_HOME
export YARN_HOME=$HADOOP_HOME
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib"
export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin

cd $EXEC_FILE_PATH
