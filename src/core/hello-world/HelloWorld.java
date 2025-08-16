// Để compiler sử dụng lệnh javac HelloWorld.java -> ByteCode (*.class)
// Để JVM chứa interpreter sử dụng lệnh java HelloWorld (không thêm ./ hoặc đuôi file)

// TODO: Lệnh compiler:   javac -d release HelloWorld.java
// TODO: Lệnh run:        java -cp ./release helloworld/HelloWorld

package helloworld;

public class HelloWorld
{
  public static void main(String[] args)
  {
    System.out.println("Hello World!");
  }
}
