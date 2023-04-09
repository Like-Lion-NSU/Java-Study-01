package Techit;

import org.w3c.dom.ls.LSOutput;

class Student {
    String studentname;
    int money;
    Student(String studentname, int money) {
        this.studentname = studentname;
        this.money = money;
    }
    void takeBus(Bus bus771) {money= money-bus771.fee; bus771.passengers++; bus771.income = bus771.fee;}
    void takeTaxi(Taxi taxi0406) {money= money-taxi0406.fee;taxi0406.income = taxi0406.fee; taxi0406.passengers++;}
    void takeSubway(Subway subway1) {money= money-subway1.fee;subway1.passengers++;subway1.income = subway1.fee;}
    void showInfo() {
        System.out.println(studentname + "의 남은 돈은 " + money+ "원 입니다.");}
}
class Transfer {
    int number;
    String subwayLine;
    int passengers;
    int income;


}
class Bus extends Transfer {
    Bus(int number) {
        this.number = number;
    }
    int fee = 1500;

    void showInfo() {
        System.out.println("버스"+number+"번의 승객은 "+passengers+"명이고, 수입은 "+ fee +"원 입니다.");
    }

}
class Taxi extends Transfer {
    Taxi(int number) {
        this.number = number;
    }
    int fee = 4500;
    void showInfo() {
        System.out.println("택시"+number+"번의 승객은 "+ passengers+"명이고, 수입은 "+ fee +"원 입니다.");
    }
}
class Subway extends Transfer {
    Subway(String subwayLine) {
        this.subwayLine = subwayLine;
    }
    int fee = 1500;
    void showInfo() {
        System.out.println("지하철"+subwayLine+"호선의 승객은"+ passengers+"명이고, 수입은 "+ fee +"원 입니다.");
    }
}
public class project2 {
    public static void main(String[] args) {
        Student studentKim = new Student("Kim",10000);
        Student studentLee = new Student("Lee",45000);
        Student studentPark = new Student("Park",5000);

        Bus bus771 = new Bus(771);
        studentKim.takeBus(bus771);
        studentKim.showInfo();
        bus771.showInfo();

        Taxi taxi0406 = new Taxi(0406);
        studentLee.takeTaxi(taxi0406);
        studentLee.showInfo();
        taxi0406.showInfo();

        Subway subway1 = new Subway("1호선");
        studentPark.takeSubway(subway1);
        studentPark.showInfo();
        subway1.showInfo();


    }
}

