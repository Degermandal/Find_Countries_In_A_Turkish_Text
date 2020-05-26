import java.io.IOException;
import java.util.*;

/*
Proje Intellij IDEA kullanılarak kodlanmıştır.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Countries obj = new Countries();
        ArrayList<String> lst = obj.find();
        System.out.println(lst);
        String text = "Yeni Zelanda’da tek bir kara yılanı dahi yoktur. " +
                "Hatta hayvanat bahçelerinde bile yılan sergilenmez. "+
                "Dünyada bayrağı dörtgen şeklinde olmayan tek ülke Nepal’dir. "+
                "7 milyon nüfuslu Papua Yeni Gine’de 848 farklı dil konuşulur. "+
                "1948’den bu yana Kosta Rika’nın silahlı kuvvetleri, yani ordusu yoktur. "+
                "Amerika Birleşik Devletleri 50 eyaletten meydana gelen bir federal birliktir. "+
                "2 milyon 150 bin kilometrekare yüzölçümüyle Türkiye’nin 3 katı büyük olan " +
                "Suudi Arabistan’da tek bir nehir dahi yoktur. " +
                "İşgal yılları Irak’ın siyasi, sosyal ve ekonomik olarak sömürüldüğü kargaşa yıllarıdır. "+
                "Amerika'nın 1492'de Avrupalılar tarafından keşfinden sonra İspanyollar, Portekizliler, "+
                "Fransızlar ve İngilizler, buradaki yerli halkların aleyhine toprak sahibi oldular. ";
        Countries obj2 = new Countries(text, "file.txt");
        ArrayList<String> lst2 = obj2.find();
        System.out.println(lst2);
    }
}
