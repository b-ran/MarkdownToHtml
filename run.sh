clear
java -jar MarkdownConverter.jar acceptance1.txt
java -jar MarkdownConverter.jar acceptance2.txt
java -jar MarkdownConverter.jar acceptance3.txt
java -jar MarkdownConverter.jar acceptance4.txt
java -jar MarkdownConverter.jar acceptance5.txt
java -jar MarkdownConverter.jar acceptance6.txt
echo -e Checking acceptance1-convert.html acceptance1-correct.txt difference
diff acceptance1-convert.html acceptance1-correct.txt
echo -e Checking acceptance2-convert.html acceptance2-correct.txt difference
diff acceptance2-convert.html acceptance2-correct.txt
echo -e Checking acceptance3-convert.html acceptance3-correct.txt difference
diff acceptance3-convert.html acceptance3-correct.txt
echo -e Checking acceptance4-convert.html acceptance4-correct.txt difference
diff acceptance4-convert.html acceptance4-correct.txt
echo -e Checking acceptance5-convert.html acceptance5-correct.txt difference
diff acceptance5-convert.html acceptance5-correct.txt
echo -e Checking acceptance6-convert.html acceptance6-correct.txt difference
diff acceptance6-convert.html acceptance6-correct.txt
java -cp MarkdownConverter.jar org.junit.runner.JUnitCore tests.RunAllTests
read -p "`echo -e '\nPress enter to end'`"