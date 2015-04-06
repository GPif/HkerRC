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
            JsonElement cmdjson = jso.getAsJsonObject("command_value");
            String cmd_class = jso.getAsJsonPrimitive("command_class").getAsString();
            array.add(CommandFactory.getFromJson(cmd_class,cmdjson));
        }
        CommandsCollection coll = new CommandsCollection();
        coll.setArray(array);
        return coll;
    }
}
