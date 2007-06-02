#!/bin/sh
CLEANCOMMAND="rm -Rf uk/org/dataforce/jisg/*.class ; rm -Rf uk/org/dataforce/jisg/*/*.class ; rm -Rf uk/org/dataforce/jisg/*/*/*.class"
COMPILECOMMAND="javac -Xlint:all uk/org/dataforce/jisg/filetypes/types/*.java uk/org/dataforce/jisg/JISG.java"
RUNCOMMAND="java uk.org.dataforce.jisg.JISG"

$CLEANCOMMAND
$COMPILECOMMAND && $RUNCOMMAND "$@"
