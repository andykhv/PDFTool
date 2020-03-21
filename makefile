JC = javac
JVM = java
FLAGS = -cp
JARS = ".:./lib/commons-logging-1.2.jar:./lib/miglayout-4.0.jar:./lib/pdfbox-2.0.19.jar:./src"

.SUFFIXES: .java .class

.java.class:
	$(JC) $(FLAGS) $(JARS) ./src/*.java

CLASSES = src/Main.java src/Window.java src/MergerUI.java src/Merger.java

MAIN = Main

default = classes

classes: $(CLASSES:.java=.class)

run: ./src/$(MAIN).class
	$(JVM) $(FLAGS) $(JARS) $(MAIN)

clean:
	$(RM) ./src/*.class
