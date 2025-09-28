JCC = javac -Xlint:unchecked

JVM = java

SRCDIR = src

BINDIR = bin

MAINCLASS = MyApplication

SOURCES = $(wildcard $(SRCDIR)/*.java)

CLASSES = $(patsubst $(SRCDIR)/%.java, $(BINDIR)/%.class, $(SOURCES))

all: $(CLASSES)

$(BINDIR)/%.class: $(SRCDIR)/%.java
	@mkdir -p $(BINDIR)
	$(JCC) -d $(BINDIR) -cp $(BINDIR) $(SOURCES)

clean:
	rm -rf $(BINDIR)

.PHONY: all run clean

