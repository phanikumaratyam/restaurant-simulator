JC = javac
JFLAGS = -g
JVM= java 

.SUFFIXES: .java .class

.java.class:
		$(JC) $*.java
		
CLASSES = \
        Main.java \
        Restaurant.java \
        Diner.java \
        Cook.java \
        Tables.java \
        BurgerMaker.java \
        FriesMaker.java \
        CokeMachine.java
		
MAIN = Main

default: classes
		$(JVM) Main
		
classes: $(CLASSES:.java=.class)

clean:
	find . -type f -name "*.class" -delete