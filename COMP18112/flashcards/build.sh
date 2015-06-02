#!/bin/bash
aspell -t check doc.tex
aspell -t check attribution.tex
pdflatex attribution.tex
pdflatex doc.tex