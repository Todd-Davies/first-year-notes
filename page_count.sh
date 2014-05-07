total=0;
for i in $(find ./ -name notes.pdf ; find ./*/flashcards/ -name doc.pdf );
  do
  nextCount=$(exiftool $i | awk "/Page Count/ {print}" | tail -c 3)
  total=$(echo $nextCount + $total | bc);
  echo $i: $nextCount;
  done;
echo "Total page count: " $total;

