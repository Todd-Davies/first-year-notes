#!/bin/bash
aspell -t check notes.tex
pdflatex three_box_model.tex
pdflatex notes.tex
