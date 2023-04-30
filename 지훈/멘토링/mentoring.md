상속
========
1. 상속이란 말 그대로 상속을 받는 것을 뜻한다. 우리가 부모님에게 어떠한
재산이나 집 같은 것을 물려받는 것을 상속이라 뜻하듯이, 자바에서도 위에서
클래스로 정의한 함수들이나, 멤버변수들을 상속받아 자기것으로 만들어 사용할 
수 있게된다.

```java
package Extends;

class Person {
    int money;
    
class Transfer extends Person {
    void showInfo() {
        System.out.println("이동수단 이용으로 "+money-1500+"원 남음");}
    
}     
}
```

2. 예를 들어 우리가 어떠한 큰 주제를 하나 예시로 class Person으로
만들어본다고 치자. 그러면 사람이 가지고 있는것은 성격과, 이름, 나이 등
여러가지를 가지고 있을것이다. 그러나 그 사람들이 하는것이나 그 사람들을 표현
할수있는 무언가가 필요하다. 하는것이라고 예를 든다면, 이동수단을 타거나, MBTI
성격 검사같은것을 할때, class Transfer extends Person 이런식으로 상속하는
함수인 extends를 써줌으로써 위에있는 정보들을 참조할 수 있고 자기걸로 유연하게 
사용할수있게끔 만들어 주는 것이다.

추상클래스
=======
1. 추상 클래스는 클래스 앞에 붙여진 추상이라는 말만 봐도 알수있듯이, 우리가 흔히
그림을 그릴때 추상화라는걸 떠올리곤 한다. 그만큼 눈앞에서 보는게 아니라서 구체적이지
않은걸 말하는데, 우리가 원래 만드는 클래스는 완성된 클래스로, 그 안에 있는 함수들과 변수들이
완성되있는형태를 말한다.

```java
package Abstract;

abstract class Computer {
    void display();

    void typing();

    class Desktop extends Computer {
        @Override
        void display() {
            System.out.println("해상도 설정");}
        void typing() {
            System.out.println("1920 x 1080");
        }
    }
}
```

2. 그러나 추상클래스는 완성되있지 않는 형태로, 추상클래스는 실행시킬수 없다.
그렇다면 추상클래스는 언제 쓰는것일까? 솔직히 이에 대해서는 나도 잘 모르겠으나, 생각을해보면
우리는 코드를 짤 때 한 주제를 정하고 그것에 대한 가지를 쳐가며 나아가는 형태라고 생각하기에
추상클래스는 상속을 할때 사용하는 것으로 알고 있다. 이 말은 추상클래스들을 하나 둘씩 만들어서
가지를 쳐가고, 나중에 상속함수를 써서 그 추상클래스들을 완벽하게 만드는 과정에서 추상클래스를
사용하는 것 같다. 

인터페이스
=======
#### 잘 모르겠다.
1. 인터페이스란 추상클래스와 비슷하지만 좀 다르다. 추상 클래스와 비슷하다고 한다면
먼저 인터페이스도 추상 메소드와 상수로만 이루어져 있어 추상클래스처럼 구현된 코드가없고,
실행도 할 수 없다. 사용하는 이유는 추상클래스와 비슷하다. 틀을 짜고 자세한걸 만들어 가듯이
인터페이스에서 큰 구조를 그려 놓은다음 추상 그 안에 들을 추상 메소드 와 추상 클래스로
채워가면서 완벽한 구조를 만들어 내는 것이다.
```java
public interface Calc {
    double PI = 3.14;
    int ERROR = -9999999;

    int add(int num1 , int num2);
    int substract(int num1 , int num2);
    int times(int num1 , int num2);
    int divide(int num1 , int num2);
}
```
다음은 책을 참고하며 만든 계산기를 만들려고 구성한 인터페이스 코드이다.(코드만 보고 내가 이해한대로 적어보겠다.)
간단하게 우리가 흔히 생각하는 계산기에는 사칙연산과 원주율을 나타내는 PI, 오류가 떴을때 나오는
에러가 있다고 가정해보자, 그렇다면 우리가 간단하게 만들수 있는 것은 위에처럼 나타낼수있을것이다.

