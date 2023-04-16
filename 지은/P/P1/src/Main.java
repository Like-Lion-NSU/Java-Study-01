//class D{
//    int num; //번호
//    static int hu=0; //사람수
//    int M=0; //돈
//}
//
//class Bus extends D{
//    int M=1500; //왜 빨간줄이야 상속했다메 했다메!!!!!!!!!!!!!!!
//    Bus(){}
//    Bus(int num){
//        this.num = num;
//    }
//    void sI(int num, int hu, int M){
//        String num = this.num; //학생은 되는데 왜 너는
//        int hu = this.hu;
//        int M = this.M;
//        System.out.printf("%s번의 승객은 %d명이고, 수입은 %d원 입니다.", num, hu, M);
//    }
//}
//class St{
//    static String name;
//    static int m;
//    static int num;
//
//    void takeBus (int num){
//        this.m -= 1500;
//        this.num=num;
//    }
//
//    void sI(){
//        String name = this.name;
//        int m = this.m;
//        System.out.printf("%s의 남은 돈은 %d원 입니다." , name, m);
//    }
//    St(){}
//    St(String name, int m){
//        this.name = name;
//        this.m=m;
//    }
//}
//public class Main {
//    public static void main(String[] args) {
//        St SK = new St("Kim",10000);
//        St SL = new St("Lee",45000);
//        St SP = new St("Park",5000); //이거 근데 this로 하면 마지막만 저장되는데 어카지 : 일단 한 명만 살린다.....
//
//        Bus bus771 = new Bus(771); //일단 버스만 잡는다
//        SK.takeBus(bus771.num); //num안붙이고 가능한가
//        SK.sI();
//        bus771.sI();
//    }
//}
////진자 뭐가 문제인데 뭐가 왜 안 되는 건데 진자 뭐가 문제인건데에에에에엑
