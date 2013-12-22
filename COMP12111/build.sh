#!/bin/bash
aspell -t check content.tex
aspell -t check meta.tex
aspell -t check three_bx_model.tex
aspell -t check instruction_format.tex
pdflatex instruction_format.tex
pdflatex three_box_model.tex
pdflatex notes.tex
pdflatex kindle.tex
# In case the Author field isn't set
exiftool notes.pdf -Author="Todd Davies"
