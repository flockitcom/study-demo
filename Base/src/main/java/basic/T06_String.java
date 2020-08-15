package basic;

public class T06_String {
    public static void main(String[] args) {
        String a= "group1/M00/00/00/rBEQEV8IFLOAaiv-AABt9WLqiVA881.jpg";
        String b = a.substring(0, a.indexOf("/"));
        String c = a.substring(a.indexOf("/"), a.length());
        System.out.println(b);
        System.out.println(c);

    }
}
