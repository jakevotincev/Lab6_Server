import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * <p>Класс, использующийся для создания объекта Animal из строки Json</p>
 *
 * @author Вотинцев Евгений
 * @version 1.0
 */

public class AnimalFactory {
    /**
     * @param json Строка Json
     * @return Объект Animal
     * @throws NumberFormatException  Указан не верный формат атрибута
     * @throws ClassCastException     Атрибут класс не найден
     * @throws ClassNotFoundException Не указан атрибут класс
     * @throws JsonSyntaxException    Ошибка синтаксиса строки Json
     */
    public static Object createAnimal(String json) throws NumberFormatException, ClassCastException, ClassNotFoundException, JsonSyntaxException {
        Gson gson = new Gson();
        Map map;
        Type type = new TypeToken<LinkedTreeMap<String, Object>>() {
        }.getType();
        Object object = null;
        map = gson.fromJson(json, type);
        if (map.containsKey("Sova")) {
            Sova sova = new Sova();
            if (map.containsKey("weight")) sova.setWeight((Double) map.get("weight"));
            if (map.containsKey("height")) sova.setHeight((Double) map.get("height"));
            if (map.containsKey("width")) sova.setWidth((Double) map.get("width"));
            if (map.containsKey("name")) sova.setName(map.get("name").toString());
            if (map.containsKey("color")) sova.setColor(map.get("color").toString());
            if (map.containsKey("x")) sova.setX((Double) map.get("x"));
            if (map.containsKey("y")) sova.setY((Double) map.get("y"));
            if (map.get("Sova").toString().startsWith("{iq")) {
                String iq = map.get("Sova").toString().replace("{iq=", "").replace("}", "");
                sova.setIq(Double.parseDouble(iq));
            }
            object = sova;
        } else if (map.containsKey("WinniePooh")) {
            WinniePooh pooh = new WinniePooh();
            if (map.containsKey("weight")) pooh.setWeight((Double) map.get("weight"));
            if (map.containsKey("height")) pooh.setHeight((Double) map.get("height"));
            if (map.containsKey("width")) pooh.setWidth((Double) map.get("width"));
            if (map.containsKey("name")) pooh.setName(map.get("name").toString());
            if (map.containsKey("color")) pooh.setColor(map.get("color").toString());
            if (map.containsKey("x")) pooh.setX((Double) map.get("x"));
            if (map.containsKey("y")) pooh.setY((Double) map.get("y"));
            if (map.get("WinniePooh").toString().startsWith("{iq")) {
                String iq = map.get("WinniePooh").toString().replace("{iq=", "").replace("}", "");
                pooh.setIq(Double.parseDouble(iq));
            }
            object = pooh;
        } else if (map.containsKey("Pyatachok")) {
            Pyatachok pyat = new Pyatachok();
            if (map.containsKey("weight")) pyat.setWeight((Double) map.get("weight"));
            if (map.containsKey("height")) pyat.setHeight((Double) map.get("height"));
            if (map.containsKey("width")) pyat.setWidth((Double) map.get("width"));
            if (map.containsKey("name")) pyat.setName(map.get("name").toString());
            if (map.containsKey("color")) pyat.setColor(map.get("color").toString());
            if (map.containsKey("x")) pyat.setX((Double) map.get("x"));
            if (map.containsKey("y")) pyat.setY((Double) map.get("y"));
            if (map.get("Pyatachok").toString().startsWith("{iq")) {
                String iq = map.get("Pyatachok").toString().replace("{iq=", "").replace("}", "");
                pyat.setIq(Double.parseDouble(iq));
            }
            object = pyat;
        } else throw new ClassNotFoundException();
        return object;
    }
}
