package the.flash.Serializer;

public interface Serializer {
    byte getSerializerAlgorithm();
    byte[]  serialize(Object obj);
    <T> T unserialize(Class<T> clazz, byte[] bytes);
    Serializer DEFAULT = new JsonSerializer();
}
