#!/bin/bash

if ./runcrud.sh; then
  chromium http://10.4.1.13:8080/crud/v1/tasks
else
  echo "There were errors"
fi