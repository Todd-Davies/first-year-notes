#!/bin/bash
cd ..
aspell -t check content.tex
aspell -t check meta.tex
cd automata
for i in `ls *.tex`
do
  pdflatex $i;
done;
cd ..
pdflatex notes.tex
pdflatex kindle.tex
# In case the Author field isn't set
exiftool notes.pdf -Author="Todd Davies"
