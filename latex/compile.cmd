call cleanup.cmd
pdflatex main
bibtex main
makeindex document.glo -s document.ist -t document.glg -o document.gls
pdflatex main
pdflatex main
pdflatex main
pdflatex main
pdflatex main
main.pdf
