package com.gpif.hkerrc.cmds;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.List;

public class ComCollSerializer implements JsonSerializer<CommandsCollection> {
    public JsonElement serialize(CommandsCollection src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray arr = new JsonArray();
        List<Command> array = src.getArray();
        for (Command cmd : array) {
            JsonObject elt = new JsonObject();
            elt.addProperty("command_class",cmd.getClassName());
            elt.add("command_value",cmd.getJsonElement());
            arr.add(elt);
        }
        return arr;
    }
}