```java
package mentoring;

import mentoring.interfacestudy.Calc;

public abstract class Calculator implements Calc {
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int substract(int num1, int num2) {
        return num1 - num2;
    }
}
```
그리고 다음 코드에는 추상 클래스를 만든뒤, 인터페이스인 Calc을 참조 하는 함수인 implements를
사용하여 어떤식으로 구성을 할건지 추상 메소드를 하나씩 구현해준다. 대충 어떤느낌으로 갈건지 정해졌다면,
```java
public class CompleteCalc extends Calculator {
    @Override
    public int times(int num1, int num2) {
        return num1 * num2;
    }

    @Override
    public int divide(int num1, int num2) {
        if (num2 != 0)
            return num1 / num2;
        else
            return Calc.ERROR;
    }

    public void showInfo() {
        System.out.println("Calc 인터페이스를 구현하였습니다.");
    }
}
```
위 코드 처럼 클래스를 만들어서 그 전에 만들었던 코드를 상속받는 함수인 extends를 사용하여 위에있는 코드들도
같이 실행할수 있게끔 상속받고, 나머지 나누기 곱하기도 마저 구조를 설정해준다음.

```java
package mentoring;

import mentoring.interfacestudy.CompleteCalc;

public class CalculatorTest {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;

        CompleteCalc calc = new CompleteCalc();
        System.out.println(calc.add(num1, num2));
        System.out.println(calc.substract(num1, num2));
        System.out.println(calc.divide(num1, num2));
        System.out.println(calc.times(num1, num2));
        calc.showInfo();
    }
}
```
최종적으로 위와같은 코드로 결과 값을 나타내게 된다. 이렇게 총 4개의 작업으로 나눠서
진행이 되었는데, 내가 생각하기에는 처음부터 코드를 완성형으로 만들기 쉽지 않으니 처음엔 뼈대를
만들어주는 과정으로 진행하며, 과정이 진행될수록 살점을 붙여주는 코드를 추가해주면서 나아가는 것같다.
그러기 위해선 상속이 필요하고 상속을 쓰는 추상 클래스, 추상메소드 , 그리고 그것들을 사용하기 전에 큰
틀이 되어주는 인터페이스가 필요한 것 같다.

제네릭(제네릭은 봐도 모르겠다.)
======
제네릭은 봐도 이해가 안된다. 내 생각하는 대로 말을하면, 한 클래스에서 똑같은 함수들이 있지만
그 변수들을 다르게 할려면 항상 클래스를 바꿔서 만들기는 번거로우니, 제네릭을 써서 이러한 번거로움을 고치고
자유롭게 사용할수 있게끔(?) 하는 것 같은데....잘모르겠다.

람다
=====
우리가 아는 자바는 객체를 기반으로 프로그랩을 구현하기 때문에 클래스를 만들고 그 안에
기능을 구현하는 메서드를 만들고 그것을 호출해야한다. 즉, 클래스가 없다면 호출할수 없다는
말과 같다. 그러나 함수의 구현과 호출만으로도 실행 가능한 방법이 있다. 그것이 바로 함수형
프로그래밍이라고 불리는 것이다. 이것을 우리는 람다식이라고 하는데, 람다식을 말하자면
코드로 보여 설명하겠다.
```java
일반코드
public class Ex{
    public static void main(String[] args) {
        
        int add(int x, int y) {
        return x+y;
        }
}
람다식
public static void main(String[] args) {
    (int x, int y)->{return x+y;}
    }
}
```
위 코드처럼 우리가 원래 썼던 클래스와 메서드 구현은 저렇게 클래스를 만들고,
변수를 설정해주고 그 구현을 하는 메서드를 사용함으로써 더하기 라는 것을 할 수 있게
코드를 구현한것인데, 람다식으로 하면 더 간결하게 나타낼수있다.

스트림
========
스트림은 우리가 배열을 사용하고 나서 그 배열을 다시 참조하여 어떤 값을 실행시킬때
간편하게 나타내기 위해서 사용하는 함수이다. 간단하게 코드를 사용하여 알아보자

```java
import java.lang.reflect.Array;
import java.util.Arrays;

public class IntArrays {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
    
        int sumArr = Arrays.stream(arr).sum();
        int count = (int) Arrays.stream(arr).count();

        System.out.println(sumArr);
        System.out.println(count);
    }
}
```
위 코드는 일단 먼저 arr이라는 변수에 1,2,3,4,5라는 숫자들을 배열로 정해놓고
sumArr이라는 integer변수에 Arrays.stream()이라는 배열 참조 스트림 함수를 괄호안에 있는
arr이라는 배열 값을 뒤에 .sum()으로 더해준다는 연산으로 다 더해주었다. 밑에있는 count 변수도
마찬가지 똑같이 해주는데 여기서 (int)가 들어간 이유는 count 함수가 반환될때 long 타입으로 반환된다고
한다. * 이부분은 나도 잘 모르겠다. * 