total=0; for i in $(find `pwd` -name notes.pdf); do total=$(echo $(exiftool $i | awk "/Page Count/ {print}" | tail -c 3) + $total | bc); done; echo "Page count: " $total
