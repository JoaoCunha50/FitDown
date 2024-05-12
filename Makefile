JAVAC = javac

JAVAC_FLAGS =-sourcepath $(SRC_DIR)

SRC_DIR = src

SOURCES = $(wildcard $(SRC_DIR)/*.java) run

all: $(SOURCES:$(SRC_DIR)/%.java=%.class)

%.class: $(SRC_DIR)/%.java
	$(JAVAC) $(JAVAC_FLAGS) $<

clean:
	rm -f $(SRC_DIR)/*.class

run:
	java -cp $(SRC_DIR) Main

.PHONY: all clean


