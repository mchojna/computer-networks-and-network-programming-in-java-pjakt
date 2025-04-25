public class Main{
    public static void main(String[] args){
//        zad 3
//        TCPClient client = new TCPClient("10.22.34.24", 9007);
//        System.out.println(client.getMessage());
//        client.sendMessage("Hello server");
//        System.out.println(client.getMessage());
//        client.close();

//      zad 1
//        TCPClient2 client2 = new TCPClient2("10.22.34.24", 9005);
//        System.out.println(client2.getMessage());
//        client2.close();

//      zad 2
        TCPClient3 client3 = new TCPClient3("10.22.34.24", 9006);
        System.out.println(client3.getMessage());
        client3.close();
    }
}