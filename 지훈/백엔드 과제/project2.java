package Techit;

class Student extends showInfo {
    String studentname;

    Student(String studentname, int money) {
        this.studentname = studentname;
        this.money = money;
    }
    int changes = money-income;
    void showInfo() {};

    void takeBus(Bus bus771) {};
    void takeTaxi(Taxi taxi0486) {};
    void takeSubway(Subway subway1) {};
}
class showInfo  {
    int number;
    int studentname;
    int passengers = 1;
    int income;
    int money;
}
class Bus extends showInfo{
    Bus(int number) {
        this.number = number;
    }
    String studentname = "Kim";
    int money = 10000;
    int income = 1500;
    int changes = money-income;

    void showInfo() {
        System.out.println(studentname + "의 남은 돈은" + (changes)+ "원 입니다.");
        System.out.println("버스"+number+"번의 승객은"+passengers+"명이고, 수입은"+income+"원 입니다.");
    }
}
class Taxi extends showInfo{
    Taxi(int number){
        this.number = number;
    }
    String studentname = "Lee";
    int money = 45000;
    int income = 4500;
    int changes = money-income;
    void showInfo() {
        System.out.println(studentname + "의 남은 돈은" + changes + "원 입니다.");
        System.out.println("택시"+number+"번의 승객은"+passengers+"명이고, 수입은"+income+"원 입니다.");
    }
}
class Subway extends showInfo{
    String subway;
    String studentname = "Park";
    int money = 5000;
    int income = 1500;
    int changes = money-income;
    Subway(String subway) {
        System.out.println(studentname + "의 남은 돈은" + changes + "원 입니다.");
        this.subway = subway;
    }


    void showInfo() {
        System.out.println("지하철"+subway+"의 승객은"+passengers+"명이고, 수입은"+income+"원 입니다.");
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

