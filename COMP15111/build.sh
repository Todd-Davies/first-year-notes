#!/bin/bash
aspell -t check content.tex
aspell -t check meta.tex
pdflatex two_box_model_diagram.tex
pdflatex notes.tex
pdflatex kindle.tex
