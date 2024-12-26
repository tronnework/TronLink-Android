package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import com.polidea.rxandroidble2.internal.util.CharacteristicPropertiesParser;
public final class ConnectionModule_ProvideCharacteristicPropertiesParserFactory implements Factory<CharacteristicPropertiesParser> {
    private static final ConnectionModule_ProvideCharacteristicPropertiesParserFactory INSTANCE = new ConnectionModule_ProvideCharacteristicPropertiesParserFactory();

    public static ConnectionModule_ProvideCharacteristicPropertiesParserFactory create() {
        return INSTANCE;
    }

    @Override
    public CharacteristicPropertiesParser get() {
        return (CharacteristicPropertiesParser) Preconditions.checkNotNull(ConnectionModule.provideCharacteristicPropertiesParser(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CharacteristicPropertiesParser proxyProvideCharacteristicPropertiesParser() {
        return (CharacteristicPropertiesParser) Preconditions.checkNotNull(ConnectionModule.provideCharacteristicPropertiesParser(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
