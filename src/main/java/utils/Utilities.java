package utils;

import com.google.gson.*;
public class Utilities {
    public static String getFieldValueFromResult(String json,String fieldName) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonElement fieldValue;

        if (jsonObject.has("result")) {
            JsonObject jsonResult = jsonObject.getAsJsonObject("result");
            if (jsonResult.has(fieldName)) {
                fieldValue = jsonResult.get(fieldName);
            } else {
                return "The second Json does not have a" + "\"" + fieldName + "\"" + " field";
            }
        } else {
            return "The big Json does not have a \"result\" field";
        }
        return fieldValue.toString();
    }
}
