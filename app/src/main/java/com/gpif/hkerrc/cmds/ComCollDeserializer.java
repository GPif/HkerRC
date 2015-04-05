package com.gpif.hkerrc.cmds;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ComCollDeserializer implements JsonDeserializer<CommandsCollection> {
    public CommandsCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonArray arr = (JsonArray) json;
        List<Command> array = new ArrayList<>();
        for (int i = 0; i < arr.size() ; i++) {
            JsonObject jso = (JsonObject) arr.get(i);
            if (jso.getAsJsonPrimitive("command_class").getAsString()
                    .equals((new WOLCommand(null).getClassName()))) {
                JsonElement cmdjson = jso.getAsJsonObject("command_value");
                WOLCommand wol = WOLCommand.fromJsonElement(cmdjson);
                array.add(wol);
            }
        }
        CommandsCollection coll = new CommandsCollection();
        coll.setArray(array);
        return coll;
    }
}
