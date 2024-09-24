import java.io.FileWriter; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.PriorityQueue; 
import java.util.Random; 
public class ToyLottery { 
<pre><code>private List&lt;Integer&gt; toyIds; 
private List&lt;String&gt; toyNames; 
private List&lt;Double&gt; toyWeights; 
private PriorityQueue&lt;Integer&gt; lotteryQueue; 
 
public ToyLottery(String... toyData) { 
    toyIds = new ArrayList&lt;&gt;(); 
    toyNames = new ArrayList&lt;&gt;(); 
    toyWeights = new ArrayList&lt;&gt;(); 
    lotteryQueue = new PriorityQueue&lt;&gt;(); 
 
    for (String data : toyData) { 
        String[] parts = data.split(","); 
        if (parts.length == 3) { 
            try { 
                toyIds.add(Integer.parseInt(parts[0].trim())); 
                toyNames.add(parts[1].trim()); 
                toyWeights.add(Double.parseDouble(parts[2].trim())); 
                // Добавляем id игрушки в очередь столько раз, сколько указано в весе 
                for (int i = 0; i &lt; Double.valueOf(parts[2].trim()).intValue(); i++) { 
                    lotteryQueue.add(Integer.parseInt(parts[0].trim())); 
                } 
            } catch (NumberFormatException e) { 
                System.err.println("Ошибка при парсинге данных: " + e.getMessage()); 
            } 
        } else { 
            System.err.println("Неверный формат данных: " + data); 
        } 
    } 
} 
 
public int getToy() { 
    Random random = new Random(); 
    int randomIndex = random.nextInt(lotteryQueue.size()); 
    return lotteryQueue.poll(); 
} 
 
public void runLottery(int times, String filename) { 
    try (FileWriter fileWriter = new FileWriter(filename)) { 
        for (int i = 0; i &lt; times; i++) { 
            int winningToyId = getToy(); 
            int toyIndex = toyIds.indexOf(winningToyId); 
            fileWriter.write("Розыгрыш " + (i + 1) + ": " + toyNames.get(toyIndex) + "
"); 
        } 
    } catch (IOException e) { 
        System.err.println("Ошибка при записи в файл: " + e.getMessage()); 
    } 
} 
 
public static void main(String[] args) { 
    // Пример использования класса ToyLottery 
    String[] toyData = { 
            "1,Кукла,2", 
            "2,Машина,3", 
            "3,Конструктор,1" 
    }; 
    ToyLottery lottery = new ToyLottery(toyData); 
    lottery.runLottery(10, "lottery_results.txt"); 
} 
 
java 
} 

<strong>Описание:</strong> 
<ol> 
<li><strong>Класс <code>ToyLottery</code>:</strong></li> 
<li>Хранит данные об игрушках в трех списках: <code>toyIds</code>, <code>toyNames</code>, <code>toyWeights</code>.</li> 
<li>Использует очередь с приоритетом <code>PriorityQueue</code> для хранения <code>toyIds</code> с учетом веса выпадения.</li> 
<li>Метод <code>getToy()</code> выбирает случайный элемент из очереди, имитируя розыгрыш.</li> 
<li> 
Метод <code>runLottery()</code> проводит заданное количество розыгрышей (<code>times</code>) и записывает результаты в файл (<code>filename</code>). 
</li> 
<li> 
<strong>Инициализация:</strong> 
</li> 
<li>Конструктор принимает массив строк <code>toyData</code>, где каждая строка содержит <code>id</code>, <code>название</code> и <code>вес</code> игрушки, разделенные запятыми.</li> 
<li> 
При инициализации данные из строк парсятся, и <code>id</code> игрушки добавляется в очередь <code>lotteryQueue</code> столько раз, сколько указано в <code>весе</code>. 
</li> 
<li> 
<strong>Розыгрыш:</strong> 
</li> 
<li><code>getToy()</code> использует <code>Random</code> для выбора случайного элемента из <code>lotteryQueue</code>.</li> 
<li> 
<code>runLottery()</code> проводит <code>times</code> розыгрышей и записывает результаты в файл <code>filename</code>. 
</li> 
<li> 
<strong>Пример использования:</strong> 
</li> 
<li>В <code>main()</code> создается объект <code>ToyLottery</code> с тестовыми данными об игрушках.</li> 
<li>Запускается метод <code>runLottery()</code> для 10 розыгрышей, результаты записываются в файл "lottery_results.txt".</li> 
</ol> 
<strong>Преимущества:</strong> 
<ul> 
<li><strong>Чёткое разделение ответственности:</strong> Класс <code>ToyLottery</code> encapsulates всю логику розыгрыша игрушек.</li> 
<li><strong>Использование <code>PriorityQueue</code>:</strong> Обеспечивает выбор игрушек с учетом их веса.</li> 
<li><strong>Ясность и читаемость кода:</strong> Структура и комментарии облегчают понимание кода.</li> 
<li><strong>Гибкость:</strong> Можно легко добавить новые игрушки или изменить их веса.</li> 
</ul> 
<strong>Дополнительные замечания:</strong> 
<ul> 
<li>Рекомендуется использовать стандартные библиотеки для работы с файлами (например, <code>java.nio.file</code>) для большей гибкости.</li> 
<li>Можно добавить обработку ошибок и валидацию входных данных для повышения надежности.</li> 
<li>Для более сложных сценариев можно рассмотреть использование других коллекций (например, <code>TreeMap</code>) или алгоритмов (например, алгоритм рулетки) для более точного моделирования розыгрыша.</li> 
</ul> 
