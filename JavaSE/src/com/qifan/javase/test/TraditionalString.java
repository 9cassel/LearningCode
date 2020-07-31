package com.qifan.javase.test;

public class TraditionalString {
    private char[] integer;
    private char[] decimal;
    private char[] map = {'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
    private char[] position = {'十','百','千','万','亿','角','分'};
    private boolean isZero = false;

    public String initialization(String str) {
        String[] strs = str.split("\\.");
        integer = append(strs[0]);
        decimal = append(strs[1]);
        String temp = TraditionalInteger(integer) + "元"+ TraditionalDecimal(decimal);
        return temp;
    }

    private char[] append(String str) {
        char[] cs = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            cs[i] = str.charAt(i);
        }
        return cs;
    }

    private String TraditionalInteger(char[] cs) {
        String temp = "";
        int count = 0;
        for (int i = cs.length - 1; i >= 0 ; i--) {
            count++;
            temp = getMap(cs[i],count) + mapLevel(count) + temp;
        }

        return temp;
    }


    private String TraditionalDecimal(char[] cs) {
        String temp = "";
        temp = getMap(cs[0],0) + position[5] + getMap(cs[1],0) + position[6];
        return temp;
    }

    private String getMap(char ch, int count) {
        if (ch == '0') {
            if (count % 4 == 1 || isZero) {
                isZero = true;
                return "";
            } else {
                isZero = true;
                return String.valueOf(map[0]);
            }
        } else {
            isZero = false;
            return String.valueOf(map[Integer.parseInt(String.valueOf(ch))]);
        }
    }

    private String mapLevel(int count) {
        String temp = "";
        if (isZero || count % 4  == 1) {
        } else {
            temp += position[(count - 2) % 4 ];
        }
        if (count == 5) {
            temp = temp + position[3];
            return temp;
        } else if (count == 9) {
            temp = temp + position[4];
            return temp;
        } else {
            return temp;
        }
    }


    public static void main(String[] args) {
        TraditionalString ts = new TraditionalString();
        String str = ts.initialization("100020.30");
        System.out.println(str);
    }
}
