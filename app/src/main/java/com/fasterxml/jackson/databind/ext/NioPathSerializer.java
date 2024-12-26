package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.lxj.xpopup.util.XPermission$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
public class NioPathSerializer extends StdScalarSerializer<Path> {
    private static final long serialVersionUID = 1;

    @Override
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        serialize(XPermission$ExternalSyntheticApiModelOutline0.m(obj), jsonGenerator, serializerProvider);
    }

    public NioPathSerializer() {
        super(XPermission$ExternalSyntheticApiModelOutline0.m());
    }

    public void serialize(Path path, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        URI uri;
        uri = path.toUri();
        jsonGenerator.writeString(uri.toString());
    }
}
