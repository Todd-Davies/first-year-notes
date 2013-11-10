#!/bin/bash
aspell -t check doc.tex
# Edit the compilation engine as appropriate for your project
pdflatex doc.tex
