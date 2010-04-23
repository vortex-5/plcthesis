call cleanup.cmd
pdflatex main
bibtex main
makeindex main.glo -s main.ist -t main.glg -o main.gls
pdflatex main
pdflatex main
pdflatex main
pdflatex main
pdflatex main
main.pdf
