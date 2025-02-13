package org.example.test.Service;

import org.example.test.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of( User.builder().userName("Meena").password("Meena").build()),
                Arguments.of( User.builder().userName("Siya").password("Siya").build()),
                Arguments.of( User.builder().userName("Ram").password("Ram").build()),
                Arguments.of( User.builder().userName("Parvati").password("Parvati").build()),
                Arguments.of( User.builder().userName("Shiva").password("").build())
        );

    }
}
