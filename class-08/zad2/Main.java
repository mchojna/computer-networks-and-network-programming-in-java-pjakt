package zad2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Client client = new Client();
        client.sendMessage("s29758 SvNnmxAoSP\n");

        // zad 1
        {
            double sum = 0;
            for(int i = 0; i < 30; i++){
                String c = client.getMessage().trim();
                sum += Double.parseDouble(c);
            }
            String s = String.valueOf(sum);

            String[] sa = s.split("\\.");

            client.sendMessage(sa[0].charAt(sa[0].length()-1) + "\n");
            client.sendMessage(sa[0].charAt(sa[0].length()-2) + "\n");

            System.out.println(client.getMessage());
        }

        // zad 2
        {
            String s = client.getMessage().trim();
            String[] sa = s.split(" ");

            BigDecimal bd = new BigDecimal(0);

            for(int i = 0; i < sa.length; i++){
                bd.add(new BigDecimal(sa[i]));
            }

            String num = bd.toString();
            int sumOfDigits = 0;

            for(int i = 0; i < num.length(); i++){
                sumOfDigits += Integer.parseInt(String.valueOf(num.charAt(i)));
            }
            client.sendMessage(sumOfDigits + "\n");


            for(int i = 0; i < bd.toString().length(); i++){
                client.sendMessage(bd.toString().length() + "\n");
            }
        }

        // zad 3
        {
            boolean check = true;

            while(check){
                List<Double> list = new ArrayList();

                String[] str1 = client.getMessage().trim().split(" ");
                for(int i = 0; i < str1.length; i++){
                    list.add(Double.parseDouble(str1[i]));
                }

                String[] str2 = client.getMessage().trim().split(" ");
                for(int i = 0; i < str1.length; i++){
                    list.add(Double.parseDouble(str2[i]));
                }

                if(list.get(0) > list.get(1) && list.get(1) > list.get(2) && list.get(2) > list.get(3)){
                    client.sendMessage("TAKK\n");
                }else{
                    client.sendMessage("NIEE\n");
                    check = false;
                }
            }
            client.close();
        }
    }
}
