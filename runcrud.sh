#!/bin/bash

export CATALINA_HOME=/usr/share/tomcat9

stop_tomcat()
{
  systemctl -H root@10.4.1.13 stop tomcat9
}

start_tomcat()
{
  systemctl -H root@10.4.1.13 start tomcat9
  end
}

rename() {
  rm build/libs/crud.war
  if mv build/libs/tasks-0.0.1-SNAPSHOT.war build/libs/crud.war; then
    echo "Successfully renamed file"
  else
    echo "Cannot rename file"
    fail
  fi
}
  copy_file() {
    if scp build/libs/crud.war root@10.4.1.13:/$CATALINA_HOME/webapps; then
      start_tomcat
      echo "Copy successful"
    else
      fail
    fi
  }

  fail() {
    echo "There were errors"
  }

  end() {
    echo "Work is finished"
  }

  if ./gradlew build; then
    rename
    copy_file
  else
    stop_tomcat
    fail
  fi