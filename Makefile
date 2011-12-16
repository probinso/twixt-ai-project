default: *.java
	@echo -------Making-------
	@javac *.java
	@echo ----Creating Jar----
	@echo Manifest-Version: 1.0 > MANIFEST.MF
	@echo Main-Class: main >> MANIFEST.MF
	@jar -cmf MANIFEST.MF twixt.jar *.class
	@echo -----Cleaning Up----
	@rm *.class
	@rm MANIFEST.MF

.PHONY: clean run

run:
	@java -jar twixt.jar	

t1j:
	@wget http://www.johannes-schwagereit.de/twixt/T1j/t1j.jar
	@java -jar t1j.jar

clean: 
	@rm -f *.class *~ *.log *.aux *.pdf *.jar *.MF
	@echo -----Cleaning Up----
