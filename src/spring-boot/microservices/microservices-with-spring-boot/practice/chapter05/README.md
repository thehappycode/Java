## Limiting available CPUs

### Lệnh kiểm tra CPUs

```docker
echo 'Runtime.getRuntime().availableProcessors()' | docker run --rm -i eclipse-temurin:21-jdk-ubi9-minimal jshell -q
```

### Lệnh set số cpus

```docker
echo 'Runtime.getRuntime().availableProcessors()' | docker run --rm -i --cpus=3 eclipse-temurin:21-jdk-ubi9-minimal jshell -q
```

---

## Limiting available memory

Java sẽ lấy 1/4 bộ nhớ khả dụng để làm vùng nhớ heap

### Lệnh kiểm tra Memory

```docker
docker run -it --rm eclipse-temurin:21-jdk-ubi9-minimal java -XX:+PrintFlagsFinal | grep "size_t MaxHeapSize"
```

### Lệnh set memory

```docker
docker run -it --rm -m=1024M eclipse-temurin:21-jdk-ubi9-minimal java -XX:+PrintFlagsFinal | grep "size_t MaxHeapSize"
```

Set 1GB làm bộ nhớ thì có MaxHeapSize = 256MB.

### Lệnh set MaxHeapSize

```docker
docker run -it --rm -m=1024M eclipse-temurin:21-jdk-ubi9-minimal java -Xmx600m -XX:+PrintFlagsFinal | grep "size_t MaxHeapSize"
```

### Lệnh kiểm tra việc giới hạn bộ nhớ hoạt động

#### Đủ bộ nhớ heap

```docker
echo 'new byte[100_000_000]' | docker run -i --rm -m=1024M eclipse-temurin:21-jdk-ubi9-minimal jshell -q
```

#### Báo lỗi java.lang.OutOfMemoryError:

```docker
echo 'new byte[100_000_000]' | docker run -i --rm -m=1024M eclipse-temurin:21-jdk-ubi9-minimal jshell -q
```
