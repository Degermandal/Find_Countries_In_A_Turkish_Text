import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;


/*
Text dosyasında verilen bütün ülkeler büyük harf ile başlandığı kabul edilmiştir.
Küçük harf ile başlayan ülke isimleri, ülke olarak kabul edilmemiş ve listeye eklenmemiştir.
 */

/*
Ülke isimlerini okuduğum dosyanın adı file.txt'dir. Ülkelerin kısa isimleri referans alınmıştır.
(ALMANYA FEDERAL CUMHURİYETİ yerine ALMANYA gibi.) Eğer dosyaya ülke ismi eklenmek istenilirse
sadece küçük harf kullanılarak yazılmalıdır. Bunun nedeni küçük harf kontrolü yapılmasıdır.
2 kelimeden daha fazla olan ülkeler için fillOtherC(...) fonksiyonu tanımlanmış ve fonksiyonda belirtilmiştir.
 */

public class Countries
{
    //******************** PRIVATE VARIABLES ******************************
    private String text;
    private String fileName;

    //******************** CONSTRUCTORS ******************************
    Countries(String text, String fileName) {
        this.text = text;
        this.fileName = fileName;
    }

    // Default constructor
    Countries() {

        this.text = "Yine paltosuz gelmiş, bir de peruk takmış. Gelirken yanında bilgisayarını " +
                "da getirmiş. Pis ve çapaçul bir durumda. Safran sarısı bir yüzü var. " +
                "Kafasının içinde kim bilir ne tilkiler dolaşıyor. Vaktinde üç roman yazan " +
                "adam bu mu? Zaman dünkü başarılı adamdan geriye kişiliksiz bir virane " +
                "bırakmış. Şimdi bir sanal manyak, tam bir manyak. Saçma sapan amaçlar " +
                "peşinde koşuyor. Ben insanları severim ama insanlar ayağını yorgana göre " +
                "uzatmalı. Davul bile dengi dengine çalar. Kim bilir kaç adam böyle sudan " +
                "sebeplerle ziyan oldu.";

        this.fileName = "file.txt";
    }

    Countries(String text) {
        this.text = text;
        this.fileName = "file.txt";
    }

    //******************** SETTERS AND GETTERS ******************************
    void setText(String text) {
        this.text = text;
    }

    String getText() {
        return this.text;
    }

    void setFileName(String fileName) {
        this.fileName = fileName;
    }

    String getFileName() {
        return this.fileName;
    }

    //******************** PRIVATE FUNCTIONS ******************************

    // Read file
    private ArrayList<String> readFile() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader(fileName));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = read.readLine();
        while (line != null)
        {
            listOfLines.add(line);
            line = read.readLine();
        }
        read.close();

        return listOfLines;
    }

    private String fillOtherC(String[] arrSplit, int k)
    {
        String temp = "";

        // İsmi 2 kelimeden oluşan ülkeler bu şekilde ifade edilmiş ve kontrolü yapılırken bu kelimeden sonra gelen kelime de kontrol edilmiştir.
        String[] firstN = {"bosna", "brunei", "burkina", "çek", "doğu", "dominik", "ekvator",
                "el", "fildişi", "gine", "güney", "güney", "güney", "demokratik", "güney", "kuzey",
                "kosta", "kuzey", "marşal", "orta", "saint", "san", "sierra", "soloman", "sri", "suudi", "yeni"};

        String[] secondN = {"hersek", "darusselam", "faso", "cumhuriyeti", "timur", "cumhuriyeti", "ginesi",
                "salvador", "sahili", "bissau", "afrika", "kıbrıs", "sudan", "kongo", "kore", "kore",
                "rika", "kıbrıs", "adaları", "afrika", "lucia", "marino", "leone", "adaları", "lanka",
                "arabistan", "zelanda"};

        for(int j = 0; j<firstN.length; ++j)
        {
            if (arrSplit[k].equals(firstN[j]) && arrSplit[k + 1].equals(secondN[j])) {
                temp = firstN[j] + " " + secondN[j];
            }
        }

        // Bu ülkeler ise ismi 3 kelimeden oluşan ülkelerdir.
        if (arrSplit[k].equals("birleşik") && arrSplit[k + 1].equals("arap")
                && arrSplit[k + 2].equals("emirlikleri")) {
            temp = "birleşik arap emirlikleri";
        }
        if (arrSplit[k].equals("papua") && arrSplit[k + 1].equals("yeni")
                && arrSplit[k + 2].equals("gine")) {
            temp = "papua yeni gine";
        }
        if (arrSplit[k].equals("antigua") && arrSplit[k + 1].equals("ve")
                && arrSplit[k + 2].equals("barbuda")) {
            temp = "antigua ve barbuda";
        }
        if (arrSplit[k].equals("trinidad") && arrSplit[k + 1].equals("ve")
                && arrSplit[k + 2].equals("tobago")) {
            temp = "trinidad ve tobago";
        }

        // Program hem Amerika kelimesini hem de Amerika Birleşik Devletleri kelimesini kabul etmektedir.
        int flag = 0;
        if (arrSplit[k].equals("amerika") && arrSplit[k + 1].equals("birleşik")
                && arrSplit[k + 2].equals("devletleri")) {
            temp = "amerika birleşik devletleri";
            flag = 1;
        }
        if (arrSplit[k].equals("amerika") && flag != 1) {
            temp = "amerika";
            flag = 0;
        }

        return temp;
    }

    // Türkçe karakter sorunu regular expression'ı değiştirerek ve Locale fonksiyonu kullanılarak çözülmüştür.
    // Noktalama işaretlerinin de kontrolü yapılmıştır.
    public ArrayList<String> find() throws IOException {
        ArrayList<String> countries = readFile();

        String[] arrSplit = text.replaceAll("[^a-zA-Z0-9ıiğüşöçIİĞÜŞÖÇ+$]", " ").split("\\s+");

        String[] bigStr = new String[10000];
        int s = 0;
        // Bu satırların amacı eğer kelime büyük harf ile başlıyor ise aranacak kelimeler arasına eklenmesini sağlamak içindir.
        for (String str : arrSplit) {
            if (Character.isUpperCase(str.charAt(0))) {
                bigStr[s] = str.toLowerCase(new Locale("tr", "TR"));
                s++;

            }
        }
        ArrayList<String> newList = new ArrayList<String>();

        // For döngüsü file.txt'deki kelimeri kontrol etmek içindir.
        // İf ise 2 veya 3 kelimeli ülkelerin kontrolü içindir.
        for(int k = 0; k<s; ++k)
        {
            for(int  j= 0; j<countries.size(); ++j)
            {
                if (bigStr[k].equals(countries.get(j))) {
                    newList.add(bigStr[k].toUpperCase(new Locale("tr","TR")));
                }
            }

            if(!fillOtherC(bigStr, k).equals(""))
                newList.add(fillOtherC(bigStr, k).toUpperCase(new Locale("tr","TR")));
        }

        return newList;
    }

}
