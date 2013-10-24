#!/bin/bash
aspell -t check doc.tex
pdflatex two_box_model_diagram.tex
pdflatex doc.tex
