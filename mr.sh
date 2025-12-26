# 生成依赖 classpath
./mvnw -q -DskipTests -DincludeScope=runtime dependency:build-classpath -Dmdep.outputFile=target/cp.txt

# 编译 MR 代码到独立目录
mkdir -p target/mr-classes
javac -cp "target/classes:$(cat target/cp.txt)" -d target/mr-classes src/mr/*.java

# 运行
java -cp "target/mr-classes:target/classes:$(cat target/cp.txt)" \
  fun.steven.bookstore.mr.hadoop.KeywordCountDriver \
  mr/input mr/output mr/keywords.txt
