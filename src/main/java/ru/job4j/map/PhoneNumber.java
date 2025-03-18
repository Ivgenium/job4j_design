package ru.job4j.map;

public class PhoneNumber {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "line num");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ": " + val);
        }
        return (short) val;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneNumber that = (PhoneNumber) o;
        return areaCode == that.areaCode && prefix == that.prefix && lineNum == that.lineNum;
    }

    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = (result << 5) - result + Short.hashCode(lineNum); // 31 * result
        return result;
    }

    public short getLineNum() {
        return lineNum;
    }

    public short getPrefix() {
        return prefix;
    }

    public short getAreaCode() {
        return areaCode;
    }

    public static int hashCodeString(String str) {
        char[] temp = str.toCharArray();
        byte[] value = new byte[temp.length];
        for (int i = 0; i < temp.length; i++) {
            value[i] = (byte) temp[i];
        }
        int h = 0;
        for (byte v : value) {
            h = h + (v & 0xff);
        }
        return h;
    }

    public static void main(String[] args) {
        PhoneNumber phoneNumber1 = new PhoneNumber(905, 654, 7842);
        PhoneNumber phoneNumber2 = new PhoneNumber(906, 125, 3389);
        System.out.printf("phoneNumber1 - хэш-код: %s\n", phoneNumber1.hashCode());
        System.out.printf("phoneNumber2 - хэш-код: %s\n", phoneNumber2.hashCode());
        System.out.printf("thing - хэш-код: %s\n", hashCodeString("thing"));
        System.out.printf("night - хэш-код: %s\n", hashCodeString("night"));
    }
}
