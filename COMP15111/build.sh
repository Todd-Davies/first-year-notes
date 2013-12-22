#!/bin/bash
aspell -t check content.tex
aspell -t check meta.tex
pdflatex two_box_model_diagram.tex
pdflatex notes.tex
pdflatex kindle.tex
# In case the Author field isn't set
exiftool notes.pdf -Author="Todd Davies"

